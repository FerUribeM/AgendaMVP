package com.ferbajoo.agendamvp;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class ListaFotos {

    private String status;
    private ArrayList<ListaFotosResult> nombreFotos;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ListaFotosResult> getNombreFotos() {
        return nombreFotos;
    }

    public void setNombreFotos(ArrayList<ListaFotosResult> nombreFotos) {
        this.nombreFotos = nombreFotos;
    }

    private class ListaFotosResult {

        @Expose
        private int IDFOTO;
        @Expose
        private String NOMBREFOTO;
        @Expose
        private String FORMATO;
        @Expose
        private String TIPO;
        @Expose
        private int TIPODOC;


        public int getIDFOTO() {
            return IDFOTO;
        }

        public void setIDFOTO(int IDFOTO) {
            this.IDFOTO = IDFOTO;
        }

        public String getNOMBREFOTO() {
            return NOMBREFOTO;
        }

        public void setNOMBREFOTO(String NOMBREFOTO) {
            this.NOMBREFOTO = NOMBREFOTO;
        }

        public String getFORMATO() {
            return FORMATO;
        }

        public void setFORMATO(String FORMATO) {
            this.FORMATO = FORMATO;
        }

        public String getTIPO() {
            return TIPO;
        }

        public void setTIPO(String TIPO) {
            this.TIPO = TIPO;
        }

        public int getTIPODOC() {
            return TIPODOC;
        }

        public void setTIPODOC(int TIPODOC) {
            this.TIPODOC = TIPODOC;
        }
    }
}
