package thymeleafexamples.stsm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import thymeleafexamples.stsm.business.entities.Theater;
import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.services.TheaterService;
import thymeleafexamples.stsm.business.services.UserService;

@Controller
public class ThearterTestForDatabaseOperationController {

	@Autowired
    private  TheaterService thearterService;
    
    public ThearterTestForDatabaseOperationController() {
    	super();
    }
    
    @RequestMapping({"/findThearterById"})
    public String FindThearterById() {
    	System.out.println("this just is a test");
    	
    	Theater newTh = new Theater();
    	newTh = thearterService.findTheaterById(0);
    	System.out.println(newTh.getId() + newTh.getTheaterName() +
    			newTh.getTheaterLocation() + newTh.getTheaterPhone() +
    			newTh.getTheaterComment());
    	
        return "test";
    }
    
}
