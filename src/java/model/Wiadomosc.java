/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serq9_000
 */
@Entity
@Table(name = "WIADOMOSC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wiadomosc.findAll", query = "SELECT w FROM Wiadomosc w"),
    @NamedQuery(name = "Wiadomosc.findById", query = "SELECT w FROM Wiadomosc w WHERE w.id = :id"),
    @NamedQuery(name = "Wiadomosc.findByTytul", query = "SELECT w FROM Wiadomosc w WHERE w.tytul = :tytul"),
    @NamedQuery(name = "Wiadomosc.findByTresc", query = "SELECT w FROM Wiadomosc w WHERE w.tresc = :tresc"),
    @NamedQuery(name = "Wiadomosc.findByPrzeczytana", query = "SELECT w FROM Wiadomosc w WHERE w.przeczytana = :przeczytana"),
    @NamedQuery(name = "Wiadomosc.findByDataWyslania", query = "SELECT w FROM Wiadomosc w WHERE w.dataWyslania = :dataWyslania"),
    @NamedQuery(name = "Wiadomosc.findByNadawca", query = "SELECT w FROM Wiadomosc w WHERE w.nadawca = :nadawca"),
    @NamedQuery(name = "Wiadomosc.findByOdbiorca", query = "SELECT w FROM Wiadomosc w WHERE w.odbiorca = :odbiorca")})
public class Wiadomosc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TYTUL")
    private String tytul;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TRESC")
    private String tresc;
    @Column(name = "PRZECZYTANA")
    private Integer przeczytana;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATA_WYSLANIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataWyslania;
    @JoinColumn(name = "ODBIORCA", referencedColumnName = "USERID")
    @ManyToOne
    private Users odbiorca;
    @JoinColumn(name = "NADAWCA", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private Users nadawca;

    public Wiadomosc() {
    }

    public Wiadomosc(Integer id) {
        this.id = id;
    }

    public Wiadomosc(Integer id, String tytul, String tresc, Date dataWyslania) {
        this.id = id;
        this.tytul = tytul;
        this.tresc = tresc;
        this.dataWyslania = dataWyslania;
    }
    
    public Wiadomosc(Integer id, String tytul, String tresc, Date dataWyslania, Users nadawca, Users odbiorca)
    {
        this.id = id;
        this.tytul = tytul;
        this.tresc = tresc;
        this.dataWyslania = dataWyslania;
        this.nadawca = nadawca;
        this.odbiorca = odbiorca;
    }
    

    public Wiadomosc(Integer id, String tytul, String tresc, int przeczytana, Date dataWyslania) {
        this.id = id;
        this.tytul = tytul;
        this.tresc = tresc;
        this.przeczytana = przeczytana;
        this.dataWyslania = dataWyslania;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPrzeczytana() {
        return przeczytana;
    }

    public void setPrzeczytana(Integer przeczytana) {
        this.przeczytana = przeczytana;
    }

    public Date getDataWyslania() {
        return dataWyslania;
    }

    public void setDataWyslania(Date dataWyslania) {
        this.dataWyslania = dataWyslania;
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
        this.nadawca = nadawca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wiadomosc)) {
            return false;
        }
        Wiadomosc other = (Wiadomosc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Wiadomosc[ id=" + id + " ]";
    }
    
}
