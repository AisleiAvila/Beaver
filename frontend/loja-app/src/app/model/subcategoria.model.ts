export interface Subcategoria {
  id: number;
  categoriaId: number;
  nome: string;
  descricao?: string;
  status?: string;
  tempoMedioMinutos?: number;
  nivelComplexidade?: string;
  precoBase?: number;
  unidadeMedida?: string;
  materiaisTipicos?: string;
  data_criacao?: Date;
  data_atualizacao?: Date;
}
