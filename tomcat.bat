del *.log

set CATALINA_HOME=D:\Servers\apache-tomcat-7.0.14
set JAVA_HOME=D:\Program Files\Java\jdk1.6.0_22
set path=%JAVA_HOME%\bin

call %CATALINA_HOME%\bin\startup.bat -config E:\Projects\pentair\rfq\tomcat.xml
