package weibosql;

import java.sql.Connection;
import java.sql.SQLException;

import weibosql.UserInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserBean {
	
	public UserBean () {
		System.out.print("00000000");
	}
	
	public boolean add( UserInfo info ){
		
		// 获取连接
		Connection conn = DBConn.getConnection();
		
		try{
			String sql = " insert user "
					+ "  (phone, password, name, head) "
					+ "  values(?, null, null, null) ";
			PreparedStatement st = conn.prepareStatement(  sql );
			int i = 1;
			
			//  phone
			st.setString( i, info.getPhone() );
			
			System.out.printf("sql = %s\n", st.toString());
			
			st.executeUpdate(  );// 执行语句
			System.out.printf("1");
		}
		catch( SQLException e ){
			System.out.printf( "新增失败:" + e.getMessage() );
			return false;
		}
		finally{
			if( conn != null ){
				try{
					conn.close();
				}
				catch( SQLException e ){
					System.out.printf( "关闭连接失败\n" + e.getMessage()  );
				}// try
			}// if
			
		}// finally
		
		return true;
	}
	
	public String selectPhone( UserInfo info ){
		
		String phone = null;
		// 获取连接
		Connection conn = DBConn.getConnection();
		
		try{
			String sql = " select phone "
					+ "  from user "
					+ "  where phone = ? ";
			PreparedStatement st = conn.prepareStatement(  sql );
			int i = 1;
			
			//  phone
			st.setString( i, info.getPhone() );
			
			System.out.printf("sql = %s\n", st.toString());
			
			ResultSet rs = st.executeQuery(  );//执行查询语句
			while( rs.next() ){
				
				// 手机号
				phone = rs.getString("phone");
				
			}
		}
		catch( SQLException e ){
			System.out.printf( "新增失败:" + e.getMessage() );
			phone = null;
			return phone;
		}
		finally{
			if( conn != null ){
				try{
					conn.close();
				}
				catch( SQLException e ){
					System.out.printf( "关闭连接失败\n" + e.getMessage()  );
				}// try
			}// if
			
		}// finally
		
		return phone;
	}
	
	public String selectPhoneByPassword(String _phone, String _password){
		
		String phone = null;
		// 获取连接
		Connection conn = DBConn.getConnection();
		
		try{
			String sql = " select phone "
					+ "  from user "
					+ "  where phone = ? and password = ? ";
			PreparedStatement st = conn.prepareStatement(  sql );
			int i = 1;
			
			//  phone
			st.setString( i, _phone );
			i++;
			st.setString( i, _password );
			
			System.out.printf("sql = %s\n", st.toString());
			
			ResultSet rs = st.executeQuery(  );//执行查询语句
			while( rs.next() ){
				
				// 手机号
				phone = rs.getString("phone");
				
			}
		}
		catch( SQLException e ){
			System.out.printf( "查找失败:" + e.getMessage() );
			phone = null;
			return phone;
		}
		finally{
			if( conn != null ){
				try{
					conn.close();
				}
				catch( SQLException e ){
					System.out.printf( "关闭连接失败\n" + e.getMessage()  );
				}// try
			}// if
			
		}// finally
		
		return phone;
	}
	
	public boolean updateUser(UserInfo info){
		
		// 获取连接
		Connection conn = DBConn.getConnection();
		
		try{
			String sql = " update user "
					+ "  set password = ? , name = ? "
					+ "  where phone = ?  ";
			PreparedStatement st = conn.prepareStatement(  sql );
			int i = 1;
			
			//  phone
			st.setString( i, info.getPassword() );
			i++;
			st.setString( i, info.getName() );
			i++;
			st.setString( i, info.getPhone() );
			
			System.out.printf("sql = %s\n", st.toString());
			
			st.executeUpdate(  );// 执行语句
		}
		catch( SQLException e ){
			System.out.printf( "更新失败:" + e.getMessage() );
			return false;
		}
		finally{
			if( conn != null ){
				try{
					conn.close();
				}
				catch( SQLException e ){
					System.out.printf( "关闭连接失败\n" + e.getMessage()  );
					return false;
				}// try
			}// if
			
		}// finally
		
		return true;
	}
}
