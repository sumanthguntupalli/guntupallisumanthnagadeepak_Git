
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginDemo extends HttpServlet
{
    public void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
       
        String uname=req.getParameter("uname");
        String pwd=req.getParameter("pwd");
     

     try
        {
    	 Class.forName("oracle.jdbc.driver.OracleDriver");  
    	    Connection con=DriverManager.getConnection( "jdbc:oracle:thin:@SUMANTH:1521:xe","system","sumanth");  
         
         
         
          PreparedStatement pstmt=con.prepareStatement("select * from lab5 where uname=? and pwd=?");
          pstmt.setString(1,uname);
          pstmt.setString(2,pwd);
         ResultSet rs=pstmt.executeQuery();
         
         if(rs.next())
         {
             out.println("<center>Login valid</center>");
           
           
             ServletContext sc = getServletContext();
             
             sc.setAttribute("uname", uname);
             sc.setAttribute("pwd", pwd);
             sc.setAttribute("id", rs.getString("id"));

           
     
           RequestDispatcher rd=req.getRequestDispatcher("remarks.html");
           rd.forward(req,res);
           

           
         }
         else
         {
         out.println("<center>Login Invalid</center>");
         RequestDispatcher rd=req.getRequestDispatcher("index.html");
         rd.include(req,res);
         }
        }
        catch(Exception e){
            System.out.println(e);
        }
       
       
    }
}
