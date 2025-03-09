export interface Endereco {
  id: number;
  logradouro: string;
  numero: string;
  complemento?: string;
  bairro: string;
  cidade_id?: {
    id: number;
    nome: string;
    estado_id: {
      id: number;
      nome: string;
      pais_id: {
        id: number;
        nome: string;
      };
    };
  };
  cep: string;
}
