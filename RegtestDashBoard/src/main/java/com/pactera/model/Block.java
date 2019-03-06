package com.pactera.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author: Pactera-lxz
 * @date: 2019-01-22
 * @Description: TODO
 */
@Entity
@Table(name = "block")
public class Block implements Serializable{
	@Id
//	@GeneratedValue(generator = "uuid2")
//	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private int height;
	private String blockhash;
	private String previousblockhash;
	private String nextblockhash;
	private String time;
	
	
	public String getBlockhash() {
		return blockhash;
	}
	public void setBlockhash(String blockhash) {
		this.blockhash = blockhash;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getPreviousblockhash() {
		return previousblockhash;
	}
	public void setPreviousblockhash(String previousblockhash) {
		this.previousblockhash = previousblockhash;
	}
	public String getNextblockhash() {
		return nextblockhash;
	}
	public void setNextblockhash(String nextblockhash) {
		this.nextblockhash = nextblockhash;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Block [blockhash=" + blockhash + ", height=" + height + ", previousblockhash=" + previousblockhash + ", nextblockhash=" + nextblockhash + ", time=" + time
				+ "]";
	}
//	/**
//	 * 区块结构
//	 * @author aaron
//	 */
//	public class Block {
//		/**
//		 * 区块索引号
//		 */
//		private int index;
//		/**
//		 * 当前区块的hash值,区块唯一标识
//		 */
//		private String hash;
//		/**
//		 * 生成区块的时间戳
//		 */
//		private long timestamp;
//		/**
//		 * 当前区块的交易集合
//		 */
//		private List<Transaction> transactions;
//		/**
//		 * 工作量证明，计算正确hash值的次数
//		 */
//		private int nonce;
//		/**
//		 * 前一个区块的hash值
//		 */
//		private String previousHash;
//
//		public Block() {
//			super();
//		}
//
//		public Block(int index, long timestamp, List<Transaction> transactions, int nonce, String previousHash, String hash) {
//			super();
//			this.index = index;
//			this.timestamp = timestamp;
//			this.transactions = transactions;
//			this.nonce = nonce;
//			this.previousHash = previousHash;
//			this.hash = hash;
//		}
//	}

}
