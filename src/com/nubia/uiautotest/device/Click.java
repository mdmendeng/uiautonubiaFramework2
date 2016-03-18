package com.nubia.uiautotest.device;


import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Click  extends UiAutomatorTestCase{
	/**
	 * 控件长按操作
	 * @param ud
	 * @param uiObject
	 * @param steps
	 * @throws UiObjectNotFoundException
	 */
	public  static void longClick(UiDevice ud, UiObject uiObject,int steps) throws UiObjectNotFoundException{
		ud.swipe(uiObject.getBounds().centerX(), uiObject.getBounds().centerY(),
				uiObject.getBounds().centerX(), uiObject.getBounds().centerY(), steps);
	}
	
	/**
	 * 双击操作
	 * @param uiObject
	 * @param timeDelay
	 * @throws UiObjectNotFoundException
	 */
	
	public static void uiObjectDoubleClick(UiObject uiObject,int timeDelay) throws UiObjectNotFoundException{
		uiObject.click();
		uiObject.click();
	}
	
}
