package com.henu.dao;

import java.util.List;

import tableModel.OutOrder;



public interface IOutOrderDAO {
	/**
	 * 表示数据库的增加操作
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean doCreate(OutOrder OutOrder) throws Exception;

	public boolean doUpdate(OutOrder OutOrder) throws Exception;

	/**
	 * 表示删除操作，按编号删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
//	public boolean doDelete(String id) throws Exception;

	/**
	 * 表示数据库的查询操作
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public OutOrder findById(String id) throws Exception;

	/**
	 * 查询的时候将返回一组对象
	 * 
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
	public List<OutOrder> findAll(String keyWord) throws Exception;

}
