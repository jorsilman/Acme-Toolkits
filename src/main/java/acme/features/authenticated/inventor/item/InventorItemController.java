package acme.features.authenticated.inventor.item;

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
	
	// Constructors ----------------------------------------------------------------------------
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("list-component","list", this.listComponentService);
		super.addCommand("show", this.showComponentService);
		super.addCommand("list-tool","list", this.listToolService);
		//super.addCommand("show-tool","show", this.showToolService);
	}

}
