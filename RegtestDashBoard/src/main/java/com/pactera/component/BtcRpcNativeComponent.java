package com.pactera.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ajaxin
 * @discription TODO
 * @title RegtestDashBoard
 * @date 2019/2/27 - 14:25
 */
@Component
public class BtcRpcNativeComponent {
    private static Log log = LogFactory.getLog(BtcRpcComponent.class);

    @Value("${custom.rpc.userName}")
    private String userName;
    @Value("${custom.rpc.passWord}")
    private String passWord;
    @Value("${custom.rpc.host}")
    private String host;
    @Value("${custom.rpc.port}")
    private String port;

    //main test
//	private String userName="userrpc";
//	private String passWord="userpassword";
//	private String host="192.168.0.3";
//	private String port="18443";

    private static BitcoinJSONRPCClient bitcoinClient;

    /**
     * @Description: get bitcoin json-rpc connection
     * @param:
     * @return: BitcoinJSONRPCClient
     */
    private BitcoinJSONRPCClient getBitcoinRpc() {
//		log.info("RPC:"+"http://" + userName + ':' + passWord + "@" + host + ":" + port + "/");
        if (bitcoinClient == null) {
            URL url = null;
            try {
                url = new URL("http://" + userName + ':' + passWord + "@" + host + ":" + port + "/");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            bitcoinClient = new BitcoinJSONRPCClient(url);
        }
        return bitcoinClient;
    }


    /**
     * @Description: list labels
     * @return List
     */
    public List<String> listLabels(){
        List<String> labelsList = (List)getBitcoinRpc().query("listlabels");
        return labelsList;
    }

    public ArrayList listAddressGroupings(){
        return (ArrayList) getBitcoinRpc().query("listaddressgroupings");
    }


}
