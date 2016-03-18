@echo off
::set /p t=input packge name!
::项目名称
set t=uiautonubiaFramework2
::log重定向文件名称
set rp=UIAReport.txt
adb shell rm -r sdcard/uiauto
call android create uitest-project -n %t% -t 2 -p D:\workspace\%t%
call pushd D:\workspace\%t%
call ant build
adb push D:\workspace\%t%\bin\%t%.jar data/local/tmp  >>%rp%
::打印时间
java -jar ReadEasyFrameworkStep.jar
@echo off & setlocal EnableDelayedExpansion
set j=0
set aa=111
for /f "delims=""" %%i in (testSteps.txt) do (
set /a j+=1
set con!j!=%%i
call set a=%%con!j!%%
set aa=!a!
)
echo 测试步骤是%aa%
echo %date%%time% >>%rp%
@echo 正在执行单个测试
adb push D:\workspace\utilTest\testSteps.txt sdcard/testSteps.txt
@adb shell uiautomator runtest %t%.jar -c com.nubia.TestAreyouok >>%rp%
::@adb shell uiautomator runtest %t%.jar --nohup -c com.uiauto.TestNewAll2 >>%rp% testEasyFramework
::@adb shell uiautomator runtest %t%.jar --nohup -c com.uiauto.TakeAphoto >>%rp%

@echo 测试完成
cmd.exe