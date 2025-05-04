import { Categoria } from './categoria.model';
import { Subcategoria } from './subcategoria.model';

export interface UsuarioSubcategoria {
  id: number;
  usuarioId: number;
  categoria?: Categoria;
  subcategoria: Subcategoria;
  dataCriacao?: string;
  dataExclusao?: string;
}
