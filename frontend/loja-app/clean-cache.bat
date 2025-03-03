@echo off
echo Cleaning Angular cache...

REM Try normal delete first
rmdir /s /q ".angular\cache" 2>nul

REM If that failed, try with a delay and retry
timeout /t 2 /nobreak >nul
rmdir /s /q ".angular\cache" 2>nul

REM Create fresh directory
mkdir ".angular\cache" 2>nul

echo Done!
