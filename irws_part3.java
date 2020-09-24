
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class irws_part3 {

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
        // Read the documents in the file. 
        String line;
        //Create a hasmap for storing these documents. 
        HashMap<String, Integer> doc = new HashMap<>();
        //Read line by line till the end of the doc and split it if ";" find.
        while ((line = br1.readLine()) != null) {
            String engineData[] = line.split(";");
            char[] seq_char = engineData[2].toCharArray();
            String engineId = engineData[1];
           //Read the characters and find Relative docs and count the length. 
           for (int i = 0; i < seq_char.length; i++) {
                if(seq_char[i] == 'R'){
                    if(doc.containsKey(engineId)){
                        doc.put(engineId, doc.get(engineId)+1);
                    }
                    else{
                        doc.put(engineId,1);
                    }                    
                }
            }            
        }
        //bufer close
        br1.close();
        //second part
        
        System.out.println("Second part...");
                  //Get the second part and put in a BufferReader.

        BufferedReader br2 = new BufferedReader(new FileReader(fileWords));
        
        int [] result = new int[20];
        //Insert these results in a arraylist.
        //First array list keeps the sequences. 
        //Second arraylist is a flag. 
        //Third one is indx of end of sequence. 
        while((line = br2.readLine()) != null){
            ArrayList<Integer> list_seq = new ArrayList<Integer>();
            ArrayList<Integer> list_endseq = new ArrayList<Integer>();
            ArrayList<Integer> end_seq = new ArrayList<Integer>();
            //Split the seperator. 
            String engineData[] = line.split(";");
            char[] seq_char = engineData[2].toCharArray();
            list_seq.add(seq_char.length);
            Iterator< HashMap.Entry<String, Integer>> ed = doc.entrySet().iterator();
            
            //Filling arraylists
            while (ed.hasNext()) {
                HashMap.Entry edPair = ed.next();
                Integer edValue = doc.get(edPair.getKey());
                list_endseq.add(edValue);
                end_seq.add(edValue);
            }
            //
            //Print these values and indexes.
            for (int i = 0; i < 20 ;i++) {
                int max_value = Collections.max(list_endseq);
                
                if(max_value == 0){
                    result[i] = result[i]+end_seq.get(i);
                    end_seq.remove(i);
                }
                else{
                    //This part is responsible of output values.
                    int max_index = list_endseq.indexOf(max_value);
                    result[i] = result[i]+list_endseq.get(max_index);
                    list_endseq.set(max_index, 0);
                    
                    if(end_seq.size() == 1){
                        break;
                    }
                    end_seq.remove(max_index);
                }
            }

                //Write the output in a text file. 
                 File TwlFile = new File(output);
                 FileWriter fr = new FileWriter(TwlFile, true);
                 BufferedWriter bw = new BufferedWriter(fr);

            
           //Results...
            for (int i = 0; i < result.length; i++) {
                bw.write(result[i]+"\n ");
                System.out.print(result[i]+" ");
            }
            //Buffer writer close.
            bw.close();
            
        }
        //Buffer reader close.
        br2.close();
        
        
    }
    
}
