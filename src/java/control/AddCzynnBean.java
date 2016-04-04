/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ejb.DataBean;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Czynnosc;
import model.Pracownik;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name = "AddCzynnosc")
@RequestScoped
public class AddCzynnBean {

    private static Logger logger = Logger.getLogger(".control.AddCzynnBean");
    @EJB
    private DataBean db;
    
    private String nazwaNewCzynn;
    private int czasTrwaniaNewCzynn;
    private double stawkaNewCzynn;
    private int id_zadania;
    private int id_czynnosci;
    private List<Pracownik> MozliwiPracownicy;

    
    /**
     * Creates a new instance of AddCzynnBean
     */
    public AddCzynnBean() {
    }

    /**
     * @return the nazwaNewCzynn
     */
    public String getNazwaNewCzynn() {
        return nazwaNewCzynn;
    }

    /**
     * @param nazwaNewCzynn the nazwaNewCzynn to set
     */
    public void setNazwaNewCzynn(String nazwaNewCzynn) {
        this.nazwaNewCzynn = nazwaNewCzynn;
    }

    /**
     * @return the czasTrwaniaNewCzynn
     */
    public int getCzasTrwaniaNewCzynn() {
        return czasTrwaniaNewCzynn;
    }

    /**
     * @param czasTrwaniaNewCzynn the czasTrwaniaNewCzynn to set
     */
    public void setCzasTrwaniaNewCzynn(int czasTrwaniaNewCzynn) {
        this.czasTrwaniaNewCzynn = czasTrwaniaNewCzynn;
    }

    /**
     * @return the stawkaNewCzynn
     */
    public double getStawkaNewCzynn() {
        return stawkaNewCzynn;
    }

    /**
     * @param stawkaNewCzynn the stawkaNewCzynn to set
     */
    public void setStawkaNewCzynn(double stawkaNewCzynn) {
        this.stawkaNewCzynn = stawkaNewCzynn;
    }

    /**
     * @return the id_zadania
     */
    public int getId_zadania() {
        return id_zadania;
    }

    /**
     * @param id_zadania the id_zadania to set
     */
    public void setId_zadania(int id_zadania) {
        this.id_zadania = id_zadania;
    }

    /**
     * @return the id_czynnosci
     */
    public int getId_czynnosci() {
        return id_czynnosci;
    }

    /**
     * @param id_czynnosci the id_czynnosci to set
     */
    public void setId_czynnosci(int id_czynnosci) {
        this.id_czynnosci = id_czynnosci;
    }
    
     public void addCzynnosc(){
        try{
            db.addCzynnosc(nazwaNewCzynn, stawkaNewCzynn, czasTrwaniaNewCzynn);
            logger.warning("Dodano Czynnosc: " + nazwaNewCzynn + " " + stawkaNewCzynn + " " + czasTrwaniaNewCzynn);
        }catch(Exception e){
            logger.warning("Nie udalo sie dodac czynnosci");
            e.printStackTrace();
        }
    }
    
}
