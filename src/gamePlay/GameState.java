package gamePlay;
public class GameState {
	
	String[][] currentGameState=new String[8][8];	
	String currentPlayer=new String();
	int evaluationValue;
	int alphaValue;
	int betaValue;
	
	public int getAlphaValue() {
		return alphaValue;
	}
	public void setAlphaValue(int alphaValue) {
		this.alphaValue = alphaValue;
	}
	public int getBetaValue() {
		return betaValue;
	}
	public void setBetaValue(int betaValue) {
		this.betaValue = betaValue;
	}
	public int getEvaluationValue() {
		return evaluationValue;
	}
	public void setEvaluationValue(int evaluationValue) {
		this.evaluationValue = evaluationValue;
	}
	FieldPosition lastMovePlayed;
	
	public FieldPosition getLastMovePlayed() {
		return lastMovePlayed;
	}
	public void setLastMovePlayed(FieldPosition lastMovePlayed) {
		this.lastMovePlayed = lastMovePlayed;
	}
	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public String[][] getCurrentGameState() {
		return currentGameState;
	}

	public void setCurrentGameState(String[][] currentGameState) {
		this.currentGameState = currentGameState;
	}
	
	
}
