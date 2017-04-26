package com.henu.dao.proxy;

import java.util.List;

import tableModel.InOrder;

import com.henu.dao.IInOrderDAO;
import com.henu.dao.impl.IInOrderDAOImpl;
import com.henu.dbc.DataBaseConnection;

public class IInOrderDAOProxy implements IInOrderDAO{
	private DataBaseConnection dbc = null;
	private IInOrderDAO dao = null;

	public IInOrderDAOProxy() {
		this.dbc = new DataBaseConnection();
		this.dao = new IInOrderDAOImpl(this.dbc.getConnection());
	}


	public boolean doCreate(InOrder inOrder) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doCreate(inOrder);
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


	public boolean doUpdate(InOrder InOrder) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doUpdate(InOrder);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public List<InOrder> findAll(String keyWord) throws Exception {
		List<InOrder> all = null;
		try {
			all = this.dao.findAll(keyWord);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

	public InOrder findById(String id) throws Exception {
		InOrder inOrder = null;
		try {
			inOrder = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return inOrder;
	}

}
