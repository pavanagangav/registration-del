package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/register")

public class servlets extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/restapi1","root","root");
			String sql="insert into regstration(name,email,password) values(?,?,?)";
			PreparedStatement pmst=conn.prepareStatement(sql);
			String name =req.getParameter("name");
			String email =req.getParameter("email");
			String password=req.getParameter("password");
			
			pmst.setString(1,name);
			pmst.setString(2,email);
			pmst.setString(3,password);
			int i=pmst.executeUpdate();
			PrintWriter pw=resp.getWriter();
			if (i>0) {
				pw.println("success");
				
			}
			else {
				pw.println("error");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
