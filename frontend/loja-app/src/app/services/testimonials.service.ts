import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class TestimonialsService {
  getTestimonials(): Observable<any[]> {
    const mockTestimonials = [
      {
        name: "Viviane Garrido",
        feedback:
          "Excelente serviço! O profissional foi pontual, prestativo e resolveu o problema rapidamente. Recomendo!!",
        rating: 5,
      },
      {
        name: "Pedro Medeiros",
        feedback:
          "Muito satisfeito com o atendimento. Preço justo e serviço de qualidade. Vou usar novamente!!",
        rating: 4,
      },
      { name: "Alice Johnson", feedback: "Highly recommend!", rating: 5 },
    ];
    return of(mockTestimonials); // Simulates an API call
  }
}
