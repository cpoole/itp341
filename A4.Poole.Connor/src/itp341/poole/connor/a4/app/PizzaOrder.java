package itp341.poole.connor.a4.app;

import java.util.List;

public class PizzaOrder {
	
	public PizzaOrder(String name, List<Boolean> toppings, String specials, int size, int numPizzas) {
		super();
		this.name = name;
		this.toppings = toppings;
		this.specials = specials;
		this.size = size;
		this.numPizzas = numPizzas;
	}
	private String name;
	private List<Boolean> toppings;
	private String specials;
	private int size;
	private int numPizzas;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Boolean> getToppings() {
		return toppings;
	}
	public void setToppings(List<Boolean> toppings) {
		this.toppings = toppings;
	}
	public String getSpecials() {
		return specials;
	}
	public void setSpecials(String specials) {
		this.specials = specials;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getNumPizzas() {
		return numPizzas;
	}
	public void setNumPizzas(int numPizzas) {
		this.numPizzas = numPizzas;
	}
}
