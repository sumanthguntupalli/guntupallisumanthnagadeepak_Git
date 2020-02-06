import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Registration extends HttpServlet
{
    public void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
       
        int id=Integer.parseInt(req.getParameter("id"));
        String name=req.getParameter("name");
        String uname=req.getParameter("uname");
        String pwd=req.getParameter("pwd");
        String email=req.getParameter("email");
       

     try
        {
    	 Class.forName("oracle.jdbc.driver.OracleDriver");  
    	    Connection conn=DriverManager.getConnection( "jdbc:oracle:thin:@SUMANTH:1521:xe","system","sumanth");
         
         
         
          PreparedStatement pstmt = conn.prepareStatement("insert into lab5 values(?,?,?,?,?) ");
          pstmt.setInt(1,id);
          pstmt.setString(2,name);
          pstmt.setString(3,uname);
          pstmt.setString(4,pwd);
          pstmt.setString(5,email);
         
         
          int n=pstmt.executeUpdate();
          System.out.println(n+"records inserted");
          if(n!=0)
          {
              //out.println("<center>Registration completed Successfully now Login to your account</center>");
              //out.println("<br>");
             RequestDispatcher rd=req.getRequestDispatcher("login.html");
              rd.include(req,res);
            
            
          }
          else
          {
          out.println("<center>Registration Unsuccesful/center>");
           RequestDispatcher rd=req.getRequestDispatcher("index.html");
              rd.include(req,res);
          }
         
        }
        catch(Exception e){
            System.out.println(e);
        }
       
       
    }
}
