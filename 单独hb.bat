@echo off
::set /p t=input packge name!
::��Ŀ����
set t=uiautonubiaFramework2
::log�ض����ļ�����
set rp=UIAReport.txt
adb shell rm -r sdcard/uiauto
call android create uitest-project -n %t% -t 2 -p D:\workspace\%t%
call pushd D:\workspace\%t%
call ant build
adb push D:\workspace\%t%\bin\%t%.jar data/local/tmp  >>%rp%
::��ӡʱ��
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
echo ���Բ�����%aa%
echo %date%%time% >>%rp%
@echo ����ִ�е�������
adb push D:\workspace\utilTest\testSteps.txt sdcard/testSteps.txt
@adb shell uiautomator runtest %t%.jar -c com.nubia.TestAreyouok >>%rp%
::@adb shell uiautomator runtest %t%.jar --nohup -c com.uiauto.TestNewAll2 >>%rp% testEasyFramework
::@adb shell uiautomator runtest %t%.jar --nohup -c com.uiauto.TakeAphoto >>%rp%

@echo �������
cmd.exe