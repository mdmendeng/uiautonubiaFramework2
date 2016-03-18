package com.nubia.uiautotest.locators;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.nubia.uiautotest.*;

/**
 * 简介：
 * UiObject
 * UiScrollable
 * @author panyanan
 *
 */
public class ElementLocator extends UiSelector {
	
	//UiObject
	public static UiObject textObject(String string){
		return new UiObject(ElementSelector.textSelector(string));
	}
	public static UiObject textMatchsObject(String string){
		return new UiObject(ElementSelector.textMatchs(string));
	}
	public static UiObject textContainsObject(String string){
		return new UiObject(ElementSelector.textContains(string));
	}
	public static UiObject resourceIdObject(String string){
		return new UiObject(ElementSelector.resourceIdSelector(string));
	}
	public static UiObject resourceIdMatchsObject(String string){
		return new UiObject(ElementSelector.resourceIdMatchs(string));
	}
	public static UiObject classNameObject(String string){
		return new UiObject(ElementSelector.classNameSelector(string));
	}
	public static UiObject classNameMatchesObject(String string){
		return new UiObject(ElementSelector.classNameMatches(string));
	}
	public static UiObject packageNameObject(String string){
		return new UiObject(ElementSelector.packageNameSelector(string));
	}
	
	//UiScrollable
	public static UiScrollable textScrollable(String string){
		return new UiScrollable(ElementSelector.textSelector(string));
	}
	public static UiObject textMatchsScrollable(String string){
		return new UiScrollable(ElementSelector.textMatchs(string));
	}
	public static UiScrollable resourceIdScrollable(String string){
		return new UiScrollable(ElementSelector.resourceIdSelector(string));
	}
	public static UiObject resourceIdMatchsScrollable(String string){
		return new UiScrollable(ElementSelector.resourceIdMatchs(string));
	}
	public static UiScrollable classNameScrollable(String string){
		return new UiScrollable(ElementSelector.classNameSelector(string));
	}
	public static UiObject classNameMatchsScrollable(String string){
		return new UiScrollable(ElementSelector.classNameMatches(string));
	}
}
