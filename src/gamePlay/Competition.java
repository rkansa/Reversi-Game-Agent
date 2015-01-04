package gamePlay;
import helper.Constants;
import helper.Utility;
import java.util.ArrayList;
import java.util.List;

public class Competition {
	GameState gs;
	float cpuTimeLeft;
	int maxDepth;
	String myPlayer;
	String opponentPlayer;
	int width;
	int height;
	Utility helper;
	
	public Competition(GameState gs, float cpuTimeLeft) {
	super();
		this.gs = gs;
		this.maxDepth=4;
		this.cpuTimeLeft = cpuTimeLeft;
		this.width = Constants.BOARD_WIDTH;
		this.height = Constants.BOARD_HEIGHT;
		this.helper=new Utility();
		this.myPlayer = gs.getCurrentPlayer();
		if(this.myPlayer.endsWith("X"))
			this.opponentPlayer="O";
		else if(this.myPlayer.endsWith("O"))
			this.opponentPlayer="X";
		if(this.cpuTimeLeft==200 || (this.cpuTimeLeft>=80 && this.cpuTimeLeft<165) || this.cpuTimeLeft<15 )
		this.maxDepth=4;                       
		if((this.cpuTimeLeft>=185 && this.cpuTimeLeft<=200)|| this.cpuTimeLeft>=15 && this.cpuTimeLeft<40)
			this.maxDepth=7;
		if(this.cpuTimeLeft>=165 && this.cpuTimeLeft<185)
			this.maxDepth=5;
		if(this.cpuTimeLeft>=40 && this.cpuTimeLeft<80)
			this.maxDepth=6;		
	}

	public String  play()
	{
		int depth=0;
		FieldPosition playMove=new FieldPosition(-1, -1);
		Integer finalValue=new Integer(0);
		Integer alpha=Constants.NEG_INFINITY;
		Integer beta=Constants.INFINITY;
		finalValue=maxValue(gs,depth,playMove,finalValue,alpha,beta);
		String move=helper.giveProperNodeValue(playMove);
		if(move.equals("root"))
			move="pass";
		System.out.println("Move to Play "+move);
		return move;
	}
	public int maxValue(GameState gs,int depth,FieldPosition moveToMakeAfterMinMax,Integer finalValue,Integer alpha,Integer beta)
	{
		if(depth==maxDepth)
		return findEvaluationValue(gs);
		
			List<FieldPosition> allLegitMoves=allPossibleMoves(gs,myPlayer);
			int maxScore=Constants.NEG_INFINITY;
			int bestMove=-1;
			for(int i=0;i<allLegitMoves.size();i++)
			{
				GameState newState=playMove(gs,allLegitMoves.get(i),myPlayer);
				Integer score=new Integer(0);
				score=minValue(newState,depth+1,moveToMakeAfterMinMax,score,alpha,beta);
				if (score > maxScore) 
				{
		            maxScore = score;
		            bestMove = i;
				}
			finalValue=maxScore;
			if(maxScore>=beta)
			{
				moveToMakeAfterMinMax.setRowCoord(allLegitMoves.get(bestMove).rowCoord);
				moveToMakeAfterMinMax.setColCoord(allLegitMoves.get(bestMove).colCoord);
				return finalValue;
			}
			alpha=max(alpha,maxScore);
			moveToMakeAfterMinMax.setRowCoord(allLegitMoves.get(bestMove).rowCoord);
			moveToMakeAfterMinMax.setColCoord(allLegitMoves.get(bestMove).colCoord);
				
			}
		
		return finalValue;
	}
	public int minValue(GameState gs,int depth,FieldPosition moveToMakeAfterMinMax,Integer finalValue,Integer alpha,Integer beta)
	{
		if(depth==maxDepth)
		return findEvaluationValue(gs);
		
		List<FieldPosition> allLegitMoves=allPossibleMoves(gs,opponentPlayer);
		int minScore=Constants.INFINITY;
		int bestMove=-1;
		for(int i=0;i<allLegitMoves.size();i++)
		{
			GameState newState=playMove(gs,allLegitMoves.get(i),opponentPlayer);
			Integer score=new Integer(0);
			score=maxValue(newState,depth+1,moveToMakeAfterMinMax,score,alpha,beta);
			if (score < minScore) 
			{
	            minScore = score;
	            bestMove = i;
			}
			finalValue=minScore;
			if(minScore<=alpha)
			{
				moveToMakeAfterMinMax.setRowCoord(allLegitMoves.get(bestMove).rowCoord);
				moveToMakeAfterMinMax.setColCoord(allLegitMoves.get(bestMove).colCoord);
				return finalValue;
			}
			beta=min(beta,minScore);
			moveToMakeAfterMinMax.setRowCoord(allLegitMoves.get(bestMove).rowCoord);
			moveToMakeAfterMinMax.setColCoord(allLegitMoves.get(bestMove).colCoord);
			
		}	
	return finalValue;
	}
	public int min(int a,int b)
	{
		
		if(a<b)
			return a;
		else return b;
					
	}
	public int max(int a,int b)
	{
		
		if(a>b)
			return a;
		else return b;
					
	}
	

