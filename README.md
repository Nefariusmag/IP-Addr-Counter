# IP-Addr-Counter

## Task

Have a file with IPv4 Addresses, one line - one IP, something like this:

```
145.67.23.4
8.34.5.23
89.54.3.124
89.54.3.124
3.45.71.5
...
```

The file could have a lot of IP and have size 10 and 100 Gb.

- Need to count number unique IP Addresses in the file spend a few memory and time.
- This need to be console application could get argument with path to the file with IP, in the end of the application can show count if the IP.
  (can use argument with path to file or use System.in)

Details:
- Have to use standard library of Java/Kotlin;
- Use Java 11 or higher or Kotlin;
- The application have to use method main() and this must be ready application not just library;
- The application must be published on GitHub.

To check your solution can use the big file with IP Addresses [file](https://ecwid-vgv-storage.s3.eu-central-1.amazonaws.com/ip_addresses.zip). Attention the archive size 20Gb after unarchive in 120Gb.

---
## The solution

The main idea use BitSet way to mark a bit index as used. The file will be read line by line and every IP Address will be converted to number. Next the number will be used to mark a bit in the BitSet.

Details:
1. Couldn't put all text with IP Addresses from file to memory, so need to read line by line.
2. Couldn't save all IP Address from file to HashSet, because get problem OutOfMemory, so try to use another way - BitSet where can mark bit index as used and at the end count number used bit index.
3. Couldn't put String with IP Address to BitSet, so need to convert to Integer.
4. The main way converting IP Address to Integer is use formula 192.168.1.1 = (192 * 256^3) + (168 * 256^2) + (1 * 256^1) + (1 * 256^0), and because the number could be more than Integer.MAX_VALUE, so the first step converting can use Long type.
5. After converting IP Address String to Long get some of them a few Integer.MAX_VALUE others more Integer.MAX_VALUE, and when will convert from Long to Integer can lose some date, so create two BitSet for these scenarios and for the second will minus Integer.MAX_VALUE from converted IP Address.
6. Because 255.255.255.255 after converting to Long and Integer and minus Integer.MAX_VALUE equals Integer.MAX_VALUE, so from this number will be minus 1.

## How to start

1. Install Java 11 and higher and Maven 3; 
2. Build the project use:
```shell
mvn package
```
3. Start the application:
```shell
java -jar target/app-1.0.1.jar <path to your file with IP Addresses> <True/False enable or disable validate line from the file, default is False> 
```