package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyToolController extends AbstractController<Any, Item>{

	@Autowired
	protected AnyToolListService listService;
	
	@Autowired
	protected AnyToolShowService showService;
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("show", this.showService);
		
		super.addCommand("list-tool","list", this.listService);
	}
}
