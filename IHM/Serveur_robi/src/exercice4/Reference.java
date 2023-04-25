package exercice4;

import java.util.HashMap;
import java.util.Map;

import stree.parser.SNode;

public class Reference {
	Object receiver;
	Map<String, Command> primitives;

	public Reference(Object receiver) {
		this.receiver = receiver;
		primitives = new HashMap<>();
	}

	public void addCommand(String str, Command primitive) {
		primitives.put(str, primitive);
	}

	public Command getCommandByName(String str) {
		return primitives.get(str);
	}

	public Reference run(SNode expr) {
		Command cmd = this.getCommandByName(expr.get(1).contents());
		return cmd.run(this, expr);

	}

	public Object getReceiver() {
		return receiver;
	}
}
