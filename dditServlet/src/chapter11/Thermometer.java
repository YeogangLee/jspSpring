package chapter11;

import java.util.HashMap;
import java.util.Map;

public class Thermometer {
	
	//[위치, 온도] 정보 보관
	private Map<String, Double> locationCelsiusMap = new HashMap<String, Double>();
	
	//[위치, 온도] 정보 추가
	public void setCelsius(String location, Double value) {
		locationCelsiusMap.put(location, value);
	}
	
	//위치의 섭씨온도 구함
	public Double getCelsius(String location) {
		return locationCelsiusMap.get(location);
	}
	
	//위치의 화씨온도 구함
	public Double getFahrenheit(String location) {
		Double celsius = getCelsius(location);
		if(celsius == null) {return null;}
		return celsius.doubleValue() * 1.8 + 32.0;
	}
	
	public String getInfo() {
		return "온도계 변환기 v1.1";
	}
	
	public String getName() {
		return "홍길동";
	}
	
}
