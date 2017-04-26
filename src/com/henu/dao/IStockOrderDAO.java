/**
 * 
 */
package com.henu.dao;

import java.util.List;

import tableModel.StockOrder;

/**
 * @author WKQ
 *
 */
public interface IStockOrderDAO {
	public boolean doCreate(StockOrder stockOrder) throws Exception;
	
	public List<List> findAll() throws Exception;
	
	public StockOrder findById(String id) throws Exception;
	
	public List<List> findBySupplier(String supplier) throws Exception;

}
