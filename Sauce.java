
public class Sauce extends Ingredient{

	public static enum Flavor {normal, hot, sweet};
	
	private Flavor flavor;
	private int amount;
	
	
	public Sauce(String name, int id, float price, boolean isClassic, 
				 boolean isVegetarian, boolean isVegan, int amount, Flavor flavor) {
		super(name, id, price, isClassic, isVegetarian, isVegan);
		this.amount = amount;
		this.flavor = flavor;
	}
	
	public String getFlavorName() {
		return flavor.name();
	}
	
	public int getAmount() {
		return amount;
	}
	
	@Override
	public int prepare() {
		System.out.printf("Shaking %s.\n", name);
		return 0;
	}

	@Override
	public int calculateHeight() {
		return 0;
	}

}
