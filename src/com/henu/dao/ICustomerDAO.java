package com.henu.dao;

import java.util.List;

import tableModel.Customer;



public interface ICustomerDAO {
	/**
	 * ��ʾ���ݿ�����Ӳ���
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean doCreate(Customer customer) throws Exception;

	public boolean doUpdate(Customer customer) throws Exception;

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
	public Customer findById(String id) throws Exception;

	/**
	 * ��ѯ��ʱ�򽫷���һ�����
	 * 
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
	public List<Customer> findAll(String keyWord) throws Exception;


}
