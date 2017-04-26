/**
 * 
 */
package com.henu.dao;

import java.util.List;
import tableModel.Supplier;

/**
 * @author WKQ
 *
 */
public interface ISupplierDAO {
	public boolean doCreate(Supplier user) throws Exception;
	
	public boolean doUpdate(Supplier user) throws Exception;

	public boolean doDelete(String id) throws Exception;
	
	public Supplier findById(String id) throws Exception;
	
	public List<Supplier> findAll(String keyWord) throws Exception;
	
	
}
