/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Acer
 */
public class Cliente extends Pessoa{
    
    private String cpf;
    private String rg;
    private String cnpj;
    private String sexo;
    private Date data_nascimento;
    
    public Cliente(){
    }

    public Cliente(String cpf, String rg, String cnpj, Date data_nascimento, int id, 
            String nome, String email, String telefone, String celular,String sexo, Endereco endereco) {
        super(id, nome, email, telefone, celular, endereco);
        this.cpf = cpf;
        this.rg = rg;
        this.cnpj = cnpj;
        this.data_nascimento = data_nascimento;
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
}
