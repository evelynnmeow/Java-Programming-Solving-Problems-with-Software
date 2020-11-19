
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String a, String b) {
        int firstOccr = b.indexOf(a);
        // check to see whether it occurs again after the first occr
        int secondOccr = b.indexOf(a, firstOccr);
        
        if (firstOccr == secondOccr) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public String lastPart (String a, String b) {
        // first occurrence
        int firstIndex = b.indexOf(a);
        // if statement to check whether a is in b
        if (firstIndex == -1){
            return b;
        }
        else {
            return b.substring(firstIndex + a.length());
        }
    }
    
    public void test() {
        String a;
        String b;
        
        System.out.println("*******Start of Part 3*********");
        System.out.println("********Testing twoOccurences********");
        
        a = "by";
        b = "A story by Abby Long";
        System.out.println("Whether " + a + " occurs twice in " + b + " " + twoOccurrences(a, b));
        
        a = "an";
        b = "banana";
        
        System.out.println("Whether " + a + " occurs twice in " + b + " " + twoOccurrences(a, b));
        
        a = "atg";
        b = "ctgtatgta";
        System.out.println("Whether " + a + " occurs twice in " + b + " " + twoOccurrences(a, b));
        
        System.out.println("********Testing lastPart********");
        a = "an";
        b = "banana";
        System.out.println("The part of the string after " + a + " in " + b + " is " + lastPart(a, b));
        
        a = "zoo";
        b = "forest";
        System.out.println("The part of the string after " + a + " in " + b + " is " + lastPart(a, b));
        
        System.out.println("*******End of Part 3*********");
    }
    
}
