package mypack;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class DispatcherServlet extends GenericServlet {

  private String target = "./homepage.html";

  // 响应客户请求
  public void service(ServletRequest request,ServletResponse response)
    throws ServletException, IOException {

    // 读取表单中的用户名
    String username = request.getParameter("username");
    // 读取表单中的密码
    String password = request.getParameter("password");

    String fileName = "D:/tomcat6/webapps/BitEdu/WEB-INF/sno.dat";
    
    // 将学号写进sno.dat
    try{
      int sno = Integer.parseInt(username);
      DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
      out.writeInt(sno);
      out.close();
    }catch(IOException iox){
      System.out.println("Problem writing" + fileName);
    }
  
    // 在request对象中添加USER属性
    request.setAttribute("USER", username);
    // 在request对象中添加PASSWORD属性
    request.setAttribute("PASSWORD", password);

    // 把请求转发给homepage.html 
    ServletContext context = getServletContext();
    RequestDispatcher dispatcher =context.getRequestDispatcher(target);
    dispatcher.forward(request, response);
  }
}
