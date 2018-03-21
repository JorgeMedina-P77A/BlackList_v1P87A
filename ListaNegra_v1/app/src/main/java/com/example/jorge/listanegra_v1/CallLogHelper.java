package com.example.jorge.listanegra_v1;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by Jorge on 20/03/2018.
 */

public class CallLogHelper {

    public void eliminar(Context contexto){

        try {

            Uri CALLLOG_URI = Uri.parse("content://call_log/calls");

        }catch (Exception err){

            Toast.makeText(contexto.getApplicationContext(),"CALLOGHELPER - "+err.getMessage(),Toast.LENGTH_LONG).show();

        }

    }

}
