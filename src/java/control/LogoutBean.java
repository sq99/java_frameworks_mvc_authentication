/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author serq9_000
 */
@ManagedBean(name="logout")
@RequestScoped
public class LogoutBean {

    /**
     * Creates a new instance of LogoutBean
     */
    public LogoutBean() {
    }
    
    public String logout()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
        session.invalidate();
        return "/index";
    }
    
}
