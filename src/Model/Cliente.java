
package Model;

import java.sql.Date;

/**
 * @author willian.carvalho
 */
public class Cliente {
    private Integer codigo;
    private String nome;
    private Integer telefone;
    private Integer celular;
    private Date cadastro;
    private String ativo;
    private String deletado;
    private Convenio convenioCodigo;

    public Cliente() {
    }

    public Cliente(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getDeletado() {
        return deletado;
    }

    public void setDeletado(String deletado) {
        this.deletado = deletado;
    }

    public Convenio getConvenioCodigo() {
        return convenioCodigo;
    }

    public void setConvenioCodigo(Convenio convenioCodigo) {
        this.convenioCodigo = convenioCodigo;
    }
}
