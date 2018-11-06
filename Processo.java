/*
  Projeto SO - Aluno Marcelo Alves Gomes
  Entrada : Cada linha é um processo
            primeiro numero = tempo de chegada do processo
            segundo numero = tempo de duração do processo
 
  Saída   : linha contendo a sigla de cada um dos tres algoritmos(FCFS,SJF,RR) 
            e os valores das três métricas solicitadas(tempo de retorno, tempo 
            de resposta e tempo de espera)
 */

public class Processo {
    private int tempoDeChegada;//o que a entrada pede
    private int duracao;//o que a entrada pede
    private int cod;//identidade do processo , ou numero
    private int restoDuracao;
    private boolean chave= true; //(variavel para habilitar ou desabilitar , 0=true 1=false)
    
    //Construtor do processo em que o restoDuracao é igual a duracao
    public Processo(int cod,int tempoDeChegada,int duracao){
        this.cod=cod;
        this.tempoDeChegada=tempoDeChegada;
        this.duracao=duracao;
        this.restoDuracao=duracao;
        chave=false;
    }
    //Construtor do processo em que o restoDuracao tem valor proprio
    public Processo(int cod,int tempoDeChegada,int duracao,int restoDuracao){
        this.cod=cod;
        this.tempoDeChegada=tempoDeChegada;
        this.duracao=duracao;
        this.restoDuracao=restoDuracao; 
    }
    
    //Parte dos getters and setters
    public int getTempoDeChegada() {
        return tempoDeChegada;
    }

    public void setTempoDeChegada(int tempoDeChegada) {
        this.tempoDeChegada = tempoDeChegada;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getRestoDuracao() {
        return restoDuracao;
    }

    public void setRestoDuracao(int restoDuracao) {
        this.restoDuracao = restoDuracao;
    }

    public boolean getChave() {
        return chave;
    }

    public void setChave(){
        chave = true; // chave ja vai ser 0(true) no começo
    }
}