package bs.ecust.domain.db;
// Generated 2017-1-13 20:35:58 by Hibernate Tools 3.2.2.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SpcMthd generated by hbm2java
 */
@Entity
@Table(name="spc_mthd"
    ,catalog="mca2"
)
public class SpcMthd  implements java.io.Serializable {


     private Long idSpcMthd;
     private SpcEnt spcEnt;
     private SpcMthdDict spcMthdDict;
     private Long defBy;
     private Long isReq;

    public SpcMthd() {
    }

	
    public SpcMthd(Long idSpcMthd) {
        this.idSpcMthd = idSpcMthd;
    }
    public SpcMthd(Long idSpcMthd, SpcEnt spcEnt, SpcMthdDict spcMthdDict, Long defBy, Long isReq) {
       this.idSpcMthd = idSpcMthd;
       this.spcEnt = spcEnt;
       this.spcMthdDict = spcMthdDict;
       this.defBy = defBy;
       this.isReq = isReq;
    }
   
     @Id 
    
    @Column(name="id_spc_mthd", unique=true, nullable=false)
    public Long getIdSpcMthd() {
        return this.idSpcMthd;
    }
    
    public void setIdSpcMthd(Long idSpcMthd) {
        this.idSpcMthd = idSpcMthd;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_ent")
    public SpcEnt getSpcEnt() {
        return this.spcEnt;
    }
    
    public void setSpcEnt(SpcEnt spcEnt) {
        this.spcEnt = spcEnt;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_mthd")
    public SpcMthdDict getSpcMthdDict() {
        return this.spcMthdDict;
    }
    
    public void setSpcMthdDict(SpcMthdDict spcMthdDict) {
        this.spcMthdDict = spcMthdDict;
    }
    
    @Column(name="def_by")
    public Long getDefBy() {
        return this.defBy;
    }
    
    public void setDefBy(Long defBy) {
        this.defBy = defBy;
    }
    
    @Column(name="is_req")
    public Long getIsReq() {
        return this.isReq;
    }
    
    public void setIsReq(Long isReq) {
        this.isReq = isReq;
    }




}

