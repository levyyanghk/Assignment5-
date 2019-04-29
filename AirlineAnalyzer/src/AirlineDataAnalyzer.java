import java.util.*;

public class AirlineDataAnalyzer {
	private ArrayList<AirlineData> listOfAirData;
	private AirlineDataRead airlineData;
	private String carrierHasMostCancelRate;
	private double maxCancelRate;
	
	class CancelData {
		int flightTimes;
		int cancelTimes;
		double cancelRate;
		
		public CancelData() {
			flightTimes = 0;
			cancelTimes = 0;
			cancelRate = 0.0;
		}
	}
	
	class AirportFlightInfo {
		int in;
		int out;
		int sink; //in - out
		int total; //in+out
		int source; //out-in
		
		public AirportFlightInfo() {
			in = 0;
			out = 0;
			sink = 0;
			total = 0;
			source = 0;
		}
	}
	
	private HashMap<Integer,AirportFlightInfo> AirportFlightInfoMap;

	public AirlineDataAnalyzer() {
		AirportFlightInfoMap = null;
		// TODO Auto-generated constructor stub
	}
	
	public AirlineDataAnalyzer(String filename) {
		
		this.airlineData = new AirlineDataRead(filename);
		this.listOfAirData = this.airlineData.getAirlineData();
		this.maxCancelRate = 0;
	}
	
	//Question 1: Which airline has the highest cancel rate

	public void calculateHighestCancelRate() {
		//Create a HashMap using Airline as the keywords
		HashMap<String, CancelData> cancelMap = new HashMap<String, CancelData>();
		CancelData tempCancelData = null;

		
		for (AirlineData data:listOfAirData) {
			if (cancelMap.containsKey(data.getCarrier())) {
				tempCancelData = cancelMap.get(data.getCarrier());
			} else {
				tempCancelData = new CancelData();
			}
/*			
			if (data.getCancelled() == 1) {
				System.out.println(" Carrier canceld " + data.getCarrier());
			}
*/			
			tempCancelData.cancelTimes += data.getCancelled();
			if (data.getCancelled() == 0)
				tempCancelData.flightTimes +=1;
			if (tempCancelData.flightTimes > 0)
				tempCancelData.cancelRate = (double)tempCancelData.cancelTimes/tempCancelData.flightTimes;

			cancelMap.put(data.getCarrier(), tempCancelData);
		}
		
		for (String carrier: cancelMap.keySet())
		{
			tempCancelData = cancelMap.get(carrier);

			if (tempCancelData.cancelRate > maxCancelRate) {
				maxCancelRate = tempCancelData.cancelRate;
				carrierHasMostCancelRate = carrier;
	/*
				System.out.println("maximum " + carrier + " flightTimes " + tempCancelData.flightTimes
						+ " Cancel times " + tempCancelData.cancelTimes);
	*/
			}		
		}

	}
	
	public String getCarrierHasMostCancelRate() {
		return carrierHasMostCancelRate;
	}
	
	public double getMaxCancelRate() {
		return maxCancelRate;
	}
	
	//Question 2, what is the most cancel cause
	public String getMostCancelCause()
	{
		HashMap<String, Integer> cancelCause = new HashMap<String,Integer>();
		int time;
		int maximumCount = 0;
		String maximumCause = new String();
		
		for (AirlineData data:listOfAirData) {
			if (data.getCancelled() == 1) {
				if(cancelCause.containsKey(data.getCancellationCode())) {
					time = cancelCause.get(data.getCancellationCode()) +1;
				} else {
					time = 1;			
				}
				cancelCause.put(data.getCancellationCode(), time);				
			}
		}
		
		for (String cancelCode: cancelCause.keySet()) {
			if (cancelCause.get(cancelCode) > maximumCount) {
				maximumCount = cancelCause.get(cancelCode);
				maximumCause = cancelCode;
			}
			
		}
		
		return maximumCause;
		
	}
	
	//Question 3: Which plane (tailNUmber) flow the furtherest
	public String getFurtherestPlane()
	{
		HashMap <String, Integer> distanceMap = new HashMap<String, Integer>();
		int distance = 0;
		int maxDistance = 0;
		String maxTailNum = null;
		for (AirlineData data:listOfAirData) {
			if(distanceMap.containsKey(data.getTailNum())) {
				distance = distanceMap.get(data.getTailNum()) + data.getDistance();
			} else {
				distance = data.getDistance();
			}
			distanceMap.put(data.getTailNum(), distance);
		}
		
		for(String tailNum:distanceMap.keySet())
		{
			if (distanceMap.get(tailNum) > maxDistance) {
				maxTailNum = tailNum;
				maxDistance = distanceMap.get(tailNum);
			}
		}
		
		return maxTailNum;
		
	}
	
	public void calAirportFlightInfo() {
		AirportFlightInfoMap = new HashMap<Integer, AirportFlightInfo>();		
		for (AirlineData data:listOfAirData) {
			AirportFlightInfo info;
			if(AirportFlightInfoMap.containsKey(data.getOriginAirportId())) {
				info = AirportFlightInfoMap.get(data.getOriginAirportId());
				info.out += 1;
			} else {
				info = new AirportFlightInfo();
				info.out = 1;
				info.in = 0;
			}
			
			info.sink = info.in - info.out;
			info.source = info.out - info.in;
			info.total = info.in + info.out;
			
			AirportFlightInfoMap.put(data.getOriginAirportId(), info);
	
			if(AirportFlightInfoMap.containsKey(data.getDestAirportId())) {
				info = AirportFlightInfoMap.get(data.getDestAirportId());
				info.in += 1;
			} else {
				info = new AirportFlightInfo();
				info.in = 1;
				info.out = 0;
			}
	
			info.sink = info.in - info.out;
			info.total = info.in + info.out;
			info.source = info.out - info.in;
			AirportFlightInfoMap.put(data.getDestAirportId(), info);			

		}
/*		
		for(int id : AirportFlightInfoMap.keySet()) {
			System.out.println("id " + id + " in " + AirportFlightInfoMap.get(id).in
					+ " out " + AirportFlightInfoMap.get(id).out + " total " +
					AirportFlightInfoMap.get(id).total);
		}
*/
		
	}
	
