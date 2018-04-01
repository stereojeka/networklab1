import java.net.*;
import java.util.Scanner;

public class URLAnalyzer {

    private static String getURLFromScanner() {
        try(Scanner in = new Scanner(System.in)) {
            System.out.print("Enter a URL e.g.(https://www.chdu.edu.ua):");
            String line = in.nextLine();
            return line.length() > 0 ? line : "https://raw.githubusercontent.com/stereojeka/myping/master/myping/myping.cpp";
        }
    }

    public static void analyzeURL(URL url) {
        System.out.println("URL host: " + url.getHost());
        System.out.println("URL file: "+url.getFile());
        System.out.println("URL port: "+url.getPort());
        System.out.println("URL ref: "+url.getRef());
        System.out.println("URL hashcode: "+url.hashCode());
    }

    public static void main(String[] args) throws MalformedURLException {
        URL url = null;
        String urlString = args.length >= 1 ? args[0] : getURLFromScanner();
        url = new URL(urlString);
        analyzeURL(url);
    }

}
