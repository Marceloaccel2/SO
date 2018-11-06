/*
  Projeto SO - Aluno Marcelo Alves Gomes
  Entrada : Cada linha é um processo
            primeiro numero = tempo de chegada do processo
            segundo numero = tempo de duração do processo
 
  Saída   : linha contendo a sigla de cada um dos tres algoritmos(FCFS,SJF,RR) 
            e os valores das três métricas solicitadas(tempo de retorno, tempo 
            de resposta e tempo de espera)
 */
import java.util.List;
import java.util.ArrayList;
/*extends = herança direta da classe */
public class FCFS extends Escalonador{
    private static List<Processo> listaConcluidos;
    private int numProcessos; //irá ser o tamanho da lista processos 
    private int tempoDeRetorno;
    private int tempoDeResposta;
    private int tempoDeEspera;
    private int fp; //Final do processo
    
    /*Para entender como irá funcionar o FCFS ,tem que saber que primeiramente irá organizar os processos pelo tempo de chegada
    na classe main , usando o Collections.sort() , após isso irá jogar a lista organizada no parentese da classe FCFS , assim iniciando
    os calculos , e assim pritando os valores*/
    
    /*Construtor FCFS*/
    public FCFS(List<Processo>processos){
        /*Tratar possiveis errors , como NZEC ERROR */
        try{ 
            tempoDeRetorno =  0;
            tempoDeResposta=  0;
            tempoDeEspera  =  0;
            
            numProcessos= processos.size(); //para saber o tamanho da lista
            fp= minimoTempoChegada(processos);// fp vai armazenar por enquanto o menor tempo de chegada entre os processos
            listaConcluidos = new ArrayList<Processo>(processos); //listaConcluidos vai armazenar todos os processos da entrada
            
            /* Enquanto a lista não estiver vazia ,entao executa o while*/
            
            /* 1- True(resultado final),pois o isEmpty vai ser false e com o "!" vai ficar True ,
                 entao vai ter que percorrer o laço obrigatoriamente.
               2- Quando ele remover todos os elementos da lista concluido ,o resultado de is.empty vai ser true 
                 ,ai vai ficar false e vai sair do laço de repetiçao */
            while(!listaConcluidos.isEmpty()){ 
                /*No array list ou linked list ,se eu remover o elemento 0 ,
                o elemento 1 passa a ser o elemento 0 ,ou seja , vai se adaptar*/
                Processo p = listaConcluidos.remove(0); 
                fp += p.getDuracao(); //fp vai ser a duracao do processo removido , ou seja , o final do processo
                tempoDeRetorno += (fp - p.getTempoDeChegada()); // Tempo de Retorno = Fp - Tempo de chegada
                tempoDeEspera  += (fp - p.getTempoDeChegada()-p.getDuracao()); //Tempo de espera = Fp - Tempo de chegada - Tempo de Pico(duracao)
                tempoDeResposta = tempoDeEspera; // No FCFS E SJF o tempoDeResposta = tempoDeEspera
                /*Tempo de Resposta = Tempo de chamada(no desenho) - Tempo de chegada*/
                
                /*Super está relacionado a herança*/
                /*Calculo das Medias*/
                super.setMediaRetorno((float)tempoDeRetorno/numProcessos); 
                super.setMediaResposta((float)tempoDeResposta/numProcessos);
                super.setMediaEspera((float)tempoDeEspera/numProcessos);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    public void printMedias(){		
        printMedias("FCFS");
    }
    
}    