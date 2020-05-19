
public class Vegetable extends Ingredient implements HeightChanger, TimeConsumer {

	private int sliceCount;
	private int sliceWidth;
	
	/**
	 * Creates new instance of Vegetable.
	 * 
	 * Uses superclass constructor to handle default Ingredient values,
	 * adds amount and thickness of slices. Is always vegan and vegetarian.
	 * 
	 * @see Ingredient#Ingredient(String, int, float, boolean, boolean, boolean)
	 * @param sliceCount (int) - Amount of slices to cut Vegetable into
	 * @param sliceWidth (int) - Thickness of each slice in mm
	 */
	public Vegetable(String name, int id, float price, boolean isClassic, 
				     int sliceCount, int sliceWidth) {
		super(name, id, price, isClassic, true, true);
		this.sliceCount = sliceCount;
		this.sliceWidth = sliceWidth;
	}
	
	@Override
	public int getTime() {
		return sliceCount;
	}
	
	@Override
	public int prepare() {
		for (int i = 1; i <= sliceCount; i++) {
			System.out.printf("Cut %s slice %d with %d mm.\n", name, i, sliceWidth);
		}
		return sliceCount;
	}

	@Override
	public int calculateHeight() {
		return sliceCount * sliceWidth;
	}

}
