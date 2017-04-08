package supsvc.model.persistence;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
//import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



import lombok.Data;


///////
/////// unused. would want to persist a history of status changes...
/////// 
@Data
//@Entity
public class Status {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String status;
	
	@Basic(optional = false)
	@Column(name = "revsnTs", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date revsnTs;
	
	
}
