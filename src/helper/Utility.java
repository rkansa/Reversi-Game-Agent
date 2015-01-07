package helper;
import java.io.*;
import java.util.List;
/**
 * @author RAJ
 * This class Writes the output 
 */
public class WriteGameOutput {
	private String path;

	public WriteGameOutput(String path) {
		super();
		this.path = path;
	}
	/**
	 * This method opens the connection and writes the output move 
	 * @param move             The move chosen to be played
	 * @throws Exception
	 */
	
	public void writeCompetitionOutput(String move) throws Exception
	{
		FileWriter fw=new FileWriter(path);
		BufferedWriter writer = new BufferedWriter(fw);
		writer.write(move);
		writer.close();
	}
	
	
	
}
