public class Cliente {
    private String nome;
    private int quantidadePessoas;

    public Cliente(String nome, int quantidadePessoas) {
        if (nome.length() < 2) {
            throw new IllegalArgumentException("O nome do cliente deve ter pelo menos 2 caracteres.");
        }
        this.nome = nome;
        this.quantidadePessoas = quantidadePessoas;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }
}