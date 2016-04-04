/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Pracownik;
import model.Przedmiot;
import model.Zastosowanie;
import model.Czynnosc;
import model.Users;
import model.UsersGroups;
import model.Wiadomosc;
import model.Zadanie;

/**
 *
 * @author serq9_000
 */
@Stateful
public class DataBean {

    private static Logger logger = Logger.getLogger(".ejb.DataBean");
    
@PersistenceContext
private EntityManager em;

public List<Pracownik> getPracownicy(){

    List<Pracownik> pracownicy = null;
    try{
    pracownicy = (List<Pracownik>) em.createNamedQuery("Pracownik.findAll").getResultList(); 
    logger.info("Odczytano pracownikow");
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return pracownicy;
}

public Pracownik getPracownik(int id, String imie, String nazwisko){

    Pracownik Znaleziony = null;
    try{
    Znaleziony = (Pracownik) em.createNamedQuery("Pracownik.findById").getParameter("ID"); 
    logger.info("Znaleziono pracownika: "+imie +" " +nazwisko);
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return Znaleziony;
}

public void addPracownik(String imie, String nazwisko, double stawka)
{
        try
        {
            Pracownik Dodawany = new Pracownik(Integer.valueOf(1), imie, nazwisko, stawka);
            logger.info("Dodaje uzytkownika: "+ imie + " " + nazwisko);
            em.persist(Dodawany);
            logger.info("Dodano uzytkownika: "+ imie + " " + nazwisko);
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

public void deletePracownik(Pracownik pracownik)
{
        try
        {
            logger.info("Usuwam uzytkownika: "+pracownik.getImie() +" " +pracownik.getNazwisko());
            Pracownik Usuwany = em.find(Pracownik.class, pracownik.getId());
            em.remove(Usuwany);
            logger.info("Usunieto uzytkownika: "+pracownik.getImie() +" " +pracownik.getNazwisko());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

public void addPrzedmiot(String nazwa)
{
        try
        {
            Przedmiot Nowy = new Przedmiot(Integer.valueOf(1), nazwa);
            logger.info("Dodaje przedmiot: "+ nazwa);
            em.persist(Nowy);
            logger.info("Dodano przedmiot: "+ nazwa);
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

public List<Przedmiot> getPrzedmioty(){

    List<Przedmiot> przedmioty = null;
    try{
    przedmioty = (List<Przedmiot>) em.createNamedQuery("Przedmiot.findAll").getResultList(); 
    logger.info("Odczytano przedmioty" + przedmioty.get(0).getNazwa());
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return przedmioty;
}

public List<Przedmiot> getPrzedmiotyBezWlasc(){

    List<Przedmiot> przedmioty = null;
    try{
    przedmioty = (List<Przedmiot>) em.createNamedQuery("Przedmiot.findAllBezWlasc").getResultList(); 
    logger.info("Odczytano przedmioty bez wlasciciela");
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return przedmioty;
}

 public List<Przedmiot> getPrzedmiotyPracownika(int id) {
        
     List<Przedmiot> przedmioty = null;
        Pracownik pracownik = null;
        try {
            
            pracownik = (Pracownik) em.createNamedQuery("Pracownik.findById").setParameter("id", id).getSingleResult();
            przedmioty = (List<Przedmiot>)pracownik.getPrzedmiotList();
            
        } catch(Exception e) {
            throw new EJBException(e.getMessage());
        }
        return przedmioty;
    }

public void deletePrzedmiot(Przedmiot przedmiot)
{
        try
        {
            logger.info("Usuwam przedmiot: "+przedmiot.getNazwa());
            Przedmiot Usuwany = em.find(Przedmiot.class, przedmiot.getId());
            em.remove(Usuwany);
            logger.info("Usunieto przedmiot: "+przedmiot.getNazwa());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

public Pracownik getPracownikById(int id) {
        Pracownik pracownik = null;
        try {
            pracownik = (Pracownik) em.createNamedQuery("Pracownik.findById")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(Exception e) {
            throw new EJBException(e.getMessage());
        }
        return pracownik;
    }

public void addPrzedm2Prac(Pracownik pracownik, Przedmiot przedmiot)
{
        try
        {
            logger.info("id prac1: "+pracownik.getId());
            logger.info("id przedm1: "+przedmiot.getId());
            pracownik = em.getReference(Pracownik.class, pracownik.getId());
            przedmiot = em.getReference(Przedmiot.class, przedmiot.getId());
            logger.info("Dodaje pracownikowi "+pracownik.getImie() +" " +pracownik.getNazwisko() +" przedmiot :" +przedmiot.getNazwa());
            //Pracownik Znaleziony = em.find(Pracownik.class, pracownik.getId());
            //Przedmiot Znaleziony2 = em.find(Przedmiot.class, przedmiot.getId());
            logger.info("id prac: "+pracownik.getId());
            logger.info("id przedm: "+przedmiot.getId());
            przedmiot.setIdPracownik(pracownik);
            pracownik.getPrzedmiotList().add(przedmiot);
            logger.info("Dodano pracownikowi "+pracownik.getImie() +" " +pracownik.getNazwisko() +" przedmiot :" +przedmiot.getNazwa());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

    public void deletePrzedmPrac(Przedmiot przedmiot, Pracownik pracownik)
    {
        try
        {
            Pracownik Usuwany = em.find(Pracownik.class, pracownik.getId());
            Przedmiot Usuwany2 = em.find(Przedmiot.class, przedmiot.getId());
            logger.info("Usuwam pracownikowi "+pracownik.getImie() +" " +pracownik.getNazwisko() +" przedmiot :" +przedmiot.getNazwa());
            em.remove(Usuwany2);
            logger.info("Usunalem pracownikowi "+pracownik.getImie() +" " +pracownik.getNazwisko() +" przedmiot :" +przedmiot.getNazwa());
        }    
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }       
    }
    
    public void addNewZast(String nazwaNewZ)
    {
        try
        {
            Zastosowanie dod = new Zastosowanie(Integer.valueOf(1), nazwaNewZ);
            logger.info("Dodaje zastosowanie: "+ nazwaNewZ);
            em.persist(dod);
            logger.info("Dodano zastosowanie: "+ nazwaNewZ);
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
    }
    
 public List<Zastosowanie> getZast(){

    List<Zastosowanie> zast = null;
    try{
    zast = (List<Zastosowanie>) em.createNamedQuery("Zastosowanie.findAll").getResultList(); 
    logger.info("Odczytano zastosowania");
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return zast;
}
    
public void deleteZastosowanie(Zastosowanie zastosowanie)
{
        try
        {
            logger.info("Usuwam zastosowanie: "+zastosowanie.getNazwa());
            Zastosowanie Usuwane = em.find(Zastosowanie.class, zastosowanie.getId());
            em.remove(Usuwane);
            logger.info("Usunieto zastosowanie: "+zastosowanie.getNazwa());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

public List<Przedmiot> getPrzedmiotyZWlasc(){

    List<Przedmiot> przedmioty = null;
    try{
    przedmioty = (List<Przedmiot>) em.createNamedQuery("Przedmiot.findAllZWlasc").getResultList(); 
    logger.info("Odczytano przedmioty z wlascicielem");
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return przedmioty;
}

public Przedmiot getPrzedmiotById(int id) {
        Przedmiot przedmiot = null;
        try {
            przedmiot = (Przedmiot) em.createNamedQuery("Przedmiot.findById")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(Exception e) {
            throw new EJBException(e.getMessage());
        }
        return przedmiot;
    }

public void addZastPrzedm(Zastosowanie zast, Przedmiot przedm)
{
    
    try
        {
            logger.info("id przedm1: "+przedm.getId());
            logger.info("id zast11: "+zast.getId());
            przedm = em.getReference(Przedmiot.class, przedm.getId());
            zast = em.getReference(Zastosowanie.class, zast.getId());
            logger.info("Dodaje przedmiotowi "+przedm.getNazwa() +" zastosowanie :" +zast.getNazwa());
            //Pracownik Znaleziony = em.find(Pracownik.class, pracownik.getId());
            //Przedmiot Znaleziony2 = em.find(Przedmiot.class, przedmiot.getId());
            logger.info("id przedm: "+przedm.getId());
            logger.info("id zast1: "+zast.getId());
            if(!zast.getPrzedmiotList().contains(przedm) && !przedm.getZastosowanieList().contains(zast)){
            
                przedm.getZastosowanieList().add(zast);
                zast.getPrzedmiotList().add(przedm);
            }
            //przedmiot.setIdPracownik(pracownik);
            //pracownik.getPrzedmiotCollection().add(przedmiot);
            logger.info("Dodaje przedmiotowi "+przedm.getNazwa() +" zastosowanie :" +zast.getNazwa());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
} 

public List<Zastosowanie> getZastWolne(int przedmiotID){

    List<Zastosowanie> wolne = null;
    
    try{
      Query q = em.createNativeQuery("SELECT z.* FROM Zastosowanie z where z.id not in "
                + "(SELECT pz.id_zastosowanie from przedmiot_zastosowanie pz where pz.id_przedmiot=?1)", Zastosowanie.class);
      q.setParameter(1,przedmiotID);
      wolne = (List<Zastosowanie>)q.getResultList();
      logger.info("Odczytano wolne zastosowania");
      
    }
    catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
    return wolne;
}

public void deleteZastPrzedmPrac(Zastosowanie zast, Przedmiot przedm){

    try
        {
            logger.info("Usuwam zastosowanie: "+zast.getNazwa()+"z przedmiotu: "+przedm.getNazwa());
            Zastosowanie Usuwane = em.find(Zastosowanie.class, zast.getId());
            Przedmiot doUsun = em.find(Przedmiot.class, przedm.getId());
            Usuwane.getPrzedmiotList().remove(doUsun);
            doUsun.getZastosowanieList().remove(Usuwane);
            logger.info("Usunieto zastosowanie: "+zast.getNazwa()+"z przedmiotu: "+przedm.getNazwa());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
    
}

//public List<Zastosowanie> getZastPrzedm(int przedmiotID){
//    List<Zastosowanie> przedm = null;
//    try{
//      Query q = em.createNativeQuery("SELECT z.* FROM Zastosowanie z where z.id in "
//                + "(SELECT pz.id_zastosowanie from przedmiot_zastosowanie pz where pz.id_przedmiot=?1)", Zastosowanie.class);
//      q.setParameter(1,przedmiotID);
//      przedm = (List<Zastosowanie>)q.getResultList();
//      
//    }
//    catch(Exception e)
//        {
//            throw new EJBException(e.getMessage());
//        }
//    return przedm;
//}

public List<Zastosowanie> getZastPrzedm(int id) {
        List <Zastosowanie> zastosowania = null;
        Przedmiot przedmiot = null;
        try {
            przedmiot = em.find(Przedmiot.class, id);
            zastosowania = przedmiot.getZastosowanieList();
            logger.info("Odczytano zastosowania przedmiotu");
        } catch(Exception e) {
            throw new EJBException(e.getMessage());   
        }
        
        return zastosowania;
    }

public Zastosowanie getZastById(int id) {
        Zastosowanie zastosowanie = null;
        try {
            zastosowanie = (Zastosowanie) em.createNamedQuery("Zastosowanie.findById")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(Exception e) {
            throw new EJBException(e.getMessage());
        }
        return zastosowanie;
    }

public Zastosowanie getZastByNazwa(String nazwa) {
        List<Zastosowanie> zastosowanie = null;
        Zastosowanie z = null;
        try {
            zastosowanie = (List<Zastosowanie>) em.createNamedQuery("Zastosowanie.findByNazwa")
                    .setParameter("nazwa", nazwa)
                    .getResultList();
            if(zastosowanie.isEmpty())
            {
                return z;
            }
            else{
                return zastosowanie.get(0);
            }
        } catch(Exception e) {
            throw new EJBException(e.getMessage());
        }
        
    }

//public void addZastPrzedm(Zastosowanie zast, Przedmiot przedm)
//{


public Zadanie getZadanieById(int id) {
        Zadanie zadanie = null;
        try {
            zadanie = (Zadanie) em.createNamedQuery("Zadanie.findById")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(Exception e) {
            throw new EJBException(e.getMessage());
        }
        return zadanie;
    }


public void addCzynnosc(String nazwa, double stawka, int czasTrwaniaNewCzynn)
{
    Czynnosc czynnosc = new Czynnosc(Integer.valueOf(1), nazwa, stawka, czasTrwaniaNewCzynn);
            logger.info("Dodaje czynnosc: "  + nazwa + " " + stawka + " " + czasTrwaniaNewCzynn);
            em.persist(czynnosc);
            logger.info("DODANO CZYNNOSC: " + nazwa + " " + stawka + " " + czasTrwaniaNewCzynn);
}


public void addNewZad(Date data, String nazwa, String status, String nazwaTworcy){//, List<Czynnosc> listaCzynnosci){
    try
        {
            Zadanie dod = new Zadanie(Integer.valueOf(1), nazwa, data, status, nazwaTworcy);
           // Zadanie dod = new Zadanie(Integer.valueOf(1), nazwa, listaCzynnosci,data);
            logger.info("Dodaje zadanie: "+ nazwa);
            em.flush();
            em.persist(dod);
            
            logger.info("Dodano zadanie: "+ nazwa);
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

public void deleteZadanie(Zadanie zadanie)
{
        try
        {
            logger.info("Usuwam zadanie: "+zadanie.getNazwa());
            Zadanie Usuwane = em.find(Zadanie.class, zadanie.getId());
            //Usuwane.setCzynnoscList(null);
            em.remove(Usuwane);
            logger.info("Usunieto zadanie: "+zadanie.getNazwa());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

public List<Czynnosc> getCzynnosci(){

    List<Czynnosc> czynnosci = null;
    try{
    czynnosci = (List<Czynnosc>) em.createNamedQuery("Czynnosc.findAll").getResultList(); 
    logger.info("Odczytano czynnosci");
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return czynnosci;
}

public Czynnosc getCzynnById(int id) {
        Czynnosc cz = null;
        try {
            cz = (Czynnosc) em.createNamedQuery("Czynnosc.findById")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(Exception e) {
            throw new EJBException(e.getMessage());
        }
        return cz;
    }



public List<Pracownik> getPracownicyZZast(){

    List<Pracownik> ludzie = null;
    try{
    ludzie = (List<Pracownik>) em.createNamedQuery("").getResultList(); 
    logger.info("Odczytano czynnosci");
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return ludzie;
}


public List<Czynnosc> getCzynnosciZadania(Zadanie idZad){

    List<Czynnosc> czynnosci = null;
   
    try{
    czynnosci = (List<Czynnosc>) em.createNamedQuery("Czynnosc.findCzZadania")
            .setParameter("zadanie", idZad)
                   .getResultList(); 
    logger.info("Odczytano czynnosci danego zadania");
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return czynnosci;
}


public List<Czynnosc> getCzynnosciBezZad(){

    List<Czynnosc> czynnosci = null;
    try{
    czynnosci = (List<Czynnosc>) em.createNamedQuery("Czynnosc.findAllBezZad").getResultList(); 
    logger.info("Odczytano czynnosci bez zadania");
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return czynnosci;
}

public void deleteCzynnosc(Czynnosc czynnosc)
{
        try
        {
            logger.info("Usuwam czynnosc: "+czynnosc.getNazwa());
            Czynnosc Usuwana = em.find(Czynnosc.class, czynnosc.getId());
            em.remove(Usuwana);
            logger.info("Usunieto czynnosc: "+czynnosc.getNazwa());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

//public float obliczCalkowotyKosztZadania(Czynnosc czynosc){
//        logger.info("Nazwa czynnosci: " + czynosc.getNazwa());
//        float calkowityKosztZadan = 0.0f;
//        //if(czynosc.getCzynnoscCollection().isEmpty())
//            //for(Czynnosc cz : czynosc.getCzynnoscCollection()){
//                
//                calkowityKosztZadan += czynosc.getStawka() * (czynosc.getCzasTrwania()/60);
//                logger.info("Czynnosc: " + czynosc.getNazwa() + " " +  czynosc.getStawka() + " czas: " + czynosc.getCzasTrwania() + " " + calkowityKosztZadan); 
//                for(Pracownik p : czynosc.getPracownikList()){
//                    calkowityKosztZadan += ((float)(czynosc.getCzasTrwania()/czynosc.getPracownikList().size())/60) * p.getStawkaPracownika();
//                    //logger.info("Pracownik: " + p.getImie() + " " + calkowityKosztZadan); 
//                } 
//                //logger.info("Koszt dla czynnosci" +j.getNazwa()+ " " + calkowityKosztZadan); 
//            //}
//            //logger.info("Całkowity koszt zadania czynnosci + pracownika: " + calkowityKosztZadan); 
//        
//        return calkowityKosztZadan;
//    }
    

   
//    public void addZadanieToPracownik(Zadanie z, int id_pracownika){
//        try{     
//            Pracownik pracownik = (Pracownik) em.createNamedQuery("Pracownik.findByIdPracownik").setParameter("idPracownik", id_pracownika).getSingleResult();
//            logger.info("Do pracownika: " + pracownik.getImie()+ " " + pracownik.getNazwisko() + " dodaje zadanie: " + z.getNazwa());
//            if(!pracownik.getZadanieList().contains(z)){
//                pracownik.getZadanieList().add(z);
//                z.getPracownikList().add(pracownik);
//            }
//            em.merge(pracownik);
//            em.merge(z);
//        }catch(Exception e){
//            throw new EJBException(e.getMessage());             
//        }
//    }
//
//    public void removeZadanieToPracownik(Zadanie z, int id_pracownika){
//        try{ 
//            Pracownik pracownik = (Pracownik) em.createNamedQuery("Pracownik.findByIdPracownik").setParameter("idPracownik", id_pracownika).getSingleResult();
//            logger.info("Pracownikowi: " + pracownik.getImie() + " " + pracownik.getNazwisko() + " usuwam zadanie: " + z.getNazwa());
//            pracownik.getZadanieList().remove(z);
//            z.getPracownikList().remove(pracownik);
//            em.merge(pracownik);
//            em.merge(z);          
//        }catch(Exception e){
//            throw new EJBException(e.getMessage());          
//        }
//    }
    
    public List<Zadanie> getZadania(){

    List<Zadanie> zadania = null;
    try{
    zadania = (List<Zadanie>) em.createNamedQuery("Zadanie.findAll").getResultList(); 
    logger.info("Odczytano zadania");
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
    return zadania;
}
    
public void addCzynnoscDoZadania(Zadanie z, Czynnosc cz)
{
        try
        {
            logger.info("id zad: "+z.getId());
            logger.info("id czynn1: "+cz.getId());
            z = em.getReference(Zadanie.class, z.getId());
            cz = em.getReference(Czynnosc.class, cz.getId());
            logger.info("Dodaje zadaniu "+z.getNazwa() +" czynnosc :" +cz.getNazwa());
            //Pracownik Znaleziony = em.find(Pracownik.class, pracownik.getId());
            //Przedmiot Znaleziony2 = em.find(Przedmiot.class, przedmiot.getId());
            logger.info("id zad: "+z.getId());
            logger.info("id czynn1: "+cz.getId());
            z.getCzynnoscList().add(cz);
            cz.setIdZadanie(z);
            logger.info("Dodano zadaniu "+z.getNazwa() +" czynnosc :" +cz.getNazwa());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

public void addPracownikDoCzynnosci(Czynnosc cz, Pracownik pr)
{
        try
        {
            logger.info("id prac: "+pr.getId());
            logger.info("id czynn1: "+cz.getId());
            pr = em.getReference(Pracownik.class, pr.getId());
            cz = em.getReference(Czynnosc.class, cz.getId());
            logger.info("Dodaje czynnosci "+cz.getNazwa() +" pracownika :" +pr.getImie() +" " +pr.getNazwisko());
            //Pracownik Znaleziony = em.find(Pracownik.class, pracownik.getId());
            //Przedmiot Znaleziony2 = em.find(Przedmiot.class, przedmiot.getId());
          
            cz.getPracownikList().add(pr);
            pr.getCzynnoscList().add(cz);
            logger.info("Dodano czynnosci "+cz.getNazwa() +" pracownika :" +pr.getImie() +" " +pr.getNazwisko());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
}

public String getGrupaName(String username)
{
    try{
       UsersGroups ug = em.find(UsersGroups.class, username);
       return ug.getGroupid();
    }
    catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }

    

}

    public List<Users> getUserList() {
         List<Users> lista = null;
    try{
        lista = em.createNamedQuery("Users.findAll").getResultList(); 
        logger.info("Odczytano userow");
    }
    catch(Exception e){
        throw new EJBException(e.getMessage());
    }
        return lista;
    }

    public void addUser(String username, String password, String groupname)
    {
         try
        {
            Users dod = new Users(username, password);
            UsersGroups ug = new UsersGroups(username, groupname);
           // Zadanie dod = new Zadanie(Integer.valueOf(1), nazwa, listaCzynnosci,data);
            logger.info("Dodaje usera: "+username +"do grupy: " +groupname);
            em.persist(dod);
            em.persist(ug);
            logger.info("Dodano usera: "+username +"do grupy: " +groupname);
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void wyslijWiad(String tytul, String tresc, Date data_wyslania, Users nadawca, Users odbiorca)
    {
         try
        {
           
            Wiadomosc dod = new Wiadomosc(Integer.valueOf(1), tytul, tresc, data_wyslania, nadawca, odbiorca);
           // Zadanie dod = new Zadanie(Integer.valueOf(1), nazwa, listaCzynnosci,data);
            logger.info("Wysylam wiadomosc od uzytkownika: "+nadawca.getUserid() +"do uzytkownika: " +odbiorca.getUserid());
            em.persist(dod);
            logger.info("Wyslano wiadomosc od uzytkownika: "+nadawca.getUserid() +"do uzytkownika: " +odbiorca.getUserid());
        }
        catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
    }

public List<Users> getUsersFromGroup(String klient) 
{ 
    try { 
        List<UsersGroups> g = em.createNamedQuery("UsersGroups.findByGroupid", UsersGroups.class)
                .setParameter("groupid", klient)
                .getResultList(); 
        List<Users> u = new ArrayList<>(); 
        for(UsersGroups gr : g) 
        { 
            Users us = em.find(Users.class, gr.getUserid()); 
            u.add(us); 
        } 
        return u; 
    }catch (Exception e) 
    { 
        throw new EJBException(e.getMessage()); 
    } 
}

public List<Wiadomosc> getOdebraneWiadomosci(Users nadawca)
{
    try { 
        List<Wiadomosc> g = em.createNamedQuery("Wiadomosc.findByOdbiorca", Wiadomosc.class)
                .setParameter("odbiorca", nadawca)
                .getResultList(); 
            return g; 
    }catch (Exception e) 
    { 
        throw new EJBException(e.getMessage()); 
    } 
}

public Users getUserByName(String name)
{
    try { 
        Users u = em.createNamedQuery("Users.findByUserid", Users.class)
                .setParameter("userid", name)
                .getSingleResult(); 
            return u; 
    }catch (Exception e) 
    { 
        throw new EJBException(e.getMessage()); 
    } 
}



}
