package com.gamedashi.dtcq.floatview.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.gamedashi.dtcq.floatview.MyFloatServes;
import com.gamedashi.dtcq.floatview.R;
//import com.gamedashi.dtcq.hookApi.HookAPI;

/**
 * 左侧 小
 * 
 * @author longtaoger
 * 
 */
public class StartFloatView extends BaseFloatView implements OnClickListener {

	private static StartFloatView mStartFloatView;
	private View mView;

	private StartFloatView(Context context) {
		super(context);
		mContext = context;
		// 初始化view
		initView();

	}

	public static StartFloatView getInstance() {

		if (mStartFloatView == null) {

			synchronized (StartFloatView.class) {

				if (mStartFloatView == null) {

					mStartFloatView = new StartFloatView(
							MyFloatServes.mContext);

				}

			}

		}

		return mStartFloatView;

	}

	@Override
	public View getFloatView() {

		if (mView != null) {
			return mView;
		} else {
			initView();
			return mView;
		}
	}

	@Override
	public void initView() {
		mView = LayoutInflater.from(mContext).inflate(
				R.layout.tz_dtcq_gamespeed_float_window_start, null);

		mView.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO
		if (v.getId() == R.id.tz_dtcq_gamespeed_float_window_start_linearlayout) {

			if (mView.getParent() != null) {

				// TODO 游戏加速2版

				if (!MyFloatServes.startHook) {

//					HookAPI.startHook(MyFloatServes.gamePackageName,
//							MyFloatServes.mContext, true);
					
					Log.i("startFloat", "starhook");
					
					
					MyFloatServes.startHook = true;
				} else {

				}

				
			
				// mMyWindowManager.andGamespeedFloatView_one();

				// Toast.makeText(mContext, "andGamespeedFloatView_one",
				// 0).show();

				remove();
				// Toast.makeText(mContext, "设置事件1", 0).show();
			}

		}

	}

}
