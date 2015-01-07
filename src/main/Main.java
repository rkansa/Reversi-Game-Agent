package main;
import gamePlay.Competition;
import gamePlay.GameState;
import helper.Constants;
import helper.ReadGameData;
import helper.Utility;
import helper.WriteGameOutput;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author RAJ
 *This is the main class which is the entry point of the program
 */
public class Main {

	public static void main(String[] args) throws Exception 
	{
		String inputPath = new File("input.txt").getAbsolutePath();
		String outputPath = new File("output.txt").getAbsolutePath();
		List<String> gameData = new ArrayList<String>();   
		float cpuTimeLeft=0;
		long startTime = System.currentTimeMillis();
		long endTime,totalTime;
		try
		{
		ReadGameData read=new ReadGameData(inputPath);
		gameData=read.openFile(inputPath);         
		String myPlayer=gameData.get(1);
		cpuTimeLeft=Float.parseFloat(gameData.get(2));
		GameState gState=new GameState();
		Utility helper=new Utility();
		String[][] currentGame=helper.createGameStateFromInput(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH,gameData);
		gState.setCurrentGameState(currentGame);
		gState.setCurrentPlayer(myPlayer);
		WriteGameOutput writeOut=new WriteGameOutput(outputPath);
		String competitionMove=null;
		System.out.println("Competition");
		Competition comp=new Competition(gState,cpuTimeLeft);
		competitionMove=comp.play();
		writeOut.writeCompetitionOutput(competitionMove);
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("Total Time Taken="+totalTime);
		System.gc();
		
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}

}
