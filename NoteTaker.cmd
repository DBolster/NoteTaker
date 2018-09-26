@echo off
SETLOCAL ENABLEEXTENSIONS

set arg1=%1
set arg2=%2

if arg1="" GOTO :ONEARG
if arg2="" GOTO :TWOARG

:ONEARG
IF %arg1%=="-h" (
java -jar NoteTaker -h 
)
if %arg1%=="-nn" (
java -jar NoteTaker -lc
)

:TWOARG 
if %arg1%=="-ac" (
java -jar NoteTaker -ac %arg2%
)
if %arg1%=="-nn" (
java -jar NoteTaker -nn %arg2%
)

ENDLOCAL
