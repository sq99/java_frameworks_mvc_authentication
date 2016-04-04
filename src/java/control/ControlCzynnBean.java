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
@ManagedBean(name = "ControlCzynnosc")
@RequestScoped
public class ControlCzynnBean {
    
    private static Logger logger = Logger.getLogger(".control.AddCzynnBean");
    @EJB
    private DataBean db;
    
    private List<Czynnosc> Czynnosci;
    private List<Pracownik> MozliwiPracownicy;
    private int czasTrwaniaCzynnosci;
    private List<Pracownik> wolniPracownicy;

    /**
     * Creates a new instance of ControlCzynnBean
     */
    public ControlCzynnBean() {
    }

    /**
     * @return the MozliwiPracownicy
     */
    public List<Pracownik> getMozliwiPracownicy() {
        return MozliwiPracownicy;
    }

    /**
     * @param MozliwiPracownicy the MozliwiPracownicy to set
     */
    public void setMozliwiPracownicy(List<Pracownik> MozliwiPracownicy) {
        this.MozliwiPracownicy = MozliwiPracownicy;
    }

    /**
     * @return the czasTrwaniaCzynnosci
     */
    public int getCzasTrwaniaCzynnosci() {
        return czasTrwaniaCzynnosci;
    }

    /**
     * @param czasTrwaniaCzynnosci the czasTrwaniaCzynnosci to set
     */
    public void setCzasTrwaniaCzynnosci(int czasTrwaniaCzynnosci) {
        this.czasTrwaniaCzynnosci = czasTrwaniaCzynnosci;
    }
    
//    public List<Pracownik> mozliwiPracownicy(int id_czynnosci, int id_zadania){
//        try{
//            this.MozliwiPracownicy = null;
//            this.MozliwiPracownicy = db.mozliwiPracownicy(id_czynnosci, id_zadania);     
//        }catch(Exception e){
//            logger.warning("Nie udalo sie usnuac Czynnosci!");
//            e.printStackTrace();
//        }
//        return MozliwiPracownicy;
//    }
//    
//    public List<Pracownik> wolniPraconicy(List<Pracownik> mozliwiPracownicy, int id_zadania){
//        try{
//            this.wolniPracownicy = null;
//            this.wolniPracownicy = db.wolniPraconicy(mozliwiPracownicy, id_zadania);     
//        }catch(Exception e){
//            logger.warning("Nie udalo sie usnuac Czynnosci!");
//            e.printStackTrace();
//        }
//        return wolniPracownicy;
//    }
//    
//    public float sredniKosztCzynnosciZadania(Czynnosc cz){
//        float srednia = (float)(cz.getStawkaCzynnosci() / (cz.getCzasTrwania()/60));
//        if(cz.getPracownikCollection().isEmpty())
//            return srednia;
//        for(Pracownik p : cz.getPracownikCollection()){
//            srednia += ((float)(cz.getCzasTrwania()/cz.getPracownikCollection().size())/60) * p.getStawkaPracownika();
//        }
//        return srednia;
//    }

//    public void obliczCzas(Czynnosc czynnosc){
//        if(czynnosc.getCzynnoscCollection().isEmpty()){
//            this.czasTrwaniaCzynnosci += czynnosc.getCzasTrwania();
//        }else{
//            for(Czynnosc cz : czynnosc.getCzynnoscCollection()){
//                obliczCzas(cz);
//            }
//        }
//        
//    }
//    public float obliczCalkowotyKosztZadania(Czynnosc czynnosc){
//        return db.obliczCalkowotyKosztZadania(czynnosc);
//    }
    
//    public String czyCzynnoscMaPracownika(Czynnosc cz){
//        if(cz.getPracownikCollection().isEmpty())
//            return "Brak Pracownika";
//        
//        return "Gotowe do wykonania";
//    }
    
//    public int pozostalyCzas(int id_czynnosci){
//        return  db.pozostalyCzas(id_czynnosci);
//    }
    
    public int naMinuty(int czas){
        return czas / 60;
    }
    
//    public void przypisz(Czynnosc czynnosc, int id_czynnosci){
//        if(czynnosc != null)
//            db.przypisz(czynnosc, id_czynnosci);
//    }

    /**
     * @return the Czynnosci
     */
    public List<Czynnosc> getCzynnosci() {
        try
        {
            this.Czynnosci = db.getCzynnosci();
        }
        
        catch(Exception e)
        {
            logger.warning("Nie udalo sie pobrac czynnosci");
            e.printStackTrace();
        }
        return Czynnosci;
    
    }
    
    public List<Czynnosc> getCzynnosciBezZad() {
        try
        {
            this.Czynnosci = db.getCzynnosciBezZad();
        }
        
        catch(Exception e)
        {
            logger.warning("Nie udalo sie pobrac czynnosci");
            e.printStackTrace();
        }
        return Czynnosci;
    
    }


    
    
}
