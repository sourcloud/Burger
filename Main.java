
public class Main {

	public static void main(String[] args) {

		Controller c = new Controller();

		c.printIntro();
		c.printChoice();
		c.printHelp();
		while (!c.quitWanted()) {
			c.getAndInterpretUserInput();
		}

	}
}
