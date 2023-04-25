package exercice4;

import stree.parser.SNode;

public class Interpreter {

	public void compute(Environment environment, SNode expr) {
		/*
		 * if(Thread.interrupted()) { return; }
		 */
		Reference target = null;
		target = environment.getReferenceByName(expr.get(0).contents());

		// System.out.print(expr.get(0).contents() + " " + expr.get(1).contents() + " "
		// + expr.get(2).contents() + "\n");
		/*
		 * if(expr.get(1).contents().equals("add")) { System.out.print("add\n"); target
		 * = new Reference(environment); }
		 */

		target.run(expr);

	}
}
