/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdm.tdm.pacientes.servicios;

import gdm.tdm.pacientes.persistencia.PersistenceManager;
import gdm.tdm.pacientes.pojos.DTO.ListaMedicamentosDTO;
import gdm.tdm.pacientes.pojos.DTO.RespuestaDTO;
import gdm.tdm.pacientes.pojos.Medicamento;
import gdm.tdm.pacientes.pojos.Paciente;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nicolas
 */
@Path("/paciente/{id}/medicamento")
public class MedicamentoService {
    
    @PersistenceContext(unitName = "PacientesPu")
    EntityManager entityManager;
    
    @PostConstruct
    public void init(){
        try{
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        }catch(Exception e){
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response darCausasPaciente(@PathParam("id") String id){
        Response r;
        try{
            Long pacienteID = Long.parseLong(id);
            Query query = entityManager.createNativeQuery("select id, nombre from medicamento where paciente_id = ?1").setParameter(1, pacienteID);
            List<Object[]> rawPaciente = query.getResultList();
            List<RespuestaDTO> causasPaciente = new LinkedList<>();
            for(Object[] f: rawPaciente){
                RespuestaDTO resDTO = new RespuestaDTO();
                resDTO.setId((Long)f[0]);
                resDTO.setNombre((String)f[1]);
                causasPaciente.add(resDTO);
            }
            ListaMedicamentosDTO respuesta = new ListaMedicamentosDTO(causasPaciente);
            r = Response.status(200).header("Access-Control-Allow-Origin", "*").entity(respuesta).build();
        }catch(Exception e){
            r= Response.status(400).build();
        }
        return r;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response nuevaCausa(@PathParam("id") String id,Medicamento nuevo){
        try{
            Paciente p = new Paciente();
            p.setId(Long.parseLong(id));
            nuevo.setPaciente(p);
            
            entityManager.getTransaction().begin();
            entityManager.persist(nuevo);
            entityManager.getTransaction().commit();
            
        }catch(Throwable t){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            nuevo = null;
        }finally{
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("{\"id\":"+nuevo.getId()+"}").build();
    }
}
