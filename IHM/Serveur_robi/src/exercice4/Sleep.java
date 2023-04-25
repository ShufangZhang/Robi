package exercice4;

import stree.parser.SNode;

public class Sleep implements Command {

	public Sleep() {
	}

	@Override
	public Reference run(Reference receiver, SNode method) {

		// Tools.sleep(Integer.parseInt(method.get(2).contents()));
		try {
			Thread.sleep(Integer.parseInt(method.get(2).contents()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("sleep interrupted");

			return null;
		}

		return null;
	}

}
