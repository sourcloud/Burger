
public abstract class Ingredient {
	
	protected int id;
	protected String name;
	protected float price;
	protected boolean isClassic;
	protected boolean isVegetarian;
	protected boolean isVegan;
	
	public Ingredient(String name, int id, float price, boolean isClassic, 
			   		  boolean isVegetarian, boolean isVegan) {
		this.name = name;
		this.id = id;
		this.price = price;
		this.isClassic = isClassic;
		this.isVegetarian = isVegetarian;
		this.isVegan = isVegan;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public abstract int prepare();
	public abstract int calculateHeight();
	
	public String toString() {
		return name + " - " + price;
	}

}
