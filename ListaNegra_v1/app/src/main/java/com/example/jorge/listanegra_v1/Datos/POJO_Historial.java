package com.example.jorge.listanegra_v1.Datos;

import java.io.Serializable;

/**
 * Created by Jorge on 07/03/2018.
 */

public class POJO_Historial implements Serializable {

    private int id_registro;
    private String number;
    private String name;
    private int block_tipo;
    private String structure_SMS;
    private String time;

    public POJO_Historial(){};

    public POJO_Historial(int id_registro, String number, String name, int block_tipo, String structure_SMS, String time) {

        this.id_registro = id_registro;
        this.number = number;
        this.name = name;
        this.block_tipo = block_tipo;
        this.structure_SMS = structure_SMS;
        this.time = time;

    }


    public int getId_registro() {
        return id_registro;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getBlock_tipo() {
        return block_tipo;
    }

    public String getStructure_SMS() {
        return structure_SMS;
    }

    public String getTime() {
        return time;
    }


    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBlock_tipo(int block_tipo) {
        this.block_tipo = block_tipo;
    }

    public void setStructure_SMS(String structure_SMS) {
        this.structure_SMS = structure_SMS;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public String toString() {

        String aux = "";

        if(this.block_tipo==0){

            aux = "\nID_Registro: " + this.id_registro + "\nNumero: " + this.number + "\nNombre: " + this.name + "\nBlock_tipo: " + "LLAMADAS" + "\nTiempo: " + this.time + "\n";

        }else if(this.block_tipo==1){

            aux = "\nID_Registro: " + this.id_registro + "\nNumero: " + this.number + "\nNombre: " + this.name + "\nBlock_tipo: " + "SMS" + "\nMensaje: " + this.structure_SMS + "\nTiempo: " + this.time + "\n";

        }

        return aux;

    }

}
