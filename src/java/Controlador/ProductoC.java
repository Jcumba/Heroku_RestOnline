package Controlador;

import Dao.ProductoD;
import Modelo.ProductoM;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "productoC")
@SessionScoped
public class ProductoC implements Serializable {

    private ProductoM producto = new ProductoM();
    private List<ProductoM> lstProducto;
    private ProductoM lstEdit;
   

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
        }
    }
    public void limpiar() {
        producto = new ProductoM();

    }

    public void registrar() throws Exception {
        ProductoD Dao;
        try {
            Dao = new ProductoD();
            Dao.registrar(producto);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AGREGADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

      
     public List<String> completeTextProducto(String query) throws SQLException {               
        ProductoD Dao = new ProductoD();
        return Dao.autocompleteProducto(query);
    }
     
    public void modificar() throws Exception {
        ProductoD Dao;
        try {
            Dao = new ProductoD();
            Dao.modificar(lstEdit);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ACTUALIZADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR", "AL ACTUALIZAR"));
            throw e;
        }
    }

    public void listar() throws Exception {
        ProductoD dao;
        try {
            dao = new ProductoD();
            lstProducto = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarInactivos() throws Exception {
        ProductoD dao;
        try {
            dao = new ProductoD();
            lstProducto = dao.listarInactivos();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(ProductoM client) throws Exception {
        ProductoD Dao;
        try {
            Dao = new ProductoD();
            Dao.eliminar(client);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ELIMINADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR", "AL ELIMINAR"));
            throw e;
        }
    }

    public ProductoM getProducto() {
        return producto;
    }

    public void setProducto(ProductoM producto) {
        this.producto = producto;
    }

    public List<ProductoM> getLstProducto() {
        return lstProducto;
    }

    public void setLstProducto(List<ProductoM> lstProducto) {
        this.lstProducto = lstProducto;
    }

    public ProductoM getLstEdit() {
        return lstEdit;
    }

    public void setLstEdit(ProductoM lstEdit) {
        this.lstEdit = lstEdit;
    }


}
