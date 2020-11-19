
/**
 * Write a description of Part3 here.
 * 
 * @author (Jingjie Ma) 
 * @version (May 24)
 */
import edu.duke.*;
import java.lang.Math;

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
    public String findGene(String dna, int startIndex) {
        
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
        
        dna = "GTGAGCTCACTCCATAGACACAAAGGTTTGGTCCTGGCCTTCTTATTAGT";
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
    
    // store all genes in a storageresource object
    public StorageResource getAllGenes(String dna) {
        int start = 0;
        StorageResource geneList = new StorageResource();
        
        while (true) {
            String gene = findGene(dna, start);
            
            if (gene.isEmpty()) {
                break;
            }
            
            geneList.add(gene);
            
            start = dna.indexOf(gene, start) + gene.length();
        }
        
        return geneList;
    }
    
    // test the getAllGenes function
    public void testGetAllGenes() {
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        StorageResource newGenes = getAllGenes(dna);
        for (String i : newGenes.data()) {
            System.out.println(i);
        }
    }
    
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
    
    
    private void processGenes (StorageResource sr) {
        
        // number of genes that are longer than 9 characters
        int moreThanNine = 0;
        
        // number of genes that has C-G ratio higher than 0.35
        int highCG = 0;
        
        // largest gene
        int longGene = 0;
        
 
        for (String gene : sr.data()) {
            // System.out.println(gene);
            int currLen = gene.length();
            float currCG = cgRatio(gene);
                // check the length of the gene
                if (currLen > 9) {
                    System.out.println("Genes with more than 9 chars" + gene);
                    moreThanNine++;
                }
                
                // check the cg ratio
                if (currCG > 0.35) {
                    System.out.println("CG Ratio greater than 0.35" + gene);
                    highCG++;
                }
                
                // check the length
                if (currLen > longGene) {
                    longGene = currLen;
                }
            
        }
        // print out number of genes that has length greater than 9
        System.out.println("The number of genes with length greater than 9 is " + moreThanNine);
        // print out number of genes that has cg ratio greater than 0.35
        System.out.println("The number of genes with CG Ratio greater than 0.35 is " + highCG);
        // print our the longest gene
        System.out.println("The longest gene is has the length of " + longGene);
        
    }
    
    // test processGene method
    public void testProcessGenes() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        
        StorageResource geneList = getAllGenes(dna);
        
        processGenes(geneList);
        
        
    }
        
        
        

}
