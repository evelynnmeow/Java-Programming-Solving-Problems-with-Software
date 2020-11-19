
/**
 * Write a description of babyNames here.
 * 
 * @author (Jingjie Ma) 
 * @version (May 27)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

// miniProject
public class babyNames {
    // method that will print the number of girls names, boys names and total names
    public void totalBirth (FileResource fr) {
        // false means there is no header in the CSV file
        CSVParser parser = fr.getCSVParser(false);
        // initialize useful variables
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        // loop through each csv record
        for (CSVRecord rec : parser) {
            // get number of born
            int numBorn = Integer.parseInt(rec.get(2));
            // get gender
            String gender = rec.get(1);
            // add numBorn to totalBirths
            totalBirths += numBorn;
            // check the gender of each record
            // if female
            if (gender.equals("F")) {
                totalGirls += numBorn;
            }
            // else
            else{
                totalBoys += numBorn;
            }
        }
        // print result
        System.out.println("The total number of births = " + totalBirths);
        System.out.println("The total number of girls = " + totalGirls);
        System.out.println("The total number of boys = " + totalBoys);
    }
       
    // test of the totalBirth method
    public void testTotalBirths () {
        FileResource fr = new FileResource();
        totalBirth(fr);
    }
    
    // method that returns the rank of the name in the given year
    public int getRank(int year, String name, String gender) {
        // the format of csv file
        FileResource fr = new FileResource("yob" + year + ".csv");
        int rank = 0;

        for(CSVRecord rec: fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){ rank++; }
            if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                return rank;
            }
        }
        return -1;
            
       
                
            
    }
    
    // test of the getRank method
    public void testGetRank() {
        int rank1 = getRank(1960, "Emily", "F"); 
        System.out.println("Rank for 1960 Emily Female " + rank1);
        int rank2 = getRank(1971, "Frank", "M"); 
        System.out.println("Rank for 1971 Frank Male " + rank2);
          
      
    }
    
    // the method will return the name of the rank at the given year
    public String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource("yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        // initialize the rank of certain record
        int recRank = 0;
        // loop through each record
        for (CSVRecord rec : parser) {
            String recGender = rec.get(1);
            String recName = rec.get(0);
            // for each gender, recRank will add one each time of the loop
            if (recGender.equals(gender)) {
                recRank++;
            }
            // check if the record is desired
            if (recGender.equals(gender) && recRank == rank) {
                return recName;
            }
        }
        return "NO NAME";
            
    }
    
    // test of the getName method
    public void testGetName() {
        String name1 = "";
        name1 = getName(1980, 350, "F");
        System.out.println(name1);
        String name2 = "";
        name2 = getName(1982, 450, "M");
        System.out.println(name2);
        //String name3 = "";
        //name1 = getName(2012, 5, "F");
        //System.out.println("It should be Ava : " + name1);
        
    }
    // the method will return what name would have been named if they were
    // born in a different year
    public void whatIsNameInYear (String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + "born in " + year + " would be " + newName + " if he/she was born in " + newYear);
        
        
      
    
    }
    
    // test of the whatIsNameInYear method
    public void testWhatIsNameInYear() {
        //System.out.println("It should be Sophia : " );
        whatIsNameInYear("Susan", 1972, 2014, "F");
        //System.out.println("It should be Jacob : " );
        whatIsNameInYear("Owen", 1974, 2014, "M");
        
    }
    
    // the method will return the year of the highest rank of the name of the given gender
    // in many years (files)
    public int yearOfHighestRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 0;
        int highestYear = -1;
    // loop through each file in the directory    
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            // get current year of the file
            int currentYear = Integer.parseInt(f.getName().replaceAll("[^\\d]", ""));
            int currentRank = getRank(currentYear, name, gender);
            if (highestRank == 0 && currentRank != -1) {
                highestRank = currentRank;
                highestYear = currentYear;
            }
            
            if (highestRank > currentRank && currentRank != -1) {
                highestRank = currentRank;
                highestYear = currentYear;
            }
        }
        return highestYear;
    }
    
    // test of yearOfHighestRank 
    public void testYearOfHighestRank() {
        System.out.println(yearOfHighestRank("Mich", "M"));
        
    }
    
    // this method will return the average rank of a name with given gender
    // over a range of files
    public double getAverageRank(String name, String gender) {
        double avgRank = 0;
        double fileCount = 0;
        double totalRank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            int currentYear = Integer.parseInt(f.getName().replaceAll("[^\\d]", ""));
            int currentRank = getRank(currentYear, name, gender);
            if (currentRank != -1) {
                totalRank += currentRank;
                fileCount++;
            }
                
            
        }
        avgRank = totalRank / fileCount;
        if (avgRank > 0) {
            return avgRank;
        }
        else {
            return -1.0;
        }
    }
    
    // test of getAverageRank
    public void testGetAverageRank() {
        System.out.println(getAverageRank("Susan", "F"));
        System.out.println(getAverageRank("Robert", "M"));
    }
    
    // the method will return the total number of births of those names with the same gender and same year 
    // who are ranked higher than name
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirths = 0;
        // rank for a specific name
        int rank = 0;
        rank = getRank(year, name, gender);
        FileResource fr = new FileResource ("yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            String currentName = rec.get(0);
            String currentGender = rec.get(1);
            int currentBirth = Integer.parseInt(rec.get(2));
            int currentRank = getRank(year, currentName, currentGender);
            if (currentRank < rank && currentGender.equals(gender)){
                totalBirths += currentBirth;
            }
        }
        return totalBirths;
    }
    
    // test of the getTotalBirthsRankedHigher method
    public void testGetTotalBirthsRankedHigher() {
        System.out.println(getTotalBirthsRankedHigher(1990, "Emily", "F"));
        //System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        
}
        
        
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
