package tableModel;

public class Customer implements java.io.Serializable {
	
	private String customerId;
	private String customerName;
	private String customerAddress;
	private String customerPostCode;
	private String customerEmail;
	private String customerPhoneNumber;
	private String customerBankId;
	private String customerBankAddress;
	private String customerWebsite;
	private String customerRemark;
	
	/**
	 * 
	 */
	public Customer() {
		
	}

	/**
	 * @param customerId
	 * @param customerName
	 */
	public Customer(String customerId, String customerName) {
		this.customerId = customerId;
		this.customerName = customerName;
	}

	/**
	 * @param customerId
	 * @param customerName
	 * @param customerAddress
	 * @param customerPostCode
	 * @param customerPhoneNumber
	 * @param customerBankId
	 * @param customerBankAddress
	 * @param customerWebsite
	 * @param customerRemark
	 */
	public Customer(String customerId, String customerName,
			String customerAddress, String customerPostCode,
			String customerEmail,
			String customerPhoneNumber, String customerBankId, 
			String customerBankAddress, String customerWebsite, 
			String customerRemark) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerPostCode = customerPostCode;
		this.customerEmail=customerEmail;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerBankId = customerBankId;
		this.customerBankAddress = customerBankAddress;
		this.customerWebsite = customerWebsite;
		this.customerRemark = customerRemark;
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
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the customerAdddress
	 */
	public String getCustomerAddress() {
		return customerAddress;
	}

	/**
	 * @param customerAdddress the customerAdddress to set
	 */
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	/**
	 * @return the customerPostCode
	 */
	public String getCustomerPostCode() {
		return customerPostCode;
	}

	/**
	 * @param customerPostCode the customerPostCode to set
	 */
	public void setCustomerPostCode(String customerPostCode) {
		this.customerPostCode = customerPostCode;
	}

	
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	
	public void setCustomerEmail(String customerEmail){
		this.customerEmail=customerEmail;
	}
	/**
	 * @return the customerPhoneNumber
	 */
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	/**
	 * @param customerPhoneNumber the customerPhoneNumber to set
	 */
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	/**
	 * @return the customerBankId
	 */
	public String getCustomerBankId() {
		return customerBankId;
	}

	/**
	 * @param customerBankId the customerBankId to set
	 */
	public void setCustomerBankId(String customerBankId) {
		this.customerBankId = customerBankId;
	}

	/**
	 * @return the customerBankAddress
	 */
	public String getCustomerBankAddress() {
		return customerBankAddress;
	}

	/**
	 * @param customerBankAddress the customerBankAddress to set
	 */
	public void setCustomerBankAddress(String customerBankAddress) {
		this.customerBankAddress = customerBankAddress;
	}

	/**
	 * @return the customerWebsite
	 */
	public String getCustomerWebsite() {
		return customerWebsite;
	}

	/**
	 * @param customerWebsite the customerWebsite to set
	 */
	public void setCustomerWebsite(String customerWebsite) {
		this.customerWebsite = customerWebsite;
	}

	/**
	 * @return the customerRemark
	 */
	public String getCustomerRemark() {
		return customerRemark;
	}

	/**
	 * @param customerRemark the customerRemark to set
	 */
	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
	}
	
}
