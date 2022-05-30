

import jakarta.annotation.ManagedBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	try {	
		Connection conexao = dao.getConnection();
		PreparedStatement stmt = conexao.prepareStatement("SELECT username,senha FROM tbl_usuario where username = ? and senha = ?");
		stmt.setString(1, username);
		stmt.setString(2,password);
		
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			if(rs.getString(2).equals("12345")) {
				HttpSession mySession = request.getSession();
				mySession.setAttribute("username", username);
				response.sendRedirect("atualizarSenha.jsp");
				
			}else {
				HttpSession mySession = request.getSession();
				mySession.setAttribute("username", username);
				response.sendRedirect("index.jsp");
			}
		} else {
			response.sendRedirect("login.jsp?error=1");
		}
		conexao.close();
		stmt.close();
	} catch (SQLException e) {
		// e.printStackTrace();
		throw new RuntimeException(e.getMessage());
	} 
		
		
	}


}

