/**
 * 
 */
package com.henu.dao.proxy;

import java.util.List;

import com.henu.dao.impl.ISalesOrderDAOImpl;
import com.henu.dbc.DataBaseConnection;
import com.henu.dao.ISalesOrderDAO;
import tableModel.SalesOrder;

/**
 * @author WKQ
 *
 */
public class ISalesOrderDAOProxy implements ISalesOrderDAO {
	private DataBaseConnection dbc = null;
	private ISalesOrderDAO dao = null;
	
	public ISalesOrderDAOProxy() {
		this.dbc = new DataBaseConnection();
		this.dao = new ISalesOrderDAOImpl(this.dbc.getConnection());
	}
	
	public boolean doCreate(SalesOrder salesOrder) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doCreate(salesOrder);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}
	
	public List<List> findAll() throws Exception {
		List<List> all = null;
		try {
			all = this.dao.findAll();
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}
	
	public SalesOrder findById(String id) throws Exception {
		SalesOrder salesOrder = null;
		try {
			salesOrder = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return salesOrder;
	}
	
	public List<List> findByCustomer(String customer) throws Exception {
		List<List> all = null;
		try {
			all = this.dao.findByCustomer(customer);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}
	
	public List<List> salesRanking(String userName) throws Exception {
		List<List> all = null;
		try {
			all = this.dao.salesRanking(userName);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}
	
}
