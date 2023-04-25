package serveur;

import java.awt.Dimension;

import exercice4.AddElement;
import exercice4.DelElement;
import exercice4.Environment;
import exercice4.NewElement;
import exercice4.NewImage;
import exercice4.NewString;
import exercice4.Reference;
import exercice4.SetColor;
import exercice4.Sleep;
import graphicLayer.GImage;
import graphicLayer.GOval;
import graphicLayer.GRect;
import graphicLayer.GSpace;
import graphicLayer.GString;

public class InitSpace {
	Environment environment = new Environment();

	public InitSpace() {
		GSpace space = new GSpace("Server", new Dimension(400, 400));
		space.open();

		Reference spaceRef = new Reference(space);
		Reference rectClassRef = new Reference(GRect.class);
		Reference ovalClassRef = new Reference(GOval.class);
		Reference imageClassRef = new Reference(GImage.class);
		Reference stringClassRef = new Reference(GString.class);

		spaceRef.addCommand("setColor", new SetColor());
		spaceRef.addCommand("sleep", new Sleep());

		spaceRef.addCommand("add", new AddElement(environment));
		spaceRef.addCommand("del", new DelElement(environment));


		rectClassRef.addCommand("new", new NewElement());
		ovalClassRef.addCommand("new", new NewElement());
		imageClassRef.addCommand("new", new NewImage());
		stringClassRef.addCommand("new", new NewString());

		environment.addReference("space", spaceRef);
		environment.addReference("rect.class", rectClassRef);
		environment.addReference("oval.class", ovalClassRef);
		environment.addReference("image.class", imageClassRef);
		environment.addReference("label.class", stringClassRef);
	}
}
