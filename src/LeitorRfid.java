import com.alien.enterpriseRFID.reader.*;
import com.alien.enterpriseRFID.tags.*;

public class LeitorRfid {
	AlienClass1Reader reader = new AlienClass1Reader();

	//cria um leitor
	public LeitorRfid() throws AlienReaderException {

		// reader.setConnection("COM1");

		// To connect to a networked reader instead, use the following:
		// 192.168.8.54:23
		reader.setConnection("192.168.8.54", 23);
		reader.setUsername("alien");
		reader.setPassword("password");
	}
	
	//lê as tags rfids (apenas uma vez)
	public void read() throws AlienReaderException {
		// Open a connection to the reader
		reader.open();

		// Ask the reader to read tags and print them
		Tag tagList[] = reader.getTagList();
		if (tagList == null) {
			System.out.println("No Tags Found");
		} else {
			System.out.println("Tag(s) found:");
			for (int i = 0; i < tagList.length; i++) {
				Tag tag = tagList[i];
				System.out.println("ID:" + tag.getTagID() + ", Discovered:"
						+ tag.getDiscoverTime() + ", Last Seen:"
						+ tag.getRenewTime() + ", Antenna:" + tag.getAntenna()
						+ ", Reads:" + tag.getRenewCount());
			}
		}

		// Close the connection
		reader.close();
	}

	// main para testes
	public static final void main(String args[]) {
		while (true) {
			try {
				new LeitorRfid();
			} catch (AlienReaderException e) {
				System.out.println("Error: " + e.toString());
			}
		}
	}

} // End of class AlienClass1ReaderTest