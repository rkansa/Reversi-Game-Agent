package helper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadGameData {
	String path;

	public ReadGameData(String path) {
		super();
		this.path = path;
	}
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
