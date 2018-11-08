package Interfaces;

import Modelo.ReservacionesM;
import java.util.List;

public interface ReservacionesI {

    public void registrar(ReservacionesM reserv) throws Exception;

    List<ReservacionesM> listarReservaciones() throws Exception;


}
