package budget_tracker.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LoginServletLogic {

	ResultSet rset = null;
	DataSource ds;
	Connection conn = null;
	PreparedStatement pstmt = null;

	public ResultSet login(String inputId, String inputPassword) {
		try {

			// 初期コンテキストを取得
			InitialContext ic = new InitialContext();
			// ルックアップしてデータソースを取得
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/searchman");

			// データソースからConnectionを取得
			conn = ds.getConnection();

			StringBuilder sql = new StringBuilder();

			sql.append("select * from login_table where id = ? && password = ? ");

			pstmt = conn.prepareStatement(new String(sql));

			pstmt.setString(1, inputId);

			pstmt.setString(2, inputPassword);

			rset = pstmt.executeQuery();

			return rset;

		} catch (Exception e) {
			e.printStackTrace();

		} 

		return rset;

	}

}
