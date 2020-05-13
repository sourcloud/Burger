
public class Controller {

	Burger burgerInCreation;
	static IngredientStorage storage;
	
	public static void printIntro() {
		System.out.println("Welcome to the competition!");
        System.out.println("We have the following ingredients: ");
	}
	
    public static int getUserInput() {
        System.out.println(">> ");
        int id = StaticScanner.nextInt();
        return id;
    }

    
    /**
     * 
     * @return
     */
    public static Bun getBun() {
    	boolean isValidInput = false;
    	Ingredient item = null;
	    while (!isValidInput) {	
	        System.out.printf("Which bun would you like?");
	        storage.printAllOf(IngredientStorage.Type.bun);
	        int id = getUserInput();
	        item = storage.get(id);
	        if (item != null && item instanceof Bun) {
	        	isValidInput = true;
	        }
	    }
	    return (Bun) item;
    }
}
