/**
 * 
 */
package com.henu.dao.proxy;

import java.util.List;



import com.henu.dao.impl.IStockOrderDAOImpl;
import com.henu.dbc.DataBaseConnection;
import com.henu.dao.IStockOrderDAO;
import tableModel.StockOrder;

/**
 * @author WKQ
 *
 */
public class IStockOrderDAOProxy implements IStockOrderDAO {
	private DataBaseConnection dbc = null;
	private IStockOrderDAO dao = null;
	
	public IStockOrderDAOProxy() {
		this.dbc = new DataBaseConnection();
		this.dao = new IStockOrderDAOImpl(this.dbc.getConnection());
	}
	
	public boolean doCreate(StockOrder stockOrder) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doCreate(stockOrder);
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
	
	public StockOrder findById(String id) throws Exception {
		StockOrder stockOrder = null;
		try {
			stockOrder = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return stockOrder;
	}
	
	public List<List> findBySupplier(String supplier) throws Exception {
		List<List> all = null;
		try {
			all = this.dao.findBySupplier(supplier);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

}
