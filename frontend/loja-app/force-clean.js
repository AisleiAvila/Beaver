const fs = require("fs");
const path = require("path");
const { exec } = require("child_process");

const cachePath = path.join(__dirname, ".angular", "cache");
const currentPid = process.pid;

console.log("Tentando limpar o cache do Angular...");

// Lista todos os processos Node.js e filtra o processo atual
exec('tasklist /fi "imagename eq node.exe" /fo csv /nh', (error, stdout) => {
  if (error) {
    console.error("Erro ao listar processos:", error);
    return;
  }

  // Extrai PIDs dos processos Node.js
  const nodeProcesses = stdout
    .split("\r\n")
    .filter((line) => line.includes("node.exe"))
    .map((line) => {
      const match = line.match(/"node.exe","?(\d+)"?/);
      return match ? parseInt(match[1]) : null;
    })
    .filter((pid) => pid && pid !== currentPid); // Filtra o processo atual

  // Encerra os processos Node.js (exceto o atual)
  if (nodeProcesses.length > 0) {
    console.log(`Encerrando ${nodeProcesses.length} processos Node.js...`);
    nodeProcesses.forEach((pid) => {
      exec(`taskkill /F /PID ${pid} /T`, () => {});
    });
  }

  // Aguarda um momento antes de limpar o cache
  setTimeout(() => {
    try {
      // Tenta excluir o diretório de cache
      if (fs.existsSync(cachePath)) {
        console.log("Removendo diretório de cache...");
        fs.rmSync(cachePath, { recursive: true, force: true });
      }
      console.log("Cache limpo com sucesso!");
    } catch (err) {
      console.error("Erro ao limpar o cache:", err.message);
      console.log(
        "Pode ser necessário reiniciar o computador para liberar os arquivos bloqueados."
      );
    }
  }, 1000);
});
