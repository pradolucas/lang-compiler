package symbols;

public class Identifier {

	private String name;
	private DataType type;
	private String value;
	private Boolean used;
	private Boolean initialized; // Como há user input, value pode ser Null durante o tempo de compilação.

	public Identifier(String name, DataType type) {
		super();
		this.name = name;
		this.type = type;
		this.used = false;
		this.initialized = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DataType getType() {
		return type;
	}

	public void setType(DataType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getUsed() {
		return used;
	}

	public void setUsed() {
		this.used = true;
	}

	public Boolean getInitialized() {
		return initialized;
	}

	public void setInitialized() {
		this.initialized = true;
	}

	@Override
	public String toString() {
		return "Identifier [name=" + name + ", type=" + type + ", value=" + value + "]";
	}

	public String generateJavaCode() {
		String str;
		if (type == DataType.NUM) {
			str = "double";
		} else {
			str = "String";
		}
		return str + " " + name + ";";
	}
}
