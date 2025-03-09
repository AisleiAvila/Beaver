import { Endereco } from './endereco.model';
import { Foto } from './foto.model';
import { Perfil } from './perfil.model';

export interface Usuario {
  id: number;
  nome: string;
  dataNascimento: string;
  email: string;
  senha: string;
  perfis: Perfil[];
  enderecos: Endereco[];
  foto?: Foto;
}
