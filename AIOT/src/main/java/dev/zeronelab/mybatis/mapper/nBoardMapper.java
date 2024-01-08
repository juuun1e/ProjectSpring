package dev.zeronelab.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import dev.zeronelab.mybatis.dto.nBoardDTO;
import dev.zeronelab.mybatis.dto.nBoardImageDTO;
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

	// List<nBoardVO> read(int bNo);

	nBoardDTO read(int bNo);

	void updateCounts(Integer bNo);

	void update(@Param("title") String title, @Param("content") String content, @Param("bNo") String bNo);

	void delete(int bNo);

	public void addAttach(@Param("imgName") String imgName, @Param("uuid") String uuid, @Param("path") String path)
			throws Exception;

	public void replaceAttach(@Param("imgName") String imgName, @Param("uuid") String uuid, @Param("path") String path,
			@Param("bNo") String bNo) throws Exception;

	public void deleteAttach(String bNo) throws Exception;

	List<nBoardImageDTO> getImageDTOList(int bNo);

	public void updateReplyCnt(String bNo, int amount) throws Exception;
}
