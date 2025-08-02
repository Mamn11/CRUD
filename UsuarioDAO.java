import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método para inserir um novo usuário
    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, cpf, email, senha) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexaoPostgres.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getCpf());
            pst.setString(3, usuario.getEmail());
            pst.setString(4, usuario.getSenha());

            pst.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    //Método para deletar um usuário pelo CPF
    public boolean delete(String cpf) {
        String sql = "DELETE FROM usuarios WHERE cpf = ?";

        try (Connection con = ConexaoPostgres.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, cpf);
            int linhasAfetadas = pst.executeUpdate();

            return linhasAfetadas > 0; // retorna true se algo foi removido

        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
            return false;
        }
    }

    // Método para atualizar os dados de um usuário
    public void update(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ?, cpf = ? WHERE cpf = ?";

        try (Connection con = ConexaoPostgres.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getSenha());
            pst.setString(4, usuario.getCpf()); // novo CPF
            pst.setString(5, usuario.getCpf()); // CPF antigo (onde cpf = ?)

            pst.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    // Buscar um usuário pelo CPF (retorna 1 usuário)
    public Usuario buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM usuarios WHERE cpf = ?";

        try (Connection con = ConexaoPostgres.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, cpf);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }

        return null; // não encontrou
    }

    // Listar todos os usuários do banco
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection con = ConexaoPostgres.conectar();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }

        return usuarios;
    }

    // Método auxiliar para saber se há registros no banco
    public boolean isEmpty() {
        String sql = "SELECT COUNT(*) FROM usuarios";

        try (Connection con = ConexaoPostgres.conectar();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1) == 0;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao verificar se lista está vazia: " + e.getMessage());
        }

        return true;
    }
}
