import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TranslateModule, TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-servico',
  standalone: true,
  imports: [CommonModule, FormsModule, TranslateModule], // Certifique-se de importar CommonModule e FormsModule
  templateUrl: './servico.component.html',
  styleUrls: ['./servico.component.scss'],
})
export class ServicoComponent implements OnInit {
  translate = inject(TranslateService);

  currentStep = 1;
  agreeTerms = false;
  showCustomServiceInput = false;
  titulo = '';

  serviceData = {
    description: '',
    type: '',
    customType: '',
    cep: '',
    street: '',
    number: '',
    neighborhood: '',
    city: '',
    state: '',
    complement: '',
    address: '', // Adicionada a propriedade address
    zipCode: '', // Adiciona a propriedade zipCode
  };

  serviceTypes = [
    {
      value: 'plumbing',
      label: 'Encanamento',
      description: 'Reparos, instalações e manutenção',
      icon: 'fa-faucet',
    },
    {
      value: 'electrical',
      label: 'Elétrica',
      description: 'Instalações e reparos elétricos',
      icon: 'fa-bolt',
    },
    {
      value: 'cleaning',
      label: 'Limpeza',
      description: 'Residencial, comercial ou pós-obra',
      icon: 'fa-broom',
    },
    {
      value: 'painting',
      label: 'Pintura',
      description: 'Residencial, comercial e industrial',
      icon: 'fa-paint-roller',
    },
    {
      value: 'construction',
      label: 'Construção',
      description: 'Reformas e construções',
      icon: 'fa-hammer',
    },
    {
      value: 'other',
      label: 'Outro',
      description: 'Outro tipo de serviço',
      icon: 'fa-tools',
    },
  ];

  ngOnInit(): void {
    this.definirTitulo();
    this.translate.setDefaultLang('pt-BR');
  }

  nextStep(): void {
    if (this.currentStep < 5) {
      this.currentStep++;
      this.showCustomServiceInput =
        this.currentStep === 2 && this.serviceData.type === 'other';
    }
  }

  prevStep(): void {
    if (this.currentStep > 1) {
      this.currentStep--;
    }
  }

  selectServiceType(service: any): void {
    this.serviceData.type = service.value;
    this.serviceData.customType = '';
    this.showCustomServiceInput = service.value === 'other';
  }

  getServiceTypeLabel(): string {
    if (this.serviceData.type === 'other') {
      return this.serviceData.customType;
    }
    const selectedService = this.serviceTypes.find(
      (service) => service.value === this.serviceData.type
    );
    return selectedService ? selectedService.label : '';
  }

  searchCEP(): void {
    if (this.serviceData.cep === '01001000') {
      this.serviceData.street = 'Praça da Sé';
      this.serviceData.neighborhood = 'Sé';
      this.serviceData.city = 'São Paulo';
      this.serviceData.state = 'SP';
    }
  }

  submitRequest(): void {
    console.log('Submitting service request:', this.serviceData);
    setTimeout(() => {
      this.currentStep = 5;
    }, 1000);
  }

  resetForm(): void {
    this.currentStep = 1;
    this.agreeTerms = false;
    this.showCustomServiceInput = false;
    for (const key in this.serviceData) {
      this.serviceData[key] = '';
    }
  }

  private definirTitulo(): void {
    const titleKey = 'TITLE_CADASTRAR_SERVICO';

    // Inscrever-se nas mudanças de idioma
    this.translate.onLangChange.subscribe(() => {
      this.translate.get(titleKey).subscribe((traducao: string) => {
        this.titulo = traducao;
      });
    });

    // Definir título inicial
    this.translate.get(titleKey).subscribe((traducao: string) => {
      this.titulo = traducao;
    });
  }
}
