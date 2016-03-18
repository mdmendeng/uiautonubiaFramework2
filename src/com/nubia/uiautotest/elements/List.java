package com.nubia.uiautotest.elements;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class List   extends UiAutomatorTestCase{
	
	public static int listChildCount(UiObject uiObject) throws RemoteException, UiObjectNotFoundException{
		
		if(uiObject.getClassName().contains("ListView")){
			return uiObject.getChildCount();
		}
		else{
			return 0;	
		}
	}
	
}
