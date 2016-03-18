package com.nubia.uiautotest.utils;

import android.sax.ElementListener;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.nubia.uiautotest.locators.ElementLocator;

/**
 * 	adb shell screenrecord  --time-limit 10 /sdcard/demo.mp4——设置10秒的录制时间
	adb shell ime list -s——列出当前输入法
	adb shell ime set com.android.inputmethod.latin/.LatinIME——切换为**输入法
	adb shell am start -a android.intent.action.VIEW -d http://testerhome.com——启动默认浏览器打开一个网页
	adb shell am start -a android.intent.action.CALL -d tel:10086——启动拨号器拨打 10086
 * @author 潘亚男
 *
 */

public class Shell extends UiAutomatorTestCase {
	public static void openActivity(String activityName){
		SystemUtil.cmdImput("am start --activity-single-top -n "+activityName);
	}
	 
	
	public static void inputText(String text){
		SystemUtil.cmdImput("input text "+text);
	}
	
	public static void screenRecord10S(String videoName){
		SystemUtil.cmdImput("screenrecord  --time-limit 10 /sdcard/"+videoName+".mp4");
	}
	
	public static void setInputMethod(String inputmethodName){
		SystemUtil.cmdImput("ime set "+inputmethodName);
	}
	
	public static void openUrl(String Url) throws UiObjectNotFoundException{
		SystemUtil.cmdImput("am start -a android.intent.action.VIEW -d http://"+Url);
		ElementLocator.textObject("浏览器").waitForExists(3000);
		if(ElementLocator.textObject("浏览器").exists()){
			ElementLocator.textObject("浏览器").click();
			ElementLocator.resourceIdObject("nubia:id/nubia_button_always").click();
		}
	}
	
	public static void phoneCall(String callNum){
		SystemUtil.cmdImput("am start -a android.intent.action.CALL -d tel:"+callNum);
	}
}
