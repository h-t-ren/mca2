package bs.ecust.domain.db;
// Generated 2017-1-13 20:35:58 by Hibernate Tools 3.2.2.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TTime generated by hbm2java
 */
@Entity
@Table(name="t_time"
    ,catalog="mca2"
)
public class TTime  implements java.io.Serializable {


     private Long idItr;
     private String insName;
     private Long PTime;
     private String CDate;
     private Long RTime;
     private Long STime;
     private Long DTime;
     private Long solverT;
     private Long solverCpu;
     private String ip;

    public TTime() {
    }

	
    public TTime(Long idItr) {
        this.idItr = idItr;
    }
    public TTime(Long idItr, String insName, Long PTime, String CDate, Long RTime, Long STime, Long DTime, Long solverT, Long solverCpu, String ip) {
       this.idItr = idItr;
       this.insName = insName;
       this.PTime = PTime;
       this.CDate = CDate;
       this.RTime = RTime;
       this.STime = STime;
       this.DTime = DTime;
       this.solverT = solverT;
       this.solverCpu = solverCpu;
       this.ip = ip;
    }
   
     @Id 
    
    @Column(name="id_itr", unique=true, nullable=false)
    public Long getIdItr() {
        return this.idItr;
    }
    
    public void setIdItr(Long idItr) {
        this.idItr = idItr;
    }
    
    @Column(name="ins_name", length=1024)
    public String getInsName() {
        return this.insName;
    }
    
    public void setInsName(String insName) {
        this.insName = insName;
    }
    
    @Column(name="p_time")
    public Long getPTime() {
        return this.PTime;
    }
    
    public void setPTime(Long PTime) {
        this.PTime = PTime;
    }
    
    @Column(name="c_date", length=254)
    public String getCDate() {
        return this.CDate;
    }
    
    public void setCDate(String CDate) {
        this.CDate = CDate;
    }
    
    @Column(name="r_time")
    public Long getRTime() {
        return this.RTime;
    }
    
    public void setRTime(Long RTime) {
        this.RTime = RTime;
    }
    
    @Column(name="s_time")
    public Long getSTime() {
        return this.STime;
    }
    
    public void setSTime(Long STime) {
        this.STime = STime;
    }
    
    @Column(name="d_time")
    public Long getDTime() {
        return this.DTime;
    }
    
    public void setDTime(Long DTime) {
        this.DTime = DTime;
    }
    
    @Column(name="solver_t")
    public Long getSolverT() {
        return this.solverT;
    }
    
    public void setSolverT(Long solverT) {
        this.solverT = solverT;
    }
    
    @Column(name="solver_cpu")
    public Long getSolverCpu() {
        return this.solverCpu;
    }
    
    public void setSolverCpu(Long solverCpu) {
        this.solverCpu = solverCpu;
    }
    
    @Column(name="ip", length=1024)
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }




}


