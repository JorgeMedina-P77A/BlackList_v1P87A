package com.example.jorge.listanegra_v1.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jorge on 07/03/2018.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    //CONSTRUCCION DE LA BD;
    private static final String DB_NAME = "db_BlackList";
    private static final int DB_VERSION = 2;



    public static final String[]COLUMNS_LISTANEGRA = {"_id","number","name","block_tel","block_sms"};
    public static final String TABLE_LISTANEGRA_NAME="listaNegra";

    private  final String TABLE_LISTANEGRA = "create table listaNegra ("+
            COLUMNS_LISTANEGRA[0]+" integer primary key autoincrement, "+
            COLUMNS_LISTANEGRA[1]+" varchar(100) not null," +
            COLUMNS_LISTANEGRA[2]+" varchar(100) not null,"+
            COLUMNS_LISTANEGRA[3]+" integer null,"+
            COLUMNS_LISTANEGRA[4]+" integer null);";



    public static final String[]COLUMNS_REGISTROS = {"_id","number","name","block_tipo","structure_sms","time"};
    public static final String TABLE_REGISTROS_NAME="registros";

    private  final String TABLE_REGISTROS = "create table registros ("+
            COLUMNS_REGISTROS[0]+" integer primary key autoincrement, "+
            COLUMNS_REGISTROS[1]+" varchar(100) not null," +
            COLUMNS_REGISTROS[2]+" varchar(100) not null,"+
            COLUMNS_REGISTROS[3]+" integer null,"+
            COLUMNS_REGISTROS[4]+" varchar(200) null,"+
            COLUMNS_REGISTROS[5]+" varchar(50) null);";








    public DBOpenHelper(Context contexto) {

        super(contexto, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {

            sqLiteDatabase.execSQL(TABLE_LISTANEGRA);
            sqLiteDatabase.execSQL(TABLE_REGISTROS);

        }catch (Exception err){}

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try{

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS listaNegra");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS registros");
            onCreate(sqLiteDatabase);

        }catch (Exception err){}

    }



}
