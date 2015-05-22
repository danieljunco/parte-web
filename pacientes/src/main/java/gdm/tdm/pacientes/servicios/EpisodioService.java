/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdm.tdm.pacientes.servicios;

import gdm.tdm.pacientes.persistencia.PersistenceManager;
import gdm.tdm.pacientes.pojos.Episodio;
import gdm.tdm.pacientes.pojos.Paciente;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nicolas
 */
@Path("/paciente/{id}/episodio")
public class EpisodioService {
    
    @PersistenceContext(unitName = "PacientesPu")
    EntityManager entityManager;
    
    @PostConstruct
    public void init(){
        try{
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearEpisodio(@PathParam("id") String id,Episodio nuevo){
        try{
           Paciente p = new Paciente();
           p.setId(Long.parseLong(id));
           nuevo.setPaciente(p);
           entityManager.getTransaction().begin();
           entityManager.persist(nuevo);
           entityManager.getTransaction().commit();   
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
        }finally{
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("{\"id\":"+nuevo.getId()+"}").build();
    }
    
    @PUT
    @Path("{idEpisodio}/funciono")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarFunciono(@PathParam("idEpisodio") String idEpisodio,Episodio parametro){
        Episodio existente = entityManager.getReference(Episodio.class, Long.parseLong(idEpisodio));
        existente.setFunciono(parametro.isFunciono());
        entityManager.merge(existente);
        return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").build();        
    }
    
}
