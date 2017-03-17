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
 * Anlz generated by hbm2java
 */
@Entity
@Table(name="anlz"
    ,catalog="mca2"
)
public class Anlz  implements java.io.Serializable {


     private Long idAnlz;
     private Usr usr;
     private Instnc instnc;
     private Long seq;
     private String snm;
     private String nm;
     private String dscr;
     private String note;
     private Date created;
     private Long pblsh;
     private Set<Itr> itrs = new HashSet<Itr>(0);

    public Anlz() {
    }

    public Anlz(Usr usr, Instnc instnc, Long seq, String snm, String nm, String dscr, String note, Date created, Long pblsh, Set<Itr> itrs) {
       this.usr = usr;
       this.instnc = instnc;
       this.seq = seq;
       this.snm = snm;
       this.nm = nm;
       this.dscr = dscr;
       this.note = note;
       this.created = created;
       this.pblsh = pblsh;
       this.itrs = itrs;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id_anlz", unique=true, nullable=false)
    public Long getIdAnlz() {
        return this.idAnlz;
    }
    
    public void setIdAnlz(Long idAnlz) {
        this.idAnlz = idAnlz;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_usr")
    public Usr getUsr() {
        return this.usr;
    }
    
    public void setUsr(Usr usr) {
        this.usr = usr;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_instnc")
    public Instnc getInstnc() {
        return this.instnc;
    }
    
    public void setInstnc(Instnc instnc) {
        this.instnc = instnc;
    }
    
    @Column(name="seq")
    public Long getSeq() {
        return this.seq;
    }
    
    public void setSeq(Long seq) {
        this.seq = seq;
    }
    
    @Column(name="snm", length=16)
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
    
    @Column(name="note", length=2048)
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
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
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="anlz")
    public Set<Itr> getItrs() {
        return this.itrs;
    }
    
    public void setItrs(Set<Itr> itrs) {
        this.itrs = itrs;
    }




}


