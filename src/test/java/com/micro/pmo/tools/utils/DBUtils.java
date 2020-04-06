package com.micro.pmo.tools.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/cars?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
	private static String userName = "root";
	private static String password = "root";
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 当前线程绑定一个开启事务的连接
    // ThreadLocal是一个map集合 具有get() set() remove()方法 key是线程名称
    public static Connection getConnection() throws SQLException {
        Connection conn = tl.get();
        if (conn == null) {
        	conn = DriverManager.getConnection(url, userName, password);
            tl.set(conn);            
        }
        return conn;
    }
    
    public static void startTransaction() {
        try {
            Connection conn = getConnection();
            if(conn != null) {
                conn.setAutoCommit(false);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    public static void rollback() {
        try {
            Connection conn = getConnection();
            if(conn != null) {
                conn.rollback();
                conn.commit();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    public static void commit() {
        try {
            Connection conn = getConnection();
            if(conn != null) {
                conn.commit();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    public static void close() {
        try {
            Connection conn = getConnection();
            if(conn != null) {
                try {
                    conn.close();
                } finally {
                    tl.remove();
                }                
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
