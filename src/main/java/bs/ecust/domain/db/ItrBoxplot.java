package bs.ecust.domain.db;
// Generated 2017-1-13 20:35:58 by Hibernate Tools 3.2.2.GA


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ItrBoxplot generated by hbm2java
 */
@Entity
@Table(name="itr_boxplot"
    ,catalog="mca2"
)
public class ItrBoxplot  implements java.io.Serializable {


     private ItrBoxplotId id;
     private Itr itr;
     private Crtrn crtrn;
     private Long lft;
     private Long mdl;
     private Long rght;

    public ItrBoxplot() {
    }

	
    public ItrBoxplot(ItrBoxplotId id, Itr itr, Crtrn crtrn) {
        this.id = id;
        this.itr = itr;
        this.crtrn = crtrn;
    }
    public ItrBoxplot(ItrBoxplotId id, Itr itr, Crtrn crtrn, Long lft, Long mdl, Long rght) {
       this.id = id;
       this.itr = itr;
       this.crtrn = crtrn;
       this.lft = lft;
       this.mdl = mdl;
       this.rght = rght;
    }
   
     @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="idItr", column=@Column(name="id_itr", nullable=false) ), 
        @AttributeOverride(name="idCrtrn", column=@Column(name="id_crtrn", nullable=false) ) } )
    public ItrBoxplotId getId() {
        return this.id;
    }
    
    public void setId(ItrBoxplotId id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_itr", nullable=false, insertable=false, updatable=false)
    public Itr getItr() {
        return this.itr;
    }
    
    public void setItr(Itr itr) {
        this.itr = itr;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_crtrn", nullable=false, insertable=false, updatable=false)
    public Crtrn getCrtrn() {
        return this.crtrn;
    }
    
    public void setCrtrn(Crtrn crtrn) {
        this.crtrn = crtrn;
    }
    
    @Column(name="lft")
    public Long getLft() {
        return this.lft;
    }
    
    public void setLft(Long lft) {
        this.lft = lft;
    }
    
    @Column(name="mdl")
    public Long getMdl() {
        return this.mdl;
    }
    
    public void setMdl(Long mdl) {
        this.mdl = mdl;
    }
    
    @Column(name="rght")
    public Long getRght() {
        return this.rght;
    }
    
    public void setRght(Long rght) {
        this.rght = rght;
    }




}