	public List<FieldPosition> allPossibleMoves(GameState gs,String currentPlayer)
	{
		List<FieldPosition> possibleMoves=new ArrayList<FieldPosition>();
		
		for(int x=0;x<height;x++)
		{
			for(int y=0;y<width;y++)
			{
				FieldPosition fPosition=new FieldPosition(x, y);
				if(allocatedPositionValid(gs,fPosition) && countFlips(gs,fPosition,currentPlayer))
				{
				possibleMoves.add(fPosition);
					
				}
			}	
		}
		
		return possibleMoves;
	}
	public boolean allocatedPositionValid(GameState gs,FieldPosition fPosition)
	{
		boolean allocatedPosition=false;
		if (!helper.isPlayerOnboard(fPosition)) 
			{
			 return false;
			 }
		
		for(int [] direction:Constants.DIRECTIONS)
		{
			FieldPosition temporaryPosition = new FieldPosition(fPosition.rowCoord + direction[0],
					 fPosition.colCoord + direction[1]);
			if (adjacentPositionOccupied(gs,temporaryPosition)) {
				allocatedPosition = true;
				 }
					
		}
		if(!helper.playeratPosition(fPosition.rowCoord, fPosition.colCoord, gs.getCurrentGameState()).equals("*"))
			allocatedPosition=false;
		
		return allocatedPosition;
		
	}
	public boolean adjacentPositionOccupied(GameState gs,FieldPosition temporaryPosition)
	{
		boolean positionOccupied=false;
		if(helper.isPlayerOnboard(temporaryPosition)&& 
				!helper.playerWithPosition(temporaryPosition,gs.getCurrentGameState()).equals("*"))
		{
			positionOccupied=true;
		}
		return positionOccupied;
	}
	public boolean countFlips(GameState gs,FieldPosition fPosition,String currentPlayer)
	{
		int flips=0;
		for(int direction[]:Constants.DIRECTIONS)
		{
			flips+=getNumberOfPiecesInBetween(gs,currentPlayer,fPosition,direction[0],direction[1]);
		}
		if(flips>0)
		{
			return true;
		}
		
		return false;
	}
	public int getNumberOfPiecesInBetween(GameState gs,String currentPlayer,FieldPosition fPosition,int xCoord,int yCoord)
	{
	String myOpponent=null;
	int flips=0;
	int opponentPieces=0;
	if(currentPlayer.equals("X"))
	{
		myOpponent="O";
	}
	else if(currentPlayer.equals("O"))
	{
		myOpponent="X";
	}
	for(int tempDir=1;
			(fPosition.rowCoord+xCoord*tempDir>=0)
			&&(fPosition.rowCoord+xCoord*tempDir<width)
			&&(fPosition.colCoord+yCoord*tempDir>=0)
			&&(fPosition.colCoord+yCoord*tempDir<height);tempDir++)
	{
String playerUnderConsideration=helper.playeratPosition(fPosition.rowCoord+xCoord*tempDir, fPosition.colCoord+yCoord*tempDir, gs.getCurrentGameState());
		if(playerUnderConsideration.equals(currentPlayer))
		{
			flips+=opponentPieces;
			opponentPieces=0;
			break;
		}
		else if(playerUnderConsideration.equals(myOpponent))
		{
			opponentPieces++;
		}
		else if(playerUnderConsideration.equals("*"))
		{
			return 0;
		}
		
	}
		
	return flips;
	}
	
