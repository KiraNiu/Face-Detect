package faceDetect;

import java.io.IOException;

public class DataProcess {
	
	private String result = null;
	
	public DataProcess(String imageURL) throws IOException {
		result = new Detect().detect(imageURL).replaceAll(" ", "");
		System.out.println(result);
	}
	
	public float getFemaleScore() {
		float femalScore = 0;
		String femalScore1;
		if(result != null) {
			femalScore1 = result.split("\"female_score\"")[1].split(",")[0];
			femalScore1 = femalScore1.substring(1, femalScore1.length());
			femalScore = Float.parseFloat(femalScore1);
		}
		return femalScore;
	}
	
	public float getMaleScore() {
		float malScore = 0;
		String malScore1;
		if(result != null) {
			malScore1 = result.split("\"male_score\"")[1].split("}")[0];
			malScore1 = malScore1.substring(1, malScore1.length());
			malScore = Float.parseFloat(malScore1);
		}
		return malScore;
	}
	
	public String getGender() {
		String gender = null;
		if(result != null) {
			gender = result.split("\"gender\"")[1].split("}")[0];
			gender = gender.substring(11, gender.length()-1);
		}
		return gender.equals("Male") ? "ÄÐ" : "Å®";
	} 
	
	public int getAge() {
		int age = 0;
		String age1 = null;
		if(result != null) {
			age1 = result.split("\"age\"")[1].split("}")[0];
			age1 = age1.substring(10, age1.length());
			age = Integer.parseInt(age1);
		}
		return age;
	}
	
	public String getGlass() {
		String glass = null;
		if(result != null) {
			glass = result.split("\"glass\"")[1].split("}")[0];
			glass = glass.substring(11, glass.length()-1);
			switch (glass) {
			case "None":
				return "²»Åå´÷ÑÛ¾µ";
			case "Dark":
				return "Åå´÷Ä«¾µ";
			case "Normal":
				return "Åå´÷ÆÕÍ¨ÑÛ¾µ";
			}
		}
		return glass;
	}
	
	public float getSkinHealth() {
		String health1 = null;
		float health = 0;
		if(result != null) {
			health1 = result.split("\"health\"")[1].split("}")[0];
			health1 = health1.substring(1, health1.length());
			health = Float.parseFloat(health1);
		}
		return health;
	}
	
	public String getSmile() {
		String threshold1, value1;
		float threshold, value;
		if(result != null) {
			threshold1 = result.split("\"smile\"")[1].split(",")[0];
			threshold1 = threshold1.substring(14, threshold1.length());
			threshold = Float.parseFloat(threshold1);
			value1 = result.split("\"smile\"")[1].split("\"value\"")[1].split("}")[0];
			value1 = value1.substring(1, value1.length());
			value = Float.parseFloat(value1);
			if(value > threshold) return "ÓÐÐ¦ÈÝ";
			else return "ÎÞÐ¦ÈÝ";
		}
		return null;
	}
	
}
