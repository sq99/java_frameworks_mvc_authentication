/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ejb.DataBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Users;
import model.Wiadomosc;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name="ControlWiad")
@ViewScoped
public class ControlWiadBean {

    private static Logger logger = Logger.getLogger(".control.ControlWiadBean");
    @EJB
    private DataBean db;
    
    private Users odbiorca;
    private Users nadawca;
    private List<Users> mozliwiOdbiorcy;
    private String tytul;
    private String tresc;
    private Date data_wyslania;
    private List<Wiadomosc> odebrane;
    private String grupaOdbiorcy;
    private String nadawca_name;
    private String odbiorca_name;
    
    /**
     * Creates a new instance of ControlWiadBean
     */
    public ControlWiadBean() {
    }
    
    public void wyslijWiad()
    {
        this.data_wyslania = new Date();
        db.wyslijWiad(tytul, tresc, data_wyslania, nadawca, odbiorca);
    }

    public Users getOdbiorca() {
        return odbiorca;
    }

    public void setOdbiorca(Users odbiorca) {
        this.odbiorca = odbiorca;
    }

    public Users getNadawca() {
        return nadawca;
    }

    public void setNadawca(Users nadawca) {
        logger.info("setnad: "+nadawca.getUserid());
        this.nadawca = nadawca;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Date getData_wyslania() {
        return data_wyslania;
    }

    public void setData_wyslania(Date data_wyslania) {
        this.data_wyslania = data_wyslania;
    }

    public List<Users> getMozliwiOdbiorcy() {
        this.mozliwiOdbiorcy = db.getUsersFromGroup(this.grupaOdbiorcy);
        return mozliwiOdbiorcy;
    }

    public void setMozliwiOdbiorcy(List<Users> mozliwiOdbiorcy) {
        logger.info("setmozlodb: "+mozliwiOdbiorcy.get(0).getUserid());
        this.mozliwiOdbiorcy = mozliwiOdbiorcy;
    }

    public List<Wiadomosc> getOdebrane() {
        this.odebrane = db.getOdebraneWiadomosci(this.nadawca);
        return odebrane;
    }

    public void setOdebrane(List<Wiadomosc> odebrane) {
        this.odebrane = odebrane;
    }

    public String getGrupaOdbiorcy() {
        return grupaOdbiorcy;
    }

    public void setGrupaOdbiorcy(String grupaOdbiorcy) {
        logger.info("setgrodb: "+grupaOdbiorcy);
        this.grupaOdbiorcy = grupaOdbiorcy;
    }

    public String getNadawca_name() {
        return nadawca_name;
    }

    public void setNadawca_name(String nadawca_name) {
        this.nadawca_name = nadawca_name;
        this.nadawca = db.getUserByName(nadawca_name);
    }

    public String getOdbiorca_name() {
        return odbiorca_name;
    }

    public void setOdbiorca_name(String odbiorca_name) {
        this.odbiorca_name = odbiorca_name;
        this.odbiorca = db.getUserByName(odbiorca_name);
    }
    
}
