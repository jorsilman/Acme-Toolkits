package acme.entities.spam;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter	 
public class Spam {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	protected SpamType 			spamType;

	@NotNull
	protected SpamLanguage 		spamLanguage;

	@NotBlank
	protected String 			word;

	// Derived attributes -----------------------------------------------------

	public Double getThreshold() {
		if(this.spamType.equals(SpamType.WEAK)) {
			return 0.25;
		}
		else{
			return 	0.1;	
		}
	}
	// Relationships ----------------------------------------------------------


}
