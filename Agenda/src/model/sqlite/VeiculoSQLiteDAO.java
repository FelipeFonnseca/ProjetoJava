package model.sqlite;

import model.Veiculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoSQLiteDAO extends SQLitebase {

    public VeiculoSQLiteDAO() {
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

    public void create (Veiculo v) {
        open();
        try {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO Veiculos VALUES(?,?,?,?);");
            stm.setString(2, v.getMarca());
            stm.setString(3, v.getModelo());
            stm.setInt(4, v.getHp());

            stm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public List<Veiculo> all (){
       ArrayList<Veiculo> result = new ArrayList<>();
        open();
        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Veiculos ORDER BY id ASC;");
            ResultSet rs =  stm.executeQuery();

            while (rs.next()){
                Veiculo v = new Veiculo(
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
    public void update (Veiculo v) {
        conn = open();
        try {
            PreparedStatement stm = conn.prepareStatement("UPDATE Veiculos SET" + "" +
                    "marca = ?" +
                    "modelo = ?" +
                    "hp = ?" +
                    "WHERE id = ?;");
            stm.setString(1, v.getMarca());
            stm.setString(2, v.getModelo());
            stm.setInt(3, v.getHp());
            stm.setInt(4,  v.get_id()); //id

            stm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void delete (Veiculo v ){
        conn = open();
        try {
            PreparedStatement stm = conn.
                    prepareStatement("DELETE FROM Veiculos WHERE id = ?;");
            stm.setInt(1, v.get_id());
            stm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Veiculo find(int pk) {
        Veiculo result = null;
        conn = open();

        try {
            PreparedStatement stm = conn
                    .prepareStatement("SELECT * FROM Veiculos WHERE id = ?;");
            stm.setInt(1,pk);
            ResultSet rs = stm.executeQuery();

            if (rs.next()){
                Veiculo v = new Veiculo(
                        rs.getInt(1), //id
                        rs.getString(2), //marca
                        rs.getString(3), //modelo
                        rs.getInt(4) //hp
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
