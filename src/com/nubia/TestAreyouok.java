package com.nubia;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.nubia.uiautotest.locators.ElementLocator;
import com.nubia.uiautotest.utils.SystemUtil;

public class TestAreyouok extends UiAutomatorTestCase {

	@Override  
    protected void setUp() throws Exception {		
        System.out.println("做一些前提条件的设置");
        sleep(500); 
    }  
    @Override  
    protected void tearDown() throws Exception {  
        System.out.println("释放一些资源");        
    }  
	
	public void testDemo() throws UiObjectNotFoundException, RemoteException{
		try{
			allTest();
		}catch(Exception e){
			e.printStackTrace();
			}
		finally{
			//getUiDevice().pressHome();
		}
	}
	
	public void allTest() throws Exception{
		System.out.println("ssssss");
//		getUiDevice().pressHome();
		String wx = "am start -n com\\.tencent.mm/.ui\\.LauncherUI";
		SystemUtil.cmdImput(wx);
		System.out.println("sssss");
//		if(ElementLocator.resourceIdObject("com.tencent.mm:id/du").exists()){//在wx界面
//			ElementLocator.textObject("潘亚男").click();
//			System.out.println("ssss");
//		}
		if(ElementLocator.resourceIdObject("com.tencent.mm:id/u9").waitForExists(1500)){//聊天界面
//			UiCollection  rbs= new UiCollection(new UiSelector().resourceId("com.tencent.mm:id/wk"));
//			rbs.
			for(int i = 0;i<10000;i++){
				if(ElementLocator.textObject("微信红包").exists()){
					System.out.println("ss");
					ElementLocator.textObject("微信红包").click();
					if(ElementLocator.resourceIdObject("com.tencent.mm:id/aub").exists()){//是已领取
						getUiDevice().pressBack();
					}
					if(ElementLocator.textObject("看看大家的手气").exists()){//领完了
						getUiDevice().pressBack();
					}
					else if(ElementLocator.resourceIdObject("com.tencent.mm:id/aww").exists()){//有可以打开的按钮
						ElementLocator.resourceIdObject("com.tencent.mm:id/aww").click();
						sleep(1000);
						getUiDevice().pressBack();
					}
					else{
						getUiDevice().pressBack();
					}
				}
				else{
					sleep(100);
				}
			}
		}
		else{
			System.out.println("没有这个包");
		}
	}
}
