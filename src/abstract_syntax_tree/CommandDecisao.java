package abstract_syntax_tree;

import java.util.ArrayList;

public class CommandDecisao extends AbstractCommand {
    
    private String condition;
    private ArrayList<AbstractCommand> listaTrue;
    private ArrayList<AbstractCommand> listaFalse;


    public CommandDecisao (String condition, ArrayList<AbstractCommand> lt, ArrayList<AbstractCommand> lf) {
        this.condition = condition;
        this.listaTrue = lt;
        this.listaFalse = lf;
    }

    @Override
    public String generateJavaCode() {
        return null;
    }

    @Override
    public String toString() {
        return "CommandDecisao [condition="+condition+"listaTrue="+listaTrue+"listaFalse="+listaFalse+"]";
    }
    
}
