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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name = "AddPracownik")
@RequestScoped
public class AddPracBean implements Serializable{
    
    private static Logger logger = Logger.getLogger(".control.AddPracBean");
    @EJB
    private DataBean db;
    
    private String imieNewP;
    private String nazwiskoNewP;
    private double stawkaNewP;

    /**
     * Creates a new instance of AddBean
     */
    public AddPracBean() {
    }

    /**
     * @return the imieNewP
     */
    public String getImieNewP() {
        return imieNewP;
    }

    /**
     * @param imieNewP the imieNewP to set
     */
    public void setImieNewP(String imieNewP) {
        this.imieNewP = imieNewP;
    }

    /**
     * @return the nazwiskoNewP
     */
    public String getNazwiskoNewP() {
        return nazwiskoNewP;
    }

    /**
     * @param nazwiskoNewP the nazwiskoNewP to set
     */
    public void setNazwiskoNewP(String nazwiskoNewP) {
        this.nazwiskoNewP = nazwiskoNewP;
    }
    
    public void addNewPracownik()
    {
        try
        {
            db.addPracownik(imieNewP, nazwiskoNewP, stawkaNewP);
        }
        catch(Exception e)
        {
            logger.warning("Nie udalo sie dodac pracownika");
            e.printStackTrace();
        }
    }

    /**
     * @return the stawkaNewP
     */
    public double getStawkaNewP() {
        return stawkaNewP;
    }

    /**
     * @param stawkaNewP the stawkaNewP to set
     */
    public void setStawkaNewP(double stawkaNewP) {
        this.stawkaNewP = stawkaNewP;
    }
    
}
