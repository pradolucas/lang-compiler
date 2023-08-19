package abstract_syntax_tree;

public class CommandLeitura extends AbstractCommand {
    
    private String id;

    public CommandLeitura (String id) {
        this.id =id;
    }

    @Override
    public String generateJavaCode() {
        return null;
    }

    @Override
    public String toString() {
        return "CommandLeitura [id="+id+"]";
    }

}
