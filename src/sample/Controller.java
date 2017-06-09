package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Comparator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Controller {

    @FXML
    private Rectangle bigFunnySquare;

    @FXML
    private ListView resultsList;

    @FXML
    private Button reactionBtn;

    private ObservableList<String> results;
    private SortedList<String> sortedResults;

    private long timeStamp;

    public void initialize(){
        results = FXCollections.observableArrayList();
        sortedResults = new SortedList<String>(results);

        sortedResults.setComparator(Comparator.comparingInt(o -> Integer.parseInt(o.substring(0, o.length() - 2))));

        resultsList.setItems(sortedResults);

        resultsList.setStyle("fx-alignment: center");

        changeSquareColor();
    }

    public void reactionBtnReleased(MouseEvent mouseEvent) {

        long reactionTime = System.currentTimeMillis() - timeStamp;
        results.add(Long.toString(reactionTime) + "ms");

        reset();

    }

    public void changeSquareColor(){

        Random rand = new Random();

        int delay = rand.nextInt(8000) + 2000;

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                reactionBtn.setDisable(false);
                bigFunnySquare.setFill(Paint.valueOf("RED"));
                timeStamp = System.currentTimeMillis();
            }
        }, delay);
    }

    public void reset(){
        reactionBtn.setDisable(true);
        bigFunnySquare.setFill(Paint.valueOf("GREEN"));
        changeSquareColor();
    }
}
