package acme.features.any.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyComponentController extends AbstractController<Any, Item>{

	@Autowired
	protected AnyComponentListService listService;
	
	@Autowired
	protected AnyComponentShowService showService;
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("show", this.showService);
		
		super.addCommand("list-component","list", this.listService);
	}
}