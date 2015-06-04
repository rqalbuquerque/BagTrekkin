import com.alien.enterpriseRFID.discovery.*;
import java.awt.event.*;

/**
 * Starts a SerialDiscoveryService to scan the local serial ports and look for
 * Alien Nanoscanner Readers.
 *
 * @version 1.1 November 2003
 * @author David Krull
 */
public class SerialDiscoveryTest implements DiscoveryListener, ActionListener {

/**
 * Constructor
 */
public SerialDiscoveryTest() throws Exception {
  SerialDiscoveryListenerService service = new SerialDiscoveryListenerService();
  service.setDiscoveryListener(this);
  service.setActionListener(this);
  service.startService();
  while (service.isRunning()) {
    Thread.sleep(100);
  }
}


/**
 * A reader has been discovered on a serial port. This method implements the
 * DiscoveryListener interface.
 *
 * @param discoveryItem details of the newly-discovered reader
 */
public void readerAdded(DiscoveryItem discoveryItem) {
  System.out.println("Reader Added:\n" + discoveryItem.toString());
}

/**
 * A known reader has been seen again. This method implements the
 * DiscoveryListener interface, but doesn't really apply to Serial Discovery.
 *
 * @param discoveryItem details of the renewed reader
 */
public void readerRenewed(DiscoveryItem discoveryItem) {
  System.out.println("Reader Renewed:\n" + discoveryItem.toString());
}


/**
 * A reader has been removed from the network and is no longer available or
 * valid. This method implements the DiscoveryListener interface, but doesn't
 * really apply to Serial Discovery.
 *
 * @param discoveryItem details of the removed reader
 */
public void readerRemoved(DiscoveryItem discoveryItem) {
  System.out.println("Reader Removed:\n" + discoveryItem.toString());
}


/**
 * ActionEvents are sent by the SerialDiscoveryListenerService to this method
 * while it scans the serial ports. This is mainly for the purposes of debugging
 * and displaying on-screen progress to the user. The ActionEvents are sent when
 * the discovery service starts scanning each port, and again when it is all
 * done scanning.
 *
 * @param actionEvent gives details about the port-scanning progress
 */
public void actionPerformed(ActionEvent event) {
  if (event.getID() == SerialDiscoveryListenerService.SCANNING_PORT) {
    System.out.println("Scanning Serial Port: " + event.getActionCommand() + "\n");
  }
  if (event.getID() == SerialDiscoveryListenerService.SCANNING_END) {
    System.out.println("Scanning Finished");
    SerialDiscoveryListenerService service = (SerialDiscoveryListenerService)event.getSource();
    System.out.println("Total Readers found = " + service.getDiscoveryItems().length);
  }
}


/**
 * Main
 */
public static final void main(String args[]) {
  try {
    new SerialDiscoveryTest();
  } catch (Exception e) {
    System.out.println("Error:" + e.toString());
  }
}

}