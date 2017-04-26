package tableModel;

public class SalesOrderDetail implements java.io.Serializable {
	
	private Integer salesOrderDetailId;
	private String salesOrderId;
	private String productId;
	private Integer amounts;
	
	
	/**
	 * 
	 */
	public SalesOrderDetail() {
	}

	/**
	 * @param salesOrderDetailId
	 * @param salesOrderId
	 * @param productId
	 * @param amounts
	 */
	public SalesOrderDetail(String salesOrderId,
			String productId, Integer amounts) {
		this.salesOrderId = salesOrderId;
		this.productId = productId;
		this.amounts = amounts;
	}

	/**
	 * @return the salesOrderDetailId
	 */
	public Integer getSalesOrderDetailId() {
		return salesOrderDetailId;
	}

	/**
	 * @param salesOrderDetailId the salesOrderDetailId to set
	 */
	public void setSalesOrderDetailId(Integer salesOrderDetailId) {
		this.salesOrderDetailId = salesOrderDetailId;
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
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the amounts
	 */
	public Integer getAmounts() {
		return amounts;
	}

	/**
	 * @param amounts the amounts to set
	 */
	public void setAmounts(Integer amounts) {
		this.amounts = amounts;
	}
	
}
