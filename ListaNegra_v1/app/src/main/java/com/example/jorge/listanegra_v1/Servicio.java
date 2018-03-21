package com.example.jorge.listanegra_v1;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.provider.CallLog;
import android.support.annotation.Nullable;

import com.example.jorge.listanegra_v1.Datos.DaoListaNegra;
import com.example.jorge.listanegra_v1.Datos.POJO_ListaNegra;

import java.util.List;

/**
 * Created by Jorge on 20/03/2018.
 */

public class Servicio extends Service{

    @Override
    public void onCreate() {

    }

    hilo h;

    @Override
    public int onStartCommand(Intent intent, int flag, int idProcess) {

        try {
            if (h == null) {
                h = new hilo();
                h.start();
            }
        } catch (Exception err) {

        }

        return START_STICKY;

    }

    @Override
    public void onDestroy() {

        try {
            if (h.isAlive()) {
                h.stop();
            }
        } catch (Exception err) {

        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public class hilo extends Thread {

        @SuppressLint("MissingPermission")
        @Override
        public void run() {

            while (true) {


                try {

                    DaoListaNegra dao = new DaoListaNegra(getBaseContext());
                    List<POJO_ListaNegra> lista = dao.buscarBLOC_Tel(1);

                    for (int i = 0; i < lista.size(); i++) {

                        Uri CALLLOG_URI = Uri.parse("content://call_log/calls");
                        getApplicationContext().getContentResolver().delete(CALLLOG_URI, CallLog.Calls.NUMBER + "=?", new String[]{lista.get(i).getNumber()});

                    }

                } catch (Exception e) {

                }

                //Uri CALLLOG_URI = Uri.parse("content://call_log/calls");
                //getApplicationContext().getContentResolver().delete(CALLLOG_URI, CallLog.Calls.NUMBER + "=?", new String[]{"4454563056"});


            }

        }

    }

}
