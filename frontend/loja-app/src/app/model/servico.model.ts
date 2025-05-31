export interface Servico {
  id?: number;
  nome: string;
  tecnico: string;
  cliente: string;
  dataAgendada: Date;
  valorCobrado: number;
  status: string;
}
