package bs.ecust.domain.db;
// Generated 2017-1-13 20:35:58 by Hibernate Tools 3.2.2.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Instnc generated by hbm2java
 */
@Entity
@Table(name="instnc"
    ,catalog="mca2"
)
public class Instnc  implements java.io.Serializable {


     private Long idInstnc;
     private Prblm prblm;
     private Usr usr;
     private String snm;
     private String nm;
     private String dscr;
     private Date created;
     private Long pblsh;
     private Set<Anlz> anlzs = new HashSet<Anlz>(0);
     private Set<Crtrn> crtrns = new HashSet<Crtrn>(0);

    public Instnc() {
    }

    public Instnc(Prblm prblm, Usr usr, String snm, String nm, String dscr, Date created, Long pblsh, Set<Anlz> anlzs, Set<Crtrn> crtrns) {
       this.prblm = prblm;
       this.usr = usr;
       this.snm = snm;
       this.nm = nm;
       this.dscr = dscr;
       this.created = created;
       this.pblsh = pblsh;
       this.anlzs = anlzs;
       this.crtrns = crtrns;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id_instnc", unique=true, nullable=false)
    public Long getIdInstnc() {
        return this.idInstnc;
    }
    
    public void setIdInstnc(Long idInstnc) {
        this.idInstnc = idInstnc;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_prblm")
    public Prblm getPrblm() {
        return this.prblm;
    }
    
    public void setPrblm(Prblm prblm) {
        this.prblm = prblm;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_usr")
    public Usr getUsr() {
        return this.usr;
    }
    
    public void setUsr(Usr usr) {
        this.usr = usr;
    }
    
    @Column(name="snm", length=17)
    public String getSnm() {
        return this.snm;
    }
    
    public void setSnm(String snm) {
        this.snm = snm;
    }
    
    @Column(name="nm", length=128)
    public String getNm() {
        return this.nm;
    }
    
    public void setNm(String nm) {
        this.nm = nm;
    }
    
    @Column(name="dscr", length=2048)
    public String getDscr() {
        return this.dscr;
    }
    
    public void setDscr(String dscr) {
        this.dscr = dscr;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created", length=19)
    public Date getCreated() {
        return this.created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    
    @Column(name="pblsh")
    public Long getPblsh() {
        return this.pblsh;
    }
    
    public void setPblsh(Long pblsh) {
        this.pblsh = pblsh;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="instnc")
    public Set<Anlz> getAnlzs() {
        return this.anlzs;
    }
    
    public void setAnlzs(Set<Anlz> anlzs) {
        this.anlzs = anlzs;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="instnc")
    public Set<Crtrn> getCrtrns() {
        return this.crtrns;
    }
    
    public void setCrtrns(Set<Crtrn> crtrns) {
        this.crtrns = crtrns;
    }




}


