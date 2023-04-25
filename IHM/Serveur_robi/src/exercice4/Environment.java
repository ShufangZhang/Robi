package exercice4;

import java.util.HashMap;

public class Environment {
	HashMap<String, Reference> variables;

	public Environment() {
		variables = new HashMap<>();
	}

	public void addReference(String string, Reference robiRef) {
		variables.put(string, robiRef);
	}

	public void removeReference(String string) {
		variables.remove(string);
	}

	public Reference getReferenceByName(String receiverName) {
		return variables.get(receiverName);
	}
}