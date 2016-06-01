package thymeleafexamples.stsm.business.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    
	// storage the city we have load
	private List<String> cityHaveLoad = new ArrayList<String>();
	
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
    
    public List<Theater> findTheatersByTheaterIds(List<Integer> ids) {
    	List<Theater> theaterlist = new ArrayList<Theater>();
    	for (int i = 0;i < ids.size(); i++) {
    		Theater newTheater = this.findTheaterById(ids.get(i));
    		theaterlist.add(newTheater);
    	}
		return theaterlist;
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
    
    public List<Theater> findTheaterListByCity(String city) {
    	
    	boolean needToFindInDataBase = true;
    	
    	// check in buffer
    	for (int i = 0; i < cityHaveLoad.size(); i++) {
    		if (cityHaveLoad.get(i).equals(city)) {
    			// System.out.println("find in buffer!");
    			needToFindInDataBase = false;
    		}
    	}
    	
    	if (needToFindInDataBase) {
    		// System.out.println("find in dataBase");
    		cityHaveLoad.add(city);
    		
	    	// find in database
	    	List<Map<String, Object>> rows = jdbcTemplate.queryForList(
	    			"SELECT * FROM moviejava.theaters where theaterCity = ?",
	    			new Object[] {city}); 
	    	
	    	return ChangeMapListToList(rows);
    	} else {
    		// find in buffer
    		// System.out.println("find in buffer");
    		List<Theater> thList = new ArrayList<Theater>();
    		
    		for (int i = 0; i < bufferTheaterList.size(); i++) {
    			Theater temp = bufferTheaterList.get(i);
    			if (temp.getTheaterCity().toString().equals(city)) {
    				thList.add(bufferTheaterList.get(i));
    			}
    		}
    		
    		return thList;
    	}
    }
    
    private List<Theater> ChangeMapListToList(List rows) {
    	List<Theater> thList = new ArrayList<Theater>();

    	Iterator it = rows.iterator(); 
    	while(it.hasNext()) { 
    	    Map theaterMap = (Map) it.next(); 
    	    Theater newTh = new Theater();
    	    
    	    // for safe
    	    Integer newThId = new Integer(-1);
    	    try {
    	    	newThId = Integer.parseInt(theaterMap.get("id").toString());
    	    } catch(Exception e) {
    	    	e.printStackTrace();
    	    }
    	    
    	    newTh.setId(newThId);
    	    newTh.setTheaterCity(theaterMap.get("theaterCity").toString());
    	    newTh.setTheaterComment(theaterMap.get("theaterComment").toString());
    	    newTh.setTheaterLocation(theaterMap.get("theaterLocation").toString());
    	    newTh.setTheaterName(theaterMap.get("theaterName").toString());
    	    newTh.setTheaterPhone(theaterMap.get("theaterPhone").toString());
    	    
    	    if (newTh.getId() != null) {
    	    	thList.add(newTh);
    	    	// also add into buffer
    	    	bufferTheaterList.add(newTh);
    	    }
    	}
    	
    	return thList;
    }
}
