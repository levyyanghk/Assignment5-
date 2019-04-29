import java.util.*;

public class AirlineData {
	private int DayofMonth;
	private int DayofWeek;
	private String FlightDate;
	private String Carrier;
	private String TailNum;
	private int OriginAirportId;
	private String Origin;
	private String OriginStateName;
	private int DestAirportId;
	private String Dest;
	private String DestStateName;
	private int DepTime;
	private int DepDelay;
	private int WheelsOff;
	private int WheelsOn;
	private int ArrTime;
	private int ArrDelay;
	private int Cancelled;
	private String CancellationCode;
	private int Diverted;
	private int AirTime;
	private int Distance;
	

	public AirlineData() {
		// TODO Auto-generated constructor stub
	}
	
	public AirlineData(String[] data)
	{
	//	System.out.println("data[0] is " + data[0]);
		this.DayofMonth = Integer.parseInt(data[0]);
		this.DayofWeek = Integer.parseInt(data[1]);
		this.FlightDate = data[2];
		this.Carrier = data[3];
		this.TailNum = data[4];
		this.OriginAirportId = Integer.parseInt(data[5]);
		this.Origin = data[6];
		this.OriginStateName =data[7];
		this.DestAirportId = Integer.parseInt(data[8]);
		this.Dest = data[9];
		this.DestStateName = data[10];
		if (data[11].isEmpty() == false)
			this.DepTime = Integer.parseInt(data[11]);
		else
			this.DepTime = 0;
		if (data[12].isEmpty() == false)
			this.DepDelay = Integer.parseInt(data[12]);
		else
			this.DepDelay = 0;
		
		if (data[13].isEmpty() == false)
			this.WheelsOff = Integer.parseInt(data[13]);
		else
			this.WheelsOff = 0;
		
		if (data[14].isEmpty() == false)
			this.WheelsOn = Integer.parseInt(data[14]);
		else
			this.WheelsOn = 0;
		
		if (data[15].isEmpty() == false)
			this.ArrTime = Integer.parseInt(data[15]);
		else
			this.ArrTime = 0;
		
		if (data[16].isEmpty() == false)
			this.ArrDelay = Integer.parseInt(data[16]);
		else
			this.ArrDelay = 0;
		
		if (data[20].isEmpty() == false)
			this.AirTime = Integer.parseInt(data[20]);
		else
			this.AirTime = 0;
		

		this.Cancelled = Integer.parseInt(data[17]);
		this.CancellationCode = data[18];
		this.Diverted = Integer.parseInt(data[19]);
		
		this.Distance = Integer.parseInt(data[21]);
	
	}
	
	public int getDayofMonth() {
		return this.DayofMonth;
	}
	
	public int getDayofWeek() {
		return this.DayofWeek;
	}
	
	public String getFlightDate() {
		return this.FlightDate;
	}
	
	public String getCarrier() {
		return this.Carrier;
	}
	
	public String getTailNum() {
		return this.TailNum;
	}
	
	public int getOriginAirportId() {
		return this.OriginAirportId;
	}
	
	public String getOrigin() {
		return this.Origin;
	}
	
	public String getOriginStateName() {
		return this.OriginStateName;
	}
	
	public int getDestAirportId() {
		return this.DestAirportId;
	}
	
	public String getDest() {
		return this.Dest;
	}
	
	public String getDestStateName() {
		return this.DestStateName;
	}

	public int getDepTime() {
		return this.DepTime;
	}
	
	public int getDepDelay() {
		return this.DepDelay;
	}
	
	public int getWheelsOff() {
		return this.WheelsOff;
	}
	
	public int getWheelsOn() {
		return this.WheelsOn;
	}
	
	public int getArrTime() {
		return this.ArrTime;
	}
	
	public int getArrDelay() {
		return this.ArrDelay;
	}
	
	public int getAirTime() {
		return this.AirTime;
	}
	
	public int getCancelled() {
		return this.Cancelled;
	}
	
	public String getCancellationCode() {
		return this.CancellationCode;
	}
	
	public int getDiverted() {
		return this.Diverted;
	}
	
	public int getDistance() {
		return this.Distance;
	}

	public void printAirlineData() {
/*		
		System.out.println("DayofMonth " + this.DayofMonth + " DayofWeek " +this.DayofWeek
				+ " FlightDate " + this.FlightDate + " Carrier " + this.Carrier + " TailNum "
				+ this.TailNum + " OriginAirportID " + this.OriginAirportId + " Origin "
				+ this.Origin + " OriginStateName " + this.OriginStateName + " DestAirportID "
				+ this.DestAirportId + " Dest " + this.Dest + " DestStateName " + this.DestStateName
				+ " DepTime " + this.DepTime + " DepDelay " + this.DepDelay + " WheelsOff "
				+ this.WheelsOff + " WheelsOn " + this.WheelsOn + " ArrTime " + this.ArrTime
				+ " ArrDelay " + this.ArrDelay + " Cancelled " + this.Cancelled + " CancellationCode "
				+ this.CancellationCode + " Diverted " + this.Diverted + " AirTime " + this.AirTime
				+ " Distance " + this.Distance);
*/				
		
	}

}
