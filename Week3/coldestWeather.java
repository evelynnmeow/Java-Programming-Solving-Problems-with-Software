
/**
 * Write a description of coldestWeather here.
 * 
 * @author (Jingjie Ma) 
 * @version (May 26)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
// part 2 of week 3: finding the coldest day
public class coldestWeather {
    //coldestHourInFile takes one parameter, a CSV parser.
    // it will return the CSVRecord with the coldest temperature in the file
    public CSVRecord coldestHourInFile (CSVParser parser) {
        // initialize the coldestSoFar
        CSVRecord coldestSoFar = null;
        // for loop to loop through the file
        for (CSVRecord currentRow : parser) {
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
            }
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if (currentTemp < lowestTemp && currentTemp != -9999) {
                coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;
                
    }
    
    // test of the coldestHourInFile method
    public void testColdestHourInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("The coldest temperature in this file is " + coldest.get("TemperatureF") + " and it occurs at " + coldest.get("DateUTC"));
    }
    
    // this method will find and return the name of the file with the lowest temperature
    // from multiple csv files
    public String fileWithColdestTemperature() {
        // construct new directory resource
        DirectoryResource dr = new DirectoryResource();
        // construct null CSVRecord of the coldest hour
        CSVRecord lowestSoFar = null;
        // file name to store and return
        File coldestFile = null;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = coldestHourInFile(parser);
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
            
            if (currentTemp < lowestTemp) {
                lowestSoFar = currentRow;
                coldestFile = f;
            }
        }
        return coldestFile.getAbsolutePath();
    }
    
    // test of the fileWithColdestTemperature
    public void testFileWithColdestTemperature() {
        String fileWithColdestTemp = fileWithColdestTemperature();
        File f = new File(fileWithColdestTemp);
        FileResource fr = new FileResource(f);
        System.out.println("Coldest day was in file " + f.getName());

        System.out.println("Coldest Temperature is: " + coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));

        System.out.println("All the Temperatures on the coldest day were:");
        for(CSVRecord data: fr.getCSVParser()){
            System.out.println(data.get("DateUTC") + ": " + data.get("TemperatureF"));
        }
    }
    
    // this method takes one CSVParser parameter and returns the CSVRecord
    // with the lowest humidity
    public CSVRecord lowestHumidityInFile (CSVParser parser) {
        // initialize the csv record with the lowest humidity
        CSVRecord lowestSoFar = null;
        // loop through the csv file
        for (CSVRecord currentRow : parser) {
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            // parse string to double for temperature
            double currentHumid = Double.parseDouble(currentRow.get("Humidity"));
            double lowestHumid = Double.parseDouble(lowestSoFar.get("Humidity"));
            if (currentHumid < lowestHumid) {
                lowestSoFar = currentRow;
            }
        }
        
        return lowestSoFar;
        
    }
    
    // test of the lowestHumidityInFile method
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumid = lowestHumidityInFile(parser);
        System.out.println("The coldest temperature in this file is " + lowestHumid.get("Humidity") + " and it occurs at " + lowestHumid.get("DateUTC"));
    }
    
    // find and returns the CSVRecord with the lowest humidity of many files
    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;

        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);

            if(lowestSoFar == null){
                lowestSoFar = lowestHumidityInFile(fr.getCSVParser());
            }
            else{
                double currentLowest = Double.parseDouble(lowestHumidityInFile(fr.getCSVParser()).get("Humidity"));
                if(currentLowest < Double.parseDouble(lowestSoFar.get("Humidity"))){
                    lowestSoFar = lowestHumidityInFile(fr.getCSVParser());
                }
            }
        }
        return lowestSoFar; 
            
   }
        
       
    
    
   public void testLowestHumidityInManyFiles(){
        CSVRecord lowestHumid = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHumid.get("Humidity") + " at " + lowestHumid.get("DateUTC"));
        
   }
   
   // returns the average temp in the file
   public double averageTemperatureInFile(CSVParser parser) {
       // the sum of all temps in the file
       double totalTemp = 0.0;
       // the number of records in the file
       double numRecords = 0.0;
       // average temp
       double avgTemp = 0.0;
       for (CSVRecord row : parser) {
           double currentTemp = Double.parseDouble(row.get("TemperatureF"));
           totalTemp = totalTemp + currentTemp;
           numRecords++;
       }
        
       avgTemp = totalTemp / numRecords;
       return avgTemp;
   }
   
   // test of averageTemperatureInFile method 
   public void testAverageTemperatureInFile() {
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       double result = averageTemperatureInFile(parser);
       System.out.println("Average temperature in file is " + result);
   }
   
   // get avg temp with humidity greater than or equal to certain value
   public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
       double avgTemp = 0.0;
       double totalTemp = 0.0;
       double numRecords = 0.0;
       for (CSVRecord row : parser) {
       int humidity = Integer.parseInt(row.get("Humidity"));
       if (humidity >= value) {
           double currentTemp = Double.parseDouble(row.get("TemperatureF"));
           totalTemp = totalTemp + currentTemp;
           numRecords++;
       }
    }
    avgTemp = totalTemp / numRecords;
    return avgTemp;
   
   }
   
   public void testAverageTemperatureWithHighHumidityInFile() {
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       double result = averageTemperatureWithHighHumidityInFile(parser, 80);
       if(!Double.isNaN(result)) {
           System.out.println("average temperature is " + result);
        } 
	else {
	    System.out.println("No Temperature was found");
	   }
}
       
    

        
        
       
        
            
            
        
      
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
