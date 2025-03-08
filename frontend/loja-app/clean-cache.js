const fs = require("fs");
const path = require("path");
const { exec } = require("child_process");

// Path to the Angular cache
const cachePath = path.join(__dirname, ".angular", "cache");

console.log("Cleaning Angular cache...");

// Check if the directory exists
if (fs.existsSync(cachePath)) {
  console.log(`Cache directory found at: ${cachePath}`);

  // On Windows, try to use the 'rmdir' command with force option
  if (process.platform === "win32") {
    exec(`rmdir /s /q "${cachePath}"`, (error) => {
      if (error) {
        console.log(`Could not remove cache with rmdir: ${error.message}`);
        console.log(
          "Try manually closing any processes that might be using the Angular files"
        );
        console.log("Then run: npm run clean && npm start");
      } else {
        console.log("Cache successfully cleaned!");
        console.log("Now you can run: npm start");
      }
    });
  } else {
    // For non-Windows platforms
    exec(`rm -rf "${cachePath}"`, (error) => {
      if (error) {
        console.log(`Could not remove cache: ${error.message}`);
      } else {
        console.log("Cache successfully cleaned!");
        console.log("Now you can run: npm start");
      }
    });
  }
} else {
  console.log("No cache directory found. You can proceed with: npm start");
}
