package com.pricing.dao;
import java.util.List;

import com.pricing.entidades.CResultado;

public interface CEntidadDAO
{
    public CResultado guardarEntidad();
    public CResultado eliminarEntidad();
   /* public int[] mantenimientoMasivo(String procedimiento,String[][] params);
    public List ejecutarProcedimiento(String procedimiento);*/
    public List ejecutarProcedimiento(String procedimiento,String[] values);
    /*public List ejecutarConsulta(String consulta);*/
}
