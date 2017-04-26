/**
 * 
 */
package com.henu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.henu.dao.ISalesOrderDAO;
import tableModel.SalesOrder;
import tableModel.SalesOrderDetail;
import tableModel.StockOrder;
import tableModel.StockOrderDetail;

/**
 * @author WKQ
 *
 */
public class ISalesOrderDAOImpl implements ISalesOrderDAO {
	private Connection conn = null;

	public ISalesOrderDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	public boolean doCreate(SalesOrder salesOrder) throws Exception {
		boolean flag = false;
		boolean flag_1 = false;
		boolean result = false;
		PreparedStatement pstmt = null;	
		String sql = "INSERT INTO salesOrder(salesOrderId,salesOrderDate,userId,customerId,totalPay) VALUES (?,?,?,?,?) ";
		
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, salesOrder.getSalesOrderId());
			pstmt.setDate(2, new java.sql.Date (salesOrder.getsalesOrderDate().getTime()));
			pstmt.setString(3, salesOrder.getUserId());
			pstmt.setString(4, salesOrder.getCustomerId());
			pstmt.setDouble(5, salesOrder.getAmountPay());
			
			if (pstmt.executeUpdate() > 0) {// 至少已经更新了一行
				flag = true;
			}
			
			Set<SalesOrderDetail> salesOrderDetail = salesOrder.getSalesOrderDetail();
			Iterator<SalesOrderDetail> iter = salesOrderDetail.iterator();
			while ( iter.hasNext()) {
				SalesOrderDetail details = iter.next();
				try {
					String sql_detail = "INSERT INTO SalesOrderDetail(salesOrderId,productId,amounts) VALUES(?,?,?)";
					PreparedStatement pstmt_1 = null;	
					pstmt_1 = this.conn.prepareStatement(sql_detail);
					pstmt_1.setString(1, salesOrder.getSalesOrderId());
					pstmt_1.setString(2, details.getProductId());
					pstmt_1.setInt(3, details.getAmounts());

					if (pstmt_1.executeUpdate() > 0) {// 至少已经更新了一行
						flag_1 = true;
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return flag && flag_1;
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
		return result;
	}

	public List<List> findAll() throws Exception {
		List<List> all = new ArrayList<List>();
		PreparedStatement pstmt = null;
		String sql = "SELECT salesOrderId,salesOrderDate,CustomerName,productName," +
				"amount,userName FROM user,customer,product,salesOrderDetail,salesOrder WHERE" +
				" user.userId = salesOrder.userId AND customer.customerId = salesOrder.customerId AND" +
				" product.productId = salesOrderDetail.productId AND salesOrderDetail.salesOrderId = " +
				"salesOrder.salesOrderId";
		try {
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			
			while (rs.next()) {
				List<String> row = new ArrayList<String>();
				for (int i = 1; i <= colCount; i++) {
					String str = rs.getString(i);
					if (str != null && !str.isEmpty())
						str = str.trim();
					row.add(str);
				}
				all.add(row);
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

	public SalesOrder findById(String id) throws Exception {
		SalesOrder salesOrder = new SalesOrder();
		Set<SalesOrderDetail> salesOrderDetail = salesOrder.getSalesOrderDetail();
		PreparedStatement pstmt = null;
		
		String sql = "SELECT salesOrderId,salesOrderDate," +
			"userId,customerId,totalPay FROM salesOrder WHERE salesOrderId=?";
		try {			
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			
			while(rs.next()){
				salesOrder.setSalesOrderId(rs.getString(1));
				salesOrder.setSalesOrderDate(rs.getDate(2));
				salesOrder.setUserId(rs.getString(3));
				salesOrder.setCustomerId(rs.getString(4));
				salesOrder.setAmountPay(rs.getDouble(5));
			}
			
			//获取结果集
			SalesOrderDetail salesDetail = new SalesOrderDetail();
			String sql_detail = "SELECT salesOrderId,productId,amounts FROM SalesOrderDetail WHERE salesOrderId=?";
			
			PreparedStatement pstmt_1 = null;
			pstmt_1 = this.conn.prepareStatement(sql_detail);
			pstmt_1.setString(1, salesOrder.getSalesOrderId());
			ResultSet rs_1 = pstmt_1.executeQuery();
			
			while(rs_1.next()) {
				salesDetail.setSalesOrderId(rs_1.getString(1));
				salesDetail.setProductId(rs_1.getString(2));
				salesDetail.setAmounts(rs_1.getInt(3));
				
				salesOrderDetail.add(salesDetail);
			}  
			salesOrder.setSalesOrderDetail(salesOrderDetail);
			   
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
		return salesOrder;
	}
	
	public List<List> findByCustomer(String customer) throws Exception {
		List<List> all = new ArrayList<List>();
		PreparedStatement pstmt = null;
		String sql = "SELECT salesOrderId,salesOrderDate,CustomerName,userName," +
				"totalPay FROM salesSelect_view WHERE customerName like ?";
		try {			
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + customer + "%");
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			
			while (rs.next()) {
				List<String> row = new ArrayList<String>();
				for (int i = 1; i <= colCount; i++) {
					String str = rs.getString(i);
					if (str != null && !str.isEmpty())
						str = str.trim();
					row.add(str);
				}
				all.add(row);
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
	
	public List<List> salesRanking(String userName) throws Exception {
		List<List> all = new ArrayList<List>();
		PreparedStatement pstmt = null;
		String sql = "SELECT userId,userName,total,amounts FROM salesRanking_view WHERE userName like ?";
		try {			
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + userName + "%");
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			
			while (rs.next()) {
				List<String> row = new ArrayList<String>();
				for (int i = 1; i <= colCount; i++) {
					String str = rs.getString(i);
					if (str != null && !str.isEmpty())
						str = str.trim();
					row.add(str);
				}
				all.add(row);
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

}
