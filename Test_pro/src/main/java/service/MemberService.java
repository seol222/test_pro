package service;

import java.util.List;

import model.MemberVO;
//인터페이스 안에는 추상메소드나 상수만 들어갈수 있음

public interface MemberService {

	public void create(MemberVO vo);
	public List<MemberVO> read();
	public List<MemberVO> readout(String keyword);
	
}
