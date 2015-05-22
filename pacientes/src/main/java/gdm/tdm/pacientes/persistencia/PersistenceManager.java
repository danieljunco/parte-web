/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdm.tdm.pacientes.persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nicolas
 */
public class PersistenceManager {
    
    public static final boolean DEBUG = true;
    private static final PersistenceManager singleton = new PersistenceManager();
    protected EntityManagerFactory emf;
 
    public static PersistenceManager getInstance() {
 
        return singleton;
    }
 
 
    private PersistenceManager() {
    }
 
    public EntityManagerFactory getEntityManagerFactory() {
 
        if (emf == null) {
            createEntityManagerFactory();
        }
        return emf;
    }
 
    public void closeEntityManagerFactory() {
 
        if (emf != null) {
            emf.close();
            emf = null;
            if (DEBUG) {
                System.out.println("Persistence finished at " + new java.util.Date());
            }
        }
    }
 
    protected void createEntityManagerFactory() {
        this.emf = Persistence.createEntityManagerFactory("PacientesPU", System.getProperties());
        if (DEBUG) {
            System.out.println("Persistence started at " + new java.util.Date());
        }
    }
    
}
