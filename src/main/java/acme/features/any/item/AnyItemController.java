package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyItemController extends AbstractController<Any, Item>{

	@Autowired
	protected AnyComponentListService listComponentService;
	
	@Autowired
	protected AnyComponentShowService showComponentService;
	
	@Autowired
	protected AnyItemListByToolkitService listByToolkitService;
	
	@Autowired
	protected AnyToolListService listToolService;
	
	@Autowired
	protected AnyToolShowService showToolService;
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("show", this.showComponentService);
		super.addCommand("list-component","list", this.listComponentService);
		//super.addCommand("show", this.showToolService);
		super.addCommand("list-tool","list", this.listToolService);
		super.addCommand("list-by-toolkit","list", this.listByToolkitService);
	}
}