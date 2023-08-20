package abstract_syntax_tree;

import symbols.DataType;
import symbols.Identifier;

public class CommandLeitura extends AbstractCommand {

	private String id;
	private Identifier var;

	public CommandLeitura(String id, Identifier var) {
		this.id = id;
		this.var = var;
	}

	@Override
	public String generateJavaCode() {
		return id + "= _key." + (var.getType() == DataType.NUM ? "nextDouble();" : "nextLine();");
	}

	@Override
	public String toString() {
		return "CommandLeitura [id=" + id + "]";
	}

}
