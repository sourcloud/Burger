
public class Sauce extends Ingredient{

	public static enum Flavor {normal, hot, sweet};
	
	private Flavor flavor;
	private int amount;
	
	/**
	 * Creates new instance of Sauce.
	 * 
	 * Uses superclass constructor to handle default Ingredient values,
	 * adds amount and flavor.
	 * 
	 * @see Ingredient#Ingredient(String, int, float, boolean, boolean, boolean)
	 * @param amount (int) - Amount of sauce to be produced in ml
	 * @param flavor (Flavor) - Flavor of the sauce.
	 */
	public Sauce(String name, int id, float price, boolean isClassic, 
				 boolean isVegetarian, boolean isVegan, int amount, Flavor flavor) {
		super(name, id, price, isClassic, isVegetarian, isVegan);
		this.amount = amount;
		this.flavor = flavor;
	}
	
	/**
	 * Getter for flavor name.
	 * 
	 * @return (String) Name of flavor
	 */
	public String getFlavorName() {
		return flavor.name();
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
		System.out.printf("Shaking %s.\n", name);
		return 0;
	}

}
