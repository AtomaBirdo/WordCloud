import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

//*********************************************************
// WordDisplay
// James Wang
//
// The display class that used the javafx to display the word cloud
// it randomize the color and position for each word and set the font
// size according to the frequency of the word.
//
// Version: 1.1
// 2019/2/21
//*********************************************************

public class WordDisplay extends Application {

    Text text;
    Group g;
    int width = 800;
    int height = 700;
    double average;

    public void start(Stage ps) throws IOException{

        Object[] ws = WordReader.chooseAndLoad(ps); //Select and load the file

        //Separate the array and frequency from Object ws
        ArrayList<String> arr = (ArrayList<String>)ws[0];
        ArrayList<Integer> num = (ArrayList<Integer>)ws[1];

        average = (double)(num.get(num.size() - 1) - num.get(0)) / 160.0; //Calculate the average font size

        //System.out.println(arr);
        //System.out.println(num);
        //System.out.println(average);

        g = new Group();

        for (int i = 0; i < arr.size(); i++) {
            text = new Text((int)(Math.random() * (width - 100)) + 25, (int)(Math.random() * (height - 100)) + 25, arr.get(i)); //Randomize the location for the text
            text.setFont(new Font(num.get(i) / average + 10)); //Set the size according to the frequency and average size
            randomColor(text); //Randomize a color for the text
            g.getChildren().add(text);
        }

        Scene scene = new Scene(g, width, height, Color.SKYBLUE);
        ps.setScene(scene);
        ps.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void randomColor(Text o){
        o.setFill(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));
    }

}
