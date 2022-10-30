<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String id = request.getParameter("id");
String pw = request.getParameter("pw");
Connection conn = null;
PreparedStatement stmt = null;
ResultSet rs = null;



try {
	
	String url = "jdbc:mysql://localhost:3306/memberlist?serverTimezone=UTC";  //3306/? => ?에는 본인의 데이터베이스 명을 입력.
	String user = "root";  //본인 db계정명
	String password = "";  //본인 db비밀번호
	
	StringBuffer qry = new StringBuffer();
	qry.append(" SELECT * FROM memberlist WHERE id = ? AND pw = ? AND signup = 'Y' "); //로그인 select문
	String sql =  qry.toString();
	
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	conn = DriverManager.getConnection(url, user, password);
	
	stmt = conn.prepareStatement(sql);
	stmt.setString(1, request.getParameter("id"));
	stmt.setString(2, request.getParameter("pw"));
	rs = stmt.executeQuery();
	if(rs.next()){ //next메서드를 통해 다음행이 있으면 true, 없으면 false반환.
		id = rs.getString("id"); //아이디값 추출해서 id에서 저장.
		
		session.setAttribute("user_id", id);  //세션이라는 인증수단 등록.
		System.out.println(rs.getString("id")+","+rs.getString("pw"));
		response.sendRedirect("dashboard.jsp");

	} else {
		System.out.println("로그인에 실패");
		response.sendRedirect("index.jsp");
	}
	out.println(id+","+pw);
	out.println("연결되었습니다.");
} catch(Exception e) {
	if(rs != null)rs.close();
	if(stmt != null)stmt.close();
	if(conn != null)conn.close();
	out.println("연결에 실패하였습니다.");
}




%> 