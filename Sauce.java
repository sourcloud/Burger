/**
 * This class represents sauce. Sauce is an ingredient that has a flavor.
 *
 */
public class Sauce extends Ingredient {

	public static enum Flavor {normal, hot, sweet};
	
	private Flavor flavor;
	private int amount;
	
	/**
	 * Creates new instance of Sauce.
	 * 
	 * Uses superclass constructor to handle default Ingredient values,
	 * adds amount and flavor.
	 * 
	 * @see Ingredient#Ingredient(String, int, float, Type[])
	 * @param amount (int) - Amount of sauce to be produced in ml
	 * @param flavor (Flavor) - Flavor of the sauce.
	 */
	public Sauce(String name, int id, float price, Type[] types, 
				 int amount, Flavor flavor) {
		super(name, id, price, types);
		this.amount = amount;
		this.flavor = flavor;
	}
	
	/**
	 * Getter for flavor.
	 * 
	 * @return (Flavor) Flavor of sauce
	 */
	
	public Flavor getFlavor() {
		return flavor;
	}
	
	/**
	 * Getter for amount.
	 * 
	 * @return (int) Amount of sauce
	 */
	public int getAmount() {
		return amount;
	}
	
	@Override
	public int prepare() {
		System.out.printf("Shake %s.\n", name);
		return 0;
	}

}
