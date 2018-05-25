package com.example.az.mysocialdemo;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by wp on 2018/5/24.
 */

public class APP extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		initUM();
	}
	
	private void initUM() {
		UMConfigure.setLogEnabled(true);
		UMConfigure.init(this,Constant.UM_APP_KEY,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
	}
	
	static {
		PlatformConfig.setWeixin("你的微信APPID", "你的微信AppSecret");//微信APPID和AppSecret
		PlatformConfig.setQQZone("3103507692", "APBR2V5U18SC");//QQAPPID和AppSecret
		PlatformConfig.setSinaWeibo("你的微博APPID", "你的微博APPSecret","微博的后台配置回调地址");//微博
	}
}
