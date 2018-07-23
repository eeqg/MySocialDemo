package com.example.az.mysocialdemo;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.az.mysocialdemo.utils.ImageUtils;
import com.example.az.mysocialdemo.utils.ImgFileUtils;
import com.example.az.mysocialdemo.utils.LogUtils;
import com.example.az.mysocialdemo.utils.PosterXQImgCache;
import com.example.az.mysocialdemo.utils.ShareDialog;
import com.example.az.mysocialdemo.utils.ShareUtils;
import com.example.az.mysocialdemo.utils.SocialUtils;
import com.umeng.socialize.UMShareAPI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
	
	private String[] images = {"https://gd2.alicdn.com/imgextra/i1/2259324182/TB2sdjGm0BopuFjSZPcXXc9EpXa_!!2259324182.jpg",
			"http://img4.tbcdn.cn/tfscom/i1/2259324182/TB2ISF_hKtTMeFjSZFOXXaTiVXa_!!2259324182.jpg",
			"http://img2.tbcdn.cn/tfscom/i1/2259324182/TB2NAMmm00opuFjSZFxXXaDNVXa_!!2259324182.jpg"};
	final ArrayList<String> imageList = new ArrayList<>();
	private List<String> imgCache;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ButterKnife.bind(this);
		
		requestPermission();
		
		findViewById(R.id.btnShare).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				LogUtils.d("test_wp", "onClick()");
				share();
			}
		});
		
		imageList.add("https://gd2.alicdn.com/imgextra/i1/2259324182/TB2sdjGm0BopuFjSZPcXXc9EpXa_!!2259324182.jpg");
		imageList.add("http://img4.tbcdn.cn/tfscom/i1/2259324182/TB2ISF_hKtTMeFjSZFOXXaTiVXa_!!2259324182.jpg");
		imageList.add("http://img2.tbcdn.cn/tfscom/i1/2259324182/TB2NAMmm00opuFjSZFxXXaDNVXa_!!2259324182.jpg");
		
		observeShare1();
		observeShare2();
	}
	
	private void requestPermission() {
		if (Build.VERSION.SDK_INT >= 23) {
			String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
					Manifest.permission.ACCESS_FINE_LOCATION,
					Manifest.permission.CALL_PHONE,
					Manifest.permission.READ_LOGS,
					Manifest.permission.READ_PHONE_STATE,
					Manifest.permission.READ_EXTERNAL_STORAGE,
					Manifest.permission.SET_DEBUG_APP,
					Manifest.permission.SYSTEM_ALERT_WINDOW,
					Manifest.permission.GET_ACCOUNTS,
					Manifest.permission.WRITE_APN_SETTINGS};
			ActivityCompat.requestPermissions(this, mPermissionList, 123);
		}
	}
	
	@OnClick(R.id.btnShare)
	public void onClick(View view) {
		LogUtils.d("test_wp", "onClick()");
		switch (view.getId()) {
			case R.id.btnShare:
				share();
				break;
		}
	}
	
	private void share() {
		SocialUtils.shareBottom(MainActivity.this, "https://www.baidu.com", "shareTitle", "content", "",
				new SocialUtils.OnShareListener() {
					@Override
					public void onStart() {
						LogUtils.d("test_wp", "onStart()");
					}
					
					@Override
					public void onStop() {
						LogUtils.d("test_wp", "onStop()");
					}
					
					@Override
					public void onSuccess() {
						LogUtils.d("test_wp", "onSuccess()");
					}
					
					@Override
					public void onFailure(String var1) {
						LogUtils.d("test_wp", "onFailure()");
						// promptMessage(var1);
					}
				});
	}
	
	private void observeShare1() {
		
		//缓存图片到本地
		for (int i = 0; i < images.length; i++) {
			// Glide.with(this)
			// 		.load(images[i]).asBitmap().into(new SimpleTarget<Bitmap>() {
			// 	@Override
			// 	public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
			// 		ImgFileUtils.saveBitmap(MainActivity.this, resource, String.valueOf(System.currentTimeMillis()));
			// 	}
			// });
			final int finalI = i;
			Glide.with(this).load(images[i]).into(new SimpleTarget<Drawable>() {
				@Override
				public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
					ImgFileUtils.saveBitmap(MainActivity.this, ImageUtils.drawableToBitmap(resource), "test" + finalI);
				}
			});
		}
		
		final View shareBoard = findViewById(R.id.btnShareDialog);
		shareBoard.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				imgCache = PosterXQImgCache.getInstance().getImgCache();
				Uri[] uris = new Uri[imgCache.size()];//创建用于存放图片的Uri数组
				//循环缓存路径分别生成文件，添加到图片Uri数组中
				for (int i = 0; i < imgCache.size(); i++) {
					LogUtils.d("-----" + imgCache.get(i));
					uris[i] = Uri.fromFile(new File(imgCache.get(i)));
				}
				
				new ShareUtils(MainActivity.this)
						.setUri(uris)
						.showShareBoard(shareBoard);
			}
		});
	}
	
	private void observeShare2() {
		findViewById(R.id.btnShareDialog2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ShareDialog.showMultiImageShare(MainActivity.this, imageList).show();
			}
		});
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode,
	                                       String permissions[], int[] grantResults) {
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/** attention to this below ,must add this**/
		UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
	}
}
