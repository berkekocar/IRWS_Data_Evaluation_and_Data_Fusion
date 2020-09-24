import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class irws_part2 {



 public static void main(String[] args) throws FileNotFoundException {

  Scanner reader = new Scanner(System.in);

  //Gets the name of the input file to be read.
  System.out.println("Please enter the location of the file: ");
  System.out.print("Input file name: ");
  String input = reader.next();
  try {
   //Get the file path and put in a BufferReader.
   File fileWords = new File(input);
   BufferedReader br1;
   br1 = new BufferedReader(new FileReader(fileWords));
  } catch (Exception e) {
   System.out.println(e.getClass());
   main(args);

  }


  System.out.println("Application is Running.....");

  //Create the necessary datastructures.
  // doclist is in type of Document that will get the objects in inner document class.
  //doc1 and doc2 is for comparing the 2 documents.
  //weights will keep the rank of the documents. 
  //output is the result of these document comparasion. 
  ArrayList < Document > doclist = new ArrayList < Document > ();
  ArrayList < String > doc1 = new ArrayList < String > ();
  ArrayList < String > doc2 = new ArrayList < String > ();
  HashMap < String, Double > weights = new HashMap < String, Double > ();
  ArrayList < String > output = new ArrayList < String > ();
  // get the document one and split it.
  doc1.stream().map((line) -> line.split("\t")).forEach((i) -> {
   //Check all document and if there is ";" split it.
   for (String x: i) {
    if (!x.isEmpty()) {
     String[] j = x.split(";");
    }
   }
  });
  //Check all document and if there is ";" split it but there is a tab between documents so, split the tab as well.
  for (String line: doc2) {
   String[] a = line.split("\t");

   for (String x: a) {
    String[] b = x.split(";");

   }
  }
  //Create an array list size of the document. 
  //Search throught the arraylist size, and read the weights of the documents. 
  ArrayList < Integer > document = new ArrayList < > ();
  for (int i = 0; document.size() < 81; i++) {
   for (String j: weights.keySet()) {
    // If the document number is 81 this means it is the last document then stop. 
    // In another case check the lenght of the specific dıcuments and put that in 
    if (doc.size() == 81)
     break;
    else {
     for (Document x: doclist) {
      if (j.equals(x.getEngine()) && !documents.contains(x.getDocNum())) {
       documents.add(x.getDocNum());
       break;
      }
     }
    }
   }
  }
  //Put every file into output array.
  int[] document = null;
  for (int i: document) {
   output.add(documents.get(i - 1).toString());
  }

  //Create a hashmap that will keep the calculated values.
  HashMap < Integer, Double > calculatedValues = new HashMap < Integer, Double > ();
  //Create a arraylist that will keep the documents.
  ArrayList < Document > dl = doclist;
  //Sort the values get min and max walue of weights.
  for (String a: weights.keySet()) {
   double min = 0.0;
   double max = 0.0;
   for (Document x: dl) {
    if (a.equals(x.getEngine())) {
     if (min == 0.0) {

      min = x.getRank();
     }
     if (min > x.getRank()) {
      min = x.getRank();

     }

     if (max < x.getRank()) {
      max = x.getRank();

     }

    }

   }
   //Set the rank of documents.
   double diff = max - min;
   for (Document x: dl) {
    if (a.equals(x.getEngine())) {
     double newRank = (x.getRank() - min) / diff;
     x.setRank(newRank);
    }
   }


  }

  //Put calculated values into Document object. 
  for (Document x: dl) {
   for (String i: weights.keySet()) {
    if (i.equals(x.getEngine())) {
     double value = x.getRank();
     if (calculatedValues.get(x.getDocNum()) != null)
      value = value + calculatedValues.get(x.getDocNum());

    }

   }
  }


  //Print the values. 
  System.out.println("\n combSum of Top 81 documents: ");
  for (String i: weights.keySet()) {
   output.add(i + "\t" + Document.class);
  }
  System.out.println("\n interleaving of Top 81 documents: ");
  for (String i: weights.keySet()) {
   output.add(i + "\t" + Document.class);
  }
  System.out.println("\n LCM of Top 81 documents: ");
  for (String i: weights.keySet()) {
   output.add(i + "\t" + Document.class);
  }

 }
 // Create an object called Document has 3 parameter; 
 //searchEngine,documentNumber,rank. 
 private class Document {
  // Search 
  String searchEngine;
  int documentNumber;
  double rank;

  // Create the getters and setters. 
  public String getEngine() {
   return searchEngine;
  }

  public void setEngine(String engine) {
   this.searchEngine = searchEngine;
  }


  public int getDocNum() {
   return documentNumber;
  }

  public void setDocNum(int docNum) {
   this.documentNumber = documentNumber;
  }

  public double getRank() {
   return rank;
  }

  public void setRank(double rank) {
   this.rank = rank;
  }


 }
}