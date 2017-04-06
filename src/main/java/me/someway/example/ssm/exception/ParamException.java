package me.someway.example.ssm.exception;

/**
 * 参数异常信息
 * @author RDuser
 *
 */
public class ParamException extends RuntimeException {

	private static final long serialVersionUID = 1L;


	public ParamException() {
    }

    public ParamException(Throwable cause) {
        super(cause);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }


    public ParamException(String message) {
        super(message);
    }



}
