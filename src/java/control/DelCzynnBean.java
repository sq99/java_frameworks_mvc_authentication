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
@ManagedBean(name = "DeleteCzynnosc")
@RequestScoped
public class DelCzynnBean {
    
    private static Logger logger = Logger.getLogger(".control.AddCzynnBean");
    @EJB
    private DataBean db;
    
    
    /**
     * Creates a new instance of DelCzynnBean
     */
    public DelCzynnBean() {
    }
    
    
    public void delCzynnosc(Czynnosc czynnosc)
    {
        try
        {
            db.deleteCzynnosc(czynnosc);
        }
        catch(Exception e)
        {
            logger.warning("Nie udalo sie usunac czynnosci");
            e.printStackTrace();
        }
    }
    
    
}
