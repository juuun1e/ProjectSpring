package dev.zeronelab.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.zeronelab.mybatis.vo.Criteria;
import dev.zeronelab.mybatis.vo.nReplyVO;

@Mapper
public interface nReplyMapper {
  public List<nReplyVO> list(@Param("bNo")Integer bNo) throws Exception;

  public void addReply(nReplyVO vo) throws Exception;

  public void modify(nReplyVO vo) throws Exception;

  public void remove(Integer rNo) throws Exception;

  public List<nReplyVO> listPage(@Param("bNo")Integer bNo,@Param("cri") Criteria cri) throws Exception;

  public int count(Integer bNo) throws Exception;

}