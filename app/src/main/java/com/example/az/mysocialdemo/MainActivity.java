package com.example.az.mysocialdemo;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.az.mysocialdemo.utils.LogUtils;
import com.example.az.mysocialdemo.utils.SocialUtils;
import com.umeng.socialize.UMShareAPI;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
	
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
