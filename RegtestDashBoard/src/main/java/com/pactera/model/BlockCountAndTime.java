package com.pactera.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: Pactera-lxz
 * @date: 2019-01-23
 * @Description: TODO
 */
@Entity
public class BlockCountAndTime {
	@Id
	private String time;
	private int blockcount;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public int getBlockcount() {
		return blockcount;
	}
	public void setBlockcount(int blockcount) {
		this.blockcount = blockcount;
	}
	@Override
	public String toString() {
		return "BlockCountAndTime [time=" + time + ", blockcount=" + blockcount + "]";
	}
	
	

}
