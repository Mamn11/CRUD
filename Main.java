import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int escolha;

        while (true) {
            System.out.println("\nBEM VINDO");
            System.out.println("1 - NOVO USUÁRIO");
            System.out.println("2 - DELETAR USUÁRIO");
            System.out.println("3 - ATUALIZAR USUÁRIO");
            System.out.println("4 - LISTAR USUÁRIOS");
            System.out.println("0 - SAIR");
            System.out.print("ESCOLHA A OPÇÃO DESEJADA: ");
            escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    char opcao;
                    do {
                        Usuario usuario = new Usuario();

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

                        usuarioDAO.inserir(usuario);

                        System.out.print("Deseja criar mais um usuário (S/N)? ");
                        opcao = scanner.nextLine().toUpperCase().charAt(0);

                    } while (opcao == 'S');
                    break;

                case 2:
                    if (usuarioDAO.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                        break;
                    }

                    List<Usuario> usuariosParaRemover = usuarioDAO.listarTodos();
                    for (Usuario u : usuariosParaRemover) {
                        System.out.println("Nome: " + u.getNome());
                        System.out.println("CPF: " + u.getCpf());
                        System.out.println("Email: " + u.getEmail());
                        System.out.println("------------------------");
                    }

                    System.out.print("Digite o CPF do usuário que deseja remover: ");
                    String cpfDelete = scanner.nextLine();

                    if (usuarioDAO.delete(cpfDelete)) {
                        System.out.println("Usuário removido com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado!");
                    }
                    break;

                case 3:
                    if (usuarioDAO.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                        break;
                    }

                    System.out.print("Digite o CPF do usuário que deseja atualizar: ");
                    String cpfUpdate = scanner.nextLine();

                    Usuario usuarioAtualizar = usuarioDAO.buscarPorCpf(cpfUpdate);

                    if (usuarioAtualizar == null) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    System.out.println("1 - Nome | 2 - CPF | 3 - Email | 4 - Senha");
                    System.out.print("Qual campo deseja atualizar? ");
                    int opcaoAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcaoAtualizar) {
                        case 1:
                            System.out.print("Novo nome: ");
                            usuarioAtualizar.setNome(scanner.nextLine());
                            break;
                        case 2:
                            System.out.print("Novo CPF: ");
                            usuarioAtualizar.setCpf(scanner.nextLine());
                            break;
                        case 3:
                            System.out.print("Novo email: ");
                            usuarioAtualizar.setEmail(scanner.nextLine());
                            break;
                        case 4:
                            System.out.print("Nova senha: ");
                            usuarioAtualizar.setSenha(scanner.nextLine());
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }

                    usuarioDAO.update(usuarioAtualizar);

                    break;

                case 4:
                    List<Usuario> lista = usuarioDAO.listarTodos();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        for (Usuario u : lista) {
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
}
