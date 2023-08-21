package abstract_syntax_tree;

import java.util.ArrayList;

public class CommandRepeticao extends AbstractCommand {
    private String condition;
	private ArrayList<AbstractCommand> listaCmd;
    private String tipoWhile;

	public CommandRepeticao(String condition, ArrayList<AbstractCommand> lc, String tipoWhile) {
		this.condition = condition;
		this.listaCmd = lc;
        this.tipoWhile = tipoWhile;
	}

	@Override
	public String generateJavaCode() {

        if(tipoWhile == "DoWhile"){

            StringBuilder str = new StringBuilder();
                str.append("do{\n");

                for (AbstractCommand cmd : listaCmd) {
                    str.append(cmd.generateJavaCode());
                }

                str.append("\n} while (" + condition + ");\n");
            

            return str.toString();
        } else {
            StringBuilder str = new StringBuilder();
                str.append("while (" + condition + "){\n");

                for (AbstractCommand cmd : listaCmd) {
                    str.append(cmd.generateJavaCode());
                }

                str.append("\n}");
            

            return str.toString();



        }
    }



    @Override
	public String generatePythonCode() {


            StringBuilder str = new StringBuilder();
                str.append("\n\twhile " + condition + ":\n");
                for (AbstractCommand cmd : listaCmd) {
                    str.append("\t\t"+cmd.generatePythonCode());
                }

                str.append("\n\t\tbreak\n");
            

            return str.toString();
    }




	@Override
	public String toString() {
		return "CommandDecisao [condition=" + condition + " listaCmd=" + listaCmd +"]";
	}
}

