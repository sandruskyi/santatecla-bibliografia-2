package es.daw.bibliografia.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.Cita;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.Autor;
import es.daw.bibliografia.user.Tabs;
import es.daw.bibliografia.user.UserComponent;

@Controller
public class TemaController {
	
	@Autowired
	private TemaService temaService;

	@Autowired
	private CitaService citaService;

	@Autowired
	private ObraService obraService;

	@Autowired
	private AutorService autorService;

	@Autowired
	private BookWebController webController;

	@Autowired
	private UserComponent userComponent;

	@RequestMapping("/tema/{nombreTema}")
	public String accederTema(Model model, @PathVariable String nombreTema) {
		
		userTabs(model, (String) ("/tema/" + nombreTema), (String) ("Tema " + nombreTema) , true);
		
		Optional <Tema> tema = temaService.findOneByContenido(nombreTema);
		webController.addUserToModel(model);
		
		if(tema.isPresent()) {
			Tema theme= tema.get();
			List<Obra> obras = theme.getObras();
			List<Cita> citas = new ArrayList<>();
			List<Autor> autores = new ArrayList<>();
			for(int i=0; i<obras.size(); i++) {
				citas = Stream.concat(citas.stream(), obras.get(i).getCitas().stream())
                        .collect(Collectors.toList());
				autores = Stream.concat(autores.stream(), obras.get(i).getAutores().stream())
                        .collect(Collectors.toList());
			}
			
			model.addAttribute("obras", obras);
			model.addAttribute("citas", citas);
			model.addAttribute("autores", autores);
			
			return "tema";
		}
		else
			return "error";
	}

	private void userTabs(Model model, String url, String name, boolean active) {
		Tabs tab = new Tabs(url, name, active);
		
		if (!sameTab(tab)) {
			updateActiveTabs(active);
			if (this.userComponent.isLoggedUser()) {
				this.userComponent.getLoggedUser().addTab(tab);
				model.addAttribute("tabs", this.userComponent.getLoggedUser().getTabs());
			}
		}
	}
	
	public void deleteTab(String url) {
		this.userComponent.getLoggedUser().deleteTabByUrl(url);
	}
	
	public void updateActiveTabs(boolean active) {
		if (active==true) {
			this.userComponent.getLoggedUser().inactiveAllTabs();
		}
	}

	public boolean sameTab(Tabs tab) {
		for (int i = 0; i < this.userComponent.getLoggedUser().getTabs().size(); i++) {
			if (this.userComponent.getLoggedUser().getTabs().get(i).getName().equalsIgnoreCase(tab.getName())
					&& this.userComponent.getLoggedUser().getTabs().get(i).getUrl().equalsIgnoreCase(tab.getUrl())) {
				return true;
			}
		}
		return false;
	}

}
