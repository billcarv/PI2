package Aplicacao;

import Conexao.Conexao;
import Consultas.ConsultaCliente;
import Consultas.ConsultaEspecialidade2;
import Consultas.ConsultaMedico;
import Consultas.ConsultaUnidade;
import Relatorios.*;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * @author willian.carvalho
 */

public class Main extends javax.swing.JFrame {
    
    private static Main p;
    Conexao con = new Conexao();
    
    public static Main getInstancia(){
        if (p==null){
            p = new Main();
        }
        return p;
    }
    
    public static JPanel getPainel(){
        return getInstancia().painelPrincipal;
    }
    
    public Main() {
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelPrincipal = new javax.swing.JPanel();
        mnuMain = new javax.swing.JMenuBar();
        mnuMainCadastros = new javax.swing.JMenu();
        mnuMainCadastrosClientes = new javax.swing.JMenuItem();
        mnuMainCadastrosAgendamentos = new javax.swing.JMenuItem();
        mnuMainConsultas = new javax.swing.JMenu();
        mnuMainConsultasClientes = new javax.swing.JMenuItem();
        mnuMainConsultasMedicos = new javax.swing.JMenuItem();
        mnuMainConsultasEspecialidades = new javax.swing.JMenuItem();
        mnuMainConsultasUnidades = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
        );

        mnuMainCadastros.setText("Cadastros");

        mnuMainCadastrosClientes.setText("Clientes");
        mnuMainCadastrosClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMainCadastrosClientesActionPerformed(evt);
            }
        });
        mnuMainCadastros.add(mnuMainCadastrosClientes);

        mnuMainCadastrosAgendamentos.setText("Agendamentos");
        mnuMainCadastrosAgendamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMainCadastrosAgendamentosActionPerformed(evt);
            }
        });
        mnuMainCadastros.add(mnuMainCadastrosAgendamentos);

        mnuMain.add(mnuMainCadastros);

        mnuMainConsultas.setText("Consultas");

        mnuMainConsultasClientes.setText("Clientes");
        mnuMainConsultasClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMainConsultasClientesActionPerformed(evt);
            }
        });
        mnuMainConsultas.add(mnuMainConsultasClientes);

        mnuMainConsultasMedicos.setText("Medicos");
        mnuMainConsultasMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMainConsultasMedicosActionPerformed(evt);
            }
        });
        mnuMainConsultas.add(mnuMainConsultasMedicos);

        mnuMainConsultasEspecialidades.setText("Especialidades");
        mnuMainConsultasEspecialidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMainConsultasEspecialidadesActionPerformed(evt);
            }
        });
        mnuMainConsultas.add(mnuMainConsultasEspecialidades);

        mnuMainConsultasUnidades.setText("Unidades");
        mnuMainConsultasUnidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMainConsultasUnidadesActionPerformed(evt);
            }
        });
        mnuMainConsultas.add(mnuMainConsultasUnidades);

        mnuMain.add(mnuMainConsultas);

        jMenu3.setText("Relatórios");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem2.setText("Medicos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem1.setText("Clientes");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem3.setText("Agenda Global");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        mnuMain.add(jMenu3);

        setJMenuBar(mnuMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mnuMainCadastrosClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMainCadastrosClientesActionPerformed
        //mnuMain.setVisible(false);
        TelaCliente obj = new TelaCliente(con);
        painelPrincipal.add(obj);
        obj.setVisible(true);
       // centralizaForm(obj);
        try {
            obj.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }//GEN-LAST:event_mnuMainCadastrosClientesActionPerformed

    private void mnuMainCadastrosAgendamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMainCadastrosAgendamentosActionPerformed
        TelaAgendamento obj = new TelaAgendamento(con);
        painelPrincipal.add(obj);
        obj.setVisible(true);
       // centralizaForm(obj);
        try {
            obj.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuMainCadastrosAgendamentosActionPerformed

    private void mnuMainConsultasClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMainConsultasClientesActionPerformed
        ConsultaCliente obj = new ConsultaCliente(con);
        obj.setVisible(true);
    }//GEN-LAST:event_mnuMainConsultasClientesActionPerformed

    private void mnuMainConsultasMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMainConsultasMedicosActionPerformed
        ConsultaMedico obj = new ConsultaMedico(con);
        obj.setVisible(true);
    }//GEN-LAST:event_mnuMainConsultasMedicosActionPerformed

    private void mnuMainConsultasEspecialidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMainConsultasEspecialidadesActionPerformed
        ConsultaEspecialidade2 obj = new ConsultaEspecialidade2(con);
        obj.setVisible(true);
    }//GEN-LAST:event_mnuMainConsultasEspecialidadesActionPerformed

    private void mnuMainConsultasUnidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMainConsultasUnidadesActionPerformed
        ConsultaUnidade obj = new ConsultaUnidade(con);
        obj.setVisible(true);
    }//GEN-LAST:event_mnuMainConsultasUnidadesActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
       
       
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        RelMedicosParam obj = new RelMedicosParam(con);
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        RelClientesParam obj = new RelClientesParam(con);
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed

    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        RelAgendamentosParam obj = new RelAgendamentosParam(con);
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed


    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                getInstancia().setVisible(true);
                getInstancia().setExtendedState(getInstancia().getExtendedState()|JFrame.MAXIMIZED_BOTH);
            }
        });
    }
    
    private void centralizaForm(JInternalFrame frame) {
        Dimension desktopSize = painelPrincipal.getSize();
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuBar mnuMain;
    private javax.swing.JMenu mnuMainCadastros;
    private javax.swing.JMenuItem mnuMainCadastrosAgendamentos;
    private javax.swing.JMenuItem mnuMainCadastrosClientes;
    private javax.swing.JMenu mnuMainConsultas;
    private javax.swing.JMenuItem mnuMainConsultasClientes;
    private javax.swing.JMenuItem mnuMainConsultasEspecialidades;
    private javax.swing.JMenuItem mnuMainConsultasMedicos;
    private javax.swing.JMenuItem mnuMainConsultasUnidades;
    private javax.swing.JPanel painelPrincipal;
    // End of variables declaration//GEN-END:variables
}
