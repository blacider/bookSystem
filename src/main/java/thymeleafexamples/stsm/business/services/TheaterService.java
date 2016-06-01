package thymeleafexamples.stsm.business.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import thymeleafexamples.stsm.business.entities.Theater;

@Service
public class TheaterService {
    
    //@Autowired
    //private UserRepository userRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	// storage all living users
	private List<Theater> bufferTheaterList = new ArrayList<Theater>();
    
    public TheaterService() {
        super();
    }
    
    public Theater findTheaterById(Integer id) {
    	
    	// find in the buffer theater list first
    	for (int i = 0; i < bufferTheaterList.size(); i++) {
    		Theater temp = bufferTheaterList.get(i);
    		if (temp.getId() == id) {
    			return temp;
    		}
    	}
    	
    	final Theater th = new Theater();
    	jdbcTemplate.query("SELECT * FROM theaters WHERE id = ?",
    			new Object[] {id}, new RowCallbackHandler() { 
                    public void processRow(ResultSet rs) throws SQLException { 
                        th.setId(rs.getInt("id")); 
                        th.setTheaterName(rs.getString("theaterName"));
                        th.setTheaterLocation(rs.getString("theaterLocation"));
                        th.setTheaterPhone(rs.getString("theaterPhone"));
                        th.setTheaterComment(rs.getString("theaterComment"));
                    } 
                });
    	
    	// add the result to the buffer
    	if (th.getId() != null) {
    		bufferTheaterList.add(th);
    	}
    	
    	return th;
    }
    
    public Theater findTheaterByName(String name) {
    	
    	// find in the buffer theater list first
    	for (int i = 0; i < bufferTheaterList.size(); i++) {
    		Theater temp = bufferTheaterList.get(i);
    		if (temp.getTheaterName() == name) {
    			return temp;
    		}
    	}
    	
    	final Theater th = new Theater();
    	jdbcTemplate.query("SELECT * FROM theaters WHERE theaterName = ?",
    			new Object[] {name}, new RowCallbackHandler() { 
                    public void processRow(ResultSet rs) throws SQLException { 
                        th.setId(rs.getInt("id")); 
                        th.setTheaterName(rs.getString("theaterName"));
                        th.setTheaterLocation(rs.getString("theaterLocation"));
                        th.setTheaterPhone(rs.getString("theaterPhone"));
                        th.setTheaterComment(rs.getString("theaterComment"));
                    } 
                });
    	
    	// add the result to the buffer
    	if (th.getId() != null) {
    		bufferTheaterList.add(th);
    	}
    	return th;
    }
    
    public List<Theater> findTheaterListByCity() {
    	List<Theater> thList = new ArrayList<Theater>();
    	return thList;
    }
}
