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
	        "AND (:#{#cri.searchType} IS NULL OR " +
	        "   (title LIKE '%' || :#{#cri.keyword} || '%' AND :#{#cri.searchType} = 't') OR " +
	        "   (content LIKE '%' || :#{#cri.keyword} || '%' AND :#{#cri.searchType} = 'c') OR " +
	        "   (writer LIKE '%' || :#{#cri.keyword} || '%' AND :#{#cri.searchType} = 'w') OR " +
	        "   (title LIKE '%' || :#{#cri.keyword} || '%' AND content LIKE '%' || :#{#cri.keyword} || '%' AND :#{#cri.searchType} = 'tc') OR " +
	        "   (content LIKE '%' || :#{#cri.keyword} || '%' AND writer LIKE '%' || :#{#cri.keyword} || '%' AND :#{#cri.searchType} = 'cw') OR " +
	        "   (title LIKE '%' || :#{#cri.keyword} || '%' AND content LIKE '%' || :#{#cri.keyword} || '%' AND writer LIKE '%' || :#{#cri.keyword} || '%' AND :#{#cri.searchType} = 'tcw')" +
	        ") " +
	        ") numbered " +
	        "WHERE rnum BETWEEN :#{#cri.pageStart + 1} AND :#{#cri.pageStart + #cri.perPageNum} " +
	        "ORDER BY rnum", nativeQuery = true)
	List<fboard> listSearch(@Param("cri") Criteria cri);

	
	@Query(value = "SELECT COUNT(*) FROM fboard", nativeQuery = true)
	int listSearchCount(SearchCriteria cri);
	
	@Query(value = "SELECT COUNT(*) FROM fboard WHERE fno > 0", nativeQuery = true)
	int selectBoardListCount(SearchCriteria cri);

}
