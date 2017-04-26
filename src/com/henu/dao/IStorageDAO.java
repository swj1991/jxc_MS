/**
 * 
 */
package com.henu.dao;

import java.util.List;

import tableModel.Storage;

/**
 * @author WKQ
 *
 */
public interface IStorageDAO {
	public boolean doCreate(Storage user) throws Exception;
	
	public boolean doUpdate(Storage user) throws Exception;

	public boolean doDelete(String id) throws Exception;
	
	public Storage findById(String id) throws Exception;
	
	public List<Storage> findAll(String keyWord) throws Exception;

}
