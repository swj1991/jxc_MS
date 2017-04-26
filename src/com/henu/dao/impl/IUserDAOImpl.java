/**
 * 
 */
package com.henu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.henu.dao.IUserDAO;
import tableModel.User;
/**
 * @author WKQ
 *
 */
public class IUserDAOImpl implements IUserDAO {
	private Connection conn = null;

	public IUserDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean doCreate(User user) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO user(UserId,UserName,UserPassword,UserClassId," +
				"UserAddress,UserPhoneNumber,UserCardNumber,UserRemark) VALUES (?,?,?,?,?,?,?,?) ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getUserPassword());
			pstmt.setString(4, user.getUserClassId());
			pstmt.setString(5, user.getUserAddress());
			pstmt.setString(6, user.getUserPhoneNumber());
			pstmt.setString(7, user.getUserCardNumber());
			pstmt.setString(8, user.getUserRemark());
			
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
		String sql = "DELETE FROM user WHERE UserId=? ";
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

	public boolean doUpdate(User user) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "UPDATE user SET UserName=?,UserPassword=?,UserClassId=?," +
				"UserPhoneNumber=?,UserAddress=?,UserCardNumber=?,UserRemark=? WHERE UserId=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserClassId());
			pstmt.setString(5, user.getUserPhoneNumber());
			pstmt.setString(4, user.getUserAddress());
			pstmt.setString(6, user.getUserCardNumber());
			pstmt.setString(7, user.getUserRemark());
			pstmt.setString(8, user.getUserId());
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
	
	public List<User> findAll() throws Exception {
		List<User> all = new ArrayList<User>();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM user";
		try {
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));	
				user.setUserClassId(rs.getString(4));
				user.setUserAddress(rs.getString(5));
				user.setUserPhoneNumber(rs.getString(6));
				user.setUserCardNumber(rs.getString(7));
				user.setUserRemark(rs.getString(8));
				all.add(user); // 所有的内容向集合中插入
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

	public List<User> findAll(String keyWord) throws Exception {
		List<User> all = new ArrayList<User>();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM user WHERE UserName LIKE ?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyWord + "%");
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));	
				user.setUserClassId(rs.getString(4));
				user.setUserAddress(rs.getString(5));
				user.setUserPhoneNumber(rs.getString(6));
				user.setUserCardNumber(rs.getString(7));
				user.setUserRemark(rs.getString(8));
				all.add(user); // 所有的内容向集合中插入
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
	
	
	

	public User findById(String id) throws Exception {
		User user = new User();
		PreparedStatement pstmt = null;
		String sql = "SELECT UserId,UserName,UserPassword,UserClassId," +
				"UserPhoneNumber,UserAddress,UserCardNumber,UserRemark FROM user WHERE UserId=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getString(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));	
				user.setUserClassId(rs.getString(4));
				user.setUserAddress(rs.getString(5));
				user.setUserPhoneNumber(rs.getString(6));
				user.setUserCardNumber(rs.getString(7));
				user.setUserRemark(rs.getString(8));
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
		return user;
	}
	
	public User getUser(String id,String password) throws Exception {
		User user = new User();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM user WHERE UserId=? AND UserPassword=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getString(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));				
				user.setUserClassId(rs.getString(4));
				user.setUserAddress(rs.getString(5));
				user.setUserPhoneNumber(rs.getString(6));
				user.setUserCardNumber(rs.getString(7));
				user.setUserRemark(rs.getString(8));
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
		return user;
	}


}
