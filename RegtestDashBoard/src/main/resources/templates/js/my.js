$(function () {
    initData()

    /*初始化*/
    function initData() {
        /*钱包信息初始化*/
        $.ajax({
            url: "/getWalletInfo",
            type: "get",
            success: function (data) {
                var walletInfoArray = [
                    {key: "walletname(默认为空)", value: data.m.walletname},
                    {key: "walletversion", value: data.m.walletversion},
                    {key: "balance", value: data.m.balance},
                    {key: "unconfirmed_balance", value: data.m.unconfirmed_balance},
                    {key: "immature_balance", value: data.m.immature_balance},
                    {key: "txcount", value: data.m.txcount},
                    {key: "keypoolsize", value: data.m.keypoolsize},
                    {key: "paytxfee", value: data.m.paytxfee},
                    {key: "hdseedid", value: data.m.hdseedid},
                    {key: "private_keys_enabled", value: data.m.private_keys_enabled}
                ]
                $('#unconfirmed_balance').html(walletInfoArray[3].value)
                $('#immature_balance').html(walletInfoArray[4].value)
                $('#txcount').html(walletInfoArray[5].value)
                var walletInfoTable = $('#table-walletInfo')
                 $('#table-walletInfo>tbody').remove()
                walletInfoTable.append('<tbody>' + '</tbody>')
                var walletInfoTable_tbody = $('#table-walletInfo>tbody')
                for (var i = 0; i < walletInfoArray.length; i++) {
                    var item = walletInfoArray[i]
                    walletInfoTable_tbody.append('<tr>' +
                        '<td>' + item.key + '</td>' +
                        '<td>' + item.value + '</td>' + '</tr>')
                }
            },
            error: function () {
                alert('返回钱包信息错误!')
            }
        });
        /*----------------------------------------------*/


        /*返回所有地址及余额信息 */
        var defAccountAddrArr = [];
        var defAccountBalArr = [];
        var customAccountAccArr = [];
        var customAccountAddrArr = [];
        var customAccountBalArr = [];
        $.ajax({
            url: "/listAddressGroupings",
            type: "get",
            async: false,
            success: function (data) {
                var groupingsArr = data;
                for (var i in groupingsArr) {
                    for (var j in groupingsArr[i]) {
                        if (groupingsArr[i][j].length == 2) {
                            defAccountAddrArr.push(groupingsArr[i][j][0])
                            defAccountBalArr.push(groupingsArr[i][j][1])
                        }
                        if (groupingsArr[i][j].length == 3) {
                            customAccountAddrArr.push(groupingsArr[i][j][0])
                            customAccountBalArr.push(groupingsArr[i][j][1])
                            customAccountAccArr.push(groupingsArr[i][j][2])
                        }
                    }
                }
            },
            error: function () {
                alert('返回所有地址信息异常!')
            }
        });
        /*----------------------------------------------*/


        var accountsArr = [];
        var accountsBalanceArr = [];

        /*整合钱包账户地址余额数据*/
        $.ajax({
            url: "/listWalletAccountsAndBalance",
            type: "GET",
            async: false,
            success: function (data1) {
                var receivedAddressesTable = $('#table-received-addresses')
                $('#table-received-addresses>tbody').remove()
                receivedAddressesTable.append('<tbody>' + '</tbody>')
                var receivedAddresses_tbody = $('#table-received-addresses>tbody')
                var map = data1;
                for (var key in map) {
                    // alert("键：" + key + ",值 ："+map[key]);
                    accountsArr.push(key);
                    accountsBalanceArr.push(map[key]);
                }
                /*嵌套ajax的使用，*/
                //外层ajax响应成功，就再次发送ajax请求到第二个地址
                /*外层的 AJAX 和内层的 AJAX 的 async 属性都要设置为 false, 如果不这样的话, 两次请求都是异步的, 可能会导致数据获取不到, 但是也有一个弊端, 因为同步, 在异步请求时, 浏览器是锁死状态, 不能进行其他的操作.*/
                var foldmenuDiv = $('#foldmenu')
                for (var index in accountsArr) {
                    //添加所有账户
                    foldmenuDiv.append('<ul id="foldmenuUl' + index + '"><span>Account:"<font color="#dc143c">' + accountsArr[index] + '</font>"===================================="<font color="#dc143c">' + '$' + accountsBalanceArr[index] + '.00' + '</font>"</span>')
                    $.ajax({
                        type: "GET",
                        url: "/getAddressesByAccount",
                        data: {"account": accountsArr[index]},
                        async: false,
                        success: function (data2) {
                            var foldmenuUl = $('#foldmenuUl' + index)
                            var addrList = data2;
                            //将默认账户地址和余额加进去
                            if (accountsArr[index].length == 0) {
                                for (var m in defAccountAddrArr) {
                                    foldmenuUl.append('<li><a href="#">' + defAccountAddrArr[m] + '<font color="#8B8986">=================</font>' + '"<font color="#dc143c">$' + defAccountBalArr[m] + '.00 ' + '</font>" ' + '</a></li>')
                                }
                            }
                            //添加账户对应的地址
                            for (var j in addrList) {
                                var k = 0;
                                for (var n in customAccountAddrArr) {
                                    if (addrList[j] == customAccountAddrArr[n]) {
                                        foldmenuUl.append('<li><a href="#">' + addrList[j] + '<font color="#8B8986">=================</font>' + '"<font color="#dc143c">$' + customAccountBalArr[n] + '.00 ' + '</font>" ' + '</a></li>')
                                    } else k++;
                                    if (k == customAccountAddrArr.length) foldmenuUl.append('<li><a href="#">' + addrList[j] + '</a></li>')
                                }
                                //列出所有收款地址
                                receivedAddresses_tbody.append('<tr>' +
                                    '<td>' + accountsArr[index] + '</td>' +
                                    '<td>' + addrList[j] + '</td>' + '</tr>')

                            }
                            //这边过于繁杂，有时间可以放后台处理这些逻辑，并且拿数据的方式，逻辑多，不应该用什么嵌套的ajax



                        },
                        error: function () {
                            alert('根据账户取地址有问题！')
                        }
                    });
                }
                foldmenuDiv.append('<ul></ul>')
            },
            error: function () {
                alert('返回钱包账户及余额数据错误呀!')
            }
        });

        /*钱包账户地址余额初始化构造 架子*/
        myMenu = new FOLDMenu("foldmenu", true);
        myMenu.init();






    }


});


