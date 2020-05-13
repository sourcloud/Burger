
public class Patty extends Ingredient {
	
	private final float SHRINK_FACTOR = 0.965f;
	
	private int cookingTime;
	private int height;
	
	/**
	 * Creates new instance of Patty.
	 * 
	 * Uses superclass constructor to handle default Ingredient values,
	 * adds height and cooking time.
	 * 
	 * @see Ingredient#Ingredient(String, int, float, boolean, boolean, boolean)
	 * @param height (int) - Height of the Bun
	 * @param cookingTime (int) - Time needed to grill the Patty
	 */
	public Patty(String name, int id, float price, boolean isClassic, 
				 boolean isVegetarian, boolean isVegan, int height, int cookingTime) {
		super(name, id, price, isClassic, isVegetarian, isVegan);
		this.height = height;
		this.cookingTime = cookingTime;
	}

	@Override
	public int prepare() {
		System.out.printf("Grilling each side of %s for %d minutes and %d seconds.\n", name, (cookingTime / 120), (cookingTime % 60));
		return cookingTime;
	}

	@Override
	public int calculateHeight() {
		int fullMinutes = cookingTime / 60;
		return (int) (height * Math.pow(SHRINK_FACTOR, fullMinutes));	
	}

}
