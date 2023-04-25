package exercice4;

import graphicLayer.GElement;
import graphicLayer.GSpace;
import stree.parser.SNode;

public class AddElement implements Command {

	Environment env;

	public AddElement(Environment environment) {
		this.env = environment;
	}

	@Override
	public Reference run(Reference receiver, SNode method) {

		GSpace space = (GSpace) receiver.getReceiver();
		Reference elem = null;

		Reference target = env.getReferenceByName(method.get(3).get(0).contents().toLowerCase() + ".class");
		elem = target.run(method.get(3));
		/*
		 * switch(method.get(3).get(0).contents()) { case "Rect": //elem = new
		 * NewElement().run(new Reference(GRect.class), method); elem =
		 * env.getReferenceByName("rectClassRef").run(method);; break; case "Oval": elem
		 * = new NewElement().run(new Reference(GOval.class), method); break; case
		 * "Image": elem = new NewElement().run(new Reference(GImage.class), method);
		 * break; case "Label": elem = new NewElement().run(new
		 * Reference(GString.class), method); ((GString)
		 * elem.getReceiver()).setString(method.get(3).get(2).contents()); break; }
		 */

		space.addElement((GElement) elem.receiver);
		env.addReference(method.get(2).contents(), elem);

		space.repaint();

		return null;
	}

}
