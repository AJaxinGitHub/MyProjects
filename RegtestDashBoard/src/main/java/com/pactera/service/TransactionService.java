package com.pactera.service;

import com.pactera.component.BtcRpcComponent;
import com.pactera.repository.BlockRepository;
import com.pactera.repository.BlocksNativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author ajaxin
 * @discription TODO
 * @title RegtestDashBoard
 * @date 2019/2/13 - 19:46
 */
@Service
@Transactional
public class TransactionService {
    @Autowired
    private BlockRepository blockRepository;
    @Autowired
    private BtcRpcComponent btcRpcComponent;
    @Autowired
    private BlocksNativeRepository blocksNativeRepository;

    public String sendToAddress(String toAddress, double amount, String comment){
        return btcRpcComponent.sendToAddress(toAddress,amount,comment);
    }

}
