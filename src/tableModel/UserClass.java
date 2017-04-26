package tableModel;

public class UserClass implements java.io.Serializable {
	
	private String userClassId;
	private String userClassName;
	
	/**
	 * @param userClassId
	 * @param userClassName
	 */
	public UserClass(String userClassId, String userClassName) {
		super();
		this.userClassId = userClassId;
		this.userClassName = userClassName;
	}

	/**
	 * @return the userClassId
	 */
	public String getUserClassId() {
		return userClassId;
	}

	/**
	 * @param userClassId the userClassId to set
	 */
	public void setUserClassId(String userClassId) {
		this.userClassId = userClassId;
	}

	/**
	 * @return the userClassName
	 */
	public String getUserClassName() {
		return userClassName;
	}

	/**
	 * @param userClassName the userClassName to set
	 */
	public void setUserClassName(String userClassName) {
		this.userClassName = userClassName;
	}
	
}
