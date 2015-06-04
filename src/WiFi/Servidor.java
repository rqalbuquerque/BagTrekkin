package WiFi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Modelo.Mala;
import Modelo.Voo;

public class Servidor {
	private List<Voo> lista_voos;
	private static Socket conexao;
	private BufferedReader in;
	
	public Servidor(List<Voo> lista_voos) throws IOException{
        ServerSocket recebimento = new ServerSocket(7172);
        recebimento.setReuseAddress(true);
        conexao = recebimento.accept();
        in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        
        this.lista_voos = lista_voos;
	}
	
	public Voo procuraVoo(String numeroVoo){
		Voo voo = null;
		for(Voo v: lista_voos){
			if (v.getNumero().equals(numeroVoo)){
				voo = v;
			}
		}
		return voo;
	}
	
	public void encerra(){
		
	}

	public List<Voo> getLista_voos() {
		return lista_voos;
	}

	public void setLista_voos(List<Voo> lista_voos) {
		this.lista_voos = lista_voos;
	}

	public static Socket getConexao() {
		return conexao;
	}

	public static void setConexao(Socket conexao) {
		Servidor.conexao = conexao;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}  
	
	public static void enviar(String informacao) throws Exception  {
        PrintWriter out = new PrintWriter(conexao.getOutputStream(), true);
        out.print(informacao);
        out.flush();
	}
	
	public void enviaListaVoo(Voo v) throws Exception{
		String str = v.formataStringVoo();
		enviar(str);
	}
	
    public static void main(String[] args) throws Exception {

        System.out.println("Servidor online...");
        
        //cria lista de voos para testes
        List<Voo> lista_voos = new ArrayList<Voo>();
        List<Mala> lista_malas = new ArrayList<Mala>();
        lista_malas.add(new Mala("E200 3411 B802 0115 1612 6723", "Arthur"));
        lista_malas.add(new Mala("E200 2996 9618 0246 2310 256F", "Renato"));
        lista_malas.add(new Mala("E200 6296 9619 0229 0370 EC2B", "Saulo"));
        lista_malas.add(new Mala("E200 2996 9618 0246 2230 2CD7", "David"));
        lista_voos.add(new Voo("0001", lista_malas));
        lista_voos.add(new Voo("0002", lista_malas));
        lista_voos.add(new Voo("0003", lista_malas));
  
        //BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        Servidor server = new Servidor(lista_voos);
        System.out.println("Cliente se conectou...");
        System.out.println("Aguardando numero do voo...");
        
        //aguarda o numero do voo
        String informacao = "";
        while (!server.getIn().ready()){}
        while (server.getIn().ready()){
        	informacao += server.getIn().readLine() + "\n";
        }
        //imprimi numero do voo
        System.out.println("Numero do voo solicitado: " + informacao);
        
        //Busca linearmente até encontrar o voo
        System.out.println(informacao);
        informacao = informacao.replaceAll("\n", "");  
        System.out.println(informacao);
        Voo v = null;
        for(int i=0; i < lista_voos.size(); i++){
        	if(lista_voos.get(i).getNumero().equals(informacao)){
        		v = lista_voos.get(i);
        	}
        }
        
        //Envia a lista de malas do voo solicitado
        server.enviaListaVoo(v);
        
        System.out.println("Iniciando leitura do RFID...");
        while(true){
	        informacao = "";
	        while (!server.getIn().ready()){}
	        while (server.getIn().ready()){
	        	informacao += server.getIn().readLine() + "\n";
	        }
	        System.out.println(informacao);
        }
  
        //in.close();  
        //conexao.close();  
        //recebimento.close();
    }
}