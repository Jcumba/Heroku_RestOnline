//package Dao;
//
//import Modelo.ClienteM;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UsuarioD  extends Dao{
//    
//    public ClienteM IniciarSesion(String DOC_CLIENT, String CONTRA_CLIENTE ) throws Exception{
//        this.Conexion();
//        ResultSet rs;
//        ClienteM cliente = null;
//        try {
//            String sql = "SELECT COD_CLIENT,NOM_CLIENT,TELF_CLIENT,EST_CLIENT FROM CLIENTE WHERE DOC_CLIENT LIKE ? AND CONTRA_CLIENTE LIKE ? AND EST_CLIENT LIKE 'A'";
//            PreparedStatement ps = this.getCn().prepareCall(sql);
//            ps.setString(1, DOC_CLIENT);
//            ps.setString(2, CONTRA_CLIENTE);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                cliente = new ClienteM();
//                cliente.setCOD_CLIENT(rs.getString("DOC_CLIENT"));
//                cliente.setNOM_CLIENT(rs.getString("NOM_CLIENT"));
//                cliente.setTELF_CLIENT(rs.getString("TELF_CLIENT"));
//                cliente.setESTADO(rs.getString("EST_CLIENT"));
//                cliente.setDOC_CLIENT(DOC_CLIENT);
//                cliente.setCONTRA_CLIENTE(CONTRA_CLIENTE);
//            }
//            return cliente;
//        } catch (SQLException e) {
//            throw e;
//        }finally{
//            this.Cerrar();
//        }
//    }
//    
//    public void registrar(ClienteM clien) throws Exception {
//        try {
//            this.Conexion();
//            String sql = "INSERT INTO CLIENTE (DOC_CLIENT,NOM_CLIENT,TELF_CLIENT,TIP_DOC_CLIENT,EST_CLIENT,CONTRA_CLIENTE) VALUES (?,?,?,?,?,?)";
//            PreparedStatement st = this.getCn().prepareStatement(sql);
//            st.setString(1, clien.getDOC_CLIENT());
//            st.setString(2, clien.getNOM_CLIENT());
//            st.setString(3, clien.getTELF_CLIENT());
//            st.setString(4, clien.getTIP_DOC_CLIEN());
//            st.setString(5, "A");
//            st.setString(6, clien.getCONTRA_CLIENTE());
//            st.executeUpdate();
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            this.Cerrar();
//        }
//    }
//
//    
//}