	//Question 4:Which airport is the busiest by total numbers of flight in and out
	
	public int getBusiestAirportId() {
		int maxTime = 0;
		int busiestAirportId = 0;
		if (AirportFlightInfoMap == null) {
			calAirportFlightInfo();
		}
		
		for(int id : AirportFlightInfoMap.keySet()) {
			if(AirportFlightInfoMap.get(id).total > maxTime) {
				busiestAirportId = id;
				maxTime = AirportFlightInfoMap.get(id).total;
			}
		}	
		return busiestAirportId;
	}
	
	//Question 5: Which airport is the biggest source of airplane
	public int getMostSourceAirportId() {
		int maxSource = 0;
		int AirportId = 0;
		if (AirportFlightInfoMap == null) {
			calAirportFlightInfo();
		}
		
		for(int id : AirportFlightInfoMap.keySet()) {
			if(AirportFlightInfoMap.get(id).source > maxSource) {
				AirportId = id;
				maxSource= AirportFlightInfoMap.get(id).source;
			}
		}
	
		return AirportId;
	}
	
	//Question 6: Which airport is the biggest sink of airplane
	public int getMostSinkAirportId() {
		int maxSink = 0;
		int AirportId = 0;
		if (AirportFlightInfoMap == null) {
			calAirportFlightInfo();
		}
		
		for(int id : AirportFlightInfoMap.keySet()) {
			if(AirportFlightInfoMap.get(id).sink > maxSink) {
				AirportId = id;
				maxSink= AirportFlightInfoMap.get(id).sink;
			}
		}
	
		return AirportId;
	}
	
	//Question 7: How many AA airline delayed more than 60 minutes. Only count 1 for depart and arrive case
	
	public int getDelayTimesOfAA() {
		int delayTimes = 0;
		for (AirlineData data:listOfAirData) {
			if (data.getCarrier().equalsIgnoreCase("AA")) {
				if (data.getArrDelay() >= 60 || data.getDepDelay() >= 60)
					delayTimes++;
			}
		}
		return delayTimes;
	}
	
	//Question 8: Which flight make up the most delay
	public String getAirlineHasMostDelayMakeup() {
		String resultString = new String();
		int maxMakeup = 0;
		for (AirlineData data:listOfAirData) {
			if (data.getDepDelay() > 0 && data.getArrDelay() <= 0) {
				if (data.getDepDelay() > maxMakeup) {
					maxMakeup = data.getDepDelay();
					resultString = data.getDayofMonth() + "," + data.getDepDelay()
									+ "," + data.getTailNum();
				}
			}
		}
		return resultString;
	}
	
	//Question 9: How many airlines delay more than 60 minutes
	public int getDelayTimesOfAllAirline() {
		int delayTimes = 0;
		for (AirlineData data:listOfAirData) {
			if (data.getArrDelay() >= 60 || data.getDepDelay() >= 60)
			delayTimes++;			
		}
		return delayTimes;
	}

	public static void main(String[] args) {
		
		AirlineDataAnalyzer airlineData = new AirlineDataAnalyzer("flights.csv");
		airlineData.calculateHighestCancelRate();
		
		System.out.println("Carrier has most cancel rate is: "
		+ airlineData.getCarrierHasMostCancelRate() + " Cancel rate is: " +
				airlineData.getMaxCancelRate());
		
		System.out.println("Most cancel cause is: " + airlineData.getMostCancelCause());
		
		System.out.println("Furtherest TailNum: " + airlineData.getFurtherestPlane());
		
		System.out.println("Busiest airport ID: " + airlineData.getBusiestAirportId());
		
		System.out.println("MostSource airport ID: " + airlineData.getMostSourceAirportId());
		
		System.out.println("MostSink airport ID: " + airlineData.getMostSinkAirportId());
		
		System.out.println(airlineData.getDelayTimesOfAA() + " are delayed for more than 60 minitues");
		
		System.out.println("Most delay make up " + airlineData.getAirlineHasMostDelayMakeup());
		
		FormattedOutput answers = new FormattedOutput();
		answers.addAnswer(1, airlineData.getCarrierHasMostCancelRate() + "," +
				airlineData.getMaxCancelRate()*100.0 + "%");
		answers.addAnswer(2, airlineData.getMostCancelCause());
		answers.addAnswer(3, airlineData.getFurtherestPlane());	
		answers.addAnswer(4, airlineData.getBusiestAirportId());
		answers.addAnswer(5, airlineData.getMostSourceAirportId());
		answers.addAnswer(6, airlineData.getDelayTimesOfAA());
		answers.addAnswer(7, airlineData.getAirlineHasMostDelayMakeup());
		answers.addAnswer(8, airlineData.getBusiestAirportId());
		answers.addAnswer(9, airlineData.getDelayTimesOfAllAirline());
		answers.writeAnswers();
		// TODO Auto-generated method stub

	}
	

}
