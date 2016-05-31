package thymeleafexamples.stsm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.services.UserService;


@Controller
public class TestForDatabaseOperationController {

	@Autowired
    private UserService userService;
    
    public TestForDatabaseOperationController() {
    	super();
    }
    
    @RequestMapping({"/findSameUserTest"})
    public String TestToFindSameUser() {
    	System.out.println("this just is a test");
    	
    	User newUser = new User();
    	newUser.setName("Frizen");
    	newUser.setPassword("1");
    	
    	int count = userService.findSameUser(newUser);
    	System.out.println(count);
    	
        return "test";
    }
    
    @RequestMapping({"/addNewUserTest"})
    public String TestToAddNewUser() {
    	System.out.println("this just is a test");
    	
    	User newUser = new User();
    	newUser.setName("Frizen2");
    	newUser.setPassword("1");
    	
    	boolean flag = userService.addNewUser(newUser);
    	System.out.println(flag);
    	
        return "test";
    }
    
    @RequestMapping({"/updateUserPassword"})
    public String UpdateUserPassword() {
    	System.out.println("this just is a test");
    	
    	boolean flag = userService.updateUserPsw("123", 1);
    	System.out.println(flag);
    	
        return "test";
    }
    
    @RequestMapping({"/findUserById"})
    public String FindUserById() {
    	System.out.println("this just is a test");
    	
    	User u = userService.findUserById(1);
    	System.out.println(u.getId() + u.getName() + u.getPassword());
    	
        return "test";
    }
}
