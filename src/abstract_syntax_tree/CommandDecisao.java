package abstract_syntax_tree;

import java.util.ArrayList;

public class CommandDecisao extends AbstractCommand {

	private String condition;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;

	public CommandDecisao(String condition, ArrayList<AbstractCommand> lt, ArrayList<AbstractCommand> lf) {
		this.condition = condition;
		this.listaTrue = lt;
		this.listaFalse = lf;
	}

	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("if (" + condition + ") {\n");
		for (AbstractCommand cmd : listaTrue) {
			str.append(cmd.generateJavaCode());
		}
		str.append("}");
		if (listaFalse.size() > 0) {
			str.append("else {\n");
			for (AbstractCommand cmd : listaFalse) {
				str.append(cmd.generateJavaCode());
			}
			str.append("}");
		}

		return str.toString();
	}


		@Override
	public String generatePythonCode() {
		StringBuilder str = new StringBuilder();
		str.append("if " + condition + " :\n");
		for (AbstractCommand cmd : listaTrue) {
			str.append("\t\t"+cmd.generatePythonCode());
		}
		
		if (listaFalse.size() > 0) {
			str.append("\telse :\n");
			for (AbstractCommand cmd : listaFalse) {
				str.append("\t\t"+cmd.generatePythonCode());
			}
			
		}

		return str.toString();
	}





	@Override
	public String toString() {
		return "CommandDecisao [condition=" + condition + " listaTrue=" + listaTrue + " listaFalse=" + listaFalse + "]";
	}

}
