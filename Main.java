import java.util.Scanner;
import dao.*;
import model.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PacienteDAO pacienteDAO = new PacienteDAO();
        MedicoDAO medicoDAO = new MedicoDAO();
        ProntuarioDAO prontuarioDAO = new ProntuarioDAO();
        ExameDAO exameDAO = new ExameDAO();
        ConsultaDAO consultaDAO = new ConsultaDAO();
        ConsultaExameDAO consultaExameDAO = new ConsultaExameDAO();
        RelatorioDAO relatorioDAO = new RelatorioDAO();

        int opcaoPrincipal = 0;

        System.out.println("Inicializando Sistema de Gestão Clínica...");
        System.out.println("Conectando ao banco de dados...");

        while (opcaoPrincipal != 8) {
            System.out.println("\n===========================================");
            System.out.println("      🏥 SISTEMA DE GESTÃO CLÍNICA 🏥      ");
            System.out.println("               Painel Central              ");
            System.out.println("===========================================");
            System.out.println("  1. Gestão de Pacientes");
            System.out.println("  2. Gestão de Médicos");
            System.out.println("  3. Gestão de Prontuários");
            System.out.println("  4. Gestão de Exames");
            System.out.println("  5. Gestão de Consultas");
            System.out.println("  6. Gestão de Exames por Consulta");
            System.out.println("  7. Módulo de Relatórios (JOINs)");
            System.out.println("-------------------------------------------");
            System.out.println("  8. Encerrar Programa (Sair)");
            System.out.print("Selecione o módulo desejado: ");

            if (scanner.hasNextInt()) {
                opcaoPrincipal = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.nextLine();
                continue;
            }

            switch (opcaoPrincipal) {
                case 1:
                    menuPacientes(scanner, pacienteDAO);
                    break;
                case 2:
                    menuMedicos(scanner, medicoDAO);
                    break;
                case 3:
                    menuProntuarios(scanner, prontuarioDAO);
                    break;
                case 4:
                    menuExames(scanner, exameDAO);
                    break;
                case 5:
                    menuConsultas(scanner, consultaDAO);
                    break;
                case 6:
                    menuConsultaExame(scanner, consultaExameDAO);
                    break;
                case 7:
                    menuRelatorios(scanner, relatorioDAO);
                    break;
                case 8:
                    System.out.println("Encerrando plantão. Desconectando com segurança...");
                    break;
                default:
                    System.out.println("Módulo não encontrado.");
            }
        }
        scanner.close();
    }

    // -- SUBMENUS

    private static void menuPacientes(Scanner scanner, PacienteDAO dao) {
        System.out.println("\n--- [ MÓDULO DE PACIENTES ] ---");
        System.out.println("1. Registrar Novo Paciente");
        System.out.println("2. Localizar por CPF");
        System.out.println("3. Atualizar Telefone");
        System.out.println("4. Remover Registro");
        System.out.print("Opção: ");
        int op = scanner.nextInt(); scanner.nextLine();

        if(op == 1) {
            Paciente p = new Paciente();
            System.out.print("Nome: "); p.setNome(scanner.nextLine());
            System.out.print("CPF: "); p.setCpf(scanner.nextLine());
            System.out.print("Nascimento (YYYY-MM-DD): "); p.setDataNascimento(scanner.nextLine());
            System.out.print("Telefone: "); p.setTelefone(scanner.nextLine());
            System.out.print("Gênero (M/F): "); p.setGenero(scanner.nextLine());
            dao.inserir(p);
        } else if(op == 2) {
            System.out.print("CPF: "); dao.buscarPorCpf(scanner.nextLine());
        } else if(op == 3) {
            System.out.print("CPF: "); String cpf = scanner.nextLine();
            System.out.print("Novo Telefone: "); String tel = scanner.nextLine();
            dao.atualizar(cpf, tel);
        } else if(op == 4) {
            System.out.print("CPF para exclusão: "); dao.deletar(scanner.nextLine());
        }
    }

    private static void menuMedicos(Scanner scanner, MedicoDAO dao) {
        System.out.println("\n--- [ MÓDULO DE MÉDICOS ] ---");
        System.out.println("1. Cadastrar Médico");
        System.out.println("2. Buscar por CRM");
        System.out.println("3. Atualizar Telefone");
        System.out.println("4. Remover Médico");
        System.out.print("Opção: ");
        int op = scanner.nextInt(); scanner.nextLine();

        if(op == 1) {
            Medico m = new Medico();
            System.out.print("Nome: "); m.setNome(scanner.nextLine());
            System.out.print("CRM: "); m.setCrm(scanner.nextLine());
            System.out.print("Especialidade: "); m.setEspecialidade(scanner.nextLine());
            System.out.print("Telefone: "); m.setTelefone(scanner.nextLine());
            dao.inserir(m);
        } else if(op == 2) {
            System.out.print("CRM: "); dao.buscarPorCrm(scanner.nextLine());
        } else if(op == 3) {
            System.out.print("CRM: "); String crm = scanner.nextLine();
            System.out.print("Novo Telefone: "); String tel = scanner.nextLine();
            dao.atualizar(crm, tel);
        } else if(op == 4) {
            System.out.print("CRM para exclusão: "); dao.deletar(scanner.nextLine());
        }
    }

    private static void menuProntuarios(Scanner scanner, ProntuarioDAO dao) {
        System.out.println("\n--- [ MÓDULO DE PRONTUÁRIOS ] ---");
        System.out.println("1. Abrir Prontuário");
        System.out.println("2. Buscar por ID do Paciente");
        System.out.println("3. Atualizar Histórico");
        System.out.println("4. Remover Prontuário");
        System.out.print("Opção: ");
        int op = scanner.nextInt(); scanner.nextLine();

        if(op == 1) {
            Prontuario pr = new Prontuario();
            System.out.print("Histórico Geral: "); pr.setHistoricoGeral(scanner.nextLine());
            System.out.print("Abertura (YYYY-MM-DD): "); pr.setDataAbertura(scanner.nextLine());
            System.out.print("ID do Paciente: "); pr.setIdPaciente(scanner.nextInt());
            dao.inserir(pr);
        } else if(op == 2) {
            System.out.print("ID do Paciente: "); dao.buscarPorIdPaciente(scanner.nextInt());
        } else if(op == 3) {
            System.out.print("ID do Prontuário: "); int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("Novo Histórico: "); String hist = scanner.nextLine();
            dao.atualizar(id, hist);
        } else if(op == 4) {
            System.out.print("ID do Prontuário para exclusão: "); dao.deletar(scanner.nextInt());
        }
    }

    private static void menuExames(Scanner scanner, ExameDAO dao) {
        System.out.println("\n--- [ MÓDULO DE EXAMES ] ---");
        System.out.println("1. Cadastrar Tipo de Exame");
        System.out.println("2. Buscar por Nome");
        System.out.println("3. Atualizar Resultado");
        System.out.println("4. Remover Exame");
        System.out.print("Opção: ");
        int op = scanner.nextInt(); scanner.nextLine();

        if(op == 1) {
            Exame ex = new Exame();
            System.out.print("Nome do Exame: "); ex.setNomeExame(scanner.nextLine());
            System.out.print("Descrição: "); ex.setDescricao(scanner.nextLine());
            System.out.print("Resultado: "); ex.setResultado(scanner.nextLine());
            dao.inserir(ex);
        } else if(op == 2) {
            System.out.print("Nome do Exame: "); dao.buscarPorNome(scanner.nextLine());
        } else if(op == 3) {
            System.out.print("ID do Exame: "); int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("Novo Resultado: "); String res = scanner.nextLine();
            dao.atualizarResultado(id, res);
        } else if(op == 4) {
            System.out.print("ID do Exame para exclusão: "); dao.deletar(scanner.nextInt());
        }
    }

    private static void menuConsultas(Scanner scanner, ConsultaDAO dao) {
        System.out.println("\n--- [ MÓDULO DE CONSULTAS ] ---");
        System.out.println("1. Agendar Consulta");
        System.out.println("2. Buscar por Status");
        System.out.println("3. Atualizar Status");
        System.out.println("4. Cancelar/Remover Consulta");
        System.out.print("Opção: ");
        int op = scanner.nextInt(); scanner.nextLine();

        if(op == 1) {
            Consulta c = new Consulta();
            System.out.print("Data (YYYY-MM-DD): "); c.setDataConsulta(scanner.nextLine());
            System.out.print("Hora (HH:MM:SS): "); c.setHoraConsulta(scanner.nextLine());
            System.out.print("Valor Pago: "); c.setValorPago(scanner.nextDouble()); scanner.nextLine();
            System.out.print("Queixa Principal: "); c.setQueixaPrincipal(scanner.nextLine());
            System.out.print("Status: "); c.setStatus(scanner.nextLine());
            System.out.print("ID do Paciente: "); c.setIdPaciente(scanner.nextInt());
            System.out.print("ID do Médico: "); c.setIdMedico(scanner.nextInt());
            dao.inserir(c);
        } else if(op == 2) {
            System.out.print("Status (Agendada/Concluída/Cancelada): "); dao.buscarPorStatus(scanner.nextLine());
        } else if(op == 3) {
            System.out.print("ID da Consulta: "); int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("Novo Status: "); String status = scanner.nextLine();
            dao.atualizarStatus(id, status);
        } else if(op == 4) {
            System.out.print("ID da Consulta para exclusão: "); dao.deletar(scanner.nextInt());
        }
    }

    private static void menuConsultaExame(Scanner scanner, ConsultaExameDAO dao) {
        System.out.println("\n--- [ MÓDULO DE EXAMES SOLICITADOS ] ---");
        System.out.println("1. Vincular Exame a uma Consulta");
        System.out.println("2. Listar Todos os Vínculos");
        System.out.println("3. Atualizar Data de Solicitação");
        System.out.println("4. Remover Vínculo");
        System.out.print("Opção: ");
        int op = scanner.nextInt(); scanner.nextLine();

        if(op == 1) {
            ConsultaExame ce = new ConsultaExame();
            System.out.print("ID da Consulta: "); ce.setIdConsulta(scanner.nextInt());
            System.out.print("ID do Exame: "); ce.setIdExame(scanner.nextInt()); scanner.nextLine();
            System.out.print("Data de Solicitação (YYYY-MM-DD): "); ce.setDataSolicitacao(scanner.nextLine());
            dao.inserir(ce);
        } else if(op == 2) {
            dao.listarTodos();
        } else if(op == 3) {
            System.out.print("ID da Consulta: "); int idC = scanner.nextInt();
            System.out.print("ID do Exame: "); int idE = scanner.nextInt(); scanner.nextLine();
            System.out.print("Nova Data: "); String data = scanner.nextLine();
            dao.atualizarDataSolicitacao(idC, idE, data);
        } else if(op == 4) {
            System.out.print("ID da Consulta: "); int idC = scanner.nextInt();
            System.out.print("ID do Exame: "); int idE = scanner.nextInt();
            dao.deletar(idC, idE);
        }
    }

    private static void menuRelatorios(Scanner scanner, RelatorioDAO dao) {
        System.out.println("\n--- [ RELATÓRIOS GERAIS ] ---");
        System.out.println("1. Emitir Histórico de Prontuários");
        System.out.println("2. Painel de Consultas em Aberto");
        System.out.println("3. Histórico Laboratorial Completo");
        System.out.print("Opção: ");
        int op = scanner.nextInt(); scanner.nextLine();

        if(op == 1) {
            dao.relatorioProntuarios();
        } else if(op == 2) {
            dao.relatorioConsultasIncompletas();
        } else if(op == 3) {
            dao.relatorioExamesCompletos();
        }
    }
}