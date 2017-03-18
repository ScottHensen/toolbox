package supsvc.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Person {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	private String lastName;
	private String status;
	
//	@Basic(optional = false)
//	@Column(name = "revsnTs", insertable = false, updatable = false)
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date revsnTs;

	//NOTE: no public constructor
	
}
