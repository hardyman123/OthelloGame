package Othello;

import javafx.geometry.Insets;

public class Constants{
	
	public static final int SQUARE_SIZE = 50;
	public static final int CIRCLE_RADIUS = 17;
	public static final int CIRCLE_OFFSET = SQUARE_SIZE/2;
	public static final int APP_WIDTH = 800;
	public static final int APP_HEIGHT = 500;
	public static final int PANEL_WIDTH = 300;
	public static final int PANEL_PADDING = 30;
	public static final Insets INSTRUCTIONS_PADDING = new Insets(0,0,70,0);
	public static final Insets PLAYER_BOX_PADDING = new Insets(0,10,5,10);
	public static final int SETTINGS_PADDING = 5;
	
	
	public static final int[][] VALUES_ARRAY = {	{0,0,0,0,0,0,0,0,0,0},
													{0,200,-70,30,25,25,30,-70,200,0},
													{0,-70,-100,-10,-10,-10,-10,-100,-70,0},
													{0,30,-10,2,2,2,2,-10,30,0},
													{0,25,-10,2,2,2,2,-10,25,0},
													{0,25,-10,2,2,2,2,-10,25,0},
													{0,30,-10,2,2,2,2,-10,30,0},
													{0,-70,-100,-10,-10,-10,-10,-100,-70,0},
													{0,200,-70,30,25,25,30,-70,200,0},
													{0,0,0,0,0,0,0,0,0,0}
												};
}