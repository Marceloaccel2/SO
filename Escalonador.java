/*
  Projeto SO - Aluno Marcelo Alves Gomes
  Entrada : Cada linha é um processo
            primeiro numero = tempo de chegada do processo
            segundo numero = tempo de duração do processo
 
  Saída   : linha contendo a sigla de cada um dos tres algoritmos(FCFS,SJF,RR) 
            e os valores das três métricas solicitadas(tempo de retorno, tempo 
            de resposta e tempo de espera)
 */
import static java.lang.String.format;
import java.util.List;


public class Escalonador {
    /*Uso do float porque requer menos memoria e por ser mais eficiente no processamento*/
    private float mediaRetorno;
    private float mediaResposta;
    private float mediaEspera;
    
    /* List<Processo> processos será um ARRAY LIST do tipo Processo (classe) */
    public int minimoTempoChegada(List<Processo> processos){
        int min = Integer.MAX_VALUE;
        for(Processo p:processos)
        {
            /*esse if irá servir para definir sempre o menor tempo de chegada dos
            processos , se o processo que entrar tiver um tempo de chegada menor 
            que o tempo de chegada minimo , o minimo passa a ser o tempo de chegada
            desse processo*/
            
            if(p.getTempoDeChegada()<min)
                min=p.getTempoDeChegada();
        }
        return min;
        }
    //Setters do escalonador

    public void setMediaRetorno(float mediaRetorno) {
        this.mediaRetorno = mediaRetorno;
    }

    public void setMediaResposta(float mediaResposta) {
        this.mediaResposta = mediaResposta;
    }

    public void setMediaEspera(float mediaEspera) {
        this.mediaEspera = mediaEspera;
    }
    
    //Para printar a saida que são as medias seguindo o padrão do professor
    public void printMedias(String escalonador)
    {
		System.out.println(format("%s %.1f %.1f %.1f", escalonador, mediaRetorno,mediaResposta,mediaEspera));
    }
}