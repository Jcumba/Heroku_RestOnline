package Dao;

import Interfaces.PersonalI;
import Modelo.PersonalM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import services.Encriptar;

public class PersonalD extends Dao implements PersonalI {

    @Override
    public void registrar(PersonalM per) throws Exception {
        try {
            this.Conexion();
            String sql = "insert into PERSONAL(CODPER,NOMPER,TDOCPER,DOCPER,SEXPER,FECNACPER,TELFPER,DIRPER,FOTOPER,TIPOPER,CONTPER,ESTPER)VALUES (SQ_CODPER.nextval,?,?,?,?,to_date(?,'dd/MM/yyyy'),?,?,?,?,?,?)";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, per.getNOM_PER());
            st.setString(2, per.getTIP_DOC_PER());
            st.setString(3, per.getDOCUMENTO_PER());
            st.setString(4, per.getSEXO_PER());
            st.setString(5, per.getFEC_NAC_PER());
            st.setString(6, per.getTELEFONO_PER());
            st.setString(7, per.getDIR_PER());
            st.setString(8, per.getFOTO_PER());
            st.setString(9, per.getTIP_PER());
            st.setString(10, Encriptar.encriptar(per.getCON_PER()));
            st.setString(11, "A");
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<PersonalM> listar() throws Exception {
        List<PersonalM> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT CODPER,NOMPER,TDOCPER,DOCPER,SEXPER,to_char( FECNACPER, 'DD/MM/YYYY' ) as FECNACPER,TELFPER,DIRPER,FOTOPER,TIPOPER,CONTPER,ESTPER FROM PERSONAL WHERE ESTPER like 'A'";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                PersonalM per = new PersonalM();
                per.setCOD_PER(rs.getString("CODPER"));
                per.setNOM_PER(rs.getString("NOMPER"));
                per.setTIP_DOC_PER(rs.getString("TDOCPER"));
                per.setDOCUMENTO_PER(rs.getString("DOCPER"));
                per.setSEXO_PER(rs.getString("SEXPER"));
                per.setFEC_NAC_PER(rs.getString("FECNACPER"));
                per.setTELEFONO_PER(rs.getString("TELFPER"));
                per.setDIR_PER(rs.getString("DIRPER"));
                per.setFOTO_PER(rs.getString("FOTOPER"));
                per.setTIP_PER(rs.getString("TIPOPER"));
                per.setCON_PER(rs.getString("CONTPER"));
                per.setESTADO(rs.getString("ESTPER"));
                lista.add(per);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    @Override
    public List<PersonalM> listarInactivos() throws Exception {
        List<PersonalM> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM PERSONAL WHERE ESTPER = 'I'";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                PersonalM per = new PersonalM();
                per.setCOD_PER(rs.getString(""));
                per.setNOM_PER(rs.getString(""));
                per.setTIP_DOC_PER(rs.getString(""));
                per.setDOCUMENTO_PER(rs.getString(""));
                per.setSEXO_PER(rs.getString(""));
                per.setFEC_NAC_PER(rs.getString(""));
                per.setTELEFONO_PER(rs.getString(""));
                per.setDIR_PER(rs.getString(""));
                per.setFOTO_PER(rs.getString(""));
                per.setTIP_PER(rs.getString(""));
                per.setCON_PER(rs.getString(""));
                per.setESTADO(rs.getString(""));
                lista.add(per);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    @Override
    public void modificar(PersonalM per) throws Exception {
        try {
            this.Conexion();
            String sql = "update PERSONAL set NOMPER=?,TDOCPER=?,DOCPER=?,SEXPER=?,FECNACPER= to_date(?,'dd/MM/yyyy'),TELFPER=?,DIRPER=?,FOTOPER=?,TIPOPER=?,ESTPER=? where CODPER=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, per.getNOM_PER());
            st.setString(2, per.getTIP_DOC_PER());
            st.setString(3, per.getDOCUMENTO_PER());
            st.setString(4, per.getSEXO_PER());
            st.setString(5, per.getFEC_NAC_PER());
            st.setString(6, per.getTELEFONO_PER());
            st.setString(7, per.getDIR_PER());
            st.setString(8, per.getFOTO_PER());
            st.setString(9, per.getTIP_PER());
            st.setString(10, per.getESTADO());
            st.setString(11, per.getCOD_PER());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(PersonalM per) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE PERSONAL SET ESTPER = 'I' WHERE CODPER = ?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, per.getCOD_PER());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public PersonalM startSession(String User, String Pass) throws Exception {
        this.Conexion();
        ResultSet rs;
        PersonalM personal = null;
        try {
            String sql = "Select DOCPER,NOMPER,TELFPER,TIPOPER from PERSONAL where DOCPER like ? and CONTPER like ? and ESTPER like 'A'";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, User);
            ps.setString(2, Pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                personal = new PersonalM();
                personal.setCOD_PER(rs.getString("DOCPER"));
                personal.setNOM_PER(rs.getString("NOMPER"));
                personal.setTELEFONO_PER(rs.getString("TELFPER"));
                personal.setTIP_PER(rs.getString("TIPOPER"));
                personal.setDOCUMENTO_PER(User);
                personal.setCON_PER(Pass);
            }
            return personal;
        } catch (SQLException e) {
            throw e;
        }
    }

}
