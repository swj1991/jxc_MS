/**
 * 
 */
package com.henu.dao;

import java.util.List;
import tableModel.User;

/**
 * @author WKQ
 *
 */
public interface IUserDAO { 	
	public boolean doCreate(User user) throws Exception;
	
	public boolean doUpdate(User user) throws Exception;

	public boolean doDelete(String id) throws Exception;
	
	public User findById(String id) throws Exception;
	
	public List<User> findAll(String keyWord) throws Exception;
	
	public List<User> findAll() throws Exception;
	
	
	public User getUser(String id,String password) throws Exception;
	
}
