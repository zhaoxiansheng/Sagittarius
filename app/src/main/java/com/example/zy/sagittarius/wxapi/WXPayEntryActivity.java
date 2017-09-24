package com.example.zy.sagittarius.wxapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.zy.sagittarius.bean.Config;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by zhaoy on 2017/9/10.
 * 微信分享以及登陆所需
 */

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private static IWXAPI iwxapi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WXTextShare("hahahaha",2);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode){
            case BaseResp.ErrCode.ERR_OK:
                Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Toast.makeText(this, "分享被取消", Toast.LENGTH_SHORT).show();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Toast.makeText(this, "分享拒绝", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public IWXAPI getWXApi(){
        if (iwxapi == null){
            iwxapi = WXAPIFactory.createWXAPI(this, Config.APP_ID, false);
            iwxapi.registerApp(Config.APP_ID);
        }
        return iwxapi;
    }

    public void WXTextShare(String text, int judge){
        //初始化WXObject对象
        WXTextObject textObject = new WXTextObject();
        textObject.text = text;

        //初始化WXMediaMessage对象
        WXMediaMessage wxMediaMessage = new WXMediaMessage();
        wxMediaMessage.mediaObject = textObject;
        wxMediaMessage.description = text;

        //构建一个Req对象
        SendMessageToWX.Req toWXReq = new SendMessageToWX.Req();
        toWXReq.transaction = String.valueOf(System.currentTimeMillis());
        toWXReq.message = wxMediaMessage;
        toWXReq.scene = judge;
        iwxapi = getWXApi();
        iwxapi.sendReq(toWXReq);
    }
}
