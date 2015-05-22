/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdm.tdm.doctores.pojos.DTO;

import java.util.Date;
import java.util.List;

/**
 *
 * @author nicolas
 */
public class EpisodioDTO {
    
    private Date fecha;
    
    private int intensidad;
    
    private boolean funciono;
    
    private String medicamento;
    
    private List<RespuestaDTO> causas;
    
    public EpisodioDTO(){
        
    }
    
    public EpisodioDTO(Date fecha, int intensidad, boolean funciono, String medicamento, List<RespuestaDTO> causas){
        this.fecha = fecha;
        this.intensidad = intensidad;
        this.funciono = funciono;
        this.medicamento = medicamento;
        this.causas = causas;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the intensidad
     */
    public int getIntensidad() {
        return intensidad;
    }

    /**
     * @param intensidad the intensidad to set
     */
    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }

    /**
     * @return the funciono
     */
    public boolean isFunciono() {
        return funciono;
    }

    /**
     * @param funciono the funciono to set
     */
    public void setFunciono(boolean funciono) {
        this.funciono = funciono;
    }

    /**
     * @return the medicamento
     */
    public String getMedicamento() {
        return medicamento;
    }

    /**
     * @param medicamento the medicamento to set
     */
    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    /**
     * @return the causas
     */
    public List<RespuestaDTO> getCausas() {
        return causas;
    }

    /**
     * @param causas the causas to set
     */
    public void setCausas(List<RespuestaDTO> causas) {
        this.causas = causas;
    }
    
    
}
