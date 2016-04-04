/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ejb.DataBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;
import javax.ejb.EJB;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import model.Czynnosc;
import model.Pracownik;
import model.Przedmiot;
import model.Zadanie;
import model.Zastosowanie;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name = "ControlZadania")
@ViewScoped
public class ControlZadBean {

    private static Logger logger = Logger.getLogger(".control.AddCzynnBean");
    @EJB
    private DataBean db;
    
    private int zadanie_id;
    private int czynnosc_id;
    private int czynnosc_id_dodawanie;
    private int id_prac;
    private Zastosowanie znalezione;
    private List<Przedmiot> listaPrzedmiotow;
    private int zast_id;
    private List<Czynnosc> BezZad;
    private List<Zadanie> Zadania;
    private Zadanie wybraneZadanie;
    private Czynnosc wybranaCzynnosc;
    private Czynnosc wybranaCzynnoscDodawanie;
    private List<Pracownik> MozliwiPracownicy = new ArrayList<Pracownik>();
    private int czasTrwaniaCzynnosci;
    private List<Pracownik> wolniPracownicy;
    private List<Czynnosc> czynnosciZadania;
    private String nazwaZast;
    
    
    
    /**
     * Creates a new instance of ControlZadBean
     */
    public ControlZadBean() {
    }
    
    public List<Zadanie> getZadania() {
        try
        {
            this.Zadania = db.getZadania();
        }
        
        catch(Exception e)
        {
            logger.warning("Nie udalo sie pobrac czynnosci");
            e.printStackTrace();
        }
        return Zadania;
    
    }

    /**
     * @return the czynnosciZadania
     */
    public List<Czynnosc> getCzynnosciZadania() {
        wybraneZadanie = db.getZadanieById(zadanie_id);
        return db.getCzynnosciZadania(wybraneZadanie);
    }

    /**
     * @param czynnosciZadania the czynnosciZadania to set
     */
    public void setCzynnosciZadania(List<Czynnosc> czynnosciZadania) {
        this.czynnosciZadania = czynnosciZadania;
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
    
    public void setWybraneZadanie(int id) {
        this.wybraneZadanie = db.getZadanieById(zadanie_id);
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
        this.wybraneZadanie = db.getZadanieById(zadanie_id);
       
        
        
    }

    /**
     * @return the czynnosc_id
     */
    public int getCzynnosc_id() {
        return czynnosc_id;
    }

    /**
     * @param czynnosc_id the czynnosc_id to set
     */
    public void setCzynnosc_id(int czynnosc_id) {
        this.czynnosc_id = czynnosc_id;
    }

    /**
     * @return the MozliwiPracownicy
     */
    public List<Pracownik> getMozliwiPracownicy() {
        this.nazwaZast = db.getCzynnById(czynnosc_id).getNazwa();
        logger.info("nazwa zastosowania z funkcji:" +nazwaZast);
        //tu coś pokombinować jak nie ma zastosowania z taką nazwą!
        this.znalezione = db.getZastByNazwa(nazwaZast);
        if(this.znalezione == null)
        {
            return this.MozliwiPracownicy;
        }
        this.listaPrzedmiotow = this.znalezione.getPrzedmiotList();
        logger.info("liczba przedmiotow:" +this.listaPrzedmiotow.size());
        if(!this.listaPrzedmiotow.isEmpty()){
            
            for(Przedmiot p : this.listaPrzedmiotow)
            {
                
                logger.info("nazwa przedmiotu z funkcji:" +p.getNazwa());
                logger.info("nazwa pracownika przedmiotu z funkcji:" +p.getIdPracownik().getImie() +" " +p.getIdPracownik().getNazwisko());
                this.MozliwiPracownicy.add(p.getIdPracownik());
            }
        }
        return this.MozliwiPracownicy;
    }

    /**
     * @param MozliwiPracownicy the MozliwiPracownicy to set
     */
    public void setMozliwiPracownicy(List<Pracownik> MozliwiPracownicy) {
        this.MozliwiPracownicy = MozliwiPracownicy;
    }
    
     public void addPrac2Czynn(Pracownik pr) {
        try{
            this.wybranaCzynnosc = db.getCzynnById(czynnosc_id);
            db.addPracownikDoCzynnosci(wybranaCzynnosc,pr);
            logger.warning("Dodano pracownika" +pr.getImie() +" " +pr.getNazwisko() +"do czynnosci: " + wybranaCzynnosc.getNazwa() + " " + wybranaCzynnosc.getCzasTrwania());
        }catch(Exception e){
            logger.warning("Nie udalo sie dodac pracownika do czynnosci");
            e.printStackTrace();
        }
    }

    /**
     * @return the czynnosc_id_dodawanie
     */
    public int getCzynnosc_id_dodawanie() {
        return czynnosc_id_dodawanie;
    }

    /**
     * @param czynnosc_id_dodawanie the czynnosc_id_dodawanie to set
     */
    public void setCzynnosc_id_dodawanie(int czynnosc_id_dodawanie) {
        this.czynnosc_id_dodawanie = czynnosc_id_dodawanie;
    }
    
    public List<Pracownik> getPracownikList(Zadanie z)
    {
        HashSet<Pracownik> prac = new HashSet<>();
        for(Czynnosc c: z.getCzynnoscList())
        {
            prac.addAll(c.getPracownikList());
        }
        return new ArrayList<Pracownik>(prac);
    }
    
    public int czasTrwaniaZadania(Zadanie z)
    {
        int czas = 0;
        for(Czynnosc c: z.getCzynnoscList())
        {
            czas+=c.getCzasTrwania();
            
        }
        return czas;
    }
    
    public Date dataZakonczZadania(Zadanie z)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(z.getDataRozpoczecia());
        cal.add(Calendar.SECOND, czasTrwaniaZadania(z));
        Date czas = cal.getTime();
        return czas;
    }
    
