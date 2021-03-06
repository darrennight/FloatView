package com.gamedashi.dtcq.floatview;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.gamedashi.dtcq.floatview.manager.MyWindowManager;
import com.gamedashi.dtcq.floatview.view.ControlView;
//import com.gamedashi.dtcq.hookApi.HookAPI;

public class MyFloatServes extends Service {

	// 窗口管理
	public static WindowManager mWindowManager;
	// public static WindowManager.LayoutParams mLayoutParams;
	public static Context mContext;
	public static Display display;

	public static String gamePackageName = "sh.lilith.dgame.lemonfat";
	public static String gameActivityName;
	public static Boolean onekeyexit;
	public static Float game_speed = 0.0f;
	public static Boolean startHook = false;

	public static String SP_NAME = "config";
	public static String NOEKEYEXIT = "noekeyexit";
	public static SharedPreferences sp;
	public static String uid;
	// 常用常量
	
	/*****************************************************************/

	public static Thread th_monitor = null;
	// public static final String ACTION =
	// "org.leepood.monitordemo.APPS_CHANGED";// 自定义动�?
	protected static final int ID_USER = 0;
	private static final int CLOSE = 1;
	private String activityName;
	public static Boolean isbegein = false;
	public static Boolean isClose = false;
	public static ActivityManager mActivityManager;
	ControlView mControlView;

	private Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {

			switch (msg.what) {
			case ID_USER:
				/**
				 * 加速
				 */
				mControlView.startSpeed();
				/**
				 * 调试
				 */
				// mControlView.start();

				Log.i("tz3", activityName + "");
				// 获取传递的数据
				// Bundle data = msg.getData();
				// int count = data.getInt("COUNT");
				// 处理UI更新等打操作

				break;

			case CLOSE:

				// mControlView = new ControlView(MyApplication.mContext);
				// mControlView.startSpeed();
				// mControlView.close();

				MyWindowManager.getInstance(MyFloatServes.mContext).removeAll();
				MyFloatServes.isbegein = false;

				Log.i("tz3", activityName + "关闭");
				// 获取传递的数据

			}
		};
	};

	@Override
	public IBinder onBind(Intent intent) {

		return null;

	}

	@Override
	public void onCreate() {

		//HookAPI.setSocketService();

		MyFloatServes.mContext = this.getApplicationContext();
		// GlobalParams.mContext = this.getApplicationContext();

		MyFloatServes.mWindowManager = (WindowManager) this
				.getApplicationContext().getSystemService(
						Context.WINDOW_SERVICE);

		MyFloatServes.display = MyFloatServes.mWindowManager
				.getDefaultDisplay();

		MyFloatServes.startHook = false;

		mActivityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		if (MyFloatServes.mContext != null) {
			mControlView = new ControlView(MyFloatServes.mContext);
		}

		// Log.i("tz", "funClick3");
		super.onCreate();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mControlView = new ControlView(MyFloatServes.mContext);
		// 启用hook

		mActivityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

		synchronized (MyFloatServes.class) {

			th_monitor = new Thread(new Runnable() {

				@Override
				public void run() {
					programWatch();

				}
			});

			th_monitor.start();

		}

		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * 
	 */
	private void programWatch() {

		ActivityManager manager = (ActivityManager) MyFloatServes.mContext
				.getSystemService(ACTIVITY_SERVICE);

		String packName = MyFloatServes.gamePackageName;

		while (true) {
			/** 获取当前正在运行的任务栈列表， 越是靠近当前运行的任务栈会被排在第一位，之后的以此类推 */
			List<RunningTaskInfo> runningTasks = manager.getRunningTasks(1);

			/** 获得当前最顶端的任务栈，即前台任务栈 */
			RunningTaskInfo runningTaskInfo = runningTasks.get(0);

			/** 获取前台任务栈的最顶端 Activity */

			ComponentName topActivity = runningTaskInfo.topActivity;

			/** 获取应用的包名 */
			String packageName = topActivity.getPackageName();

			activityName = topActivity.getClassName();

			/** 输出检测到的启动应用信息 */

			synchronized (MyFloatServes.class) {

				if (packageName.equals(packName)) {
					// Log.i("tz_packname1", packName + "******");

					// Log.i("tz_gakagename1", packageName + "**");
					// Log.i("tz_Glob_packagename1",
					// GlobalParams_float.gamePackageName);
					Log.i("tz_activityname1", activityName + "");
					// Log.i("tz_pid1", GlobalParams_float.uid+"");
					// Log.i("tz_isbegin1", MyFloatServes.isbegein + "");
					// Log.i("tz_isbegin1", MyFloatServes.isClose + "");

					if (isbegein == false) {
						isbegein = true;

						isClose = false;
						Message msg = new Message();
						msg.what = ID_USER;
						// 这三句可以传递数据
						// Bundle data = new Bundle();
						// data.putInt("COUNT", 100);//COUNT是标签,handleMessage中使用
						// msg.setData(data);

						mHandler.sendMessage(msg);

					}

				} else if (packageName.contains("sh.lilith.dgame")) {

					// 不是从客户端启动

					MyFloatServes.gamePackageName = packageName;

					if (isbegein == false) {
						isbegein = true;

						isClose = false;
						Message msg = new Message();
						msg.what = ID_USER;
						// 这三句可以传递数据
						// Bundle data = new Bundle();
						// data.putInt("COUNT",
						// 100);//COUNT是标签,handleMessage中使用
						// msg.setData(data);

						mHandler.sendMessage(msg);

					}

				} else if (!packageName.contains("sh.lilith.dgame")) { // (!packageName.equals(packName)

					// Launcher

					Log.i("tz_packname2", packName + "******");

					Log.i("tz_gakagename2", packageName + "**");
					Log.i("tz_Glob_packagename2", MyFloatServes.gamePackageName);
					// Log.i("tz_activityname2", activityName+"");
					// Log.i("tz_pid2", GlobalParams_float.uid+"");
					// Log.i("tz_isbegin2", MyFloatServes.isbegein + "");
					// Log.i("tz_isbegin2", MyFloatServes.isClose + "");

					if (isClose == false) {

						Message msg = new Message();
						msg.what = CLOSE;
						mHandler.sendMessage(msg);

						isClose = true;

					}

				}

			}

			/** 为便于观察信息的输出，程序休眠2秒 */
			SystemClock.sleep(2000);
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {

		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

		} else {
			// ToastUtils.showToast("当前屏幕为坚屏");
			// textView.setText("当前屏幕为竖屏");
		}

		super.onConfigurationChanged(newConfig);
	}

}
