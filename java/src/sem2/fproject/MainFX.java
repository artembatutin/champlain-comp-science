package sem2.fproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class MainFX extends Application {

    @FXML
    private ListView<String> view;

    private LinkedList<String> list = new LinkedList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("LinkedList FX - by Artem Batutin");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void updateList(LinkedList list) {
        view.getItems().clear();
        list.updateView(view.getItems());
    }

    @FXML
    void addAction(ActionEvent event) {
        try {
            Main.add(list);
            updateList(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void excludeAction(ActionEvent actionEvent) {
        try {
            Main.exclude(list);
            updateList(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sortAction(ActionEvent actionEvent) {
        Main.sort(list);
        updateList(list);
    }

    public void manAddAction(ActionEvent actionEvent) {
    }

    public void manRemoveAction(ActionEvent actionEvent) {
    }
}
