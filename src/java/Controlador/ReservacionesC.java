package Controlador;

import Dao.ClienteD;
import Dao.ProductoD;
import Dao.ReservacionesD;
import Modelo.ReservacionesM;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "reservacionesC")
@SessionScoped
public class ReservacionesC implements Serializable {

    private ReservacionesM reserva = new ReservacionesM();
    private List<ReservacionesM> listProd;
    private List<ReservacionesM> lstCliente;
    private ReservacionesD dao;

    @PostConstruct
    public void init() {
        try {
            listarReservacion();
        } catch (Exception e) {
        }
    }

    public void listarReservacion() throws Exception {
        try {
            dao = new ReservacionesD();
            listProd = dao.listarReservaciones();
        } catch (Exception e) {
            throw e;
        }
    }

    private void limpiar() {
        reserva = new ReservacionesM();
    }

    Calendar Calend = Calendar.getInstance();
    String act = Calend.get(Calend.DATE) + "/" + (Calend.get(Calend.MONTH) + 01) + "/" + Calend.get(Calend.YEAR);

    public String fechaActual() {
        Date fecha = new Date(0, 0, 0);
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return formateador.format(fecha);
    }

    public void registrarRese() throws Exception {
        ReservacionesD Dao;
        ClienteD cliente;
        ProductoD producto;
        try {
            Dao = new ReservacionesD();
            cliente = new ClienteD();
            reserva.setCODCLIE(cliente.obtenerCodigoCliente(reserva.getCODCLIE()));
            producto = new ProductoD();
            reserva.setCODPROD(producto.obtenerCodigoProducto(reserva.getCODPROD()));
            Dao.registrar(reserva);
            listarReservacion();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AGREGADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }



    public Calendar getCalend() {
        return Calend;
    }

    public void setCalend(Calendar Calend) {
        this.Calend = Calend;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public ReservacionesM getReserva() {
        return reserva;
    }

    public void setReserva(ReservacionesM reserva) {
        this.reserva = reserva;
    }

    public List<ReservacionesM> getListProd() {
        return listProd;
    }

    public void setListProd(List<ReservacionesM> listProd) {
        this.listProd = listProd;
    }

    public ReservacionesD getDao() {
        return dao;
    }

    public void setDao(ReservacionesD dao) {
        this.dao = dao;
    }

    public List<ReservacionesM> getLstCliente() {
        return lstCliente;
    }

    public void setLstCliente(List<ReservacionesM> lstCliente) {
        this.lstCliente = lstCliente;
    }
}