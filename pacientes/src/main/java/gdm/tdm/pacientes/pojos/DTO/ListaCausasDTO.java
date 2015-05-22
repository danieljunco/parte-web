/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdm.tdm.pacientes.pojos.DTO;

import java.util.List;

/**
 *
 * @author nicolas
 */
public class ListaCausasDTO {
    private List<RespuestaDTO> causas;
    
    public ListaCausasDTO(){
        
    }
    
    public ListaCausasDTO(List<RespuestaDTO> causas){
        this.causas = causas;
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
