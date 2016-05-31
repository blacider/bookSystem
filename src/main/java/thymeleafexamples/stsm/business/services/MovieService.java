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
import java.util.Date;

// abandon user Repository, save the message in user service
// also do the database operation here

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;
import thymeleafexamples.stsm.business.entities.User;
import thymeleafexamples.stsm.business.entities.Movie;

public class MovieService {
    
    //@Autowired
    //private UserRepository userRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//storage All Movies
	private List<Movie> movieList = new ArrayList<Movie>();
    
    public MovieService() {
    	movieList = new ArrayList<Movie>();
    }
    
    public List<Movie> getAllMovie() {
    	movieList.clear();
    	List<Map<String, Object>> moviesInformation = jdbcTemplate.queryForList("SELECT * From movies");
    	for (int i = 0; i < moviesInformation.size(); i++) {
    		Movie newMovie = new Movie();
    		newMovie = LoadMovelModelBySQLMap(moviesInformation.get(i));
    		movieList.add(newMovie);
    	}
		return movieList;
    }
    
    public Movie LoadMovelModelBySQLMap(Map<String, Object> MapModel) {
    	Movie newMovie = new Movie();
		newMovie.setMovieId((Integer)MapModel.get("movieId"));
		newMovie.setMovieName(MapModel.get("movieName").toString());
		newMovie.setMovieSummary(MapModel.get("movieSummary").toString());
		newMovie.setMovieActors(MapModel.get("movieActors").toString());
		newMovie.setMovieTypes(MapModel.get("movieType").toString());
		newMovie.setMovieFilmLength((Integer)MapModel.get("movieFilmLength"));
		newMovie.setMovieOnShow(((Integer) MapModel.get("movieOnShow") == 1));
		newMovie.setMoviePublishTime((Date) MapModel.get("moviePublishTime"));
		newMovie.setMovieDirector(MapModel.get("movieDirector").toString());
		newMovie.setMovieBelongCountry(MapModel.get("movieBelongCountry").toString());
		return newMovie;
    }

	public Movie getMovieByMovieId(String movieId) {
		// TODO Auto-generated method stub
		for (int i = 0; i < movieList.size(); i++) {
			if (movieList.get(i).getMovieId().toString().equals(movieId)) {
				return movieList.get(i);
			}
		}
		return null;
	}
}
