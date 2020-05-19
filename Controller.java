import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
	
	private final String CMD_ADD = "ingredient";
	private final String CMD_COMMIT = "commit";
	private final String CMD_HELP = "help";
	private final String CMD_MENU = "menu";
	private final String CMD_NAME = "name";
	private final String CMD_QUIT = "ok";
	private final String CMD_SHOW = "burger";
	
	private IngredientStorage storage;
	private Burger burgerInCreation;
	private String currentInput;
	
	
	public Controller() {
		storage = IngredientStorage.getInstance();
		burgerInCreation = new Burger();
	}
	
	public void printIntro() {
		System.out.println("Welcome to the competition!");
		System.out.println();
	}
	
	public void printChoice() {
        System.out.println("You can choose between the following ingredients: ");
        storage.printAll();
        System.out.println();
	}
	
	public void printHelp() {
		System.out.println(CMD_ADD + " [Ingredient ID] - adds the ingredients to your burger. Multiple IDs possible.");
		System.out.println(CMD_COMMIT + " - commits the current state of your burger to the competition.");
		System.out.println(CMD_HELP + " - opens this list of commands.");
		System.out.println(CMD_MENU + " - shows all ingredients in storage.");
		System.out.println(CMD_NAME + " [name] - assigns a name to your burger.");
		System.out.println(CMD_QUIT + " - finishes the composition and prints the recipe.");
		System.out.println(CMD_SHOW + " - shows current state of your burger.");
	}
	
    public void getUserInput() {
        System.out.print(">> ");
        currentInput = StaticScanner.nextString();
    }
    
    public void print() {
    	System.out.println(burgerInCreation);
    }
    
    public void interpretInput() {
    	if (currentInput != null) {
    		List<String> toParse = new ArrayList<String>(Arrays.asList(currentInput.split(" ")));
    		String command = toParse.remove(0); // returns and removes command, leaves arguments for further processing
    		switch (command) {
	    		case CMD_ADD:
	    			executeAdd(toParse);
	    			break;
	    		case CMD_COMMIT:
	    			// TODO: add name and add burger to contest
	    			break;
	    		case CMD_HELP:
	    			printHelp();
	    			break;
	    		case CMD_MENU:
	    			storage.printAll();
	    			break;
	    		case CMD_NAME:
	    			executeName(toParse);
	    			break;
	    		case CMD_QUIT:
	    			finish();
	    			break;
	    		case CMD_SHOW:
	    			System.out.println(burgerInCreation);
	    			break;
	    		default:
	    			System.out.println("Invalid input!");	
    		}
    	}
    }
    
    private void executeAdd(List<String> ingredientIDs) {
    	if (ingredientIDs != null && ingredientIDs.size() > 0) {
    		for (String number : ingredientIDs) {
    			try {
    				int id = Integer.parseInt(number);
    				Ingredient toAdd = storage.get(id);
    				if (toAdd == null) {
    					System.out.printf("ID %d is not in storage!\n", id);
    				} else {    					
    					burgerInCreation.addIngredient(toAdd);
    				}
       			} catch (NumberFormatException e) {
       				System.out.printf("Ignored command \"%s\" because it is not a number!\n", number);
       			} 
    		}
    	}
    }
    
    private void executeName(List<String> namingParts) {
    	boolean listExists = namingParts != null;
    	boolean listNotEmpty = namingParts.size() > 0;	
    	if (listExists && listNotEmpty) {
    		String name = "";
    		for (String part : namingParts) {
    			name += part + " ";
    		}
    		burgerInCreation.setName(name);
    	} else {
    		System.out.println("No name passed!");
    	}
    }
    
    private void finish() {
    	String name = burgerInCreation.getName();
    	if (name == null || name.isBlank()) {
    		forceName();
    	}
    	printSummary();
    }
    
    private void forceName() {
  		do {
			System.out.println("Please assign a name to your burger.");
			getUserInput();
		} while (currentInput.isBlank());
		burgerInCreation.setName(currentInput);
    }
    
    private void printSummary() {
       	System.out.println();
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	System.out.printf("This is your recipe for %s:\n", burgerInCreation.getName());
    	System.out.println("-----------------------------");
    	burgerInCreation.showRecipe();
    	System.out.println("-----------------------------");
    	int time = burgerInCreation.getTime();
    	System.out.printf("It takes %d minutes and %d seconds to prepare.\n", (time / 60), (time % 60));
    	System.out.println("-----------------------------");
    	float price = burgerInCreation.getPrice();
    	System.out.printf("It costs %.2f EUR.\n", price);
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	System.out.println();
    }
    
    
    public static void main(String[] args) {
    	
    	// just random testing
    	Controller c = new Controller();
    	c.printChoice();
    	List<String> adds = new ArrayList<>();
    	adds.add("10");
    	adds.add("10");
    	adds.add("20");
    	adds.add("a");
    	adds.add("0");
    	c.executeAdd(adds);
    	c.finish();
    	c.print();
    	
    }
}
