import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//*********************************************************
// WordReader
// James Wang
//
// The helper class of WordDisplay that read the file, split
// them into ArrayList and calculate times they appear in the
// text file, this class also removed the punctuation of the word
// and turn them into lowercase. It sort the word based on their
// frequency using bubble sort
//
// Version: 1.2
// 2019/2/21
//*********************************************************

public class WordReader {

    static int globalIndex = 0;
    
    static ArrayList<String> arr = new ArrayList<String>();
    static ArrayList<Integer> num = new ArrayList<Integer>();

    public static Object[] chooseAndLoad(Stage ps) throws IOException {
        
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir"))); //Set the initial directory to users current folder
        File f = fc.showOpenDialog(ps); //Show the file selecting dialog
        //System.out.println(System.getProperty("user.dir"));
        Scanner scan = new Scanner(f);
        String word;

        while (scan.hasNext()){
            word = scan.next(); //Scan the next word
            word = changeWord(word); //change the word
            if (word.equals("the") || word.equals("and") || word.equals("is") || word.equals("a") || word.equals("are") || word.equals("of") || word.equals("to") || word.equals("in") || word.equals("as")) continue; //Remove the articles
            //System.out.println(word);
            if (contains(arr, word)) {
                num.set(globalIndex, num.get(globalIndex) + 1); //Add the frequency of this word by one
                //System.out.println("arr contains " + word + " already");
            }else{
                //System.out.println("arr does not contain " + word);
                arr.add(word); //Add this word into the ArrayList
                num.add(1); //Add its frequency into another ArratList
            }
        }
        bubbleSort(); //Sort the ArrayLists according to the frequency from small to big
        Object[] group = {arr, num};
        return group;
    }

    public static boolean contains(ArrayList<String> a, String w){

        for (int i = 0; i < a.size(); i++)
            if (a.get(i).equals(w)){ //If contains the word
                globalIndex = i; //Set the index for the frequency ArrayList
                return true;
            }
        return false;
    }

    public static void bubbleSort() { //This method sort both ArrayLists according to the frequency from small to big
        for (int i = 0; i < num.size() - 1; i++)
            for (int j = 0; j < num.size() - i - 1; j++)
                if (num.get(j) > num.get(j + 1)){
                    //swap temp and frequency
                    int temp = num.get(j);
                    num.set(j, num.get(j + 1));
                    num.set(j + 1, temp);
                    //swap word and original word
                    String word = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, word);
                }
    }

    public static int findLocation(ArrayList<String> a, String w){

        for (int i = 0; i < a.size(); i++)
            if (a.get(i).compareTo(w) >= 0){
                return i; //return the index of the
            }
        return a.size();
    }

    public static String changeWord(String w){ //This method remove the punctuation and turn the word into lowercase.
        w = w.toLowerCase();
        if (w.charAt(w.length() - 1) == '.' || w.charAt(w.length() - 1) == ',' || w.charAt(w.length() - 1) == '"' || w.charAt(w.length() - 1) == ';' || w.charAt(w.length() - 1) == '\'' || w.charAt(w.length() - 1) == ':' || w.charAt(w.length() - 1) == ')' || w.charAt(w.length() - 1) == '}' || w.charAt(w.length() - 1) == ']' ){
            w = w.substring(0, w.length() - 1);
        }
        if (w.charAt(0) == '"' || w.charAt(0) == '\'' || w.charAt(0) == '(' || w.charAt(0) == '[' || w.charAt(0) == '{'){
            w = w.substring(1);
        }
        return w;
    }

}