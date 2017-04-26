package tableModel;

public class User implements java.io.Serializable {
	
	private String userId;
	private String userName;
	private String userPassword;
	private String userClassId;
	private String userPhoneNumber;
	private String userAddress;
	private String userCardNumber;
	private String userRemark;
	
	/**
	 * 
	 */
	public User() {
		
	}

	/**
	 * @param userID
	 * @param userName
	 */
	public User(String userId, String userName, 
			String userPassword) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	/**
	 * @param userID
	 * @param userName
	 * @param userClassID
	 * @param userPhoneNumber
	 * @param userAddress
	 * @param userCardNumber
	 * @param userRemark
	 */
	public User(String userId, String userName, String userPassword, 
			String userClassId, String userPhoneNumber, 
			String userAddress, String userCardNumber, String userRemark) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userClassId = userClassId;
		this.userPhoneNumber = userPhoneNumber;
		this.userAddress = userAddress;
		this.userCardNumber = userCardNumber;
		this.userRemark = userRemark;
	}

	/**
	 * @return the userID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the userClassID
	 */
	public String getUserClassId() {
		return userClassId;
	}

	/**
	 * @param userClassID the userClassID to set
	 */
	public void setUserClassId(String userClassId) {
		this.userClassId = userClassId;
	}

	/**
	 * @return the userPhoneNumber
	 */
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	/**
	 * @param userPhoneNumber the userPhoneNumber to set
	 */
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	/**
	 * @return the userAddress
	 */
	public String getUserAddress() {
		return userAddress;
	}

	/**
	 * @param userAddress the userAddress to set
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	/**
	 * @return the userCardNumber
	 */
	public String getUserCardNumber() {
		return userCardNumber;
	}

	/**
	 * @param userCardNumber the userCardNumber to set
	 */
	public void setUserCardNumber(String userCardNumber) {
		this.userCardNumber = userCardNumber;
	}

	/**
	 * @return the userRemark
	 */
	public String getUserRemark() {
		return userRemark;
	}

	/**
	 * @param userRemark the userRemark to set
	 */
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

}
