/**
 * This class represents Salad. Salad is an ingredient.
 */
public class Salad extends Ingredient{

	/**
	 * Creates new instance of Salad.
	 * 
	 * Uses superclass constructor to handle default Ingredient values,
	 * but is always vegetarian and vegan.
	 *  
	 * @see Ingredient#Ingredient(String, int, float, boolean, boolean, boolean)
	 */
	public Salad(String name, int id, float price, boolean isClassic) {
		super(name, id, price, isClassic, true, true);
	}
	
	@Override
	public int prepare() {
		System.out.printf("Clean %s.\n", name);
		return 0;
	}
	
}
