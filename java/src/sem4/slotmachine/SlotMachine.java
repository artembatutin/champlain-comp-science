package sem4.slotmachine;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class SlotMachine extends Application {
	private static final int EMBLEMS = 8;
	private static final int VIEWS = 3;

	private final Image[] images = new Image[EMBLEMS];
	private final ImageView[] views = new ImageView[VIEWS];

	private boolean spinning = false;

	@Override
	public void start(Stage stage) throws Exception {
		for(int i = 0; i < EMBLEMS; i++) {
			images[i] = new Image(getClass().getResource("emblem-"+i+".png").toString());
		}
		for(int i = 0; i < VIEWS; i++) {
			views[i] = new ImageView(images[0]);
		}
		VBox vBox = new VBox();
		HBox hBox = new HBox(views);
		vBox.getChildren().add(hBox);
		vBox.setAlignment(Pos.CENTER);

		Random r = new Random();
		Button b = new Button("Spin");
		b.setOnMouseClicked(e -> {
			if(spinning)
				return;
			spinning = true;
			boolean[] slots = new boolean[VIEWS];
			for(int i = 0; i < slots.length; i++) {
				if(!slots[i]) {
					int ee = r.nextInt(EMBLEMS);
					views[i].setImage(images[ee]);
					if(r.nextInt(10) == 2) {
						slots[i] = true;
					}
				}
			}
			spinning = false;
		});
		vBox.getChildren().add(b);

		Scene scene = new Scene(vBox);
		stage.setScene(scene);
		stage.show();
	}

}
