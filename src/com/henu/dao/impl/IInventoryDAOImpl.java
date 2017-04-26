package com.henu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.henu.dao.IInventoryDAO;

import tableModel.Inventory;



public class IInventoryDAOImpl implements IInventoryDAO {
	private Connection conn = null;

	public IInventoryDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean doCreate(Inventory inventory) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO Inventory(InventoryId,inventoryDate,storageId,productId,amounts" +
				") VALUES (?,?,?,?,?) ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, inventory.getInventoryId()); // 所有的内容从Inventory类中取出
			pstmt.setDate(2, new java.sql.Date(inventory.getInventoryDate().getTime())); // 所有的内容从Inventory类中取出
			pstmt.setString(3, inventory.getStorageId());
			pstmt.setString(4, inventory.getProductId());
			pstmt.setInt(5, inventory.getAmounts());
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
		String sql = "DELETE FROM Inventory WHERE Inventoryid=? ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id); // 所有的内容从Inventory类中取出
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

	public boolean doUpdate(Inventory inventory) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "UPDATE Inventory SET inventoryDate=?,storageId=?,productId=?,amounts=? WHERE Inventoryid=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			 // 所有的内容从Inventory类中取出
			pstmt.setDate(1, new java.sql.Date(inventory.getInventoryDate().getTime())); // 所有的内容从Inventory类中取出
			pstmt.setString(2, inventory.getStorageId());
			pstmt.setString(3, inventory.getProductId());
			pstmt.setInt(4, inventory.getAmounts());
			pstmt.setString(5, inventory.getInventoryId());
			
			
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

	public List<List> findAll(String keyWord) throws Exception {
		List<List> all = new ArrayList<List>();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM inventory_view WHERE productName LIKE ? ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyWord + "%");
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
				all.add(row); // 所有的内容向集合中插入
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

	@SuppressWarnings("null")
	public Inventory findById(String id) throws Exception {
		Inventory inventory = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM Inventory WHERE Inventoryid=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			if (rs.next()) {
				inventory.setInventoryId(rs.getString(1));
				inventory.setInventoryDate(rs.getDate(2));
				inventory.setStorageId(rs.getString(3));
				inventory.setProductId(rs.getString(4));
				inventory.setAmounts(rs.getInt(5));
				
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
		return inventory;
	}

}
