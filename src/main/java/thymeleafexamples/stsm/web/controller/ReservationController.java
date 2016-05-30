/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package thymeleafexamples.stsm.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.services.UserService;

@Controller
@SessionAttributes("currentUser")
public class ReservationController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private UserService userService;
    
    public ReservationController() {
        super();
    }
    
    @ModelAttribute("allUserStarters")
    public List<User> populateUserStarters() {
        return this.userService.findAll();
    }
    
    @RequestMapping({"","/homepage"})
    public String Homepage(final User user, HttpServletRequest req) {
    	HttpSession session = req.getSession(true);
    	
    	session.setMaxInactiveInterval(10000);
    	User currentUser = (User) session.getAttribute("currentUser");
    	if (currentUser != null) {
    		return "result";
    	} else {
            return "index";
    	}
    }
    
    @RequestMapping({"/book"})
    public String Book(final User user) {
        return "book";
    }
    
    @RequestMapping(value={"/homepage","/book"}, params={"login"})
    public String Login(User user,final BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpSession session) {
    	model.addAttribute("str", "111");
    	request.setAttribute("mes", "heh");
    	if (bindingResult.hasErrors()) {
            return "index";
        }
        user = (User)model.get("user");
        try {
        	int count = jdbcTemplate.queryForObject("SELECT count(*) FROM users WHERE userName = ? AND password = ?", new Object[] {user.getName(), user.getPassword()},Integer.class);
        	if (count > 0) {
        		request.getSession().setAttribute("currentUser", user);
        		return "result";
        	}
        }catch(EmptyResultDataAccessException e) {
        	return "index";
        }
    	return "index";
    }

    @RequestMapping("/logout")
    public String Logout(HttpSession session) {
        session.invalidate();
        return "redirect:homepage";
    }
    
    @RequestMapping(value={"/homepage","/book"}, params={"signup"})
    public String Signup(User user, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        jdbcTemplate.update("INSERT INTO users(userName, password) VALUES (?,?)", user.getName(), user.getPassword());
        this.userService.add(user);
        model.put("user", user);
        return "result";
    }
}
