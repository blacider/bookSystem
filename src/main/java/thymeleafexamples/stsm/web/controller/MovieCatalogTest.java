package thymeleafexamples.stsm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.services.MovieCatalogService;
import thymeleafexamples.stsm.business.services.UserService;

@Controller
public class MovieCatalogTest {

	@Autowired
    private MovieCatalogService movieCatalogService;
	
	@RequestMapping({"/findMovieIdByTheaterId"})
    public String FindMovieIdByTheaterId() {
		
		List<Integer> res = movieCatalogService.findMovieIdList(0);
		
		for (int i = 0; i < res.size(); i++) {
    		System.out.println(res.get(i).toString());
    	}
    	
        return "test";
    }
	
	@RequestMapping({"/findMovieIdByMovieId"})
    public String FindMovieIdByMovieId() {
		
		List<Integer> res = movieCatalogService.findTheaterIdList(0);
		
		for (int i = 0; i < res.size(); i++) {
    		System.out.println(res.get(i).toString());
    	}
    	
        return "test";
    }
}
