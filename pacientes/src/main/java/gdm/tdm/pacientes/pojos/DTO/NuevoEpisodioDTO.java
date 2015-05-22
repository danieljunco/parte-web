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
public class NuevoEpisodioDTO {
 
    private List<String> listaCausas;
    
    private String medicamento;
    
    public NuevoEpisodioDTO(){
        
    }

    /**
     * @return the listaCausas
     */
    public List<String> getListaCausas() {
        return listaCausas;
    }

    /**
     * @param listaCausas the listaCausas to set
     */
    public void setListaCausas(List<String> listaCausas) {
        this.listaCausas = listaCausas;
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
    
    
}
