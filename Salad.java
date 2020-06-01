/**
 * This class represents Salad. Salad is an ingredient.
 */
public class Salad extends Ingredient{

	/**
	 * Creates new instance of Salad.
	 * 
	 * Uses superclass constructor to handle default Ingredient values.
	 *  
	 * @see Ingredient#Ingredient(String, int, float, Type[])
	 */
	public Salad(String name, int id, float price, Type[] types) {
		super(name, id, price, types);
	}
	
	@Override
	public int prepare() {
		System.out.printf("Clean %s.\n", name);
		return 0;
	}
	
}
