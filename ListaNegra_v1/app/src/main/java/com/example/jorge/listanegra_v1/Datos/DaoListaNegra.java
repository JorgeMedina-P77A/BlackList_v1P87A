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

public class DaoListaNegra {

    private Context _contexto;
    private SQLiteDatabase _midb;


    //CREACION DE LA DB;
    public DaoListaNegra(Context contexto){

        this._contexto = contexto;
        this._midb = new DBOpenHelper(contexto).getWritableDatabase();

    }

    //INSERTAR DATOS;
    public long insert(POJO_ListaNegra c){

        ContentValues cv = new ContentValues();

        cv.put(DBOpenHelper.COLUMNS_LISTANEGRA[1],c.getNumber());
        cv.put(DBOpenHelper.COLUMNS_LISTANEGRA[2],c.getName());
        cv.put(DBOpenHelper.COLUMNS_LISTANEGRA[3],c.getBlock_tel());
        cv.put(DBOpenHelper.COLUMNS_LISTANEGRA[4],c.getBlock_sms());

        return _midb.insert(DBOpenHelper.TABLE_LISTANEGRA_NAME,null,cv) ;

    }

    //ACTUALIZAR DATOS;
    public long update(POJO_ListaNegra c){

        ContentValues cv = new ContentValues();

        cv.put(DBOpenHelper.COLUMNS_LISTANEGRA[1],c.getNumber());
        cv.put(DBOpenHelper.COLUMNS_LISTANEGRA[2],c.getName());
        cv.put(DBOpenHelper.COLUMNS_LISTANEGRA[3],c.getBlock_tel());
        cv.put(DBOpenHelper.COLUMNS_LISTANEGRA[4],c.getBlock_sms());

        return _midb.update(DBOpenHelper.TABLE_LISTANEGRA_NAME, cv, "_id=?", new String[] { String.valueOf( c.getId_alerta())});

    }

    //ELIMINAR DATOS;
    public int delete(String id){

        return  _midb.delete("listaNegra","_id='"+id+"'",null);

    }

    //CREACION DE LISTA;
    public List<POJO_ListaNegra> buscarTodos() {

        List<POJO_ListaNegra> notesArrayList = new ArrayList<POJO_ListaNegra>();
        String selectQuery = "SELECT * FROM listaNegra";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                POJO_ListaNegra notas = new POJO_ListaNegra();
                notas.setId_alerta(c.getInt(c.getColumnIndex("_id")));
                notas.setNumber(c.getString(c.getColumnIndex("number")));
                notas.setName(c.getString(c.getColumnIndex("name")));
                notas.setBlock_tel(c.getInt(c.getColumnIndex("block_tel")));
                notas.setBlock_sms(c.getInt(c.getColumnIndex("block_sms")));

                notesArrayList.add(notas);

            } while (c.moveToNext());

        }

        return notesArrayList;

    }

    //BUSQUEDA POR ID;
    public POJO_ListaNegra buscarUno(int iden) {

        POJO_ListaNegra notesUno = new POJO_ListaNegra();
        String selectQuery = "SELECT * FROM listaNegra WHERE _id = '"+iden+"'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                POJO_ListaNegra notas = new POJO_ListaNegra();
                notas.setId_alerta(c.getInt(c.getColumnIndex("_id")));
                notas.setNumber(c.getString(c.getColumnIndex("number")));
                notas.setName(c.getString(c.getColumnIndex("name")));
                notas.setBlock_tel(c.getInt(c.getColumnIndex("block_tel")));
                notas.setBlock_sms(c.getInt(c.getColumnIndex("block_sms")));

                notesUno = notas;

            } while (c.moveToNext());

        }

        return notesUno;

    }

    //BUSQUEDA POR TELEFONO;
    public POJO_ListaNegra buscarTel(String iden) {

        POJO_ListaNegra notesUno = new POJO_ListaNegra();
        String selectQuery = "SELECT * FROM listaNegra WHERE number = '"+iden+"'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                POJO_ListaNegra notas = new POJO_ListaNegra();
                notas.setId_alerta(c.getInt(c.getColumnIndex("_id")));
                notas.setNumber(c.getString(c.getColumnIndex("number")));
                notas.setName(c.getString(c.getColumnIndex("name")));
                notas.setBlock_tel(c.getInt(c.getColumnIndex("block_tel")));
                notas.setBlock_sms(c.getInt(c.getColumnIndex("block_sms")));

                notesUno = notas;

            } while (c.moveToNext());

        }

        return notesUno;

    }

    // BUSCAR POR BLOQUEO TEL;
    public List<POJO_ListaNegra> buscarBLOC_Tel(int iden) {

        List<POJO_ListaNegra> notesArrayList = new ArrayList<POJO_ListaNegra>();
        String selectQuery = "SELECT * FROM listaNegra WHERE block_tel = '"+iden+"'";
        Log.d("", selectQuery);
        SQLiteDatabase db = this._midb;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                POJO_ListaNegra notas = new POJO_ListaNegra();
                notas.setId_alerta(c.getInt(c.getColumnIndex("_id")));
                notas.setNumber(c.getString(c.getColumnIndex("number")));
                notas.setName(c.getString(c.getColumnIndex("name")));
                notas.setBlock_tel(c.getInt(c.getColumnIndex("block_tel")));
                notas.setBlock_sms(c.getInt(c.getColumnIndex("block_sms")));

                notesArrayList.add(notas);

            } while (c.moveToNext());

        }

        return notesArrayList;

    }

}
