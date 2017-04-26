/**
 * 
 */
package com.henu.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author WKQ
 *
 */
public class DataBaseConnection {
	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver" ;
	private static final String DBURL = "jdbc:mysql://192.168.34.7:3306/db_jxcms?" +
			"useUnicode=true&characterEncoding=UTF-8" ;
	private static final String DBUSER = "user" ;
	private static final String DBPASS = "123" ;
	private Connection conn = null ;
	public DataBaseConnection(){
		try {
			Class.forName(DBDRIVER) ;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DBURL, DBUSER,DBPASS) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		return this.conn ;
	}
	public void close(){
		if(this.conn!=null){
			try {
				this.conn.close() ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
