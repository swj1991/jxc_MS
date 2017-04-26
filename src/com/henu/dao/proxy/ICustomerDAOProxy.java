package com.henu.dao.proxy;

import java.util.List;

import tableModel.Customer;



import com.henu.dao.ICustomerDAO;
import com.henu.dao.impl.ICustomerDAOImpl;
import com.henu.dbc.DataBaseConnection;

public class ICustomerDAOProxy implements ICustomerDAO{
	private DataBaseConnection dbc = null;
	private ICustomerDAO dao = null;

	public ICustomerDAOProxy() {
		this.dbc = new DataBaseConnection();
		this.dao = new ICustomerDAOImpl(this.dbc.getConnection());
	}


	public boolean doCreate(Customer customer) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doCreate(customer);
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


	public boolean doUpdate(Customer customer) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doUpdate(customer);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public List<Customer> findAll(String keyWord) throws Exception {
		List<Customer> all = null;
		try {
			all = this.dao.findAll(keyWord);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

	public Customer findById(String id) throws Exception {
		Customer customer = null;
		try {
			customer = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return customer;
	}

}
