package main.java.com.getsetgo.model;

public class MatchClass {
	int match_class_id;
	String match_class_name;
	String match_class_desc;

	public int getMatch_class_id() {
		return match_class_id;
	}
	public void setMatch_class_id(int match_class_id) {
		this.match_class_id = match_class_id;
	}
	public String getMatch_class_name() {
		return match_class_name;
	}
	public void setMatch_class_name(String match_class_name) {
		this.match_class_name = match_class_name;
	}	
	public String getMatch_class_desc() {
		return match_class_desc;
	}
	public void setMatch_class_desc(String match_class_desc) {
		this.match_class_desc = match_class_desc;
	}
	
	public MatchClass(String match_class_name, String match_class_desc){
		this.match_class_name = match_class_name;
		this.match_class_desc = match_class_desc;
	}
}
