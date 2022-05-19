package acme.features.inventor.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronageReport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportShowService implements AbstractShowService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;

	// AbstractShowService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		final int id = request.getModel().getInteger("id");
		final PatronageReport patronageReport = this.repository.findOnePatronageReportById(id);
		return patronageReport != null && patronageReport.getPatronage().getInventor().getId()==request.getPrincipal().getActiveRoleId();

		
	}

	@Override
	public PatronageReport findOne(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageReportById(id);

		return result;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "creationMoment", "memorandum", "link");
	}

}
