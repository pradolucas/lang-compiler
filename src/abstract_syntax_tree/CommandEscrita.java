package abstract_syntax_tree;

public class CommandEscrita extends AbstractCommand {

	private String id;

	public CommandEscrita(String id) {
		this.id = id;
	}

	@Override
	public String generateJavaCode() {
		return "System.out.println(" + id + ");\n";
	}

	@Override
	public String generatePythonCode() {
		return "print(" + id + ")\n";
	}

	@Override
	public String toString() {
		return "CommandEscrita [id=" + id + "]";
	}

}
