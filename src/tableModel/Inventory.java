package tableModel;

import java.util.Date;

public class Inventory implements java.io.Serializable {
	
	private String inventoryId;
	private Date inventoryDate;
	private String storageId;
	private String productId;
	private Integer amounts;
	
	/**
	 * @param inventoryId
	 * @param inventoryDate
	 * @param storageId
	 * @param productId
	 * @param amounts
	 */
	public Inventory(String inventoryId, Date inventoryDate, String storageId,
			String productId, Integer amounts) {
		super();
		this.inventoryId = inventoryId;
		this.inventoryDate = inventoryDate;
		this.storageId = storageId;
		this.productId = productId;
		this.amounts = amounts;
	}

	public Inventory() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the inventoryId
	 */
	public String getInventoryId() {
		return inventoryId;
	}

	/**
	 * @param inventoryId the inventoryId to set
	 */
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	/**
	 * @return the inventoryDate
	 */
	public Date getInventoryDate() {
		return inventoryDate;
	}

	/**
	 * @param inventoryDate the inventoryDate to set
	 */
	public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}

	/**
	 * @return the storageId
	 */
	public String getStorageId() {
		return storageId;
	}

	/**
	 * @param storageId the storageId to set
	 */
	public void setStorageId(String storageId) {
		this.storageId = storageId;
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
