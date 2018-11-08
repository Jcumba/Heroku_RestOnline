package Dao;

import Interfaces.ProductoI;
import Modelo.ProductoM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoD extends Dao implements ProductoI {

    @Override
    public void registrar(ProductoM produc) throws Exception {
        try {
            this.Conexion();
            String sql = "insert into PRODUCTO(CODPROD,TIPPROD,NOMPROD,PRECIOPROD,OFERPROD,FOTOPROD,ESTPROD)VALUES (SQ_CODPROD.nextval,?,?,?,?,?,?)";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, produc.getTIPPROD());
            st.setString(2, produc.getNOMPROD());
            st.setString(3, produc.getPRECIOPROD());
            st.setString(4, produc.getOFERPROD());
            st.setString(5, produc.getFOTOPROD());
            st.setString(6, "A");
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<ProductoM> listar() throws Exception {
        List<ProductoM> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT CODPROD,TIPPROD,NOMPROD,PRECIOPROD,OFERPROD,FOTOPROD,ESTPROD FROM PRODUCTO WHERE ESTPROD like 'A'";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                ProductoM produc = new ProductoM();
                produc.setCODPROD(rs.getString("CODPROD"));
                produc.setTIPPROD(rs.getString("TIPPROD"));
                produc.setNOMPROD(rs.getString("NOMPROD"));
                produc.setPRECIOPROD(rs.getString("PRECIOPROD"));
                produc.setOFERPROD(rs.getString("OFERPROD"));
                produc.setFOTOPROD(rs.getString("FOTOPROD"));
                produc.setESTPROD(rs.getString("ESTPROD"));
                lista.add(produc);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    @Override
    public List<ProductoM> listarInactivos() throws Exception {
        List<ProductoM> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM PRODUCTO WHERE ESTPROD = 'I'";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                ProductoM produc = new ProductoM();
                produc.setCODPROD(rs.getString("CODPROD"));
                produc.setTIPPROD(rs.getString("TIPPROD"));
                produc.setNOMPROD(rs.getString("NOMPROD"));
                produc.setPRECIOPROD(rs.getString("PRECIOPROD"));
                produc.setOFERPROD(rs.getString("OFERPROD"));
                produc.setFOTOPROD(rs.getString("FOTOPROD"));
                produc.setESTPROD(rs.getString("ESTPROD"));
                lista.add(produc);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    @Override
    public void modificar(ProductoM produc) throws Exception {
        try {
            this.Conexion();
            String sql = "update PRODUCTO set TIPPROD=?,NOMPROD=?,PRECIOPROD=?,OFERPROD=?,FOTOPROD=?,ESTPROD=? where CODPROD=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, produc.getTIPPROD());
            st.setString(2, produc.getNOMPROD());
            st.setString(3, produc.getPRECIOPROD());
            st.setString(4, produc.getOFERPROD());
            st.setString(5, produc.getFOTOPROD());
            st.setString(6, produc.getESTPROD());
            st.setString(8, produc.getCODPROD());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(ProductoM produc) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE PRODUCTO SET ESTPROD = 'I' WHERE CODPROD = ?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, produc.getCODPROD());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public String obtenerCodigoProducto(String Product) throws SQLException {
        this.Conexion();
        ResultSet rs;
        try {
                String sql = "SELECT CODPROD FROM PRODUCTO WHERE NOMPROD LIKE ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, Product);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODPROD");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteProducto(String Consulta) throws SQLException {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select NOMPROD AS PRODUCTO from PRODUCTO WHERE upper(NOMPROD)  LIKE upper(?) AND ROWNUM <=10";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("PRODUCTO"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

}
