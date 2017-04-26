package com.henu.dao;

import java.util.List;

import tableModel.InOrder;



public interface IInOrderDAO {
	
		/**
		 * ��ʾ���ݿ�����Ӳ���
		 * 
		 * @param user
		 * @return
		 * @throws Exception
		 */
		public boolean doCreate(InOrder inOrder) throws Exception;

		public boolean doUpdate(InOrder inOrder) throws Exception;

		/**
		 * ��ʾɾ�������������ɾ��
		 * 
		 * @param id
		 * @return
		 * @throws Exception
		 */
//		public boolean doDelete(String id) throws Exception;

		/**
		 * ��ʾ���ݿ�Ĳ�ѯ����
		 * 
		 * @param id
		 * @return
		 * @throws Exception
		 */
		public InOrder findById(String id) throws Exception;

		/**
		 * ��ѯ��ʱ�򽫷���һ�����
		 * 
		 * @param keyWord
		 * @return
		 * @throws Exception
		 */
		public List<InOrder> findAll(String keyWord) throws Exception;
	}




