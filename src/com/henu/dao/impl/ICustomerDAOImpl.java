package com.henu.dao.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import tableModel.Customer;


import com.henu.dao.ICustomerDAO;

public class ICustomerDAOImpl implements ICustomerDAO {
	private Connection conn = null;

	public ICustomerDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean doCreate(Customer customer) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO customer(customerId,customerName,customerAddress,customerPostCode,customerEmail,customerPhoneNumber" +
				",customerBankId,customerBankAddress,customerWebsite,customerRemark) VALUES (?,?,?,?,?,?,?,?,?,?) ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, customer.getCustomerId()); // ���е����ݴ�customer����ȡ��
			pstmt.setString(2, customer.getCustomerName()); // ���е����ݴ�customer����ȡ��
			pstmt.setString(3, customer.getCustomerAddress());
			pstmt.setString(4, customer.getCustomerPostCode());
			pstmt.setString(5, customer.getCustomerEmail());
			pstmt.setString(6, customer.getCustomerPhoneNumber());
			pstmt.setString(7, customer.getCustomerBankId());
			pstmt.setString(8, customer.getCustomerBankAddress());
			pstmt.setString(9, customer.getCustomerWebsite());
			pstmt.setString(10, customer.getCustomerRemark());
			if (pstmt.executeUpdate() > 0) {// �����Ѿ�������һ��
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally { // ��������׳������տ϶���Ҫ�������ݿ�Ĺرղ�����
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return flag;
	}
	
	public boolean doDelete(String id) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM customer WHERE customerid=? ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id); // ���е����ݴ�customer����ȡ��
			if (pstmt.executeUpdate() > 0) {// �����Ѿ�������һ��
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally { // ��������׳������տ϶���Ҫ�������ݿ�Ĺرղ�����
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return flag;
	}

	public boolean doUpdate(Customer customer) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "UPDATE customer SET customername=?,customerAddress=?,customerPostCode=?,customerEmail=?,customerPhoneNumber=?,customerBankId=?" +
				",customerBankAddress=?,customerWebsite=?,customerRemark=? WHERE customerid=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			 // ���е����ݴ�customer����ȡ��
			pstmt.setString(1, customer.getCustomerName()); // ���е����ݴ�customer����ȡ��
			pstmt.setString(2, customer.getCustomerAddress());
			pstmt.setString(3, customer.getCustomerPostCode());
			pstmt.setString(4, customer.getCustomerEmail());
			pstmt.setString(5, customer.getCustomerPhoneNumber());
			pstmt.setString(6, customer.getCustomerBankId());
			pstmt.setString(7, customer.getCustomerBankAddress());
			pstmt.setString(8, customer.getCustomerWebsite());
			pstmt.setString(9, customer.getCustomerRemark());
			pstmt.setString(10, customer.getCustomerId());
			if (pstmt.executeUpdate() > 0) {// �����Ѿ�������һ��
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally { // ��������׳������տ϶���Ҫ�������ݿ�Ĺرղ�����
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return flag;
	}

	public List<Customer> findAll(String keyWord) throws Exception {
		List<Customer> all = new ArrayList<Customer>();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM customer WHERE customerName LIKE ? order by customerID";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyWord + "%");
			
			ResultSet rs = pstmt.executeQuery(); // ִ�в�ѯ����
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getString(1));
				customer.setCustomerName(rs.getString(2));
				customer.setCustomerAddress(rs.getString(3));
				customer.setCustomerPostCode(rs.getString(4));
				customer.setCustomerEmail(rs.getString(5));
				customer.setCustomerPhoneNumber(rs.getString(6));
				customer.setCustomerBankId(rs.getString(7));
				customer.setCustomerBankAddress(rs.getString(8));
				customer.setCustomerWebsite(rs.getString(9));
				customer.setCustomerRemark(rs.getString(10));
				
				all.add(customer); // ���е������򼯺��в���
			}
			rs.close();
		} catch (Exception e) {
			throw e;
		} finally { // ��������׳������տ϶���Ҫ�������ݿ�Ĺرղ�����
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return all;
	}
//	public List<String> findByName(String keyword) throws Exception{
//		List<String> all = new ArrayList<String>();
//		PreparedStatement pstmt = null;
//		String sql = "SELECT customerName FROM customer WHERE customerName LIKE ? ";
//		try {
//			pstmt = this.conn.prepareStatement(sql);
//			pstmt.setString(1, "%" + keyword + "%");
//			
//			ResultSet rs = pstmt.executeQuery(); // ִ�в�ѯ����
//			while (rs.next()) {
//				all.add(rs.getString(1)); // ���е������򼯺��в���
//			}
//			rs.close();
//		} catch (Exception e) {
//			throw e;
//		} finally { // ��������׳������տ϶���Ҫ�������ݿ�Ĺرղ�����
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (Exception e1) {
//
//				}
//			}
//		}
//		return all;
//	}


	public Customer findById(String id) throws Exception {
		Customer customer = new Customer();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM customer WHERE customerid=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); // ִ�в�ѯ����
			if (rs.next()) {
				customer.setCustomerId(rs.getString(1));
				customer.setCustomerName(rs.getString(2));
				customer.setCustomerAddress(rs.getString(3));
				customer.setCustomerPostCode(rs.getString(4));
                customer.setCustomerEmail(rs.getString(5));
				customer.setCustomerPhoneNumber(rs.getString(6));
				customer.setCustomerBankId(rs.getString(7));
				customer.setCustomerBankAddress(rs.getString(8));
				customer.setCustomerWebsite(rs.getString(9));
				customer.setCustomerRemark(rs.getString(10));
				
			}
			rs.close();
		} catch (Exception e) {
			throw e;
		} finally { // ��������׳������տ϶���Ҫ�������ݿ�Ĺرղ�����
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return customer;
	}

}
