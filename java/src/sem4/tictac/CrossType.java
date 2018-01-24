package sem4.tictac;

import javafx.scene.image.Image;

enum CrossType {
	X("tac-x.png"),
	O("tic-o.png"),
	NONE("toe.png");

	private final String img;

	CrossType(String img) {
		this.img = img;
	}

	Image getImage() {
		return new Image(getClass().getResource(img).toString());
	}
}
