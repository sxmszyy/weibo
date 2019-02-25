package weibosql;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {

	// 获得数据库连接
    public static Connection getConnection() {

        Connection con = null;
        try {
        	// 加载MySql数据驱动
            Class.forName("com.mysql.jdbc.Driver");
         // 建立链接
            con = DriverManager.getConnection(
            	//  本机，端口号：３３０６，　数据库：weibo
                    "jdbc:mysql://localhost:3306/weibo"
                            + "?useUnicode=true&characterEncoding=UTF-8",
                    "root", // 用户名
                    ""); // 密码
            // 创建数据库连接
        } catch (Exception e) {
            System.out.printf("数据库连接失败\n");
        }

        return con;
    }
}
