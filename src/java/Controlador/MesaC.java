package Controlador;

import Dao.MesaD;
import Modelo.MesaM;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "mesaC")
@SessionScoped
public class MesaC implements Serializable {

    private MesaM mesa = new MesaM();
    private List<MesaM> lstMesa;
    private MesaM selectmes;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        MesaD Dao;
        try {
            Dao = new MesaD();
            Dao.registrar(mesa);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AGREGADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void modificar() throws Exception {
        MesaD Dao;
        try {
            Dao = new MesaD();
            Dao.modificar(selectmes);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ACTUALIZADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR", "AL ACTUALIZAR"));
            throw e;
        }
    }

    public void listar() throws Exception {
        MesaD dao;
        try {
            dao = new MesaD();
            lstMesa = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarInactivos() throws Exception {
        MesaD dao;
        try {
            dao = new MesaD();
            lstMesa = dao.listarInactivos();
        } catch (Exception e) {
            throw e;
        }
    }

   

    public void limpiar() {
        mesa = new MesaM();

    }
//GETTER AND SETTER

    public MesaM getMesa() {
        return mesa;
    }

    public void setMesa(MesaM mesa) {
        this.mesa = mesa;
    }

    public List<MesaM> getLstMesa() {
        return lstMesa;
    }

    public void setLstMesa(List<MesaM> lstMesa) {
        this.lstMesa = lstMesa;
    }

    public MesaM getSelectmes() {
        return selectmes;
    }

    public void setSelectmes(MesaM selectmes) {
        this.selectmes = selectmes;
    }

}
