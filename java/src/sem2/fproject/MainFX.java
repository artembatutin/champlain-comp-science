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

/**
 * Graphical {@link LinkedList} test controller.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public class MainFX extends Application {
	
	/**
	 * The view list.
	 */
	@FXML
	private ListView<String> view;
	
	/**
	 * The tested linked list.
	 */
	private LinkedList<String> list = new LinkedList<>();
	
	/**
	 * JavaFX {@link Application} start class.
	 * @param primaryStage starting stage.
	 * @throws Exception any errors.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
		Parent root = loader.load();
		primaryStage.setTitle("LinkedList FX - by Artem Batutin");
		primaryStage.setScene(new Scene(root, 300, 375));
		primaryStage.show();
	}
	
	/**
	 * Main launcher.
	 * @param args no arguments used.
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Updates the view list with the {@link LinkedList}.
	 * @param list list to gather data.
	 */
	private void updateList(LinkedList list) {
		view.getItems().clear();
		list.updateView(view.getItems());
	}
	
	/**
	 * Add file button action.
	 */
	@FXML
	void addAction() {
		try {
			Main.add(list);
			updateList(list);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Exclude file button action.
	 */
	@FXML
	public void excludeAction() {
		try {
			Main.exclude(list);
			updateList(list);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sort button action.
	 */
	@FXML
	public void sortAction() {
		Main.sort(list);
		updateList(list);
	}
}
