package budget_tracker.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// データソースの作成
		DataSource ds;

		// 初期化処理
		public void init() throws ServletException {
			try {
				// 初期コンテキストを取得
				InitialContext ic = new InitialContext();
				// ルックアップしてデータソースを取得
				ds = (DataSource) ic.lookup("java:comp/env/jdbc/searchman");
			} catch (Exception e) {

			}
		}
		
		/**
		 * @see HttpServlet#HttpServlet()
		 */
		public Login() {
			super();
			// TODO Auto-generated constructor stub
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// DB関連の初期設定
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 文字コードの設定
		request.setCharacterEncoding("Windows-31J");

		// index.jspで入力したnameの取得
		String name = request.getParameter("name");

		// index.jspで入力したidの取得
		String id = request.getParameter("id");

		// index.jspで入力したseiの取得
		String sei = request.getParameter("sei");

		// index.jspで入力したnenの取得
		String nen = request.getParameter("nen");
		
	
		try {
			// JDBC Driver の登録
			Class.forName("com.mysql.jdbc.Driver");
			// Connectionの作成
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_db?serverTimezone=UTC&useSSL=false",
					"suser", "spass");

			// sql文作成の準備
			StringBuffer sql = new StringBuffer();

			// sql文 の作成（nameから）
			sql.append("select id, name, sei, nen, address from shain_table where name like '%");
			sql.append(name + "%'");

			// idが選択されている場合は、追加する
			if (id != "") {
				sql.append("and id ='" + id + "'");
			}

			// seiが選択されている場合は、追加する
			if (sei != "") {
				sql.append("and sei ='" + sei + "'");
			}

			// nenが選択されている場合は、追加する
			if (nen != "") {
				sql.append("and nen ='" + nen + "'");
			}

			// sql文を表示
			System.out.println(sql);

			// sql文実行準備
			pstmt = conn.prepareStatement(new String(sql));

			// sql文実行
			pstmt.execute();

			// 実行結果を、ResultSetクラスに代入
			rset = pstmt.executeQuery();

			// 遷移ページへ、引渡し（Attributeで追加する）
			request.setAttribute("kekka", rset);

			// search.jspへ遷移
			request.getRequestDispatcher("/search.jsp").forward(request, response);

			// 使用したオブジェクトを終了させる
			rset.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
//			e.printStackTrace();
//			
//			String status ="検索に失敗しました。管理者に連絡してください。";					
//			request.setAttribute("status", status);
//			request.getRequestDispatcher("/result.jsp").forward(request, response);

		} finally {
			try {
				// 念のため、finallyでDBとの接続を切断しておく
				conn.close();
			} catch (Exception e) {
			}
		}

	}

}
