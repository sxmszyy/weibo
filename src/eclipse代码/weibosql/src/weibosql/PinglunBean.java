package weibosql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PinglunBean {

public  List< PinglunInfo > getAll(String weibonum){
		
	List<PinglunInfo> data = new ArrayList<PinglunInfo>(); 
		
		
		Connection conn = DBConn.getConnection();// 取得连接 
		try{
			
			String sql = " select name, head, pinglun.pinglunid, pinglunword, pinglunTime, "
					+ " level FROM pinglun , user, userpinglun "
					+ " where pinglun.pinglunid in "
					+ " (select pinglunid from weibopinglun where weibopinglun.weibonum = ?) "
					+ " and userpinglun.pinglunid = pinglun.pinglunid "
					+ " and userpinglun.phone = user.phone "
					+ " and level = ? ";
			PreparedStatement st = conn.prepareStatement( sql );

			int i = 1;
			
			//  phone
			st.setString( i, weibonum );
			i++;
			st.setString( i, "0" );
			
			ResultSet rs = st.executeQuery(  );// 执行查询语句
			System.out.println( "查询结果为：" );
			
			while( rs.next() ){
				String head = rs.getString( "head" );
				String name = rs.getString( "name" );
				String pinglunid = rs.getString( "pinglunid" );
				String pinglunword = rs.getString("pinglunword");
				String pingluntime = rs.getString("pingluntime");
				String level = rs.getString("level");
				
				PinglunInfo info = new PinglunInfo(pinglunid, head, name,
						pinglunword, pingluntime, level);
				
				data.add( info );
			}
			
		}
		catch( SQLException e ){
			e.printStackTrace();
			System.out.printf( "数据库查询失败\n" + e.getMessage()  );
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

		return data;
	}


public  List< PinglunInfo > getHuifu(String weibonum, String Pinglunid){
	
	List<PinglunInfo> data = new ArrayList<PinglunInfo>(); 
		
		
		Connection conn = DBConn.getConnection();// 取得连接 
		try{
			
			String sql = " select name, head, pinglun.pinglunid, pinglunword, pinglunTime, "
					+ " level FROM pinglun , user, userpinglun "
					+ " where pinglun.pinglunid in "
					+ " (select pinglunid from weibopinglun where weibopinglun.weibonum = ?) "
					+ " and userpinglun.pinglunid = pinglun.pinglunid "
					+ " and userpinglun.phone = user.phone "
					+ " and level = ? ";
			PreparedStatement st = conn.prepareStatement( sql );

			int i = 1;
			
			//  phone
			st.setString( i, weibonum );
			i++;
			st.setString( i, Pinglunid );
			
			ResultSet rs = st.executeQuery(  );// 执行查询语句
			System.out.println( "查询结果为：" );
			
			while( rs.next() ){
				String head = rs.getString( "head" );
				String name = rs.getString( "name" );
				String pinglunid = rs.getString( "pinglunid" );
				String pinglunword = rs.getString("pinglunword");
				String pingluntime = rs.getString("pingluntime");
				String level = rs.getString("level");
				
				PinglunInfo info = new PinglunInfo(pinglunid, head, name,
						pinglunword, pingluntime, level);
				
				data.add( info );
			}
			
		}
		catch( SQLException e ){
			e.printStackTrace();
			System.out.printf( "数据库查询失败\n" + e.getMessage()  );
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

		return data;
	}
	
public boolean addPinglun( String weibonum, String phone, 
		String pinglunword, String pingluntime, String level ){
	
	// 获取连接
	Connection conn = DBConn.getConnection();
	Calendar calendar = Calendar.getInstance();
	
    //年
    int year = calendar.get(Calendar.YEAR);
    //月
    int month = calendar.get(Calendar.MONTH);
    //日
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    //小时
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    //分钟
    int minute = calendar.get(Calendar.MINUTE);
    int ran = (int)(Math.random() * 100);
    String pinglunid = phone + year + month + day + hour + minute + ran;
	try{
		String sql1 = " insert pinglun "
				+ "  (pinglunid, pinglunword, level, pinglunTime) "
				+ "  values(?, ?, ?, ?) ";
		PreparedStatement st = conn.prepareStatement(  sql1 );
		int i = 1;
		
		st.setString( i, pinglunid );
		i++;
		//  word
		st.setString( i, pinglunword );
		i++;
		//  time
		st.setString( i,  level);
		i++;
		//  level
		st.setString( i, pingluntime );			
		
		System.out.printf("sql = %s\n", st.toString());
		
		st.executeUpdate(  );// 执行语句
		
		String sql2 = " insert userpinglun "
				+ "  (phone, pinglunid) "
				+ "  values(?, ?) ";
		PreparedStatement stuserpinglun = conn.prepareStatement(  sql2 );
		int ii = 1;
		
		//  phone
		stuserpinglun.setString( ii, phone );
		ii++;
		//  pinglunid
		stuserpinglun.setString( ii, pinglunid );
		
		stuserpinglun.executeUpdate(  );// 执行语句
		
		String sql3 = " insert weibopinglun "
				+ "  (weibonum, pinglunid, isread) "
				+ "  values(?, ?, ?) ";
		PreparedStatement stweibopinglun = conn.prepareStatement(  sql3 );
		int j = 1;
		
		//  weibonum
		stweibopinglun.setString( j, weibonum );
		j++;
		//  pinglunid
		stweibopinglun.setString( j, pinglunid );
		j++;
		// isread
		stweibopinglun.setString(j, "1");
		
		stweibopinglun.executeUpdate(  );// 执行语句
		
		String sqladd = " update weibo set ping = ping + 1 where weibonum = ? ";
		
		PreparedStatement stadd = conn.prepareStatement(  sqladd );
		int t = 1;
		
		//  weibonum
		stadd.setString( t, weibonum );
		
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
	
}
