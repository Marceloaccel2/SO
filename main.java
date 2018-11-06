/*
  Projeto SO - Aluno Marcelo Alves Gomes
  Entrada : Cada linha é um processo
            primeiro numero = tempo de chegada do processo
            segundo numero = tempo de duração do processo
 
  Saída   : linha contendo a sigla de cada um dos tres algoritmos(FCFS,SJF,RR) 
            e os valores das três métricas solicitadas(tempo de retorno, tempo 
            de resposta e tempo de espera)
 */
import java.io.*;
import java.util.*;
/*Classe principal que vai rodar todo as outras classes*/
public class main {
    public static void main(String[] args) throws FileNotFoundException{
    /*Tratar possiveis errors , como NZEC ERROR */
        try{
            /*Vai ler o arquivo txt de entrada de dados*/
            FileReader arquivo = new FileReader("src/dados.txt");
            Scanner dados = new Scanner(arquivo);
            int cod = 0;
            /*Ira criar um arraylist ProcessoLista do tipo Processo*/
            ArrayList<Processo> ProcessosLista = new ArrayList<>();
            
            /*Apos ler o arquivo de entrada de dados, vai separar os valores de tempo de chegada e duracao e armazenar em um novo processo
            Se for hasNext() do iterator, esse método retorna true enquanto existe proximo elemento na lista, geralmente é usado com o método Next();*/
            while(dados.hasNext())
                    {
                        int tempoDeChegada = Integer.valueOf(dados.next());
                        int duracao = Integer.valueOf(dados.next());

                        ProcessosLista.add(new Processo(++cod,tempoDeChegada,duracao));
                    }
            /*Vai ordenar os processos pelo TempoDeChegada , apos isso , vai colocar essa lista em cada construtor (FCFS,SJF,RR)*/
            Collections.sort(ProcessosLista, new Comparator<Processo>(){
                public int compare(Processo p1, Processo p2){
                    return Integer.valueOf(p1.getTempoDeChegada()).compareTo(p2.getTempoDeChegada());
                }
            });
            
            //Vai adicionar a lista ordenada em cada algoritmo e declarar os algoritmos a serem usados
            FCFS algoritmoFCFS = new FCFS(ProcessosLista);
            SJF algoritmoSJF = new SJF(ProcessosLista);
            RR algoritmoRR = new RR(ProcessosLista);
            //Chama a funcao de printar media de cada escalonador 
            algoritmoFCFS.printMedias();
            algoritmoSJF.printMedias();
            algoritmoRR.printMedias();
            
        }catch(Exception e){
                System.out.println(e.getMessage());
        }
    }
}