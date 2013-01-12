@echo off
set DIRNAME=%~dp0%
set HOME=%DIRNAME%..
set CLASSPATH=%HOME%/lib/jline.jar;%HOME%/lib/core.jar
java -cp %CLASSPATH% org.jledit.main.Main %*