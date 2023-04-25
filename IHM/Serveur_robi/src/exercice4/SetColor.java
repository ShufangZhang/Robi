package exercice4;

import java.awt.Color;

import graphicLayer.GElement;
import graphicLayer.GSpace;
import stree.parser.SNode;

public class SetColor implements Command {

	public SetColor() {
	}

	@Override
	public Reference run(Reference receiver, SNode method) {

		GElement a = null;
		GSpace b = null;

		System.out.print(receiver.getReceiver().getClass().getSimpleName());

		if (receiver.getReceiver() instanceof GElement) {
			a = (GElement) receiver.receiver;
		} else if (receiver.getReceiver() instanceof GSpace) {
			b = (GSpace) receiver.receiver;
		}

		Color color;
		try {
			java.lang.reflect.Field field = Class.forName("java.awt.Color").getField(method.get(2).contents());
			color = (Color) field.get(null);
		} catch (Exception e) {
			color = null; // Not defined
		}
		if (receiver.getReceiver() instanceof GElement) {
			a.setColor(color);
		} else if (receiver.getReceiver() instanceof GSpace) {
			b.setColor(color);
		}

		return receiver;
	}
}