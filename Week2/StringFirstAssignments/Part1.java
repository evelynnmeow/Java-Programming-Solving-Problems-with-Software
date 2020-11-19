
/**
 * Write a description of Part1 here.
 * 
 * @author (Jingjie Ma) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        // initialize string
        String result = "";
        // postion of beginning 
        int startIndex = dna.toUpperCase().indexOf("ATG");
        if (startIndex == -1) {
            return "No start Coden";
        }
        // position of ending
        int endIndex = dna.toUpperCase().indexOf("TAA");
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
        System.out.println("**********");
        String t1 = "GAAATGATTGTTATTTAA";
        String r1 = findSimpleGene(t1);
        System.out.println("DNA is " + t1);
        System.out.println("Gene is " + r1);
       
        String t2 = "atggggtttaaataataatag";
        System.out.println(t2.equals(r1));
        String t3 = "atgcctag";
        String r3 = findSimpleGene(t3);
        System.out.println("DNA is " + t3);
        System.out.println("Gene is " + r3);
        String t4 = "";
        String r4 = findSimpleGene(t4);
        System.out.println("DNA is " + t4);
        System.out.println("Gene is " + r4);
        String t5 = "AGCCCTAA";
        String r5 = findSimpleGene(t5);
        System.out.println("DNA is " + t5);
        System.out.println("Gene is " + r5);
    }
    
}