
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LabingXEON
 */
public class EjemploConexionMySQL {

    public static void main(String[] args) {
        String consulta = null;

        try {
            //1. Cargar el driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver cargado");
            //2. Establecer la conexion
            Connection conexion = null;
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "");
            if (conexion != null) {
                System.out.println("Conexion exitosa");
            } else {
                System.out.println("Revisar la url de conexion,usuario");
            }
            //3.Paso Ejecutar una operacion de Seleccion
            consulta = "SELECT   products.productName,customers.customerName FROM customers,products,orders,orderdetails WHERE products.productCode = orderdetails.productCode and orderdetails.orderNumber = orders.orderNumber and customers.customerNumber= orders.customerNumber";
            Statement st = conexion.createStatement();
            //Si la consulta me retorna resultados
            ResultSet rs = st.executeQuery(consulta);
            //Si la consulta es de inserte, update o delte 
            //int rsi=st.executeUpdate(consulta)
            while(rs.next()){
                System.out.println(rs.getString("customerName"));
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no encontrada");
        } catch (InstantiationException ex) {
            System.out.println("No se puede crear la insatancia");
        } catch (IllegalAccessException ex) {
            System.out.println("No tiene acceso al driver");
        } catch (SQLException ex) {
            System.out.println("Connection Failed");
        }
    }

}
