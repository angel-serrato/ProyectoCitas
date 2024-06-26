package Modelo;

import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;

public class GestorPaciente {

    private static LinkedList<Paciente> pacientes;
    private static Connection conn;

    public GestorPaciente() {
        Recursos.Conexion conexion = new Recursos.Conexion();
        conn = conexion.getConexion();
        pacientes = new LinkedList<Paciente>();
    }

//    public void RegistrarPacientes(Paciente paciente) {
//        PreparedStatement pst;
//        try {
//            pst = conn.prepareStatement("insert into PACIENTE values(?,?,?,?,?)");
//            pst.setString(1, paciente.getIdentificacion());
//            pst.setString(2, paciente.getNombres());
//            pst.setString(3, paciente.getApellidos());
//            pst.setString(4, paciente.getFechaNacimiento());
//            pst.setString(5, paciente.getGenero());
//            pst.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Paciente Registrado");
//        } catch (SQLException ex) {
//            Logger.getLogger(GestorPaciente.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        pacientes.add(paciente);
//    }

    public void RegistrarPacientes(Paciente paciente) {
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement("insert into PACIENTE values(?,?,?,?,?)");
            pst.setString(1, paciente.getIdentificacion());
            pst.setString(2, paciente.getNombres());
            pst.setString(3, paciente.getApellidos());

            // Formatear la fecha en el formato correcto (YYYY-MM-DD HH:MM:SS)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(paciente.getFechaNacimiento());

            pst.setString(4, formattedDate);
            pst.setString(5, paciente.getGenero());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente Registrado");
        } catch (SQLException ex) {
            Logger.getLogger(GestorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LinkedList<Paciente> getPacientebyParametro(int parametro, String valor) {
        LinkedList<Paciente> resultado = new LinkedList<Paciente>();
        String sql = "";
        //for(Paciente pac:pacientes)
        //{
        switch (parametro) {
            case 1: //if(pac.getIdentificacion().equals(valor))
                sql = "select * from pacientes where PACIDENTIFICACION =’" + valor + "’";
                //resultado.add(pac);
                break;
            case 2: //if(pac.getNombres().equals(valor))
                sql = "select * from pacientes where PACNOMBRES =’" + valor + "’";
                //resultado.add(pac);
                break;
            case 3: //if(pac.getApellidos().equals(valor))
                sql = "select * from pacientes where PACAPELLIDOS =’" + valor + "’";
                //resultado.add(pac);
                break;
            case 4: //if(pac.getGenero().equals(valor))
                sql = "select * from pacientes where PACGENERO =’" + valor + "’";
                //resultado.add(pac);
                break;
        }
        //}
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                resultado.add(new Paciente(rs.getString("PACIDENTIFICACION"), rs.getString("PACNOMBRES"), rs.getString("PACAPELLIDOS"), rs.getString("PACFECHANACIMIENTO"), rs.getString("PACGENERO"))
                // ,rs.getString("PACGENERO") esto se puede agregar a la linea 65
                );
            }
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return resultado;
    }
}
