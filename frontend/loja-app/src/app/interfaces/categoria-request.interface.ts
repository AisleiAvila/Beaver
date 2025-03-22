export interface CategoriaRequest {
  id?: number;
  nome?: string;
  status?: string;
  requer_certificacao?: boolean;
  tipo_certificacao?: string;
  experiencia_minima_meses?: number;
  nivel_risco?: string;
  limit?: number;
  offset?: number;
}
