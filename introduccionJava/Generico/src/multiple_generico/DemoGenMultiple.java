package multiple_generico;

public class DemoGenMultiple {

	public static void main(String[] args) {
		GenericoMultiple<String, Integer> gm = new GenericoMultiple<>();
		gm.setVariable1("Texto");
		gm.setVariable2(20);

	}

}

class GenericoMultiple<K, V> {
	private K variable1;
	private V variable2;

	public K getVariable1() {
		return variable1;
	}

	public void setVariable1(K variable1) {
		this.variable1 = variable1;
	}

	public V getVariable2() {
		return variable2;
	}

	public void setVariable2(V variable2) {
		this.variable2 = variable2;
	}

}
