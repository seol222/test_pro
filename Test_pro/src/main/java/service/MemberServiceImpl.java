package service;

import java.util.List;

import mapper.MemberMapper;
import model.MemberVO;

public class MemberServiceImpl implements MemberService {

	@Override
	public void create(MemberVO vo) {
		MemberMapper mapper = new MemberMapper();
		mapper.create(vo);	
	}

	@Override
	public List<MemberVO> read() {
		MemberMapper mapper = new MemberMapper();		
		return mapper.read();	
	}

	@Override
	public List<MemberVO> readout(String keyword) {
		MemberMapper mapper = new MemberMapper();		
		return mapper.readout(keyword);	
	}

}
