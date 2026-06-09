package org.example;

import org.example.dao.*;
import org.example.model.*;

import java.util.Scanner;


public class Main {
    static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        int opcao;
        System.out.println("Inicializando Sistema de Gestão Clínica...");
        System.out.println("Conectando ao banco de dados...");

        do {
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
            System.out.println("  0. Encerrar Programa (Sair)");
            System.out.print("Selecione o módulo desejado: ");

            opcao = teclado.nextInt();

            switch(opcao){

                case 1:
                    menuPacientes();
                    break;

                case 2:
                    menuMedicos();
                    break;

                case 3:
                    menuProntuarios();
                    break;

                case 4:
                    menuExames();
                    break;

                case 5:
                    menuConsultas();
                    break;

                case 6:
                    menuConsultaExame();
                    break;

                case 7:
                    menuRelatorios();
                    break;

                case 0:
                    System.out.println("Encerrando plantão. Desconectando com segurança...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while(opcao != 0);

    }

    public static void menuPacientes(){
        PacienteDAO pacienteDAO = new PacienteDAO();
        int opcaoPaciente;
        do {
            System.out.println("\n===== MENU PACIENTE =====");
            System.out.println("1. Registrar Novo Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Localizar por CPF");
            System.out.println("4. Atualizar Paciente");
            System.out.println("5. Remover Registro");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcaoPaciente = teclado.nextInt();
            teclado.nextLine();

            switch (opcaoPaciente) {

                case 1:
                    Paciente novoPaciente = new Paciente();

                    System.out.print("Nome: ");
                    novoPaciente.setNome(teclado.nextLine());

                    System.out.print("CPF: ");
                    novoPaciente.setCpf(teclado.nextLine());

                    System.out.print("Data de nascimento (AAAA-MM-DD): ");
                    novoPaciente.setDataNascimento(teclado.nextLine());

                    System.out.print("Telefone: ");
                    novoPaciente.setTelefone(teclado.nextLine());

                    System.out.print("Gênero (M/F): ");
                    novoPaciente.setGenero(teclado.nextLine().charAt(0));

                    pacienteDAO.inserirPaciente(novoPaciente);
                    break;

                case 2:
                    pacienteDAO.listarPacientes();
                    break;

                case 3:
                    System.out.print("Digite o CPF para busca: ");
                    String cpf = teclado.nextLine();

                    pacienteDAO.buscarPacientePorCpf(cpf);
                    break;

                case 4:
                    Paciente pacienteAtualizado = new Paciente();

                    System.out.print("ID do paciente: ");
                    pacienteAtualizado.setIdPaciente(teclado.nextInt());
                    teclado.nextLine();

                    System.out.print("Novo nome: ");
                    pacienteAtualizado.setNome(teclado.nextLine());

                    System.out.print("Novo CPF: ");
                    pacienteAtualizado.setCpf(teclado.nextLine());

                    System.out.print("Nova data de nascimento (AAAA-MM-DD): ");
                    pacienteAtualizado.setDataNascimento(teclado.nextLine());

                    System.out.print("Novo telefone: ");
                    pacienteAtualizado.setTelefone(teclado.nextLine());

                    System.out.print("Novo gênero (M/F): ");
                    pacienteAtualizado.setGenero(teclado.nextLine().charAt(0));

                    pacienteDAO.atualizarPaciente(pacienteAtualizado);
                    break;

                case 5:

                    System.out.print("ID do paciente a excluir: ");
                    int idExcluir = teclado.nextInt();

                    pacienteDAO.excluirPaciente(idExcluir);

                    break;

                case 0:

                    System.out.println("Retornando ao menu principal...");
                    break;

                default:

                    System.out.println("Opção inválida!");
            }
        } while (opcaoPaciente != 0);
    }

    public static void menuMedicos(){
        MedicoDAO medicoDAO = new MedicoDAO();
        int opcaoMedico;
        do {
            System.out.println("\n===== MENU MÉDICO =====");
            System.out.println("1. Cadastrar Médico");
            System.out.println("2. Listar Médicos");
            System.out.println("3. Buscar por CRM");
            System.out.println("4. Atualizar Médico");
            System.out.println("5. Remover Médico");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcaoMedico = teclado.nextInt();
            teclado.nextLine();

            switch (opcaoMedico) {

                case 1:
                    Medico novoMedico = new Medico();

                    System.out.print("Nome: ");
                    novoMedico.setNome(teclado.nextLine());

                    System.out.print("CRM: ");
                    novoMedico.setCrm(teclado.nextLine());

                    System.out.print("Especialidade: ");
                    novoMedico.setEspecialidade(teclado.nextLine());

                    System.out.print("Telefone: ");
                    novoMedico.setTelefone(teclado.nextLine());

                    medicoDAO.inserirMedico(novoMedico);
                    break;

                case 2:
                    medicoDAO.listarMedicos();
                    break;

                case 3:
                    System.out.print("Digite o crm para busca: ");
                    String crm = teclado.nextLine();

                    medicoDAO.buscarMedicoPorCrm(crm);
                    break;

                case 4:
                    Medico medicoAtualizado = new Medico();

                    System.out.print("ID do médico: ");
                    medicoAtualizado.setIdMedico(teclado.nextInt());
                    teclado.nextLine();

                    System.out.print("Novo nome: ");
                    medicoAtualizado.setNome(teclado.nextLine());

                    System.out.print("Novo CRM: ");
                    medicoAtualizado.setCrm(teclado.nextLine());

                    System.out.print("Nova especialidade: ");
                    medicoAtualizado.setEspecialidade(teclado.nextLine());

                    System.out.print("Novo telefone: ");
                    medicoAtualizado.setTelefone(teclado.nextLine());

                    medicoDAO.atualizarMedico(medicoAtualizado);
                    break;

                case 5:

                    System.out.print("ID do médico a excluir: ");
                    int idExcluir = teclado.nextInt();

                    medicoDAO.excluirMedico(idExcluir);

                    break;

                case 0:

                    System.out.println("Retornando ao menu principal...");
                    break;

                default:

                    System.out.println("Opção inválida!");
            }
        } while (opcaoMedico != 0);
    }

    public static void menuProntuarios(){
        ProntuarioDAO prontuarioDAO = new ProntuarioDAO();
        int opcaoProntuario;
        do {
            System.out.println("\n===== MENU PRONTUÁRIO =====");
            System.out.println("1. Abrir Prontuário");
            System.out.println("2. Listar Prontuários");
            System.out.println("2. Buscar por ID do Paciente");
            System.out.println("4. Atualizar Prontuário");
            System.out.println("5. Remover Prontuário");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcaoProntuario = teclado.nextInt();
            teclado.nextLine();

            switch (opcaoProntuario) {

                case 1:
                    Prontuario novoProntuario = new Prontuario();

                    System.out.print("Histórico Geral: ");
                    novoProntuario.setHistorico_geral(teclado.nextLine());

                    System.out.print("Data de Abertura: ");
                    novoProntuario.setData_abertura(teclado.nextLine());

                    System.out.print("ID do Paciente: ");
                    novoProntuario.setIdPaciente(teclado.nextInt());

                    prontuarioDAO.inserirProntuario(novoProntuario);
                    break;

                case 2:
                    prontuarioDAO.listarProntuarios();
                    break;

                case 3:
                    System.out.print("Digite o id do paciente para busca: ");
                    int id_paciente = teclado.nextInt();

                    prontuarioDAO.buscarProntuarioPorPaciente(id_paciente);
                    break;

                case 4:
                    Prontuario prontuarioAtualizado = new Prontuario();

                    System.out.print("ID do Prontuário: ");
                    prontuarioAtualizado.setIdProntuario(teclado.nextInt());
                    teclado.nextLine();

                    System.out.print("Novo Histórico Geral: ");
                    prontuarioAtualizado.setHistorico_geral(teclado.nextLine());

                    System.out.print("Nova Data de Abertura: ");
                    prontuarioAtualizado.setData_abertura(teclado.nextLine());

                    System.out.print("Novo ID do Paciente: ");
                    prontuarioAtualizado.setIdPaciente(teclado.nextInt());

                    prontuarioDAO.atualizarProntuario(prontuarioAtualizado);
                    break;

                case 5:

                    System.out.print("ID do prontuário a excluir: ");
                    int idExcluir = teclado.nextInt();

                    prontuarioDAO.excluirProntuario(idExcluir);
                    break;

                case 0:

                    System.out.println("Retornando ao menu principal...");
                    break;

                default:

                    System.out.println("Opção inválida!");
            }
        } while (opcaoProntuario != 0);
    }

    public static void menuExames(){
        ExameDAO exameDAO = new ExameDAO();
        int opcaoExame;
        do {
            System.out.println("\n===== MENU EXAME =====");
            System.out.println("1. Cadastrar Exame");
            System.out.println("2. Listar Exames");
            System.out.println("3. Buscar por Nome");
            System.out.println("4. Atualizar Exame");
            System.out.println("5. Remover Exame");
            System.out.println();
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcaoExame = teclado.nextInt();
            teclado.nextLine();

            switch (opcaoExame) {

                case 1:
                    Exame novoExame = new Exame();

                    System.out.print("Nome do Exame: ");
                    novoExame.setNome_exame(teclado.nextLine());

                    System.out.print("Descrição do Exame: ");
                    novoExame.setDescricao(teclado.nextLine());

                    System.out.print("Resultado do Exame: ");
                    novoExame.setResultado(teclado.nextLine());

                    exameDAO.inserirExame(novoExame);
                    break;

                case 2:
                    exameDAO.listarExames();
                    break;

                case 3:
                    System.out.print("Digite o nome do exame para busca: ");
                    String nome_exame = teclado.nextLine();

                    exameDAO.buscarExamePorNome(nome_exame);
                    break;

                case 4:
                    Exame exameAtualizado = new Exame();

                    System.out.print("ID do exame: ");
                    exameAtualizado.setIdExame(teclado.nextInt());
                    teclado.nextLine();

                    System.out.print("Novo Nome: ");
                    exameAtualizado.setNome_exame(teclado.nextLine());

                    System.out.print("Nova Descrição: ");
                    exameAtualizado.setDescricao(teclado.nextLine());

                    System.out.print("Novo Resultado: ");
                    exameAtualizado.setResultado(teclado.nextLine());

                    exameDAO.atualizarExame(exameAtualizado);
                    break;

                case 5:

                    System.out.print("ID do exame a excluir: ");
                    int idExcluir = teclado.nextInt();

                    exameDAO.excluirExame(idExcluir);
                    break;

                case 0:

                    System.out.println("Retornando ao menu principal...");
                    break;

                default:

                    System.out.println("Opção inválida!");
            }
        } while (opcaoExame != 0);
    }

    public static void menuConsultas(){
        ConsultaDAO consultaDAO = new ConsultaDAO();
        int opcaoConsulta;
        do {
            System.out.println("\n===== MENU CONSULTA =====");
            System.out.println("1. Agendar Consulta");
            System.out.println("2. Listar Consultas");
            System.out.println("3. Buscar por Status");
            System.out.println("4. Atualizar Consulta");
            System.out.println("5. Cancelar/Remover Consulta");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcaoConsulta = teclado.nextInt();
            teclado.nextLine();

            switch (opcaoConsulta) {

                case 1:
                    Consulta novaConsulta = new Consulta();

                    System.out.print("Data da Consulta: ");
                    novaConsulta.setData_consulta(teclado.nextLine());

                    System.out.print("Hora da Consulta: ");
                    novaConsulta.setHora_consulta(teclado.nextLine());

                    System.out.print("Valor Pago: ");
                    novaConsulta.setValor_pago(teclado.nextLine());

                    System.out.print("Queixa Principal: ");
                    novaConsulta.setQueixa_principal(teclado.nextLine());

                    System.out.print("Status: ");
                    novaConsulta.setStatus(teclado.nextLine());

                    System.out.print("ID do Paciente: ");
                    novaConsulta.setId_paciente(teclado.nextInt());

                    System.out.print("ID do Médico: ");
                    novaConsulta.setId_medico(teclado.nextInt());

                    consultaDAO.inserirConsulta(novaConsulta);
                    break;

                case 2:
                    consultaDAO.listarConsultas();
                    break;

                case 3:
                    System.out.print("Digite o status para busca: ");
                    String status = teclado.nextLine();

                    consultaDAO.buscarConsultaPorStatus(status);
                    break;

                case 4:
                    Consulta consultaAtualizada = new Consulta();

                    System.out.print("ID da consulta: ");
                    consultaAtualizada.setId_consulta(teclado.nextInt());
                    teclado.nextLine();

                    System.out.print("Nova Data da Consulta: ");
                    consultaAtualizada.setData_consulta(teclado.nextLine());

                    System.out.print("Nova Hora da Consulta: ");
                    consultaAtualizada.setHora_consulta(teclado.nextLine());

                    System.out.print("Novo Valor Pago: ");
                    consultaAtualizada.setValor_pago(teclado.nextLine());

                    System.out.print("Nova Queixa Principal: ");
                    consultaAtualizada.setQueixa_principal(teclado.nextLine());

                    System.out.print("Novo Status: ");
                    consultaAtualizada.setStatus(teclado.nextLine());

                    System.out.print("Novo ID do Paciente: ");
                    consultaAtualizada.setId_paciente(teclado.nextInt());

                    System.out.print("Novo ID do Médico: ");
                    consultaAtualizada.setId_medico(teclado.nextInt());

                    consultaDAO.atualizarConsulta(consultaAtualizada);
                    break;

                case 5:

                    System.out.print("ID da consulta a excluir: ");
                    int idExcluir = teclado.nextInt();

                    consultaDAO.excluirConsulta(idExcluir);
                    break;

                case 0:

                    System.out.println("Retornando ao menu principal...");
                    break;

                default:

                    System.out.println("Opção inválida!");
            }
        } while (opcaoConsulta != 0);
    }

    public static void menuConsultaExame(){
        ConsultaExameDAO consultaExameDAO = new ConsultaExameDAO();
        int opcaoConsultaExame;
        do {
            System.out.println("\n===== MENU DE EXAMES SOLICITADOS =====");
            System.out.println("1. Vincular Exame a uma Consulta");
            System.out.println("2. Listar Todos os Vínculos");
            System.out.println("3. Buscar Exame da Consulta por Data");
            System.out.println("4. Atualizar Vínculos");
            System.out.println("5. Remover Vínculo");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcaoConsultaExame = teclado.nextInt();
            teclado.nextLine();

            switch (opcaoConsultaExame) {

                case 1:
                    ConsultaExame novaConsultaExame = new ConsultaExame();

                    System.out.print("ID da Consulta: ");
                    novaConsultaExame.setId_consulta(teclado.nextInt());

                    System.out.print("ID do Exame: ");
                    novaConsultaExame.setId_exame(teclado.nextInt());

                    System.out.print("Data da Solicitação: ");
                    novaConsultaExame.setData_solicitacao(teclado.nextLine());

                    consultaExameDAO.inserirConsultaExame(novaConsultaExame);
                    break;

                case 2:
                    consultaExameDAO.listarConsultaExame();
                    break;

                case 3:
                    System.out.print("Digite a data de solicitação para busca: ");
                    String data = teclado.nextLine();

                    consultaExameDAO.buscarConsultaExamePorData(data);
                    break;

                case 4:
                    ConsultaExame consultaExameAtualizada = new ConsultaExame();

                    System.out.print("ID da consulta: ");
                    consultaExameAtualizada.setId_consulta(teclado.nextInt());
                    teclado.nextLine();

                    System.out.print("ID do exame: ");
                    consultaExameAtualizada.setId_exame(teclado.nextInt());

                    System.out.print("Nova Data de Solicitação: ");
                    consultaExameAtualizada.setData_solicitacao(teclado.nextLine());

                    consultaExameDAO.atualizarConsultaExame(consultaExameAtualizada);
                    break;

                case 5:

                    System.out.print("ID da consulta a excluir: ");
                    int idExcluir1 = teclado.nextInt();

                    System.out.println("ID do exame a excluir: ");
                    int idExcluir2 = teclado.nextInt();

                    consultaExameDAO.excluirConsultaExame(idExcluir1, idExcluir2);
                    break;

                case 0:

                    System.out.println("Retornando ao menu principal...");
                    break;

                default:

                    System.out.println("Opção inválida!");
            }
        } while (opcaoConsultaExame != 0);
    }

    private static void menuRelatorios() {
        RelatorioDAO relatorio = new RelatorioDAO();
        System.out.println("\n===== MENU DE RELATÓRIOS =====");
        System.out.println("1. Emitir Histórico de Prontuários");
        System.out.println("2. Painel de Consultas em Aberto");
        System.out.println("3. Histórico Laboratorial Completo");
        System.out.print("Opção: ");
        int op = teclado.nextInt();
        teclado.nextLine();

        if(op == 1) {
            relatorio.relatorioProntuarios();
        } else if(op == 2) {
            relatorio.relatorioConsultasIncompletas();
        } else if(op == 3) {
            relatorio.relatorioExamesCompletos();
        }
    }
}