import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
	
	public static final int MAX_COMMITS = 5;

	private final String CMD_ADD = "ingredient";
	private final String CMD_COMMIT = "commit";
	private final String CMD_FINISH = "ok";
	private final String CMD_HELP = "help";
	private final String CMD_MENU = "menu";
	private final String CMD_QUIT = "quit";
	private final String CMD_NAME = "name";
	private final String CMD_SHOW = "burger";
	
	private List<Burger> commitedBurgers;

	private IngredientStorage storage;
	private Burger burgerInCreation;
	private boolean quitWanted;

	public Controller() {
		commitedBurgers = new ArrayList<Burger>();
		storage = IngredientStorage.getInstance();
		burgerInCreation = new Burger();
		quitWanted = false;
	}

	/**
	 * This method answers, if application should end.
	 * 
	 * @return (boolean) Answering if application should end.
	 */
	public boolean quitWanted() {
		return quitWanted;
	}

	/**
	 * This methods gets user input from console and tries to interpret it.
	 * 
	 * @see Controller#getUserInput()
	 * @see Controller#interpretInput(String)
	 */
	public void getAndInterpretUserInput() {
		String userInput = getUserInput();
		interpretInput(userInput);
	}

	/**
	 * Prints introduction text to console.
	 */
	public void printIntro() {
		System.out.println("Welcome to the competition!");
		System.out.println();
		System.out.println("You can now create your own burger!");
		System.out.println("A burger consits of exactly one bun and up to "
				+ Burger.MAX_INGREDIENTS + " other ingredients.");
		System.out.println();
		System.out.println("To get the recipe for your burger or to commit it"
				+ " to the competition, you need to name it first.");
		System.out.println();
		// TODO: write intro text
	}

	/**
	 * Prints list of Ingredients in Storage to console.
	 */
	public void printChoice() {
		System.out.println("You can choose between the following ingredients:");
		storage.printAll();
		System.out.println();
	}

	/**
	 * Prints list of commands to console.
	 */
	public void printHelp() {
		System.out.println("You can use the following commands:");
		System.out.println();
		System.out.println(CMD_ADD + " [Ingredient ID] - Adds the ingredient to your burger. Multiple IDs possible.");
		System.out.println(CMD_COMMIT + "\t\t\t   - Commits the current state of your burger to the competition.");
		System.out.println(CMD_FINISH + "\t\t\t   - Finishes the competition and quits application.");
		System.out.println(CMD_HELP + "\t\t\t   - Opens this list of commands.");
		System.out.println(CMD_MENU + "\t\t\t   - Shows all ingredients in storage.");
		System.out.println(CMD_NAME + " [name]\t\t   - Assigns a name to your burger.");
		System.out.println(CMD_QUIT + "\t\t\t   - Quits application.");
		System.out.println(CMD_SHOW + "\t\t\t   - Shows current state of your burger.");
		System.out.println();
	}

	/**
	 * Returns user input from console.
	 * 
	 * @return (String) user input
	 * @see StaticScanner#nextString()
	 */
	private String getUserInput() {
		System.out.print(">> ");
		return StaticScanner.nextString();
	}

	/**
	 * Interprets user input. First word of input is command, rest are arguments.
	 * Checks command and executes if valid. If needed for execution, will pass and
	 * process arguments.
	 * 
	 */
	private void interpretInput(String userInput) {
		
		boolean inputExists = (userInput != null);
		boolean inputNotEmpty = (!userInput.isBlank());
		
		if (inputExists && inputNotEmpty) {
			String[] input = userInput.split(" ");
			List<String> toParse = new ArrayList<String>(Arrays.asList(input));
			// separates command from arguments
			String command = toParse.remove(0);
			switch (command.toLowerCase()) {
			case CMD_ADD:
				executeAdd(toParse);
				break;
			case CMD_COMMIT:
				commit();
				break;
			case CMD_FINISH:
				finishCurrentBurger();
				break;
			case CMD_HELP:
				printHelp();
				break;
			case CMD_MENU:
				printChoice();
				break;
			case CMD_NAME:
				executeName(toParse);
				break;
			case CMD_QUIT:
				quitWanted = true;
				break;
			case CMD_SHOW:
				printCurrentBurger();
				break;
			default:
				System.out.println("Unknown command \"" + command + "\"!");
			}
		} else {
			System.out.println("No input provided!");
		}
	}

	/**
	 * 
	 * @param ingredientIDs (List<String>) List of IDs
	 */
	private void executeAdd(List<String> ingredientIDs) {
		boolean listExists = (ingredientIDs != null);
		boolean listNotEmpty = (ingredientIDs.size() > 0);
		if (listExists && listNotEmpty) {
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
					System.out.printf("Ignored argument \"%s\" ", number);
					System.out.print("because it is not a number!\n");
				}
			}
		}
	}

	private void executeName(List<String> namingParts) {
		boolean listExists = (namingParts != null);
		boolean listNotEmpty = (namingParts.size() > 0);
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
	
	private void commit() {
		if (meetsRequirements()) {
			commitedBurgers.add(burgerInCreation);
			burgerInCreation = new Burger(); // start from scratch after commit
			boolean reachedLimit = (commitedBurgers.size() >= MAX_COMMITS);
			if (reachedLimit) {
				finishCompetition();
			} else {
			}
		}
	}

	private void finishCurrentBurger() {
		if (meetsRequirements()) {	
			commitedBurgers.add(burgerInCreation);
			finishCompetition();
		}
	}
	
	private void finishCompetition() {
		System.out.println("Finished!");
		System.out.println("You commited following recipes: ");
		for (Burger burger : commitedBurgers) {
			printSummary(burger);
		}
		quitWanted = true;
	}
	
	private boolean meetsRequirements() {
		String burgerName = burgerInCreation.getName();
		boolean burgerHasBun = burgerInCreation.hasBun();
		boolean nameExists = (burgerName != null);
		boolean nameNotEmpty = (!burgerName.isBlank());
		if (!(nameExists && nameNotEmpty)) {
			System.out.println("Please name your burger first");
			return false;
		} else if (!burgerHasBun) {
			System.out.println("Please add a bun first!");
			return false;
		}
		return true;
	}

	private void printCurrentBurger() {
		System.out.println("Your current burger: ");
		System.out.println(burgerInCreation);
	}

	private void printSummary(Burger toSummarize) {
		String burgerName = toSummarize.getName();
		float burgerPrice = toSummarize.getPrice();
		int burgerHeight = toSummarize.getHeight();
		int burgerTime = toSummarize.getTime();
		int minutes = burgerTime / 60;
		int seconds = burgerTime % 60;
		
		String classicString = toSummarize.isClassic() ? " classic" : "";
		String veggieString = toSummarize.isVegan() 
				? "vegan" 
				: toSummarize.isVegetarian() 
					? " vegetarian" 
					: "";
		
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.printf("This is your recipe for %s:\n", burgerName);
		System.out.println("-----------------------------");
		toSummarize.showRecipe();
		System.out.println("-----------------------------");
		System.out.printf("It takes %d minute(s) and %d seconds to prepare\n", minutes, seconds);
		System.out.printf("and will be %d mm high!\n", burgerHeight);
		System.out.println("-----------------------------");
		System.out.printf("It costs %.2f EUR.\n", burgerPrice);
		System.out.println("-----------------------------");
		if (!classicString.isBlank() || !veggieString.isBlank()) {
			System.out.println("You've got a" + veggieString + classicString + " Burger!");
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
	}

}
