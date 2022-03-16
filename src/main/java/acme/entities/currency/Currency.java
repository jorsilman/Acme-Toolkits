package acme.entities.currency;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Currency extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	protected CurrencyType 			currencyType = CurrencyType.EUR;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
