import java.util.Scanner;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SimplePing {

    private static String getHostFromScanner() {
        try(Scanner in = new Scanner(System.in)) {
            System.out.print("Enter a host: ");
            String line = in.nextLine();
            return line.length() > 0 ? line : "localhost";
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        String host = args.length >= 1 ? args[0] : getHostFromScanner();
        int timeout = 4000;
        int count = 4;
        if (args.length >= 2) {
            String paramTimeout = "-t:";
            int idxTimeout = args[1].indexOf(paramTimeout);
            if (idxTimeout >= 0) {
                timeout = Integer.parseInt(args[1].substring(paramTimeout.length()));
            }
        }
        while(count-- > 0)
        {
            System.out.print("Host: " + host + " is ");
            if (InetAddress.getByName(host).isReachable(timeout))
            {
                System.out.println("reacheable");
            } else
            {
                System.out.println("unreacheable");
            }
        }
    }
}