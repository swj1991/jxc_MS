package tableModel;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class StockOrder implements java.io.Serializable {
	
	private String stockOrderId;
	private Date stockOrderDate;
	private String userId;
	private String supplierId;
	private double amountPay;
	private Set<StockOrderDetail> stockOrderDetail = new HashSet<StockOrderDetail>(0);
	
	/**
	 * 
	 */
	public StockOrder() {

	}

	/**
	 * @param stockOrderId
	 * @param stockOrderDate
	 * @param userId
	 * @param supplierId
	 * @param amountPay
	 */
	public StockOrder(String stockOrderId, Date stockOrderDate, String userId,
			String supplierId, double amountPay) {
		this.stockOrderId = stockOrderId;
		this.stockOrderDate = stockOrderDate;
		this.userId = userId;
		this.supplierId = supplierId;
		this.amountPay = amountPay;
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
	 * @return the stockOrderInfo
	 */
	public String getStockOrderId() {
		return stockOrderId;
	}

	/**
	 * @param stockOrderInfo the stockOrderInfo to set
	 */
	public void setStockOrderId(String stockOrderId) {
		this.stockOrderId = stockOrderId;
	}

	/**
	 * @return the stockOrderDate
	 */
	public Date getStockOrderDate() {
		return stockOrderDate;
	}

	/**
	 * @param stockOrderDate the stockOrderDate to set
	 */
	public void setStockOrderDate(Date stockOrderDate) {
		this.stockOrderDate = stockOrderDate;
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
	 * @return the supplierId
	 */
	public String getSupplierId() {
		return supplierId;
	}

	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * @return the stockOrderDetail
	 */
	public Set<StockOrderDetail> getStockOrderDetail() {
		return stockOrderDetail;
	}

	/**
	 * @param stockOrderDetail the stockOrderDetail to set
	 */
	public void setStockOrderDetail(Set<StockOrderDetail> stockOrderDetail) {
		stockOrderDetail = stockOrderDetail;
	}
	
}
