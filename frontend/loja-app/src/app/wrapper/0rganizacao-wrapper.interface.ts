import { Organizacao } from '../model/organizacao.model';

// Adicione isto no mesmo arquivo onde está a interface Organizacao ou em um arquivo separado
export interface OrganizacaoWrapper {
  organizacoes: Organizacao[];
  totalRecords: number | null;
}
