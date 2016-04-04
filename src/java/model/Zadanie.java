/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "ZADANIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zadanie.findAll", query = "SELECT z FROM Zadanie z"),
    @NamedQuery(name = "Zadanie.findById", query = "SELECT z FROM Zadanie z WHERE z.id = :id"),
    @NamedQuery(name = "Zadanie.findByDataRozpoczecia", query = "SELECT z FROM Zadanie z WHERE z.dataRozpoczecia = :dataRozpoczecia"),
    @NamedQuery(name = "Zadanie.findByNazwa", query = "SELECT z FROM Zadanie z WHERE z.nazwa = :nazwa"),
    @NamedQuery(name = "Zadanie.findByStatus", query = "SELECT z FROM Zadanie z WHERE z.status = :status")})
public class Zadanie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DATA_ROZPOCZECIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRozpoczecia;
    @Size(max = 50)
    @Column(name = "NAZWA")
    private String nazwa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;
    @OneToMany(mappedBy = "idZadanie")
    private List<Czynnosc> czynnoscList;
    @Column(name = "STATUS_SLOWNIE")
    private String status_slownie;
    @NotNull
    @Column(name = "TWORCA")
    private String tworca;

    public Zadanie() {
    }

    public Zadanie(Integer id) {
        this.id = id;
    }

    public Zadanie(Integer id, int status) {
        this.id = id;
        this.status = status;
    }
    
    public Zadanie(Integer id, String nazwa, Date data){
        this.id = id;
        this.nazwa = nazwa;
        this.dataRozpoczecia = data;
    }
    
    public Zadanie(Integer id, String nazwa, Date data, String status, String tworca){
        this.id = id;
        this.nazwa = nazwa;
        this.dataRozpoczecia = data;
        this.status_slownie = status;
        this.tworca = tworca;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        if (!(object instanceof Zadanie)) {
            return false;
        }
        Zadanie other = (Zadanie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Zadanie[ id=" + id + " ]";
    }

    public List<Czynnosc> getCzynnoscList() {
        return czynnoscList;
    }

    public void setCzynnoscList(List<Czynnosc> czynnoscList) {
        this.czynnoscList = czynnoscList;
    }

    public String getStatus_slownie() {
        if(this.getStatus() == 0)
        {
            this.setStatus_slownie("W oczekiwaniu na rozpoczecie");
        }
        else if(this.getStatus() == 1)
        {
            this.setStatus_slownie("W trakcie");
        }
        else if(this.getStatus() == 2)
        {
            this.setStatus_slownie("Zakonczone");
        }
        
        return status_slownie;
    }

    public void setStatus_slownie(String status_slownie) {
        this.status_slownie = status_slownie;
    }

    public String getTworca() {
        this.tworca = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return tworca;
    }

    public void setTworca(String tworca) {
        this.tworca = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        
    }
    
}
