package helper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author RAJ
 *This class provides methods to Read Game data from the Input File
 */
public class ReadGameData {
	String path;

	public ReadGameData(String path) {
		super();
		this.path = path;
	}
	/**
	 * This Method opens the file connection and reads data from it
	 * @param File_Path          Path where the file is stored
	 * @return fileData          Data of the file
	 * @throws IOException
	 */
	public List<String> openFile(String File_Path) throws IOException
	{
		List<String> fileData = new ArrayList<String>();
		FileReader fr=new FileReader(File_Path);
		BufferedReader br=new BufferedReader(fr);
		int lineCounter=readLines(File_Path);
		
		for(int i=0;i<lineCounter;i++)
		{
			fileData.add(br.readLine());
		}
		
		br.close();
		return fileData;
	}

	/**
	 * This method counts the total number of lines to be read
	 * @param path               Path where the file is stored
	 * @return totalLines        Total number of lines in the file
	 * @throws IOException
	 */
	public int readLines(String path)throws IOException
	{
		FileReader fr=new FileReader(path);
		BufferedReader br=new BufferedReader(fr);
		int totalLines=0;
		while((br.readLine())!=null)
		{
			totalLines++;
		}
		br.close();
		return totalLines;
	}
}
