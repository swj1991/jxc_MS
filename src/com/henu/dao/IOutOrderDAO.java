package com.henu.dao;

import java.util.List;

import tableModel.OutOrder;



public interface IOutOrderDAO {
	/**
	 * ��ʾ���ݿ�����Ӳ���
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean doCreate(OutOrder OutOrder) throws Exception;

	public boolean doUpdate(OutOrder OutOrder) throws Exception;

	/**
	 * ��ʾɾ�������������ɾ��
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
//	public boolean doDelete(String id) throws Exception;

	/**
	 * ��ʾ���ݿ�Ĳ�ѯ����
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public OutOrder findById(String id) throws Exception;

	/**
	 * ��ѯ��ʱ�򽫷���һ�����
	 * 
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
	public List<OutOrder> findAll(String keyWord) throws Exception;

}
