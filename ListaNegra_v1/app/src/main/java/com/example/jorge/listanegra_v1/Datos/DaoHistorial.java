package com.example.jorge.listanegra_v1.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 07/03/2018.
 */

public class DaoHistorial {

    private Context _contexto;
    private SQLiteDatabase _midb;


    //CREACION DE LA DB;
    public DaoHistorial(Context contexto){

        this._contexto = contexto;
        this._midb = new DBOpenHelper(contexto).getWritableDatabase();

    }

    //INSERTAR DATOS;
    public long insert(POJO_Historial c){

        ContentValues cv = new ContentValues();

        cv.put(DBOpenHelper.COLUMNS_REGISTROS[1],c.getNumber());
        cv.put(DBOpenHelper.COLUMNS_REGISTROS[2],c.getName());
        cv.put(DBOpenHelper.COLUMNS_REGISTROS[3],c.getBlock_tipo());
        cv.put(DBOpenHelper.COLUMNS_REGISTROS[4],c.getStructure_SMS());
        cv.put(DBOpenHelper.COLUMNS_REGISTROS[5],c.getTime());

        return _midb.insert(DBOpenHelper.TABLE_REGISTROS_NAME,null,cv) ;

    }

    //ACTUALIZAR DATOS;
    public long update(POJO_Historial c){

        ContentValues cv = new ContentValues();

        cv.put(DBOpenHelper.COLUMNS_REGISTROS[1],c.getNumber());
        cv.put(DBOpenHelper.COLUMNS_REGISTROS[2],c.getName());
        cv.put(DBOpenHelper.COLUMNS_REGISTROS[3],c.getBlock_tipo());
        cv.put(DBOpenHelper.COLUMNS_REGISTROS[4],c.getStructure_SMS());
        cv.put(DBOpenHelper.COLUMNS_REGISTROS[5],c.getTime());

        return _midb.update(DBOpenHelper.TABLE_REGISTROS_NAME, cv, "_id=?", new String[] { String.valueOf( c.getId_registro())});

    }

    //ELIMINAR DATOS;
    public int delete(String id){

        return  _midb.delete("registros","_id='"+id+"'",null);

    }

    //CREACION DE LISTA;
    public List<POJO_Historial> buscarTodos(int tipo) {

        List<POJO_Historial> notesArrayList = new ArrayList<POJO_Historial>();
        String selectQuery = "SELECT * FROM registros WHERE block_tipo = '"+tipo+"'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                POJO_Historial notas = new POJO_Historial();
                notas.setId_registro(c.getInt(c.getColumnIndex("_id")));
                notas.setNumber(c.getString(c.getColumnIndex("number")));
                notas.setName(c.getString(c.getColumnIndex("name")));
                notas.setBlock_tipo(c.getInt(c.getColumnIndex("block_tipo")));
                notas.setStructure_SMS(c.getString(c.getColumnIndex("structure_sms")));
                notas.setTime(c.getString(c.getColumnIndex("time")));

                notesArrayList.add(notas);

            } while (c.moveToNext());

        }

        return notesArrayList;

    }

    //BUSQUEDA POR ID;
    public POJO_Historial buscarUno(int iden) {

        POJO_Historial notesUno = new POJO_Historial();
        String selectQuery = "SELECT * FROM registros WHERE _id = '"+iden+"'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                POJO_Historial notas = new POJO_Historial();
                notas.setId_registro(c.getInt(c.getColumnIndex("_id")));
                notas.setNumber(c.getString(c.getColumnIndex("number")));
                notas.setName(c.getString(c.getColumnIndex("name")));
                notas.setBlock_tipo(c.getInt(c.getColumnIndex("block_tipo")));
                notas.setStructure_SMS(c.getString(c.getColumnIndex("structure_sms")));
                notas.setTime(c.getString(c.getColumnIndex("time")));

                notesUno = notas;

            } while (c.moveToNext());

        }

        return notesUno;

    }



}
