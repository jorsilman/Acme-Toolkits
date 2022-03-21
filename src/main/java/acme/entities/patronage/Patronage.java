package acme.entities.patronage;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity{
	
	// Serialisation identifier		---------------------------------------------
	
	protected static final long serialVersionUID =	1L;
	
	// Attributes					---------------------------------------------
	
	@NotNull
	protected PatronageStatus		status;
	
	@NotNull
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String 			code;
	
	@NotBlank
	@Length(min=1, max=255)
	protected String			legalStuff;
	
	@NotNull
	@Positive
	protected Money 			budget;
	
	@NotNull
	@Past
	@Temporal(TemporalType.DATE)
	protected Date				creationDate;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				startPeriodOfTime;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				endPeriodOfTime;
	
	@URL
	protected String 			link;
	
	// Derived attributes 	----------------------------------
	
	public Duration periodOfTime(){
		return Duration.ofMillis(this.endPeriodOfTime.getTime() - this.startPeriodOfTime.getTime());  
	}
	
	// Relationships		----------------------------------
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	protected Patron patron;
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	protected Inventor inventor;
	
}
