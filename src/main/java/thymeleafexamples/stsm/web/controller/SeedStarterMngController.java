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

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.EmptyResultDataAccessException;

import thymeleafexamples.stsm.business.entities.Feature;
import thymeleafexamples.stsm.business.entities.Row;
import thymeleafexamples.stsm.business.entities.SeedStarter;
import thymeleafexamples.stsm.business.entities.Type;
import thymeleafexamples.stsm.business.entities.Variety;
import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.services.SeedStarterService;
import thymeleafexamples.stsm.business.services.VarietyService;
import thymeleafexamples.stsm.business.services.UserService;

@Controller
public class SeedStarterMngController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private VarietyService varietyService;
    
    @Autowired
    private SeedStarterService seedStarterService;
    
    @Autowired
    private UserService userService;
    
    public SeedStarterMngController() {
        super();
    }
    
    
    @ModelAttribute("allTypes")
    public List<Type> populateTypes() {
        return Arrays.asList(Type.ALL);
    }
    
    @ModelAttribute("allFeatures")
    public List<Feature> populateFeatures() {
        return Arrays.asList(Feature.ALL);
    }
    
    @ModelAttribute("allVarieties")
    public List<Variety> populateVarieties() {
        return this.varietyService.findAll();
    }
    
    @ModelAttribute("allSeedStarters")
    public List<SeedStarter> populateSeedStarters() {
        return this.seedStarterService.findAll();
    }
    
    @ModelAttribute("allUserStarters")
    public List<User> populateUserStarters() {
        return this.userService.findAll();
    }
    
    
    
    
    @RequestMapping({"/","/seedstartermng"})
    public String showSeedstarters(final SeedStarter seedStarter) {
        seedStarter.setDatePlanted(Calendar.getInstance().getTime());
        return "seedstartermng";
    }
    
    
    
    @RequestMapping(value="/seedstartermng", params={"save"})
    public String saveSeedstarter(final SeedStarter seedStarter, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "seedstartermng";
        }
        this.seedStarterService.add(seedStarter);
        model.clear();
        return "redirect:/seedstartermng";
    }
    

    
    @RequestMapping(value="/seedstartermng", params={"addRow"})
    public String addRow(final SeedStarter seedStarter, final BindingResult bindingResult) {
        seedStarter.getRows().add(new Row());
        return "seedstartermng";
    }
    
    
    @RequestMapping(value="/seedstartermng", params={"removeRow"})
    public String removeRow(final SeedStarter seedStarter, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        seedStarter.getRows().remove(rowId.intValue());
        return "seedstartermng";
    }
    
    @RequestMapping({"/signup"})
    public String signUp(final User user) {
    	jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users(id SERIAL, userName VARCHAR(255), passWord VARCHAR(255))");
        return "signup";
    }
    
	@RequestMapping(value="/signup", method=RequestMethod.POST)
    public String checkPersonInfo(User user, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        jdbcTemplate.update("INSERT INTO users(userName, password) VALUES (?,?)", user.getName(), user.getPassword());
        this.userService.add(user);
        model.put("user", user);
        return "result";
    }
    
    @RequestMapping({"/login"})
    public String logIn(final User user) {
        return "login";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String checkLoginIn(User user, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        user = (User)model.get("user");
        List<User> temp = this.userService.findAll();
        /*for(int i=0;i<temp.size();i++)
        {
           if(temp.get(i).getName().equals(user.getName())
        	&&temp.get(i).getPassword().equals(user.getPassword())) {
        	   return "result";
           }
        }*/
        try {
        	int count = jdbcTemplate.queryForObject("SELECT count(*) FROM users WHERE userName = ? AND password = ?", new Object[] {user.getName(), user.getPassword()},Integer.class);
        	if (count > 0) {
        		return "result";
        	}
        }catch(EmptyResultDataAccessException e) {
        	return "login";
        }
        return "login";
    }
}