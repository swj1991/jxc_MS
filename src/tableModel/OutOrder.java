package tableModel;

import java.util.Date;

public class OutOrder implements java.io.Serializable {
	
	private String outOrderID;
	private String storageID;
	private Date outDate;
	private String userID;
	private String salesOrderID;
	private boolean isPayStatus;
	private String outOrderRemark;
	
	/**
	 * @param outOrderID
	 * @param storageID
	 * @param outDate
	 * @param userID
	 * @param salesOrderID
	 * @param isPayStatus
	 * @param outOrderRemark
	 */
	public OutOrder(String outOrderID, String storageID, Date outDate,
			String userID, String salesOrderID, boolean isPayStatus,
			String outOrderRemark) {
		this.outOrderID = outOrderID;
		this.storageID = storageID;
		this.outDate = outDate;
		this.userID = userID;
		this.salesOrderID = salesOrderID;
		this.isPayStatus = isPayStatus;
		this.outOrderRemark = outOrderRemark;
	}

	public OutOrder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the outOrderID
	 */
	public String getOutOrderID() {
		return outOrderID;
	}

	/**
	 * @param outOrderID the outOrderID to set
	 */
	public void setOutOrderID(String outOrderID) {
		this.outOrderID = outOrderID;
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
	 * @return the outDate
	 */
	public Date getOutDate() {
		return outDate;
	}

	/**
	 * @param outDate the outDate to set
	 */
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
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
	 * @return the salesOrderID
	 */
	public String getSalesOrderID() {
		return salesOrderID;
	}

	/**
	 * @param salesOrderID the salesOrderID to set
	 */
	public void setSalesOrderID(String salesOrderID) {
		this.salesOrderID = salesOrderID;
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
	 * @return the outOrderRemark
	 */
	public String getOutOrderRemark() {
		return outOrderRemark;
	}

	/**
	 * @param outOrderRemark the outOrderRemark to set
	 */
	public void setOutOrderRemark(String outOrderRemark) {
		this.outOrderRemark = outOrderRemark;
	}
	
}
