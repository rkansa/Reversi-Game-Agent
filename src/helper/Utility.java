package helper;
import gamePlay.*;
import java.util.ArrayList;
import java.util.List;


public class Utility {

	public String[][] createGameStateFromInput(int height,int width,List<String> gData)
	{
		String[][] gState=new String[height][width];	
		int gameRowNumber=0;
		
		for(int j=3;j<(height+3);j++)                                               //above 3 rows store information 
		{     
			String firstBit=gData.get(j).substring(0,1);
			gState[gameRowNumber][0]=firstBit;
			String secondBit=gData.get(j).substring(1,2);
			gState[gameRowNumber][1]=secondBit;
			String thirdBit=gData.get(j).substring(2,3);
			gState[gameRowNumber][2]=thirdBit;
			String fourthBit=gData.get(j).substring(3,4);
			gState[gameRowNumber][3]=fourthBit;
			String fifthBit=gData.get(j).substring(4,5);
			gState[gameRowNumber][4]=fifthBit;
			String sixthBit=gData.get(j).substring(5,6);
			gState[gameRowNumber][5]=sixthBit;
			String seventhBit=gData.get(j).substring(6,7);
			gState[gameRowNumber][6]=seventhBit;
			String eigthBit=gData.get(j).substring(7,8);
			gState[gameRowNumber][7]=eigthBit;	
			gameRowNumber++;
		}
		return gState;
	}
	

	public String playerWithPosition(FieldPosition position,String[][] currentGame)
	{
		String playerWithPosition=null;
		playerWithPosition=currentGame[position.rowCoord][position.colCoord];
		return playerWithPosition;
	}
	public String playeratPosition(int x,int y,String[][] currentGame)
	{
		String playerAtPosition=null;
		playerAtPosition=currentGame[x][y];
		return playerAtPosition;
	}
	public boolean isPlayerOnboard(FieldPosition position)
	{
	int x=position.rowCoord;
	int y=position.colCoord;
	if((x<0)||(x>7))
	{
		return false;
	}
	if((y<0)||(y>7))
	{
		return false;
	}
	
	return true;
	
	}
	public String giveProperNodeValue(FieldPosition fPos)
	{
		String sysNode=null;
		String synodeCol=null;
		String synodeRow=null;
		int fPosCol=(fPos.colCoord+1);
		switch(fPosCol)
		{
		case -1:
			synodeCol="pass";
			break;
		case 0:
			synodeCol="root";
			break;
		case 1:
			synodeCol="a";
			break;
		case 2:
			synodeCol="b";
			break;
		case 3:
			synodeCol="c";
			break;
		case 4:
			synodeCol="d";
			break;
		case 5:
			synodeCol="e";
			break;
		case 6:
			synodeCol="f";
			break;
		case 7:
			synodeCol="g";
			break;
		case 8:
			synodeCol="h";
			break;
		}
		int fPosRow=fPos.rowCoord+1;
		if(fPosRow!=0 || fPosRow!=-1)
		{
		synodeRow=fPosRow+"";
		}
		if(fPosRow==0 || fPosRow==-1)
		{
			synodeRow="";
		}
		sysNode=synodeCol+synodeRow;
	
		return sysNode;
	}
	public String giveProperEvalValue(int eval)
	{
		String evalValue=null;
		if(eval==Constants.NEG_INFINITY)
		{
			evalValue="-Infinity";
		}
		else if(eval==Constants.INFINITY)
		{
			evalValue="Infinity";
		}
		else
		{
			evalValue=eval+"";
		}
		
		return evalValue;
	}
	
	public List<String> twoDimToOne(String [][] afterPlay)
	{
		String[] afterPlaySingDim=new String[8];
		
		
		for(int i=0;i<8;i++)
		{
			StringBuffer result = new StringBuffer();
			for(int j=0;j<8;j++)
			{
				
			result.append(afterPlay[i][j]);
			}
			afterPlaySingDim[i]=result.toString();
			//afterPlaySingDim[i]=Arrays.toString(afterPlay[i]);
		}
		
			List<String> game=new ArrayList<String>(8);
			
			for(String gameRow:afterPlaySingDim)
			{
				game.add(gameRow);
			}
		return game;
	}
	public boolean isBoardFullOrhasOnlyXorhasOnlyO(GameState gs)
	{
		String [][]game=gs.getCurrentGameState();
		int countX=0,countY=0,countStar=0;
		for(int i=0;i<Constants.BOARD_HEIGHT;i++)
		{
			for(int j=0;j<Constants.BOARD_HEIGHT;j++)
			{
			if(game[i][j].equals("X"))
			countX++;
			if(game[i][j].equals("O"))
			countY++;
			if(game[i][j].equals("*"))
				countStar++;
			}
		}
		if(countX==0 || countY==0 || countStar==0)
			return false;
		
		return true;
	}
	
	
}
