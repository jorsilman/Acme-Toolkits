package acme.entities.patronage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
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
	protected StatusEnum		status;
	
	@NotNull
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String 			code;
	
	@NotNull
	@NotBlank
	@Length(max=255)
	protected String			legalStuff;
	
	@NotNull
	@Positive
	protected Money 			budget;
	
	@NotNull		//ACABAR
	protected Date				periodOfTime;
	
	@URL
	protected String 			link;
	
	
	

}
