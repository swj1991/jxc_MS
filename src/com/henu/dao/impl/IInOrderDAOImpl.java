package com.henu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tableModel.InOrder;

import com.henu.dao.IInOrderDAO;

public class IInOrderDAOImpl implements IInOrderDAO {
	private Connection conn = null;

	public IInOrderDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean doCreate(InOrder inOrder) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO InOrder(InOrderId,stockOrderID,PayStatus) VALUES (?,?,?) ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, inOrder.getInOrderID()); // ���е����ݴ�InOrder����ȡ��
			pstmt.setString(2, inOrder.getStockOrderID());
			pstmt.setBoolean(3, inOrder.isPayStatus());
			
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

//	public boolean doDelete(String id) throws Exception {
//		boolean flag = false;
//		PreparedStatement pstmt = null;
//		String sql = "DELETE FROM InOrder WHERE InOrderid=? ";
//		try {
//			pstmt = this.conn.prepareStatement(sql);
//			pstmt.setString(1, id); // ���е����ݴ�InOrder����ȡ��
//			if (pstmt.executeUpdate() > 0) {// �����Ѿ�������һ��
//				flag = true;
//			}
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
//		return flag;
//	}

	public boolean doUpdate(InOrder inOrder) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "UPDATE InOrder SET storageID=?,inDate=?,userID=?,PayStatus=?,inOrderRemark=?" +
				"WHERE InOrderid=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			 // ���е����ݴ�InOrder����ȡ��
			pstmt.setString(1, inOrder.getStorageID()); // ���е����ݴ�InOrder����ȡ��
			pstmt.setDate(2, new java.sql.Date(inOrder.getInDate().getTime()));
			pstmt.setString(3, inOrder.getUserID());
			pstmt.setBoolean(4, inOrder.isPayStatus());
			pstmt.setString(5, inOrder.getInOrderRemark());
			pstmt.setString(6, inOrder.getInOrderID());
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

	public List<InOrder> findAll(String keyWord) throws Exception {
		List<InOrder> all = new ArrayList<InOrder>();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM InOrder WHERE InOrderId LIKE ? ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyWord + "%");
			
			ResultSet rs = pstmt.executeQuery(); // ִ�в�ѯ����
			while (rs.next()) {
				InOrder inOrder = new InOrder();
				inOrder.setInOrderID(rs.getString(1));
				inOrder.setStorageID(rs.getString(2));
				inOrder.setInDate(rs.getDate(3));
				inOrder.setUserID(rs.getString(4));
				inOrder.setStockOrderID(rs.getString(5));
				inOrder.setPayStatus(rs.getBoolean(6));
				inOrder.setInOrderRemark(rs.getString(7));
				
				all.add(inOrder); // ���е������򼯺��в���
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

	public InOrder findById(String id) throws Exception {
		InOrder inOrder = new InOrder();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM InOrder WHERE InOrderid=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); // ִ�в�ѯ����
			if (rs.next()) {
				inOrder.setInOrderID(rs.getString(1));
				inOrder.setStorageID(rs.getString(2));
				inOrder.setInDate(rs.getDate(3));
				inOrder.setUserID(rs.getString(4));
                inOrder.setStockOrderID(rs.getString(5));
				inOrder.setPayStatus(rs.getBoolean(6));
				inOrder.setInOrderRemark(rs.getString(7));
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
		return inOrder;
	}

}

