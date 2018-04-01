import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


public class PortScanner {

    private int minPort = 1;
    private int maxPort = 0x10000;
    private String host = "192.168.1.104";
    private int timeout = 100;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMinPort() {
        return minPort;
    }

    public void setMinPort(int minPort) {
        this.minPort = minPort;
    }

    public int getMaxPort() {
        return maxPort;
    }

    public void setMaxPort(int maxPort) {
        this.maxPort = maxPort;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<Integer> scan() {
        try {
            InetAddress ia = InetAddress.getByName(getHost());
            return scan(ia);
        } catch (IOException ioe) {
            return null;
        }
    }

    List<Integer> scan(InetAddress inetAddress) {
        List<Integer> openPortsList = new ArrayList<Integer>(0xFF);
        System.out.println("scanning ports: ");
        for (int port = minPort; port <= maxPort; port++) {
            System.out.print(port);
            try {
                InetSocketAddress isa = new InetSocketAddress(inetAddress,port);
                Socket socket = new Socket();
                socket.connect(isa,timeout);
                System.out.println(" opened");
                openPortsList.add(port);
                socket.close();
            } catch (IOException ioe) {
                System.out.println("");
            }
        }
        return openPortsList;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            usage();
            return;
        }

        String host = args[0];
        System.out.println("Scanning host "+host);

        PortScanner scanner = new PortScanner();

        if (args.length==2) {
            if (args[1].indexOf("-")>-1) {
                // range of ports pointed out
                String[] ports = args[1].split("-");
                try {
                    int minPort = Integer.parseInt(ports[0]);
                    int maxPort = Integer.parseInt(ports[1]);
                    scanner.setMinPort(minPort);
                    scanner.setMaxPort(maxPort);
                } catch (NumberFormatException nfe) {
                    System.out.println("Wrong ports!");
                    return;
                }
            } else {
                // one port pointed out
                try {
                    int port = Integer.parseInt(args[1]);
                    scanner.setMinPort(port);
                    scanner.setMaxPort(port);
                } catch (NumberFormatException nfe) {
                    System.out.println("Wrong port!");
                    return;
                }
            }
        }

        scanner.setHost(host);
        List<Integer> openPortsList = scanner.scan();
        if (openPortsList != null) {
            if (openPortsList.size() >0) {
                System.out.println("List of opened ports:");
                for (Integer openedPort : openPortsList) {
                    System.out.println(openedPort);
                }
            } else {
                System.out.println("No opened ports!");
            }
        } else {
            System.out.println("Error happened!");
        }
    }

    static void usage() {
        System.out.println("Java Port Scanner usage: ");
        System.out.println("java PortScanner host port");
        System.out.println("Examples:");
        System.out.println("java PortScanner 192.168.1.100 1-1024");
        System.out.println("java PortScanner 192.168.1.100 1099");
        System.out.println("java PortScanner 192.168.1.100 (this scans all ports from 1 to 0x10000)");
    }
}