    public double kosztZadania(Zadanie z)
    {
        double koszt = 0;
        for(Czynnosc c : z.getCzynnoscList())
        {
            koszt += kosztCzynnosci(c);
        }
        return koszt;
    }
    
    public double kosztCzynnosci(Czynnosc c)
    {
        double koszt = 0;
        int liczbaPrac = c.getPracownikList().size();
        if(liczbaPrac == 0) return 0;
        koszt += c.getStawka()*(double)c.getCzasTrwania()/60.0;
        for(Pracownik p : c.getPracownikList())
        {
            koszt += p.getStawka()*(double)c.getCzasTrwania()/60.0;
        }
        return koszt;
    }
    
    public double kosztSprzetu(Czynnosc c)
    {
        double koszt = 0;
        koszt += c.getStawka()*(double)c.getCzasTrwania()/60.0;
        
        return koszt;
    }
    
    public double sredniKosztSprzetuZadania(Zadanie z)
    {
        double koszt = 0;
        for(Czynnosc c: z.getCzynnoscList())
        {
            koszt += kosztSprzetu(c);
        }
        koszt/=z.getCzynnoscList().size();
        return koszt;
    }
    
    public double sredniKosztPracownikaZadania(Zadanie z)
    {
        double koszt = 0;
        List<Pracownik> prac = this.getPracownikList(z);
        for(Pracownik p : prac)
        {
            koszt += p.getStawka();
        }
        koszt/=prac.size();
        return koszt;
    }
    
    public double sredniKosztCzynnosciZadania(Zadanie z)
    {
        double koszt = 0;
        for(Czynnosc c : z.getCzynnoscList())
        {
            koszt += this.kosztCzynnosci(c);
        }
        koszt/=z.getCzynnoscList().size();
        return koszt;
    }
    
//    public double czasPracyPracownikaZadania(Zadanie z, Pracownik p)
//    {
//        
//    }
//    

    

   
}
