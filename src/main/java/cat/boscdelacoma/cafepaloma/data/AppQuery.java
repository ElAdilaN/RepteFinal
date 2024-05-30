/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.cafepaloma.data;

import cat.boscdelacoma.cafepaloma.model.Cafeteria;
import cat.boscdelacoma.cafepaloma.model.Worker;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 *
 * @author noureddin
 */
public class AppQuery {

    private DBConnection c = new DBConnection();

    public void updateProducts() {

        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("DELETE FROM Products");
            ps.executeUpdate();
            ps.close();

            java.sql.PreparedStatement ps1 = c.getCon().prepareStatement("INSERT INTO Products (Name, Price, Extra, Worker) "
                    + "SELECT Name, Price, Filling AS Extra, Worker FROM Entrepans");
            ps1.executeUpdate();
            ps1.close();

            java.sql.PreparedStatement ps2 = c.getCon().prepareStatement("INSERT INTO Products (Name, Price, Extra, Worker)  SELECT Name, Price, Size AS Extra, Worker FROM Cafeteria");
            ps2.executeUpdate();
            ps2.close();

            java.sql.PreparedStatement ps3 = c.getCon().prepareStatement("INSERT INTO Products (Name, Price, Extra, Worker) SELECT Name, Price, Flavor AS Extra, Worker FROM Refrescos");
            ps3.executeUpdate();
            ps3.close();

            c.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addRefrescos(cat.boscdelacoma.cafepaloma.model.Refrescos refresco) {
        try {
            this.updateProducts();
            c.getDBConn();
            PreparedStatement ps = c.getCon().prepareStatement("insert into refrescos(Name, Price, Flavor)values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, refresco.getName());
            ps.setDouble(2, refresco.getPrice());
            ps.setString(3, refresco.getFlavor());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int generatedId = rs.getInt(1); // Get the generated ID
                refresco.setId(generatedId); // Set the generated ID to the Refrescos object
            }
            ps.close();
            c.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<cat.boscdelacoma.cafepaloma.model.Refrescos> getRefrescosList() {
        ObservableList<cat.boscdelacoma.cafepaloma.model.Refrescos> refrescoList = FXCollections.observableArrayList();

        try {
            String query = "Select id , Name , Price , Flavor from Refrescos order by Name asc ";
            c.getDBConn();
            Statement st = c.getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            cat.boscdelacoma.cafepaloma.model.Refrescos s;
            while (rs.next()) {
                s = new cat.boscdelacoma.cafepaloma.model.Refrescos(rs.getInt("id"), rs.getString("Name"), Double.parseDouble(rs.getString("Price")), rs.getString("Flavor"));
                refrescoList.add(s);
            }

            rs.close();
            st.close();
            c.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return refrescoList;

    }

    public void UpdateRefrescos(cat.boscdelacoma.cafepaloma.model.Refrescos refresco) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("update `Refrescos` set `Name`=?, `Price` = ? , `Flavor`= ? where `id`= ? ");
            ps.setString(1, refresco.getName());
            ps.setDouble(2, refresco.getPrice());
            ps.setString(3, refresco.getFlavor());
            ps.setInt(4, refresco.getId());
            ps.execute();
            ps.close();
            c.closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void DeleteRefrescos(cat.boscdelacoma.cafepaloma.model.Refrescos refresco) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("delete from  `refrescos` where `id`= ? ");

            ps.setInt(1, refresco.getId());
            ps.execute();
            ps.close();
            c.closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /*      ****************************************************************/
    public void addEntrepans(cat.boscdelacoma.cafepaloma.model.Entrepans entrepan) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("insert into entrepans(Name, Price, Filling)values(?,?,?)");
            ps.setString(1, entrepan.getName());
            ps.setDouble(2, entrepan.getPrice());
            ps.setString(3, entrepan.getFilling());
            ps.execute();
            ps.close();
            c.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<cat.boscdelacoma.cafepaloma.model.Entrepans> getEntrepansList() {
        ObservableList<cat.boscdelacoma.cafepaloma.model.Entrepans> entrepanList = FXCollections.observableArrayList();

        try {
            String query = "Select id , Name , Price , Filling from Entrepans order by Name asc ";
            c.getDBConn();
            Statement st = c.getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            cat.boscdelacoma.cafepaloma.model.Entrepans s;
            while (rs.next()) {
                s = new cat.boscdelacoma.cafepaloma.model.Entrepans(rs.getInt("id"), rs.getString("Name"), Double.parseDouble(rs.getString("Price")), rs.getString("Filling"));

                entrepanList.add(s);
            }

            rs.close();
            st.close();
            c.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return entrepanList;

    }

    public void UpdateEntrepans(cat.boscdelacoma.cafepaloma.model.Entrepans entrepan) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("update `Entrepans` set `Name`=?, `Price` = ? , `Filling`= ? where `id`= ? ");
            ps.setString(1, entrepan.getName());
            ps.setDouble(2, entrepan.getPrice());
            ps.setString(3, entrepan.getFilling());
            ps.setInt(4, entrepan.getId());
            ps.execute();
            ps.close();
            c.closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void DeleteEntrepans(cat.boscdelacoma.cafepaloma.model.Entrepans entrepan) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("delete from  `entrepans` where `id`= ? ");

            ps.setInt(1, entrepan.getId());
            ps.execute();
            ps.close();
            c.closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /*      ****************************************************************/
    public void addCafeteria(cat.boscdelacoma.cafepaloma.model.Cafeteria cafeteria) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("insert into Cafeteria(Name, Price, Size)values(?,?,?)");
            ps.setString(1, cafeteria.getName());
            ps.setDouble(2, cafeteria.getPrice());
            ps.setString(3, cafeteria.getSize());
            ps.execute();
            ps.close();
            c.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Cafeteria> getCafeteriaList() {
        ObservableList<Cafeteria> cafeteriaList = FXCollections.observableArrayList();

        try {
            String query = "Select id , Name , Price , Size from Cafeteria order by Name asc ";
            c.getDBConn();
            Statement st = c.getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            cat.boscdelacoma.cafepaloma.model.Cafeteria s;
            while (rs.next()) {
                s = new Cafeteria(rs.getInt("id"), rs.getString("Name"), Double.parseDouble(rs.getString("Price")), rs.getString("Size"));

                cafeteriaList.add(s);
            }

            rs.close();
            st.close();
            c.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cafeteriaList;

    }

    public void UpdateCafeteria(cat.boscdelacoma.cafepaloma.model.Cafeteria cafateria) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("update `Cafeteria` set `Name`=?, `Price` = ? , `Size`= ? where `id`= ? ");
            ps.setString(1, cafateria.getName());
            ps.setDouble(2, cafateria.getPrice());
            ps.setString(3, cafateria.getSize());
            ps.setInt(4, cafateria.getId());
            ps.execute();
            ps.close();
            c.closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void DeleteCafeteria(cat.boscdelacoma.cafepaloma.model.Cafeteria cafateria) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("delete from  `cafeteria` where `id`= ? ");

            ps.setInt(1, cafateria.getId());
            ps.execute();
            ps.close();
            c.closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

//***************************************************************************************************************
    public void addWorker(cat.boscdelacoma.cafepaloma.model.Worker worker) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("insert into Workers(Name)values(?)");

            ps.setString(1, worker.getName());

            ps.execute();
            ps.close();
            c.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Worker> getWorkerList() {
        ObservableList<Worker> workerList = FXCollections.observableArrayList();

        try {
            String query = "Select id , Name from Workers order by Name asc ";
            c.getDBConn();
            Statement st = c.getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            cat.boscdelacoma.cafepaloma.model.Worker s;
            while (rs.next()) {
                s = new Worker(rs.getInt("id"), rs.getString("Name"));

                workerList.add(s);
            }

            rs.close();
            st.close();
            c.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workerList;

    }

    public void UpdateWorker(cat.boscdelacoma.cafepaloma.model.Worker worker) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("update `Workers` set `Id`=?, `Name` = ? ");
            ps.setInt(1, worker.getId());
            ps.setString(2, worker.getName());

            ps.execute();
            ps.close();
            c.closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void DeleteWorker(cat.boscdelacoma.cafepaloma.model.Worker worker) {
        try {
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("delete from  `workers` where `id`= ? ");

            ps.setInt(1, worker.getId());
            ps.execute();
            ps.close();
            c.closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

//the end hehe 
}
