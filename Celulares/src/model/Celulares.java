package model;

import model.sqlite.PhoneSQLiteDAO;

import java.util.List;

public class Celulares {

    private Integer _id;
    private String marca;
    private String modelo;
    private int ano;

    public Celulares(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public Celulares(int _id, String marca, String modelo, int ano) {
        this._id = _id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Celular("+marca+"\t"+modelo+"\t"+ ano +") ["+ _id +"]";
    }

    /** DAO */
    private static PhoneSQLiteDAO dao = new PhoneSQLiteDAO();

    public void save() {
        if (_id != null && dao.find(_id) != null){
            dao.update(this);
        } else dao.create(this);
    }

    public void delete() {
        if (dao.find(_id) != null){
            dao.delete(this);
        }
    }

    public static List<Celulares> all() {
        return dao.all();
    }

    public static Celulares fing(int pk){
        return dao.find(pk);
    }

}
