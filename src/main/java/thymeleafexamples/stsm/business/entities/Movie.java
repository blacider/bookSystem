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
package thymeleafexamples.stsm.business.entities;

import java.util.*;


public class Movie {
    private Integer movieId = null;
    private String movieName = null;
    
    private String movieActors = null;
    private String movieSummary = null;
    private String movieDirector = null;
    private String movieTypes = null;
    private String movieBelongCountry = null;
    private Integer movieFilmLength = null;
    private java.util.Date moviePublishTime = new java.util.Date();
    private List<Integer> thearterList = new ArrayList<Integer>();
    private boolean movieOnShow = null != null;
    public Movie() {
        super();
    }


    public Integer getMovieId() {
        return this.movieId;
    }
    public void setMovieId(final Integer id) {
        this.movieId = id;
    }
    
    public String getMovieName() {
        return this.movieName;
    }
    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }
    
    public String getMovieActors() {
    	return this.movieActors;
    }
    
    public void setMovieActors(final String movieActors) {
    	this.movieActors = movieActors;
    }


	public String getMovieSummary() {
		return movieSummary;
	}


	public void setMovieSummary(String summary) {
		this.movieSummary = summary;
	}


	public String getMovieTypes() {
		return movieTypes;
	}


	public void setMovieTypes(String movieType) {
		this.movieTypes = movieType;
	}


	public String getMovieBelongCountry() {
		return movieBelongCountry;
	}


	public void setMovieBelongCountry(String belongCountry) {
		this.movieBelongCountry = belongCountry;
	}


	public Integer getMovieFilmLength() {
		return movieFilmLength;
	}


	public void setMovieFilmLength(Integer filmLength) {
		this.movieFilmLength = filmLength;
	}


	public java.util.Date getMoviePublishTime() {
		return moviePublishTime;
	}


	public void setMoviePublishTime(java.util.Date publishTime) {
		this.moviePublishTime = publishTime;
	}


	public List<Integer> getThearterList() {
		return thearterList;
	}


	public void setThearterList(List<Integer> thearterList) {
		this.thearterList = thearterList;
	}
	
	public void setMovieOnShow(boolean onshow) {
		this.movieOnShow = onshow;
	}
	
	public boolean getMovieOnShow() {
		return this.movieOnShow;
	}


	public String getMovieDirector() {
		return movieDirector;
	}


	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}
}
