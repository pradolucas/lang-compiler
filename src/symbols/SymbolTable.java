package symbols;

import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;

public class SymbolTable {
	HashMap<String, Identifier> symbolTable;

	public boolean containsKey(Object key) {
		return symbolTable.containsKey(key);
	}

	public Identifier get(Object key) {
		return symbolTable.get(key);
	}

	public Identifier add(String key, Identifier value) {
		return symbolTable.put(key, value);
	}

	public Collection<Identifier> getValues() {
		return symbolTable.values();
	}

	public ArrayList<Identifier> getAll() {
		ArrayList<Identifier> lista = new ArrayList<Identifier>();
		for (Identifier symbol : symbolTable.values()) {
			lista.add(symbol);
		}
		return lista;
	}

	public SymbolTable() {
		this.symbolTable = new HashMap<String, Identifier>();
	}

}
