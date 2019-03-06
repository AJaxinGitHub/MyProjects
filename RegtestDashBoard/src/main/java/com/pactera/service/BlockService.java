package com.pactera.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.pactera.model.BlockCountAndTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pactera.component.BtcRpcComponent;
import com.pactera.model.Block;
import com.pactera.repository.BlockRepository;
import com.pactera.repository.BlocksNativeRepository;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

/**
 * @author: Pactera-lxz
 * @date: 2019-01-23
 * @Description: TODO
 */
@Service
@Transactional
public class BlockService {
	@Autowired
	private BlockRepository blockRepository;
	@Autowired
	private BtcRpcComponent btcRpcComponent;
	@Autowired
	private BlocksNativeRepository blocksNativeRepository;

	public int getBlockcount() {
		return btcRpcComponent.getBlockcount();
	}

	public String getBestBlockHash() {
		return btcRpcComponent.getBestBlockHash();
	}

	public BitcoindRpcClient.Block getBlock(int height){
		return btcRpcComponent.getBlock(height);
	}

	public List<String> genBlocks(int genNum){
		return btcRpcComponent.generateBlocks(genNum);
	}

	public String getBlockTime(int height) {
		Date date = btcRpcComponent.getBlock(height).time();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		String str_time = format.format(date);
		return str_time;
	}

	
	public Block getBlockEntity(int height) {
		Block block = new Block();
		
		Date date = btcRpcComponent.getBlock(height).time();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		String block_time = format.format(date);
		
		String block_hash = btcRpcComponent.getBlock(height).hash();
		String block_nextHash = btcRpcComponent.getBlock(height).nextHash();
		String block_previousHash = btcRpcComponent.getBlock(height).previousHash();
		
		block.setHeight(height);
		block.setBlockhash(block_hash);
		block.setTime(block_time);
		block.setPreviousblockhash(block_previousHash);
		block.setNextblockhash(block_nextHash);

		return block;
	}
	
	
	 public void saveBlock(Block block) {
		if(null!=block) {
			blockRepository.saveAndFlush(block);
		}
	}

	public int findBlockRecords(){
		int blockRecords = blocksNativeRepository.findBlockRecords();
		return blockRecords;
	}

	public void deleteLastBlockRec(){
		blocksNativeRepository.deleteLastBlockRec();
	}
	 
	 public List<BlockCountAndTime> findBlocksAndTime() {
		 List<BlockCountAndTime> list = blocksNativeRepository.findBlocksAndTime();
			return list;
	 }
	 
//	 public HashMap<String, Integer> findBlocksAndTime() {
//		 List<BlockCountAndTime> list = blocksNativeRepository.findBlocksAndTime();
////		 System.out.println(list.toString());
//		 HashMap<String,Integer> hashMap = new HashMap<>();
//		 
//		 for(int i=0;i<list.size();i++) {
//			 String key = list.get(i).getTime();
//			 int value = list.get(i).getBlocks();
//			 hashMap.put(key, value);
//		 }
//			return hashMap;
//	 }
	 
//	 public void findBlocksAndTime() {
//		 List<HashMap<String, Integer>> list = blockRepository.findBlocksAndTime();
//		 System.out.println(list.toString());
//		 HashMap<String,Integer> hashMap = new HashMap<>();
//		 
//		 for(int i=0;i<list.size();i++) {
//			 Set<String> keySet = list.get(i).keySet();
//			 for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
//					String key = it.next(); 
//					int value = list.get(i).get(key);
//					System.out.println(key+":"+value);
//					hashMap.put(key, value);
//				}
//		 }
//		 
//		 Set<Map.Entry<String, Integer>> entrySet = hashMap.entrySet();
//			for (Iterator<Map.Entry<String, Integer>> it = entrySet.iterator(); it.hasNext();) {
//				Map.Entry<String, Integer> me = it.next();
//				String key = me.getKey();
//				int value = me.getValue();
//				System.out.println(key+":"+value);
//			}
////			return hashMap;
//	 }
	 
	 
	 
}
