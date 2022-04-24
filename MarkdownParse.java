//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;

        int numberCloseParen = 0;
        int numberOpenParen = 0;
        boolean extraLinesAfterLink = false;

        // figure out number of ")" in markdown
        for (int i = 0; i < markdown.length(); i++){
            if (markdown.charAt(i) == ')'){
                numberCloseParen++;
            }
        }

        for (int i = 0; i < markdown.length(); i++){
            if (markdown.charAt(i) == '('){
                numberOpenParen++;
            }
        }

        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            // fixes test-file2
            if (openBracket < currentIndex){
                extraLinesAfterLink = true;
            }
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);   
            int closeParen = 0;

            if (numberOpenParen >= numberCloseParen){
                closeParen = markdown.indexOf(")", openParen);
                currentIndex = closeParen + 1;
            }
            else{
                int closeParenIndex = 0;
                int nextOpenBracket =  markdown.indexOf("[", openBracket + 1);

                if (nextOpenBracket == -1){
                    nextOpenBracket = markdown.length();
                }

                for (int j = 0; j < nextOpenBracket; j++){
                    if (markdown.charAt(j) == ')'){
                        closeParenIndex = j;
                        
                    }
                }
                closeParen = closeParenIndex;
                currentIndex = closeParen + 1;
            }

            //fixes test-file2
            if (extraLinesAfterLink){
                currentIndex = markdown.length();
            }

            toReturn.add(markdown.substring(openParen + 1, closeParen));
        
            //System.out.println(currentIndex);
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
