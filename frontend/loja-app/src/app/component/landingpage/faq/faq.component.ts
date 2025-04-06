import { Component } from "@angular/core";
import { faChevronDown } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { TranslateModule } from "@ngx-translate/core";
import { CommonModule } from "@angular/common";

interface FaqItem {
  id: number;
  question: string;
  answer: string;
  isOpen: boolean;
}

@Component({
  selector: "app-faq",
  standalone: true,
  imports: [CommonModule, FontAwesomeModule, TranslateModule],
  templateUrl: "./faq.component.html",
  styleUrls: ["./faq.component.scss"],
})
export class FAQComponent {
  faChevronDown = faChevronDown;

  faqItems: FaqItem[] = [
    {
      id: 1,
      question: "faq.items.hiring.question",
      answer: "faq.items.hiring.answer",
      isOpen: false,
    },
    {
      id: 2,
      question: "faq.items.provider.question",
      answer: "faq.items.provider.answer",
      isOpen: false,
    },
    {
      id: 3,
      question: "faq.items.warranty.question",
      answer: "faq.items.warranty.answer",
      isOpen: false,
    },
    {
      id: 4,
      question: "faq.items.payment.question",
      answer: "faq.items.payment.answer",
      isOpen: false,
    },
    {
      id: 5,
      question: "faq.items.cancellation.question",
      answer: "faq.items.cancellation.answer",
      isOpen: false,
    },
  ];

  toggleFaq(id: number): void {
    const item = this.faqItems.find((item) => item.id === id);
    if (item) {
      item.isOpen = !item.isOpen;
    }
  }
}
