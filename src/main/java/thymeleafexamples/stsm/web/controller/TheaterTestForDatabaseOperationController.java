package thymeleafexamples.stsm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import thymeleafexamples.stsm.business.entities.Theater;
import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.services.TheaterService;
import thymeleafexamples.stsm.business.services.UserService;

@Controller
public class TheaterTestForDatabaseOperationController {

	@Autowired
    private  TheaterService theaterService;
    
    public TheaterTestForDatabaseOperationController() {
    	super();
    }
    
    @RequestMapping({"/findTheaterById"})
    public String FindTheaterById() {
    	System.out.println("this just is a test");
    	
    	Theater newTh = new Theater();
    	newTh = theaterService.findTheaterById(0);
    	System.out.println(newTh.getId() + newTh.getTheaterName() +
    			newTh.getTheaterLocation() + newTh.getTheaterPhone() +
    			newTh.getTheaterComment());
    	
        return "test";
    }
    
    @RequestMapping({"/findTheaterByName"})
    public String FindTheaterByName() {
    	System.out.println("this just is a test");
    	
    	Theater newTh = new Theater();
    	newTh = theaterService.findTheaterByName("金逸影城");
    	System.out.println(newTh.getId() + newTh.getTheaterName() +
    			newTh.getTheaterLocation() + newTh.getTheaterPhone() +
    			newTh.getTheaterComment());
    	
        return "test";
    }
    
    @RequestMapping({"/findTheaterListByCity"})
    public String FindTheaterListByCity() {
    	System.out.println("this just is a test");
    	
    	List<Theater> res = theaterService.findTheaterListByCity("广州");
    	
    	for (int i = 0; i < res.size(); i++) {
    		System.out.println(res.get(i).MyToString());
    	}
    	
        return "test";
    }
}
