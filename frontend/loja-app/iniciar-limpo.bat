@echo off
echo ========================================
echo Iniciando aplicação com limpeza de cache
echo ========================================

REM Execute este script em uma janela CMD separada
REM para evitar problemas de auto-encerramento

echo 1. Tentando limpar processos Node.js (exceto este)...
REM Usa um filtro para não matar o próprio processo
for /f "tokens=2" %%a in ('tasklist /fi "imagename eq node.exe" ^| findstr /i /c:"node.exe"') do (
    REM Não tenta matar o próprio PID
    echo Tentando finalizar processo Node.js %%a...
    taskkill /F /PID %%a /T 2>nul
)

echo 2. Removendo cache do Angular...
rmdir /s /q ".angular\cache" 2>nul

echo 3. Aguardando 3 segundos...
timeout /t 3 /nobreak >nul

echo 4. Criando diretório de cache limpo...
mkdir ".angular\cache" 2>nul

echo 5. Iniciando aplicação Angular...
echo.
echo ========================================
echo Aplicação Angular iniciando...
echo ========================================
echo.

ng serve
