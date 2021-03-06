package com.gamedashi.dtcq.floatview.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.gamedashi.dtcq.floatview.MyFloatServes;
import com.gamedashi.dtcq.floatview.R;
import com.gamedashi.dtcq.floatview.manager.MyWindowManager;
//import com.gamedashi.dtcq.hookApi.HookAPI;

/**
 * 左侧 小设置按钮
 * 
 * @author longtaoger
 * 
 */
public class SetFloatView extends BaseFloatView implements OnClickListener {

	private static SetFloatView mSetFloatViewIntance;
	private View mView;

	private SetFloatView(Context context) {
		super(context);
		mContext = context;
		// 初始化view
		initView();

	}

	public static SetFloatView getInstance() {

		if (mSetFloatViewIntance == null) {

			synchronized (SetFloatView.class) {

				if (mSetFloatViewIntance == null) {

					mSetFloatViewIntance = new SetFloatView(
							MyFloatServes.mContext);

				}

			}

		}

		return mSetFloatViewIntance;

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
				R.layout.tz_dtcq_gamespeed_float_window_small_set, null);

		ImageView mImageView = (ImageView) mView
				.findViewById(R.id.tz_dtcq_gamespeed_float_window_small_set_image);

		//mView.setOnClickListener(this);
		// mImageView.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO
		if (v.getId() == R.id.tz_dtcq_gamespeed_float_window_small_set_linearlayout) {

			if (mView.getParent() != null) {

				// TODO 游戏加速2版

				if (!MyFloatServes.startHook) {

				//	HookAPI.startHook(MyFloatServes.gamePackageName,
							//MyFloatServes.mContext, true);
					MyFloatServes.startHook = true;
					
					
				} else {

				}

				mMyWindowManager.andGamespeedFloatView_one();
				Log.i("Float", "starhook");
				// Toast.makeText(mContext, "andGamespeedFloatView_one",
				// 0).show();
				remove();
				// Toast.makeText(mContext, "设置事件1", 0).show();
			}

		}

	}

}
