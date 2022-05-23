package acme.features.inventor.quantity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Quantity;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorQuantityController extends AbstractController<Inventor, Quantity>{
	// Internal state ------------------------------------------------------------

	@Autowired
	protected InventorQuantityListService		listService;

	@Autowired
	protected InventorQuantityShowService		showService;

	@Autowired
	protected InventorQuantityCreateToolService		createToolService;
	
	@Autowired
	protected InventorQuantityCreateComponentService		createComponentService;

	@Autowired
	protected InventorQuantityUpdateService		updateService;

	@Autowired
	protected InventorQuantityDeleteService		deleteService;


	// Constructors --------------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create-tool","create", this.createToolService);
		super.addCommand("create-component", "create",this.createComponentService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
	}
}
