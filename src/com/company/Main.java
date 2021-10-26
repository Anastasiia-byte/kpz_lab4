package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static List<String> potentialEmails = new ArrayList<>();
    private static List<String> emails = new ArrayList<>();

    public static void main(String[] args) {
        String fileName = "input.txt";
        String input = FileReader.getInput(fileName);

        List<String> lines = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(input, ".\n");
        while (st.hasMoreTokens()){
            lines.add(st.nextToken());
        }

        st = new StringTokenizer(input, " \t\n\r,:;'");
        while(st.hasMoreTokens()){
            String word = st.nextToken();
            if(checkForEmail(word)){
                emails.add(word);
                input = input.replace(word, lines.get(lines.size()-1));
            }
        }

        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.append(input);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Emails: ");
        for (String email : emails){
            System.out.println(email);
        }

        System.out.println("\nEmails with mistakes:");
        for (String word : potentialEmails){
            System.out.println(word);
        }

    }

    public static boolean checkForEmail(String word){
        if(!word.contains("@")){
            return false;
        }

        String name = word.substring(0, word.indexOf("@"));
        String domain = word.substring(word.indexOf("@"));
        if(!checkPart(domain) || !domain.contains(".")){
            potentialEmails.add(word);
            return false;
        }
        return checkPart(name) && checkPart(domain);
    }

    public static boolean checkPart(String part){
        String[] specialChars = new String[] {"!", "#", "$", "%", "^", "&", "*", "(", ")", "-", "/", "~", "[", "]"} ;
        for (String specialChar : specialChars) {
            if (part.contains(specialChar)){
                return false;
            }
        }
        return true;
    }
}
