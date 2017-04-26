package com.henu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tableModel.OutOrder;

import com.henu.dao.IOutOrderDAO;

public class IOutOrderDAOImpl implements IOutOrderDAO {
	private Connection conn = null;

	public IOutOrderDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean doCreate(OutOrder OutOrder) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO OutOrder(OutOrderId,salesOrderID,PayStatus) VALUES (?,?,?) ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, OutOrder.getOutOrderID()); // 所有的内容从OutOrder类中取出
			pstmt.setString(2, OutOrder.getSalesOrderID());
			pstmt.setBoolean(3, OutOrder.isPayStatus());
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

//	public boolean doDelete(String id) throws Exception {
//		boolean flag = false;
//		PreparedStatement pstmt = null;
//		String sql = "DELETE FROM OutOrder WHERE OutOrderid=? ";
//		try {
//			pstmt = this.conn.prepareStatement(sql);
//			pstmt.setString(1, id); // 所有的内容从OutOrder类中取出
//			if (pstmt.executeUpdate() > 0) {// 至少已经更新了一行
//				flag = true;
//			}
//		} catch (Exception e) {
//			throw e;
//		} finally { // 不管如何抛出，最终肯定是要进行数据库的关闭操作的
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (Exception e1) {
//
//				}
//			}
//		}
//		return flag;
//	}

	public boolean doUpdate(OutOrder outOrder) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "UPDATE OutOrder SET storageID=?,OutDate=?,userID=?,PayStatus=?,OutOrderRemark=?" +
				"WHERE OutOrderid=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			 // 所有的内容从OutOrder类中取出
			pstmt.setString(1, outOrder.getStorageID()); // 所有的内容从InOrder类中取出
			pstmt.setDate(2, new java.sql.Date(outOrder.getOutDate().getTime()));
			pstmt.setString(3, outOrder.getUserID());
			pstmt.setBoolean(4, outOrder.isPayStatus());
			pstmt.setString(5, outOrder.getOutOrderRemark());
			pstmt.setString(6, outOrder.getOutOrderID());
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

	public List<OutOrder> findAll(String keyWord) throws Exception {
		List<OutOrder> all = new ArrayList<OutOrder>();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM OutOrder WHERE OutOrderId LIKE ? ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyWord + "%");
			
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			while (rs.next()) {
				OutOrder outOrder = new OutOrder();
				outOrder.setOutOrderID(rs.getString(1));
				outOrder.setStorageID(rs.getString(2));
				outOrder.setOutDate(rs.getDate(3));
				outOrder.setUserID(rs.getString(4));
				outOrder.setSalesOrderID(rs.getString(5));
				outOrder.setPayStatus(rs.getBoolean(6));
				outOrder.setOutOrderRemark(rs.getString(7));
				
				all.add(outOrder); // 所有的内容向集合中插入
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

	public OutOrder findById(String id) throws Exception {
		OutOrder outOrder = new OutOrder();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM OutOrder WHERE OutOrderid=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			if (rs.next()) {
				outOrder.setOutOrderID(rs.getString(1));
				outOrder.setStorageID(rs.getString(2));
				outOrder.setOutDate(rs.getDate(3));
				outOrder.setUserID(rs.getString(4));
                outOrder.setSalesOrderID(rs.getString(5));
				outOrder.setPayStatus(rs.getBoolean(6));
				outOrder.setOutOrderRemark(rs.getString(7));
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
		return outOrder;
	}

}

