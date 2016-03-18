package com.nubia.uiautotest.rules;


import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


import com.nubia.uiautotest.utils.CommonUtil;
import com.nubia.uiautotest.utils.DateUtil;
import com.nubia.uiautotest.utils.ScreenShotUtil;


/**
 * 简介
 * 断言——String包含
 * 断言——String反向包含
 * 断言——String相同
 * 断言——int相等
 * 断言——正则匹配
 * 断言——布尔正确
 * @author panyanan luojinghua
 *
 */


public class AssertCollector  extends UiAutomatorTestCase {
	

	/**
	 * 断言-不终止流程
	 * String判断
	 * @param expected——预期
	 * @param actual——实际
	 * @param className——getClass().getName()
	 * @param uiDevice——getUidevice()
	 * @param message——检查点命名
	 */
	public static void assertEqual(String expected ,String actual, String className,UiDevice uiDevice, String message){
		try {
			if(expected.equals(actual)){
				System.out.println("检查点\""+message+"\"成功");
			}
			else{
				ScreenShotUtil.takeshot(uiDevice, className);
				System.out.println("检查点\""+message+"\"失败: 预期:<["+expected+"]> 实际:<["+actual+"]>"+"失败时间是"+DateUtil.getDate());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 断言-不终止流程
	 * Int判断
	 * @param expected
	 * @param actual——实际
	 * @param className——getClass().getName()
	 * @param uiDevice——getUidevice()
	 * @param message——检查点命名
	 */
	public static void assertEqual(int expected ,int actual,String className,UiDevice uiDevice, String message){
		try {
			if(expected==actual){
				System.out.println("检查点\""+message+"\"成功");
			}
			else{
				ScreenShotUtil.takeshot(uiDevice, className);
				System.out.println("检查点\""+message+"\"失败: 预期:<["+expected+"]> 实际:<["+actual+"]>"+"失败时间是"+DateUtil.getDate());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 断言-不终止流程
	 * 布尔判断
	 * @param actual——实际
	 * @param className——getClass().getName()
	 * @param uiDevice——getUidevice()
	 * @param message——检查点命名
	 */
	public static void assertTrue(boolean actual,String className,UiDevice uiDevice, String message){
		try {
			if(actual){
				System.out.println("检查点\""+message+"\"成功");
			}
			else{
				ScreenShotUtil.takeshot(uiDevice, className);
				System.out.println("检查点\""+message+"\"失败: 预期:<[true]>]> 实际:<[false]>"+"失败时间是"+DateUtil.getDate());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 断言-不终止流程
	 * 正则匹配判断
	 * @param regxp
	 * @param actual——实际
	 * @param className——getClass().getName()
	 * @param uiDevice——getUidevice()
	 * @param message——检查点命名
	 */
	public static void assertMatch(String regxp,String actual,String className,UiDevice uiDevice, String message){
		try {
			if(CommonUtil.regex(regxp, actual)){
				System.out.println("检查点\""+message+"\"成功");
			}
			else{
				ScreenShotUtil.takeshot(uiDevice, className);
				System.out.println("检查点\""+message+"\"失败: 预期:<["+regxp+"]> 实际:<["+actual+"]>"+"失败时间是"+DateUtil.getDate());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 断言-不终止流程
	 * String包含判断（实际包括预期）
	 * @param expected
	 * @param actual——实际
	 * @param className——getClass().getName()
	 * @param uiDevice——getUidevice()
	 * @param message——检查点命名
	 */
	public static void assertContains(String expected ,String actual,String className,UiDevice uiDevice, String message){
		try {
			if(actual.contains(expected)){
				System.out.println("检查点\""+message+"\"成功");
				//return true;
			}
			else{
				ScreenShotUtil.takeshot(uiDevice, className);
				System.out.println("检查点\""+message+"\"失败: 预期:<["+expected+"]> 实际:<["+actual+"]>"+"失败时间是"+DateUtil.getDate());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 断言-不终止流程
	 * String反向包含判断（预期包括实际）
	 * @param expected
	 * @param actual——实际
	 * @param className——getClass().getName()
	 * @param uiDevice——getUidevice()
	 * @param message——检查点命名
	 */
	public static void assertBackContains(String expected ,String actual,String className,UiDevice uiDevice, String message){
		try {
			if(expected.contains(actual)){
				System.out.println("检查点\""+message+"\"成功");
				//return true;
			}
			else{
				ScreenShotUtil.takeshot(uiDevice, className);
				System.out.println("检查点\""+message+"\"失败: 预期:<["+expected+"]> 实际:<["+actual+"]>"+"失败时间是"+DateUtil.getDate());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
