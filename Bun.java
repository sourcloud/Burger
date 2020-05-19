
public class Bun extends Ingredient implements HeightChanger, TimeConsumer {
	
	private final float GROWTH_FACTOR = 1.02f;

	private int toastingTime;
	private int height;
	
	/**
	 * Creates new instance of Bun.
	 * 
	 * Uses superclass constructor to handle default Ingredient values,
	 * adds height and toasting time.
	 * @see Ingredient#Ingredient(String, int, float, boolean, boolean, boolean)
	 * @param height (int) - Height of the Bun
	 * @param toastingTime (int) - Time needed to toast the Bun
	 */
	public Bun(String name, int id, float price, boolean isClassic, 
			   boolean isVegetarian, boolean isVegan, int height, int toastingTime) {
		super(name, id, price, isClassic, isVegetarian, isVegan);
		this.height = height;
		this.toastingTime = toastingTime;
	}
	
	@Override
	public int getTime() {
		return toastingTime;
	}
	
	@Override
	public int prepare() {
		System.out.printf("Toast %s for %d minutes and %d seconds.\n", name, (toastingTime / 60), (toastingTime % 60));
		return toastingTime;
	}

	@Override
	public int calculateHeight() {
		int fullMinutes = toastingTime / 60;
		return (int) (height * Math.pow(GROWTH_FACTOR, fullMinutes));		
	}


}
