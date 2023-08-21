package abstract_syntax_tree;

public class CommandAtribuicao extends AbstractCommand {

	private String id;
	private String expr;

	public CommandAtribuicao(String id, String expr) {
		this.id = id;
		this.expr = expr;
	}

	@Override
	public String generateJavaCode() {
		return id + " = " + expr + ";\n";
	}

	@Override
	public String generatePythonCode() {
		return id + " = " + expr + "\n";
	}

	@Override
	public String toString() {
		return "CommandAtribuicao [id=" + id + ", expr=" + expr + "]";
	}

}
