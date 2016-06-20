package Aplicacao;

/**
 * @author willian.carvalho
 */

import Conexao.Conexao;
import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.table.*;

public class TelaCliente extends javax.swing.JInternalFrame {

    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private Conexao con;
    
    public TelaCliente(Conexao conect) {
        con = conect;
        criaJTablet();
        initComponents();
        jScrollPane1.setViewportView(tabela);
    }
 

    private void criaJTablet(){
        tabela = new JTable(modelo);
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Telefone");
        modelo.addColumn("Celular");
        modelo.addColumn("Cadastro");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(15);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(40);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(25);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(25);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(20); 


        try {
            con.conectar();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        pesquisarNomes("SELECT codigo,nome,cpf,telefone,celular,cadastro FROM "
                + "cliente where deletado is null");
    }

    
    private void pesquisarNomes(String sql){
        DecimalFormat ze = new DecimalFormat("000000");        
        modelo.setNumRows(0);
        con.executeSQL(sql);
        try {
            con.resultset.first();
            do{
                modelo.addRow(new Object [] {
                ze.format(con.resultset.getObject("codigo")),
                con.resultset.getObject("nome"),
                con.resultset.getObject("cpf"),
                con.resultset.getObject("telefone"),
                con.resultset.getObject("celular"),
                con.resultset.getObject("cadastro")});
            }while(con.resultset.next());
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }   
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblPesquisaCliente = new javax.swing.JLabel();
        cmbPesquisaCliente = new javax.swing.JComboBox<>();
        txtPesquisaCliente = new javax.swing.JTextField();
        btnPesquisaCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mnuClientes = new javax.swing.JMenuBar();
        mnuClientesIncluir = new javax.swing.JMenu();
        mnuClientesPesquisar = new javax.swing.JMenu();
        mnuClientesVisualizar = new javax.swing.JMenu();
        mnuClientesAlterar = new javax.swing.JMenu();
        mnuClientesExcluir = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        mnuClientesSair = new javax.swing.JMenu();

        setBorder(null);
        setTitle("Cadastro de Clientes");

        lblPesquisaCliente.setText("Tipo");
        lblPesquisaCliente.setEnabled(true);
        lblPesquisaCliente.setVisible(false);
        lblPesquisaCliente.setInheritsPopupMenu(false);

        cmbPesquisaCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CPF", "Nome" }));
        cmbPesquisaCliente.setVisible(false);

        txtPesquisaCliente.setVisible(false);

        btnPesquisaCliente.setText("Pesquisar");
        btnPesquisaCliente.setVisible(false);
        btnPesquisaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPesquisaClienteMouseClicked(evt);
            }
        });
        btnPesquisaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(460, Short.MAX_VALUE)
                .addComponent(lblPesquisaCliente)
                .addGap(18, 18, 18)
                .addComponent(cmbPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPesquisaCliente)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisaCliente)
                    .addComponent(txtPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPesquisaCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE))
        );

        mnuClientesIncluir.setText("Incluir");
        mnuClientesIncluir.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                mnuClientesIncluirMenuSelected(evt);
            }
        });
        mnuClientes.add(mnuClientesIncluir);

        mnuClientesPesquisar.setText("Pesquisar");
        mnuClientesPesquisar.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                mnuClientesPesquisarMenuSelected(evt);
            }
        });
        mnuClientesPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuClientesPesquisarActionPerformed(evt);
            }
        });
        mnuClientes.add(mnuClientesPesquisar);

        mnuClientesVisualizar.setText("Visualizar");
        mnuClientesVisualizar.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                mnuClientesVisualizarMenuSelected(evt);
            }
        });
        mnuClientesVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuClientesVisualizarActionPerformed(evt);
            }
        });
        mnuClientes.add(mnuClientesVisualizar);

        mnuClientesAlterar.setText("Alterar");
        mnuClientesAlterar.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                mnuClientesAlterarMenuSelected(evt);
            }
        });
        mnuClientes.add(mnuClientesAlterar);

        mnuClientesExcluir.setText("Excluir");
        mnuClientesExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuClientesExcluirMouseClicked(evt);
            }
        });
        mnuClientes.add(mnuClientesExcluir);
        mnuClientes.add(jMenu5);

        jMenu7.setText("    ");
        jMenu7.setEnabled(false);
        mnuClientes.add(jMenu7);

        mnuClientesSair.setText("Sair");
        mnuClientesSair.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                mnuClientesSairMenuSelected(evt);
            }
        });
        mnuClientes.add(mnuClientesSair);

        setJMenuBar(mnuClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void mnuClientesPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuClientesPesquisarActionPerformed

    }//GEN-LAST:event_mnuClientesPesquisarActionPerformed

    private void mnuClientesPesquisarMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_mnuClientesPesquisarMenuSelected
        habilitaPesquisa();
    }//GEN-LAST:event_mnuClientesPesquisarMenuSelected

    private void btnPesquisaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesquisaClienteMouseClicked
        desabilitaPesquisa();
    }//GEN-LAST:event_btnPesquisaClienteMouseClicked

    private void mnuClientesIncluirMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_mnuClientesIncluirMenuSelected
        Cliente janela = new Cliente(null,1);
        Main.getPainel().add(janela);
        janela.setVisible(true);
        //this.hide();
        centralizaForm(janela);        
    }//GEN-LAST:event_mnuClientesIncluirMenuSelected

    private void mnuClientesSairMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_mnuClientesSairMenuSelected
        this.dispose();
    }//GEN-LAST:event_mnuClientesSairMenuSelected

    private void mnuClientesVisualizarMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_mnuClientesVisualizarMenuSelected
        int x = tabela.getSelectedRow();
        Object codigo = modelo.getValueAt(x,0);
        Cliente janela = new Cliente(codigo,2);
        Main.getPainel().add(janela);
        janela.setVisible(true);
        //this.hide();
        centralizaForm(janela);
    }//GEN-LAST:event_mnuClientesVisualizarMenuSelected

    private void mnuClientesVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuClientesVisualizarActionPerformed

    }//GEN-LAST:event_mnuClientesVisualizarActionPerformed

    private void btnPesquisaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaClienteActionPerformed
        //Se o item selecionado no comboBox for igual a zero a pesquisa será por CPF 
        //Caso contrário será por Nome do cliente ou parte do nome.
        String valor = txtPesquisaCliente.getText();
        String consulta = "";
        if(cmbPesquisaCliente.getSelectedIndex()==0){ 
            consulta = "select codigo,nome,cpf,telefone,celular,cadastro from "
                    + "cliente where cpf Like '%"+valor+"%' and deletado is null";
        }else{
            consulta = "select codigo,nome,cpf,telefone,celular,cadastro from "
                    + "cliente where nome Like '%"+valor+"%' and deletado is null";
        }   
        pesquisarNomes(consulta);
        
    }//GEN-LAST:event_btnPesquisaClienteActionPerformed

    private void mnuClientesAlterarMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_mnuClientesAlterarMenuSelected
        int x = tabela.getSelectedRow();
        Object codigo = modelo.getValueAt(x,0);
        Cliente janela = new Cliente(codigo,3);
        Main.getPainel().add(janela);
        janela.setVisible(true);
        //this.hide();
        centralizaForm(janela);
    }//GEN-LAST:event_mnuClientesAlterarMenuSelected

    private void mnuClientesExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuClientesExcluirMouseClicked
        int x = tabela.getSelectedRow();
        Object codigo = modelo.getValueAt(x,0);
        con.updateSQL("UPDATE cliente SET deletado = '*' WHERE codigo ="+Integer.parseInt((String) codigo));
    }//GEN-LAST:event_mnuClientesExcluirMouseClicked

    
    private void desabilitaPesquisa(){
        lblPesquisaCliente.setVisible(false);
        cmbPesquisaCliente.setVisible(false);
        txtPesquisaCliente.setVisible(false);
        btnPesquisaCliente.setVisible(false);        
    }
    
    private void habilitaPesquisa(){
        lblPesquisaCliente.setVisible(true);
        cmbPesquisaCliente.setVisible(true);
        txtPesquisaCliente.setVisible(true);
        btnPesquisaCliente.setVisible(true);
    }  
    
    private void centralizaForm(JInternalFrame frame) {
        Dimension desktopSize = Main.getPainel().getSize();
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
    } 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisaCliente;
    private javax.swing.JComboBox<String> cmbPesquisaCliente;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPesquisaCliente;
    private javax.swing.JMenuBar mnuClientes;
    private javax.swing.JMenu mnuClientesAlterar;
    private javax.swing.JMenu mnuClientesExcluir;
    private javax.swing.JMenu mnuClientesIncluir;
    private javax.swing.JMenu mnuClientesPesquisar;
    private javax.swing.JMenu mnuClientesSair;
    private javax.swing.JMenu mnuClientesVisualizar;
    private javax.swing.JTextField txtPesquisaCliente;
    // End of variables declaration//GEN-END:variables
}
