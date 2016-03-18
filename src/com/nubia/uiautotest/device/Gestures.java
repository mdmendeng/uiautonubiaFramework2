package com.nubia.uiautotest.device;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.nubia.uiautotest.locators.ElementLocator;

public class Gestures  extends UiAutomatorTestCase{
	
	/**
	 * 滑动解锁
	 * @param uiDevice
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	public static void unLock(UiDevice uiDevice) throws RemoteException, UiObjectNotFoundException{
		float width = uiDevice.getDisplayWidth();
		float height = uiDevice.getDisplayHeight();
		
		if(!uiDevice.isScreenOn()){
			uiDevice.wakeUp();
			do{
				uiDevice.swipe((int) (width / 2),(int) (height / 20 * 19), 
								(int) (width / 2),(int) (height / 2), 50);// 滑动
			}
			while(ElementLocator.resourceIdObject("com.android.systemui:id/notification_stack_scroller").exists());
		}
		else if(uiDevice.isScreenOn()&&ElementLocator.resourceIdObject("com.android.systemui:id/notification_stack_scroller").exists()){
			do{
				uiDevice.swipe((int) (width / 2),(int) (height / 20 * 19), 
								(int) (width / 2),(int) (height / 2), 50);// 滑动
			}
			while(ElementLocator.resourceIdObject("com.android.systemui:id/notification_stack_scroller").exists());
		}
	}
	
	/**
	 * 搜索桌面图标点击方法
	 * @param string
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public static UiObject searchAndClick(String string , UiDevice uiDevice) throws UiObjectNotFoundException{
		uiDevice.pressHome();
		if(!ElementLocator.textObject(string).exists()){
		int i = 0;
		do{
			scroll(uiDevice, 0, 1);
			++i;
			}while(!ElementLocator.textObject(string).exists()&&i<5);
		}
		ElementLocator.textObject(string).click();
		return null;
	}
	
	/**
	 * 屏幕四向滑屏
	 * 滑动以1/10为单位
	 * @param ud
	 * @param direction——方向
	 * @param steps——滑动次数
	 * @throws UiObjectNotFoundException
	 */
	
	public static  void scroll(UiDevice ud , int direction , int steps) throws UiObjectNotFoundException{
		for(int i=0 ;i<steps;i++){
			if(direction==0){
				ud.swipe(ud.getDisplayWidth() * 9 / 10,
						ud.getDisplayHeight() / 2,
						ud.getDisplayWidth() * 1 / 10,
						ud.getDisplayHeight() / 2, 100);//向屏幕左边
			}
			else if(direction==1){
				ud.swipe(ud.getDisplayWidth() * 1 / 10,
				ud.getDisplayHeight() / 2,
				ud.getDisplayWidth() * 9 / 10,
				ud.getDisplayHeight() / 2, 100);//向屏幕右边
			}
			else if(direction==2){
				ud.swipe(ud.getDisplayWidth() / 2,
				ud.getDisplayHeight() * 1 / 10,
				ud.getDisplayWidth() / 2,
				ud.getDisplayHeight() * 9 / 10, 100);//向屏幕上边
			}
			else if(direction==3){
				ud.swipe(ud.getDisplayWidth() / 2,
				ud.getDisplayHeight() * 9 / 10,
				ud.getDisplayWidth() / 2,
				ud.getDisplayHeight() * 1 / 10, 100);//向屏幕下边
			}
			else{System.out.println("请输入方向为：0-右边，1-左边， 2-手指向下，3-手指向上");}
		}
	}


	
	/**
	 * 滑动选择日期控件
	 * 每次滑动一格，为110像素
	 * @param uiObject
	 * @param count
	 * @param direction
	 * @throws UiObjectNotFoundException
	 */
	
	public static void dataPicker(UiObject uiObject, int count , int direction) throws UiObjectNotFoundException{
		int x = uiObject.getBounds().centerX();
		int y = uiObject.getBounds().centerY();		
		for(int i = 0; i<count ;i++){	
			if(direction == 1){
			uiObject.dragTo(x, y-110, 20);
			}
			else if (direction ==0){
				uiObject.dragTo(x, y+110, 20);
			}
			else{
				System.out.println("请输入方向为：1-手指向上滑，0-手指向下滑");
			}
		}
	}
	
	/**
	 * 滑动对象到SlidLength像素长度
	 * @param uiObject
	 * @param count
	 * @param direction
	 * @throws UiObjectNotFoundException
	 */
	
	public static void swipTo(UiObject uiObject, int SlidLength , int direction) throws UiObjectNotFoundException{
		int x = uiObject.getBounds().centerX();
		int y = uiObject.getBounds().centerY();		
		if(direction == 1){
			uiObject.dragTo(x, y-SlidLength, 20);
			}
		else if (direction ==0){
			uiObject.dragTo(x, y+SlidLength, 20);
			}
		else{
			System.out.println("请输入方向为：1-手指向上滑，0-手指向下滑");
		}
	}
	/**
	 * 多点触控操作
	 * 两点放大缩小
	 * 三点移动
	 * 
	 */
	public static void ZoomZoom(UiObject uiObject)throws UiObjectNotFoundException{
//		uiObject.pinchOut(30, 5)//还未完成
	}

}
