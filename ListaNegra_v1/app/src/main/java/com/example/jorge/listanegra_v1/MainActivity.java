package com.example.jorge.listanegra_v1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jorge.listanegra_v1.Datos.DaoListaNegra;
import com.example.jorge.listanegra_v1.Datos.POJO_ListaNegra;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lista;

    private POJO_ListaNegra recordatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista =(ListView)findViewById(R.id.lista_black);

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                recordatorio = new POJO_ListaNegra();
                recordatorio.setId_alerta(adp.getItem(i).getId_alerta());
                recordatorio.setNumber(adp.getItem(i).getNumber());
                recordatorio.setName(adp.getItem(i).getName());
                recordatorio.setBlock_tel(adp.getItem(i).getBlock_tel());
                recordatorio.setBlock_sms(adp.getItem(i).getBlock_sms());

                elige_Lista();

                return false;

            }

        });


        Intent service = new Intent(getBaseContext(),  Servicio.class);
        startService(service);


        reiniciarDatos();

    }

    ArrayAdapter<POJO_ListaNegra> adp;
    public void reiniciarDatos(){

        DaoListaNegra dao = new DaoListaNegra(MainActivity.this);
        adp = new ArrayAdapter<POJO_ListaNegra>(MainActivity.this, android.R.layout.simple_list_item_1,dao.buscarTodos());
        lista.setAdapter(adp);

    }

    String opc[] = new String[]{"Editar", "Eliminar"};
    String checks[] = new String[]{"Bloquear llamadas TEL", "Bloquear mensajes SMS"};
    int setTelBlock = 0;
    int setSmsBlock = 0;
    public void elige_Lista(){

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Opciones de edición")
                .setIcon(R.mipmap.ic_launcher)
                .setItems(opc, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(opc[which].equals("Editar")){

                            boolean flag_TEL = false;
                            boolean flag_SMS = false;

                            if(recordatorio.getBlock_tel()==0){
                                flag_TEL = false;
                                setTelBlock = 0;
                            }else if(recordatorio.getBlock_tel()==1){
                                flag_TEL = true;
                                setTelBlock = 1;
                            }

                            if(recordatorio.getBlock_sms()==0){
                                flag_SMS = false;
                                setSmsBlock = 0;
                            }else if(recordatorio.getBlock_sms()==1){
                                flag_SMS = true;
                                setSmsBlock = 1;
                            }

                            AlertDialog dialogCh = new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Opciones de bloqueo")
                                    .setIcon(R.mipmap.ic_launcher)
                                    .setMultiChoiceItems(checks, new boolean[]{flag_TEL, flag_SMS}, new DialogInterface.OnMultiChoiceClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                            //Toast.makeText(MainActivity.this, checks[which] + (isChecked? " Verificado": " No Verificado"), Toast.LENGTH_SHORT).show();

                                            if(which==0){

                                                if(isChecked==true){
                                                    setTelBlock = 1;
                                                }else if(isChecked==false){
                                                    setTelBlock = 0;
                                                }

                                            }else if(which==1){

                                                if(isChecked==true){
                                                    setSmsBlock = 1;
                                                }else if(isChecked==false){
                                                    setSmsBlock = 0;
                                                }

                                            }

                                        }

                                    })
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            recordatorio.setBlock_tel(setTelBlock);
                                            recordatorio.setBlock_sms(setSmsBlock);

                                            try {

                                                DaoListaNegra dao = new DaoListaNegra(MainActivity.this);

                                                if(dao.update(recordatorio)>0) {

                                                    Toast.makeText(getBaseContext(), "Bloqueos editados", Toast.LENGTH_SHORT).show();
                                                    reiniciarDatos();

                                                }else{

                                                    Toast.makeText(getBaseContext(), "No pudo ser editado", Toast.LENGTH_SHORT).show();

                                                }

                                            }catch (Exception err){

                                                Toast.makeText(getBaseContext(),err.getMessage(), Toast.LENGTH_LONG).show();

                                            }

                                        }

                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            //Toast.makeText(MainActivity.this, "Presiono Cancel", Toast.LENGTH_SHORT).show();

                                        }

                                    })
                                    .create();

                            dialogCh.show();

                        }else if(opc[which].equals("Eliminar")) {

                            AlertDialog dialog2 = new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Confirmar")
                                    .setIcon(android.R.drawable.ic_delete)
                                    .setMessage("¿Eliminar numero de BlackList?")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            try {

                                                DaoListaNegra dao = new DaoListaNegra(MainActivity.this);

                                                if (dao.delete(recordatorio.getId_alerta() + "") > 0) {

                                                    Toast.makeText(getBaseContext(), "Numero removido", Toast.LENGTH_SHORT).show();
                                                    reiniciarDatos();

                                                } else {

                                                    Toast.makeText(getBaseContext(), "El numero no pudo ser removido", Toast.LENGTH_SHORT).show();

                                                }

                                            } catch (Exception err) {

                                                Toast.makeText(getBaseContext(), err.getMessage(), Toast.LENGTH_LONG).show();

                                            }

                                        }

                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            //Toast.makeText(MainActivity.this, "Presiono CANCEL", Toast.LENGTH_SHORT).show();

                                        }

                                    })
                                    .create();

                            dialog2.show();

                        }

                        dialog.dismiss();

                    }

                })
                .create();

        dialog.show();

    }



    public void btnNewNumber(View view) {

        Intent intent = new Intent(getApplication(),ActivityContactos.class);
        startActivityForResult(intent,1001);

    }

    public void btnRegCalls(View view) {

        Intent intent = new Intent(getApplication(),ActivityRegistros.class);
        intent.putExtra("tipo_integer", 0);
        startActivityForResult(intent,1002);

    }

    public void btnRegSMS(View view) {

        Intent intent = new Intent(getApplication(),ActivityRegistros.class);
        intent.putExtra("tipo_integer", 1);
        startActivityForResult(intent,1003);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // NUEVO NUMERO EN BLACK LIST;
        if (resultCode==RESULT_OK && requestCode==1001) {

            try {

                String number = (String) data.getSerializableExtra("stringNumber");
                String name = (String) data.getSerializableExtra("stringName");

                DaoListaNegra dao = new DaoListaNegra(MainActivity.this);

                if(dao.insert(new POJO_ListaNegra(0,number,name,1,1))>0) {

                    Toast.makeText(getBaseContext(), "Numero registrado en lista negra", Toast.LENGTH_SHORT).show();
                    reiniciarDatos();

                }else{

                    Toast.makeText(getBaseContext(), "El numero no fue registrado", Toast.LENGTH_SHORT).show();

                }

            }catch (Exception err){

                Toast.makeText(getBaseContext(),err.getMessage(),Toast.LENGTH_LONG).show();

            }

            reiniciarDatos();

        }

    }



}
