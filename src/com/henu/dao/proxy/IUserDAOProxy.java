package com.henu.dao.proxy;

import java.util.List;

import com.henu.dao.IUserDAO;
import com.henu.dao.impl.IUserDAOImpl;
import com.henu.dbc.DataBaseConnection;
import tableModel.User;

public class IUserDAOProxy implements IUserDAO {
	private DataBaseConnection dbc = null;
	private IUserDAO dao = null;

	public IUserDAOProxy() {
		this.dbc = new DataBaseConnection();
		this.dao = new IUserDAOImpl(this.dbc.getConnection());
	}

	public boolean doCreate(User user) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doCreate(user);
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


	public boolean doUpdate(User user) throws Exception {
		boolean flag = true;
		try {
			flag = this.dao.doUpdate(user);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public List<User> findAll(String keyWord) throws Exception {
		List<User> all = null;
		try {
			all = this.dao.findAll(keyWord);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}
	
	
	
	public List<User> findAll() throws Exception {
		List<User> all = null;
		try {
			all = this.dao.findAll();
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

	public User findById(String id) throws Exception {
		User user = null;
		try {
			user = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return user;
	}
	
	public User getUser(String id,String password) throws Exception {
		User user = null;
		try {
			user = this.dao.getUser(id,password);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return user;
	}
	

}
