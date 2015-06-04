package WiFi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	Socket envio;
	private BufferedReader in;
	
	public BufferedReader getIn() {
		return in;
	}
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	public Cliente() throws Exception  {
        envio = new Socket("localhost", 7172);
        in = new BufferedReader(new InputStreamReader(envio.getInputStream()));
	}
	public void enviar(String informacao) throws Exception  {
        PrintWriter out = new PrintWriter(envio.getOutputStream(), true);
        out.print(informacao);
        out.flush();
	}
	
	public String recebeString() throws IOException{
        while (!in.ready()){}
        return in.readLine() + "\n";     
	}
	
	public void encerrar() throws IOException{
        envio.close();
	}
	   
    public static void main(String[] args) throws Exception  {  
         Cliente cliente = new Cliente();
         cliente.enviar("teste");
    }  
    
}