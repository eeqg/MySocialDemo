package com.example.az.mysocialdemo.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class ShareDialog /*extends Dialog*/ {
    // private SocialDialogShareBinding dataBinding;
    // private boolean showWechat;
    // private boolean showQQ;
    // private boolean showWeibo;
    // private String shareUrl;
    // private String shareTitle;
    // private String shareContent;
    // private String sharePictureUrl;
    // private SocialUtils.OnShareListener onShareListener;
    //
    // public static ShareDialog show(Activity activity, String shareUrl, String shareTitle, String shareContent, String sharePictureUrl, SocialUtils.OnShareListener onShareListener) {
    //     ShareDialog shareDialog = new ShareDialog(activity);
    //     shareDialog.setOwnerActivity(activity);
    //     shareDialog.configSocial();
    //     shareDialog.shareUrl = shareUrl;
    //     shareDialog.shareTitle = shareTitle;
    //     shareDialog.shareContent = shareContent;
    //     shareDialog.sharePictureUrl = sharePictureUrl;
    //     shareDialog.onShareListener = onShareListener;
    //     shareDialog.show();
    //     return shareDialog;
    // }
    //
    // private ShareDialog(Context context) {
    //     super(context, style.SocialDialogShareTheme);
    // }
    //
    // private void configSocial() {
    //     this.showWechat = !TextUtils.isEmpty(SocialUtils.getWXAppId()) && !TextUtils.isEmpty(SocialUtils.getWXAppSecret());
    //     this.showQQ = !TextUtils.isEmpty(SocialUtils.getQQAppId()) && !TextUtils.isEmpty(SocialUtils.getQQAppKey());
    //     this.showWeibo = !TextUtils.isEmpty(SocialUtils.getWeiboAppKey()) && !TextUtils.isEmpty(SocialUtils.getWeiboAppSecret()) && !TextUtils.isEmpty(SocialUtils.getWeiboRedirectUrl());
    // }
    //
    // protected void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     this.dataBinding = (SocialDialogShareBinding)DataBindingUtil.inflate(this.getLayoutInflater(), layout.social_dialog_share, (ViewGroup)null, false);
    //     this.setContentView(this.dataBinding.getRoot());
    //     this.dataBinding.setShowWechat(this.showWechat);
    //     this.observeWechat();
    //     this.observeFriend();
    //     this.dataBinding.setShowQQ(this.showQQ);
    //     this.observeQQ();
    //     this.observeQZone();
    //     this.dataBinding.setShowWeibo(this.showWeibo);
    //     this.observeWeibo();
    //     this.observeExit();
    // }
    //
    // private void observeWechat() {
    //     this.dataBinding.setWechatClickListener(new OnClickListener() {
    //         public void onClick(View v) {
    //             ShareDialog.this.share(SHARE_MEDIA.WEIXIN);
    //         }
    //     });
    // }
    //
    // private void observeFriend() {
    //     this.dataBinding.setFriendClickListener(new OnClickListener() {
    //         public void onClick(View v) {
    //             ShareDialog.this.share(SHARE_MEDIA.WEIXIN_CIRCLE);
    //         }
    //     });
    // }
    //
    // private void observeQQ() {
    //     this.dataBinding.setQqClickListener(new OnClickListener() {
    //         public void onClick(View v) {
    //             ShareDialog.this.share(SHARE_MEDIA.QQ);
    //         }
    //     });
    // }
    //
    // private void observeQZone() {
    //     this.dataBinding.setQzoneClickListener(new OnClickListener() {
    //         public void onClick(View v) {
    //             ShareDialog.this.share(SHARE_MEDIA.QZONE);
    //         }
    //     });
    // }
    //
    // private void observeWeibo() {
    //     this.dataBinding.setWeiboClickListener(new OnClickListener() {
    //         public void onClick(View v) {
    //             ShareDialog.this.share(SHARE_MEDIA.SINA);
    //         }
    //     });
    // }
    //
    // private void share(SHARE_MEDIA shareMedia) {
    //     SocialUtils.share(this.getOwnerActivity(), this.shareUrl, this.shareTitle, this.shareContent, this.sharePictureUrl, shareMedia, this.onShareListener);
    // }
    //
    // private void observeExit() {
    //     this.dataBinding.setExitClickListener(new OnClickListener() {
    //         public void onClick(View v) {
    //             ShareDialog.this.onShareListener.onStop();
    //             ShareDialog.this.dismiss();
    //         }
    //     });
    // }
    //
    // public void onDetachedFromWindow() {
    //     super.onDetachedFromWindow();
    //     UMShareAPI.get(this.getContext()).release();
    // }
    //
    // public void onAttachedToWindow() {
    //     super.onAttachedToWindow();
    //     Window window = this.getWindow();
    //     if(window != null) {
    //         DisplayMetrics displayMetrics = this.getContext().getResources().getDisplayMetrics();
    //         WindowManager.LayoutParams layoutParams = window.getAttributes();
    //         layoutParams.width = displayMetrics.widthPixels;
    //         layoutParams.gravity = 80;
    //         window.setAttributes(layoutParams);
    //     }
    // }
}