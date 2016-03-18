package com.nubia.uiautotest.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.gesture.Gesture;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.nubia.uiautotest.device.Gestures;
import com.nubia.uiautotest.locators.ElementLocator;
import com.nubia.uiautotest.locators.ElementSelector;

public class CommonUtil  extends UiAutomatorTestCase {
	
	/**
	 * 切换默认输入法方法
	 * @param uiDevice
	 * @throws UiObjectNotFoundException
	 */
	public void inputMethonSwitch(UiDevice uiDevice) throws UiObjectNotFoundException{
		PackageUtil packageUtil = new PackageUtil();
		uiDevice.pressHome();
		SystemUtil.cmdImput(packageUtil.getSettings());
		sleep(1000);
		do{
			Gestures.scroll(uiDevice, 3, 2);
		}
		while(!ElementLocator.textContainsObject("其他").exists());
		sleep(200);
		new UiObject(new UiSelector().textContains("其他")).click();
		sleep(500);
		new UiObject(new UiSelector().textContains("语言")).click();
		System.out.println(ElementLocator
				.textObject("当前输入法")
				.getFromParent(
						ElementSelector.resourceIdSelector("android:id/summary"))
				.getText());
		ElementLocator.textObject("当前输入法").click();
		ElementLocator.textObject("英语(美国)").click();
		sleep(500);
		uiDevice.pressHome();
	}
	
	/**
	 * 切换输入法为中文Utf7Ime输入法
	 * 
	 * 1.在批处理程序中增加一行，安装输入法
	 * @adb install \\10.204.68.133\外包工作记录\测试小组-潘亚男\自动化工具\应用\Utf7Ime.apk
	 * 2.在测试类测试准备中增加一行，修改输入法为此输入法：	
	 * common.inputMethonSwitchUtf7Ime(getUiDevice());
	 * 3.将helper-library.jar加入到工程lib中
	 * \\10.204.68.133\外包工作记录\测试小组-潘亚男\自动化工具\应用\Utf7Ime
	 * 4.在使用中文的地方使用即可输入汉字
	 * Location.resourceIdObject("XXX").setText(Utf7ImeHelper.e("汉字输入yingyushuru"));
	 * 
	 * @param uiDevice
	 * @throws UiObjectNotFoundException
	 */
	public void inputMethonSwitchChinese(UiDevice uiDevice) throws UiObjectNotFoundException{
		PackageUtil packageEvent = new PackageUtil();
		uiDevice.pressHome();
		SystemUtil.cmdImput(packageEvent.getSettings());
		sleep(1000);
		Gestures.scroll(uiDevice, 3, 2);
		sleep(200);
		new UiObject(new UiSelector().textContains("其他")).click();
		sleep(500);
		new UiObject(new UiSelector().textContains("语言")).click();
		sleep(500);
		new UiObject(new UiSelector().textContains("UTF7")).click();
		if(ElementLocator.resourceIdObject("nubia:id/nubia_button1").exists()){
			ElementLocator.resourceIdObject("nubia:id/nubia_button1").click();//确认对话框
			}
		else{
			ElementLocator.resourceIdObject("android:id/button1").click();}
		sleep(1000);
		ElementLocator.textObject("当前输入法").click();
		sleep(3000);
		new UiObject(new UiSelector().textContains("UTF7")).click();
		sleep(500);
		uiDevice.pressHome();
	}
	
	/**
	 * 任意位置一键加速删除后台
	 * @param ud
	 * @throws UiObjectNotFoundException
	 */
	public void OneKeyClean(UiDevice ud) throws UiObjectNotFoundException{
		sleep(1000);
		ud.openQuickSettings();
		sleep(1500);
		if(ElementLocator.resourceIdObject("com.android.systemui:id/dismiss_text").exists()){//清理通知栏消息
			sleep(1000);
			ElementLocator.resourceIdObject("com.android.systemui:id/dismiss_text").click();
		sleep(500);
		ud.openQuickSettings();
		sleep(1500);
		}
		if(ElementLocator.resourceIdObject("com.android.systemui:id/quick_settings_tips").exists()){//处理首次提示
			ElementLocator.resourceIdObject("com.android.systemui:id/quick_settings_tips").click();
		}
		else{
			do{
				ElementLocator.resourceIdObject("com.android.systemui:id/qs_tips_arrow").click();//第二层下拉框
			}
			while(!ElementLocator.textContainsObject("一键").exists());
		}
		sleep(1000);
		ElementLocator.textContainsObject("一键").click();//一键清理
		sleep(2500);
	}
	
	/**
	 * 相机欢迎界面
	 * @param ud
	 * @throws UiObjectNotFoundException
	 */
	public void skipWelcomeView(UiDevice ud) throws UiObjectNotFoundException{
		sleep(3000);
		if(ElementLocator.resourceIdObject("com.android.camera:id/ok_button").exists()){
			do{
				ElementLocator.resourceIdObject("com.android.camera:id/ok_button").click();
			}
			while(ElementLocator.resourceIdObject("com.android.camera:id/ok_button").exists());
			sleep(500);
			do{
			Gestures.scroll(ud, 0, 1);//向右滑动
			sleep(500);
			}
			while(ElementLocator.resourceIdObject("com.android.camera:id/welcome1_text_layout").exists());//没滑动继续滑
			ElementLocator.classNameObject("android.widget.TextView").click();
		}
		else if (ElementLocator.resourceIdObject("com.android.camera:id/camera_welcome_viewpager").exists()){
			sleep(500);
			do{
			Gestures.scroll(ud, 0, 1);//向右滑动
			sleep(500);
			}
			while(ElementLocator.resourceIdObject("com.android.camera:id/welcome1_text_layout").exists());
			ElementLocator.classNameObject("android.widget.TextView").click();
		}
		
	}
	
	/**
	 * 正则匹配方法
	 * @param regxp
	 * @param actual
	 * @return
	 */
	public static boolean regex(String regxp,String actual){
        Pattern p = null; //正则表达式      
        Matcher m = null; //操作的字符串  
        boolean b = false;
        p = Pattern.compile(regxp);    
        m = p.matcher(actual);    
        b = m.matches();    
        return b;
    }
}
