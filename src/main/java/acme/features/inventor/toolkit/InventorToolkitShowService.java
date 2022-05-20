package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		
		assert request != null;

		return true;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		
		assert request != null;
		final int id  = request.getModel().getInteger("id");
		return this.repository.findToolkitById(id);
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes","published", "link");

		final int id = request.getModel().getInteger("id");
		final Double euros = this.repository.findRetailPriceByToolkitId(id, "EUR");
		final Money retailPriceEuro = new Money();
		retailPriceEuro.setAmount(euros);
		retailPriceEuro.setCurrency("EUR");
		final Money retailPriceDollar = new Money();
		final Double dollar = this.repository.findRetailPriceByToolkitId(id, "USD");
		retailPriceDollar.setAmount(dollar);
		retailPriceDollar.setCurrency("USD");
		final Money retailPriceGBD = new Money();
		final Double gbd = this.repository.findRetailPriceByToolkitId(id, "GBD");
		retailPriceGBD.setAmount(gbd);
		retailPriceGBD.setCurrency("GBD");
		model.setAttribute("retailPriceEuro", retailPriceEuro.toString());
		model.setAttribute("retailPriceDollar", retailPriceDollar.toString());
		model.setAttribute("retailPriceGBD", retailPriceGBD.toString());
			
	}

}
