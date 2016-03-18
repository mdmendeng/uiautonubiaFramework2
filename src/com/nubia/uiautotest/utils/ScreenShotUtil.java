package com.nubia.uiautotest.utils;

import java.io.File;

import android.os.RemoteException;

import com.android.uiautomator.core.Configurator;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;

public class ScreenShotUtil {
	/**
	 * 截图方法
	 * @param uiDevice
	 * @param classname
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	public static void takeshot(UiDevice uiDevice, String classname) throws RemoteException, UiObjectNotFoundException{
		String file = classname.split("\\.")[classname.split("\\.").length-1];
		uiDevice.takeScreenshot(new File("/storage/sdcard0/uiauto/"+file+"/error"+DateUtil.getDate()+".png"));
	}
	
	/**
	 * 自定义命名截图方法+日期名称
	 * @param uiDevice
	 * @param classname
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	public static void takeshotByDate(UiDevice uiDevice, String classname,String picName) throws RemoteException, UiObjectNotFoundException{
		String file = classname.split("\\.")[classname.split("\\.").length-1];
		uiDevice.takeScreenshot(new File("/storage/sdcard0/uiauto/"+file+"/"+picName+DateUtil.getDate()+".png"));
	}
	
	/**
	 * 自定义命名截图方法
	 * @param uiDevice
	 * @param classname
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	public static void takeshot(UiDevice uiDevice, String classname,String picName) throws RemoteException, UiObjectNotFoundException{
		String file = classname.split("\\.")[classname.split("\\.").length-1];
		uiDevice.takeScreenshot(new File("/storage/sdcard0/uiauto/"+file+"/"+picName+".png"));
	}

}
