/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdm.tdm.doctores.servicios;

import gdm.tdm.doctores.persistencia.PersistenceManager;
import gdm.tdm.doctores.pojos.Causa;
import gdm.tdm.doctores.pojos.DTO.EpisodioDTO;
import gdm.tdm.doctores.pojos.DTO.ListaEpisodiosDTO;
import gdm.tdm.doctores.pojos.DTO.RespuestaDTO;
import gdm.tdm.doctores.pojos.Episodio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("/paciente")
public class PacienteService {
    
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
    @Path("{id}/episodio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response darEpisodiosPaciente(@PathParam("id") String id){
        
        Query q = entityManager.createQuery("select u from Episodio u where u.paciente.id = ?1 ");
        q.setParameter(1, Long.parseLong(id));
        List<Episodio> episodios = q.getResultList();
        System.out.println(episodios.size());
        List<EpisodioDTO> episodiosDTO = new LinkedList<>();
        for(Episodio original: episodios){
            EpisodioDTO dto = new EpisodioDTO();
            dto.setFunciono(original.isFunciono());
            dto.setFecha(original.getFecha());
            dto.setMedicamento(original.getMedicamento().getNombre());
            dto.setIntensidad(original.getIntensidad());
            List<RespuestaDTO> listaCausasDTO = new LinkedList<>();
            for(Causa causaOriginal: original.getCausas()){
                RespuestaDTO causaDTO = new RespuestaDTO();
                causaDTO.setId(causaOriginal.getId());
                causaDTO.setNombre(causaOriginal.getNombre());
                listaCausasDTO.add(causaDTO);
            }
            dto.setCausas(listaCausasDTO);
            episodiosDTO.add(dto);
        }
        return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS").header("Access-Control-Allow-Headers", "Content-Type, Content-Range, Content-Disposition, Content-Description").entity(new ListaEpisodiosDTO(episodiosDTO)).build();
    }
    
    @GET
    @Path("{id}/episodio/{inicio}/{fin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response darEpisodiosPorFechaPaciente(@PathParam("id") String id, @PathParam("inicio") String inicio, @PathParam("fin")String fin){
        
        try {
            Date fechaInicio = new SimpleDateFormat("dd-MM-yyyy").parse(inicio);
            Date fechaFin = new SimpleDateFormat("dd-MM-YYYY").parse(fin);
            Query q = entityManager.createQuery("select e from Episodio e where e.fecha BETWEEN :start AND :end");
            q.setParameter("start", fechaInicio);
            q.setParameter("ent", fechaFin);
            List<Episodio> episodios = q.getResultList();
            List<EpisodioDTO> episodiosDTO = new LinkedList<>();
            for(Episodio original: episodios){
                EpisodioDTO dto = new EpisodioDTO();
                dto.setFunciono(original.isFunciono());
                dto.setFecha(original.getFecha());
                dto.setMedicamento(original.getMedicamento().getNombre());
                dto.setIntensidad(original.getIntensidad());
                List<RespuestaDTO> listaCausasDTO = new LinkedList<>();
                for(Causa causaOriginal: original.getCausas()){
                    RespuestaDTO causaDTO = new RespuestaDTO();
                    causaDTO.setId(causaOriginal.getId());
                    causaDTO.setNombre(causaOriginal.getNombre());
                    listaCausasDTO.add(causaDTO);
                }
                dto.setCausas(listaCausasDTO);
                episodiosDTO.add(dto);
            }
            return Response.status(Response.Status.OK).entity(new ListaEpisodiosDTO(episodiosDTO)).build();
        } catch (ParseException ex) {
            Logger.getLogger(PacienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(500).build();
    }
}
