package sem4.tictac;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TicTac extends Application {

	private static final int SIZE = 3;
	private static final int XCROSSED = 1;
	private static final int OCROSSED = 2;

	private boolean xTurn;
	private int played[][];

	private GridPane grid;

	@Override
	public void start(Stage stage) throws Exception {
		Restart();
		Scene scene = new Scene(grid);
		stage.setScene(scene);
		stage.show();
	}

	private void Restart() {
		if(grid == null) {
			grid = new GridPane();
		} else {
			grid.getChildren().clear();
		}
		played = new int[SIZE][SIZE];
		for(int x = 0; x < SIZE; x++) {
			for(int y = 0; y < SIZE; y++) {
				ImageView v = new ImageView(CrossType.NONE.getImage());
				v.setFitWidth(100);
				v.setFitHeight(100);
				int finalX = x;
				int finalY = y;
				v.setOnMouseClicked(e -> {
					if(played[finalX][finalY] > 0)
						return;
					if(xTurn) {
						v.setImage(CrossType.X.getImage());
					} else {
						v.setImage(CrossType.O.getImage());
					}
					played[finalX][finalY] = xTurn ? XCROSSED : OCROSSED;
					xTurn = !xTurn;
					check();
				});
				grid.add(v, x, y);
			}
		}
	}

	public void check() {
		//check vertical lines
		for(int x = 0; x < SIZE; x++) {
			int play = played[x][0];
			boolean lined = true;
			for(int y = 0; y < SIZE; y++) {
				if(play == 0) {
					lined = false;
					break;
				}
				if(played[x][y] != play) {
					lined = false;
					break;
				}
			}
			if(lined) {
				finished(play);
				return;
			}
		}
		//check horizontal lines
		for(int y = 0; y < SIZE; y++) {
			int play = played[0][y];
			boolean lined = true;
			for(int x = 0; x < SIZE; x++) {
				if(play == 0) {
					lined = false;
					break;
				}
				if(played[x][y] != play) {
					lined = false;
					break;
				}
			}
			if(lined) {
				finished(play);
				return;
			}
		}
		//vertical check
		int topLeft = played[0][0];
		int topRight = played[2][0];
		int middle = played[1][1];
		int bottomLeft = played[0][2];
		int bottomRight = played[2][2];
		if(middle != 0) {
			if(topLeft == middle && middle == bottomRight) {
				finished(topLeft);
			} else if(topRight == middle && middle == bottomLeft) {
				finished(topRight);
			}
		}
	}

	public void finished(int play) {
		String win = (play == XCROSSED ? "X's" : "O's") + " team won!";
		Label l = new Label(win);
		l.setTextFill(Color.web("#0076a3"));
		grid.add(l, 1, 1);
		/*for(int s = 1; s < 6; s++) {
			l.setText(win + " - restarting in " + s);
			try {
				Thread.sleep(1000l);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}*/
	}
}
