/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ejb.DataBean;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import model.Czynnosc;
import model.Zadanie;

/**
 *
 * @author serq9_000
 */
@Singleton
public class Harmonogram {

    private static Logger logger = Logger.getLogger(".control.Przedm2PracBean");
    @EJB
    private DataBean db;
    private List<Zadanie> zadania;
    
    
    @Schedule(dayOfWeek = "*", month = "*", hour = "9-17", dayOfMonth = "*", year = "*", minute = "*/30", second = "*", persistent = false)
    
    
    
    public void myTimer() {
        this.zadania = db.getZadania();
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        for(Zadanie z: this.zadania)
        {
            cal.setTime(z.getDataRozpoczecia());
            for(Czynnosc c : z.getCzynnoscList())
            {
                cal.add(Calendar.SECOND, c.getCzasTrwania());
            }
            Date czas = cal.getTime();
            
            System.out.print("data rozp: "+z.getDataRozpoczecia().toString());
            System.out.print("data zak: "+czas.toString());
            
            if(z.getDataRozpoczecia().after(now))
            {
                z.setStatus(0);
                System.out.print("Zmieniam status na 0");
            }
            
            else if(z.getDataRozpoczecia().before(now) && czas.after(now))
            {
                z.setStatus(1);
                System.out.print("Zmieniam status na 1");
            }
            else if(z.getDataRozpoczecia().before(now) && czas.before(now))
            {
                z.setStatus(2);
                System.out.print("Zmieniam status na 2");
            }
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Zadanie> getZadania() {
        return zadania;
    }

    public void setZadania(List<Zadanie> zadania) {
        this.zadania = zadania;
    }
}
