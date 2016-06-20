
package Model;

import java.util.Date;

/**
 * @author willian.carvalho
 */

public class Agendamento {

    private Integer codigo;
    private Date dtHrInicio;
    private Date dtHrFim;
    private String reservado;
    private String espera;
    private String deletado;

    public Agendamento() {
    }

    public Agendamento(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getDtHrInicio() {
        return dtHrInicio;
    }

    public void setDtHrInicio(Date dtHrInicio) {
        this.dtHrInicio = dtHrInicio;
    }

    public Date getDtHrFim() {
        return dtHrFim;
    }

    public void setDtHrFim(Date dtHrFim) {
        this.dtHrFim = dtHrFim;
    }

    public String getReservado() {
        return reservado;
    }

    public void setReservado(String reservado) {
        this.reservado = reservado;
    }

    public String getEspera() {
        return espera;
    }

    public void setEspera(String espera) {
        this.espera = espera;
    }

    public String getDeletado() {
        return deletado;
    }

    public void setDeletado(String deletado) {
        this.deletado = deletado;
    }

}
