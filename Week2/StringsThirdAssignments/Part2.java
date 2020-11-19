
/**
 * Write a description of Part2 here.
 * 
 * @author (Jingjie Ma) 
 * @version (May 24)
 */
import edu.duke.*;
public class Part2 {
    public static float cgRatio (String dna) {
        // initialize count
        int cgCount = 0;

        for (int i = 0; i < dna.length(); i ++) {
            // check whether it is c or g
            // single quote indicates a char
            if (dna.toUpperCase().charAt(i) == 'C' || dna.toUpperCase().charAt(i) == 'G'){
                cgCount++;
            }
        }
        
        
        
        return (float)cgCount/dna.length();
    }
    
    public void testCgRatio() {
        String dna = "ATGCCATAG";
        float cgr = cgRatio(dna);
        System.out.println("CG Ratio is " + cgr);
    }
            
        

}
