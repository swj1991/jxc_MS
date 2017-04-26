package tableModel;

public class StockOrderDetail implements java.io.Serializable {
	
	private Integer stockOrderDetailId;
	private String stockOrderId;
	private String productId;
	private Integer amounts;
	
	
	/**
	 * 
	 */
	public StockOrderDetail() {
	}

	/**
	 * @param stockOrderDetailId
	 * @param stockOrderId
	 * @param productId
	 * @param amount
	 */
	public StockOrderDetail(String stockOrderId,
			String productId, Integer amounts) {
		this.stockOrderId = stockOrderId;
		this.productId = productId;
		this.amounts = amounts;
	}

	/**
	 * @return the stockOrderDetailId
	 */
	public Integer getStockOrderDetailId() {
		return stockOrderDetailId;
	}

	/**
	 * @param stockOrderDetailId the stockOrderDetailId to set
	 */
	public void setStockOrderDetailId(Integer stockOrderDetailId) {
		this.stockOrderDetailId = stockOrderDetailId;
	}

	/**
	 * @return the stockOrderId
	 */
	public String getStockOrderId() {
		return stockOrderId;
	}

	/**
	 * @param stockOrderId the stockOrderId to set
	 */
	public void setStockOrderId(String stockOrderId) {
		this.stockOrderId = stockOrderId;
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
	 * @return the amount
	 */
	public Integer getAmounts() {
		return amounts;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmounts(Integer amounts) {
		this.amounts = amounts;
	}

}
