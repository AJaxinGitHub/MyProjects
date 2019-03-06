package com.pactera.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pactera.model.BlockCountAndTime;

/**
 * @author: Pactera-lxz
 * @date: 2019-01-23
 * @Description: TODO
 */
@Repository
public interface BlocksNativeRepository extends JpaRepository<BlockCountAndTime, String>{

	@Query(value="SELECT time,COUNT(*)blockcount\r\n" + 
			"FROM block\r\n" + 
			"GROUP BY time\r\n" + 
			"ORDER BY time",nativeQuery=true)
	List<BlockCountAndTime> findBlocksAndTime ();

//		@Query(value="SELECT time,COUNT(*)blockcount\r\n" +
//			"FROM block\r\n" +
//			"GROUP BY time\r\n" +
//			"ORDER BY time",nativeQuery=true)
//	List<HashMap<String, Integer>> findBlocksAndTime ();

	@Query(value = "SELECT COUNT(*) FROM block",nativeQuery = true)
	int findBlockRecords();

	@Modifying
	@Query(value = "DELETE FROM block WHERE height=" +
			"(SELECT MAX(height) FROM BLOCK)",nativeQuery = true)
	void deleteLastBlockRec();
}
