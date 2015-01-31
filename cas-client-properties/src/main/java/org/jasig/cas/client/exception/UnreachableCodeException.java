/**
 * 
 */
package org.jasig.cas.client.exception;

/**
 * @author Cruise.Xu
 *
 */
public class UnreachableCodeException extends RuntimeException{
	
	private static final long serialVersionUID = -4015393249021653091L;

    public UnreachableCodeException() {
        super();
    }

    public UnreachableCodeException(String message) {
        super(message);
    }

    public UnreachableCodeException(Throwable cause) {
        super(cause);
    }

    public UnreachableCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
