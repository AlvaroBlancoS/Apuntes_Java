package services;

public class ExceptionDNI extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String msg;

	public ExceptionDNI(String msg) {
		this.msg = msg;
	}

	public ExceptionDNI() {

	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "DNI invalido";
	}

}
