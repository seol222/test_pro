package mapper;

import java.sql.*;
import java.util.*;

import model.BoardVO;
import model.PagingDTO;

public class BoardMapper {
	
	//게시글등록 하는 메소드
	public void create(BoardVO vo) {
		

		//String bo_title = request.getParameter("bo_title");
		
		
		
		String url = "jdbc:mysql://localhost:3306/bigdata05?serverTimezone=UTC";  
		String user = "root";  
		String password = "bigdata";  

		StringBuffer qry = new StringBuffer();
		qry.append("INSERT INTO big_board(bo_num,bo_category,bo_title, bo_content, bo_mb_id, bo_mb_name, bo_hit, bo_inputdate, bo_ip) ");
		qry.append("VALUES (null,?,?,?,?,?,0,now(),?)");
		String sql = qry.toString();
		
		Connection conn = null;
		PreparedStatement stmt = null;
	
			
		//글 등록 후 리스트로 이동
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			stmt = conn.prepareStatement(sql);		
			
			stmt.setString(1, vo.getBo_category());
			stmt.setString(2, vo.getBo_title());
			stmt.setString(3, vo.getBo_content());
			stmt.setString(4, vo.getBo_mb_id());
			stmt.setString(5, vo.getBo_mb_name());
			stmt.setString(6, vo.getBo_ip());
		
			int res = stmt.executeUpdate();
			
			if(res != 0){
				
			}
		}catch(Exception e){
			
		}finally{
			try{
				
				if(stmt != null) stmt.close();
				if(conn != null) conn.close(); 
			}catch(Exception e){
					
			}
		}

	
	}
	// 글 수정하는 메소드
	public void update(BoardVO vo) {	
		
		String url = "jdbc:mysql://localhost:3306/bigdata05?serverTimezone=Asia/Seoul";  
		String user = "root";  
		String password = "bigdata";  

		StringBuffer qry = new StringBuffer();
		qry.append("UPDATE big_board SET bo_category = ? ,bo_title = ? , bo_content = ? ");
		qry.append("WHERE bo_num = ? ");
		String sql = qry.toString();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
			
		//글 등록 후 리스트로 이동
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			stmt = conn.prepareStatement(sql);		
			
			stmt.setString(1, vo.getBo_category());
			stmt.setString(2, vo.getBo_title());
			stmt.setString(3, vo.getBo_content());	
			stmt.setInt(4, vo.getBo_num());

			stmt.executeUpdate();
			int res = stmt.executeUpdate();
			if(res != 0){
				
			}
		}catch(Exception e){
			
		}finally{
			try{
				
				if(stmt != null) stmt.close();
				if(conn != null) conn.close(); 
			}catch(Exception e){
					
			}
		}
		
	}
	
	public List<BoardVO> readWithPaging(PagingDTO dto) {
		

		String url = "jdbc:mysql://localhost:3306/bigdata05?serverTimezone=Asia/Seoul";  
		String user = "root";  
		String password = "bigdata";  

		
		StringBuffer qry = new StringBuffer();
		qry.append("SELECT * FROM big_board ORDER BY bo_num DESC LIMIT " + dto.startPage() + ", " + dto.getPageRow());
		String sql = qry.toString();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<BoardVO> list = new ArrayList<>();
		
	    try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			stmt = conn.prepareStatement(sql);		
		
			rs = stmt.executeQuery();
		
			while(rs.next()){
				BoardVO board = new BoardVO();
				
				board.setBo_num(rs.getInt("bo_num"));
				board.setBo_category(rs.getString("bo_category"));
				board.setBo_title(rs.getString("bo_title"));
				board.setBo_content(rs.getString("bo_content"));
				board.setBo_mb_id(rs.getString("bo_mb_id"));
				board.setBo_mb_name(rs.getString("bo_mb_name"));
				board.setBo_hit(rs.getInt("bo_hit"));
				board.setBo_inputdate(rs.getDate("bo_inputdate"));
				board.setBo_ip(rs.getString("bo_ip"));
				
				list.add(board);
			}
			
		}catch(Exception e){
			
		}finally{
			try{
				if(rs !=null) rs.close();
				if(stmt !=null) stmt.close();
				if(conn !=null) conn.close();
			}catch(Exception e){
					
			}
		}
		
		return list;
		
	}
	
	public int totalCnt() {
		String url = "jdbc:mysql://localhost:3306/bigdata05?serverTimezone=Asia/Seoul";  
		String user = "root";  
		String password = "bigdata";  

		
		StringBuffer qry = new StringBuffer();
		qry.append("SELECT count(*) as cnt FROM big_board");
		String sql = qry.toString();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		 int total = 0;
		 
		    try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, password);
				
				stmt = conn.prepareStatement(sql);		
			
				rs = stmt.executeQuery();
			
				if(rs.next()){
					total = rs.getInt("cnt");
				}
				
			}catch(Exception e){
				
			}finally{
				try{
					if(rs !=null) rs.close();
					if(stmt !=null) stmt.close();
					if(conn !=null) conn.close();
				}catch(Exception e){
						
				}
			}
		return total;
	}
	
	
}
