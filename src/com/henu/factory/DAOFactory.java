/**
 * 
 */
package com.henu.factory;

import com.henu.dao.IUserDAO;
import com.henu.dao.proxy.IUserDAOProxy;
import com.henu.dao.ISupplierDAO;
import com.henu.dao.proxy.ISupplierDAOProxy;
import com.henu.dao.IStorageDAO;
import com.henu.dao.proxy.IStorageDAOProxy;
import com.henu.dao.ICustomerDAO;
import com.henu.dao.proxy.ICustomerDAOProxy;
import com.henu.dao.IProductDAO;
import com.henu.dao.proxy.IProductDAOProxy;
import com.henu.dao.IInOrderDAO;
import com.henu.dao.proxy.IInOrderDAOProxy;
import com.henu.dao.IOutOrderDAO;
import com.henu.dao.proxy.IOutOrderDAOProxy;
import com.henu.dao.IInventoryDAO;
import com.henu.dao.proxy.IInventoryDAOProxy;
import com.henu.dao.IStockOrderDAO;
import com.henu.dao.proxy.IStockOrderDAOProxy;
import com.henu.dao.ISalesOrderDAO;
import com.henu.dao.proxy.ISalesOrderDAOProxy;


/**
 * @author WKQ
 *
 */
public class DAOFactory {
	public static IUserDAO getIUserDAOInstance() {
		return new IUserDAOProxy();
	}
	
	public static ISupplierDAO getISupplierDAOInstance() {
		return new ISupplierDAOProxy();
	}
	
	public static IStorageDAO getIStorageDAOInstance() {
		return new IStorageDAOProxy();
	}
	
	public static ICustomerDAO getICustomerDAOInstance() {
		return new ICustomerDAOProxy();
	}
	
	public static IProductDAO getIProductDAOInstance() {
		return new IProductDAOProxy();
	}
	
	public static IInOrderDAO getIInOrderDAOInstance() {
		return new IInOrderDAOProxy();
	}
	
	public static IOutOrderDAO getIOutOrderDAOInstance() {
		return new IOutOrderDAOProxy();
	}
	
	public static IInventoryDAO getIInventoryDAOInstance() {
		return new IInventoryDAOProxy();
	}
	
	public static IStockOrderDAO getIStockOrderDAOInstance() {
		return new IStockOrderDAOProxy();
	}
	
	public static ISalesOrderDAO getISalesOrderDAOInstance() {
		return new ISalesOrderDAOProxy();
	}
	
	

}
