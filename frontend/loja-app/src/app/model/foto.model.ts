export interface Foto {
  id: number;
  usuario_id: number;
  foto: string;
  ativo: boolean;
  data_criacao: string;
  data_atualizacao?: string;
}
