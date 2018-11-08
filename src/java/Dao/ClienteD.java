package Dao;

import Interfaces.ClienteI;
import Modelo.ClienteM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteD extends Dao implements ClienteI {

    @Override
    public void registrar(ClienteM clien) throws Exception {
        try {
            this.Conexion();
            String sql = "INSERT INTO CLIENTE (CODCLIE,TDOC_CLIE,DOCCLIE,NOMCLIE,TELFCLIE,ESTCLIE,PWDCLIE,DIRCLIE,APEPATECLIE,APEMATECLIE) VALUES (SQ_CODCLIE.nextval,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, clien.getTDOC_CLIE());
            st.setString(2, clien.getDOCCLIE());
            st.setString(3, clien.getNOMCLIE());
            st.setString(4, clien.getTELFCLIE());
            st.setString(5, "A");
            st.setString(6, clien.getPWDCLIE());
            st.setString(7, clien.getDIRCLIE());
            st.setString(8, clien.getAPEPATECLIE());
            st.setString(9, clien.getAPEMATECLIE());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<ClienteM> listar() throws Exception {
        List<ClienteM> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM CLIENTE WHERE ESTCLIE like 'A'";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                ClienteM cliente = new ClienteM();
                cliente.setCODCLIE(rs.getString("CODCLIE"));
                cliente.setTDOC_CLIE(rs.getString("TDOC_CLIE"));
                cliente.setDOCCLIE(rs.getString("DOCCLIE"));
                cliente.setNOMCLIE(rs.getString("NOMCLIE"));
                cliente.setTELFCLIE(rs.getString("TELFCLIE"));
                cliente.setESTCLIE(rs.getString("ESTCLIE"));
                cliente.setPWDCLIE(rs.getString("PWDCLIE"));
                cliente.setDIRCLIE(rs.getString("DIRCLIE"));
                cliente.setAPEPATECLIE(rs.getString("APEPATECLIE"));
                cliente.setAPEMATECLIE(rs.getString("APEMATECLIE"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    @Override
    public List<ClienteM> listarInactivos() throws Exception {
        List<ClienteM> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM CLIENTE WHERE ESTCLIE = 'I'";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                ClienteM cliente = new ClienteM();
                cliente.setCODCLIE(rs.getString("CODCLIE"));
                cliente.setTDOC_CLIE(rs.getString("TDOC_CLIE"));
                cliente.setDOCCLIE(rs.getString("DOCCLIE"));
                cliente.setNOMCLIE(rs.getString("NOMCLIE"));
                cliente.setTELFCLIE(rs.getString("TELFCLIE"));
                cliente.setESTCLIE(rs.getString("ESTCLIE"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    @Override
    public void modificar(ClienteM cliente) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE CLIENTE SET TDOC_CLIE=?, DOCCLIE=?, NOMCLIE=?, TELFCLIE=?,ESTCLIE=?,PWDCLIE=?, DIRCLIE=?,APEPATECLIE=?,APEMATECLIE=? WHERE CODCLIE=? ";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, cliente.getTDOC_CLIE());
            st.setString(2, cliente.getDOCCLIE());
            st.setString(3, cliente.getNOMCLIE());
            st.setString(4, cliente.getTELFCLIE());
            st.setString(5, cliente.getESTCLIE());
            st.setString(6, cliente.getPWDCLIE());
            st.setString(7, cliente.getDIRCLIE());
            st.setString(8, cliente.getAPEPATECLIE());
            st.setString(9, cliente.getAPEMATECLIE());
            st.setString(10, cliente.getCODCLIE());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(ClienteM cliente) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE CLIENTE SET ESTCLIE = 'I' WHERE CODCLIE = ?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, cliente.getCODCLIE());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public String obtenerCodigoCliente(String Client) throws SQLException {
        this.Conexion();
        ResultSet rs;

        try {
            String sql = "select CODCLIE from CLIENTE where UPPER(NOMCLIE||' '||APEPATECLIE||' '||APEMATECLIE) like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, Client);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODCLIE");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteCliente(String Consulta) throws SQLException {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select (UPPER(NOMCLIE||' '||APEPATECLIE||' '||APEMATECLIE)) AS CLIENTE from CLIENTE WHERE upper(NOMCLIE)  LIKE upper(?) OR UPPER(APEPATECLIE) LIKE UPPER(?) AND ROWNUM <= 10";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            ps.setString(2, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("CLIENTE"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

}
