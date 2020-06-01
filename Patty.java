/**
 * This class represents a patty. A patty is an ingredient that takes time to
 * prepare and influences height of a burger.
 *
 */
public class Patty extends Ingredient implements HeightChanger, TimeConsumer {
	
	private final float SHRINK_FACTOR = 0.965f;
	
	private int cookingTime;
	private int height;
	
	/**
	 * Creates new instance of Patty.
	 * 
	 * Uses superclass constructor to handle default Ingredient values,
	 * adds height and cooking time.
	 * 
	 * @see Ingredient#Ingredient(String, int, float, Type[])
	 * @param height (int) - Height of the Bun
	 * @param cookingTime (int) - Time needed to grill the Patty
	 */
	public Patty(String name, int id, float price, Type[] types, 
				 int height, int cookingTime) {
		super(name, id, price, types);
		this.height = height;
		this.cookingTime = cookingTime;
	}
	
	@Override
	public int getTimeConsumed() {
		return cookingTime;
	}

	@Override
	public int prepare() {
		int timePerSide = cookingTime / 2;
		int minutes = timePerSide / 60;
		int seconds = timePerSide % 60;
		System.out.printf("Grill each side of %s for %d minutes and %d seconds.\n", 
				name, minutes, seconds);
		return cookingTime;
	}

	@Override
	public int calculateHeight() {
		int fullMinutes = cookingTime / 60;
		return (int) (height * Math.pow(SHRINK_FACTOR, fullMinutes));	
	}

}
