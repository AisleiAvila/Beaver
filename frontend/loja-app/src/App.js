import React from "react";
import { ThemeProvider } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import materialTheme from "./theme/materialTheme";
import Button from "@mui/material/Button";

function App() {
  return (
    <ThemeProvider theme={materialTheme}>
      <CssBaseline />
      <div>
        {/* Substituir classes do TailwindCSS */}
        <Button variant="contained" color="primary">
          Clique Aqui
        </Button>
      </div>
    </ThemeProvider>
  );
}

export default App;
