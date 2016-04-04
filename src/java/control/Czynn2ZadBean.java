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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Czynnosc;
import model.Pracownik;
import model.Zadanie;


/**
 *
 * @author serq9_000
 */
@ManagedBean(name = "Czynn2Zad")
@RequestScoped
public class Czynn2ZadBean {

    
    private static Logger logger = Logger.getLogger(".control.Czynn2Zad");
    @EJB
    private DataBean db;
    
    private int zadanie_id;
    private Zadanie wybraneZadanie;
    private List<Czynnosc> bezZad;
    
   
    /**
     * Creates a new instance of Czynn2ZastBean
     */
    public Czynn2ZadBean() {
    }

    /**
     * @return the zadanie_id
     */
    public int getZadanie_id() {
        return zadanie_id;
    }

    /**
     * @param zadanie_id the zadanie_id to set
     */
    public void setZadanie_id(int zadanie_id) {
        this.zadanie_id = zadanie_id;
    }

    /**
     * @return the wybraneZadanie
     */
    public Zadanie getWybraneZadanie() {
        return wybraneZadanie;
    }

    /**
     * @param wybraneZadanie the wybraneZadanie to set
     */
    public void setWybraneZadanie(Zadanie wybraneZadanie) {
        this.wybraneZadanie = wybraneZadanie;
    }

   
    
    public void addCzynn2Zad(Czynnosc cz) {
        try{
            this.wybraneZadanie = db.getZadanieById(zadanie_id);
            db.addCzynnoscDoZadania(wybraneZadanie, cz);
            logger.warning("Dodano Czynnosc: " + cz.getNazwa() + " " + cz.getCzasTrwania() + " do zadania:" + wybraneZadanie.getId());
        }catch(Exception e){
            logger.warning("Nie udalo sie dodac czynnosci");
            e.printStackTrace();
        }
    }

    /**
     * @return the bezZad
     */
    public List<Czynnosc> getBezZad() {
        this.bezZad = db.getCzynnosciBezZad();
        return this.bezZad;
    }

    /**
     * @param bezZad the bezZad to set
     */
    public void setBezZad(List<Czynnosc> bezZad) {
        this.bezZad = bezZad;
    }
    
}

