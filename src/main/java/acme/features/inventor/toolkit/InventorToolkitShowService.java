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
		// TODO Auto-generated method stub
		assert request != null;

		return true;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		// TODO Auto-generated method stub
		assert request != null;
		final int id  = request.getModel().getInteger("id");
		final Toolkit result = this.repository.findOneToolkitById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes","published", "link");

		final int id = request.getModel().getInteger("id");
		final Double euros = this.repository.findRetailPriceByToolkitId(id, "EUR");
		final Money retailP = new Money();
		retailP.setAmount(euros);
		retailP.setCurrency("EUR");
		model.setAttribute("retailPrice", retailP);
	}

}
