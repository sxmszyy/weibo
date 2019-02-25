package weibosql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WeiboBean {
	
	public WeiboBean () {
		System.out.print("00000000");
	}
	
	public List<WeiboInfo>getAll(String phone){
		List< WeiboInfo > data = 
				new ArrayList< WeiboInfo >();
		
		
		Connection conn = DBConn.getConnection();// 取得连接
		try{
			
			String sql = " select weibo.weibonum, head, name, time, introduce, " + 
					" word, image, label, zhuan, zan, ping "
					+ " from  weibo, user, userweibo "
					+ " where user.phone = userweibo.phone and userweibo.weibonum = weibo.weibonum and user.phone != ? order by time desc ";
			PreparedStatement st = conn.prepareStatement( sql );
			
			int ii = 1;
			
			//  phone
			st.setString( ii, phone );
			
			ResultSet rs = st.executeQuery(  );//执行查询语句
			System.out.println( "查询结果为：" );
			
			while( rs.next() ){
				//是否关注
				String isguanzhu = "+关注";
				//是否赞
				String iszan = "0";
				// 微博号
				String weibonum = rs.getString( "weibonum" );
				try {
					String sqlzan = " select phone from zan where weibonum = ? and phone = ? ";
					PreparedStatement stzan = conn.prepareStatement( sqlzan );
					int i = 1;
					//  weibonum
					stzan.setString( i, weibonum );
					i++;
					//  phone
					stzan.setString( i, phone );
					ResultSet rszan = stzan.executeQuery(  );//执行查询语句
					while( rszan.next() ) {
						String phonezan = rszan.getString("phone");
						if (!phonezan.equals("null") && !phonezan.equals(""))
							iszan = "1";
					}
				} catch (SQLException e ) {
					e.printStackTrace();
				} finally {}
				try {
					String sqlguanzhu = " select guanzhuphone from guanzhu where guanzhuphone in "
							+ "(select phone from userweibo, weibo where "
							+ " userweibo.weibonum = weibo.weibonum and weibo.weibonum = ?) and phone  = ? ";
					PreparedStatement stguanzhu = conn.prepareStatement( sqlguanzhu );
					int t = 1;
					//  weibonum
					stguanzhu.setString( t, weibonum );
					t++;
					//  phone
					stguanzhu.setString( t, phone );
					ResultSet rsguanzhu = stguanzhu.executeQuery(  );//执行查询语句
					while( rsguanzhu.next() ) {
						String phoneguanzhu = rsguanzhu.getString("guanzhuphone");
						if (!phoneguanzhu.equals("null") && !phoneguanzhu.equals("")){
							isguanzhu = "已关注";
						}
						else {
							isguanzhu = "+关注";
						}
					}
				} catch (SQLException e ) {
					e.printStackTrace();
				} finally {}
				
				// 头像
				String head = rs.getString( "head" );
				// 名字
				String name = rs.getString("name");
				// 正文
				String word = rs.getString( "word" );
				// 说明
				String introduce = rs.getString("introduce");
				// 时间
				String time = rs.getString( "time" );
				// 标签
				String label = rs.getString("label");
				// 图
				String image = rs.getString("image");
				if (image.equals("null") || image.equals(""))
					image = "0";
				// 转
				int zhuan = rs.getInt( "zhuan" );
				// 赞
				int zan = rs.getInt( "zan" );
				// 评
				int ping = rs.getInt( "ping" );
				WeiboInfo info = new WeiboInfo(weibonum, head, name, time, introduce, isguanzhu,
						word, image, label, zhuan, zan, ping, iszan);
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
	
	public List<WeiboInfo>getGuanzhuWeibo(String phone){
		List< WeiboInfo > data = 
				new ArrayList< WeiboInfo >();
		
		
		Connection conn = DBConn.getConnection();// 取得连接
		try{
			
			String sql = " select weibo.weibonum, head, name, time, introduce, " + 
					" word, image, label, zhuan, zan, ping "
					+ " from  weibo, user, userweibo "
					+ " where user.phone = userweibo.phone and userweibo.weibonum = weibo.weibonum " + 
				"	and userweibo.weibonum in (select weibonum from userweibo where phone in (select guanzhuphone from guanzhu where phone = ? ) ) order by time desc";
			PreparedStatement st = conn.prepareStatement( sql );
			
			int ii = 1;
			
			//  phone
			st.setString( ii, phone );
			
			ResultSet rs = st.executeQuery(  );//执行查询语句
			System.out.println( "查询结果为：" );
			
			while( rs.next() ){
				//是否关注
				String isguanzhu = "+关注";
				//是否赞
				String iszan = "0";
				// 微博号
				String weibonum = rs.getString( "weibonum" );
				try {
					String sqlzan = " select phone from zan where weibonum = ? and phone = ? ";
					PreparedStatement stzan = conn.prepareStatement( sqlzan );
					int i = 1;
					//  weibonum
					stzan.setString( i, weibonum );
					i++;
					//  phone
					stzan.setString( i, phone );
					ResultSet rszan = stzan.executeQuery(  );//执行查询语句
					while( rszan.next() ) {
						String phonezan = rszan.getString("phone");
						if (!phonezan.equals("null") && !phonezan.equals(""))
							iszan = "1";
					}
				} catch (SQLException e ) {
					e.printStackTrace();
				} finally {}
				try {
					String sqlguanzhu = " select guanzhuphone from guanzhu where guanzhuphone in "
							+ "(select phone from userweibo, weibo where "
							+ " userweibo.weibonum = weibo.weibonum and weibo.weibonum = ?) and phone  = ? ";
					PreparedStatement stguanzhu = conn.prepareStatement( sqlguanzhu );
					int t = 1;
					//  weibonum
					stguanzhu.setString( t, weibonum );
					t++;
					//  phone
					stguanzhu.setString( t, phone );
					ResultSet rsguanzhu = stguanzhu.executeQuery(  );//执行查询语句
					while( rsguanzhu.next() ) {
						String phoneguanzhu = rsguanzhu.getString("guanzhuphone");
						if (!phoneguanzhu.equals("null") && !phoneguanzhu.equals("")){
							isguanzhu = "已关注";
						}
						else {
							isguanzhu = "+关注";
						}
					}
				} catch (SQLException e ) {
					e.printStackTrace();
				} finally {}
				
				// 头像
				String head = rs.getString( "head" );
				// 名字
				String name = rs.getString("name");
				// 正文
				String word = rs.getString( "word" );
				// 说明
				String introduce = rs.getString("introduce");
				// 时间
				String time = rs.getString( "time" );
				// 标签
				String label = rs.getString("label");
				// 图
				String image = rs.getString("image");
				if (image.equals("null") || image.equals(""))
					image = "0";
				// 转
				int zhuan = rs.getInt( "zhuan" );
				// 赞
				int zan = rs.getInt( "zan" );
				// 评
				int ping = rs.getInt( "ping" );
				WeiboInfo info = new WeiboInfo(weibonum, head, name, time, introduce, isguanzhu,
						word, image, label, zhuan, zan, ping, iszan);
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
	
	public boolean addWeibo( String phone, String word, String time ){
		
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
        String weibonum = phone + year + month + day + hour + minute + ran;
		try{
			String sql = " insert weibo "
					+ "  (weibonum, word, introduce, time, label, zhuan, zan, ping, yuanchuang, image) "
					+ "  values(?, ?, null, ?, null, ?, ?, ?, ?, ?) ";
			PreparedStatement st = conn.prepareStatement(  sql );
			int i = 1;
			
			//  weibonum
			st.setString( i, weibonum );
			i++;
			//  word
			st.setString( i, word );
			i++;
			//  time
			st.setString( i, time );
			i++;
			//  zhuan
			st.setInt( i, 0 );
			i++;
			//  zan
			st.setInt( i, 0 );
			i++;
			//  ping
			st.setInt( i, 0 );
			i++;
			//  yuanchuang
			st.setString( i, "0" );
			i++;
			//  image
			st.setInt( i, 0 );			
			
			System.out.printf("sql = %s\n", st.toString());
			
			st.executeUpdate(  );// 执行语句
			
			String sqluserweibo = " insert userweibo "
					+ "  (phone, weibonum) "
					+ "  values(?, ?) ";
			PreparedStatement stuserweibo = conn.prepareStatement(  sqluserweibo );
			int ii = 1;
			
			//  weibonum
			stuserweibo.setString( ii, phone );
			ii++;
			//  word
			stuserweibo.setString( ii, weibonum );
			
			stuserweibo.executeUpdate(  );// 执行语句
			
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
	
	public boolean addWeibo( String phone, String word, String time, String zhuanfaweibonum, String image ){
		
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
        String weibonum = phone + year + month + day + hour + minute + ran;
		try{
			String sql = " insert weibo "
					+ "  (weibonum, word, introduce, time, label, zhuan, zan, ping, yuanchuang, image) "
					+ "  values(?, ?, null, ?, null, ?, ?, ?, ?, ?) ";
			PreparedStatement st = conn.prepareStatement(  sql );
			int i = 1;
			
			//  weibonum
			st.setString( i, weibonum );
			i++;
			//  word
			st.setString( i, word );
			i++;
			//  time
			st.setString( i, time );
			i++;
			//  zhuan
			st.setInt( i, 0 );
			i++;
			//  zan
			st.setInt( i, 0 );
			i++;
			//  ping
			st.setInt( i, 0 );
			i++;
			//  yuanchuang
			st.setString( i, zhuanfaweibonum );
			i++;
			//  image
			st.setString( i, image );			
			
			System.out.printf("sql = %s\n", st.toString());
			
			st.executeUpdate(  );// 执行语句
			
			String sqluserweibo = " insert userweibo "
					+ "  (phone, weibonum) "
					+ "  values(?, ?) ";
			PreparedStatement stuserweibo = conn.prepareStatement(  sqluserweibo );
			int ii = 1;
			
			//  weibonum
			stuserweibo.setString( ii, phone );
			ii++;
			//  word
			stuserweibo.setString( ii, weibonum );
			
			stuserweibo.executeUpdate(  );// 执行语句
			
			String sqladd = " update weibo set zhuan = zhuan + 1 where weibonum = ? ";
			
			PreparedStatement stadd = conn.prepareStatement(  sqladd );
			int t = 1;
			
			//  weibonum
			stadd.setString( t, zhuanfaweibonum );
			
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
