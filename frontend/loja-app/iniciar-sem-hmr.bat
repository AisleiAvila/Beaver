@echo off
echo ========================================
echo Iniciando aplicação sem HMR e com limpeza de cache
echo ========================================

REM Execute este script em uma janela CMD separada

echo 1. Tentando limpar processos Node.js (exceto este)...
for /f "tokens=2" %%a in ('tasklist /fi "imagename eq node.exe" ^| findstr /i /c:"node.exe"') do (
    echo Tentando finalizar processo Node.js %%a...
    taskkill /F /PID %%a /T 2>nul
)

echo 2. Removendo cache do Angular...
rmdir /s /q ".angular\cache" 2>nul

echo 3. Aguardando 3 segundos...
timeout /t 3 /nobreak >nul

echo 4. Criando diretório de cache limpo...
mkdir ".angular\cache" 2>nul

echo 5. Iniciando aplicação Angular sem HMR...
echo.
echo ========================================
echo Aplicação Angular iniciando sem HMR...
echo ========================================
echo.

ng serve --no-hmr
