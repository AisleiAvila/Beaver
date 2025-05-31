import { Servico } from './servico.model';

export interface ServicoResponseDTO {
  servicos: Servico[];
  totalRecords: number;
}
