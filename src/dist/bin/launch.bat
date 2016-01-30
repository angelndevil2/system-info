set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.

%DIRNAME%system-info.bat -d %DIRNAME%.. $*
