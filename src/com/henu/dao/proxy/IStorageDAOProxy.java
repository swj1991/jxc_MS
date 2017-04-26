/**
 * 
 */
package com.henu.dao.proxy;

import java.util.List;

import com.henu.dao.IStorageDAO;
import com.henu.dao.impl.IStorageDAOImpl;
import com.henu.dbc.DataBaseConnection;
import tableModel.Storage;
/**
 * @author WKQ
 *
 */
public class IStorageDAOProxy implements IStorageDAO{
	private DataBaseConnection dbc = null;
	private IStorageDAO dao = null;

	public IStorageDAOProxy() {
		this.dbc = new DataBaseConnection();
		this.dao = new IStorageDAOImpl(this.dbc.getConnection());
	}

	public boolean doCreate(Storage storage) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doCreate(storage);
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


	public boolean doUpdate(Storage storage) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doUpdate(storage);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public List<Storage> findAll(String keyWord) throws Exception {
		List<Storage> all = null;
		try {
			all = this.dao.findAll(keyWord);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

	public Storage findById(String id) throws Exception {
		Storage storage = null;
		try {
			storage = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return storage;
	}

}
