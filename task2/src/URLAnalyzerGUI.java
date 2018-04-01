import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class URLAnalyzerGUI {

    private static void analyzeURL(URL url) {
        //Create and set up the window.
        JFrame frame = new JFrame(url.toString());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel hostLabel = new JLabel("URL host: " + url.getHost());
        hostLabel.setBounds(0, 0, 400, 50);
        frame.getContentPane().add(hostLabel);
        JLabel fileLabel = new JLabel("URL file: " + url.getFile());
        fileLabel.setBounds(0, 50, 400, 50);
        frame.getContentPane().add(fileLabel);
        JLabel portLabel = new JLabel("URL port: " + url.getPort());
        portLabel.setBounds(0, 100, 400, 50);
        frame.getContentPane().add(portLabel);
        JLabel refLabel = new JLabel("URL ref: " + url.getRef());
        refLabel.setBounds(0, 150, 400, 50);
        frame.getContentPane().add(refLabel);
        JLabel hashLabel = new JLabel("URL hashcode: " + url.hashCode());
        hashLabel.setBounds(0, 200, 400, 50);
        frame.getContentPane().add(hashLabel);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private static String getURLFromScanner() {
        try(Scanner in = new Scanner(System.in)) {
            System.out.print("Enter a URL e.g.(https://www.chdu.edu.ua):");
            String line = in.nextLine();
            return line.length() > 0 ? line : "https://raw.githubusercontent.com/stereojeka/myping/master/myping/myping.cpp";
        }
    }

    public static void main(String[] args) {
        URL url = null;
        String urlString = args.length >= 1 ? args[0] : getURLFromScanner();
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        URL finalUrl = url;
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                analyzeURL(finalUrl);
            }
        });
    }

}
