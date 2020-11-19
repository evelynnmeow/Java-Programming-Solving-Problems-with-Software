
/**
 * Write a description of csvTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class csvTest {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // test of countryInfo Method
        //System.out.println(countryInfo(parser, "Nauru"));
        //parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Germany"));
        //parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Peru"));
        
        // test of numberOfExports method
        //numberOfExports(parser, "cocoa");
        
        // test of listExportersTwoProducts
        //listExportersTwoProducts(parser, "cotton", "flowers");
        
        // test of bigExporters
        bigExporters(parser, "$999,999,999,999");
        
    }
    
    public String countryInfo (CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)) {
                String exports = record.get("Exports");
                String values = record.get("Value (dollars)");
                String info = country + ": " + exports + ": " + values;
                return info;
            }
            
        }
        
        return "Not Found";
    }
    
    public void numberOfExports(CSVParser parser, String exportItem) {
        // number of countries export the item
            int count = 0;
        for (CSVRecord record : parser) {
            
            if (record.get("Exports").contains(exportItem)) {
                //System.out.println(record.get("Country"));
                count++;
            }
        }
        System.out.println("The number of countries export " + exportItem + "is " + count);
    }
    
    // the method takes three parameters, one csv parser and two export items
    // it will return the country that exports both two items
    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    // the method takes two parameters, a parser and an amount in string format
    // it will print out the name and value of a country that has export value
    // greater than the amount
    public void bigExporters (CSVParser parser, String amount) {
        String newAmount = amount.replaceAll("[$,]", "");
        for (CSVRecord record : parser){
            String currValue = record.get("Value (dollars)").replaceAll("[$,]", "");
            if(Float.parseFloat(currValue) > Float.parseFloat(newAmount)){
                System.out.println(record.get("Country") + ": " + record.get("Value (dollars)"));
            }
        }
    }
            
            
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
                
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
