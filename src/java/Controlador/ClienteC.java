package Controlador;

import Dao.ClienteD;
import Modelo.ClienteM;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "clienteC")
@SessionScoped
public class ClienteC implements Serializable {

    private ClienteM cliente = new ClienteM();
    private List<ClienteM> lstCliente;
    private ClienteM actulcli;
    private ClienteD dao;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception ex) {
        }
    }

    public void limpiar() {
        cliente = new ClienteM();

    }
    
     public List<String> completeTextCliente(String query) throws SQLException {               
        ClienteD Dao = new ClienteD();
        return Dao.autocompleteCliente(query);
    }

    public void registrar() throws Exception {
        ClienteD Dao;
        try {
            Dao = new ClienteD();
            Dao.registrar(cliente);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AGREGADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void modificar() throws Exception {
        ClienteD Dao;
        try {
            Dao = new ClienteD();
            Dao.modificar(actulcli);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ACTUALIZADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR", "AL ACTUALIZAR"));
            throw e;
        }
    }

    public void listar() throws Exception {
        ClienteD Dao;
        try {
            Dao = new ClienteD();
            lstCliente = Dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarInactivos() throws Exception {
        ClienteD Dao;
        try {
            Dao = new ClienteD();
            lstCliente = Dao.listarInactivos();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(ClienteM client) throws Exception {
        ClienteD Dao;
        try {
            Dao = new ClienteD();
            Dao.eliminar(client);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ELIMINADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR", "AL ELIMINAR"));
            throw e;
        }
    }

    /* GETTER AND SETTER */
    public ClienteM getCliente() {
        return cliente;
    }

    public void setCliente(ClienteM cliente) {
        this.cliente = cliente;
    }

    public List<ClienteM> getLstCliente() {
        return lstCliente;
    }

    public void setLstCliente(List<ClienteM> lstCliente) {
        this.lstCliente = lstCliente;
    }

    public ClienteM getActulcli() {
        return actulcli;
    }

    public void setActulcli(ClienteM actulcli) {
        this.actulcli = actulcli;
    }

    public ClienteD getDao() {
        return dao;
    }

    public void setDao(ClienteD dao) {
        this.dao = dao;
    }

}
