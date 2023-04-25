package exercice4;

import java.awt.Image;
import java.awt.Toolkit;

import graphicLayer.GImage;
import stree.parser.SNode;

public class NewImage implements Command {

	@Override
	public Reference run(Reference reference, SNode method) {
		Image img1 = Toolkit.getDefaultToolkit().getImage("../" + method.get(2).contents());
		GImage e = new GImage(img1);
		Reference ref = new Reference(e);
		ref.addCommand("translate", new Translate());
		return ref;

	}

}
