package com.pactera.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pactera.component.BtcRpcComponent;
//import com.pactera.model.Block;

import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient.Block;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient.RawTransaction;

/**
 * @author: Pactera-lxz
 * @date: 2019-01-22
 * @Description: TODO
 */
@Service
public class BtcRpcService {
	@Autowired
	private BtcRpcComponent btcRpcComponent;

	public double getTotalBalance() {
		return btcRpcComponent.getTotalBalance();
	}

	public RawTransaction getRawtransaction(String txId) {
		return btcRpcComponent.getRawtransaction(txId);
	}


}
