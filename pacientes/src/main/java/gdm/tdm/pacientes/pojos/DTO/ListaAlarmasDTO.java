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
public class ListaAlarmasDTO {
    private List<AlarmaDTO> alarmas;
    
    public ListaAlarmasDTO(){
        
    }
    
    public ListaAlarmasDTO(List<AlarmaDTO> alarmas){
        this.alarmas = alarmas;
    }

    /**
     * @return the alarmas
     */
    public List<AlarmaDTO> getAlarmas() {
        return alarmas;
    }

    /**
     * @param alarmas the alarmas to set
     */
    public void setAlarmas(List<AlarmaDTO> alarmas) {
        this.alarmas = alarmas;
    }
    
    
}
