package thymeleafexamples.stsm.business.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import thymeleafexamples.stsm.business.entities.MovieCatalog;
import thymeleafexamples.stsm.business.entities.Theater;

@Service
public class MovieCatalogService {
    //@Autowired
    //private UserRepository userRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	enum ListType {
		movieList, theaterList
	}
	
	private class OneCatalog {
		private Integer id;
		private List<Integer> list = new ArrayList<Integer>();
		/**
		 * @return the id
		 */
		public Integer getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(Integer id) {
			this.id = id;
		}
		/**
		 * @return the list
		 */
		public List<Integer> getList() {
			return list;
		}
		/**
		 * @param list the list to set
		 */
		public void setList(List<Integer> list) {
			this.list = list;
		}
	}
	// storage buffer movieIdList
	private List<OneCatalog> bufferMovieIdList = new ArrayList<OneCatalog>();
	// storage buffer theaterIdList
	private List<OneCatalog> bufferTheaterIdList = new ArrayList<OneCatalog>();
	
	public MovieCatalogService() {
		super();
	}
	
	public List<Integer> findMovieIdList(Integer theaterId) {
		
		// find in buffer first
		for (int i = 0; i < bufferTheaterIdList.size(); i++) {
			OneCatalog temp = bufferTheaterIdList.get(i);
			if (theaterId == temp.getId()) {
				return temp.getList();
			}
		}
		

		// find in database
		List<Integer> l = new ArrayList<Integer>();
    	List<Map<String, Object>> rows = jdbcTemplate.queryForList(
    			"SELECT * FROM moviejava.moviecatalog where theaterId = ?",
    			new Object[] {theaterId}); 
    	OneCatalog newCatalog = ChangeMapListToList(rows, ListType.movieList);
    	// add to buffer
    	bufferTheaterIdList.add(newCatalog);
    	return newCatalog.getList();
	}
	
	public List<Integer> findTheaterIdList(Integer movieId) {
		
		// find in buffer first
		for (int i = 0; i < bufferMovieIdList.size(); i++) {
			OneCatalog temp = bufferMovieIdList.get(i);
			if (movieId == temp.getId()) {
				return temp.getList();
			}
		}
		

		// find in database
		List<Integer> l = new ArrayList<Integer>();
    	List<Map<String, Object>> rows = jdbcTemplate.queryForList(
    			"SELECT * FROM moviejava.moviecatalog where movieId = ?",
    			new Object[] {movieId}); 
    	OneCatalog newCatalog = ChangeMapListToList(rows, ListType.theaterList);
    	// add to buffer
    	bufferMovieIdList.add(newCatalog);
    	return newCatalog.getList();
	}
	
    private OneCatalog ChangeMapListToList(List rows, ListType lt) {
    	OneCatalog newCatalog = new OneCatalog(); 
    	Iterator it = rows.iterator(); 
    	while(it.hasNext()) { 
    	    Map catalogMap = (Map) it.next(); 
    	    
    	    // for safe
    	    Integer newThId = new Integer(-1);
    	    Integer newMovieId = new Integer(-1);
    	    try {
    	    	newThId = Integer.parseInt(catalogMap.get("theaterId").toString());
    	    	newMovieId = Integer.parseInt(catalogMap.get("movieId").toString());
    	    } catch(Exception e) {
    	    	e.printStackTrace();
    	    }
    	    
    	    // full the temp catalog
    	    if (lt == ListType.movieList) {
    	    	newCatalog.setId(newThId);
    	    	newCatalog.list.add(newMovieId);
    	    }
    	    if (lt == ListType.theaterList) {
    	    	newCatalog.setId(newMovieId);
    	    	newCatalog.list.add(newThId);
    	    }
    	}
    	
    	return newCatalog;
    }
}
