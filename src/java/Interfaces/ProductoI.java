package Interfaces;

import Modelo.ProductoM;
import java.util.List;


public interface ProductoI {

    public void registrar(ProductoM produc) throws Exception;

    public List<ProductoM> listar() throws Exception;

    public List<ProductoM> listarInactivos() throws Exception;

    public void modificar(ProductoM dat) throws Exception;

    public void eliminar(ProductoM dat) throws Exception;

}
