package Controlador;

import Dao.PersonalD;
import Modelo.PersonalM;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import services.SessionUtils;

@Named(value = "personalC")
@SessionScoped
public class PersonalC implements Serializable {

    PersonalM personal = new PersonalM();
    private List<PersonalM> lstPersonal;
    private PersonalM lstPeredit;
    private String User;
    private String Pass;
    private int intentos, number;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void limpiar() {
        personal = new PersonalM();

    }

    public void registrar() throws Exception {
        PersonalD Dao;
        try {
            Dao = new PersonalD();
            Dao.registrar(personal);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AGREGADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void modificar() throws Exception {
        PersonalD Dao;
        try {
            Dao = new PersonalD();
            Dao.modificar(lstPeredit);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ACTUALIZADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR", "AL ACTUALIZAR"));
            throw e;
        }
    }

    public void listar() throws Exception {
        PersonalD dao;
        try {
            dao = new PersonalD();
            lstPersonal = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarInactivos() throws Exception {
        PersonalD dao;
        try {
            dao = new PersonalD();
            lstPersonal = dao.listarInactivos();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(PersonalM client) throws Exception {
        PersonalD Dao;
        try {
            Dao = new PersonalD();
            Dao.eliminar(client);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ELIMINADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR", "AL ELIMINAR"));
            throw e;
        }
    }

// SESION
    public void increment() {
        number++;
        if (number > 10) {
            number = 0;
            intentos = 0;
            RequestContext.getCurrentInstance().execute("location.reload();");
        }
    }

    public void startSession() throws Exception {
        PersonalD dao;
        try {
            dao = new PersonalD();
            personal = dao.startSession(User, Pass);
            if (personal != null) {
                intentos = 0;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personname", personal);
                switch (personal.getTIP_PER()) {
                    case "1":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Restaurant/faces/Vistas/Personal/personal.xhtml");
                        break;
                    case "2":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Restaurant/faces/Vistas/Login.xhtml");
                        break;
                }
            } else {
                setPass(null);
                personal = new PersonalM();
                intentos++;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La Contraseña o el Usuario es incorrecto."));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void finishSession() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear(); //Cierra la Session
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Restaurant"); // Mandamos al Login
    }

     public void securityLogin() throws IOException {
        PersonalM us = SessionUtils.obtenerObjetoSesion();
        if (us != null) {
            switch (us.getTIP_PER()) {
                case "3":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/Fractal/faces/vistas/notas/notas.xhtml");
                    break;
                case "2":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/Fractal/faces/vistas/asistencias/asistencia.xhtml");
                    break;
                case "1":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/Fractal/faces/vistas/asistencias/asistencia.xhtml");
                    break;
            }
        }
    }

    public void securitySession() throws IOException {
        if (SessionUtils.obtenerObjetoSesion() == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Restaurant");
        }
    }

    public void obtenerDatos() {
        System.out.println(SessionUtils.ObtenerCodigoSesion());
        System.out.println(SessionUtils.ObtenerNombreSesion());
    }
// FIN DE LA SESION

    public PersonalM getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalM personal) {
        this.personal = personal;
    }

    public List<PersonalM> getLstPersonal() {
        return lstPersonal;
    }

    public void setLstPersonal(List<PersonalM> lstPersonal) {
        this.lstPersonal = lstPersonal;
    }

    public PersonalM getLstPeredit() {
        return lstPeredit;
    }

    public void setLstPeredit(PersonalM lstPeredit) {
        this.lstPeredit = lstPeredit;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

   

}
