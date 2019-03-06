package com.pactera.component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient.WalletInfo;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient.Block;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient.RawTransaction;

/**
 * @author: Pactera-lxz
 * @date: 2019-01-10
 * @Description: TODO
 */
@Component
public class BtcRpcComponent {
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
     * @Description: get total block amount from the chain of regtest
     * @param:
     * @return: int
     */
    public int getBlockcount() {
        int blockCount = getBitcoinRpc().getBlockCount();
        return blockCount;
    }

    /**
     * @Description: get total balance
     * @param:
     * @return: double
     */
    public double getTotalBalance() {
        double toatlBalance = getBitcoinRpc().getBalance();
        return toatlBalance;
    }
    /**
     * @Description: get account balance
     * @param:
     * @return: double
     */
    public double getAccountBalance(String account){
        double accountBalance = getBitcoinRpc().getBalance(account);
        return accountBalance;
    }

    /**
     * @Description: get the last block hash
     * @param:
     * @return: String
     */
    public String getBestBlockHash() {
        String bestBlockHash = getBitcoinRpc().getBestBlockHash();
        return bestBlockHash;
    }

    /**
     * @Description: get the block info with the block height,the Block entity is not the native Block entity class
     * @param: @param height
     * @return: Block
     */
    public Block getBlock(int height) {
        Block block = getBitcoinRpc().getBlock(height);
        return block;
    }

    /**
     * @Description: generate blocks
     * @param: @param num
     * @return: List<String>
     */
    public List<String> generateBlocks(int genNum) {
        List<String> genBlocksHash = getBitcoinRpc().generate(genNum);
        return genBlocksHash;
    }

    /**
     * @Description: get raw transaction with txid
     * @param: @param txId
     * @return: RawTransaction
     */
    public RawTransaction getRawtransaction(String txId) {
        RawTransaction rawTransaction = getBitcoinRpc().getRawTransaction(txId);
        return rawTransaction;
//		getBitcoinRpc().createRawTransaction()

    }

    /**
     * @Description: send to address amount
     * @param toAddress
     * @param amount
     * @param comment
     * @return String
     */
    public String sendToAddress(String toAddress, double amount, String comment) {
        return getBitcoinRpc().sendToAddress(toAddress, amount, comment);
    }

    /**
     * @Description:
     * @param address
     * @return
     */
    public boolean validateAddress(String address){
       return getBitcoinRpc().validateAddress(address).isValid();
    }

    /**
     * @Description: get wallet info
     * @return WalletInfo
     */
    public WalletInfo getWalletInfo(){
        WalletInfo walletInfo = getBitcoinRpc().getWalletInfo();
        return walletInfo;
    }

    public Map<String, Number> listAccounts(){
        Map<String, Number> listAccounts = getBitcoinRpc().listAccounts();
        return listAccounts;
    }

    public List<String> getAddressesByAccount(String account){
        List<String> addresses = getBitcoinRpc().getAddressesByAccount(account);
        return addresses;
    }


//	public static void main(String[] args) {
//		BtcRpcComponent btcRpcComponent = new BtcRpcComponent();
//		Date date = btcRpcComponent.getBlock(120).time();
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String str = format.format(date);
//		System.out.println(str);

//        BtcRpcComponent btcRpcComponent = new BtcRpcComponent();
//        System.out.println(btcRpcComponent.validateAddress("2N2fypk1aghxNx7dmFsNStoKannu4WEe7bb"));
//    }


}
