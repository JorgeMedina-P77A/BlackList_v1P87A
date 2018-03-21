package com.example.jorge.listanegra_v1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorge.listanegra_v1.Datos.DaoHistorial;
import com.example.jorge.listanegra_v1.Datos.POJO_Historial;

/**
 * Created by Jorge on 08/03/2018.
 */

public class ActivityRegistros extends AppCompatActivity {

    int type_flag = 0;

    ListView lista;
    TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        lista =(ListView)findViewById(R.id.lst_registros);

        Bundle datos = this.getIntent().getExtras();
        int recupera_tipo = datos.getInt("tipo_integer");
        type_flag = recupera_tipo;

        titulo = (TextView)findViewById(R.id.titulo_registro);

        if(type_flag==0){

            //Toast.makeText(ActivityRegistros.this, "RAGISTRO CALLS", Toast.LENGTH_LONG).show();
            titulo.setText("REGISTRO LLAMADAS");
            cargaCALLS(0);

        }else if(type_flag==1){

            //Toast.makeText(ActivityRegistros.this, "RAGISTRO SMS", Toast.LENGTH_LONG).show();
            titulo.setText("REGISTRO MENSAJES");
            cargaCALLS(1);

        }





    }



    public void btnRefresh(View view) {

        Toast.makeText(ActivityRegistros.this, "ACTUALIZA LISTA", Toast.LENGTH_LONG).show();

        if(type_flag==0){
            cargaCALLS(0);
        }else if(type_flag==1){
            cargaCALLS(1);
        }

    }

    ArrayAdapter<POJO_Historial> adp;
    public void cargaCALLS(int tipo){

        DaoHistorial dao = new DaoHistorial(ActivityRegistros.this);
        adp = new ArrayAdapter<POJO_Historial>(ActivityRegistros.this, android.R.layout.simple_list_item_1,dao.buscarTodos(tipo));
        lista.setAdapter(adp);


    }




}
