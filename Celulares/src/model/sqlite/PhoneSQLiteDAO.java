package model.sqlite;

import model.Celulares;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneSQLiteDAO extends SQLitebase {

    public PhoneSQLiteDAO() {
        open();
        try {
            PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS veiculos (" +
                    "id INTEGER PRIMARY KEY AUTONCREMENT," +
                    "marca TEXT," +
                    "modelo TEXT,"  +
                    "hp INTEGER);");
            stm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void create (Celulares v) {
        open();
        try {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO Veiculos VALUES(?,?,?,?);");
            stm.setString(2, v.getMarca());
            stm.setString(3, v.getModelo());
            stm.setInt(4, v.getAno());

            stm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public List<Celulares> all (){
       ArrayList<Celulares> result = new ArrayList<>();
        open();
        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Veiculos ORDER BY id ASC;");
            ResultSet rs =  stm.executeQuery();

            while (rs.next()){
                Celulares v = new Celulares(
                    rs.getInt(1), //id
                    rs.getString(2), //marca
                    rs.getString(3), //modelo
                    rs.getInt(4) //hp
               );
              result.add(v);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }
    public void update (Celulares v) {
        conn = open();
        try {
            PreparedStatement stm = conn.prepareStatement("UPDATE Veiculos SET" + "" +
                    "marca = ?" +
                    "modelo = ?" +
                    "ano = ?" +
                    "WHERE id = ?;");
            stm.setString(1, v.getMarca());
            stm.setString(2, v.getModelo());
            stm.setInt(3, v.getAno());
            stm.setInt(4,  v.get_id()); //id

            stm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void delete (Celulares v ){
        conn = open();
        try {
            PreparedStatement stm = conn.
                    prepareStatement("DELETE FROM Celulares WHERE id = ?;");
            stm.setInt(1, v.get_id());
            stm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Celulares find(int pk) {
        Celulares result = null;
        conn = open();

        try {
            PreparedStatement stm = conn
                    .prepareStatement("SELECT * FROM Veiculos WHERE id = ?;");
            stm.setInt(1,pk);
            ResultSet rs = stm.executeQuery();

            if (rs.next()){
                Celulares v = new Celulares(
                        rs.getInt(1), //id
                        rs.getString(2), //marca
                        rs.getString(3), //modelo
                        rs.getInt(4) //celular
                );
                result = v;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

}
