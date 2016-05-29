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

import java.util.ArrayList;
import java.util.List;


public class Movie {

    private Integer id = null;
    private String movieName = null;
    private List<String> movieActors = new ArrayList<String>();
    
    public Movie() {
        super();
    }


    public Integer getId() {
        return this.id;
    }
    public void setId(final Integer id) {
        this.id = id;
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
}
