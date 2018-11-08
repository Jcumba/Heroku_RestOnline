//package Controlador;
//
//import Dao.ClienteD;
//import Modelo.ClienteM;
//import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
//import java.io.Serializable;
//import java.util.List;
//import org.primefaces.context.RequestContext;
//
//@Named(value = "usuarioC")
//@SessionScoped
//public class UsuarioC implements Serializable {
//
//    private ClienteM cliente = new ClienteM();
//    private List<ClienteM> lstCliente;
//    
//    //Variables de Logeo
//    private String COD_CLIENTE;
//    private String NOM_CLIENTE;
//    private String DOC_CLIENTE; // SERA EL USUARIO DEL CLIENTE
//    private String PWD_CLIENTE; // CONTRASEÑA DEL CLIENTE
//    private String EST_CLIENTE; 
//    private int intentos, number; // PARA EL CONTADOR SI ESQUE EL USUARIO PASA EL LIMITE DE INTENTOS
//
//    public void increment(){
//        number++;
//        if (number > 5) {
//            number = 0;
//            intentos  = 0;
//            RequestContext.getCurrentInstance().execute(" locatio.reload (); ");
//        }
//    }
//    
//    public void startSessionEmpleado () throws Exception{
//        ClienteD Dao;
//        try {
//            Dao = new ClienteD();
//            cliente = Dao.IniciarSesion(DOC_CLIENTE, PWD_CLIENTE);
//        } catch (Exception e) {
//        }
//    }
//            
//    
//    // GETTER AND SETTER
//    public ClienteM getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(ClienteM cliente) {
//        this.cliente = cliente;
//    }
//
//    public List<ClienteM> getLstCliente() {
//        return lstCliente;
//    }
//
//    public void setLstCliente(List<ClienteM> lstCliente) {
//        this.lstCliente = lstCliente;
//    }
//
//    public String getCOD_CLIENT() {
//        return COD_CLIENTE;
//    }
//
//    public void setCOD_CLIENT(String COD_CLIENT) {
//        this.COD_CLIENTE = COD_CLIENT;
//    }
//
//    public String getNOM_CLIENTE() {
//        return NOM_CLIENTE;
//    }
//
//    public void setNOM_CLIENTE(String NOM_CLIENTE) {
//        this.NOM_CLIENTE = NOM_CLIENTE;
//    }
//
//    public String getDOC_CLIENTE() {
//        return DOC_CLIENTE;
//    }
//
//    public void setDOC_CLIENTE(String DOC_CLIENTE) {
//        this.DOC_CLIENTE = DOC_CLIENTE;
//    }
//
//    public String getPWD_CLIENTE() {
//        return PWD_CLIENTE;
//    }
//
//    public void setPWD_CLIENTE(String PWD_CLIENTE) {
//        this.PWD_CLIENTE = PWD_CLIENTE;
//    }
//
//    public String getEST_CLIENTE() {
//        return EST_CLIENTE;
//    }
//
//    public void setEST_CLIENTE(String EST_CLIENTE) {
//        this.EST_CLIENTE = EST_CLIENTE;
//    }
//
//    public int getIntentos() {
//        return intentos;
//    }
//
//    public void setIntentos(int intentos) {
//        this.intentos = intentos;
//    }
//
//    public int getNumber() {
//        return number;
//    }
//
//    public void setNumber(int number) {
//        this.number = number;
//    }
// }
