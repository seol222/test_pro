package service;

import java.util.List;

import model.BoardVO;
import model.PagingDTO;

public interface BoardService {

	public void create(BoardVO vo);
	public void update(BoardVO vo);
	public List<BoardVO> readWithPaging(PagingDTO dto);
	public int totalCnt();
}
