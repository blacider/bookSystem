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

    private Integer id = null;
    private String movieName = null;
    
    // 详细信息
    private List<String> movieActors = new ArrayList<String>();
    // 电影简介
    private String summary = null;
    // 电影类型，如动作，战争，奇幻
    private List<String> movieType = new ArrayList<String>();
    // 电影国家，用字符串记录，可用枚举简化数据
    private String belongCountry = null;
    // 电影时长，单位 min
    private Integer filmLength = null;
    // 首映时间
    private java.util.Date publishTime = new java.util.Date();
    
    // 显示有权播放该电影的电影场次列表
    // 仅仅记录有版权的影院，而影院是否有提供该场次的电影不可知
    private List<Integer> thearterList = new ArrayList<Integer>();
    
    
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


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}


	public List<String> getMovieType() {
		return movieType;
	}


	public void setMovieType(List<String> movieType) {
		this.movieType = movieType;
	}


	public String getBelongCountry() {
		return belongCountry;
	}


	public void setBelongCountry(String belongCountry) {
		this.belongCountry = belongCountry;
	}


	public Integer getFilmLength() {
		return filmLength;
	}


	public void setFilmLength(Integer filmLength) {
		this.filmLength = filmLength;
	}


	public java.util.Date getPublishTime() {
		return publishTime;
	}


	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}


	public List<Integer> getThearterList() {
		return thearterList;
	}


	public void setThearterList(List<Integer> thearterList) {
		this.thearterList = thearterList;
	}

}
