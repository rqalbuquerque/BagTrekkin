import java.io.IOException;

import WiFi.Cliente;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;

public class LeitorRfid {
	AlienClass1Reader reader = new AlienClass1Reader();
	Cliente cliente;

	//cria um leitor
	public LeitorRfid() throws Exception {

		// reader.setConnection("COM1");

		// To connect to a networked reader instead, use the following:
		// 192.168.8.54:23
		reader.setConnection("192.168.8.54", 23);
		reader.setUsername("alien");
		reader.setPassword("password");
		

		cliente = new Cliente();
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
			for (int i = 0; i < tagList.length; i++) {
				Tag tag = tagList[i];
				/*
				System.out.println("ID:" + tag.getTagID() + ", Discovered:"
						+ tag.getDiscoverTime() + ", Last Seen:"
						+ tag.getRenewTime() + ", Antenna:" + tag.getAntenna()
						+ ", Reads:" + tag.getRenewCount());
				*/
				envio = envio + "ID:" + tag.getTagID() + ", Discovered:"
						+ tag.getDiscoverTime() + ", Last Seen:"
						+ tag.getRenewTime() + ", Antenna:" + tag.getAntenna()
						+ ", Reads:" + tag.getRenewCount() + "\n";
			}
			System.out.println(envio);
			cliente.enviar(envio);
		}
	}
	public void encerrar() throws IOException{
		// Close the connection
		cliente.encerrar();
		reader.close();
	}

	// main para testes
	public static final void main(String args[]) throws Exception {
		LeitorRfid leitor = new LeitorRfid();
		
		while (true) {
			try {
				leitor.read();
			} catch (AlienReaderException e) {
				leitor.encerrar();
				System.out.println("Error: " + e.toString());
			}
		}
	}

} // End of class AlienClass1ReaderTest