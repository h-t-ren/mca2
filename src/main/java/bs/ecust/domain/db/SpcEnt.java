package bs.ecust.domain.db;
// Generated 2017-1-13 20:35:58 by Hibernate Tools 3.2.2.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SpcEnt generated by hbm2java
 */
@Entity
@Table(name="spc_ent"
    ,catalog="mca2"
)
public class SpcEnt  implements java.io.Serializable {


     private Long idEnt;
     private Long entType;
     private Long valueId;
     private String snm;
     private String nm;
     private Long seq;
     private Set<ItrEntVl> itrEntVls = new HashSet<ItrEntVl>(0);
     private Set<SpcMthd> spcMthds = new HashSet<SpcMthd>(0);

    public SpcEnt() {
    }

	
    public SpcEnt(Long idEnt) {
        this.idEnt = idEnt;
    }
    public SpcEnt(Long idEnt, Long entType, Long valueId, String snm, String nm, Long seq, Set<ItrEntVl> itrEntVls, Set<SpcMthd> spcMthds) {
       this.idEnt = idEnt;
       this.entType = entType;
       this.valueId = valueId;
       this.snm = snm;
       this.nm = nm;
       this.seq = seq;
       this.itrEntVls = itrEntVls;
       this.spcMthds = spcMthds;
    }
   
     @Id 
    
    @Column(name="id_ent", unique=true, nullable=false)
    public Long getIdEnt() {
        return this.idEnt;
    }
    
    public void setIdEnt(Long idEnt) {
        this.idEnt = idEnt;
    }
    
    @Column(name="ent_type")
    public Long getEntType() {
        return this.entType;
    }
    
    public void setEntType(Long entType) {
        this.entType = entType;
    }
    
    @Column(name="value_id")
    public Long getValueId() {
        return this.valueId;
    }
    
    public void setValueId(Long valueId) {
        this.valueId = valueId;
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
    
    @Column(name="seq")
    public Long getSeq() {
        return this.seq;
    }
    
    public void setSeq(Long seq) {
        this.seq = seq;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="spcEnt")
    public Set<ItrEntVl> getItrEntVls() {
        return this.itrEntVls;
    }
    
    public void setItrEntVls(Set<ItrEntVl> itrEntVls) {
        this.itrEntVls = itrEntVls;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="spcEnt")
    public Set<SpcMthd> getSpcMthds() {
        return this.spcMthds;
    }
    
    public void setSpcMthds(Set<SpcMthd> spcMthds) {
        this.spcMthds = spcMthds;
    }




}


