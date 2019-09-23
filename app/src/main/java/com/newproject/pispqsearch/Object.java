package com.newproject.pispqsearch;

import java.util.ArrayList;

/**
 * Created by Lopes on 06/05/2018.
 */

public class Object extends ArrayList<Object> {
    private String produto;
    private String onu;
    private String classe;
    private String risco;
    private String epi;

    public Object(String Prod, String Onu, String Classe, String Risco, String Epi){
        this.produto = Prod;
        this.onu     = Onu;
        this.classe  = Classe;
        this.risco   = Risco;
        this.epi     = Epi;

    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getOnu() {
        return onu;
    }

    public void setOnu(String onu) {
        this.onu = onu;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getRisco() {
        return risco;
    }

    public void setRisco(String risco) {
        this.risco = risco;
    }

    public String getEpi() {
        return epi;
    }

    public void setEpi(String epi) {
        this.epi = epi;
    }

}
