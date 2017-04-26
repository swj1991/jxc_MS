/**
 * 
 */
package com.henu.dao.proxy;

import java.util.List;

import com.henu.dao.ISupplierDAO;
import com.henu.dao.impl.ISupplierDAOImpl;
import com.henu.dbc.DataBaseConnection;
import tableModel.Supplier;

/**
 * @author WKQ
 *
 */
public class ISupplierDAOProxy implements ISupplierDAO {
	private DataBaseConnection dbc = null;
	private ISupplierDAO dao = null;

	public ISupplierDAOProxy() {
		this.dbc = new DataBaseConnection();
		this.dao = new ISupplierDAOImpl(this.dbc.getConnection());
	}

	public boolean doCreate(Supplier supplier) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doCreate(supplier);
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


	public boolean doUpdate(Supplier supplier) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doUpdate(supplier);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public List<Supplier> findAll(String keyWord) throws Exception {
		List<Supplier> all = null;
		try {
			all = this.dao.findAll(keyWord);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

	public Supplier findById(String id) throws Exception {
		Supplier supplier = null;
		try {
			supplier = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return supplier;
	}

}
