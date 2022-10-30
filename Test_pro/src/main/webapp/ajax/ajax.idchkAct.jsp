<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page trimDirectiveWhitespaces="true" %>
 <%
//자바코드 입력

String url = "jdbc:mysql://localhost:3306/bigdata05?serverTimezone=Asia/Seoul"; //데이터베이스 연결1 <<3306 : 포트번호, bigdata05 : 데이터베이스
String user = "root";  //데이터베이스 연결1
String password = "bigdata";  //데이터베이스 연결1

StringBuffer qry = new StringBuffer();
qry.append("SELECT count(*) FROM big_member WHERE mb_id = ? ");
String sql = qry.toString();

Connection conn = null;
PreparedStatement stmt = null;
ResultSet rs = null;

String ajaxMessage = "Fail";

try{
	Class.forName("com.mysql.cj.jdbc.Driver"); //드라이버 로드
	conn =  DriverManager.getConnection(url, user, password); //데이터베이스 연결
	
	stmt = conn.prepareStatement(sql);
	
	stmt.setString(1,request.getParameter("mb_id"));
	
	
	rs = stmt.executeQuery();

	if(rs.next()){ //next() : 
		
		//세션변수 생성 아이디,이름
		int  idchknum = rs.getInt("count(*)");
		 if(idchknum == 0){
			 ajaxMessage = "Success";
		 }else{
			 ajaxMessage = "Fail";
		 }
	} 
}catch(Exception e){
	
}finally{
	try{
		if(rs !=null) rs.close();
		if(stmt !=null) stmt.close();
		if(conn !=null) conn.close();
		
	} catch(Exception e){		
	}
}
out.print(ajaxMessage);
%>