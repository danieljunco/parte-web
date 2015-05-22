/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdm.tdm.doctores.servicios;

import gdm.tdm.doctores.persistencia.PersistenceManager;
import gdm.tdm.doctores.pojos.Causa;
import gdm.tdm.doctores.pojos.DTO.EpisodioDTO;
import gdm.tdm.doctores.pojos.DTO.RespuestaDTO;
import gdm.tdm.doctores.pojos.Episodio;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nicolas
 */
@Path("/episodio")
public class EpisodioService {
    
    @PersistenceContext(unitName = "DoctoresPu")
    EntityManager entityManager;
    
    @PostConstruct
    public void init(){
        try{
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory("PacientesPU").createEntityManager();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @GET
    @Path("{idEpisodio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response darEpisodioPorId(@PathParam("idEpisodio") String idEpisodio){
        Query q = entityManager.createQuery("select e from Episodio e where e.id = :id");
        q.setParameter("id", Long.parseLong(idEpisodio));
        Episodio episodioRespuesta = (Episodio)q.getResultList().get(0);
        EpisodioDTO dto = new EpisodioDTO();
        dto.setFecha(episodioRespuesta.getFecha());
        dto.setMedicamento(episodioRespuesta.getMedicamento().getNombre());
        dto.setFunciono(episodioRespuesta.isFunciono());
        dto.setIntensidad(episodioRespuesta.getIntensidad());
        List<RespuestaDTO> causas = new LinkedList<>();
        for(Causa c: episodioRespuesta.getCausas()){
            RespuestaDTO causaDto = new RespuestaDTO();
            causaDto.setId(c.getId());
            causaDto.setNombre(c.getNombre());
            causas.add(causaDto);
        }
        dto.setCausas(causas);
        
        return Response.status(200).entity(dto).build();
        
    }
}
