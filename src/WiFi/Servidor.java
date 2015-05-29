package WiFi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Modelo.Mala;
import Modelo.Voo;

public class Servidor {
	List<Voo> lista_voos;
	Socket conexao;
	
	public Servidor(List<Voo> lista_voos) throws IOException{
        ServerSocket recebimento = new ServerSocket(80);
        recebimento.setReuseAddress(true);
        conexao = recebimento.accept();
        
        this.lista_voos = lista_voos;
	}
	
	public Voo procuraVoo(String numeroVoo){
		Voo voo = null;
		int numero = Integer.parseInt(numeroVoo);
		for(Voo v: lista_voos){
			if (v.getNumero() == numero){
				voo = v;
			}
		}
		return voo;
	}
	
	public void encerra(){
		
	}
	
    public static void main(String[] args) throws Exception {  
  
        
        System.out.println("Conectado");
        
        //cria lista de voos para testes
        List<Voo> lista_voos = new ArrayList<Voo>();
        List<Mala> lista_malas = new ArrayList<Mala>();
        lista_malas.add(new Mala(001, "Arthur"));
        lista_malas.add(new Mala(002, "Renato"));
        lista_malas.add(new Mala(003, "Saulo"));
        lista_malas.add(new Mala(004, "David"));
        lista_voos.add(new Voo(0001, lista_malas));
        lista_voos.add(new Voo(0002, lista_malas));
        lista_voos.add(new Voo(0003, lista_malas));
  
        BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        
        //aguarda o numero do voo
        String informacao = "";
        while (!in.ready()){}
        while (in.ready()){
        	informacao += in.readLine() + "\n";
        }
        //imprimi numero do voo
        System.out.println("Numero do voo solicitado: " + informacao);
        
        while(true){
	        informacao = "";
	        while (!in.ready()){}
	        while (in.ready()){
	        	informacao += in.readLine() + "\n";
	        }
	        //System.out.println(informacao);
        }
  
        //in.close();  
        //conexao.close();  
        //recebimento.close();
    }  
}