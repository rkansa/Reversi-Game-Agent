package helper;
import java.io.*;
import java.util.List;
public class WriteGameOutput {
	private String path;

	public WriteGameOutput(String path) {
		super();
		this.path = path;
	}
	public void writeGreedyOutput(String game[][]) throws Exception
	{	FileWriter fw=new FileWriter(path);
		String[] words=null;
		 BufferedWriter writer = new BufferedWriter(fw);
		 for(int i=0;i<8;i++)
		 {
		    words = game[i];
		    for (String word: words) {
		        writer.write(word);
		        
		    }
		    writer.newLine();    
		 }
		    
		    writer.close();	
	}	
	public void writeMinMaxAlphaBetaOutput(List<String> minMax) throws Exception
	{
		FileWriter fw=new FileWriter(path);
		BufferedWriter writer = new BufferedWriter(fw);
		for(String word:minMax)
		{
			writer.write(word);
			writer.newLine();
		}
		writer.close();
		
	}
	public void writeCompetitionOutput(String move) throws Exception
	{
		FileWriter fw=new FileWriter(path);
		BufferedWriter writer = new BufferedWriter(fw);
		writer.write(move);
		writer.close();
	}
	
	
	
}
