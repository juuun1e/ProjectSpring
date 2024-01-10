package dev.zeronelab.mybatis.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.zeronelab.mybatis.entity.fboard;
import dev.zeronelab.mybatis.vo.Criteria;
import dev.zeronelab.mybatis.vo.SearchCriteria;

public interface fboardRepository extends JpaRepository<fboard, Long> {

	/*
	 * @Query(value =
	 * "SELECT * FROM (SELECT fno, title, content, writer, regidate, viewCnt, replyCnt,"
	 * +
	 * "ROW_NUMBER() OVER (ORDER BY fno DESC, regidate DESC) AS rnum FROM fboard )numbered WHERE"
	 * + "numbered.rnum BETWEEN  #{pageStart} +1 AND (#{pageStart} + #{perPageNum})"
	 * + "ORDER BY numbered.rnum", nativeQuery = true) List<fboard>
	 * selectBoardList(Criteria cri) throws Exception;
	 */
	@Query(value = "SELECT * FROM (SELECT fno, title, content, writer, regidate, viewCnt, replyCnt,"
	        + "ROW_NUMBER() OVER (ORDER BY fno DESC, regidate DESC) AS rnum FROM fboard ) numbered WHERE "
	        + "numbered.rnum BETWEEN :#{#cri.pageStart + 1} AND (:#{#cri.pageStart + #cri.perPageNum})"
	        + "ORDER BY numbered.rnum", nativeQuery = true)
	List<fboard> selectBoardList(@Param("cri") Criteria cri) throws Exception;

	@Query(value = "SELECT * FROM (SELECT fno, title, content, writer, regidate, viewCnt, replyCnt, " +
	        "ROW_NUMBER() OVER (ORDER BY fno DESC, regidate DESC) AS rnum FROM fboard " +
	        "WHERE fno > 0 " +
	        "<if test='cri.searchType == \"t\"'>AND (title LIKE '%' || :cri.keyword || '%') </if>" +
	        "<if test='cri.searchType == \"c\"'>AND (content LIKE '%' || :cri.keyword || '%') </if>" +
	        "<if test='cri.searchType == \"w\"'>AND (writer LIKE '%' || :cri.keyword || '%') </if>" +
	        "<if test='cri.searchType == \"tc\"'>AND (title LIKE '%' || :cri.keyword || '%' OR content LIKE '%' || :cri.keyword || '%') </if>" +
	        "<if test='cri.searchType == \"cw\"'>AND (content LIKE '%' || :cri.keyword || '%' OR writer LIKE '%' || :cri.keyword || '%') </if>" +
	        "<if test='cri.searchType == \"tcw\"'>AND (title LIKE '%' || :cri.keyword || '%' OR content LIKE '%' || :cri.keyword || '%' OR writer LIKE '%' || :cri.keyword || '%') </if>" +
	        ") " +
	        "WHERE rnum BETWEEN :cri.pageStart + 1 AND (:cri.pageStart + :cri.perPageNum) " +
	        "ORDER BY rnum", nativeQuery = true)
	List<fboard> listSearch(@Param("cri") Criteria cri);

	
	@Query(value = "SELECT COUNT(*) FROM fboard", nativeQuery = true)
	int listSearchCount(SearchCriteria cri);
	
	@Query(value = "SELECT COUNT(*) FROM fboard WHERE fno > 0", nativeQuery = true)
	int selectBoardListCount(SearchCriteria cri);

}
