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
public class ListaMedicamentosDTO {
    private List<RespuestaDTO> medicamentos;
    
    public ListaMedicamentosDTO(){
        
    }
    
    public ListaMedicamentosDTO(List<RespuestaDTO> medicamentos){
        this.medicamentos = medicamentos;
    }

    /**
     * @return the medicamentos
     */
    public List<RespuestaDTO> getMedicamentos() {
        return medicamentos;
    }

    /**
     * @param medicamentos the medicamentos to set
     */
    public void setMedicamentos(List<RespuestaDTO> medicamentos) {
        this.medicamentos = medicamentos;
    }
    
}