/*下面这些函数有报错，导致一整个加载都执行不到预加载*/
/*交易记录查询*/
$("#btn-txid").click(function () {
    var txId = $("#txId").val();
    if (null != txId && "" != txId && " " != txId) {
        //alert(txId);
        $.ajax({
            url: "/rawTransaction",
            data: {"txId": txId},
            type: "get",
            success: function (data) {
                //alert(data.m.version)
                var transactionArray = [
                    {key: "txid", value: data.m.txid},
                    {key: "version", value: data.m.version},
                    {key: "vin", value: data.m.version},
                    {key: "vout", value: data.m.version},
                    {key: "blockhash", value: data.m.blockhash},
                    {key: "confirmations", value: data.m.confirmations},
                    {key: "blocktime", value: data.m.blocktime}
                ]
                var transactionTable = $('#table-transaction')
                $('#table-transaction>tbody').remove()
                transactionTable.append('<tbody>' + '</tbody>')
                var transactionTable_tbody = $('#table-transaction>tbody')
                for (var i = 0; i < transactionArray.length; i++) {
                    var item = transactionArray[i]
                    transactionTable_tbody.append('<tr>' +
                        '<td>' + item.key + '</td>' +
                        '<td>' + item.value + '</td>' + '</tr>')
                }
            },
            error: function () {
                alert('没有这条交易记录，请检您输入的txid是否有效!')
            }
        });
    }
});


/*区块信息查询*/
$("#btn-block").click(function () {
    var blockHeight = $("#block-height").val();
    //这边要有一个正则的判断
    if (null != blockHeight && "" != blockHeight && " " != blockHeight && blockHeight >= 0) {
        $.ajax({
            url: "/blockInfo",
            data: {"blockHeight": blockHeight},
            type: "get",
            success: function (data) {
                // alert(data.m.hash)
                //所有的区块信息都能拿出来
                $("#block-hash").val(data.m.hash)
            },
            error: function () {
                alert('没有这个区块信息，请检您输入的区块高度是否有效!')
            }
        });
    }
});

$("#btn-block-pre").click(function () {
    var a = $("#block-height").val()
    if (a > 0)
        a--;
    $("#block-height").val(a);
});
$("#btn-block-next").click(function () {
    var b = $("#block-height").val()
    b++;
    $("#block-height").val(b);
});


/*挖矿*/
$("#btn-gen-block").click(function () {
    var genNum = $("#gen-block-count").val();
    if (null != genNum && "" != genNum && " " != genNum && "0" != genNum) {
        $.ajax({
            url: "/genBlock",
            data: {"genNum": genNum},
            type: "get",
            success: function (data) {
                alert("成功挖矿")
                alert(data)
            },
            error: function () {
                alert('挖矿失败，请检查您输入的数目是否是有效正整数！')
            }
        });
    }
});

/*转账*/
/*地址不为空，金额大于0，备注可以为空*/
$("#btn-send-to").click(function () {
    var toAddress = $("#toAddress").val();
    var amount = $("#amount").val();
    var comment = $("#comment").val();

    if (null != toAddress && "" != toAddress && " " != toAddress) {
        $.ajax({
            url: "/sendToAddress",
            data: {
                "toAddress": toAddress,
                "amount": amount,
                "commnet": comment
            },
            type: "get",
            success: function (data) {
                // alert(data)
                $("#return-txId").val(data)
            },
            error: function () {
                alert('转账失败，请检查您输入的地址是否有效！输入的金额是否是大于0的数字！')
            }
        });
    }


});

/*这个不行，有跨域问题*/
// $.ajax({
//     async : false,
//     cache : false,
//     type : 'POST',
//     url : 'https://www.okcoin.com/api/v1/ticker.do?symbol=btc_usd',//（1）请求的action路径,可以传递参数到后台
//     error : function() {
//         alert('实时BTC价格请求失败 ！');
//     },
//     success : function(data) {
//         alert(data);
//     }
// });
