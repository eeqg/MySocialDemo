package com.example.az.mysocialdemo.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.example.az.mysocialdemo.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 拉起微信，朋友圈功能类，支持单张图片，多张图片，文字
 */
public class ShareUtils {
	
	PopupWindow popupWindow;
	Context context;
	private String path;//单张图片路径
	private String content;
	private Button btn;
	private Uri[] uris;//多张图片路径uri数组
	
	public ShareUtils(Context context) {
		this.context = context;
		//  this.path=path;
		// this.content = content;
		
		//  this.btn=btn;
		
		// showpop(targetView);
	}
	
	public ShareUtils setUri(Uri[] uris) {
		this.uris = uris;
		return this;
	}
	
	public ShareUtils setPath(String path) {
		this.path = path;
		return this;
	}
	
	public void showShareBoard(View targetView) {
		View view = LayoutInflater.from(context).inflate(
				R.layout.share_view, null);
		ImageView ivWeChat = (ImageView) view.findViewById(R.id.ivWeChat);
		ImageView ivWxCircle = (ImageView) view.findViewById(R.id.ivWxCircle);
		popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable()); // 点击返回按钮popwindow消失
		
		ivWeChat.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isAppAvailable(context, "com.tencent.mm")) {// 判断是否安装微信客户端
					// shareweixin(path);
					shareWXSomeImg(context, uris);
					// login(SHARE_MEDIA.WEIXIN);
				} else {
					//ActivityUtil.showToast(context, "请安装微信客户端");
				}
				
				popupWindow.dismiss();
			}
		});
		ivWxCircle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if (isAppAvailable(context, "com.tencent.mm")) {// 判断是否安装微信客户端
					//   shareweipyq(path,content);//拉起微信朋友圈带一张图片
					shareweipyqSomeImg(context, uris);//拉起微信朋友圈带多张图片
					// login(SHARE_MEDIA.WEIXIN);
				} else {
					//ActivityUtil.showToast(context, "请安装微信客户端");
				}
				popupWindow.dismiss();
			}
		});
		
		popupWindow.showAtLocation(targetView, Gravity.BOTTOM, 0, 0);// 先设置popwindow的所有参数，最后再show
		
	}
	
	/**
	 * 拉起微信好友发送单张图片
	 */
	private void shareweixin(String path) {
		Uri uriToImage = Uri.fromFile(new File(path));
		Intent shareIntent = new Intent();
		//发送图片到朋友圈
		//ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
		//发送图片给好友。
		ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
		shareIntent.setComponent(comp);
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
		shareIntent.setType("image/jpeg");
		context.startActivity(Intent.createChooser(shareIntent, "分享图片"));
	}
	
	/**
	 * 拉起微信朋友圈发送单张图片
	 */
	private void shareweipyq(String path, String content) {
		Uri uriToImage = Uri.fromFile(new File(path));
		Intent shareIntent = new Intent();
		//发送图片到朋友圈
		ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
		//发送图片给好友。
		//        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
		shareIntent.setComponent(comp);
		shareIntent.putExtra("Kdescription", content);
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
		shareIntent.setType("image/jpeg");
		context.startActivity(Intent.createChooser(shareIntent, "分享图片"));
	}
	
	/**
	 * 拉起微信朋友圈发送多张图片
	 */
	private void shareweipyqSomeImg(Context context, Uri[] uri) {
		Intent shareIntent = new Intent();
		//1调用系统分析
		shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
		// shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		//2添加图片数组
		ArrayList<Uri> imageUris = new ArrayList<>();
		for (int i = 0; i < uri.length; i++) {
			imageUris.add(uri[i]);
		}
		
		shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
		shareIntent.setType("image/*");
		
		//3指定选择微信
		ComponentName componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
		shareIntent.setComponent(componentName);
		
		//4开始分享
		context.startActivity(Intent.createChooser(shareIntent, "分享图片"));
	}
	
	/**
	 * 拉起微信发送多张图片给好友
	 */
	private void shareWXSomeImg(Context context, Uri[] uri) {
		LogUtils.d("-----uri : " + uri);
		Intent shareIntent = new Intent();
		//1调用系统分析
		shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
		// shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		//2添加图片数组
		ArrayList<Uri> imageUris = new ArrayList<>();
		for (int i = 0; i < uri.length; i++) {
			imageUris.add(uri[i]);
		}
		
		shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
		shareIntent.setType("image/*");
		
		//3指定选择微信
		ComponentName componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
		shareIntent.setComponent(componentName);
		
		//4开始分享
		context.startActivity(Intent.createChooser(shareIntent, "分享图片"));
	}
	
	//判断是否安装了微信,QQ,QQ空间
	public static boolean isAppAvailable(Context context, String mType) {
		final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
		if (pinfo != null) {
			for (int i = 0; i < pinfo.size(); i++) {
				String pn = pinfo.get(i).packageName;
				if (pn.equals(mType)) {
					return true;
				}
			}
		}
		return false;
	}
}