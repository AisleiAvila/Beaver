import { StatusServico } from '../enum/status-servico.enum';

export interface CategoriaRequest {
  id?: number;
  nome?: string;
  status?: StatusServico | StatusServico[];
  requer_certificacao?: boolean;
  tipo_certificacao?: string;
  experiencia_minima_meses?: number;
  nivel_risco?: string;
  withSubcategorias?: boolean;
  limit?: number;
  offset?: number;
}
