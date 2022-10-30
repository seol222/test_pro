<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%


Connection conn = null;
PreparedStatement stmt = null;
ResultSet rs = null;

List<HashMap<String, String>> list = new ArrayList<>();

try {
	
	String url = "jdbc:mysql://localhost:3306/memberlist?serverTimezone=UTC";  //3306/? => ?에는 본인의 데이터베이스 명을 입력.
	String user = "root";  //본인 db계정명
	String password = "";  //본인 db비밀번호
	
	StringBuffer qry = new StringBuffer();
	qry.append(" SELECT * FROM memberlist WHERE signup = 'Y' "); //가입여부가 y인 조건인 모든 테이블 조회.
	String sql =  qry.toString();
	
	
	
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	conn = DriverManager.getConnection(url, user, password);
	
	
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()){ //next메서드를 통해 다음행이 있으면 true, 없으면 false반환.
		HashMap<String, String> hm = new HashMap<>();

		hm.put("id", rs.getString("id"));
		hm.put("pw", rs.getString("pw"));
		hm.put("code", rs.getString("code"));
		hm.put("addr", rs.getString("addr"));
		hm.put("tel", rs.getString("tel"));
		hm.put("signup", rs.getString("signup"));
		
		list.add(hm);

	} 
	
} catch(Exception e) {
	if(rs != null)conn.close();
	if(stmt != null)conn.close();
	if(conn != null)conn.close();
	out.println("연결에 실패하였습니다.");
} 


%> 
 
<%@include file="/views/include/header.jsp" %> 

<div class="row">
	<div class="col-md-12">
		<h4 class="m-b-lg" style="text-align: center;">Member List</h4>
	</div><!-- END column -->
	<div class="col-md-12">
		<div class="widget p-lg">
			<h4 class="m-b-lg">회원목록</h4>
				<p class="m-b-lg docs">
					가입된 회원의 목록입니다. 1년간 로그인이 없을 시 탈퇴회원 또는 휴면계정으로 변환하셔야합니다.
				</p>
				<div class="table-responsive">



					<table class="table">
						<tr>
							<th>#순번</th>
							<th>아이디</th>
							<th>비번</th>
							<th>우편번호</th>
							<th>주소</th>
							<th>연락처</th>
							<th>가입여부</th>
						</tr>
<%
int number = 1;
int chk = 1;
Iterator<HashMap<String, String>> it = list.iterator();

 
while(it.hasNext()){
	HashMap<String, String> member = it.next();

%>	
				
						<tr>
							<td><%=number++ %></td>
							<td><%=member.get("id") %></td>
							<td><%=member.get("pw") %></td>
							<td><%=member.get("code") %></td>
							<td><%=member.get("addr") %></td>
							<td><%=member.get("tel") %></td>
							<td><%=member.get("signup") %></td>
						</tr>



<%
}					
if(chk == 1){
%>							
							<tr>
								<td colspan="7">가입된 회원이 없습니다.</td>
								
							</tr>
<%
}
%>				
						
					</table>
				</div>
		</div>
	</div>
</div>


<%@include file="/views/include/footer.jsp" %>