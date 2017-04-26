package tableModel;

public class Storage implements java.io.Serializable {
	
	private String storageId;
	private String storageName;
	private String storageAddress;
	
	
	
	/**
	 * 
	 */
	public Storage() {

	}

	/**
	 * @param storageId
	 * @param storageName
	 * @param sotrageAddress
	 */
	public Storage(String storageId, String storageName, String storageAddress) {
		super();
		this.storageId = storageId;
		this.storageName = storageName;
		this.storageAddress = storageAddress;
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
	 * @return the storageName
	 */
	public String getStorageName() {
		return storageName;
	}

	/**
	 * @param storageName the storageName to set
	 */
	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	/**
	 * @return the sotrageAddress
	 */
	public String getStorageAddress() {
		return storageAddress;
	}

	/**
	 * @param sotrageAddress the sotrageAddress to set
	 */
	public void setStorageAddress(String storageAddress) {
		this.storageAddress = storageAddress;
	}
	
}
