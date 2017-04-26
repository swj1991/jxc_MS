package tableModel;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SalesOrder implements java.io.Serializable {
	
	private String salesOrderId;
	private Date salesOrderDate;
	private String userId;
	private String customerId;
	private double amountPay;
	private Set<SalesOrderDetail> salesOrderDetail= new HashSet<SalesOrderDetail>(0);
	
	

	/**
	 * 
	 */
	public SalesOrder() {
	}

	/**
	 * @param salesOrderId
	 * @param salesOrderDate
	 * @param userId
	 * @param customerId
	 * @param amountPay
	 */
	public SalesOrder(String salesOrderId, Date salesOrderDate, String userId,
			String customerId, double amountPay) {
		this.salesOrderId = salesOrderId;
		this.salesOrderDate = salesOrderDate;
		this.userId = userId;
		this.customerId = customerId;
		this.amountPay = amountPay;
	}

	/**
	 * @return the salesOrderId
	 */
	public String getSalesOrderId() {
		return salesOrderId;
	}

	/**
	 * @param salesOrderId the salesOrderId to set
	 */
	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	/**
	 * @return the salesOrderDate
	 */
	public Date getsalesOrderDate() {
		return salesOrderDate;
	}

	/**
	 * @param salesOrderDate the salesOrderDate to set
	 */
	public void setsalesOrderDate(Date salesOrderDate) {
		this.salesOrderDate = salesOrderDate;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	
	/**
	 * @return the salesOrderDate
	 */
	public Date getSalesOrderDate() {
		return salesOrderDate;
	}

	/**
	 * @param salesOrderDate the salesOrderDate to set
	 */
	public void setSalesOrderDate(Date salesOrderDate) {
		this.salesOrderDate = salesOrderDate;
	}

	/**
	 * @return the amountPay
	 */
	public double getAmountPay() {
		return amountPay;
	}

	/**
	 * @param amountPay the amountPay to set
	 */
	public void setAmountPay(double amountPay) {
		this.amountPay = amountPay;
	}

	/**
	 * @return the salesOrderDetail
	 */
	public Set<SalesOrderDetail> getSalesOrderDetail() {
		return salesOrderDetail;
	}

	/**
	 * @param salesOrderDetail the salesOrderDetail to set
	 */
	public void setSalesOrderDetail(Set<SalesOrderDetail> salesOrderDetail) {
		this.salesOrderDetail = salesOrderDetail;
	}

}
