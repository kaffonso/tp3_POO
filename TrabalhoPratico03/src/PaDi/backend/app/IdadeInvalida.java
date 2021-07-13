package PaDi.backend.app;

import java.time.LocalDate;


public class IdadeInvalida extends Exception {
	private final LocalDate ddn;
	private final String msg;
	
	public IdadeInvalida(LocalDate ddn, String m) {
		this.ddn = ddn;
		this.msg = m;
	}

	/**
	 * @return the idade
	 */
	public LocalDate getDdn() {
		return ddn;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

}
