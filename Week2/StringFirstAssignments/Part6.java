
/**
 * Write a description of part6 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part6 {

    public static void main(String[] args) {

        printUrls("https://www.dukelearntoprogram.com/course2/data/manylinks.html");

    }

    public static void printUrls(String url) {

        String ytLink = "youtube.com/";
        URLResource myURL = new URLResource(url);

        for(String word: myURL.words()){
            if(word.toLowerCase().contains(ytLink)){
                int startIndex = word.indexOf("http");
                int lastIndex = word.indexOf("\"", ytLink.length());
                System.out.println(word.substring(startIndex, lastIndex));
            }
        }
    }

}
