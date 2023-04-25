package exercice4;

import graphicLayer.GElement;
import graphicLayer.GSpace;
import stree.parser.SNode;

public class DelElement implements Command {

	Environment env;

	public DelElement(Environment environment) {
		this.env = environment;
	}

	@Override
	public Reference run(Reference receiver, SNode method) {

		GSpace space = (GSpace) receiver.getReceiver();

		Reference elem = env.getReferenceByName(method.get(2).contents());

		space.removeElement((GElement) elem.receiver);
		env.removeReference(method.get(2).contents());

		space.repaint();

		return null;
	}

}
