package mapper;

import java.sql.*;

import model.MemberVO;

public class LoginMapper {

	public MemberVO read(MemberVO vo) {
		
		String url = "jdbc:mysql://localhost:3306/bigdata05?serverTimezone=Asia/Seoul"; //데이터베이스 연결1 <<3306 : 포트번호, bigdata05 : 데이터베이스
		String user = "root";  //데이터베이스 연결1
		String password = "bigdata";  //데이터베이스 연결1

		StringBuffer qry = new StringBuffer();
		qry.append("SELECT * FROM big_member WHERE mb_outdate = 'N'AND mb_id = ? AND mb_pw = ? ");
		String sql = qry.toString();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		MemberVO member = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver"); //드라이버 로드
			conn =  DriverManager.getConnection(url, user, password); //데이터베이스 연결
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,vo.getMb_id());
			stmt.setString(2,vo.getMb_pw());
			
			rs = stmt.executeQuery();

			if(rs.next()){ //next() : 
				
				//세션변수 생성 아이디,이름
				member = new MemberVO();
				
				member.setMb_id(rs.getString("mb_id"));
				member.setMb_name(rs.getString("mb_name"));
				
				    
				
			} else{
				
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
		
		
		return member;
		
		
	}
}
