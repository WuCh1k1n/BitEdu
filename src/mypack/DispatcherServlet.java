package mypack;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class DispatcherServlet extends GenericServlet {

  private String target = "./homepage.html";

  // ��Ӧ�ͻ�����
  public void service(ServletRequest request,ServletResponse response)
    throws ServletException, IOException {

    // ��ȡ���е��û���
    String username = request.getParameter("username");
    // ��ȡ���е�����
    String password = request.getParameter("password");

    String fileName = "D:/tomcat6/webapps/BitEdu/WEB-INF/sno.dat";
    
    // ��ѧ��д��sno.dat
    try{
      int sno = Integer.parseInt(username);
      DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
      out.writeInt(sno);
      out.close();
    }catch(IOException iox){
      System.out.println("Problem writing" + fileName);
    }
  
    // ��request���������USER����
    request.setAttribute("USER", username);
    // ��request���������PASSWORD����
    request.setAttribute("PASSWORD", password);

    // ������ת����homepage.html 
    ServletContext context = getServletContext();
    RequestDispatcher dispatcher =context.getRequestDispatcher(target);
    dispatcher.forward(request, response);
  }
}
