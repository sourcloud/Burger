
public class Vegetable extends Ingredient{

	private int sliceCount;
	private int sliceWidth;
	
	public Vegetable(String name, int id, float price, boolean isClassic, 
				     int sliceCount, int sliceWidth) {
		super(name, id, price, isClassic, true, true); // Vegetable is always vegan
		this.sliceCount = sliceCount;
		this.sliceWidth = sliceWidth;
	}
	
	@Override
	public int prepare() {
		for (int i = 1; i <= sliceCount; i++) {
			System.out.printf("Cutting %s slice %d with %d mm.\n", name, i, sliceWidth);
		}
		return sliceCount;
	}

	@Override
	public int calculateHeight() {
		return sliceCount * sliceWidth;
	}

}
