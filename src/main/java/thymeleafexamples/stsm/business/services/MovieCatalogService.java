package thymeleafexamples.stsm.business.services;

import java.util.ArrayList;
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
	
	private enum IdType {
		movie, theater
	};
	
	// to represent the record
	private class IdAndType {
		Integer idNum;
		IdType idType;
	}
	
	// storage all living users
	private List<Map<IdAndType, List<Integer>>> bufferMovieCatalogList = new ArrayList<Map<IdAndType, List<Integer>>>();
	
	public MovieCatalogService() {
		super();
	}
	
	public List<Integer> findMoviesList(Integer theaterId) {
		
		List<Integer> l = new ArrayList<Integer>();
		
		// find in database
    	List<Map<String, Object>> rows = jdbcTemplate.queryForList(
    			"SELECT * FROM moviejava.moviecatalog where movieId = ?",
    			new Object[] {theaterId}); 
		
		return l;
	}
}
