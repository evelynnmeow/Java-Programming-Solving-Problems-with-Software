
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        
        // currIndex is three codon away from the startIndex
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        // as long as current index is not -1
        while (currIndex != -1) {
           
            // check if diff is a multiple of 3
            // if so, return currIndex
            // else update currIndex and find stop codon from currIndex + 1
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }

                currIndex = dna.indexOf(stopCodon, currIndex + stopCodon.length());
            
        }
        return dna.length();
    }
    
    public void testFindStopCodon() {
        
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        
        int index = findStopCodon(dna, 0, "TAA");
        if (index != 9) System.out.println("error on 9");

        
        index = findStopCodon(dna, 9, "TAA");
        if (index != 21) System.out.println("error on 21");
                
        index = findStopCodon(dna, 1, "TAA");
        if (index != 26) System.out.println("error on 26");
                
        System.out.println("finished");
    }
    
    // returns gene or empty string
    public String findGene(String dna, int where) {
        int startIndex = startIndex = dna.indexOf("ATG", where);
        // if there is no ATG, return empty string
        if (startIndex == -1) {
            return "";
        }
        // first occurrence of TAA
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        // first occurrence of TAG
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        // first occurrence of TGA
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        // find the min of the above three indices
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        
        // if there is no stop codon
        if (minIndex == dna.length()) {
            return "";
        }
        
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void testFindGene(){
        String dna = "GTTAATGTAGCTTAAACCTTTAAAGCAAGGCACTGAAAATGCCTAGATGA";
        System.out.println("Gene: " + findGene(dna, 0));
        
        dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println("Gene: " + findGene(dna, 0));
        
        dna = "TTTCAGTGAGCTTACACATGCAAGTATCCGCGCGCCAGTGAAAATGCCCT";
        System.out.println("Gene: " + findGene(dna, 0));
        
        dna = "TCAAATCATTACTGACCATAAAGGAGCGGGTATCAAGCACACACCTATGT";
        System.out.println("Gene: " + findGene(dna, 0));
        
        dna = "AGCTCACAACACCTTGCTTAGCCACACCCCCACGGGATACAGCAGTGATA";
        System.out.println("Gene: " + findGene(dna, 0));
        
        
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        
        while (true) {
            // find genes
            String gene = findGene(dna, startIndex);
            // check if gene exists
            if (gene.length() == 0) {break;}
            System.out.println(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }
    
    public void testPrintAllGenes() {
                    //012345678901234567890123456789012
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA"; //3
        printAllGenes(dna);

    }
    
    public int countGenes(String dna) {

        // total number of gene
        int totalGenes = 0;
        // start index 
        int start = 0;
        
        // while loop
        while (true) {
            String currGene = findGene(dna, start);
            
            // if no gene exists, break the while loop
            if (currGene.length() == 0) {break;}
            
            totalGenes++;
            start = dna.indexOf(currGene, start) + currGene.length();
        }
        return totalGenes;
    }
    
    public void testCountGenes() {
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));        //2
        printAllGenes("ATGTAAGATGCCCTAGT");
    }
        
                
            
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
