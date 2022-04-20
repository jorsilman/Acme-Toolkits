package acme.features.inventor.items;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item>{
	@Autowired
	protected InventorItemListOwnService listOwnService;
	
	@Autowired
	protected InventorItemShowService showOwnService;
	
	@Autowired
	protected InventorItemListToolkitService listByToolkitService;
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("show", this.showOwnService);
		super.addCommand("list-own","list", this.listOwnService);
		super.addCommand("list-by-toolkit","list", this.listByToolkitService);
	}

}
