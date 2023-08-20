package abstract_syntax_tree;

import java.util.ArrayList;

public class CommandRepeticao extends AbstractCommand {
    private String condition;
	private ArrayList<AbstractCommand> listaCmd;

	public CommandRepeticao(String condition, ArrayList<AbstractCommand> lc) {
		this.condition = condition;
		this.listaCmd = lc;
	}

	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
            str.append("do{\n");

            for (AbstractCommand cmd : listaCmd) {
                str.append(cmd.generateJavaCode());
            }

            str.append("\n} while (" + condition + ");\n");
		

		return str.toString();
    }

	@Override
	public String toString() {
		return "CommandDecisao [condition=" + condition + " listaCmd=" + listaCmd +"]";
	}
}

