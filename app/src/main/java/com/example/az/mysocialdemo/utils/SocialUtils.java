package com.example.az.mysocialdemo.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.az.mysocialdemo.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.util.Map;

/**
 * Created by wp on 2018/5/11.
 */

public class SocialUtils {
	
	public static void shareWithPlatform(Activity activity, String shareUrl, String shareTitle, String shareContent, String sharePictureUrl, SHARE_MEDIA shareMedia, SocialUtils.OnShareListener onShareListener){
		UMWeb umWeb = new UMWeb(shareUrl);
		umWeb.setTitle(shareTitle);
		umWeb.setDescription(shareContent);
		if(!TextUtils.isEmpty(sharePictureUrl)) {
			umWeb.setThumb(new UMImage(activity, sharePictureUrl));
		}
		(new ShareAction(activity)).setPlatform(shareMedia).withMedia(umWeb).setCallback(createShareListener(onShareListener)).share();
	}
	
	public static void shareBottom(final Activity activity,
	                         final String shareUrl, final String shareTitle, final String shareContent,
	                         final String sharePictureUrl,
	                         final SocialUtils.OnShareListener onShareListener) {
		new ShareAction(activity).setDisplayList(
				SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
				SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
				.setShareboardclickCallback(new ShareBoardlistener() {
					@Override
					public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
						UMWeb umWeb = new UMWeb(shareUrl);
						umWeb.setTitle(shareTitle);
						umWeb.setDescription(shareContent);
						if (!TextUtils.isEmpty(sharePictureUrl)) {
							umWeb.setThumb(new UMImage(activity, sharePictureUrl));
						} else {
							umWeb.setThumb(new UMImage(activity, R.mipmap.ic_launcher));
						}
						
						(new ShareAction(activity)).setPlatform(share_media).withMedia(umWeb)
								.setCallback(createShareListener(onShareListener)).share();
					}
				}).open();
	}
	
	public static void shareCenter(final Activity activity,
	                               final String shareUrl, final String shareTitle, final String shareContent,
	                               final String sharePictureUrl,
	                               final SocialUtils.OnShareListener onShareListener) {
		ShareBoardConfig config = new ShareBoardConfig();
		config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER);
		config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
		new ShareAction(activity).setDisplayList(
				SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
				SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
				.setShareboardclickCallback(new ShareBoardlistener() {
					@Override
					public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
						UMWeb umWeb = new UMWeb(shareUrl);
						umWeb.setTitle(shareTitle);
						umWeb.setDescription(shareContent);
						if (!TextUtils.isEmpty(sharePictureUrl)) {
							umWeb.setThumb(new UMImage(activity, sharePictureUrl));
						} else {
							umWeb.setThumb(new UMImage(activity, R.mipmap.ic_launcher));
						}
						
						(new ShareAction(activity)).setPlatform(share_media).withMedia(umWeb)
								.setCallback(createShareListener(onShareListener)).share();
					}
				}).open(config);
	}
	
	public static boolean isInstall(Activity activity, SHARE_MEDIA shareMedia) {
		return shareMedia != SHARE_MEDIA.WEIXIN && shareMedia != SHARE_MEDIA.WEIXIN_CIRCLE ?
				(shareMedia != SHARE_MEDIA.QQ && shareMedia != SHARE_MEDIA.QZONE ?
						UMShareAPI.get(activity).isInstall(activity, shareMedia)
						: UMShareAPI.get(activity).isInstall(activity, SHARE_MEDIA.QQ))
				: UMShareAPI.get(activity).isInstall(activity, shareMedia);
	}
	
	/**
	 * 授权
	 *
	 * @param platform 平台类型
	 */
	public static void doOauthVerify(Activity activity, SHARE_MEDIA platform) {
		UMShareAPI.get(activity).doOauthVerify(activity, platform, new UMAuthListener() {
			@Override
			public void onStart(SHARE_MEDIA share_media) {
			
			}
			
			@Override
			public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
			
			}
			
			@Override
			public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
			
			}
			
			@Override
			public void onCancel(SHARE_MEDIA share_media, int i) {
			
			}
		});
	}
	
	/**
	 * 刪除授权
	 *
	 * @param platform 平台类型
	 */
	public static void deleteOauth(Activity activity, SHARE_MEDIA platform) {
		boolean isAuth = UMShareAPI.get(activity).isAuthorize(activity, platform);
		if (!isAuth) {
			return;
		}
		UMShareAPI.get(activity).deleteOauth(activity, platform, new UMAuthListener() {
			@Override
			public void onStart(SHARE_MEDIA share_media) {
			
			}
			
			@Override
			public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
			
			}
			
			@Override
			public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
			
			}
			
			@Override
			public void onCancel(SHARE_MEDIA share_media, int i) {
			
			}
		});
	}
	
	/**
	 * 获取用户信息
	 *
	 * @param platform 平台类型
	 */
	private void getPlatformInfo(Activity activity, SHARE_MEDIA platform) {
		UMShareAPI.get(activity)
				.getPlatformInfo(activity, platform, new UMAuthListener() {
					@Override
					public void onStart(SHARE_MEDIA shareMedia) {
					}
					
					@Override
					public void onComplete(SHARE_MEDIA shareMedia, int i, Map<String, String> map) {
					}
					
					@Override
					public void onError(SHARE_MEDIA shareMedia, int i, Throwable throwable) {
					}
					
					@Override
					public void onCancel(SHARE_MEDIA shareMedia, int i) {
					}
				});
	}
	
	private static UMShareListener createShareListener(final SocialUtils.OnShareListener onShareListener) {
		return new UMShareListener() {
			private Handler mHandler = new Handler(Looper.getMainLooper());
			
			public void onStart(SHARE_MEDIA share_media) {
				this.mHandler.post(new Runnable() {
					public void run() {
						onShareListener.onStart();
					}
				});
			}
			
			public void onResult(SHARE_MEDIA share_media) {
				this.mHandler.post(new Runnable() {
					public void run() {
						onShareListener.onStop();
						onShareListener.onSuccess();
					}
				});
			}
			
			public void onError(SHARE_MEDIA share_media, Throwable throwable) {
				this.mHandler.post(new Runnable() {
					public void run() {
						onShareListener.onStop();
						onShareListener.onFailure("分享失败");
					}
				});
			}
			
			public void onCancel(SHARE_MEDIA share_media) {
				this.mHandler.post(new Runnable() {
					public void run() {
						onShareListener.onStop();
					}
				});
			}
		};
	}
	
	UMAuthListener authListener = new UMAuthListener() {
		@Override
		public void onStart(SHARE_MEDIA platform) {
			// SocializeUtils.safeShowDialog(dialog);
		}
		
		@Override
		public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
			// SocializeUtils.safeCloseDialog(dialog);
			// Toast.makeText(mContext, "成功了", Toast.LENGTH_LONG).show();
		}
		
		@Override
		public void onError(SHARE_MEDIA platform, int action, Throwable t) {
			// SocializeUtils.safeCloseDialog(dialog);
			// Toast.makeText(mContext, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
		}
		
		@Override
		public void onCancel(SHARE_MEDIA platform, int action) {
			// SocializeUtils.safeCloseDialog(dialog);
			// Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
		}
	};
	
	public interface OnShareListener {
		void onStart();
		
		void onStop();
		
		void onSuccess();
		
		void onFailure(String var1);
	}
}
