import { NgModule } from "@angular/core";
import { FontAwesomeModule as FaModule } from "@fortawesome/angular-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { fab } from "@fortawesome/free-brands-svg-icons";

// Adiciona os ícones do Font Awesome
library.add(fas, fab);

@NgModule({
  imports: [FaModule],
  exports: [FaModule],
})
export class FontAwesomeModule {}
