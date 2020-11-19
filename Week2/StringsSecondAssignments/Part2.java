
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany (String a, String b) {
        // first occurrence of a in b
        int firstOccr = 0;
        // total occurrence
        int totalOccr = 0;
        // start index
        int start = 0;
        // while loop
        while (true) {
            firstOccr = b.indexOf(a, start);
            
            // if there is no occurrence
            if (firstOccr == -1) {
                break;
            }
            
            // if there is one occurrence
            totalOccr++;
            start = firstOccr + a.length();
        }
        return totalOccr;
    }
    
    public void testHowMany() {
       String a1 = "GAA";
       String b1 = "ATGAACGAATTGAATC";
        
       System.out.println(howMany(a1, b1));
       
       String a2 = "AA";
       String b2 = "ATAAAA";
       
       System.out.println(howMany(a2, b2));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
