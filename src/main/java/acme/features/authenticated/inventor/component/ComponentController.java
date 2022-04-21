package acme.features.authenticated.inventor.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class ComponentController extends AbstractController<Inventor, Item>{
	
	@Autowired
	protected ComponentListService listService;
	
	@Autowired
	protected ComponentShowService showService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}

}
