package model.dao;

public class Element {
	
	private int id;
	private char symbol;
	
	public Element(int id, char symbol) {
		this.id= id;
		this.symbol = symbol;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	

}
