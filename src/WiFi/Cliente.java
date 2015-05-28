package WiFi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	Socket envio;
	
	public Cliente() throws Exception  {
        envio = new Socket("localhost", 80);
	}
	public void enviar(String informacao) throws Exception  {
        int SetPoint = 3;
        PrintWriter out = new PrintWriter(envio.getOutputStream(), true);
        out.print(informacao);
        
        out.flush();
	}
	public void encerrar() throws IOException{
        envio.close();
	}
	   
    public static void main(String[] args) throws Exception  {  
         Cliente cliente = new Cliente();
         cliente.enviar("teste");
    }  
}