package com.example.zy.sagittarius.wxapi;

import com.example.zy.sagittarius.MyApplication;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;

/**
 * Created by zhaoy on 2017/9/10.
 * 微信测试
 */

public class WXUtils {

    /**
     * 分享文本到朋友圈
     * @param text 文本内容
     * @param judge 类型选择 好友--WECHART_FRIEND 朋友圈--WECHART_MOMENT
     */
//    public static void WXTextShare(String text, int judge){
//        //初始化WXObject对象
//        WXTextObject textObject = new WXTextObject();
//        textObject.text = text;
//
//        //初始化WXMediaMessage对象
//        WXMediaMessage wxMediaMessage = new WXMediaMessage();
//        wxMediaMessage.mediaObject = textObject;
//        wxMediaMessage.description = text;
//
//        //构建一个Req对象
//        SendMessageToWX.Req toWXReq = new SendMessageToWX.Req();
//        toWXReq.transaction = String.valueOf(System.currentTimeMillis());
//        toWXReq.message = wxMediaMessage;
//        toWXReq.scene = judge;
//        iwxapi = getWXApi(MyApplication.context);
//        iwxapi.sendReq(toWXReq);
//    }

}
