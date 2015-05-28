package WiFi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {  
	  
	   
    public static void main(String[] args) throws Exception  {  

          Socket envio = new Socket("java.sun.com", 80);  

          DataInputStream in = new DataInputStream(envio.getInputStream()); //canal pra receber dados  
          DataOutputStream out = new DataOutputStream(envio.getOutputStream()); //canal para enviar dados  

          int SetPoint = 3;  
          out.writeInt(SetPoint);  

          out.close();  
          envio.close();  


    }  
}