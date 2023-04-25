package serveur;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import exercice4.Environment;
import exercice4.Interpreter;
import stree.parser.SNode;
import stree.parser.SParser;

public class ParserCall extends Thread {

	String program;
	Environment environment;

	public ParserCall(String program, Environment environment) {
		this.program = program;
		this.environment = environment;
	}

	@Override
	public void run() {
		SParser<SNode> parser = new SParser<>();
		List<SNode> compiled = null;
		try {
			System.out.println(program);
			compiled = parser.parse(program);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// execution des s-expressions compilees
		Iterator<SNode> itor = compiled.iterator();
		while (itor.hasNext()) {
			if (Thread.interrupted()) {
				System.out.println("thread has received interruption signal");
				return;
			} else {
				System.out.println("isok");
			}
			new Interpreter().compute(this.environment, itor.next());
		}
	}
}
