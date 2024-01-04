package dev.zeronelab.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.zeronelab.mybatis.dto.nBoardDTO;
import dev.zeronelab.mybatis.vo.Criteria;
import dev.zeronelab.mybatis.vo.SearchCriteria;
import dev.zeronelab.mybatis.vo.nBoardVO;

@Mapper
public interface nBoardMapper {
	List<nBoardVO> selectBoardList(Criteria cri) throws Exception;

	List<nBoardVO> listSearch(Criteria cri);

	int listSearchCount(SearchCriteria cri);
	
	int selectBoardListCount(SearchCriteria cri);

	void write(nBoardDTO vo);
	
	List<nBoardDTO> read(int bNo);
	
	void updateCounts(Integer bNo);
	
	void update(@Param("title") String title, @Param("content") String content, @Param("bNo") Integer bNo);

	void delete(int bNo);
	
	public void addAttach(String imgName,String uuid,String path)throws Exception;
	
	public List<String> getAttach(Integer bNo)throws Exception;
	

	public void replaceAttach(String imgName,String uuid,String path,Integer bNo)throws Exception;
	
	public void deleteAttach(Integer bNo)throws Exception;
}
  