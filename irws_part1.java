import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;



public class irws_part1 {

   
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //Use scanner to get input from user. 
        Scanner reader = new Scanner(System.in);

      //Get the input filepath.
      System.out.print("Enter the input file path: ");
      String input = reader.next();
      //Get the output file path.
      System.out.print("Enter the output file path:  ");
      String output = reader.next();

        try {
          //Get the file path and put in a BufferReader.
             File fileWords = new File(input);
              BufferedReader br1;
             br1 = new BufferedReader(new FileReader(fileWords));
       //If there is an error catch the error and repeat it.        
     } catch (Exception e) {
               System.out.println(e.getClass());
               System.out.println("File path is wrong!");
               System.out.println("********************");
                main(args);

     }


      //Insert the path into the buffer. 
        File fileWords = new File(input);
        BufferedReader br1 = new BufferedReader(new FileReader(fileWords));


        //Create the hashmaps for storing operations.
        //Create the necessary datastructures.
     
        HashMap<String, Double> enginedata = new HashMap<>();
        HashMap<String, Double> engineofR = new HashMap<>();
        HashMap<String, Double> engineofPk = new HashMap<>();
        HashMap<String, Double> engineofPr = new HashMap<>();
        
        //Key Length.
        HashMap<String, Integer> engineId_length = new HashMap<>();
        
        double x;
        String line;
        //Get the document and read it.
        while ((line = br1.readLine()) != null) {
            //When ";" has seen split it. 
            String engineData[] = line.split(";");
            // class Run and a hashmap representing the engines and their MAP's with a
             // default precision value of 0.
            char[] seq_char = engineData[2].toCharArray();
            String engineId = engineData[1];
            int counter = 0;
            double precisionOfK = 0;
            for (int i = 0; i < seq_char.length; i++) {
                if(seq_char[i] == 'R'){
                    counter++;
                    if(i < 5){
                        precisionOfK++;
                    }
                }
            }
            
            int counted = Integer.parseInt(engineData[3]);
            //Represents the length of sequence.
            x = 40 - seq_char.length - (counted-counter);
            double precision = counter/counted;
            double recall = counter/(counter + x);
            
            int j = 0;
            //2 ->  len(seq)+1
            for (int i = 0; i < seq_char.length; i++) {
               char [] second_seq_char = engineData[2].substring(0, i).toCharArray();
               int second_seq_counter = 0;
               //counting R in second_seq
               for (int k = 0; k < second_seq_char.length; k++) {
                    if(second_seq_char[k] == 'R'){
                        second_seq_counter ++;
                    }
                }
               //Calculate the recall and precision. 
               x = 40 - (second_seq_char.length) - (counted - second_seq_counter);
               //Initialize the precision @ value
               double recall1 = second_seq_counter/(second_seq_counter + x);
               double precisionOfR = 0 ;
              //Check the recall.
               if(recall1 == 0.5){
                   j = i;
               }
               if(j != 0){
                    char [] first_seq = engineData[2].substring(0,j).toCharArray();
                    precisionOfR = second_seq_counter / j;
               }
               if(enginedatas.containsKey(engineId)){
                   double prev_value = enginedatas.get(engineId);
                   enginedatas.put(engineId, prev_value + precision);
                   engineofR.put(engineId, engineofR.get(engineId) + recall);
                   engineofPk.put(engineId, engineofPk.get(engineId) + precisionOfK);
                   engineofPr.put(engineId, engineofPr.get(engineId)+precisionOfR);
               }
            
               //Print the precision and recall values. 
                System.out.println("Key " + engineId + " precision " + precision + " precision at r= 0.5 : " + precisionOfR+ " precision at 5: "
                        + precisionOfK + " Recall " + recall);
                //Write the outputs in a text file. 
                File tfl = new File(output);
                BufferedWriter bw = new BufferedWriter(new FileWriter(tfl));
                
                Iterator< HashMap.Entry<String, Double>> ed = enginedatas.entrySet().iterator();
                while (ed.hasNext()) {
                    HashMap.Entry edPair = ed.next();
                    Double edValue = enginedatas.get(edPair.getKey());
                    bw.write(edPair.getKey() + " : " + edValue);
                    bw.newLine();
                }
                //Print them in a text file. 
                bw.write(" Precision " + precision);
                bw.write(" Precision @ 5: " + precisionOfK);
                bw.write(" Precision @ r= 0.5 : " + precisionOfR);
                bw.write(" Pecall " + recall);
                bw.write("\n");
                bw.close();               
            }
            
        }
        br1.close();
       
    }
    
}
