package bs.ecust.domain.db;
// Generated 2017-1-13 20:35:58 by Hibernate Tools 3.2.2.GA


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EntHntId generated by hbm2java
 */
@Embeddable
public class EntHntId  implements java.io.Serializable {


     private Long idEnt;
     private Long seq;

    public EntHntId() {
    }

    public EntHntId(Long idEnt, Long seq) {
       this.idEnt = idEnt;
       this.seq = seq;
    }
   

    @Column(name="id_ent", nullable=false)
    public Long getIdEnt() {
        return this.idEnt;
    }
    
    public void setIdEnt(Long idEnt) {
        this.idEnt = idEnt;
    }

    @Column(name="seq", nullable=false)
    public Long getSeq() {
        return this.seq;
    }
    
    public void setSeq(Long seq) {
        this.seq = seq;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EntHntId) ) return false;
		 EntHntId castOther = ( EntHntId ) other; 
         
		 return ( (this.getIdEnt()==castOther.getIdEnt()) || ( this.getIdEnt()!=null && castOther.getIdEnt()!=null && this.getIdEnt().equals(castOther.getIdEnt()) ) )
 && ( (this.getSeq()==castOther.getSeq()) || ( this.getSeq()!=null && castOther.getSeq()!=null && this.getSeq().equals(castOther.getSeq()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIdEnt() == null ? 0 : this.getIdEnt().hashCode() );
         result = 37 * result + ( getSeq() == null ? 0 : this.getSeq().hashCode() );
         return result;
   }   


}

