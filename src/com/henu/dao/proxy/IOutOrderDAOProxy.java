package com.henu.dao.proxy;

import java.util.List;

import tableModel.OutOrder;

import com.henu.dao.IOutOrderDAO;
import com.henu.dao.impl.IOutOrderDAOImpl;
import com.henu.dbc.DataBaseConnection;

public class IOutOrderDAOProxy implements IOutOrderDAO{
	private DataBaseConnection dbc = null;
	private IOutOrderDAO dao = null;

	public IOutOrderDAOProxy() {
		this.dbc = new DataBaseConnection();
		this.dao = new IOutOrderDAOImpl(this.dbc.getConnection());
	}


	public boolean doCreate(OutOrder outOrder) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doCreate(outOrder);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}


//	public boolean doDelete(String id) throws Exception {
//		boolean flag = true;
//		try {
//			flag = this.dao.doDelete(id);
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			this.dbc.close();
//		}
//		return flag;
//	}


	public boolean doUpdate(OutOrder OutOrder) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doUpdate(OutOrder);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public List<OutOrder> findAll(String keyWord) throws Exception {
		List<OutOrder> all = null;
		try {
			all = this.dao.findAll(keyWord);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

	public OutOrder findById(String id) throws Exception {
		OutOrder outOrder = null;
		try {
			outOrder = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return outOrder;
	}

}

