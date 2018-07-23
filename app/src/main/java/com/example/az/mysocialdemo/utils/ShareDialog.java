package com.example.az.mysocialdemo.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.az.mysocialdemo.R;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShareDialog extends Dialog {
	private boolean showWechat;
	private boolean showQQ;
	private boolean showWeibo;
	private String shareUrl;
	private String shareTitle;
	private String shareContent;
	private String sharePictureUrl;
	private SocialUtils.OnShareListener onShareListener;
	private View rootView;
	
	public static ShareDialog show(Activity activity, String shareUrl, String shareTitle, String shareContent, String sharePictureUrl, SocialUtils.OnShareListener onShareListener) {
		ShareDialog shareDialog = new ShareDialog(activity);
		shareDialog.setOwnerActivity(activity);
		shareDialog.configSocial();
		shareDialog.shareUrl = shareUrl;
		shareDialog.shareTitle = shareTitle;
		shareDialog.shareContent = shareContent;
		shareDialog.sharePictureUrl = sharePictureUrl;
		shareDialog.onShareListener = onShareListener;
		shareDialog.show();
		return shareDialog;
	}
	
	public static ShareDialog showMultiImageShare(Activity activity, ArrayList<String> imageList) {
		ShareDialog shareDialog = new ShareDialog(activity);
		shareDialog.setOwnerActivity(activity);
		shareDialog.cacheImage(imageList);
		// shareDialog.show();
		return shareDialog;
	}
	
	private void cacheImage(ArrayList<String> imageList) {
		//缓存图片到本地
		PosterXQImgCache.getInstance().removeImgCache();//清空缓存历史
		for (int i = 0; i < imageList.size(); i++) {
			final int finalI = i;
			Glide.with(getContext()).load(imageList.get(i)).into(new SimpleTarget<Drawable>() {
				@Override
				public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
					ImgFileUtils.saveBitmap(getContext(), ImageUtils.drawableToBitmap(resource), "test" + finalI);
				}
				
				@Override
				public void onLoadFailed(@Nullable Drawable errorDrawable) {
					super.onLoadFailed(errorDrawable);
				}
			});
		}
	}
	
	private ShareDialog(Context context) {
		super(context, R.style.DialogTransparentTheme_Bottom);
	}
	
	private void configSocial() {
		// this.showWechat = !TextUtils.isEmpty(SocialUtils.getWXAppId()) && !TextUtils.isEmpty(SocialUtils.getWXAppSecret());
		// this.showQQ = !TextUtils.isEmpty(SocialUtils.getQQAppId()) && !TextUtils.isEmpty(SocialUtils.getQQAppKey());
		// this.showWeibo = !TextUtils.isEmpty(SocialUtils.getWeiboAppKey()) && !TextUtils.isEmpty(SocialUtils.getWeiboAppSecret()) && !TextUtils.isEmpty(SocialUtils.getWeiboRedirectUrl());
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.rootView = getLayoutInflater().inflate(R.layout.share_dialog, (ViewGroup) null, false);
		this.setContentView(rootView);
		// this.dataBinding.setShowWechat(this.showWechat);
		this.observeWechat();
		this.observeFriend();
		// this.dataBinding.setShowQQ(this.showQQ);
		this.observeQQ();
		this.observeQZone();
		// this.dataBinding.setShowWeibo(this.showWeibo);
		this.observeWeibo();
		this.observeExit();
	}
	
	private void observeWechat() {
		// this.dataBinding.setWechatClickListener(new OnClickListener() {
		// 	public void onClick(View v) {
		// 		ShareDialog.this.share(SHARE_MEDIA.WEIXIN);
		// 	}
		// });
		this.rootView.findViewById(R.id.llWechat).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				List<String> imgCache = PosterXQImgCache.getInstance().getImgCache();
				Uri[] uris = new Uri[imgCache.size()];//创建用于存放图片的Uri数组
				//循环缓存路径分别生成文件，添加到图片Uri数组中
				for (int i = 0; i < imgCache.size(); i++) {
					LogUtils.d("-----" + imgCache.get(i));
					uris[i] = Uri.fromFile(new File(imgCache.get(i)));
				}
				new ShareUtils(getContext()).shareWXSomeImg(getContext(), uris);
				
				dismiss();
			}
		});
	}
	
	private void observeFriend() {
		// this.dataBinding.setFriendClickListener(new OnClickListener() {
		// 	public void onClick(View v) {
		// 		ShareDialog.this.share(SHARE_MEDIA.WEIXIN_CIRCLE);
		// 	}
		// });
		this.rootView.findViewById(R.id.llMoment).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				List<String> imgCache = PosterXQImgCache.getInstance().getImgCache();
				Uri[] uris = new Uri[imgCache.size()];//创建用于存放图片的Uri数组
				//循环缓存路径分别生成文件，添加到图片Uri数组中
				for (int i = 0; i < imgCache.size(); i++) {
					LogUtils.d("-----" + imgCache.get(i));
					uris[i] = Uri.fromFile(new File(imgCache.get(i)));
				}
				new ShareUtils(getContext()).shareweipyqSomeImg(getContext(), uris);
				
				dismiss();
			}
		});
	}
	
	private void observeQQ() {
		// this.dataBinding.setQqClickListener(new OnClickListener() {
		// 	public void onClick(View v) {
		// 		ShareDialog.this.share(SHARE_MEDIA.QQ);
		// 	}
		// });
	}
	
	private void observeQZone() {
		// this.dataBinding.setQzoneClickListener(new OnClickListener() {
		// 	public void onClick(View v) {
		// 		ShareDialog.this.share(SHARE_MEDIA.QZONE);
		// 	}
		// });
	}
	
	private void observeWeibo() {
		// this.dataBinding.setWeiboClickListener(new OnClickListener() {
		// 	public void onClick(View v) {
		// 		ShareDialog.this.share(SHARE_MEDIA.SINA);
		// 	}
		// });
	}
	
	private void share(SHARE_MEDIA shareMedia) {
		// SocialUtils.share(this.getOwnerActivity(), this.shareUrl, this.shareTitle, this.shareContent, this.sharePictureUrl, shareMedia, this.onShareListener);
	}
	
	private void observeExit() {
		// this.dataBinding.setExitClickListener(new OnClickListener() {
		// 	public void onClick(View v) {
		// 		ShareDialog.this.onShareListener.onStop();
		// 		ShareDialog.this.dismiss();
		// 	}
		// });
	}
	
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		UMShareAPI.get(this.getContext()).release();
	}
	
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = this.getWindow();
		if (window != null) {
			DisplayMetrics displayMetrics = this.getContext().getResources().getDisplayMetrics();
			WindowManager.LayoutParams layoutParams = window.getAttributes();
			layoutParams.width = displayMetrics.widthPixels;
			layoutParams.gravity = 80;
			window.setAttributes(layoutParams);
		}
	}
}