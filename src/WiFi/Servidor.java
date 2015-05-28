package WiFi;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {  
    public static void main(String[] args) throws Exception {  
  
        ServerSocket recebimento = new ServerSocket(80);  
        Socket conexao = recebimento.accept();  
  
        DataInputStream in = new DataInputStream(conexao.getInputStream());  
         
        int setPoint = in.readInt();  
        System.out.println(in.readInt());  
  
        in.close();  
        conexao.close();  
        recebimento.close(); 
    }  
}