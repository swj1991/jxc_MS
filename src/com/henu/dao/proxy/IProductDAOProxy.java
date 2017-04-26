package com.henu.dao.proxy;

import java.util.List;

import tableModel.Product;

import com.henu.dao.IProductDAO;
import com.henu.dao.impl.IProductDAOImpl;
import com.henu.dbc.DataBaseConnection;

public class IProductDAOProxy implements IProductDAO{
	private DataBaseConnection dbc = null;
	private IProductDAO dao = null;

	public IProductDAOProxy() {
		this.dbc = new DataBaseConnection();
		this.dao = new IProductDAOImpl(this.dbc.getConnection());
	}


	public boolean doCreate(Product product) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doCreate(product);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}


	public boolean doDelete(String id) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doDelete(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}


	public boolean doUpdate(Product product) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doUpdate(product);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public List<Product> findAll(String keyWord) throws Exception {
		List<Product> all = null;
		try {
			all = this.dao.findAll(keyWord);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

	public Product findById(String id) throws Exception {
		Product product = null;
		try {
			product = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return product;
	}

}
