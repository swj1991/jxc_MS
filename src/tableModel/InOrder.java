package tableModel;

import java.util.Date;

public class InOrder implements java.io.Serializable {
	
	private String inOrderID;
	private String storageID;
	private Date inDate;
	private String userID;
	private String stockOrderID;
	private boolean isPayStatus;
	private String inOrderRemark;
	
	/**
	 * @param inOrderID
	 * @param storageID
	 * @param inDate
	 * @param userID
	 * @param stockOrderID
	 * @param isPayStatus
	 * @param inOrderRemark
	 */
	public InOrder(String inOrderID, String storageID, Date inDate,
			String userID, String stockOrderID, boolean isPayStatus,
			String inOrderRemark) {
		super();
		this.inOrderID = inOrderID;
		this.storageID = storageID;
		this.inDate = inDate;
		this.userID = userID;
		this.stockOrderID = stockOrderID;
		this.isPayStatus = isPayStatus;
		this.inOrderRemark = inOrderRemark;
	}

	public InOrder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the inOrderID
	 */
	public String getInOrderID() {
		return inOrderID;
	}

	/**
	 * @param inOrderID the inOrderID to set
	 */
	public void setInOrderID(String inOrderID) {
		this.inOrderID = inOrderID;
	}

	/**
	 * @return the storageID
	 */
	public String getStorageID() {
		return storageID;
	}

	/**
	 * @param storageID the storageID to set
	 */
	public void setStorageID(String storageID) {
		this.storageID = storageID;
	}

	/**
	 * @return the inDate
	 */
	public Date getInDate() {
		return inDate;
	}

	/**
	 * @param inDate the inDate to set
	 */
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the stockOrderID
	 */
	public String getStockOrderID() {
		return stockOrderID;
	}

	/**
	 * @param stockOrderID the stockOrderID to set
	 */
	public void setStockOrderID(String stockOrderID) {
		this.stockOrderID = stockOrderID;
	}

	/**
	 * @return the isPayStatus
	 */
	public boolean isPayStatus() {
		return isPayStatus;
	}

	/**
	 * @param isPayStatus the isPayStatus to set
	 */
	public void setPayStatus(boolean isPayStatus) {
		this.isPayStatus = isPayStatus;
	}

	/**
	 * @return the inOrderRemark
	 */
	public String getInOrderRemark() {
		return inOrderRemark;
	}

	/**
	 * @param inOrderRemark the inOrderRemark to set
	 */
	public void setInOrderRemark(String inOrderRemark) {
		this.inOrderRemark = inOrderRemark;
	} 

}
