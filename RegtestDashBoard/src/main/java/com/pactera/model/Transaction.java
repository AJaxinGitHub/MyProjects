package com.pactera.model;

/**
 * @author ajaxin
 * @discription TODO
 * @title RegtestDashBoard
 * @date 2019/2/14 - 19:03
 */
public class Transaction {
    /**
     * 交易唯一标识
     */
    private String id;
    /**
     * 交易发送方钱包地址
     */
    private String sender;
    /**
     * 交易接收方钱包地址
     */
    private String recipient;
    /**
     * 交易金额
     */
    private int amount;

    public Transaction() {
        super();
    }

    public Transaction(String id, String sender, String recipient, int amount) {
        super();
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }
}

