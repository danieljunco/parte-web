package gdm.tdm.pacientes.servicios;

import gdm.tdm.pacientes.persistencia.PersistenceManager;
import gdm.tdm.pacientes.pojos.DTO.AlarmaDTO;
import gdm.tdm.pacientes.pojos.DTO.ListaAlarmasDTO;
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
 * Root resource (exposed at "paciente" path)
 */
@Path("/paciente/")
public class PacienteService {

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
    
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, desde Paciente";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearPaciente(Paciente nuevo){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(nuevo);
            entityManager.getTransaction().commit();
            
        }catch(Throwable t){
            t.printStackTrace();
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            nuevo = null;
        }finally{
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS").header("Access-Control-Allow-Headers", "Content-Type, Content-Range, Content-Disposition, Content-Description").entity("{\"id\":"+nuevo.getId()+"}").build();
    }
    
    @GET
    @Path("{id}/causas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response darCausas(@PathParam("id") String id){
        String query = "select c.nombre, COUNT(c.id) from episodio_causa ec inner join causa c on ec.causas_id = c.id GROUP BY c.id HAVING c.paciente_id = ?1";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter(1, Long.parseLong(id));
        List<Object[]> resultados = q.getResultList();
        List<AlarmaDTO> listaAlarmas = new LinkedList<>();
        for(Object[] l : resultados){
            AlarmaDTO dto = new AlarmaDTO((String)l[0], (Long)l[1]);
            listaAlarmas.add(dto);
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS").header("Access-Control-Allow-Headers", "Content-Type, Content-Range, Content-Disposition, Content-Description").entity(new ListaAlarmasDTO(listaAlarmas)).build();
    }
}
