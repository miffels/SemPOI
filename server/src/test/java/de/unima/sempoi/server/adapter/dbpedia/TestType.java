package de.unima.sempoi.server.adapter.dbpedia;

public class TestType {
	
	private String in;
	private String out;
	
	public String getIn() {
		return in;
	}
	public String getOut() {
		return out;
	}
	
	@Override
	public String toString() {
		return this.in + "->" + this.out;
	}

}
