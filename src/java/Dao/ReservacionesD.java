package Dao;

import Interfaces.ReservacionesI;
import Modelo.ReservacionesM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservacionesD extends Dao implements ReservacionesI {

    @Override
    public void registrar(ReservacionesM per) throws Exception {
        try {
            this.Conexion();
            String sql = "INSERT INTO RESERVACION (CODCLIE,FECRES,HORRES,CODPROD,CANTPROD,ESTRES,CODMESA)VALUES(?,TO_DATE(?,'DD/MM/YYYY'),?,?,?,?,?)";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, per.getCODCLIE());
            st.setString(2, per.getFECRES());
            st.setString(3, per.getHORRES());
            st.setString(4, per.getCODPROD());
            st.setString(5, per.getCANTPROD());
            st.setString(6, "A");
            st.setString(7, per.getCODMESA());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<ReservacionesM> listarReservaciones() throws Exception {
        List<ReservacionesM> list;
        ResultSet rs;
        this.Conexion();
        try {
            String sql = "select * from VW_RESERVACION";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            ReservacionesM reserva;
            while (rs.next()) {
                reserva = new ReservacionesM();
                reserva.setCODRES(rs.getString("CODRES"));
                reserva.setCLIENTE(rs.getString("CLIENTE"));
                reserva.setFECRES(rs.getString("FECRES"));
                reserva.setHORRES(rs.getString("HORRES"));
                reserva.setMESA(rs.getString("MESA"));
                reserva.setPRODUCTO(rs.getString("PRODUCTO"));
                reserva.setPRECIOPROD(rs.getString("PRECIOPROD"));
                reserva.setOFERTA(rs.getString("OFERTA"));
                reserva.setCANTPROD(rs.getString("CANTPROD"));
                reserva.setESTRES(rs.getString("ESTRES"));
                list.add(reserva);
            }
            return list;
        } catch (SQLException e) {
            throw e;
        }
    }
}
