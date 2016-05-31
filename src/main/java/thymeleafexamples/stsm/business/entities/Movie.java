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
    
    private List<String> movieActors = new ArrayList<String>();
    private String movieSummary = null;
    private List<String> movieType = new ArrayList<String>();
    private String movieBelongCountry = null;
    private Integer movieFilmLength = null;
    private java.util.Date moviePublishTime = new java.util.Date();
    private List<Integer> thearterList = new ArrayList<Integer>();
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
    
    public List<String> getMovieActors() {
    	return this.movieActors;
    }
    
    public void setMovieActors(final List<String> movieActors) {
    	this.movieActors = movieActors;
    }


	public String getMovieSummary() {
		return movieSummary;
	}


	public void setMovieSummary(String summary) {
		this.movieSummary = summary;
	}


	public List<String> getMovieType() {
		return movieType;
	}


	public void setMovieType(List<String> movieType) {
		this.movieType = movieType;
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

}
