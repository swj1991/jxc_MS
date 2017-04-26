package com.henu.dao;

import java.util.List;

import tableModel.InOrder;



public interface IInOrderDAO {
	
		/**
		 * 表示数据库的增加操作
		 * 
		 * @param user
		 * @return
		 * @throws Exception
		 */
		public boolean doCreate(InOrder inOrder) throws Exception;

		public boolean doUpdate(InOrder inOrder) throws Exception;

		/**
		 * 表示删除操作，按编号删除
		 * 
		 * @param id
		 * @return
		 * @throws Exception
		 */
//		public boolean doDelete(String id) throws Exception;

		/**
		 * 表示数据库的查询操作
		 * 
		 * @param id
		 * @return
		 * @throws Exception
		 */
		public InOrder findById(String id) throws Exception;

		/**
		 * 查询的时候将返回一组对象
		 * 
		 * @param keyWord
		 * @return
		 * @throws Exception
		 */
		public List<InOrder> findAll(String keyWord) throws Exception;
	}




