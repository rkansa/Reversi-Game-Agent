package helper;

public class Constants {
	public static final int BOARD_HEIGHT=8,BOARD_WIDTH=8;
	
	public static final int NEG_INFINITY=-10000;
	
	public static final int INFINITY=10000;
	
	public static final int [][] COMPETITION_HEURISTIC_EVALUATION={ {129,-8,8,6,6,8,-8,129},
																{-8,-24,-4,-3,-3,-4,-24,-8},
																{8,-4,7,4,4,7,-4,8},  
																{6,-3,4,0,0,4,-3,6},
																{6,-3,4,0,0,4,-3,6},
																{8,-4,7,4,4,7,-4,8},
																{-8,-24,-4,-3,-3,-4,-24,-8},
																{129,-8,8,6,6,8,-8,129},
																};
	
	
	
	public static final int[][] DIRECTIONS = {{-1, -1}, {0, -1}, {1, -1},
			 {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};

	
}
