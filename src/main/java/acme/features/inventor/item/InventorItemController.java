package acme.features.inventor.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item>{
	
	// Internal State --------------------------------------------------------------------------
	
	@Autowired
	protected InventorComponentListService listComponentService;
	
	@Autowired
	protected InventorComponentShowService showComponentService;
	
	@Autowired
	protected InventorToolListService listToolService;
	
	@Autowired
	protected InventorToolShowService showToolService;
	
	@Autowired
	protected InventorComponentCreateService createComponentService;
	
	@Autowired
	protected InventorComponentUpdateService updateComponentService;
	
	@Autowired
	protected InventorComponentDeleteService deleteComponentService;
	
	@Autowired
	protected InventorItemPublishService publishItemService;

	@Autowired
	protected InventorToolCreateService createToolService;
	
	@Autowired
	protected InventorToolUpdateService updateToolService;
	
	@Autowired
	protected InventorToolDeleteService deleteToolService;


	
	// Constructors ----------------------------------------------------------------------------
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("list-component","list", this.listComponentService);
		super.addCommand("show", this.showComponentService);
		super.addCommand("list-tool","list", this.listToolService);

		super.addCommand("create-component","create", this.createComponentService);
		super.addCommand("update-component", "update", this.updateComponentService);
		super.addCommand("delete-component", "delete", this.deleteComponentService);
	
		super.addCommand("publish", "update", this.publishItemService);

		super.addCommand("create-tool", "create", this.createToolService);
		super.addCommand("update-tool", "update", this.updateToolService);
		super.addCommand("delete-tool", "delete", this.deleteToolService);
		super.addCommand("show-tool","show", this.showToolService);

	}

}
