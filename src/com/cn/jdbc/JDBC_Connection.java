package com.cn.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBC_Connection {
	static String drivername="com.mysql.jdbc.Driver";
	static String url="jdbc:mysql://rdsys6g6ap201micjtyil.mysql.rds.aliyuncs.com:3306/rvtj5kg3g7wld3y2";
    static String username="weiqianghu";
    static String password="930409";
    
    static{
    	try{
    		Class.forName(drivername);
    		System.out.println("���������ɹ���");
    	}catch(ClassNotFoundException e){
    		System.out.println("��������ʧ�ܣ�����������");
    		e.printStackTrace();
    	}
    }
    public static Connection getConnection(){
    	Connection conn=null;
    	try{
    		conn=(Connection)DriverManager.getConnection(url,username,password);
    		//System.out.println("�������ݿ�ɹ���");
    	}catch(SQLException e)
    	{
    		System.out.println("�������ݿ�ʧ�ܣ�����url��username��password");
    		e.printStackTrace();
    	}
    	return conn;
    }
    
    public static void free(ResultSet rs,Connection conn,Statement stmt){
    	try{
    		if(rs!=null)
    			rs.close();
    	}catch(SQLException e){
    		System.out.println("�ر�resultSetʧ�ܣ�");
    		e.printStackTrace();
    	}finally{
    		try{
    			if(conn!=null)
        			conn.close();
    		}catch(SQLException e){
        		System.out.println("�ر�connectionʧ�ܣ�");
        		e.printStackTrace();
        	}finally{
        		try{
        			if(stmt!=null)
            			stmt.close();
        		}catch(SQLException e){
            		System.out.println("�ر�Statementʧ�ܣ�");
            		e.printStackTrace();
            	}
        	}
    	}
    	
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JDBC_Connection.getConnection();

	}

}