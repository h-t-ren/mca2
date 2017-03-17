package bs.ecust.domain.db;
// Generated 2017-1-13 20:35:58 by Hibernate Tools 3.2.2.GA


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ItrDotsId generated by hbm2java
 */
@Embeddable
public class ItrDotsId  implements java.io.Serializable {


     private Long idItr;
     private Long idCrtrn;
     private Long pos;

    public ItrDotsId() {
    }

    public ItrDotsId(Long idItr, Long idCrtrn, Long pos) {
       this.idItr = idItr;
       this.idCrtrn = idCrtrn;
       this.pos = pos;
    }
   

    @Column(name="id_itr", nullable=false)
    public Long getIdItr() {
        return this.idItr;
    }
    
    public void setIdItr(Long idItr) {
        this.idItr = idItr;
    }

    @Column(name="id_crtrn", nullable=false)
    public Long getIdCrtrn() {
        return this.idCrtrn;
    }
    
    public void setIdCrtrn(Long idCrtrn) {
        this.idCrtrn = idCrtrn;
    }

    @Column(name="pos", nullable=false)
    public Long getPos() {
        return this.pos;
    }
    
    public void setPos(Long pos) {
        this.pos = pos;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ItrDotsId) ) return false;
		 ItrDotsId castOther = ( ItrDotsId ) other; 
         
		 return ( (this.getIdItr()==castOther.getIdItr()) || ( this.getIdItr()!=null && castOther.getIdItr()!=null && this.getIdItr().equals(castOther.getIdItr()) ) )
 && ( (this.getIdCrtrn()==castOther.getIdCrtrn()) || ( this.getIdCrtrn()!=null && castOther.getIdCrtrn()!=null && this.getIdCrtrn().equals(castOther.getIdCrtrn()) ) )
 && ( (this.getPos()==castOther.getPos()) || ( this.getPos()!=null && castOther.getPos()!=null && this.getPos().equals(castOther.getPos()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIdItr() == null ? 0 : this.getIdItr().hashCode() );
         result = 37 * result + ( getIdCrtrn() == null ? 0 : this.getIdCrtrn().hashCode() );
         result = 37 * result + ( getPos() == null ? 0 : this.getPos().hashCode() );
         return result;
   }   


}


