package com.pactera.repository;

import javax.persistence.PostUpdate;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pactera.model.Block;

/**
 * @author: Pactera-lxz
 * @date: 2019-01-23
 * @Description: TODO
 */
@Repository
public interface Test2Repository extends JpaRepository<Block, String>{
	@Query(value = "SELECT b.time FROM block b WHERE b.height=?1 ", nativeQuery = true)
	String findTimeByHeight(int height);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO block VALUES(?1,?2)", nativeQuery = true)
	void persisBlock(int height, String time);
}
