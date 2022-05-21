package acme.entities.chimpum;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;

public class Chimpum extends AbstractEntity{

	// Serialisation identifier		---------------------------------------------

	protected static final long serialVersionUID =	1L;

	// Attributes					---------------------------------------------
	
	@NotNull
	@Pattern(regexp = "^[0-9]{2}-[0-9]{2}-[0-9]{2}$")
	protected String code;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected String creationMoment;
	
	@NotBlank
	@Length(min=1, max=100)
	protected String title;
	
	@NotBlank
	@Length(min=1, max=255)
	protected String			description;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				startPeriodOfTime;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				endPeriodOfTime;
	
	@NotNull
	protected Money 			budget;
	
	// Derived attributes 	----------------------------------
	
	public Duration periodOfTime(){
		return Duration.ofMillis(this.endPeriodOfTime.getTime() - this.startPeriodOfTime.getTime());  
	}

	// Relationships		----------------------------------
}
