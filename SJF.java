/*
  Projeto SO - Aluno Marcelo Alves Gomes
  Entrada : Cada linha é um processo
            primeiro numero = tempo de chegada do processo
            segundo numero = tempo de duração do processo
 
  Saída   : linha contendo a sigla de cada um dos tres algoritmos(FCFS,SJF,RR) 
            e os valores das três métricas solicitadas(tempo de retorno, tempo 
            de resposta e tempo de espera)
 */
import java.util.*;
/*extends = herança direta da classe */
public class SJF extends Escalonador{
    private int numProcessos;
    private int tempoDeRetorno;
    private int tempoDeResposta;
    private int tempoDeEspera;
    private int fp; //final do processo
    private ArrayList<Processo> listaConcluidos;
    private static List<Processo> listaAuxiliar;
    
    /*Construtor SJF
    O algoritmo SJF consiste em executar primeiramente o processo que chegou primeiro e por seguinte executar os processos em ordem pelo tempo de
    duracao , sabendo disso , o algoritmo implementado consiste em ordenar primeiramente os processos na Classe escalonador pelo tempo de chegada
    depois jogar na classe SJF em que ira checar o processo que chegou primeiro na listaAuxiliar , depois transferir esse processo para a
    listaConcluidos , ordernar nessa lista de concluidos pela ordem de duracao , e depois incementar o fp ,tRetorno , tResposta, tEspera de acordo
    com os dados do processo , apos isso , volta-se para o while de novo até que a listaAux e listaConcluidos estiverem vazias , caso nao ocorra ,
    ficara em um "Círculo vicioso". */
    
    public SJF(List<Processo> processos){
        /*Tratar possiveis errors , como NZEC ERROR */
        try{
            tempoDeRetorno =  0;
            tempoDeResposta=  0;
            tempoDeEspera  =  0;
            
            numProcessos= processos.size(); //para saber o tamanho da lista
            fp= minimoTempoChegada(processos);// fp vai armazenar por enquanto o menor tempo de chegada entre os processos
            
            listaConcluidos = new ArrayList<>();  /*Ira criar um arraylist listaConcluidos do tipo Processo*/
            listaAuxiliar = new ArrayList<Processo>(processos); /*Ira criar um arraylist do tipo Processo , herdando todos os processos(valores) organizados
                                                                  na entrada*/
            
            /*Se as listas estiverem cheias , executa o while*/
            while(!(listaAuxiliar.isEmpty() && listaConcluidos.isEmpty())){
                /*Como a lista auxiliar ja vai está com os processos herdados na entrada , entao ira executar esse while*/
                while (!listaAuxiliar.isEmpty()){
                    /*Se o tempo de chegada do primeiro processo da lista for menor que o TempoDeChegada minimo , e a listaConcluidos estiver vazia
                      ira executar esse if , ou seja , logo no começo ele ja vai ser executado*/
                    if((listaAuxiliar.get(0).getTempoDeChegada() <= fp) || (listaConcluidos.isEmpty())){
                        /*remove o primeiro processo na listaAuxiliar e joga para a lista de concluidos*/
                        Processo p = listaAuxiliar.remove(0); //depois que remove , a lista de reoordena , o p(1) passa a ser p(0)
                        listaConcluidos.add(new Processo(p.getCod(), p.getTempoDeChegada(), p.getDuracao()));
                        
                    }
                    /*O comando break vai quebrar o laco while(!listaAuxiliar.isEmpty())*/
                    else
                        break;
                }
                /*Ordena listaConcluidos em relação ao tempo de duração */
                Collections.sort(listaConcluidos, new Comparator<Processo>(){
                            public int compare(Processo p1, Processo p2)
                            {
				return Integer.valueOf(p1.getDuracao()).compareTo(p2.getDuracao());
                            }
		});
                /*Se a listaConcluidos estiver cheia , executa*/
                if(!listaConcluidos.isEmpty()){
                    Processo p = listaConcluidos.remove(0);//desenfileira
                    fp += p.getDuracao();//fp vai ser a duracao do processo removido , ou seja , o final do processo
                    tempoDeRetorno += (fp - p.getTempoDeChegada());// Tempo de Retorno = Fp - Tempo de chegada
                    tempoDeEspera += (fp - p.getTempoDeChegada() - p.getDuracao());//Tempo de espera = Fp - Tempo de chegada - Tempo de Pico(duracao)
		}
            }
            /*Tempo de Resposta = Tempo de chamada(no desenho) - Tempo de chegada*/
            tempoDeResposta = tempoDeEspera; // No FCFS E SJF o tempoDeResposta = tempoDeEspera
            /*Super está relacionado a herança*/
            /*Calculo das Medias*/
            super.setMediaRetorno((float)tempoDeRetorno/numProcessos); 
            super.setMediaResposta((float)tempoDeResposta/numProcessos);
            super.setMediaEspera((float)tempoDeEspera/numProcessos);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    public void printMedias(){
        printMedias("SJF");
    }
}