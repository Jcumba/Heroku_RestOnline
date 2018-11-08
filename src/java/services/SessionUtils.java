package services;


import javax.faces.context.FacesContext;
import Modelo.PersonalM;

public class SessionUtils {

    public static PersonalM obtenerObjetoSesion() {
        return (PersonalM) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personname");
    }

    public static String ObtenerNombreSesion() {
        PersonalM user = (PersonalM) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personname");
        if (user != null) {
            return user.getNOM_PER();
        } else {
            return null;
        }
    }

    public static String ObtenerCodigoSesion() {
        PersonalM user = (PersonalM) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personname");
        if (user != null) {
            return user.getCOD_PER();
        } else {
            return null;
        }
    }

}
