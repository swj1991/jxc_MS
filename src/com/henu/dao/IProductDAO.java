package com.henu.dao;

import java.util.List;

import tableModel.Product;

public interface IProductDAO {
	/**
	 * ��ʾ���ݿ�����Ӳ���
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean doCreate(Product product) throws Exception;

	public boolean doUpdate(Product product) throws Exception;

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
	public Product findById(String id) throws Exception;

	/**
	 * ��ѯ��ʱ�򽫷���һ�����
	 * 
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
	public List<Product> findAll(String keyWord) throws Exception;

}
