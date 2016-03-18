package com.nubia.uiautotest.watcher;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.nubia.uiautotest.device.Gestures;
import com.nubia.uiautotest.locators.ElementLocator;
import com.nubia.uiautotest.utils.ScreenShotUtil;

public class Watcher {
	/**
	 * 监听任何信息方法
	 * @param uiDevice
	 * @param className
	 * @param info
	 * @param btnName
	 */
	public void watcherTheInfo(final UiDevice uiDevice, final String className, final String info, final String btnName) {
		UiDevice.getInstance().registerWatcher("watcherTheInfo",new UiWatcher() {
			UiObject rejectBTN = ElementLocator.textObject(info); 
			@Override
			public boolean checkForCondition() {
				if (rejectBTN.exists()) {
					try {
						ScreenShotUtil.takeshot(uiDevice, className);
						ElementLocator.textObject(btnName).click();
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					return true;
				}
				return false;
			}
		});
	}
	

	/**
	 * 报错监听方法
	 * @param uiDevice
	 * @param className
	 */
	public static void watcherTheError(final UiDevice uiDevice, final String className) {
		UiDevice.getInstance().registerWatcher("watcherTheError",new UiWatcher() {
			UiObject rejectBTN = new UiObject(new UiSelector().textContains("停止运行")); 
			@Override
			public boolean checkForCondition() {
				if (rejectBTN.exists()) {
					try {
						ScreenShotUtil.takeshot(uiDevice, className);
						ElementLocator.resourceIdObject("android:id/button1").click();
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					return true;
				}
				return false;
			}
		});
	}

	
	/**
	 * 电话监听方法
	 * 滑动挂掉来电
	 * @param uiDevice
	 * @param className
	 */
	public static void watcherThePhone(final UiDevice uiDevice, final String className) {
		UiDevice.getInstance().registerWatcher("watcherThePhone",new UiWatcher() {
			UiObject rejectBTN = ElementLocator.packageNameObject("com.android.incallui"); 
			@Override
			public boolean checkForCondition() {
				if (rejectBTN.exists()) {
					try {
						ScreenShotUtil.takeshot(uiDevice, className);
						Gestures.swipTo(ElementLocator.resourceIdObject("com.android.incallui:id/nNBKeyguardIcons"), 400, 1);
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					return true;
				}
				return false;
			}
		});
	}
	
	/**
	 * 搜狗弹框监听方法
	 * @param uiDevice
	 * @param className
	 * @throws UiObjectNotFoundException 
	 */
	public static void watchersogou(final UiDevice uiDevice, final String className) {
		UiDevice.getInstance().registerWatcher("watcherTheSogou",new UiWatcher() {
					UiObject rejectBTN = ElementLocator.packageNameObject("com.sohu.inputmethod.sogou");
			@Override
			public boolean checkForCondition() {
				if (rejectBTN.exists()) {
					try {
						ScreenShotUtil.takeshot(uiDevice, className);
						ElementLocator.textObject("允许").click();
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					} catch (RemoteException e) {
						e.printStackTrace();
					} 
					return true;
				}
				return false;
			}
		});
	}

}
