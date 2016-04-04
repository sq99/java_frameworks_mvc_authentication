/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ejb.DataBean;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Users;
import model.Zadanie;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name = "ControlUser")
@ViewScoped
public class ControlUserBean {

    private static Logger logger = Logger.getLogger(".control.ControlUserBean");
    @EJB
    private DataBean db;
    
    /**
     * Creates a new instance of ControlUserBean
     */
    public ControlUserBean() {
    }
    
    private List<Users> userList;
    private String username;
    private String password;
    private String usernameNew;
    private String passwordNew;
    private String wybranaGrupa;
    private List<String> listaGrup;
    private List<Zadanie> zadaniaKlienta = new ArrayList<>();
    
    
    public String getGrupaName(String usrnm)
    {
          return db.getGrupaName(usrnm);
    }
    
    
    
    public String haszujHaslo(String haslo) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(haslo.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for(byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            
            return sb.toString();
    }

    /**
     * @return the userList
     */
    public List<Users> getUserList() {
        userList = db.getUserList();
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }

    public String getUsernameNew() {
        return usernameNew;
    }

    public void setUsernameNew(String usernameNew) {
        this.usernameNew = usernameNew;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    public String getWybranaGrupa() {
        return wybranaGrupa;
    }

    public void setWybranaGrupa(String wybranaGrupa) {
        this.wybranaGrupa = wybranaGrupa;
    }

    public List<String> getListaGrup() {
        if(listaGrup == null)
        {
            listaGrup = new ArrayList<>();
            listaGrup.add("user");
            listaGrup.add("klient");
            listaGrup.add("admin");
        }
        return listaGrup;
    }

    public void setListaGrup(List<String> listaGrup) {
        this.listaGrup = listaGrup;
    }
    
    public void addUser()
    {
        try {
            logger.info("hasz: "+haszujHaslo(this.passwordNew));
            db.addUser(this.usernameNew, haszujHaslo(this.passwordNew), this.wybranaGrupa);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ControlUserBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControlUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUsername() {
        this.username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public List<Zadanie> getZadaniaKlienta() 
    { 
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName(); 
        List<Zadanie> wszystkiezadania = db.getZadania(); 
        logger.info("username: "+username); 
        for(Zadanie z : wszystkiezadania) 
        { 
           logger.info("zadanie tworca: "+ z.getTworca()); 
           if( z.getTworca().equals(username)) this.zadaniaKlienta.add(z); 
        } 
        return zadaniaKlienta; 
    }

    public void setZadaniaKlienta(List<Zadanie> zadaniaKlienta) {
        this.zadaniaKlienta = zadaniaKlienta;
    }
    
}
