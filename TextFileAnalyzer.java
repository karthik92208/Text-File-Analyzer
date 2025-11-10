package Task5;

import java.util.*;
import java.io.*;

public class TextFileAnalyzer {

    File file;

    TextFileAnalyzer(String fileName){
        this.file = new File(fileName);
    }

    public void displayText() throws IOException, FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        System.out.println("________________________________________________________________________________________________________________________________________________________________________________________________________________________");
        while((line = br.readLine()) != null){
            System.out.println(line);
        }
        System.out.println("_________________________________________________________________________________________________________________________________________________________________________________________________________________________");
        br.close();
    }
    public void writeText(String text) throws IOException, FileNotFoundException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(text);
        bw.close();
        System.out.println("Text written into file successfully.");
    }
    public void textAnalysis() throws IOException, FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        int charCount = 0;
        int lineCount = 0;
        int wordCount = 0;
        while((line = br.readLine()) != null){
            lineCount++;
            charCount += line.length();

            if(!line.trim().isEmpty()) {
                String[] words = line.trim().split("\\s+");
                wordCount += words.length;

            }
        }
        System.out.println("\n================== Text Analysis ==================");
        System.out.println("Lines: " + lineCount);
        System.out.println("Words: " + wordCount);
        System.out.println("Characters: " + charCount);
        System.out.println("___________________________________________________");


        br.close();
    }
    public void wordSearch(Scanner sc) throws IOException, FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader(file));

        System.out.println("Enter a word to search: ");
        String searchWord = sc.nextLine();
        int wordOccurrence = 0;
        String line;
        while((line = br.readLine()) != null){
            String[] words = line.trim().split("\\s+");
            for(String word : words){
                word = word.replaceAll("[^a-zA-Z]", "");
                if(word.equalsIgnoreCase(searchWord)){
                    wordOccurrence++;
                }
            }
        }
        br.close();
        if (wordOccurrence > 0){
            System.out.println("___________________________________________________");
            System.out.println("\"" + searchWord + "\" appeared for " + wordOccurrence + " times." );
            System.out.println("___________________________________________________");
        } else {
            System.out.println("The word \"" + searchWord + "\" was not found.");
        }
    }


    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n******** TEXT FILE ANALYZER ********\n");
            System.out.print("Enter file path: ");
            String data = sc.nextLine();


            TextFileAnalyzer obj = new TextFileAnalyzer(data);

            while (true) {
                System.out.println("\n......... MENU ......... \n1. Display text.\n2. Text Analysis (Line count, Word Count, Charater Count).\n3. Word Search.\n4. Write Text.\n5. Exit. ");
                System.out.print("\nChoose an option: ");
                int option = sc.nextInt();


                switch (option) {
                    case 1:
                        obj.displayText();
                        break;

                    case 2:
                        obj.textAnalysis();
                        break;

                    case 3:
                        sc.nextLine();
                        obj.wordSearch(sc);
                        break;

                    case 4:
                        sc.nextLine();
                        while(true) {
                        System.out.print("Are you sure! Do you want to modify the file content permanently? (yes/no): ");
                        String confirmation = sc.nextLine();
                        if (confirmation.equalsIgnoreCase("yes")) {
                            System.out.print("Enter text to write into the file: ");
                            String text = sc.nextLine();
                            obj.writeText(text);
                            break;
                        } else if (confirmation.equalsIgnoreCase("no")) {
                            System.out.println("Text in the file remains unchanged.");
                            break;
                        } else{
                            System.out.println("Please enter a valid input.");
                        }
                    }
                        break;

                    case 5:
                        System.out.println("Text File Analyzer Exited. Thank You.. ");
                        sc.close();
                        return;

                    default:
                        System.out.println("Please enter a valid option from the Menu..");
                }
            }

        } catch (FileNotFoundException e){
            System.out.println("Error! File not found." + e.getMessage());
        } catch (IOException e){
            System.out.println("Error: IOException Caught ");
            e.printStackTrace();
        }
    }

}
