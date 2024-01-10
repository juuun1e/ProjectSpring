package dev.zeronelab.mybatis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.zeronelab.mybatis.entity.fboard;

public interface fboardRepository extends JpaRepository<fboard, Long> {
	
	List<fboard> findByFnoBetweenOrderByFnoDesc(Long from, Long to);
	
	Page<fboard> findByFnoBetween(Long from, Long to, Pageable pageable);
	
	void deleteMemoByFnoLessThan(Long num);
	
	}
