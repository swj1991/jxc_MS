/**
 * 
 */
package com.henu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.henu.dao.IStorageDAO;
import tableModel.Storage;
/**
 * @author WKQ
 *
 */
public class IStorageDAOImpl implements IStorageDAO {
	private Connection conn = null;

	public IStorageDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean doCreate(Storage storage) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO storage(storageId,storageName,storageAddress) VALUES (?,?,?) ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, storage.getStorageId());
			pstmt.setString(2, storage.getStorageName());
			pstmt.setString(3, storage.getStorageAddress());
			
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
		String sql = "DELETE FROM storage WHERE StorageId=? ";
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

	public boolean doUpdate(Storage storage) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "UPDATE storage SET storageName=?,storageAddress=? WHERE storageId=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			
			pstmt.setString(1, storage.getStorageName());
			pstmt.setString(2, storage.getStorageAddress());
			pstmt.setString(3, storage.getStorageId());
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

	public List<Storage> findAll(String keyWord) throws Exception {
		List<Storage> all = new ArrayList<Storage>();
		PreparedStatement pstmt = null;
		String sql = "SELECT storageId,storageName,storageAddress FROM storage WHERE storageAddress LIKE ?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyWord + "%");
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			while (rs.next()) {
				Storage storage = new Storage();
				storage.setStorageId(rs.getString(1));
				storage.setStorageName(rs.getString(2));
				storage.setStorageAddress(rs.getString(3));
				all.add(storage); // 所有的内容向集合中插入
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

	public Storage findById(String id) throws Exception {
		Storage storage = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT storageId,storageName,storageAddress FROM storage WHERE storageId=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			if (rs.next()) {
				storage = new Storage();
				storage.setStorageId(rs.getString(1));
				storage.setStorageName(rs.getString(2));
				storage.setStorageAddress(rs.getString(3));
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
		return storage;
	}

}
