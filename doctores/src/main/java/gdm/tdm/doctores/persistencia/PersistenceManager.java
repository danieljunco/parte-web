/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdm.tdm.doctores.persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nicolas
 */
public class PersistenceManager {
    
    public static final boolean DEBUG = true;
    private static final PersistenceManager singleton = new PersistenceManager();
    protected EntityManagerFactory emfDoctores;
    protected EntityManagerFactory emfPacientes;
 
    public static PersistenceManager getInstance() {
 
        return singleton;
    }
 
 
    private PersistenceManager() {
    }
 
    public EntityManagerFactory getEntityManagerFactory(String unidadPersistencia) {
        EntityManagerFactory respuesta = null;
        
        switch(unidadPersistencia){
            case "DoctoresPU":
                if(emfDoctores == null){
                    createEntityManagerFactory("DoctoresPU");
                }
                respuesta = emfDoctores;
            break;
            case "PacientesPU":
                if(emfPacientes == null){
                    createEntityManagerFactory("PacientesPU");
                }
                respuesta = emfPacientes;
            break;
        }
        return respuesta;
    }
 
    public void closeEntityManagerFactory(String unidadPersistencia) {
        switch(unidadPersistencia){
            case "DoctoresPU":
                if(emfDoctores != null){
                    emfDoctores.close();
                    emfDoctores = null;
                    if(DEBUG){
                        System.out.println("Persistence finished at "+ new java.util.Date());
                    }
                }
            break;
            case "PacientesPU":
                if(emfPacientes != null){
                    emfPacientes.close();
                    emfPacientes = null;
                    if(DEBUG){
                        System.out.println("Persistence finished at "+ new java.util.Date());
                    }
                }
            break;
        }
    }
 
    protected void createEntityManagerFactory(String unidadPersistencia) {
        switch(unidadPersistencia){
            case "DoctoresPU":
                this.emfDoctores = Persistence.createEntityManagerFactory(unidadPersistencia, System.getProperties());
            break;
            case "PacientesPU":
                this.emfPacientes = Persistence.createEntityManagerFactory(unidadPersistencia, System.getProperties());
            break;
        }
        if (DEBUG) {
            System.out.println("Persistence started at " + new java.util.Date());
        }
    }
    
}
