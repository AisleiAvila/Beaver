import { StatusServico } from '../enum/status-servico.enum';

export interface SubcategoriaRequest {
  id?: number;
  categoria_id?: number;
  nome?: string;
  status?: StatusServico | StatusServico[];
  nivel_medio_complexidade?: string;
  limit?: number;
  offset?: number;
}
