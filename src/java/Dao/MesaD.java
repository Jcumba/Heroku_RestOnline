package Dao;

import Interfaces.MesaI;
import Modelo.MesaM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesaD extends Dao implements MesaI {

    @Override
    public void registrar(MesaM mesa) throws Exception {
        try {
            this.Conexion();
            String sql = "insert into MESA(CODMESA,NUMMESA,CANTSILLAMESA,ESTMESA) values (SQ_CODMESA.nextval,?,?,?)";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, mesa.getNUM_MESA());
            st.setString(2, mesa.getCANT_SILLAS_MESA());
            st.setString(3, "A");
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<MesaM> listar() throws Exception {
        List<MesaM> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT CODMESA,NUMMESA,CANTSILLAMESA,ESTMESA FROM MESA WHERE ESTMESA like 'A'";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                MesaM mesa = new MesaM();
                mesa.setCOD_MESA(rs.getString("CODMESA"));
                mesa.setNUM_MESA(rs.getString("NUMMESA"));
                mesa.setCANT_SILLAS_MESA(rs.getString("CANTSILLAMESA"));
                mesa.setESTADO(rs.getString("ESTMESA"));
                lista.add(mesa);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    @Override
    public List<MesaM> listarInactivos() throws Exception {
        List<MesaM> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM MESA WHERE ESTMESA = 'I'";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                MesaM mesa = new MesaM();
                mesa.setNUM_MESA(rs.getString("NUMMESA"));
                mesa.setCANT_SILLAS_MESA(rs.getString("CANTSILLAMESA"));
                mesa.setESTADO(rs.getString("ESTMESA"));
                lista.add(mesa);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

   

    @Override
    public void modificar(MesaM dat) throws Exception {
        try {
            this.Conexion();
            String sql = "update MESA set NUMMESA=?, CANTSILLAMESA=?, ESTMESA=? where CODMESA=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, dat.getNUM_MESA());
            st.setString(2, dat.getCANT_SILLAS_MESA());
            st.setString(3, dat.getESTADO());
            st.setString(4, dat.getCOD_MESA());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
    
     public String obtenerCodigoMesa(String Mesa) throws SQLException {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "select CODMESA from MESA where UPPER(NUMMESA||' '||CANTSILLAMESA) like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, Mesa);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODMESA");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

   

}
