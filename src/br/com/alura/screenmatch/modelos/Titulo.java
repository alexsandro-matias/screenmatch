package br.com.alura.screenmatch.modelos;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;

public class Titulo implements Comparable<Titulo> {

    // As anotações podem ser considerados como metadados, já que são dados que
    // auxiliam na descrição ou caracterização de outros dados.

    //    @SerializedName("Title") - fazendo a conversão, já não se torna obrigatória a serialização do atributo.
    private String nome;

    //    Porém essa abordagem de serialização não ideal uma vez que se mudar o local de consumo da API,
    //    o campo do JSON possa ser,
    //    ou muito provável que seja. Então iremos criar um "Record".
//    @SerializedName("Year")
    private int anoDeLancamento;
    private boolean incluidoNoPlano;
    private double somaDasAvaliacoes;
    private int totalDeAvaliacoes;
    private int duracaoEmMinutos;

    public Titulo(String nome, int anoDeLancamento) {
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
    }

    public Titulo(TituloOmbd meuTitulooOmbd) {
        this.nome = meuTitulooOmbd.title();
        // Para verificar que o ano do Título tenha no máximo 4 caracteres, ou, um número de 4 algarismos,
        // iremos criar uma própria exceção.
//        this.anoDeLancamento = Integer.valueOf(meuTitulooOmbd.year());

        if (meuTitulooOmbd.year().length() > 4) {
            throw new ErroDeConversaoDeAnoException("Não consegui converter o ano, já que o ano tem mais de 4 caracteres.");
        }

        this.duracaoEmMinutos = Integer.valueOf(meuTitulooOmbd.runtime().substring(0, 3));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public boolean isIncluidoNoPlano() {
        return incluidoNoPlano;
    }

    public void setIncluidoNoPlano(boolean incluidoNoPlano) {
        this.incluidoNoPlano = incluidoNoPlano;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public int getTotalDeAvaliacoes() {
        return totalDeAvaliacoes;
    }

    public void exibeFichaTecnica() {
        System.out.println("Nome do filme: " + nome);
        System.out.println("Ano de lançamento: " + anoDeLancamento);
    }

    public void avalia(double nota) {
        somaDasAvaliacoes += nota;
        totalDeAvaliacoes++;
    }

    public double pegaMedia() {
        return somaDasAvaliacoes / totalDeAvaliacoes;
    }

    @Override
    public int compareTo(Titulo outroTitulo) {
        return this.getNome().compareTo(outroTitulo.getNome());
    }

    @Override
    public String toString() {
        return "Titulo: " + nome + " | " + "Ano de Lançamento: " + this.getAnoDeLancamento() + " | " + "Duração: " + this.getDuracaoEmMinutos();
    }
}