package mapper;

import java.sql.*;
import java.util.*;

import model.MemberVO;

public class MemberMapper {
	
	
	public void create(MemberVO vo) {
		

		//JDBC 프로그래밍..어려워.....
		String url = "jdbc:mysql://localhost:3306/bigdata05?serverTimezone=UTC"; //데이터베이스 연결1
		String user = "root";  //데이터베이스 연결1
		String password = "bigdata";  //데이터베이스 연결1

		StringBuffer qry = new StringBuffer(); //statement 생성  StringBuffer : 값 추가
		qry.append(" INSERT INTO big_member (mb_id, mb_pw, mb_name, mb_email, mb_zipcode, mb_addr, mb_detailaddr, mb_phone, mb_birth, mb_gender, mb_joindate) ");
		qry.append(" VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now()) ");

		String sql = qry.toString(); //toString : 문자열로 리턴

		int idx = 1;
		Connection conn = null;
		PreparedStatement stmt = null; 
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver"); //jdbc드라이버 로드
			conn = DriverManager.getConnection(url, user, password); //데이터베이스 연결2
			stmt = conn.prepareStatement(sql);  //statement 생성
			//?에 값넣기
			stmt.setString(idx++, vo.getMb_id()); //setString : 값 넣어주는
			stmt.setString(idx++, vo.getMb_pw());
			stmt.setString(idx++, vo.getMb_name());
			stmt.setString(idx++, vo.getMb_email());
			stmt.setString(idx++, vo.getMb_zipcode());
			stmt.setString(idx++, vo.getMb_addr());
			stmt.setString(idx++, vo.getMb_detailaddr());
			stmt.setString(idx++, vo.getMb_phone());
			stmt.setString(idx++, vo.getMb_birth());
			stmt.setString(idx++, vo.getMb_gender());
			//실행
			int res = stmt.executeUpdate(); //executeUpdate : SQL문 전송 (전체실행), 실행 동시에 결과받기
			if(res != 0){
				
			}
		} catch(Exception e){
			e.getLocalizedMessage(); //getLocalizedMessage : 에러메세지 출력하는 함수
		} finally{
			try{
				//연결해제
				if(stmt != null) stmt.close(); //열었으니까 닫아주래..왜인지는 모르겠지만...값이 더이상 입력되지 않오록 종료시키는것임!
				if(conn != null) conn.close(); //열었으니까 닫아주래..왜인지는 모르겠지만...
			}catch(Exception e){
					e.getLocalizedMessage();   //에러메세지 출력하는 함수
			}
		}
	}

	public List<MemberVO> read(){
		
		//회원목록 가져오기

		String url = "jdbc:mysql://localhost:3306/bigdata05?serverTimezone=UTC"; //데이터베이스 연결2
		String user = "root";  //데이터베이스 연결2
		String password = "bigdata";  //데이터베이스 연결2

		StringBuffer qry = new StringBuffer();  //stmt 생성2
		qry.append("SELECT * FROM big_member WHERE mb_outdate = 'N' ORDER BY mb_joindate desc ");  //stmt 생성2

		String sql = qry.toString();   //stmt 생성2

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<MemberVO> list = new ArrayList<>();  //HashMap : 키와 밸류를 함께 가지고 있다

		try{
			Class.forName("com.mysql.cj.jdbc.Driver"); //드라이버로드 1
			conn =  DriverManager.getConnection(url, user, password); //데이버베이스연결1
			
			stmt = conn.prepareStatement(sql); //stmt 생성1
			
			rs =  stmt.executeQuery();  //sql문 실행
			while(rs.next()){
				MemberVO member = new MemberVO();
				
				member.setMb_id(rs.getString("mb_id"));
				member.setMb_name(rs.getString("mb_name"));
				member.setMb_email(rs.getString("mb_email"));
				member.setMb_phone(rs.getString("mb_phone"));
				member.setMb_birth(rs.getString("mb_birth"));
				member.setMb_gender(rs.getString("mb_gender"));
				member.setMb_joindate(rs.getDate("mb_joindate"));
				
				list.add(member);
			}
		}catch(Exception e){
			
		}finally{ // finally : 예외가 발생하던 말던 무조건 한번 수행됨!
			try{
				if(rs !=null) rs.close();
				if(stmt !=null) stmt.close();
				if(conn !=null) conn.close();
				
			} catch(Exception e){		
			}
		}
		return list;		
	}
	
	public List<MemberVO> readout(String keyword){
		
		
		//회원목록 가져오기

		String url = "jdbc:mysql://localhost:3306/bigdata05?serverTimezone=Asia/Seoul"; //데이터베이스 연결2
		String user = "root";  //데이터베이스 연결2
		String password = "bigdata";  //데이터베이스 연결2

		StringBuffer qry = new StringBuffer();  //stmt 생성2
		qry.append("SELECT * FROM big_member WHERE NOT mb_outdate = 'N' ");
		
		  if(keyword!=null || "".equals(keyword)){ 
			  qry.append("AND (mb_name LIKE ? OR mb_email LIKE ? OR mb_phone LIKE ? OR mb_birth LIKE ?)"
		  ); }
		 
		qry.append("ORDER BY mb_joindate desc ");  //stmt 생성2

		String sql = qry.toString();   //stmt 생성2

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<MemberVO> list = new ArrayList<>();   //HashMap : 키와 밸류를 함께 가지고 있다

		try{
			Class.forName("com.mysql.cj.jdbc.Driver"); //드라이버로드 1
			conn =  DriverManager.getConnection(url, user, password); //데이버베이스연결1
			
			stmt = conn.prepareStatement(sql); //stmt 생성1
			
			
			  if(keyword!=null || "".equals(keyword)){ 
				  stmt.setString(1, "%"+keyword+"%");
				  stmt.setString(2, "%"+keyword+"%"); 
				  stmt.setString(3, "%"+keyword+"%");
				  stmt.setString(4, "%"+keyword+"%"); 
				  }
			 
			
			rs =  stmt.executeQuery();  //sql문 실행

			while(rs.next()){
				MemberVO member = new MemberVO();
				
				member.setMb_id(rs.getString("mb_id"));
				member.setMb_name(rs.getString("mb_name"));
				member.setMb_email(rs.getString("mb_email"));
				member.setMb_phone(rs.getString("mb_phone"));
				member.setMb_birth(rs.getString("mb_birth"));
				member.setMb_gender(rs.getString("mb_gender"));
				member.setMb_joindate(rs.getDate("mb_joindate"));
				
				list.add(member);
			}
		}catch(Exception e){
			
		}finally{ // finally : 예외가 발생하던 말던 무조건 한번 수행됨!
			try{
				if(rs !=null) rs.close();
				if(stmt !=null) stmt.close();
				if(conn !=null) conn.close();
				
			} catch(Exception e){		
			}
		}
	
		return list;
	}
}
