package abstract_syntax_tree;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import symbols.Identifier;
import symbols.SymbolTable;

public class Program {
	private SymbolTable varTable;
	private ArrayList<AbstractCommand> comandos;
	private String programName;

	public void generateTargetJava() {
		StringBuilder str = new StringBuilder();
		str.append("import java.util.Scanner;\n");
		str.append("public class MainClass{\n");
		str.append("public static void main(String[] args) {\n");

		for (AbstractCommand command : comandos) {
			if(CommandEscrita.class.isInstance(command) || CommandLeitura.class.isInstance(command)) {
				str.append("    Scanner _key = new Scanner(System.in);\n");
				break;
			}
		}

		
		for (Identifier symbol : varTable.getAll()) {
			str.append(symbol.generateJavaCode() + "\n");
		}
		for (AbstractCommand command : comandos) {
			str.append(command.generateJavaCode() + "\n");
		}

		str.append("}");
		str.append("}");

		try {
			FileWriter fr = new FileWriter(new File("MainClass.java"));
			fr.write(str.toString());
			fr.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}










	public void generateTargetPython() {
		StringBuilder str = new StringBuilder();
		str.append("def main():\n");



		
		for (Identifier symbol : varTable.getAll()) {
			str.append("	"+symbol.generatePythonCode() + "\n");
		}
		for (AbstractCommand command : comandos) {
			str.append("	"+command.generatePythonCode() + "\n");
		}


		try {
			FileWriter fr = new FileWriter(new File("MainClass.py"));
			fr.write(str.toString());
			fr.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}














	public SymbolTable getVarTable() {
		return varTable;
	}

	public void setVarTable(SymbolTable varTable) {
		this.varTable = varTable;
	}

	public ArrayList<AbstractCommand> getComandos() {
		return comandos;
	}

	public void setComandos(ArrayList<AbstractCommand> comandos) {
		this.comandos = comandos;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

}
