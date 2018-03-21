package com.example.jorge.listanegra_v1.Datos;

import java.io.Serializable;

/**
 * Created by Jorge on 07/03/2018.
 */

public class POJO_ListaNegra implements Serializable {

    private int id_alerta;
    private String number;
    private String name;
    private int block_tel;
    private int block_sms;

    public POJO_ListaNegra(){};

    public POJO_ListaNegra(int id_alerta, String number, String name, int block_tel, int block_sms) {

        this.id_alerta = id_alerta;
        this.number = number;
        this.name = name;
        this.block_tel = block_tel;
        this.block_sms = block_sms;

    }


    public int getId_alerta() {
        return id_alerta;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getBlock_tel() {
        return block_tel;
    }

    public int getBlock_sms() {
        return block_sms;
    }


    public void setId_alerta(int id_alerta) {
        this.id_alerta = id_alerta;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBlock_tel(int block_tel) {
        this.block_tel = block_tel;
    }

    public void setBlock_sms(int block_sms) {
        this.block_sms = block_sms;
    }


    @Override
    public String toString() {

        String auxCall = "";
        if(this.block_tel==0){
            auxCall = "No";
        }else if(this.block_tel==1){
            auxCall = "Si";
        }

        String auxSMS = "";
        if(this.block_sms==0){
            auxSMS = "No";
        }else if(this.block_sms==1){
            auxSMS = "Si";
        }

        return "\nID: " + this.id_alerta + "\nNumero: " + this.number + "\nNombre: " + this.name + "\n\nBloqueo llamadas: " + auxCall + "\nBloqueo SMS: " + auxSMS + "\n";

    }

}
