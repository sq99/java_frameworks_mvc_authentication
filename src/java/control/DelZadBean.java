/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ejb.DataBean;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Zadanie;
/**
 *
 * @author serq9_000
 */
@ManagedBean(name="DeleteZadanie")
@RequestScoped
public class DelZadBean {
    
    private static Logger logger = Logger.getLogger(".control.DelZadBean");
    @EJB
    private DataBean db;

    /**
     * Creates a new instance of DelZadBean
     */
    public DelZadBean() {
    }
    
    public void delZadanie(Zadanie zad)
    {
        try
        {
            db.deleteZadanie(zad);
        }
        catch(Exception e)
        {
            logger.warning("Nie udalo sie usunac zadania");
            e.printStackTrace();
        }
    }
    
}
