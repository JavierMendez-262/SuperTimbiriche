package interfaces;

import java.io.IOException;

/**
 *
 * @author Javier Obeso, J. Armando Méndez, J. Eduardo Montoya, L. Enrique Mendoza
 */
public interface IBroker {
    
    public void findClient() throws IOException;
    
    public void findServer() throws IOException;
    
    public Object forwardRequest() throws IOException, ClassNotFoundException;
    
    public void forwardResponse(Object response) throws IOException;
}
