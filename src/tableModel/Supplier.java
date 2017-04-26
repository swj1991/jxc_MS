package tableModel;

public class Supplier implements java.io.Serializable {
	
	private String supplierId;
	private String supplierName;
	private String supplierAddress;
	private String supplierPostCode;
	private String supplierEmail;
	private String supplierPhoneNumber;
	private String supplierBankId;
	private String supplierBankAddress;
	private String supplierWebsite;
	private String supplierRemark;
	
	/**
	 * 
	 */
	public Supplier() {
		
	}

	/**
	 * @param supplierId
	 * @param supplierName
	 */
	public Supplier(String supplierId, String supplierName) {
		this.supplierId = supplierId;
		this.supplierName = supplierName;
	}

	/**
	 * @param supplierId
	 * @param supplierName
	 * @param supplierAddress
	 * @param supplierPostCode
	 * @param supplierEmail
	 * @param supplierPhoneNumber
	 * @param supplierBankId
	 * @param supplierBankAddress
	 * @param supplierWebsite
	 * @param supplierRemark
	 */
	public Supplier(String supplierId, String supplierName,
			String supplierAddress, String supplierPostCode,
			String supplierEmail, String supplierPhoneNumber, 
			String supplierBankId, String supplierBankAddress, 
			String supplierWebsite, String supplierRemark) {
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierAddress = supplierAddress;
		this.supplierPostCode = supplierPostCode;
		this.supplierEmail = supplierEmail;
		this.supplierPhoneNumber = supplierPhoneNumber;
		this.supplierBankId = supplierBankId;
		this.supplierBankAddress = supplierBankAddress;
		this.supplierWebsite = supplierWebsite;
		this.supplierRemark = supplierRemark;
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
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * @return the supplierAdddress
	 */
	public String getSupplierAddress() {
		return supplierAddress;
	}

	/**
	 * @param supplierAdddress the supplierAdddress to set
	 */
	public void setSupplierAddress(String supplierAdddress) {
		this.supplierAddress = supplierAdddress;
	}

	/**
	 * @return the supplierPostCode
	 */
	public String getSupplierPostCode() {
		return supplierPostCode;
	}

	/**
	 * @param supplierPostCode the supplierPostCode to set
	 */
	public void setSupplierPostCode(String supplierPostCode) {
		this.supplierPostCode = supplierPostCode;
	}

	/**
	 * @return the supplierEmail
	 */
	public String getSupplierEmail() {
		return supplierEmail;
	}

	/**
	 * @param supplierEmail the supplierEmail to set
	 */
	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	/**
	 * @return the supplierPhoneNumber
	 */
	public String getSupplierPhoneNumber() {
		return supplierPhoneNumber;
	}

	/**
	 * @param supplierPhoneNumber the supplierPhoneNumber to set
	 */
	public void setSupplierPhoneNumber(String supplierPhoneNumber) {
		this.supplierPhoneNumber = supplierPhoneNumber;
	}

	/**
	 * @return the supplierBankId
	 */
	public String getSupplierBankId() {
		return supplierBankId;
	}

	/**
	 * @param supplierBankId the supplierBankId to set
	 */
	public void setSupplierBankId(String supplierBankId) {
		this.supplierBankId = supplierBankId;
	}

	/**
	 * @return the supplierBankAddress
	 */
	public String getSupplierBankAddress() {
		return supplierBankAddress;
	}

	/**
	 * @param supplierBankAddress the supplierBankAddress to set
	 */
	public void setSupplierBankAddress(String supplierBankAddress) {
		this.supplierBankAddress = supplierBankAddress;
	}

	/**
	 * @return the supplierWebsite
	 */
	public String getSupplierWebsite() {
		return supplierWebsite;
	}

	/**
	 * @param supplierWebsite the supplierWebsite to set
	 */
	public void setSupplierWebsite(String supplierWebsite) {
		this.supplierWebsite = supplierWebsite;
	}

	/**
	 * @return the supplierRemark
	 */
	public String getSupplierRemark() {
		return supplierRemark;
	}

	/**
	 * @param supplierRemark the supplierRemark to set
	 */
	public void setSupplierRemark(String supplierRemark) {
		this.supplierRemark = supplierRemark;
	}

}
