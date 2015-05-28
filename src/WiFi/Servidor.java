package WiFi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {  
    public static void main(String[] args) throws Exception {  
  
        ServerSocket recebimento = new ServerSocket(80);
        recebimento.setReuseAddress(true);
        Socket conexao = recebimento.accept();  
  
        BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        
        while(true){
	        String informacao = "";
	        while (!in.ready()){}
	        while (in.ready()){
	        	informacao += in.readLine() + "\n";
	        }
	        System.out.println(informacao);
        }
  
        //in.close();  
        //conexao.close();  
        //recebimento.close();
    }  
}