	public GameState playMove(GameState gs,FieldPosition fPosition,String currentPlayer)
	{
		GameState newGameState=new GameState();
		int newx=0,newy=0;
		int flips=0;
		String currentGame[][]=gs.getCurrentGameState();
		String [][]temPoraryGame=new String[8][8];
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
		temPoraryGame[i][j]=currentGame[i][j];
			}
		}
		newx=fPosition.rowCoord;
		newy=fPosition.colCoord;
		temPoraryGame[newx][newy]=currentPlayer;
		for(int direction[]:Constants.DIRECTIONS)
		{
			flips=getNumberOfPiecesInBetween(gs,currentPlayer,fPosition,direction[0],direction[1]);
			
			if(flips>0)
			{
				temPoraryGame=flipPieces(temPoraryGame,currentPlayer, fPosition, direction[0], direction[1]);	
				
			}
		}	
		newGameState.setCurrentGameState(temPoraryGame);
		if(currentPlayer.equals(myPlayer))
		{
			newGameState.setEvaluationValue(Constants.NEG_INFINITY);
		}
		if(currentPlayer.equals(opponentPlayer))
		{
			newGameState.setEvaluationValue(Constants.INFINITY);
		}
		
		return newGameState;
	}
	public String[][] flipPieces(String [][] temPoraryGame,String currentPlayer,FieldPosition oneOftheBest,int xCoord,int yCoord)
	{

		String [][]flippedTemporaryGame=new String[8][8];
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				flippedTemporaryGame[i][j]=temPoraryGame[i][j];
			}
		}
			
		String myOpponent=null;
		if(currentPlayer.equals("X"))
		{
			myOpponent="O";
		}
		else if(currentPlayer.equals("O"))
		{
			myOpponent="X";
		}
		for (int temp = 1;; temp++) 
		{
	
			if(flippedTemporaryGame[oneOftheBest.rowCoord+temp*xCoord][oneOftheBest.colCoord+temp*yCoord].equals(currentPlayer))
			{
				break;
			}
			else if(flippedTemporaryGame[oneOftheBest.rowCoord+temp*xCoord][oneOftheBest.colCoord+temp*yCoord].equals(myOpponent))
			{
				flippedTemporaryGame[oneOftheBest.rowCoord+temp*xCoord][oneOftheBest.colCoord+temp*yCoord]=currentPlayer;
			}
			
		}
		
		return flippedTemporaryGame;
	}
	
	
	
	public int findEvaluationValue(GameState gs)
	{
		//Competition
		List<FieldPosition> allLegitMovesICanPlay=allPossibleMoves(gs,myPlayer);		
		List<FieldPosition> allLegitMovesOpponentCanPlay=allPossibleMoves(gs,opponentPlayer);
		int numberOfPossibleMovesICanPlay=allLegitMovesICanPlay.size();
		int numberOfPossibleMovesOpponentCanPlay=allLegitMovesOpponentCanPlay.size();
		int myAdvantage=(numberOfPossibleMovesICanPlay-numberOfPossibleMovesOpponentCanPlay)*10;
		//Adding Number Of Moves Advantage
		int evaluatedValue=0;
		String temPoraryGame[][]=gs.getCurrentGameState();
		
		int totalValueOfMyPlayer=0,totalValueOfOpponent=0;
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(temPoraryGame[i][j].equals(myPlayer))
				{
					totalValueOfMyPlayer+=Constants.COMPETITION_HEURISTIC_EVALUATION[i][j];
				}
			}
		}
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(temPoraryGame[i][j].equals(opponentPlayer))
				{
					totalValueOfOpponent+=Constants.COMPETITION_HEURISTIC_EVALUATION[i][j];
				}
			}
		}
		evaluatedValue=(totalValueOfMyPlayer-totalValueOfOpponent)+myAdvantage;
			
		return evaluatedValue;
	}
	
	
}
