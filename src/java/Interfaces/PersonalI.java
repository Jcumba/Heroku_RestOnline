package Interfaces;

import Modelo.PersonalM;
import java.util.List;


public interface PersonalI {
    PersonalM startSession(String User, String Pass) throws Exception;
    
    public void registrar(PersonalM per) throws Exception;

    public List<PersonalM> listar() throws Exception;

    public List<PersonalM> listarInactivos() throws Exception;


    public void modificar(PersonalM dat) throws Exception;

    public void eliminar(PersonalM dat) throws Exception;
    
    

}
