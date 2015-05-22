package gdm.tdm.doctores.servicios;

import gdm.tdm.doctores.persistencia.PersistenceManager;
import gdm.tdm.doctores.pojos.Doctor;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "paciente" path)
 */
@Path("/doctor")
public class DoctorService {

    @PersistenceContext(unitName = "DoctoresPu")
    EntityManager entityManager;
    
    @PostConstruct
    public void init(){
        try{
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory("DoctoresPU").createEntityManager();
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
        return "Hello, desde DoctorLandia";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String crearPaciente(Doctor nuevo){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(nuevo);
            entityManager.getTransaction().commit();
            entityManager.refresh(nuevo);
            
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
        return "{\"id\":"+nuevo.getId()+"}";
    }
}
