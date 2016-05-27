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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.dao.EmptyResultDataAccessException;

import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.services.UserService;

@Controller
public class SeedStarterMngController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private UserService userService;
    
    public SeedStarterMngController() {
        super();
    }
    
    @ModelAttribute("allUserStarters")
    public List<User> populateUserStarters() {
        return this.userService.findAll();
    }
    
    @RequestMapping({"/","/homepage"})
    public String Homepage(final User user) {
        return "index";
    }
    
    @RequestMapping(value="/homepage", params={"login"})
    public String Login(User user, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        user = (User)model.get("user");
        try {
        	int count = jdbcTemplate.queryForObject("SELECT count(*) FROM users WHERE userName = ? AND password = ?", new Object[] {user.getName(), user.getPassword()},Integer.class);
        	if (count > 0) {
        		return "result";
        	}
        }catch(EmptyResultDataAccessException e) {
        	return "index";
        }
    	return "index";
    }
    
    @RequestMapping(value="/homepage", params={"signup"})
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