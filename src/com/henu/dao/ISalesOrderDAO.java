/**
 * 
 */
package com.henu.dao;

import java.util.List;

import tableModel.SalesOrder;

/**
 * @author WKQ
 *
 */
public interface ISalesOrderDAO {
	public boolean doCreate(SalesOrder SalesOrder) throws Exception;
	
	public List<List> findAll() throws Exception;
	
	public SalesOrder findById(String id) throws Exception;
	
	public List<List> findByCustomer(String customer) throws Exception;
	
	public List<List> salesRanking(String userName) throws Exception;

}
