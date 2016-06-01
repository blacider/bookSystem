package thymeleafexamples.stsm.business.entities;

public class Theater {

	private Integer id = null;
	
	// 基础信息
	private String theaterName = null;
	private String theaterLocation = null;
	private String theaterPhone = null;
	private String theaterComment = null;
	private String theaterCity = null;
	
	public String MyToString() {
		return id.toString() + " " + 
				theaterName + " " + 
				theaterLocation + " " +
				theaterPhone + " " + 
				theaterComment + " " 
				+ theaterCity;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the theaterName
	 */
	public String getTheaterName() {
		return theaterName;
	}
	/**
	 * @param theaterName the theaterName to set
	 */
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	/**
	 * @return the theaterLocation
	 */
	public String getTheaterLocation() {
		return theaterLocation;
	}
	/**
	 * @param theaterLocation the theaterLocation to set
	 */
	public void setTheaterLocation(String theaterLocation) {
		this.theaterLocation = theaterLocation;
	}
	/**
	 * @return the theaterPhone
	 */
	public String getTheaterPhone() {
		return theaterPhone;
	}
	/**
	 * @param theaterPhone the theaterPhone to set
	 */
	public void setTheaterPhone(String theaterPhone) {
		this.theaterPhone = theaterPhone;
	}
	/**
	 * @return the theaterComment
	 */
	public String getTheaterComment() {
		return theaterComment;
	}
	/**
	 * @param theaterComment the theaterComment to set
	 */
	public void setTheaterComment(String theaterComment) {
		this.theaterComment = theaterComment;
	}
	/**
	 * @return the theaterCity
	 */
	public String getTheaterCity() {
		return theaterCity;
	}
	/**
	 * @param theaterCity the theaterCity to set
	 */
	public void setTheaterCity(String theaterCity) {
		this.theaterCity = theaterCity;
	}
}
