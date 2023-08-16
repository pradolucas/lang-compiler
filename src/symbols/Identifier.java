package symbols;

public class Identifier {
	private String name;
	private DataType type;
	private String value;

	public Identifier(String name, DataType type) {
		super();
		this.name = name;
		this.type = type;
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

	@Override
	public String toString() {
		return "Identifier [name=" + name + ", type=" + type + ", value=" + value + "]";
	}

}
