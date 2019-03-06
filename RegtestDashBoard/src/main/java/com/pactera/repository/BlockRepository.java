package com.pactera.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.pactera.model.Block;
/**
 * @author: Pactera-lxz
 * @date: 2019-01-23
 * @Description: TODO
 */
@Repository
public interface BlockRepository extends JpaRepository<Block, String>{

	@Modifying
	<S extends Block> S saveAndFlush(S entity);
}
