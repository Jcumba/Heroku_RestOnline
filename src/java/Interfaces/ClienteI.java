package Interfaces;

import Modelo.ClienteM;
import java.io.IOException;
import java.util.List;


public interface ClienteI {

    public void registrar(ClienteM clien) throws Exception;

    public List<ClienteM> listar() throws Exception;

    public List<ClienteM> listarInactivos() throws Exception;


    public void modificar(ClienteM dat) throws Exception;

    public void eliminar(ClienteM dat) throws Exception;
    
//    public ClienteM consultaDNI(ClienteM cliente) throws org.json.JSONException, IOException;

}
