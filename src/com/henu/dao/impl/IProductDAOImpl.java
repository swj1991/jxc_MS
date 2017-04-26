package com.henu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tableModel.Product;

import com.henu.dao.IProductDAO;

public class IProductDAOImpl implements IProductDAO {
	private Connection conn = null;

	public IProductDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean doCreate(Product product) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO Product(ProductId,ProductName,productUnit,productInPrice,productOutPrice,productAmount,productRemark" +
				") VALUES (?,?,?,?,?,?,?) ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, product.getProductId()); // 所有的内容从Product类中取出
			pstmt.setString(2, product.getProductName()); // 所有的内容从Product类中取出
			pstmt.setString(3, product.getProductUnit());
			pstmt.setDouble(4,product.getProductInPrice());
			pstmt.setDouble(5, product.getProductOutPrice());
			pstmt.setInt(6, product.getProductAmount());
			pstmt.setString(7, product.getProductRemark());
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
		String sql = "DELETE FROM Product WHERE Productid=? ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id); // 所有的内容从Product类中取出
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

	public boolean doUpdate(Product product) throws Exception {
		boolean flag = false;
		PreparedStatement pstmt = null;
		String sql = "UPDATE Product SET ProductName=?,productUnit=?,productInPrice=?,productOutPrice=?,productAmount=?,productRemark=?" +
				"WHERE ProductId=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			 // 所有的内容从Product类中取出
			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getProductUnit());
			pstmt.setDouble(3, product.getProductInPrice());
			pstmt.setDouble(4, product.getProductOutPrice());
			pstmt.setInt(5, product.getProductAmount());
			pstmt.setString(6, product.getProductRemark());
			pstmt.setString(7, product.getProductId()); 
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

	public List<Product> findAll(String keyWord) throws Exception {
		List<Product> all = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM Product WHERE ProductName LIKE ? ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyWord + "%");
			
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getString(1));
				product.setProductName(rs.getString(2));
				product.setProductUnit(rs.getString(3));
				product.setProductInPrice(rs.getDouble(4));
				product.setProductOutPrice(rs.getDouble(5));
				product.setProductAmount(rs.getInt(6));
				product.setProductRemark(rs.getString(7));
				
				all.add(product); // 所有的内容向集合中插入
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
	

	public Product findById(String id) throws Exception {
		Product product = new Product();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM Product WHERE Productid=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			if (rs.next()) {
				product.setProductId(rs.getString(1));
				product.setProductName(rs.getString(2));
				product.setProductUnit(rs.getString(3));
				product.setProductInPrice(rs.getDouble(4));
                product.setProductOutPrice(rs.getDouble(5));
                product.setProductAmount(rs.getInt(6));
				product.setProductRemark(rs.getString(7));
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
		return product;
	}

}

