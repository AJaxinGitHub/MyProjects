package com.pactera.controller;

import com.pactera.service.BtcRpcService;
import com.pactera.service.TransactionService;
import com.pactera.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient.WalletInfo;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author ajaxin
 * @discription TODO
 * @title RegtestDashBoard
 * @date 2019/2/27 - 10:39
 */
@Controller
public class WalletController {
    @Autowired
    private BtcRpcService btcRpcService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestParam String username, String password) {
        //前后台校验
        System.out.println(username + " " + password);
        return "OK";
    }

//    @GetMapping("/wallet")
//    public String wallet(HttpServletRequest request) {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        System.out.println(username + " " + password);
//        return "wallet";
//    }

    @GetMapping("/wallet")
    public String wallet(Model model,HttpServletRequest request) throws UnknownHostException {
        /*initial info*/
        model.addAttribute("LocalHostAddress", InetAddress.getLocalHost().getHostAddress());
        model.addAttribute("TotalBalance", btcRpcService.getTotalBalance());

        System.out.println(request.getParameter("username"));

        return "wallet";
    }

    @GetMapping("/listWalletAccountsAndBalance")
    @ResponseBody
    public Map<String, Number> listWalletAccountsAndBalance() {
        return walletService.listAccounts();
    }

    @GetMapping("/getAddressesByAccount")
    @ResponseBody
    public List<String> getAddressesByAccount(@RequestParam String account) {
        return walletService.getAddressesByAccount(account);
    }

    @GetMapping("/getWalletInfo")
    @ResponseBody
    public WalletInfo getWalletInfo() {
        return walletService.getWalletInfo();
    }

    @GetMapping("/listAddressGroupings")
    @ResponseBody
    public ArrayList listAddressGroupings() {
        return walletService.listAddressGroupings();
    }

    @GetMapping("/sendToAddress")
    @ResponseBody
    public String sendToAddress(@RequestParam String toAddress, double amount, String comment) {
        String txId = transactionService.sendToAddress(toAddress, amount, comment);
        //test address:    2NEDg8X4oRz9tEhfK19Uq6zvFoJZTLqaP83
        return txId;
    }


    @GetMapping("/test")
    @ResponseBody
    public ArrayList test() {
        return walletService.listAddressGroupings();
    }
}
