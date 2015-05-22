/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdm.tdm.doctores.pojos.DTO;

import java.util.List;


/**
 *
 * @author nicolas
 */
public class ListaEpisodiosDTO {
    
    private List<EpisodioDTO> episodios;
    
    public ListaEpisodiosDTO(){
        
    }
    
    public ListaEpisodiosDTO(List<EpisodioDTO> episodios){
        this.episodios = episodios;
    }

    /**
     * @return the episodios
     */
    public List<EpisodioDTO> getEpisodios() {
        return episodios;
    }

    /**
     * @param episodios the episodios to set
     */
    public void setEpisodios(List<EpisodioDTO> episodios) {
        this.episodios = episodios;
    }
    
    
    
    
}
