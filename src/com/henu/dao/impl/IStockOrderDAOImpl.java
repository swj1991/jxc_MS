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

import javax.swing.JOptionPane;

import com.henu.dao.IStockOrderDAO;
import tableModel.StockOrder;
import tableModel.StockOrderDetail;
/**
 * @author WKQ
 *
 */
public class IStockOrderDAOImpl implements IStockOrderDAO {
	private Connection conn = null;

	public IStockOrderDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	public boolean doCreate(StockOrder stockOrder) throws Exception {
		boolean flag = false;
		boolean flag_1 = false;
		boolean result = false;
		PreparedStatement pstmt = null;	
		String sql = "INSERT INTO stockOrder(stockOrderId,stockOrderDate," +
				"userId,supplierId,totalPay) VALUES (?,?,?,?,?) ";
		
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, stockOrder.getStockOrderId());
			pstmt.setDate(2, new java.sql.Date(stockOrder.getStockOrderDate().getTime()));
			pstmt.setString(3, stockOrder.getUserId());
			pstmt.setString(4, stockOrder.getSupplierId());
			pstmt.setDouble(5, stockOrder.getAmountPay());
			if (pstmt.executeUpdate() > 0) {// 至少已经更新了一行
				flag = true;
			}
			
			Set<StockOrderDetail> stockOrderDetail = stockOrder.getStockOrderDetail();
			Iterator<StockOrderDetail> iter = stockOrderDetail.iterator();
			
			while ( iter.hasNext()) {
				StockOrderDetail details = iter.next();
				try {
					String sql_detail = "INSERT INTO StockOrderDetail(stockOrderId,productId,amounts) VALUES(?,?,?)";
					PreparedStatement pstmt_1 = null;	
					pstmt_1 = this.conn.prepareStatement(sql_detail);
					pstmt_1.setString(1, stockOrder.getStockOrderId());
					pstmt_1.setString(2, details.getProductId());
					pstmt_1.setInt(3, details.getAmounts());

					if (pstmt_1.executeUpdate() > 0) {// 至少已经更新了一行
						flag_1 = true;
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}		
			}	
			
			result = flag && flag_1;
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
		String sql = "SELECT stockOrderId,stockOrderDate,supplierName,productName," +
				"amount,userName FROM user,supplier,product,stockOrderDetail,stockOrder WHERE" +
				" user.userId = stockOrder.userId AND supplier.supplierId = stockOrder.supplierId AND" +
				" product.productId = stockOrderDetail.productId AND stockOrderDetail.stockOrderId = " +
				"stockOrder.stockOrderId";
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

	public StockOrder findById(String id) throws Exception {
		StockOrder stockOrder = new StockOrder();
		Set<StockOrderDetail> stockOrderDetail = stockOrder.getStockOrderDetail();
		PreparedStatement pstmt = null;
		
		String sql = "SELECT stockOrderId,stockOrderDate," +
				"userId,supplierId,totalPay FROM stockOrder WHERE stockOrderId=?";
		try {			
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			while(rs.next()){
				stockOrder.setStockOrderId(rs.getString(1));
				stockOrder.setStockOrderDate(rs.getDate(2));
				stockOrder.setUserId(rs.getString(3));
				stockOrder.setSupplierId(rs.getString(4));
				stockOrder.setAmountPay(rs.getDouble(5));
			}
			
			//获取结果集
			StockOrderDetail stockDetail = new StockOrderDetail();
			String sql_detail = "SELECT stockOrderId,productId,amounts FROM StockOrderDetail WHERE stockOrderId=?";
			
			PreparedStatement pstmt_1 = null;
			pstmt_1 = this.conn.prepareStatement(sql_detail);
			pstmt_1.setString(1, stockOrder.getStockOrderId());
			ResultSet rs_1 = pstmt_1.executeQuery();
			
			while(rs_1.next()) {
				stockDetail.setStockOrderId(rs_1.getString(1));
				stockDetail.setProductId(rs_1.getString(2));
				stockDetail.setAmounts(rs_1.getInt(3));
				
				stockOrderDetail.add(stockDetail);
			}  
			stockOrder.setStockOrderDetail(stockOrderDetail);
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
		return stockOrder;
	}
	
	public List<List> findBySupplier(String supplier) throws Exception {
		List<List> all = new ArrayList<List>();
		PreparedStatement pstmt = null;
		String sql = "SELECT stockOrderId,stockOrderDate,supplierName,userName," +
				"totalPay FROM stockSelect_view WHERE supplierName like ?";
		try {			
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + supplier + "%");
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
