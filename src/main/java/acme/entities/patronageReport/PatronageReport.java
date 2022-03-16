package acme.entities.patronageReport;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.patronage.Patronage;
import acme.framework.entities.AbstractEntity;


public class PatronageReport extends AbstractEntity {
	
		//Serialisation number
		protected static final long serialVersionUID	= 1L;
		
		//Attributes
		
		@NotBlank
		protected Integer 			serialNumber;
		
		@NotNull
		@Temporal(TemporalType.DATE)
		@Past
		protected Date 				creationMoment;
		
		@NotBlank
		@Length(max = 255)
		protected String 			memorandum;
		
		@URL
		protected String			link;
		
		
		
		//Derived attributes
		@NotBlank
		public String sequenceNumber(){
			return this.patronage.getCode() + ":" + this.serialNumber;
			
		}
		//Relationships
		@Valid
		@NotNull
		@ManyToOne(optional = false)
		protected Patronage patronage;
		
		
		

}
