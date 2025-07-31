public class Usuario {

    String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    String email;
    String senha;
    String cpf;


    //constutor vazio
    public Usuario(){

    }

    public Usuario(String nome, String email, String senha, String cpf) {}


    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

    // Método para validar o CPF
    public boolean validateCpf(String cpf) {
        // Retorna true se o CPF tiver exatamente 11 dígitos numéricos
        return cpf != null && cpf.length() == 11 && cpf.matches("\\d+");
    }

    // Método para validar o email
    public boolean validateEmail(String email) {
        // Retorna true se o email contiver "@" e terminar com ".com"
        return email != null && email.contains("@") && email.endsWith(".com");
    }

    // Método para validar a senha
    public boolean validateSenha(String senha) {
        // Retorna true se a senha tiver pelo menos 6 caracteres e conter letras
        return senha != null && senha.length() >= 6 && senha.matches(".*[A-Za-z].*");
    }
}


