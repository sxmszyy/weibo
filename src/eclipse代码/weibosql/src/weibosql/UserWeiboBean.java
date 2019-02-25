package weibosql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserWeiboBean {
	public UserWeiboBean () {
		System.out.print("00000000");
	}
	
	public boolean addZan( UserWeiboInfo info ){
		
		// 获取连接
		Connection conn = DBConn.getConnection();
		
		try{
			String sql = " insert zan "
					+ "  (phone, weibonum) "
					+ "  values(?, ?) ";
			PreparedStatement st = conn.prepareStatement(  sql );
			int i = 1;
			
			//  phone
			st.setString( i, info.getPhone() );
			i++;
			//  weibonum
			st.setString( i, info.getWeibonum() );
			
			System.out.printf("sql = %s\n", st.toString());
			
			st.executeUpdate(  );// 执行语句
			
			String sqladd = " update weibo set zan = zan + 1 where weibonum = ? ";
			
			PreparedStatement stadd = conn.prepareStatement(  sqladd );
			int ii = 1;
			
			//  weibonum
			stadd.setString( ii, info.getWeibonum() );
			
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
	
	public String selectPhone( UserWeiboInfo info ){
		
		String phone = null;
		// 获取连接
		Connection conn = DBConn.getConnection();
		
		try{
			String sql = " select phone "
					+ "  from zan "
					+ "  where phone = ? and weibonum = ? ";
			PreparedStatement st = conn.prepareStatement(  sql );
			int i = 1;
			
			//  phone
			st.setString( i, info.getPhone() );
			i++;
			//  weibonum
			st.setString( i, info.getWeibonum() );
			
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
	
	public boolean delZan( UserWeiboInfo info ){
		
		// 获取连接
		Connection conn = DBConn.getConnection();
		
		try{
			String sql = " delete from zan "
					+ "  where phone = ? and weibonum = ? ";
			PreparedStatement st = conn.prepareStatement(  sql );
			int i = 1;
			
			//  phone
			st.setString( i, info.getPhone() );
			i++;
			//  weibonum
			st.setString( i, info.getWeibonum() );
			
			System.out.printf("sql = %s\n", st.toString());
			
			st.executeUpdate(  );// 执行语句
			
			String sqldel = " update weibo set zan = zan - 1 where weibonum = ? ";
			
			PreparedStatement stdel = conn.prepareStatement(  sqldel );
			int ii = 1;
			
			//  weibonum
			stdel.setString( ii, info.getWeibonum() );
			
			System.out.printf("sql = %s\n", stdel.toString());
			
			stdel.executeUpdate(  );// 执行语句
			
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
}
