

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;


/**
 * Servlet implementation class PasswordReset
 */
public class PasswordReset extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordReset() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    		if (request.getParameterMap().containsKey("username")) {
    			response.getWriter().append(request.getParameter("username"));
    			String username = request.getParameter("username");
    			String idPedidor;
    			try {
    				Connection conexao = dao.getConnection();
    				//Verificacao se existe
    				PreparedStatement stmt = conexao.prepareStatement("SELECT id FROM tbl_usuario where username = ?");
    				stmt.setString(1, username);
    				ResultSet rs = stmt.executeQuery();
    				if (rs.next()) {
    					idPedidor =Integer.toString(rs.getInt(1));
    					stmt = conexao.prepareStatement("INSERT INTO tbl_pedido (tipo_pedido,id_pedidor) values (1,?)");
        				stmt.setString(1, idPedidor);
        				if(stmt.executeUpdate() > 0) {
        					response.sendRedirect("login.jsp?reset=send");
        				}
    					stmt.close();
    					conexao.close();
    				}else {
    					response.sendRedirect("login.jsp?error=3");
    				}
    				
    				
    			} catch (SQLException e) {
    				// e.printStackTrace();
    				throw new RuntimeException(e);
    			}
    		}else {
    			response.sendRedirect("login.jsp?error=2");
    		}
          	
    	
    }

}
