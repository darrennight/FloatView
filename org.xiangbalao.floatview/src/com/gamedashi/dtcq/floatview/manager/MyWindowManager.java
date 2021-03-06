package com.gamedashi.dtcq.floatview.manager;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;

import com.gamedashi.dtcq.floatview.MyFloatServes;
import com.gamedashi.dtcq.floatview.R;
import com.gamedashi.dtcq.floatview.utils.MyTools;
import com.gamedashi.dtcq.floatview.view.AdditionFloatView;
import com.gamedashi.dtcq.floatview.view.DialogFloatView;
import com.gamedashi.dtcq.floatview.view.EquipFloatView;
import com.gamedashi.dtcq.floatview.view.EquipListFloatView;
import com.gamedashi.dtcq.floatview.view.EquipmentListFloatView;
import com.gamedashi.dtcq.floatview.view.EquipmentList_DialogFloatView;
import com.gamedashi.dtcq.floatview.view.ExitFloatView;
import com.gamedashi.dtcq.floatview.view.GamespeedFloatView;
import com.gamedashi.dtcq.floatview.view.GamespeedFloatView_one;
import com.gamedashi.dtcq.floatview.view.Hero_Card_FloatView;
import com.gamedashi.dtcq.floatview.view.RecruitDialogFloatView;
import com.gamedashi.dtcq.floatview.view.SetFloatView;
import com.gamedashi.dtcq.floatview.view.Set_DialogFloatView;
import com.gamedashi.dtcq.floatview.view.StartFloatView;
//import com.gamedashi.dtcq.hookApi.HookAPI;

public class MyWindowManager extends BaseWindowManager {

	private static MyTools myTools;
	static int tag = 0;

	private static KillThead mKillThead;
	private static MyWindowManager mMyWindowManagerIntance = null;

	public static MyWindowManager getInstance(Context context) {

		if (mMyWindowManagerIntance == null) {

			synchronized (MyWindowManager.class) {

				if (mMyWindowManagerIntance == null) {

					mMyWindowManagerIntance = new MyWindowManager(context);

				}

			}

		}

		return mMyWindowManagerIntance;

	}

