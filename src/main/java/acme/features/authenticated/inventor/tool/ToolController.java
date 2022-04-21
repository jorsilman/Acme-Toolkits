package acme.features.authenticated.inventor.tool;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class ToolController extends AbstractController<Inventor, Item>{
		
		// Internal State --------------------------------------------------------------------------
		
		@Autowired
		protected ToolListService listService;
		
		@Autowired
		protected ToolShowService showService;
		
		// Constructors ----------------------------------------------------------------------------
		
		@PostConstruct
		protected void initialise() {
			super.addCommand("list", this.listService);
			super.addCommand("show", this.showService);
		}

}
