import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Scanner para entrada de dados
        ArrayList<Usuario> usuarios = new ArrayList<>(); // Lista para armazenar objetos Usuario
        int escolha; // Opção do menu

        while (true) {
            // Menu principal
            System.out.println("\nBEM VINDO");
            System.out.println("1 - NOVO USUÁRIO");
            System.out.println("2 - DELETAR USUÁRIO");
            System.out.println("3 - ATUALIZAR USUÁRIO");
            System.out.println("4 - LISTAR USUÁRIOS");
            System.out.println("0 - SAIR");
            System.out.print("ESCOLHA A OPÇÃO DESEJADA: ");
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (escolha) {

                case 1:
                    char opcao; // Variável de controle do loop de criação
                    do {
                        Usuario usuario = new Usuario(); // Cria um novo usuário

                        // Leitura e validação dos campos
                        System.out.print("Digite o nome do usuário: ");
                        usuario.setNome(scanner.nextLine());

                        System.out.print("Digite o CPF do usuário: ");
                        String cpf = scanner.nextLine();
                        while (!usuario.validateCpf(cpf)) {
                            System.out.println("CPF inválido. Deve conter 11 dígitos numéricos.");
                            System.out.print("Digite novamente o CPF: ");
                            cpf = scanner.nextLine();
                        }
                        usuario.setCpf(cpf);

                        System.out.print("Digite o email do usuário: ");
                        String email = scanner.nextLine();
                        while (!usuario.validateEmail(email)) {
                            System.out.println("Email inválido. Deve conter '@' e terminar com '.com'");
                            System.out.print("Digite novamente o email: ");
                            email = scanner.nextLine();
                        }
                        usuario.setEmail(email);

                        System.out.print("Digite a senha do usuário: ");
                        String senha = scanner.nextLine();
                        while (!usuario.validateSenha(senha)) {
                            System.out.println("Senha inválida. Deve ter no mínimo 6 caracteres e conter letras.");
                            System.out.print("Digite novamente a senha: ");
                            senha = scanner.nextLine();
                        }
                        usuario.setSenha(senha);

                        usuarios.add(usuario); // Adiciona à lista
                        System.out.println("Usuário cadastrado com sucesso!");

                        System.out.print("Deseja criar mais um usuário (S/N)? ");
                        opcao = scanner.nextLine().toUpperCase().charAt(0);

                    } while (opcao == 'S');
                    break;

                case 2:
                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                        break;
                    }

                    listarUsuariosSimples(usuarios); // Lista apenas nome/CPF/email

                    System.out.print("Digite o CPF do usuário que deseja remover: ");
                    String cpfDelete = scanner.nextLine();
                    boolean removido = false;

                    for (int i = 0; i < usuarios.size(); i++) {
                        if (usuarios.get(i).getCpf().equalsIgnoreCase(cpfDelete)) {
                            usuarios.remove(i);
                            System.out.println("Usuário removido com sucesso!");
                            removido = true;
                            break;
                        }
                    }

                    if (!removido) {
                        System.out.println("Usuário não encontrado!");
                    }
                    break;

                case 3:
                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                        break;
                    }

                    listarUsuariosSimples(usuarios);

                    System.out.print("Digite o CPF do usuário que deseja atualizar: ");
                    String cpfUpdate = scanner.nextLine();
                    boolean encontrado = false;

                    for (Usuario usuario : usuarios) {
                        if (usuario.getCpf().equalsIgnoreCase(cpfUpdate)) {
                            encontrado = true;

                            System.out.println("Qual campo deseja atualizar?");
                            System.out.println("1 - Nome");
                            System.out.println("2 - CPF");
                            System.out.println("3 - Email");
                            System.out.println("4 - Senha");
                            System.out.print("Escolha a opção: ");
                            int opcaoAtualizar = scanner.nextInt();
                            scanner.nextLine(); // limpa buffer

                            switch (opcaoAtualizar) {
                                case 1:
                                    System.out.print("Novo nome: ");
                                    usuario.setNome(scanner.nextLine());
                                    break;
                                case 2:
                                    System.out.print("Novo CPF: ");
                                    String novoCpf = scanner.nextLine();
                                    while (!usuario.validateCpf(novoCpf)) {
                                        System.out.println("CPF inválido.");
                                        novoCpf = scanner.nextLine();
                                    }
                                    usuario.setCpf(novoCpf);
                                    break;
                                case 3:
                                    System.out.print("Novo email: ");
                                    String novoEmail = scanner.nextLine();
                                    while (!usuario.validateEmail(novoEmail)) {
                                        System.out.println("Email inválido.");
                                        novoEmail = scanner.nextLine();
                                    }
                                    usuario.setEmail(novoEmail);
                                    break;
                                case 4:
                                    System.out.print("Nova senha: ");
                                    String novaSenha = scanner.nextLine();
                                    while (!usuario.validateSenha(novaSenha)) {
                                        System.out.println("Senha inválida.");
                                        novaSenha = scanner.nextLine();
                                    }
                                    usuario.setSenha(novaSenha);
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                            }

                            System.out.println("Usuário atualizado com sucesso!");
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 4:
                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        for (Usuario u : usuarios) {
                            System.out.println("Nome: " + u.getNome());
                            System.out.println("CPF: " + u.getCpf());
                            System.out.println("Email: " + u.getEmail());
                            System.out.println("Senha: " + u.getSenha());
                            System.out.println("------------------------");
                        }
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // Método auxiliar para exibir lista de usuários de forma simplificada
    public static void listarUsuariosSimples(ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("CPF: " + usuario.getCpf());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("------------------------");
        }
    }
}
