/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdm.tdm.pacientes.pojos.DTO;

/**
 *
 * @author nicolas
 */
public class AlarmaDTO {
    private String nombre;
    
    private Long ocurrencias;
    
    public AlarmaDTO(){
        
    }
    
    public AlarmaDTO(String nombre, Long ocurrencias){
        this.nombre = nombre;
        this.ocurrencias = ocurrencias;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the ocurrencias
     */
    public Long getOcurrencias() {
        return ocurrencias;
    }

    /**
     * @param ocurrencias the ocurrencias to set
     */
    public void setOcurrencias(Long ocurrencias) {
        this.ocurrencias = ocurrencias;
    }
    
    
    
    
}
