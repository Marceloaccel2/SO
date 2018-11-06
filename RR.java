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
public class RR extends Escalonador{
    private int numProcessos; //irá ser o tamanho da lista processos 
    private int tempoDeRetorno;
    private int tempoDeResposta;
    private int tempoDeEspera;
    private int q; // valor do quantum 
    private int next;
    private int duracao;
    private int fp; // tempo de Chegada
    private ArrayList<Processo> listaConcluidos;
    private static List<Processo> listaAuxiliar;
    private ArrayList<Processo> listaNova;
    
    /*Construtor RR
    Resumindo como ira ser realizado o RR , primeiramente irá ser realizado uma ordenacao baseada na ordem de chegada , sendo asssim a listaAux ira
    possuir os processos ja organizados pelo tempo de chegada , depois disso irá ser jogado os processos da listaAux para a listaConcluidos, mas vale 
    lembrar que so irao ser os processos que possui tChegada < fp , depois disso, executa-se o if da listaConcluidos , fazendo com que comece a ser
    subtraido a duracao dos processos que vem sendo adicionaos em listaConcluidos, apos isso joga-se os elementos para ListaNova , subtraindo-se a  duracao
    do processo pelo quantum , e jogando devolta para a lista de concluidos , caso o processo nao tenha terminado , joga devolta para listaNova e recomeça
    o processo , até que a duração zere */
    
    public RR(List<Processo> processos){
        /*Tratar possiveis errors , como NZEC ERROR */
        try{
            tempoDeRetorno =  0;
            tempoDeResposta=  0;
            tempoDeEspera  =  0;
            
            numProcessos = processos.size();//para saber o tamanho da lista
            fp = minimoTempoChegada(processos);// fp vai armazenar por enquanto o menor tempo de chegada entre os processos
            next = 0;
            q = 2; //quantum requisitado no projeto é 2
            
            listaConcluidos = new ArrayList<>();  /*Ira criar um arraylist listaConcluidos do tipo Processo*/
            /*Ira criar um arraylist do tipo Processo , herdando todos os processos(valores) organizados na entrada*/
            listaAuxiliar = new ArrayList<Processo>(processos);
            /*Ira criar um arraylist listaNova do tipo Processo*/
            listaNova = new ArrayList<>(); 
            
            /*Enquanto as listas nao estiverem vazias*/
            while(!(listaAuxiliar.isEmpty() && listaNova.isEmpty() && listaConcluidos.isEmpty())){
                /* Ira armazenar todos os processos que entraram antes do tempo minimo de chegada , no caso , fp 
                   Resumindo , esse !listaAuxiliar irá ser o 1° a ser executado*/
                while (!listaAuxiliar.isEmpty()){
                    /*Se o primeiro processo da listaAuxiliar , que no caso é a lista de entrada do usuario , for menor que o tempoMinimoDeChegada
                    entao, irá adicionar na listaConcluidos e excluir da lista Auxiliar*/
                    if((listaAuxiliar.get(0).getTempoDeChegada() <= fp)|| (listaConcluidos.isEmpty())){
                        /*No array list ou linked list ,se eu remover o elemento 0 ,
                         o elemento 1 passa a ser o elemento 0 ,ou seja , vai se adaptar*/
                        Processo p = listaAuxiliar.remove(0);
                        listaConcluidos.add(new Processo(p.getCod(), p.getTempoDeChegada(), p.getDuracao()));
                    }
                    else{
                        break;
                    }
                }
                /*Irá ser o 3° a ser executado na primeira vez que o programa rodar , depois passa ser o 2°*/
                if(!listaNova.isEmpty()){
                    Processo p = listaNova.remove(0);
                    listaConcluidos.add(new Processo(p.getCod(), p.getTempoDeChegada(), p.getDuracao(),duracao));
                }
                /*Irá ser o 2° a ser executado na primeira vez que o programa rodar , depois passa a ser o 3°*/
		if(!listaConcluidos.isEmpty()){
                    Processo p = listaConcluidos.remove(0);
                    duracao = (p.getRestoDuracao()) - q;
                    //p.getChave() é False na primeira execucao
                    if(!(p.getChave())){
                        /*Tempo de Resposta = Tempo de chamada(no desenho) - Tempo de chegada*/
                        tempoDeResposta += fp - p.getTempoDeChegada();
                        //Seta True na chave
                        p.setChave();

                    }
                    /*Caso aconteca isso , o processo logicamente ja terminou a execucao*/
                    if(duracao<=0){
                        fp += q + duracao;//fp vai ser a duracao do processo removido , ou seja , o final do processo
			tempoDeRetorno += (fp - p.getTempoDeChegada());// Tempo de Retorno = Fp - Tempo de chegada
			tempoDeEspera += (fp - p.getTempoDeChegada() - p.getDuracao());	//Tempo de espera = Fp - Tempo de chegada - Tempo de Pico(duracao)
                        /*Tempo de Resposta = Tempo de chamada(no desenho) - Tempo de chegada*/
                    }
                    else
                    {
                        p.setRestoDuracao(duracao);
			//soma do Fp com o quantum , no caso do p1 da listaConcluido , o tempo que o p2 vai entrar vai ser o 2
                        fp += q;
			listaNova.add(new Processo(p.getCod(), p.getTempoDeChegada(), p.getDuracao(), duracao));
                    }

                }
  
            }
            /*Super está relacionado a herança
              Calculo das Medias*/
            super.setMediaResposta((float)tempoDeResposta/numProcessos); 
            super.setMediaRetorno((float)tempoDeRetorno/numProcessos);
            super.setMediaEspera((float)tempoDeEspera/numProcessos);  
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    public void printMedias(){
	printMedias("RR");
    }
}