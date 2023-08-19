package abstract_syntax_tree;

import java.util.ArrayList;

import symbols.SymbolTable;

public class Program {
    private SymbolTable varTable;
    private ArrayList<AbstractCommand> comandos;
    private String programName;



    public void generateTarget(){

    }



    public SymbolTable getVarTable(){
        return varTable;
    }

    public void setVarTable(SymbolTable varTable){
        this.varTable = varTable;
    }

    public ArrayList<AbstractCommand> getComandos(){
        return comandos;
    }

    public void setComandos(ArrayList<AbstractCommand> comandos){
        this.comandos = comandos;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName){
        this.programName = programName;
    }


}
