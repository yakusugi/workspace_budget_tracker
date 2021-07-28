package budget_tracker.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// �f�[�^�\�[�X�̍쐬
		DataSource ds;

		// ����������
		public void init() throws ServletException {
			try {
				// �����R���e�L�X�g���擾
				InitialContext ic = new InitialContext();
				// ���b�N�A�b�v���ăf�[�^�\�[�X���擾
				ds = (DataSource) ic.lookup("java:comp/env/jdbc/searchman");
			} catch (Exception e) {

			}
		}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// setting text code
		request.setCharacterEncoding("Windows-31J");

		// get the id in index.jsp
		String inputId = request.getParameter("id");

		// get the password in index.jsp
		String inputPassword = request.getParameter("password");

		System.out.println(inputId);
		System.out.println(inputPassword);
		
		try {
			
			// �f�[�^�\�[�X����Connection���擾
			conn = ds.getConnection();
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("select * from login_table where id = ? && password = ? ");
			
			pstmt = conn.prepareStatement(new String(sql));
			
			pstmt.setString(1, inputId);
			
			pstmt.setString(2, inputPassword);
			
			rset = pstmt.executeQuery();

			// transfer the data to the transition page(put it by Attribute)
			request.setAttribute("SqlResult", rset);

			// move on to loginResult.jsp or loginResultFailed.jsp
			if (rset.next()) {
	            request.getRequestDispatcher("mainSearch.jsp").forward(request, response);
	        } else {
	            request.getRequestDispatcher("loginfailed.jsp").forward(request, response);
	        }

			// terminate the used objects
			rset.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();

			doGet(request, response);
		} finally {
			try {
				// just in case, terminate the DB connection with finally statement
				conn.close();
			} catch (Exception e) {
			}
		}
	}

}
