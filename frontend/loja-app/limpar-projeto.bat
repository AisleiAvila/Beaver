@echo off
echo ========================================
echo Limpeza Completa do Projeto Angular
echo ========================================

echo 1. Encerrando processos Node.js...
taskkill /F /IM node.exe /T 2>nul

echo 2. Removendo cache do Angular...
rmdir /s /q ".angular\cache" 2>nul
timeout /t 2 /nobreak >nul
rmdir /s /q ".angular\cache" 2>nul

echo 3. Removendo node_modules...
rmdir /s /q "node_modules" 2>nul

echo 4. Limpando arquivos temporários do npm...
del package-lock.json 2>nul

echo 5. Reinstalando dependências...
call npm install

echo 6. Criando diretório de cache limpo...
mkdir ".angular\cache" 2>nul

echo ========================================
echo Limpeza completa! Tente executar o projeto agora.
echo Use: npm start
echo ========================================
