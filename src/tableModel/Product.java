package tableModel;

public class Product implements java.io.Serializable {
	
	private String productId;
	private String productName;
	private String productUnit;
	private double productInPrice;
	private double productOutPrice;
	private int productAmount;
	private String productRemark;

	/**
	 * 
	 */
	public Product() {
		
	}
	
	/**
	 * @param productId
	 * @param productName
	 */
	public Product(String productId, String productName) {
		this.productId = productId;
		this.productName = productName;
	}


	/**
	 * @return the productAmount
	 */
	public int getProductAmount() {
		return productAmount;
	}

	/**
	 * @param productAmount the productAmount to set
	 */
	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	/**
	 * @param productId
	 * @param productName
	 * @param productUnit
	 * @param productInPrice
	 * @param productOutPrice
	 * @param productAmount
	 * @param productRemark
	 */
	public Product(String productId, String productName, String productUnit,
			double productInPrice, double productOutPrice, int productAmount,
			String productRemark) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productUnit = productUnit;
		this.productInPrice = productInPrice;
		this.productOutPrice = productOutPrice;
		this.productAmount = productAmount;
		this.productRemark = productRemark;
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
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productUnit
	 */
	public String getProductUnit() {
		return productUnit;
	}

	/**
	 * @param productUnit the productUnit to set
	 */
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	/**
	 * @return the productInPrice
	 */
	public double getProductInPrice() {
		return productInPrice;
	}

	/**
	 * @param productInPrice the productInPrice to set
	 */
	public void setProductInPrice(double productInPrice) {
		this.productInPrice = productInPrice;
	}

	/**
	 * @return the productOutPrice
	 */
	public double getProductOutPrice() {
		return productOutPrice;
	}

	/**
	 * @param productOutPrice the productOutPrice to set
	 */
	public void setProductOutPrice(double productOutPrice) {
		this.productOutPrice = productOutPrice;
	}

	/**
	 * @return the productRemark
	 */
	public String getProductRemark() {
		return productRemark;
	}

	/**
	 * @param productRemark the productRemark to set
	 */
	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark;
	}
	
	

}
