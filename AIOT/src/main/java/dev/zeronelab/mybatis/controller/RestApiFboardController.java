package dev.zeronelab.mybatis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.zeronelab.mybatis.entity.fboard;
import dev.zeronelab.mybatis.repository.fboardRepository;
import dev.zeronelab.mybatis.vo.PageMaker;
import dev.zeronelab.mybatis.vo.SearchCriteria;

@RestController
@RequestMapping("/api/fboard")
public class RestApiFboardController {

	private static final Logger logger = LoggerFactory.getLogger(RestApiFboardController.class);

	@Autowired
	private fboardRepository fr;

	// 게시글 페이지 리스트
	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> boardListPage(@PathVariable("page") Integer page,
			@ModelAttribute(value = "cri") SearchCriteria cri) {
		ResponseEntity<Map<String, Object>> entity = null;

		try {// 검색 조건이 없으면 새로운 SearchCriteria 객체를 생성하여 사용
			if (cri == null) {
				cri = new SearchCriteria();
			}
			cri.setPage(page);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);

			Map<String, Object> map = new HashMap<>();
			List<fboard> list;

			// 검색 조건이 있는 경우와 없는 경우를 구분하여 데이터를 가져옴
			int boardCount;
			if (cri.hasSearchCondition()) {
				// 검색 조건이 있는 경우
				list = fr.listSearch(cri);
				boardCount = fr.listSearchCount(cri);
			} else {
				// 검색 조건이 없는 경우
				list = fr.selectBoardList(cri);
				boardCount = fr.selectBoardListCount(cri);

			}
			pageMaker.setTotalCount(boardCount);

			map.put("list", list);
			map.put("pageMaker", pageMaker);

			entity = new ResponseEntity<>(map, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}