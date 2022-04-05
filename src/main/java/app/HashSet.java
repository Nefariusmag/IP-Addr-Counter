package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class HashSet
{
    public static void main( String[] args ) throws IOException {

        if (args.length != 1) {
            System.out.println("Need only one argument with path to file with IP Addresses");
            System.exit(1);
        }

        String sourceFileName = args[0].toString();
        FileInputStream fileInputStream = new FileInputStream(sourceFileName);
        Scanner scanner = new Scanner(fileInputStream);

        Set<String> set = new java.util.HashSet<String>();

        while (scanner.hasNextLine()) {
            set.add(scanner.nextLine());
        }
        scanner.close();
        System.out.print(set.size());
        
    }
}
