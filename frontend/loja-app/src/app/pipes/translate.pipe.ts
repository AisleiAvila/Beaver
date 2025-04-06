import { Pipe, PipeTransform } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";

@Pipe({
  name: "translate",
  pure: false,
})
export class TranslatePipe implements PipeTransform {
  constructor(private translate: TranslateService) {}

  transform(key: string): string {
    let translation = "";
    this.translate.get(key).subscribe((res: string) => {
      translation = res;
    });
    return translation;
  }
}
