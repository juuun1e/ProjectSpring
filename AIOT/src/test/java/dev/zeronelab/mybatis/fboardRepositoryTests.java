package dev.zeronelab.mybatis;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import dev.zeronelab.mybatis.entity.fboard;
import dev.zeronelab.mybatis.repository.fboardRepository;

@SpringBootTest
public class fboardRepositoryTests {

	@Autowired
	fboardRepository repository;

	@Test
	public void testClass() {
		System.out.println("/* testClass(): " + repository.getClass().getName());
	}

	@Test
	public void testInsertDummies() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			fboard fboard = dev.zeronelab.mybatis.entity.fboard.builder().content("Sample..." + i).build();
			repository.save(fboard);
		});
	}

	@Test
	public void testSelect() {
		// 데이터베이스에 존재하는 fno
		Long fno = 99L;
		Optional<fboard> result = repository.findById(fno);
		System.out.println("==================================");
		if (result.isPresent()) {
		}
		fboard fboard = result.get();
		System.out.println(fboard);
	}

	@Transactional
	@Test
	public void testSelect2() {
		// 데이터베이스에 존재하는 fno
		Long fno = 99L;
		Optional<fboard> result = repository.findById(fno);
		fboard fboard = result.orElse(null);
		System.out.println("==================================");
		System.out.println(fboard);
	}

	@Test
	public void testUpdate() {
		fboard fboard = dev.zeronelab.mybatis.entity.fboard.builder().fno(100L).content("Update Text").build();
		System.out.println(repository.save(fboard));
	}

	@Test
	public void testDelete() {
		Long fno = 100L;
		repository.deleteById(fno);
	}

	// 페이징처리
	@Test
	public void testPageDefault() {
		Pageable pageable = PageRequest.of(0, 10); // 1페이지 10개
		Page<fboard> result = repository.findAll(pageable);
		System.out.println(result);
		System.out.println("---------------------------------------");
		System.out.println("Total Pages: " + result.getTotalPages());
		System.out.println("Total Count: " + result.getTotalElements());
		System.out.println("Page Number: " + result.getNumber());
		System.out.println("Page Size: " + result.getSize());
		System.out.println("has next page?: " + result.hasNext());
		System.out.println("first page?: " + result.isFirst());
		System.out.println("---------------------------------------");
		for (fboard fboard : result.getContent()) {
			System.out.println(fboard);
		}
	}

	// 정렬조건
	@Test
	public void testSort() {
		Sort sort1 = Sort.by("fno").descending();
		Pageable pageable = PageRequest.of(0, 10, sort1);
		Page<fboard> result = repository.findAll(pageable);
		result.get().forEach(fboard -> {
			System.out.println(fboard);
		});
	}

	// 쿼리메서드 (메서드 이름 자체가 쿼리문)
	@Test
	public void testQueryMethods() {
		List<fboard> list = repository.findByFnoBetweenOrderByFnoDesc(70L, 80L);
		for (fboard fboard : list) {
			System.out.println(fboard);
		}
	}

	// 쿼리메서드 + 페이징
	@Test
	public void testQueryMethodWithPagable() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());
		Page<fboard> result = repository.findByFnoBetween(10L, 50L, pageable);
		result.get().forEach(fboard -> System.out.println(fboard));
	}

	@Commit
	@Transactional
	@Test
	public void testDeleteQueryMethods() {
		repository.deleteMemoByFnoLessThan(10L);
	}

//	@Transactional
//	@Query(value = "SELECT * FROM FBOARD ORDER BY FNO DESC", nativeQuery = true)
//	List<fboard> getListDesc();


}
