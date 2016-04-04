/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ejb.DataBean;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import javax.ejb.EJB;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Czynnosc;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name = "AddZadanie")
@RequestScoped
public class AddZadBean {

    private static Logger logger = Logger.getLogger(".control.AddZadBean");
    @EJB
    private DataBean db;
    
    private String nazwa;
    private Date dataNewZad;
    private String datapomocn;
    private List<Czynnosc> listaCzynnNewZad;
    private String nazwaTworcy;
    
    
    /**
     * Creates a new instance of AddZadBean
     */
    public AddZadBean() {
    }

    /**
     * @return the dataNewZad
     */
    public Date getDataNewZad() {
        return dataNewZad;
    }

    /**
     * @param dataNewZad the dataNewZad to set
     */
    public void setDataNewZad(Date dataNewZad) {
        this.dataNewZad = dataNewZad;
    }

    /**
     * @return the listaCzynnNewZad
     */
    public List<Czynnosc> getListaCzynnNewZad() {
        return listaCzynnNewZad;
    }

    /**
     * @param listaCzynnNewZad the listaCzynnNewZad to set
     */
    public void setListaCzynnNewZad(List<Czynnosc> listaCzynnNewZad) {
        this.listaCzynnNewZad = listaCzynnNewZad;
    }
    
     public void addNewZadanie()
    {
        
        try
        {
            this.nazwaTworcy = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            db.addNewZad(dataNewZad, getNazwa(), "W oczekiwaniu na wykonanie", nazwaTworcy);// listaCzynnNewZad);
        }
        catch(Exception e)
        {
            logger.warning("Nie udalo sie dodac nowego zadania");
            e.printStackTrace();
        }
    }

    /**
     * @return the nazwa
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * @param nazwa the nazwa to set
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * @return the datapomocn
     */
    public String getDatapomocn() {
        return datapomocn;
    }

    /**
     * @param datapomocn the datapomocn to set
     */
    public void setDatapomocn(String datapomocn) {
        this.datapomocn = datapomocn;
    }

    public String getNazwaTworcy() {
        return nazwaTworcy;
    }

    public void setNazwaTworcy(String nazwaTworcy) {
        this.nazwaTworcy = nazwaTworcy;
    }
    
}
