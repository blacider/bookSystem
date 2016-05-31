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
package thymeleafexamples.stsm.business.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

// abandon user Repository, save the message in user service
// also do the database operation here

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;
import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.entities.repositories.UserRepository;

@Service
public class UserService {
    
    //@Autowired
    //private UserRepository userRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	// storage all living users
	private List<User> userList = new ArrayList<User>();
    
    public UserService() {
        super();
    }
    
    // return the count of user that has same name and password, it would be 1 if exist
    public Integer findSameUser(User user) {
    	int count = jdbcTemplate.queryForObject("SELECT count(*) FROM users WHERE userName = ? AND password = ?",
    			new Object[] {user.getName(), user.getPassword()},Integer.class);
    	return count;
    }
    
    // add a new user, return true if success and false if failure
    public boolean addNewUser(User user) {
    	// if same username, return fasle
    	int count = jdbcTemplate.queryForObject("SELECT count(*) FROM users WHERE userName = ?",
    			new Object[] {user.getName()},Integer.class);
    	if (0 != count) return false;
    	
    	jdbcTemplate.update("INSERT INTO users(userName, password) VALUES (?,?)",
    			user.getName(), user.getPassword());
    	
    	// query again to get user id ...so sad
    	int result = jdbcTemplate.queryForObject("SELECT users.id FROM users WHERE userName = ?",
    			new Object[] {user.getName()},Integer.class);
    	System.out.println("result is : " + result);
    	
    	// add this user to the online userList
    	user.setId(result);
    	userList.add(user);
    	
    	return true;
    }
    
    public boolean updateUserPsw(String psw, Integer id) {
    	int result = jdbcTemplate.update("UPDATE users SET password= ? WHERE id = ?",
    			psw, id);
    	if (0 == result) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    // only delete in the online user list
    public boolean userLogout(Integer id) {
    	for(int i = 0;i < userList.size(); i ++){
            if ( userList.get(i).getId() == id ) {
            	return true;
            }
        }
    	return false;
    }
    
    // find user by id
    public User findUserById(Integer id) {
    	final User u = new User();
    	
    	jdbcTemplate.query("SELECT * FROM users WHERE id = ?",
    			new Object[] {id}, new RowCallbackHandler() { 
                    public void processRow(ResultSet rs) throws SQLException { 
                        u.setId(rs.getInt("id")); 
                        u.setName(rs.getString("userName")); 
                        u.setPassword(rs.getString("password"));
                    } 
                });
    	
    	return u;
    }
    
    public String getPasswordByUserName(String userName) {
    	String psw = jdbcTemplate.queryForObject("SELECT users.password FROM users WHERE userName = ?",
    			new Object[] {userName},String.class);
    	return psw;
    }
    
    public List<User> findAll() {
        return this.userList;
    }

    public void add(final User user) {
        this.userList.add(user);
    }
    
}
