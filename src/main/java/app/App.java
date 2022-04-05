package app;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

        if (args.length != 1) {
            System.out.println("Need only one argument with path to file with IP Addresses");
            System.exit(1);
        }

        String sourceFileName = args[0];
        FileInputStream fileInputStream = new FileInputStream(sourceFileName);
        Scanner scanner = new Scanner(fileInputStream);

        Set<String> set = new HashSet<>();

        while (scanner.hasNextLine()) {
            String valueWithIP = scanner.nextLine();
            if (valueWithIP.equals("")) {
                continue;
            }
            set.add(valueWithIP);
        }
        scanner.close();
        System.out.print(set.size());


    }
}
