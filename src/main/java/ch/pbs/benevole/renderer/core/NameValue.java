package ch.pbs.benevole.renderer.core;

public class NameValue {

	private String name;
	private String value;
	
	private NameValue(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	public static NameValue create(String name, String value){
		return new NameValue(name, value);
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	

}
