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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thymeleafexamples.stsm.business.entities.Movie;
import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.services.MovieService;
import thymeleafexamples.stsm.business.services.UserService;

@Controller
public class ReservationController {
	@Autowired
    private UserService userService;
    
    @Autowired
    private MovieService movieService;
    
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
    	String error = (String)session.getAttribute("error");
        if (error != null) {
            req.setAttribute("error", error);
            session.removeAttribute("error");
        }
        List<Movie> movies = movieService.getAllMovie();
        req.setAttribute("movies", movies);
    	return "index";
    }
    
    @RequestMapping("/book")
    public String Book(final User user,@RequestParam(value="id", required=false, defaultValue="1") String movieId, ModelMap model) {
    	Movie movie = movieService.getMovieByMovieId(movieId);
    	model.addAttribute("movie",movie);
        return "book";
    }
    
    @RequestMapping("/login")
    public String Login(User user,final BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpSession session) {
        user = (User)model.get("user");
        String pas = userService.getPasswordByUserName(user.getName()); 
        if (pas == null)
            session.setAttribute("error", "Username Not Found!");
        else if (!pas.equals(user.getPassword()))
                session.setAttribute("error", "Incorrect Password!");
            else
                session.setAttribute("currentUser", user.getName());

        String history = request.getParameter("history").toString();
        String[] strarray=history.split("/");
        String destination; 
        if (strarray.length == 2) {
        	destination = "/";
        } else {
        	destination = strarray[2];
        	for (int i = 3;i < strarray.length;i++) {
        		destination = destination + "/" + strarray[i];
        	}
        }
        String path = "redirect:" + destination.toString();
        return path;
    }

    @RequestMapping("/logout")
    public String Logout(HttpSession session) {
        session.invalidate();
        return "redirect:homepage";
    }
    
    @RequestMapping("/signup")
    public String Signup(User user, final BindingResult bindingResult, final ModelMap model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        if (this.userService.addNewUser(user)) {
            model.put("user", user);
            session.setAttribute("currentUser", user.getName());
        }
        return "redirect:/";
    }
}

