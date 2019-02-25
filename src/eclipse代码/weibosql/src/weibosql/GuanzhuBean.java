package weibosql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuanzhuBean {
	public GuanzhuBean () {
		System.out.print("00000000");
	}
	
	public boolean addGuanzhu( GuanzhuInfo info ){
		
		// 获取连接
		Connection conn = DBConn.getConnection();
		
		try{
			String sql = " insert guanzhu "
					+ "  (phone, guanzhuphone) "
					+ "  values(?, ?) ";
			PreparedStatement st = conn.prepareStatement(  sql );
			int i = 1;
			
			//  phone
			st.setString( i, info.getPhone() );
			i++;
			//  guanzhuphone
			st.setString( i, info.getGuanzhuphone() );
			
			System.out.printf("sql = %s\n", st.toString());
			
			st.executeUpdate(  );// 执行语句
			
			String sqladd = " insert fensi "
					+ "  (phone, fensiphone) "
					+ "  values(?, ?) ";
			
			PreparedStatement stadd = conn.prepareStatement(  sqladd );
			int ii = 1;
			
			//  getGuanzhuphone
			stadd.setString( ii, info.getGuanzhuphone() );
			ii++;
			//  phone
			stadd.setString( ii, info.getPhone() );
			
			System.out.printf("sql = %s\n", stadd.toString());
			
			stadd.executeUpdate(  );// 执行语句
			
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
	
	public boolean delGuanzhu( GuanzhuInfo info ){
		
		// 获取连接
		Connection conn = DBConn.getConnection();
		
		try{
			String sql = " delete from guanzhu "
					+ "  where phone = ? and guanzhuphone = ? ";
			PreparedStatement st = conn.prepareStatement(  sql );
			int i = 1;
			
			//  phone
			st.setString( i, info.getPhone() );
			i++;
			//  guanzhuphone
			st.setString( i, info.getGuanzhuphone() );
			
			System.out.printf("sql = %s\n", st.toString());
			
			st.executeUpdate(  );// 执行语句
			
			String sqldel = " delete from fensi "
					+ "  where phone = ? and fensiphone = ? ";
			
			PreparedStatement stadd = conn.prepareStatement(  sqldel );
			int ii = 1;
			
			//  getGuanzhuphone
			stadd.setString( ii, info.getGuanzhuphone() );
			ii++;
			//  phone
			stadd.setString( ii, info.getPhone() );
			
			System.out.printf("sql = %s\n", stadd.toString());
			
			stadd.executeUpdate(  );// 执行语句
			
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
	
	public String selectPhone( GuanzhuInfo info ){
		
		String phone = null;
		// 获取连接
		Connection conn = DBConn.getConnection();
		
		try{
			String sql = " select phone "
					+ "  from guanzhu "
					+ "  where phone = ? and guanzhuphone = ? ";
			PreparedStatement st = conn.prepareStatement(  sql );
			int i = 1;
			
			//  phone
			st.setString( i, info.getPhone() );
			i++;
			//  guanzhuphone
			st.setString( i, info.getGuanzhuphone() );
			
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
	
	public String selectPhone( String weibonum ){
		
		String phone = null;
		// 获取连接
		Connection conn = DBConn.getConnection();
		
		try{
			String sql = " select phone "
					+ "  from userweibo "
					+ "  where weibonum = ? ";
			PreparedStatement st = conn.prepareStatement(  sql );
			int i = 1;
			//  weibonum
			st.setString( i, weibonum );
			
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
}
