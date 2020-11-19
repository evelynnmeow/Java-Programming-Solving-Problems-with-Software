
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCoden, String endCoden) {
        // initialize string
        String result = "";
        // postion of beginning 
        int startIndex = dna.toUpperCase().indexOf(startCoden);
        if (startIndex == -1) {
            return "No start Coden";
        }
        // position of ending
        int endIndex = dna.toUpperCase().indexOf(endCoden);
        if (endIndex == -1) {
            return "No end Coden";
        }
        // get the substring inside
        result = dna.substring(startIndex, endIndex + 3);
        if (result.length() % 3 == 0){
            return result;
        }
        else {
            return "Not valid length";
        }
    }
    
    public void testSimpleGene(){
        System.out.println("**********Start of Part 2***********");
        String start = "ATG";
        String end = "TAA";
        String t1 = "ATCTAACATC";
        String r1 = findSimpleGene(t1, start, end);
        System.out.println("DNA is " + t1);
        System.out.println("Gene is " + r1);
       
        String t2 = "ATTATCATGTTA";
        String r2 = findSimpleGene(t2, start, end);
        System.out.println("DNA is " + t2);
        System.out.println("Gene is " + r2);
       
        String t3 = "ATTAGTGTA";
        String r3 = findSimpleGene(t3, start, end);
        System.out.println("DNA is " + t3);
        System.out.println("Gene is " + r3);
        String t4 = "gatgctataat";
        String r4 = findSimpleGene(t4, start, end);
        System.out.println("DNA is " + t4);
        System.out.println("Gene is " + r4);
        String t5 = "ATGGGTTAAGTC";
        String r5 = findSimpleGene(t5, start, end);
        System.out.println("DNA is " + t5);
        System.out.println("Gene is " + r5);
        System.out.println("**********End of Part 2***********");
    }

}
