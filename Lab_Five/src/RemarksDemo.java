
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemarksDemo extends HttpServlet
{
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
       
            ServletContext sc = getServletContext();
           
        int id= Integer.parseInt((String) sc.getAttribute("id"));
        String uname= (String) sc.getAttribute("uname");
        String pwd=(String) sc.getAttribute("pwd");
        String remark=req.getParameter("uremark");
       // out.println(id+"&nbsp;"+uname+"&nbsp;"+pwd+"&nbsp;"+remark);

     try
        {
    	 Class.forName("oracle.jdbc.driver.OracleDriver");  
    	    Connection con=DriverManager.getConnection( "jdbc:oracle:thin:@SUMANTH:1521:xe","system","sumanth");  
         
         
         
          PreparedStatement pstmt=con.prepareStatement("select * from farewell where id=?");
        pstmt.setInt(1,id);
         ResultSet rs=pstmt.executeQuery();
         
         if(rs.next())
         {
             out.println("<center>Login valid</center>");
           
           
             PreparedStatement pstmt1 = con.prepareStatement(" update farewell set uremark=? where id=? ");
 
             pstmt1.setString(1, remark);
             pstmt1.setInt(2, id);
             
              int n = pstmt1.executeUpdate();
 
 
              out.println("<center>Thanks for feedback  ur feedback updated<center> ");
              System.out.println(n+" records updated");
             
         }
         else
         {
         PreparedStatement pstmt2= con.prepareStatement(" insert into farewell values(?,?,?,?) ");
         pstmt2.setInt(1, id);
         pstmt2.setString(2, uname);
         pstmt2.setString(3,pwd);
         pstmt2.setString(4,remark);
         
         
         int n = pstmt2.executeUpdate();

         out.println("<center>Thanks for feedback  <center> ");
         System.out.println(n+" records inserted");
         }
             
        }
        catch(Exception e){
            System.out.println(e);
        }
       
       
    }
}
