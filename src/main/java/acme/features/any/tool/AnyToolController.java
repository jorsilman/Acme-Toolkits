package acme.features.any.tool;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

public class AnyToolController extends AbstractController<Any, Item>{

	@Autowired
	protected AnyToolListService listService;
	
	@Autowired
	protected AnyToolShowService showService;
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("show", this.showService);
		
		super.addCommand("list22","list", this.listService);
	}
}