	private MyWindowManager(Context context) {
		super();
		mContext = context;
		myTools = new MyTools(mContext);
		if (mContext != null) {
			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);

			mWindowManager = MyFloatServes.mWindowManager;

			// TODO
			ScreenWidth = MyFloatServes.display.getWidth();

			ScreenHeight = MyFloatServes.display.getHeight();

		}
		mLayoutParams = new WindowManager.LayoutParams();
		mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams.flags = 40;
		mLayoutParams.format = -3;
	}

	/**
	 * 将加速视图添加到窗口
	 */
	public static void andGamespeedFloatView() {
		if (mLayoutParams_GameSpeed == null) {

			mLayoutParams_GameSpeed = new WindowManager.LayoutParams();
			// mLayoutParams_GameSpeed = mLayoutParams;
		}
		mLayoutParams_GameSpeed.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams_GameSpeed.flags = 40;
		mLayoutParams_GameSpeed.format = -3;
		mLayoutParams_GameSpeed.width = ScreenWidth * 8 / 10; // WindowManager.LayoutParams.WRAP_CONTENT;
		mLayoutParams_GameSpeed.height = WindowManager.LayoutParams.MATCH_PARENT;// ScreenHeight
																					// *
																					// //
																					// /
		mLayoutParams_GameSpeed.x = 0;

		mGamespeedFloatView = GamespeedFloatView.getInstance().getFloatView();

		mLayoutParams_GameSpeed.y = ScreenHeight;

		if (mWindowManager != null) {

			try {
				if (mGamespeedFloatView.getParent() == null) {
					mWindowManager.addView(mGamespeedFloatView,
							mLayoutParams_GameSpeed);
				}

			} catch (Exception e) {
				mWindowManager.addView(mGamespeedFloatView,
						mLayoutParams_GameSpeed);
			}

			// mWindowManager.removeViewImmediate(view);

		} else {
			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);

			mWindowManager
					.addView(mGamespeedFloatView, mLayoutParams_GameSpeed);

		}

	}

	/**
	 * 将加速视图添加到窗口_版本1
	 */
	public static void andGamespeedFloatView_one() {
		if (mLayoutParams_GameSpeed_one == null) {

			mLayoutParams_GameSpeed_one = new WindowManager.LayoutParams();
			// mLayoutParams_GameSpeed = mLayoutParams;
		}
		mLayoutParams_GameSpeed_one.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams_GameSpeed_one.flags = 40;
		mLayoutParams_GameSpeed_one.format = -3;
		mLayoutParams_GameSpeed_one.width = ScreenWidth * 8 / 10; // WindowManager.LayoutParams.WRAP_CONTENT;
		mLayoutParams_GameSpeed_one.height = WindowManager.LayoutParams.MATCH_PARENT;// ScreenHeight
																						// *
		
														// /
		mLayoutParams_GameSpeed_one.x = 0;

		mGamespeedFloatView_one = GamespeedFloatView_one.getInstance()
				.getFloatView();
		mLayoutParams_GameSpeed_one.y = ScreenHeight;

		if (mWindowManager != null) {

			try {
				if (mGamespeedFloatView_one.getParent() == null) {
					mWindowManager.addView(mGamespeedFloatView_one,
							mLayoutParams_GameSpeed_one);
				}

			} catch (Exception e) {
				mWindowManager.addView(mGamespeedFloatView_one,
						mLayoutParams_GameSpeed_one);
			}

			// mWindowManager.removeViewImmediate(view);

		} else {
			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);

			mWindowManager.addView(mGamespeedFloatView_one,
					mLayoutParams_GameSpeed_one);

		}

	}

	/**
	 * 退出窗口
	 */
	public static void andExitFloatView() {

		if (mLayoutParams_Exit == null) {

			mLayoutParams_Exit = new WindowManager.LayoutParams();
			// mLayoutParams_Exit = mLayoutParams;

		}
		mLayoutParams_Exit.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams_Exit.flags = 40;
		mLayoutParams_Exit.format = -3;

		mLayoutParams_Exit.width = WindowManager.LayoutParams.WRAP_CONTENT;
		mLayoutParams_Exit.height = WindowManager.LayoutParams.WRAP_CONTENT;

		MyFloatServes.sp = MyFloatServes.mContext.getSharedPreferences(
				MyFloatServes.SP_NAME, 0);

		mLayoutParams_Exit.x = MyFloatServes.sp.getInt("paramsx_exit",
				-ScreenHeight);
		mLayoutParams_Exit.y = MyFloatServes.sp.getInt("paramsy_exit",
				-ScreenWidth / 8);

		// mLayoutParams_Exit.x = -ScreenHeight;
		// mLayoutParams_Exit.y = -ScreenWidth / 8;// -MyTools.dip2px(80);

		mExitFloatView = ExitFloatView.getInstance().getFloatView();

		if (mWindowManager != null) {

			if (mExitFloatView != null) {
				try {
					if (mExitFloatView.getParent() == null) {
						mWindowManager.addView(mExitFloatView,
								mLayoutParams_Exit);
					}

				} catch (Exception e) {
					mWindowManager.addView(mExitFloatView, mLayoutParams_Exit);
				}

			}

			// mWindowManager.addView(mExitFloatView, mLayoutParams_Exit);
		} else {
			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);

			mWindowManager.addView(mExitFloatView, mLayoutParams_Exit);

		}

		setExitOnTouch(mExitFloatView, mLayoutParams_Exit);

	}

	/**
	 * 设置
	 */
	public static void andSetFloatView() {

		if (mLayoutParams_Set == null) {
			mLayoutParams_Set = new WindowManager.LayoutParams();

			// mLayoutParams_Set = mLayoutParams;
		}
		mLayoutParams_Set.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams_Set.flags = 40;
		mLayoutParams_Set.format = -3;
		mLayoutParams_Set.width = WindowManager.LayoutParams.WRAP_CONTENT;
		mLayoutParams_Set.height = WindowManager.LayoutParams.WRAP_CONTENT;

		mSetFloatView = SetFloatView.getInstance().getFloatView();

		MyFloatServes.sp = MyFloatServes.mContext.getSharedPreferences(
				MyFloatServes.SP_NAME, 0);

		mLayoutParams_Set.x = MyFloatServes.sp.getInt("paramsx_set",
				-ScreenHeight);
		mLayoutParams_Set.y = MyFloatServes.sp.getInt("paramsy_set",
				-ScreenWidth / 15);

		// mLayoutParams_Set.x = -ScreenHeight;
		// mLayoutParams_Set.y = ScreenWidth / 15;

		if (mWindowManager != null) {

			if (mSetFloatView.getParent() == null) {

				try {
					if (mSetFloatView.getParent() == null) {

						mWindowManager
								.addView(mSetFloatView, mLayoutParams_Set);

					}

				} catch (Exception e) {
					mWindowManager.addView(mSetFloatView, mLayoutParams_Set);

				}

			}

		} else {

			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);
			mWindowManager.addView(mSetFloatView, mLayoutParams_Set);

		}

		setOnTouch(mSetFloatView, mLayoutParams_Set);

	}

	/**
	 * 招幕
	 */
	public static void andRecruitDialogFloatView() {

		if (mLayoutParams_RecruitDialog == null) {
			mLayoutParams_RecruitDialog = new WindowManager.LayoutParams();
			// mLayoutParams_RecruitDialog = mLayoutParams;
		}
		mLayoutParams_RecruitDialog.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams_RecruitDialog.flags = 40;
		mLayoutParams_RecruitDialog.format = -3;
		mLayoutParams_RecruitDialog.width = ScreenWidth * 8 / 10; // WindowManager.LayoutParams.MATCH_PARENT;
		mLayoutParams_RecruitDialog.height = ScreenHeight * 8 / 10; // WindowManager.LayoutParams.WRAP_CONTENT;

		mRecruitDialogFloatView = RecruitDialogFloatView.getInstance()
				.getFloatView();

		// mLayoutParams_Set.x = -ScreenHeight;
		// mLayoutParams_Set.y = ScreenWidth / 15;

		if (mWindowManager != null) {

			try {
				if (mRecruitDialogFloatView.getParent() == null) {
					mWindowManager.addView(mRecruitDialogFloatView,
							mLayoutParams_RecruitDialog);
				}

			} catch (Exception e) {
				mWindowManager.addView(mRecruitDialogFloatView,
						mLayoutParams_RecruitDialog);
			}

		} else {
			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);

			mWindowManager.addView(mRecruitDialogFloatView,
					mLayoutParams_RecruitDialog);
		}

	}

	/**
	 * 设置对话框
	 */
	public static void andSetDialogFloatView() {

		if (mLayoutParams_SetDialog == null) {
			mLayoutParams_SetDialog = new WindowManager.LayoutParams();

			// mLayoutParams_SetDialog = mLayoutParams;

		}
		mLayoutParams_SetDialog.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams_SetDialog.flags = 40;
		mLayoutParams_SetDialog.format = -3;
		mLayoutParams_SetDialog.width = ScreenWidth;// * 7 / 10; //
													// WindowManager.LayoutParams.MATCH_PARENT;
		mLayoutParams_SetDialog.height = ScreenHeight; // * 7 / 10; //
														// WindowManager.LayoutParams.WRAP_CONTENT;

		mSetDialogFloatView = Set_DialogFloatView.getInstance().getFloatView();

		// mLayoutParams_Set.x = -ScreenHeight;
		// mLayoutParams_Set.y = ScreenWidth / 15;

		if (mWindowManager != null) {

			try {
				if (mSetDialogFloatView.getParent() == null) {

					mWindowManager.addView(mSetDialogFloatView,
							mLayoutParams_SetDialog);

				}
			} catch (Exception e) {
				mWindowManager.addView(mSetDialogFloatView,
						mLayoutParams_SetDialog);

			}

		} else {
			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);

			if (mSetDialogFloatView.getParent() == null) {
				mWindowManager.addView(mSetDialogFloatView,
						mLayoutParams_SetDialog);

			}
		}

	}

	/**
	 * 添加英雄图鉴
	 */
	public static void andHero_Card_FloatView() {

		if (mLayoutParams_Hero_Card == null) {
			mLayoutParams_Hero_Card = new WindowManager.LayoutParams();

			// mLayoutParams_Hero_Card = mLayoutParams;

		}
		mLayoutParams_Hero_Card.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams_Hero_Card.flags = 40;
		mLayoutParams_Hero_Card.format = -3;
		mLayoutParams_Hero_Card.width = myTools.dip2px(95); // ScreenWidth*8/10;
		// WindowManager.LayoutParams.WRAP_CONTENT;
		mLayoutParams_Hero_Card.height = myTools.dip2px(60);
		// WindowManager.LayoutParams.WRAP_CONTENT;

		mHero_Card_FloatView = Hero_Card_FloatView.getInstance().getFloatView();

		// mLayoutParams_Set.x = -ScreenHeight;
		// mLayoutParams_Set.y = ScreenWidth / 15;

		if (mWindowManager != null) {

			try {
				if (mHero_Card_FloatView.getParent() == null) {
					mWindowManager.addView(mHero_Card_FloatView,
							mLayoutParams_Hero_Card);
				}

			} catch (Exception e) {
				mWindowManager.addView(mHero_Card_FloatView,
						mLayoutParams_Hero_Card);
			}

		} else {
			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);

			if (mHero_Card_FloatView.getParent() == null) {
				mWindowManager.addView(mHero_Card_FloatView,
						mLayoutParams_Hero_Card);
			}

		}

	}

	/**
	 * 添加装备图鉴
	 */
	public static void andHeroEquipFloatView() {

		if (mLayoutParams_Equip == null) {
			// mLayoutParams_Equip = mLayoutParams;

			mLayoutParams_Equip = new WindowManager.LayoutParams();

		}

		mLayoutParams_Equip.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams_Equip.flags = 40;
		mLayoutParams_Equip.format = -3;

		mLayoutParams_Equip.width = myTools.dip2px(95); // ScreenWidth*8/10;
		// WindowManager.LayoutParams.WRAP_CONTENT;
		mLayoutParams_Equip.height = myTools.dip2px(60);
		// WindowManager.LayoutParams.WRAP_CONTENT;

		mEquipFloatView = EquipFloatView.getInstance().getFloatView();

		// mLayoutParams_Set.x = -ScreenHeight;
		// mLayoutParams_Set.y = ScreenWidth / 15;

		if (mWindowManager != null) {

			try {
				if (mEquipFloatView.getParent() == null) {
					mWindowManager
							.addView(mEquipFloatView, mLayoutParams_Equip);
				}
			} catch (Exception e) {
				mWindowManager.addView(mEquipFloatView, mLayoutParams_Equip);
			}

		} else {
			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);

			if (mEquipFloatView.getParent() == null) {
				mWindowManager.addView(mEquipFloatView, mLayoutParams_Equip);
			}

		}

	}

	/**
	 * 进阶装备列表
	 */
	public static void andHeroEquipListFloatView() {

		if (mLayoutParams_EquipList == null) {
			mLayoutParams_EquipList = new WindowManager.LayoutParams();

			// mLayoutParams_EquipList = mLayoutParams;

		}
		mLayoutParams_EquipList.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams_EquipList.flags = 40;
		mLayoutParams_EquipList.format = -3;
		mLayoutParams_EquipList.width = ScreenWidth * 6 / 10; // ScreenWidth*8/10;
		// WindowManager.LayoutParams.WRAP_CONTENT;
		mLayoutParams_EquipList.height = // MyTools.dip2px(60);
		WindowManager.LayoutParams.WRAP_CONTENT;

		mEquipListFloatView = EquipListFloatView.getInstance("5")
				.getFloatView();

		// mLayoutParams_Set.x = -ScreenHeight;
		// mLayoutParams_Set.y = ScreenWidth / 15;

		if (mWindowManager != null) {

			try {
				if (mEquipListFloatView.getParent() == null) {
					mWindowManager.addView(mEquipListFloatView,
							mLayoutParams_EquipList);
				}
			} catch (Exception e) {
				mWindowManager.addView(mEquipListFloatView,
						mLayoutParams_EquipList);
			}

		} else {
			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);

			if (mEquipListFloatView.getParent() == null) {
				mWindowManager.addView(mEquipListFloatView,
						mLayoutParams_EquipList);
			}

		}

	}

	/**
	 * 对话框
	 */
	public static void andDialogFloatView() {

		if (mLayoutParams_Dialog == null) {
			mLayoutParams_Dialog = new WindowManager.LayoutParams();

			// mLayoutParams_Dialog = mLayoutParams;

		}
		mLayoutParams_Dialog.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams_Dialog.flags = 40;
		mLayoutParams_Dialog.format = -3;
		mLayoutParams_Dialog.width = // ScreenWidth*8/10;
		WindowManager.LayoutParams.WRAP_CONTENT;
		mLayoutParams_Dialog.height = // MyTools.dip2px(60);
		WindowManager.LayoutParams.WRAP_CONTENT;

		mDialogFloatView = DialogFloatView.getInstance().getFloatView();
		// mLayoutParams_Set.x = -ScreenHeight;
		// mLayoutParams_Set.y = ScreenWidth / 15;

		if (mWindowManager != null) {

			try {
				if (mDialogFloatView.getParent() == null) {
					mWindowManager.addView(mDialogFloatView,
							mLayoutParams_Dialog);
				}
			} catch (Exception e) {
				mWindowManager.addView(mDialogFloatView, mLayoutParams_Dialog);
			}

		} else {
			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);

			if (mDialogFloatView.getParent() == null) {
				mWindowManager.addView(mDialogFloatView, mLayoutParams_Dialog);
			}

		}

	}

	/**
	 * 显示装备列表
	 */
	public static void andEquipmentListFloatView() {

		if (mLayoutParams_EquipmentList == null) {
			mLayoutParams_EquipmentList = new WindowManager.LayoutParams();
			// mLayoutParams_EquipmentList = mLayoutParams;
		}
		mLayoutParams_EquipmentList.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams_EquipmentList.flags = 40;
		mLayoutParams_EquipmentList.format = -3;
		mLayoutParams_EquipmentList.width = ScreenWidth * 95 / 100;
		// WindowManager.LayoutParams.MATCH_PARENT;
		mLayoutParams_EquipmentList.height = ScreenHeight * 9 / 10;
		// MyTools.dip2px(60);
		// WindowManager.LayoutParams.WRAP_CONTENT;

		mEquipmentListFloatView = EquipmentListFloatView.getInstance()
				.getFloatView();
		// mLayoutParams_Set.x = -ScreenHeight;
		// mLayoutParams_Set.y = ScreenWidth / 15;

		if (mWindowManager != null) {
			try {
				if (mEquipmentListFloatView.getParent() == null) {
					mWindowManager.addView(mEquipmentListFloatView,
							mLayoutParams_EquipmentList);
				}
			} catch (Exception e) {
				mWindowManager.addView(mEquipmentListFloatView,
						mLayoutParams_EquipmentList);
			}

		} else {
			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);

			if (mEquipmentListFloatView.getParent() == null) {
				mWindowManager.addView(mEquipmentListFloatView,
						mLayoutParams_EquipmentList);
			}

		}

	}

	/**
	 * 显示装备对话框
	 */
	public static void andEquipmentList_DialogFloatView(String mItemID) {

		if (mLayoutParams_EquipmentList_Dialog == null) {
			mLayoutParams_EquipmentList_Dialog = new WindowManager.LayoutParams();

			// mLayoutParams_EquipmentList_Dialog = mLayoutParams;

		}

		mLayoutParams_EquipmentList_Dialog.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams_EquipmentList_Dialog.flags = 40;
		mLayoutParams_EquipmentList_Dialog.format = -3;
		mLayoutParams_EquipmentList_Dialog.width = // ScreenWidth*8/10;
		WindowManager.LayoutParams.MATCH_PARENT;
		mLayoutParams_EquipmentList_Dialog.height = // MyTools.dip2px(60);
		WindowManager.LayoutParams.MATCH_PARENT;

		// TODO 补齐 参数
		mEquipmentList_DialogFloatView = EquipmentList_DialogFloatView
				.getInstance(mItemID).getFloatView();
		// mLayoutParams_Set.x = -ScreenHeight;
		// mLayoutParams_Set.y = ScreenWidth / 15;

		if (mWindowManager != null) {
			try {

				if (mEquipmentList_DialogFloatView.getParent() == null) {
					mWindowManager.addView(mEquipmentList_DialogFloatView,
							mLayoutParams_EquipmentList_Dialog);
				}

			} catch (Exception e) {

				mWindowManager.addView(mEquipmentList_DialogFloatView,
						mLayoutParams_EquipmentList_Dialog);

			}

		} else {

			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);

			if (mEquipmentList_DialogFloatView.getParent() == null) {
				mWindowManager.addView(mEquipmentList_DialogFloatView,
						mLayoutParams_EquipmentList_Dialog);
			}

		}

	}

	/**
	 * 显示附魔属性对话框
	 */
	public static void andAdditionFloatView() {

		if (mLayoutParams_Addition == null) {
			mLayoutParams_Addition = new WindowManager.LayoutParams();
			// mLayoutParams_Addition = mLayoutParams;
		}
		mLayoutParams_Addition.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams_Addition.flags = 40;
		mLayoutParams_Addition.format = -3;
		mLayoutParams_Addition.width = ScreenWidth * 3 / 10;
		// WindowManager.LayoutParams.WRAP_CONTENT;
		mLayoutParams_Addition.height = // MyTools.dip2px(60);
		WindowManager.LayoutParams.WRAP_CONTENT;

		mAdditionFloatView = AdditionFloatView.getInstance().getFloatView();
		// mLayoutParams_Set.x = -ScreenHeight;
		// mLayoutParams_Set.y = ScreenWidth / 15;

		if (mWindowManager != null) {

			try {
				if (mAdditionFloatView.getParent() == null) {
					mWindowManager.addView(mAdditionFloatView,
							mLayoutParams_Addition);
				}
			} catch (Exception e) {

				mWindowManager.addView(mAdditionFloatView,
						mLayoutParams_Addition);

			}

		} else {

			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);

			if (mAdditionFloatView.getParent() == null) {
				mWindowManager.addView(mAdditionFloatView,
						mLayoutParams_Addition);
			}

		}

	}

	/**
	 * 开始NULL
	 */
	public static void andStartFloatView() {

		if (mLayoutParams_start_null == null) {
			mLayoutParams_start_null = new WindowManager.LayoutParams();

			// mLayoutParams_Set = mLayoutParams;
		}
		mLayoutParams_start_null.type = WindowManager.LayoutParams.TYPE_PHONE;
		mLayoutParams_start_null.flags = 40;
		mLayoutParams_start_null.format = -3;
		mLayoutParams_start_null.width = WindowManager.LayoutParams.MATCH_PARENT;
		mLayoutParams_start_null.height = WindowManager.LayoutParams.MATCH_PARENT;

		mStartFloatView_null = StartFloatView.getInstance().getFloatView();

		if (mWindowManager != null) {

			if (mStartFloatView_null.getParent() == null) {

				try {
					if (mStartFloatView_null.getParent() == null) {

						mWindowManager.addView(mStartFloatView_null,
								mLayoutParams_start_null);

					}

				} catch (Exception e) {
					mWindowManager.addView(mStartFloatView_null,
							mLayoutParams_start_null);

				}

			}

		} else {

			mWindowManager = (WindowManager) mContext
					.getSystemService(Context.WINDOW_SERVICE);
			mWindowManager.addView(mStartFloatView_null,
					mLayoutParams_start_null);

		}

	}

	/**
	 * 开始
	 */
	public static void start() {

		MyFloatServes.sp = MyFloatServes.mContext.getSharedPreferences(
				MyFloatServes.SP_NAME, 0);

		MyFloatServes.onekeyexit = MyFloatServes.sp.getBoolean(
				MyFloatServes.NOEKEYEXIT, true);

		if (MyFloatServes.onekeyexit) {

			andExitFloatView();
			// andExitFloatView2();

		}

		andSetFloatView();

		if (!MyFloatServes.startHook) {

			andStartFloatView();
		}

	}

	public void removeAll() {

		removeView(mGamespeedFloatView);
		removeView(mExitFloatView);
		removeView(mSetFloatView);
		removeView(mRecruitDialogFloatView);

		removeView(mSetDialogFloatView);
		removeView(mHero_Card_FloatView);
		removeView(mEquipFloatView);
		removeView(mEquipListFloatView);

		removeView(mDialogFloatView);
		removeView(mEquipmentListFloatView);
		removeView(mEquipmentList_DialogFloatView);
		removeView(mAdditionFloatView);

		removeView(mGamespeedFloatView_one);

	}

	/**
	 * 从窗口中移除
	 * 
	 * @param view
	 */
	public static void removeView(View view) {

		if (view != null) {
			if (view.getParent() != null) {

				if (mWindowManager != null) {
					mWindowManager.removeView(view);
				}
				// if (mWindowManager == null) {
				// mWindowManager = MyFloatServes.mWindowManager;
				// }
				//

			}

		}

	};

	public static void setOnTouch(final View mView,
			final WindowManager.LayoutParams mwParams) {

		mView.setOnTouchListener(new OnTouchListener() {
			float lastX, lastY;

			public boolean onTouch(View v, MotionEvent event) {
				final int action = event.getAction();

				float x = event.getX();
				float y = event.getY();

				if (action == MotionEvent.ACTION_DOWN) {

					tag = 0;
					lastX = x;
					lastY = y;

				} else if (action == MotionEvent.ACTION_MOVE) {

					x = event.getX();
					y = event.getY();

					if (Math.abs(x - lastX) > 10 | Math.abs(y - lastY) > 10) {
						tag = 1;
						mwParams.x += (int) (x - lastX) / 2;
						mwParams.y += (int) (y - lastY) / 2;

						mWindowManager.updateViewLayout(mView, mwParams);
						return true;
					}

					return false;
				}

				else if (action == MotionEvent.ACTION_UP) {
					x = event.getX();
					y = event.getY();

					ScreenHeight = MyFloatServes.display.getHeight();
					ScreenWidth = MyFloatServes.display.getWidth();

					if (mwParams.x < 0) {

						mwParams.x = -MyFloatServes.display.getWidth();

					}

					if (mwParams.x > 0) {
						mwParams.x = MyFloatServes.display.getWidth();

					}

					MyFloatServes.sp = MyFloatServes.mContext
							.getSharedPreferences(MyFloatServes.SP_NAME, 0);

					Editor mEditor = MyFloatServes.sp.edit();
					mEditor.putInt("paramsx_set", mwParams.x);
					mEditor.putInt("paramsy_set", mLayoutParams_Set.y);
					mEditor.commit();

					mwParams.x = MyFloatServes.sp.getInt("paramsx_set",
							-ScreenHeight);
					mwParams.y = MyFloatServes.sp.getInt("paramsy_set",
							-ScreenWidth / 15);

					
						mWindowManager.updateViewLayout(
								MyWindowManager.mSetFloatView, mwParams);
				

					if ((Math.abs(x - lastX) < 10 & Math.abs(y - lastY) < 10)) {

						if (v.getId() == R.id.tz_dtcq_gamespeed_float_window_small_set_linearlayout
								& tag != 1) {

							if (mView.getParent() != null) {

								if (!MyFloatServes.startHook) {

//									HookAPI.startHook(
//											MyFloatServes.gamePackageName,
//											MyFloatServes.mContext, true);
									MyFloatServes.startHook = true;

								} else {

								}

								SetFloatView.getInstance().remove();

								MyWindowManager.getInstance(
										MyFloatServes.mContext)
										.andGamespeedFloatView_one();

								// TODO 第二版
								// MyWindowManager.getInstance(
								// MyFloatServes.mContext)
								// .andGamespeedFloatView();
								tag = 0;

							}

						}

						return false;

					} else {

						tag = 1;

						// return true;

					}
				}
				return false;
			}
		});
	}

	public static void setExitOnTouch(final View mView,
			final WindowManager.LayoutParams mwParams) {

		mView.setOnTouchListener(new OnTouchListener() {
			float lastX, lastY;

			public boolean onTouch(View v, MotionEvent event) {
				final int action = event.getAction();
				float x = event.getX();
				float y = event.getY();

				if (action == MotionEvent.ACTION_DOWN) {

					tag = 0;
					lastX = x;
					lastY = y;

				} else if (action == MotionEvent.ACTION_MOVE) {

					x = event.getX();
					y = event.getY();

					if (Math.abs(x - lastX) > 10 | Math.abs(y - lastY) > 10) {
						tag = 1;
						mwParams.x += (int) (x - lastX) / 2;
						mwParams.y += (int) (y - lastY) / 2;

						ScreenHeight = MyFloatServes.display.getHeight();
						ScreenWidth = MyFloatServes.display.getWidth();

						mWindowManager.updateViewLayout(mView, mwParams);

						return true;

					}

					return false;
				}

				else if (action == MotionEvent.ACTION_UP) {

					x = event.getX();
					y = event.getY();

					MyFloatServes.sp = MyFloatServes.mContext
							.getSharedPreferences(MyFloatServes.SP_NAME, 0);

					Editor editor = MyFloatServes.sp.edit();

					ScreenHeight = MyFloatServes.display.getHeight();
					ScreenWidth = MyFloatServes.display.getWidth();

					if (mwParams.x < 0) {

						mwParams.x = -MyFloatServes.display.getWidth();

					}

					if (mwParams.x > 0) {
						mwParams.x = MyFloatServes.display.getWidth();

					}

					editor.putInt("paramsx_exit", mwParams.x);
					editor.putInt("paramsy_exit", mwParams.y);

					editor.commit();

					mwParams.x = MyFloatServes.sp.getInt("paramsx_exit",
							-ScreenHeight);
					mwParams.y = MyFloatServes.sp.getInt("paramsy_exit",
							-ScreenWidth / 8);

					
						mWindowManager.updateViewLayout(
								MyWindowManager.mExitFloatView, mwParams);
				

					if (v.getId() == R.id.tz_dtcq_gamespeed_float_window_small_quit_linearlayout
							& tag != 1) {

						if ((Math.abs(x - lastX) < 10 & Math.abs(y - lastY) < 10)) {

							if (mView.getParent() != null) {

								mKillThead = new KillThead();
								mKillThead.start();

								MyFloatServes.isbegein = false;

								MyFloatServes.startHook = false;

								MyWindowManager.getInstance(
										MyFloatServes.mContext).removeAll();

								tag = 0;

							}

							return false;

						} else {

							tag = 1;
						}

					}

				}
				return false;
			}
		});
	}

	public static class KillThead extends Thread {

		public KillThead() {
			super();

		}

		@Override
		public void run() {

			ExitFloatView.killProcess(MyFloatServes.gamePackageName);

		//	HookAPI.setExit();

			// Log.i("speed_EXIT","HOOKEXIT" );
			super.run();
		}

	}

}
