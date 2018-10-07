@echo off
SETLOCAL ENABLEEXTENSIONS

set arg1=%1
set arg2=%2

java -jar NoteTaker.jar %arg1% %arg2%

ENDLOCAL
