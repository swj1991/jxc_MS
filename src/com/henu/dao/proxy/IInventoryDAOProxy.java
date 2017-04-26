package com.henu.dao.proxy;

import java.util.List;

import tableModel.Inventory;




import com.henu.dao.IInventoryDAO;

import com.henu.dao.impl.IInventoryDAOImpl;
import com.henu.dbc.DataBaseConnection;

public class IInventoryDAOProxy implements IInventoryDAO{
	private DataBaseConnection dbc = null;
	private IInventoryDAO dao = null;

	public IInventoryDAOProxy() {
		this.dbc = new DataBaseConnection();
		this.dao = new IInventoryDAOImpl(this.dbc.getConnection());
	}


	public boolean doCreate(Inventory Inventory) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doCreate(Inventory);
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


	public boolean doUpdate(Inventory inventory) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doUpdate(inventory);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public List<List> findAll(String keyWord) throws Exception {
		List<List> all = null;
		try {
			all = this.dao.findAll(keyWord);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

	public Inventory findById(String id) throws Exception {
		Inventory inventory = null;
		try {
			inventory = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return inventory;
	}

}
