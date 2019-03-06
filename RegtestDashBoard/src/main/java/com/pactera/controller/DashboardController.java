package com.pactera.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import com.pactera.model.Block;
import com.pactera.model.BlockCountAndTime;
import com.pactera.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pactera.service.BlockService;
import com.pactera.service.BtcRpcService;

import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient.RawTransaction;

/**
 * @author: Pactera-lxz
 * @date: 2019-01-10
 * @Description: TODO
 */
@Controller
public class DashboardController {
    @Autowired
    private BtcRpcService btcRpcService;
    @Autowired
    private BlockService blockService;

    //sign in, sign up
    @RequestMapping(value = {"/", "/index"})
    public String index(){
        return "index";
    }
    //initial
    @RequestMapping("/dashboard")
    public String dashboard(Model model) throws UnknownHostException {
        model.addAttribute("LocalHostAddress", InetAddress.getLocalHost().getHostAddress());
        model.addAttribute("BlockCount", blockService.getBlockcount());
        model.addAttribute("TotalBalance", btcRpcService.getTotalBalance());
        model.addAttribute("BestBlockHash", blockService.getBestBlockHash());
//		String currentPrice = new BTCPriceUtil().getSearchCurrentPrice();
        model.addAttribute("CurrentPrice", 3558.226);

        //update the blockInfo in the database
        updateBlock();

        return "dashboard";
    }

    @GetMapping("/genBlock")
    @ResponseBody
    public List<String> genBlock(@RequestParam int genNum){
        return blockService.genBlocks(genNum);
    }

    @GetMapping("/blocksAndTime")
    @ResponseBody
    public List<BlockCountAndTime> getBlocksAndTime() {
        return blockService.findBlocksAndTime();
    }

    @GetMapping("/blockInfo")
    @ResponseBody
    public BitcoindRpcClient.Block getBlockInfo(@RequestParam int blockHeight) {
        return blockService.getBlock(blockHeight);
    }

    @GetMapping("/rawTransaction")
    @ResponseBody
    public RawTransaction getRawTransaction(@RequestParam String txId) {
        RawTransaction transaction = btcRpcService.getRawtransaction(txId);
//		5866a9c650751a63ab31e41f4de3d3b48dd0d7fbaca9fdff089145ae45ab2f5a
        return transaction;
    }

    /**
     * update blocks data
     * Q:这边如果切换链怎么办
     */
    public void updateBlock(){
        //更新block数据库数据
        int blockRecords = blockService.findBlockRecords();
        //第一次插区块数据，要把创世区块插进去
        if(blockRecords!=0){
            blockRecords-=1;
            //每次先删除最后一条记录
            blockService.deleteLastBlockRec();
        }
        for (int i = blockRecords; i <= blockService.getBlockcount(); i++) {
            Block block = blockService.getBlockEntity(i);
            blockService.saveBlock(block);
        }
    }

    @RequestMapping("/echartsTest")
    public String testEcharts() {
        return "echartsTest";
    }

}
