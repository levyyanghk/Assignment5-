import java.util.*;
import java.io.*;

public class AirlineDataRead {
	
	private ArrayList<AirlineData> listOfAirline;

	public AirlineDataRead(String filename){
		File f = new File(filename);
		int lineNo = 0;
		try {
			Scanner sc = new Scanner(f);
			//Skip the first row
			sc.nextLine();
			listOfAirline = new ArrayList<AirlineData>();

			while(sc.hasNextLine()) {
				String row = sc.nextLine();
				String[] rawData = row.split(",");
				AirlineData data = new AirlineData(rawData);
				lineNo++;
		//		System.out.print("line:" + lineNo +" ");
				data.printAirlineData();
				listOfAirline.add(data);
			
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ArrayList<AirlineData> getAirlineData() {
		return this.listOfAirline;
	}
	
}
