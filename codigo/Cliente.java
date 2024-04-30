public class Cliente {

    private String nome;
    private int quantPessoas;

    public Cliente(String nome, int quantPessoas) {
        this.nome = nome;
        this.quantPessoas = quantPessoas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantPessoas() {
        return quantPessoas;
    }

    public void setQuantPessoas(int quantPessoas) {
        this.quantPessoas = quantPessoas;
    }

}
