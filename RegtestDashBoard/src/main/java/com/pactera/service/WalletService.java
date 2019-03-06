package com.pactera.service;

import com.pactera.component.BtcRpcComponent;
import com.pactera.component.BtcRpcNativeComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient.WalletInfo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ajaxin
 * @discription TODO
 * @title RegtestDashBoard
 * @date 2019/2/27 - 10:41
 */
@Service
@Transactional
public class WalletService {
    @Autowired
    private BtcRpcComponent btcRpcComponent;
    @Autowired
    private BtcRpcNativeComponent btcRpcNativeComponent;

    public WalletInfo getWalletInfo() {
        return btcRpcComponent.getWalletInfo();
    }


    public List<String> listLabels() {
        return btcRpcNativeComponent.listLabels();
    }

    public ArrayList listAddressGroupings(){
        return btcRpcNativeComponent.listAddressGroupings();
    }

    public Map<String, Number> listAccounts() {
        return btcRpcComponent.listAccounts();
    }

    public List<String> getAddressesByAccount(String account){
        return btcRpcComponent.getAddressesByAccount(account);
    }
}
