import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Modelo.Mala;
import Modelo.Voo;
import WiFi.Cliente;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;

public class LeitorRfid {
	private AlienClass1Reader reader = new AlienClass1Reader();
	private Cliente cliente;
	private Voo voo;

	private String strMalasCorretas;
	private String strMalasErradas;

	//cria um leitor
	public LeitorRfid() throws Exception {

		//reader.setConnection("COM22");

		// To connect to a networked reader instead, use the following:
		// 192.168.8.54:23
		reader.setConnection("192.168.8.54", 23);
		reader.setUsername("alien");
		reader.setPassword("password");
		

		cliente = new Cliente();
	}
	
	public String formataListaMalas(List<Mala> malas){
		String strVoo = "";
		for(Mala item : malas){
			strVoo += item.getTag() + "\n";
		}
		strVoo += "end\n";
		return strVoo;
	}
	
	//lê as tags rfids (apenas uma vez)
	public void read() throws Exception {
		// Open a connection to the reader
		reader.open();

		// Ask the reader to read tags and print them
		Tag tagList[] = reader.getTagList();
		if (tagList == null) {
			System.out.println("No Tags Found");
		} else {
			String envio = "";
			envio = envio + "Tag(s) found:" + "\n";
			//System.out.println("Tag(s) found:");
			
			List<Mala> malas = voo.getMalas();
			List<Mala> malas_erradas = new ArrayList<Mala>();
			List<Mala> malas_corretas = new ArrayList<Mala>();
			
			//pega tag por tag lida pelo leitor
			for (int i = 0; i < tagList.length; i++) {
				Tag tag = tagList[i];
				Boolean achou = false;
				
				//procura mala na lista de malas do voo
				if(malas != null){
					for(Mala m: malas){
						if(m.getTag().equals(tag.getTagID())){
							malas_corretas.add(new Mala(tag.getTagID(), "desconhecido"));
							malas.remove(m);
							achou = true;
							break;
						}
					}
				}
				
				//adiciona mala errada na lista de malas erradas
				if(!achou){
					Boolean achou_mala_errada = false;
					for(Mala m: malas_erradas){
						if(m.getTag().equals(tag.getTagID())){
							achou_mala_errada = true;
						}
					}
					if(!achou_mala_errada){
						malas_erradas.add(new Mala(tag.getTagID(), "desconhecido"));
					}
				}
				envio = envio + "ID:" + tag.getTagID() + ", Discovered:"
						+ tag.getDiscoverTime() + ", Last Seen:"
						+ tag.getRenewTime() + ", Antenna:" + tag.getAntenna()
						+ ", Reads:" + tag.getRenewCount() + "\n";
			}
			//atualiza a lista de malas do voo
			voo.setMalas(malas);

			strMalasCorretas = formataListaMalas(malas_corretas);
			strMalasErradas = formataListaMalas(malas_erradas);
		}
	}
	
	public String recebeListaVoo(String numero) throws IOException{
		String str = "";
		String strLista = "";
        List<Mala> lista_malas = new ArrayList<Mala>();
        
		while(!str.equals("end")){
			str = cliente.recebeString();
			lista_malas.add(new Mala(str.replaceAll("\n", ""), "desconhecido"));
			strLista += str;
			System.out.println(str);
		}
		voo = new Voo(numero, lista_malas);
		return strLista;
	}
	
	//envia string para o servidor
	public void enviar(String str) throws Exception{
		cliente.enviar(str+"\n");
	}
 
	
	//encerra a conexao
	public void encerrar() throws IOException{
		// Close the connection
		cliente.encerrar();
		reader.close();
	}

	// main para testes
	public static final void main(String args[]) throws Exception {
		LeitorRfid leitor = new LeitorRfid();
		
		Scanner entrada = new Scanner(System.in); 
		String voo = "";
		int estado = 1;
		
		while (true) {
			switch (estado){
				case 1:
					System.out.println("Digite o numero do voo:");
					voo = entrada.nextLine();
					leitor.enviar(voo);
					estado = 2;
					break;
				case 2:
					String strLista = "";
					strLista = leitor.recebeListaVoo(voo);
					System.out.println(strLista);
					estado = 3;
					break;
				case 3:
					try {
						
						leitor.read();
					} catch (AlienReaderException e) {
						leitor.encerrar();
						System.out.println("Error: " + e.toString());
					}
					break;
			}
		}
	}

} // End of class AlienClass1ReaderTest