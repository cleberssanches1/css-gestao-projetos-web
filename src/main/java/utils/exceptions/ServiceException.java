package utils.exceptions;

import lombok.Getter;

@Getter
public class ServiceException extends Exception{
	 
	private static final long serialVersionUID = -5893606168250266376L;
	
	private Integer code;
	
	public ServiceException(String msg, Integer code) {
		super(msg);
		this.code = code;
	}
	
	public ServiceException(String msg) {
		super(msg);
	}
	 
}
