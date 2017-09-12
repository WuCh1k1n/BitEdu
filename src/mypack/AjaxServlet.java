package mypack;

import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		Connection con;
		Statement stmt;
		PreparedStatement prepStmt;
		ResultSet rs;

		try {
			// 读取学号
			int sno;
			String fileName = "D:/tomcat6/webapps/BitEdu/WEB-INF/sno.dat";
			DataInputStream instr = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			sno = instr.readInt();
			instr.close();

			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			String dbUrl = "jdbc:mysql://localhost:3306/bitedu?useUnicode=true&characterEncoding=GB2312";
			String dbUser = "root";
			String dbPwd = "mysql/19951020";
			con = java.sql.DriverManager.getConnection(dbUrl, dbUser, dbPwd);

			// 输出每次考试每科分数
			String selectStatement = "select exam_time,chi,math,eng,phy,chem,bio,his,geo,pol from exam where sno = ? ";
			prepStmt=con.prepareStatement(selectStatement);
			prepStmt.setInt(1,sno);
			rs = prepStmt.executeQuery();

			PrintWriter write = response.getWriter();
			for (int i = 0; i < 3; i++) {
				rs.next();
				//write.println(sno+",");
				write.println(rs.getString(1)+",");
				write.println(rs.getFloat(2)+",");
				write.println(rs.getFloat(3)+",");
				write.println(rs.getFloat(4)+",");
				write.println(rs.getFloat(5)+",");
				write.println(rs.getFloat(6)+",");
				write.println(rs.getFloat(7)+",");
				write.println(rs.getFloat(8)+",");
				write.println(rs.getFloat(9)+",");
				write.println(rs.getFloat(10)+",");
			}

			int rank[]=new int[3];
			int avg_score[]=new int[3];

			// 输出语文成绩平均排名
			String selectStatement_chi = "SELECT COUNT(chi)+1 FROM exam WHERE chi > (SELECT chi FROM exam WHERE sno = ? AND exam_time = ? ) AND exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_chi);
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-10-01");
			prepStmt.setString(3,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[0]=rs.getInt(1);
			write.println(rank[0]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-11-01");
			prepStmt.setString(3,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[1]=rs.getInt(1);
			write.println(rank[1]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-12-01");
			prepStmt.setString(3,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[2]=rs.getInt(1);
			write.println(rank[2]+",");

			// 输出数学成绩平均排名
			String selectStatement_math = "SELECT COUNT(math)+1 FROM exam WHERE math > (SELECT math FROM exam WHERE sno = ? AND exam_time = ? ) AND exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_math);
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-10-01");
			prepStmt.setString(3,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[0]=rs.getInt(1);
			write.println(rank[0]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-11-01");
			prepStmt.setString(3,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[1]=rs.getInt(1);
			write.println(rank[1]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-12-01");
			prepStmt.setString(3,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[2]=rs.getInt(1);
			write.println(rank[2]+",");

			// 输出英语成绩平均排名
			String selectStatement_eng = "SELECT COUNT(eng)+1 FROM exam WHERE eng > (SELECT eng FROM exam WHERE sno = ? AND exam_time = ? ) AND exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_eng);
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-10-01");
			prepStmt.setString(3,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[0]=rs.getInt(1);
			write.println(rank[0]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-11-01");
			prepStmt.setString(3,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[1]=rs.getInt(1);
			write.println(rank[1]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-12-01");
			prepStmt.setString(3,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[2]=rs.getInt(1);
			write.println(rank[2]+",");

			// 输出物理成绩平均排名
			String selectStatement_phy = "SELECT COUNT(phy)+1 FROM exam WHERE phy > (SELECT phy FROM exam WHERE sno = ? AND exam_time = ? ) AND exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_phy);
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-10-01");
			prepStmt.setString(3,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[0]=rs.getInt(1);
			write.println(rank[0]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-11-01");
			prepStmt.setString(3,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[1]=rs.getInt(1);
			write.println(rank[1]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-12-01");
			prepStmt.setString(3,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[2]=rs.getInt(1);
			write.println(rank[2]+",");

			// 输出化学成绩平均排名
			String selectStatement_chem = "SELECT COUNT(chem)+1 FROM exam WHERE chem > (SELECT chem FROM exam WHERE sno = ? AND exam_time = ? ) AND exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_chem);
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-10-01");
			prepStmt.setString(3,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[0]=rs.getInt(1);
			write.println(rank[0]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-11-01");
			prepStmt.setString(3,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[1]=rs.getInt(1);
			write.println(rank[1]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-12-01");
			prepStmt.setString(3,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[2]=rs.getInt(1);
			write.println(rank[2]+",");

			// 输出生物成绩平均排名
			String selectStatement_bio = "SELECT COUNT(bio)+1 FROM exam WHERE bio > (SELECT bio FROM exam WHERE sno = ? AND exam_time = ? ) AND exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_bio);
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-10-01");
			prepStmt.setString(3,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[0]=rs.getInt(1);
			write.println(rank[0]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-11-01");
			prepStmt.setString(3,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[1]=rs.getInt(1);
			write.println(rank[1]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-12-01");
			prepStmt.setString(3,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[2]=rs.getInt(1);
			write.println(rank[2]+",");

			// 输出历史成绩平均排名
			String selectStatement_his = "SELECT COUNT(his)+1 FROM exam WHERE his > (SELECT his FROM exam WHERE sno = ? AND exam_time = ? ) AND exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_his);
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-10-01");
			prepStmt.setString(3,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[0]=rs.getInt(1);
			write.println(rank[0]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-11-01");
			prepStmt.setString(3,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[1]=rs.getInt(1);
			write.println(rank[1]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-12-01");
			prepStmt.setString(3,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[2]=rs.getInt(1);
			write.println(rank[2]+",");

			// 输出地理成绩平均排名
			String selectStatement_geo = "SELECT COUNT(geo)+1 FROM exam WHERE geo > (SELECT geo FROM exam WHERE sno = ? AND exam_time = ? ) AND exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_geo);
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-10-01");
			prepStmt.setString(3,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[0]=rs.getInt(1);
			write.println(rank[0]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-11-01");
			prepStmt.setString(3,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[1]=rs.getInt(1);
			write.println(rank[1]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-12-01");
			prepStmt.setString(3,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[2]=rs.getInt(1);
			write.println(rank[2]+",");

			// 输出政治成绩平均排名
			String selectStatement_pol = "SELECT COUNT(pol)+1 FROM exam WHERE pol > (SELECT pol FROM exam WHERE sno = ? AND exam_time = ? ) AND exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_pol);
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-10-01");
			prepStmt.setString(3,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[0]=rs.getInt(1);
			write.println(rank[0]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-11-01");
			prepStmt.setString(3,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[1]=rs.getInt(1);
			write.println(rank[1]+",");
			
			prepStmt.setInt(1,sno);
			prepStmt.setString(2,"2016-12-01");
			prepStmt.setString(3,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			rank[2]=rs.getInt(1);
			write.println(rank[2]+",");

			String selectStatement_avg_chi="SELECT AVG(chi) FROM exam where exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_avg_chi);

			prepStmt.setString(1,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[0]=rs.getInt(1);
			write.println(avg_score[0]+",");

			prepStmt.setString(1,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[1]=rs.getInt(1);
			write.println(avg_score[1]+",");

			prepStmt.setString(1,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[2]=rs.getInt(1);
			write.println(avg_score[2]+",");

			String selectStatement_avg_math="SELECT AVG(math) FROM exam where exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_avg_math);

			prepStmt.setString(1,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[0]=rs.getInt(1);
			write.println(avg_score[0]+",");

			prepStmt.setString(1,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[1]=rs.getInt(1);
			write.println(avg_score[1]+",");

			prepStmt.setString(1,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[2]=rs.getInt(1);
			write.println(avg_score[2]+",");

			String selectStatement_avg_eng="SELECT AVG(eng) FROM exam where exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_avg_eng);

			prepStmt.setString(1,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[0]=rs.getInt(1);
			write.println(avg_score[0]+",");

			prepStmt.setString(1,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[1]=rs.getInt(1);
			write.println(avg_score[1]+",");

			prepStmt.setString(1,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[2]=rs.getInt(1);
			write.println(avg_score[2]+",");

			String selectStatement_avg_phy="SELECT AVG(phy) FROM exam where exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_avg_phy);

			prepStmt.setString(1,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[0]=rs.getInt(1);
			write.println(avg_score[0]+",");

			prepStmt.setString(1,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[1]=rs.getInt(1);
			write.println(avg_score[1]+",");

			prepStmt.setString(1,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[2]=rs.getInt(1);
			write.println(avg_score[2]+",");

			String selectStatement_avg_chem="SELECT AVG(chem) FROM exam where exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_avg_chem);

			prepStmt.setString(1,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[0]=rs.getInt(1);
			write.println(avg_score[0]+",");

			prepStmt.setString(1,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[1]=rs.getInt(1);
			write.println(avg_score[1]+",");

			prepStmt.setString(1,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[2]=rs.getInt(1);
			write.println(avg_score[2]+",");

			String selectStatement_avg_bio="SELECT AVG(bio) FROM exam where exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_avg_bio);

			prepStmt.setString(1,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[0]=rs.getInt(1);
			write.println(avg_score[0]+",");

			prepStmt.setString(1,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[1]=rs.getInt(1);
			write.println(avg_score[1]+",");

			prepStmt.setString(1,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[2]=rs.getInt(1);
			write.println(avg_score[2]+",");

			String selectStatement_avg_his="SELECT AVG(his) FROM exam where exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_avg_his);

			prepStmt.setString(1,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[0]=rs.getInt(1);
			write.println(avg_score[0]+",");

			prepStmt.setString(1,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[1]=rs.getInt(1);
			write.println(avg_score[1]+",");

			prepStmt.setString(1,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[2]=rs.getInt(1);
			write.println(avg_score[2]+",");

			String selectStatement_avg_geo="SELECT AVG(geo) FROM exam where exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_avg_geo);

			prepStmt.setString(1,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[0]=rs.getInt(1);
			write.println(avg_score[0]+",");

			prepStmt.setString(1,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[1]=rs.getInt(1);
			write.println(avg_score[1]+",");

			prepStmt.setString(1,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[2]=rs.getInt(1);
			write.println(avg_score[2]+",");

			String selectStatement_avg_pol="SELECT AVG(pol) FROM exam where exam_time = ? ";
			prepStmt=con.prepareStatement(selectStatement_avg_pol);

			prepStmt.setString(1,"2016-10-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[0]=rs.getInt(1);
			write.println(avg_score[0]+",");

			prepStmt.setString(1,"2016-11-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[1]=rs.getInt(1);
			write.println(avg_score[1]+",");

			prepStmt.setString(1,"2016-12-01");
			rs = prepStmt.executeQuery();
			rs.next();
			avg_score[2]=rs.getInt(1);
			write.println(avg_score[2]+",");

			write.flush();
			write.close();

			rs.close();
			prepStmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//javac -classpath D:\tomcat6\lib\servlet-api.jar;D:\tomcat6\lib\mysql-connector-java-5.1.41-bin.jar -sourcepath src -d WEB-INF\classes src\mypack\AjaxServlet.java
}