/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdm.tdm.pacientes.pojos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

/**
 *
 * @author nicolas
 */
@Entity
public class Episodio implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    
    @ManyToOne
    private Paciente paciente;
    
    @ManyToMany
    private List<Causa> causas;
    
    private Timestamp fecha;
    
    @ManyToOne
    private Medicamento medicamento;
    
    private boolean funciono;
    
    private int intensidad;
    
    public Episodio(){
        
    }
    
    public Episodio(Paciente paciente, List<Causa> causas, Timestamp fecha, Medicamento medicamento, int intensidad){
        this.paciente = paciente;
        this.causas = causas;
        this.fecha = fecha;
        this.medicamento = medicamento;
        this.intensidad = intensidad;
    }
    
    @PrePersist
    private void definirFechaCreacion(){
        this.fecha = new Timestamp(new Date().getTime());
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the paciente
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * @param paciente the paciente to set
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * @return the causas
     */
    public List<Causa> getCausas() {
        return causas;
    }

    /**
     * @param causas the causas to set
     */
    public void setCausas(List<Causa> causas) {
        this.causas = causas;
    }

    /**
     * @return the fecha
     */
    public Timestamp getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the medicamento
     */
    public Medicamento getMedicamento() {
        return medicamento;
    }

    /**
     * @param medicamento the medicamento to set
     */
    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    /**
     * @return the funciono
     */
    public boolean isFunciono() {
        return funciono;
    }

    /**
     * @param funciono the funciono to set
     */
    public void setFunciono(boolean funciono) {
        this.funciono = funciono;
    }

    /**
     * @return the intensidad
     */
    public int getIntensidad() {
        return intensidad;
    }

    /**
     * @param intensidad the intensidad to set
     */
    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }
    
    
    
}
