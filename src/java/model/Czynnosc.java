/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author serq9_000
 */
@Entity
@Table(name = "CZYNNOSC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Czynnosc.findAll", query = "SELECT c FROM Czynnosc c"),
    @NamedQuery(name = "Czynnosc.findById", query = "SELECT c FROM Czynnosc c WHERE c.id = :id"),
    @NamedQuery(name = "Czynnosc.findByCzasTrwania", query = "SELECT c FROM Czynnosc c WHERE c.czasTrwania = :czasTrwania"),
    @NamedQuery(name = "Czynnosc.findByNazwa", query = "SELECT c FROM Czynnosc c WHERE c.nazwa = :nazwa"),
    @NamedQuery(name = "Czynnosc.findAllBezZad", query = "SELECT c FROM Czynnosc c WHERE c.idZadanie IS NULL"),
    @NamedQuery(name = "Czynnosc.findCzZadania", query = "SELECT c FROM Czynnosc c WHERE c.idZadanie = :zadanie"),
    @NamedQuery(name = "Czynnosc.findByStawka", query = "SELECT c FROM Czynnosc c WHERE c.stawka = :stawka")})
public class Czynnosc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CZAS_TRWANIA")
    private Integer czasTrwania;
    @Size(max = 50)
    @Column(name = "NAZWA")
    private String nazwa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STAWKA")
    private double stawka;
    @JoinTable(name = "PRACOWNIK_CZYNNOSC", joinColumns = {
        @JoinColumn(name = "ID_CZYNNOSC", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PRACOWNIK", referencedColumnName = "ID")})
    @ManyToMany
    private List<Pracownik> pracownikList;
    @JoinColumn(name = "ID_ZADANIE", referencedColumnName = "ID")
    @ManyToOne
    private Zadanie idZadanie;

    public Czynnosc() {
    }

    public Czynnosc(Integer id) {
        this.id = id;
    }

    public Czynnosc(Integer id, double stawka) {
        this.id = id;
        this.stawka = stawka;
    }
    
    public Czynnosc(Integer id, String nazwa, double stawka, int czastrwania) {
        this.id = id;
        this.nazwa = nazwa;
        this.stawka = stawka;
        this.czasTrwania = czastrwania;
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(Integer czasTrwania) {
        this.czasTrwania = czasTrwania;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getStawka() {
        return stawka;
    }

    public void setStawka(double stawka) {
        this.stawka = stawka;
    }

    @XmlTransient
    public List<Pracownik> getPracownikList() {
        return pracownikList;
    }

    public void setPracownikList(List<Pracownik> pracownikList) {
        this.pracownikList = pracownikList;
    }

    public Zadanie getIdZadanie() {
        return idZadanie;
    }

    public void setIdZadanie(Zadanie idZadanie) {
        this.idZadanie = idZadanie;
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
        if (!(object instanceof Czynnosc)) {
            return false;
        }
        Czynnosc other = (Czynnosc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Czynnosc[ id=" + id + " ]";
    }
    
}
