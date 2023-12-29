package dev.zeronelab.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.zeronelab.mybatis.vo.SearchCriteria;
import dev.zeronelab.mybatis.vo.nBoardVO;

@Mapper
public interface nBoardMapper {
	List<nBoardVO> selectBoardList() throws Exception;

	List<nBoardVO> listSearch(SearchCriteria cri);

	int listSearchCount(SearchCriteria cri);

	void write(nBoardVO vo);

	List<nBoardVO> read(int bNo);

	void delete(int bNo);
	
	void updateCounts(Integer bNo);
	
	//public void update(nBoardVO board,Integer bNo) throws Exception;
	
	// void update(@Param("vo")nBoardVO vo);
	
	void update(@Param("title") String title, @Param("content") String content, @Param("bNo") String bNo);

}
