/**
 * This abstract class describes requirements for an ingredient of a burger.
 * Must have id, name, price and information about being classic, vegetarian
 * and vegan.
 * 
 * Each subclass must implement a prepare() method.
 */
public abstract class Ingredient {
	
	public static enum Type {classic, none, vegan, vegetarian};
	
	protected String name;
	protected Type[] types;
	protected float price;
	protected int id;
	
	/**
	 * Superclass constructor that can be used by classes inheriting
	 * from this class.
	 * 
	 * @param name (String) - Name of the Ingredient
	 * @param id (int) - Identifier of the Ingredient
	 * @param price (float) - Price of the Ingredient
	 * @param types (Type[]) - Types of the Ingredient
	 */
	public Ingredient(String name, int id, float price, Type[] types) {
		this.name = name;
		this.id = id;
		this.price = price;
		this.types = types;
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
	 * Getter for types.
	 * 
	 * @return (Type[]) Types of an Ingredient
	 */
	public Type[] getTypes() {
		return types;
	}
	
	@Override
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
