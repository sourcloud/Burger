
public class Salad extends Ingredient{

	public Salad(String name, int id, float price, boolean isClassic) {
		super(name, id, price, isClassic, true, true);	// Salad is always vegan
	}
	
	@Override
	public int prepare() {
		System.out.printf("Cleaning %s.\n", name);
		return 0;
	}

	@Override
	public int calculateHeight() {
		return 0;
	}
	
}
