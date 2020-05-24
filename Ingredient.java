
public abstract class Ingredient {
	
	protected int id;
	protected String name;
	protected float price;
	protected boolean isClassic;
	protected boolean isVegetarian;
	protected boolean isVegan;
	
	/**
	 * Superclass constructor that can be used by classes inheriting
	 * from this class.
	 * 
	 * @param name (String) - Name of the Ingredient
	 * @param id (int) - Identifier of the Ingredient
	 * @param price (float) - Price of the Ingredient
	 * @param isClassic (boolean) - tells if it's a classic Ingredient
	 * @param isVegetarian (boolean) - tells if it's a vegetarian Ingredient
	 * @param isVegan (boolean) - tells if it's a vegan Ingredient
	 */
	public Ingredient(String name, int id, float price, boolean isClassic, 
			   		  boolean isVegetarian, boolean isVegan) {
		this.name = name;
		this.id = id;
		this.price = price;
		this.isClassic = isClassic;
		this.isVegetarian = isVegetarian;
		this.isVegan = isVegan;
	}
	
	/**
	 * Getter for id.
	 * 
	 * @return (int) ID of an Ingredient
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Getter for name.
	 * 
	 * @return (String) Name of an Ingredient
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for price.
	 * 
	 * @return (float) Price of an Ingredient
	 */
	public float getPrice() {
		return price;
	}
	
	/**
	 * Getter for classic property.
	 * 
	 * @return (boolean) Classic property
	 */
	public boolean isClassic() {
		return isClassic;
	}
	
	/**
	 * Getter for vegetarian property.
	 * 
	 * @return (boolean) Vegetarian property
	 */
	public boolean isVegetarian() {
		return isVegetarian;
	}
	
	/**
	 * Getter for vegan property.
	 * 
	 * @return (boolean) Vegan property
	 */
	public boolean isVegan() {
		return isVegan;
	}
	
	public String toString() {
		return String.format("%d - %s - %.2f", id, name, price);
	}
	
	/**
	 * This method prints steps to prepare an Ingredient and returns
	 * the time needed in seconds.
	 * 
	 * @return (int) Time for preparation of an Ingredient
	 */
	public abstract int prepare();

}
