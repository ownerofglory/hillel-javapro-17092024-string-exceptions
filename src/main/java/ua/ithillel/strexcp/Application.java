package ua.ithillel.strexcp;

import ua.ithillel.strexcp.flyweight.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) {
//        String pattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        String pattern = "(\\w{1,256}\\.?){1,20}@(\\w{1,256}\\.?){0,20}(\\w){1,256}";
//        String pattern = "\\w";
        String text = "Kyiv (also Kiev)[a] is the capital and most populous city of Ukraine. It is in north-central Ukraine along the Dnieper River. As of 1 January 2022, its population was 2,952,301,[2] making Kyiv the seventh-most populous city in Europe.[11] Kyiv is an important industrial, scientific, educational, and cultural center in Eastern Europe. It is home to many high-tech industries, higher education institutions, and historical landmarks. The city has an extensive system of public transport and infrastructure, including the Kyiv Metro.\n" +
                "\n" +
                "The city's name is said to derive from the name of Kyi, one of its four legendary founders. During its history, Kyiv, one of the oldest cities in Eastern user.lastname@gmail.com Europe, passed through several stages of prominence and obscurity. The city probably existed as a commercial center as early as the 5th century. A Slavic settlement on the great trade route between Scandinavia and Constantinople, Kyiv was a tributary of the Khazars,[12] until its capture by the Varangians (Vikings) in the mid-9th century. Under Varangian rule, the city became a capital of sddslastname@test.com Kievan Rus', the first East Slavic state. Completely destroyed during the Mongol invasions in 1240, the city lost most of its influence for the centuries to come. Coming under Lithuania, then Poland and then Russia, the city would grow from a frontier market into an important centre of Orthodox learning in the sixteenth century, and later of industry, commerce, and administration by the nineteenth.[1]\n" +
                "\n" +
                "The city prospered again during the Russian Empire's Industrial Revolution in the late 19th century. In 1918, when the Ukrainian People's Republic declared independence from the Russian Republic after the October Revolution there, Kyiv became its capital. From the end of the Ukrainian-Soviet and Polish-Soviet wars in 1921, Kyiv was a city of the Ukrainian SSR, and made its capital in 1934. The city suffered significant destruction during World War II but quickly recovered in the postwar years, remaining the Soviet Union's third-largest city. ";

//        boolean matches = Pattern.matches(pattern, text);
        Pattern compile = Pattern.compile(pattern, Pattern.MULTILINE);
        Matcher matcher = compile.matcher(text);
        boolean matches = matcher.matches();


        String s = "Hello World!";
        // immutability
        String lowerCase = s.toLowerCase();
        System.out.println(lowerCase);
        String upperCase = s.toUpperCase();
        System.out.println(upperCase);

        char i = (char) s.codePointAt(4);

        boolean containsHello = s.contains("Hello");
        boolean containsHello2 = s.contains(new StringBuffer("Hello"));

        boolean startsWithHello = s.startsWith("Hello");
        boolean endsWith = s.endsWith("Hello");

        int positionOfW = s.indexOf("W");

        boolean empty = " ".isEmpty(); // space
        boolean blank = " ".isBlank(); // space



        System.out.println("Contat: " + s);

        String stringSum = s + "Hi everyone";


        // StringBuffer - thread safe
        // StringBuilder  - more efficient

        StringBuffer fileContent = new StringBuffer();
        try(FileInputStream fileInputStream = new FileInputStream("./files/text2.txt")) {

            byte[] buffer = new byte[32];
            int read;
            while ((read = fileInputStream.read(buffer)) != -1) {
                s = new String(buffer, 0, read, StandardCharsets.UTF_16BE);
                fileContent.append(s);
                System.out.println("File content " + s);
            }

        } catch (IOException e) {
            System.out.println("File not found");
        };

        String fileContentString = fileContent.toString();
        System.out.printf(fileContentString);

        //
//        String fileContent = "";
//        try(FileInputStream fileInputStream = new FileInputStream("./files/text2.txt")) {
//
//            byte[] buffer = new byte[32];
//            int read;
//            while ((read = fileInputStream.read(buffer)) != -1) {
//                s = new String(buffer, 0, read, StandardCharsets.UTF_16BE);
//                fileContent += s;
//                System.out.println("File content " + s);
//            }
//
//        } catch (IOException e) {
//            System.out.println("File not found");
//        };

        System.out.println(fileContent);

        Player player1 = new Player("John", 23, 36, 0);
        Player player2 = new Player("John3", 23, 36, 0);
        Player playerNPC1 = new Player("NPC1", 0, 0, 255);

        
        // ... a lot of code
        String s1 = "Hello World!";

        System.out.printf("equal by reference %s\n", s == s1);
        System.out.printf("equal by content %s\n", s.equals(s1));

        String sHeap = new String("Hello World!");
        String intern = sHeap.intern();

        System.out.printf("Heap vs String Pool; equal by reference %s\n", s == sHeap);
        System.out.printf("Heap vs String Pool; equal by content %s\n", s.equals(sHeap));

        System.out.printf("intern; equal by reference %s\n", s == intern);
        System.out.printf("intern; equal by content %s\n", s.equals(intern));


        int length = s.length();
        byte[] bytes = s.getBytes();
        int codePointCount = s.codePointCount(0, s.length());

        System.out.printf("length: %d, bytes: %d, code points: %d\n", length, bytes.length, codePointCount);

        s = "Grüß dich!";
        length = s.length();
        bytes = s.getBytes();
        codePointCount = s.codePointCount(0, s.length());
        System.out.printf("length: %d, bytes: %d, code points: %d\n", length, bytes.length, codePointCount);

        s = "\uD83E\uDD14";

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            s = scanner.nextLine();
            length = s.length();
            bytes = s.getBytes();
            codePointCount = s.codePointCount(0, s.length());
            System.out.printf("length: %d, bytes: %d, code points: %d\n", length, bytes.length, codePointCount);
        }

        char c = 'a'; // 2 bytes
        char[] chars = s.toCharArray();

        System.out.println();
    }
}
