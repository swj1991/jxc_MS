/**
 * 
 */
package com.henu.dao.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.henu.dao.ISupplierDAO;
import tableModel.Supplier;

/**
 * @author WKQ
 *
 */
public class ISupplierDAOImpl implements ISupplierDAO {
	private Connection conn = null;

	public ISupplierDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean doCreate(Supplier supplier) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO Supplier(supplierId,supplierName,supplierAddress," +
				"supplierPostCode,supplierPhoneNumber,supplierEmail,supplierBankId," +
				"supplierBankAddress,supplierWebsite,supplierRemark) VALUES (?,?,?,?,?,?,?,?,?,?) ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, supplier.getSupplierId());
			pstmt.setString(2, supplier.getSupplierName());
			pstmt.setString(3, supplier.getSupplierAddress());
			pstmt.setString(4, supplier.getSupplierPostCode());
			pstmt.setString(5, supplier.getSupplierPhoneNumber());
			pstmt.setString(6, supplier.getSupplierEmail());
			pstmt.setString(7, supplier.getSupplierBankId());
			pstmt.setString(8, supplier.getSupplierBankAddress());
			pstmt.setString(9, supplier.getSupplierWebsite());
			pstmt.setString(10, supplier.getSupplierRemark());
			
			if (pstmt.executeUpdate() > 0) {// 至少已经更新了一行
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally { // 不管如何抛出，最终肯定是要进行数据库的关闭操作的
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
		String sql = "DELETE FROM Supplier WHERE SupplierId=? ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			if (pstmt.executeUpdate() > 0) {// 至少已经更新了一行
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally { // 不管如何抛出，最终肯定是要进行数据库的关闭操作的
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return flag;
	}

	public boolean doUpdate(Supplier supplier) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "UPDATE supplier SET supplierName=?,supplierAddress=?," +
				"supplierPostCode=?,supplierPhoneNumber=?,supplierEmail=?,supplierBankId=?," +
				"supplierBankAddress=?,supplierWebsite=?,supplierRemark=? WHERE supplierId=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, supplier.getSupplierName());
			pstmt.setString(2, supplier.getSupplierAddress());
			pstmt.setString(3, supplier.getSupplierPostCode());
			pstmt.setString(4, supplier.getSupplierPhoneNumber());
			pstmt.setString(5, supplier.getSupplierEmail());
			pstmt.setString(6, supplier.getSupplierBankId());
			pstmt.setString(7, supplier.getSupplierBankAddress());
			pstmt.setString(8, supplier.getSupplierWebsite());
			pstmt.setString(9, supplier.getSupplierRemark());
			pstmt.setString(10, supplier.getSupplierId());
			if (pstmt.executeUpdate() > 0) {// 至少已经更新了一行
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		} finally { // 不管如何抛出，最终肯定是要进行数据库的关闭操作的
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return flag;
	}

	public List<Supplier> findAll(String keyWord) throws Exception {
		List<Supplier> all = new ArrayList<Supplier>();
		PreparedStatement pstmt = null;
		String sql = "SELECT supplierId,supplierName,supplierAddress," +
				"supplierPostCode,supplierPhoneNumber,supplierEmail,supplierBankId," +
				"supplierBankAddress,supplierWebsite,supplierRemark FROM supplier WHERE supplierName LIKE ?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyWord + "%");
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setSupplierId(rs.getString(1));
				supplier.setSupplierName(rs.getString(2));
				supplier.setSupplierAddress(rs.getString(3));
				supplier.setSupplierPostCode(rs.getString(4));
				supplier.setSupplierPhoneNumber(rs.getString(5));
				supplier.setSupplierEmail(rs.getString(6));
				supplier.setSupplierBankId(rs.getString(7));
				supplier.setSupplierBankAddress(rs.getString(8));
				supplier.setSupplierWebsite(rs.getString(9));
				supplier.setSupplierRemark(rs.getString(10));
				
				all.add(supplier); // 所有的内容向集合中插入
			}
			rs.close();
		} catch (Exception e) {
			throw e;
		} finally { // 不管如何抛出，最终肯定是要进行数据库的关闭操作的
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return all;
	}
	
	public Supplier findById(String id) throws Exception {
		Supplier supplier = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT supplierId,supplierName,supplierAddress," +
				"supplierPostCode,supplierPhoneNumber,supplierEmail,supplierBankId," +
				"supplierBankAddress,supplierWebsite,supplierRemark FROM supplier WHERE supplierId=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			if (rs.next()) {
				supplier = new Supplier();
				supplier.setSupplierId(rs.getString(1));
				supplier.setSupplierName(rs.getString(2));
				supplier.setSupplierAddress(rs.getString(3));
				supplier.setSupplierPostCode(rs.getString(4));
				supplier.setSupplierPhoneNumber(rs.getString(5));
				supplier.setSupplierEmail(rs.getString(6));
				supplier.setSupplierBankId(rs.getString(7));
				supplier.setSupplierBankAddress(rs.getString(8));
				supplier.setSupplierWebsite(rs.getString(9));
				supplier.setSupplierRemark(rs.getString(10));
			}
			rs.close();
		} catch (Exception e) {
			throw e;
		} finally { // 不管如何抛出，最终肯定是要进行数据库的关闭操作的
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return supplier;
	}

}
