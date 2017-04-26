package com.henu.dao;

import java.util.List;

import tableModel.Inventory;


public interface IInventoryDAO {
	/**
	 * ��ʾ���ݿ�����Ӳ���
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean doCreate(Inventory inventory) throws Exception;

	public boolean doUpdate(Inventory inventory) throws Exception;

	/**
	 * ��ʾɾ�������������ɾ��
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDelete(String id) throws Exception;

	/**
	 * ��ʾ���ݿ�Ĳ�ѯ����
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Inventory findById(String id) throws Exception;

	/**
	 * ��ѯ��ʱ�򽫷���һ�����
	 * 
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
	public List<List> findAll(String keyWord) throws Exception;


}
