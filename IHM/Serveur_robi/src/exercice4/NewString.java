package exercice4;

import java.lang.reflect.InvocationTargetException;

import graphicLayer.GString;
import stree.parser.SNode;

public class NewString implements Command {

	@Override
	public Reference run(Reference reference, SNode method) {
		try {
			@SuppressWarnings("unchecked")
			GString e = ((Class<GString>) reference.getReceiver()).getDeclaredConstructor().newInstance();
			e.setString(method.get(2).contents());
			Reference ref = new Reference(e);
			ref.addCommand("setColor", new SetColor());
			ref.addCommand("translate", new Translate());
			return ref;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return null;
	}

}
