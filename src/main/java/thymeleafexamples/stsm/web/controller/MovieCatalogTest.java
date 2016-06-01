package thymeleafexamples.stsm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.services.MovieCatalogService;
import thymeleafexamples.stsm.business.services.UserService;

@Controller
public class MovieCatalogTest {

	@Autowired
    private MovieCatalogService movieCatalogService;
	
	@ModelAttribute("allUserStarters")
    public String populateUserStarters() {
		
		movieCatalogService.findMoviesList(0);
		
        return "test";
    }
}
