package Aplicacao;

import Aplicacao.Main;
import Conexao.Conexao;
import java.awt.Dimension;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;

public class TelaAgendamento extends javax.swing.JInternalFrame {

    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private Conexao con;
    
    public TelaAgendamento(Conexao conect) {
        con = conect;
        criaTabelaAgendamento();
        initComponents();
        jScrollPane1.setViewportView(tabela);
    }
 

    private void criaTabelaAgendamento(){ 
        
        tabela = new JTable(modelo);   
        modelo.addColumn("Codigo");
        modelo.addColumn("Unidade");
        modelo.addColumn("Cliente");
        modelo.addColumn("Data");
        modelo.addColumn("Medico");
        modelo.addColumn("Especialidade");
        modelo.addColumn("Reservado");
        modelo.addColumn("Espera");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(15);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(10); 
        tabela.getColumnModel().getColumn(7).setPreferredWidth(10); 
        tabela.getColumn("Data").setCellRenderer(centercell);
        tabela.getColumn("Reservado").setCellRenderer(centercell);
        tabela.getColumn("Espera").setCellRenderer(centercell);

        try {
            con.conectar();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        pesquisarAgendamentos("SELECT ag.codigo as codigo, cl.nome as nome,"
                + "ag.dt_hr_inicio as data, md.nome as medico, ep.nome as "
                + "especialidade,un.nome as unidade,ag.reservado as reservado, "
                + "ag.espera as espera "
                + "FROM agendamento ag "
                + "JOIN cliente cl "
                + "ON cl.codigo = ag.cliente_codigo "
                + "JOIN medico md "
                + "ON md.codigo = ag.medico_codigo "
                + "JOIN especialidade ep "
                + "ON ep.codigo = md.especialidade_codigo "
                + "JOIN unidade un "
                + "ON un.codigo = md.unidade_codigo "
                + "JOIN status_agendamento sa "
                + "ON sa.codigo = ag.status_agendamento_codigo "
                + "WHERE ag.deletado is null");
    }
    
    DefaultTableCellRenderer centercell = new DefaultTableCellRenderer() {
        public void setValue(Object value) {
            setHorizontalAlignment(JLabel.CENTER);
            super.setValue(value);
        }
    };
    
    
    public static void alinhaTableCentro(JTable table, int[] posicoesDireita) {
        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);

        for (int numCol = 0; numCol < table.getColumnCount(); numCol++) {
            for (int i = 0; i < posicoesDireita.length; i++) {
                if (numCol == posicoesDireita[i]) {
                    table.getColumnModel().getColumn(numCol).setCellRenderer(
                                    cellRender);
                }
            }
        }
    }    
    
    
    private void pesquisarAgendamentos(String sql){
        DecimalFormat ze = new DecimalFormat("000000");  
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        modelo.setNumRows(0);
        con.executeSQL(sql);
        try {
            con.resultset.first();
            do{
                modelo.addRow(new Object [] {
                ze.format(con.resultset.getObject("codigo")),                  
                con.resultset.getObject("unidade"),                    
                con.resultset.getObject("nome"),
                df.format(con.resultset.getTimestamp("data")),  
                con.resultset.getObject("medico"),
                con.resultset.getObject("especialidade"),
                con.resultset.getObject("reservado"),
                con.resultset.getObject("espera")});
            }while(con.resultset.next());
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }   
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblPesquisaAgendamento = new javax.swing.JLabel();
        cmbPesquisaAgendamento = new javax.swing.JComboBox<>();
        txtPesquisaAgendamento = new javax.swing.JTextField();
        btnPesquisaAgendamento = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mnuAgendamento = new javax.swing.JMenuBar();
        mnuAgendamentoIncluir = new javax.swing.JMenu();
        mnuAgendamentoPesquisar = new javax.swing.JMenu();
        mnuAgendamentoVisualizar = new javax.swing.JMenu();
        mnuAgendamentoCancelar = new javax.swing.JMenu();
        mnuAgendamentoTransferir = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        mnuClientesSair = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();

        setBorder(null);
        setTitle("Cadastro de Agendamentos");

        lblPesquisaAgendamento.setText("Tipo");
        lblPesquisaAgendamento.setEnabled(true);
        lblPesquisaAgendamento.setVisible(false);
        lblPesquisaAgendamento.setInheritsPopupMenu(false);

        cmbPesquisaAgendamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cod. Agendamento", "Cod. Cliente","Nome Cliente","Data","Cod. Medico","Nome Médico","Cod. Especialidade","Nome Especialidade" }));
        cmbPesquisaAgendamento.setVisible(false);

        txtPesquisaAgendamento.setVisible(false);

        btnPesquisaAgendamento.setText("Pesquisar");
        btnPesquisaAgendamento.setVisible(false);
        btnPesquisaAgendamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPesquisaAgendamentoMouseClicked(evt);
            }
        });
        btnPesquisaAgendamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaAgendamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(423, Short.MAX_VALUE)
                .addComponent(lblPesquisaAgendamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPesquisaAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisaAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPesquisaAgendamento)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisaAgendamento)
                    .addComponent(txtPesquisaAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPesquisaAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPesquisaAgendamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
        );

        mnuAgendamentoIncluir.setText("Incluir");
        mnuAgendamentoIncluir.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                mnuAgendamentoIncluirMenuSelected(evt);
            }
        });
        mnuAgendamento.add(mnuAgendamentoIncluir);

        mnuAgendamentoPesquisar.setText("Pesquisar");
        mnuAgendamentoPesquisar.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                mnuAgendamentoPesquisarMenuSelected(evt);
            }
        });
        mnuAgendamentoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAgendamentoPesquisarActionPerformed(evt);
            }
        });
        mnuAgendamento.add(mnuAgendamentoPesquisar);

        mnuAgendamentoVisualizar.setText("Visualizar");
        mnuAgendamentoVisualizar.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                mnuAgendamentoVisualizarMenuSelected(evt);
            }
        });
        mnuAgendamentoVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAgendamentoVisualizarActionPerformed(evt);
            }
        });
        mnuAgendamento.add(mnuAgendamentoVisualizar);

        mnuAgendamentoCancelar.setText("Cancelar");
        mnuAgendamentoCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuAgendamentoCancelarMouseClicked(evt);
            }
        });
        mnuAgendamento.add(mnuAgendamentoCancelar);

        mnuAgendamentoTransferir.setText("Transferir");
        mnuAgendamento.add(mnuAgendamentoTransferir);
        mnuAgendamento.add(jMenu5);

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
        mnuAgendamento.add(mnuClientesSair);

        jMenu7.setText("    ");
        jMenu7.setEnabled(false);
        mnuAgendamento.add(jMenu7);

        setJMenuBar(mnuAgendamento);

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

    private void mnuAgendamentoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAgendamentoPesquisarActionPerformed

    }//GEN-LAST:event_mnuAgendamentoPesquisarActionPerformed

    private void mnuAgendamentoPesquisarMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_mnuAgendamentoPesquisarMenuSelected
        habilitaPesquisa();
    }//GEN-LAST:event_mnuAgendamentoPesquisarMenuSelected

    private void btnPesquisaAgendamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesquisaAgendamentoMouseClicked
        desabilitaPesquisa();
    }//GEN-LAST:event_btnPesquisaAgendamentoMouseClicked

    private void mnuAgendamentoIncluirMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_mnuAgendamentoIncluirMenuSelected

        Agendamento janela = new Agendamento("1",1);
        Main.getPainel().add(janela);
        janela.setVisible(true);
        centralizaForm(janela);        
    }//GEN-LAST:event_mnuAgendamentoIncluirMenuSelected

    
    private void mnuClientesSairMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_mnuClientesSairMenuSelected
        this.dispose();
    }//GEN-LAST:event_mnuClientesSairMenuSelected

    private void mnuAgendamentoVisualizarMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_mnuAgendamentoVisualizarMenuSelected
        int x = tabela.getSelectedRow();
        Object codigo = modelo.getValueAt(x,0);
        Agendamento janela = new Agendamento(codigo,2);
        Main.getPainel().add(janela);
        janela.setVisible(true);
        centralizaForm(janela);
    }//GEN-LAST:event_mnuAgendamentoVisualizarMenuSelected

    private void mnuAgendamentoVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAgendamentoVisualizarActionPerformed
        
    }//GEN-LAST:event_mnuAgendamentoVisualizarActionPerformed

    private void btnPesquisaAgendamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaAgendamentoActionPerformed

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String valor = txtPesquisaAgendamento.getText();
        String dia = "";
        if(cmbPesquisaAgendamento.getSelectedIndex()==3){
            do{
                switch (valor.length()) {
                    case 6:
                        dia = "20"+valor.substring(4)+"-"+valor.substring(2,4)+"-"+valor.substring(0,2);
                        break;
                    case 8:
                        if(valor.charAt(2)=='/'){
                            dia = "20"+valor.substring(6)+"-"+valor.substring(3,5)+"-"+valor.substring(0,2);
                        }else{
                            dia = valor.substring(4)+"-"+valor.substring(2,4)+"-"+valor.substring(0,2);
                        }
                        break;
                    case 10:
                        dia = valor.substring(6)+"-"+valor.substring(3,5)+"-"+valor.substring(0,2);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Data Inválida!");
                }                
            
            }while(valor.length()>10);
        }
        String consulta = "";        
        /*
        Faz a Verificação do tipo de Agendamento a Filtrar, sendo:
        0 - Cod. Agendamento, 1 - Cod. Cliente, 2 - Nome Cliente
        3 - Data, 4 - Cod. Medico, 5 - Nome Médico, 6 - Cod. Especialidade
        7 - Nome Especialidade
        */
        
        switch (cmbPesquisaAgendamento.getSelectedIndex()) {
            case 0: //Por Código do Agendamento
                consulta =  "SELECT ag.codigo as codigo, cl.nome as nome,";
                consulta += "ag.dt_hr_inicio as data, md.nome as medico, ep.nome as ";
                consulta += "especialidade,un.nome as unidade,ag.reservado as reservado, ";
                consulta += "ag.espera as espera ";
                consulta += "FROM agendamento ag ";
                consulta += "JOIN cliente cl ";
                consulta += "ON cl.codigo = ag.cliente_codigo ";
                consulta += "JOIN medico md ";
                consulta += "ON md.codigo = ag.medico_codigo ";
                consulta += "JOIN especialidade ep ";
                consulta += "ON ep.codigo = md.especialidade_codigo ";
                consulta += "JOIN unidade un ";
                consulta += "ON un.codigo = md.unidade_codigo ";
                consulta += "JOIN status_agendamento sa ";
                consulta += "ON sa.codigo = ag.status_agendamento_codigo ";
                consulta += "WHERE ag.deletado is null ";
                consulta += "AND ag.codigo = "+Integer.parseInt(valor.trim());
                break;
            case 1: //Por Código do Cliente
                consulta =  "SELECT ag.codigo as codigo, cl.nome as nome,";
                consulta += "ag.dt_hr_inicio as data, md.nome as medico, ep.nome as ";
                consulta += "especialidade,un.nome as unidade,ag.reservado as reservado, ";
                consulta += "ag.espera as espera ";
                consulta += "FROM agendamento ag ";
                consulta += "JOIN cliente cl ";
                consulta += "ON cl.codigo = ag.cliente_codigo ";
                consulta += "AND cl.codigo = "+Integer.parseInt(valor.trim())+ " ";
                consulta += "JOIN medico md ";
                consulta += "ON md.codigo = ag.medico_codigo ";
                consulta += "JOIN especialidade ep ";
                consulta += "ON ep.codigo = md.especialidade_codigo ";
                consulta += "JOIN unidade un ";
                consulta += "ON un.codigo = md.unidade_codigo ";
                consulta += "JOIN status_agendamento sa ";
                consulta += "ON sa.codigo = ag.status_agendamento_codigo ";
                consulta += "WHERE ag.deletado is null "; 
                break;
            case 2: //Por nome do Cliente
                consulta =  "SELECT ag.codigo as codigo, cl.nome as nome,";
                consulta += "ag.dt_hr_inicio as data, md.nome as medico, ep.nome as ";
                consulta += "especialidade,un.nome as unidade,ag.reservado as reservado, ";
                consulta += "ag.espera as espera ";
                consulta += "FROM agendamento ag ";
                consulta += "JOIN cliente cl ";
                consulta += "ON cl.codigo = ag.cliente_codigo ";
                consulta += "AND cl.nome like '%"+valor.trim()+ "%' ";
                consulta += "JOIN medico md ";
                consulta += "ON md.codigo = ag.medico_codigo ";
                consulta += "JOIN especialidade ep ";
                consulta += "ON ep.codigo = md.especialidade_codigo ";
                consulta += "JOIN unidade un ";
                consulta += "ON un.codigo = md.unidade_codigo ";
                consulta += "JOIN status_agendamento sa ";
                consulta += "ON sa.codigo = ag.status_agendamento_codigo ";
                consulta += "WHERE ag.deletado is null ";    
                break;
            case 3: //Por Data
                consulta =  "SELECT ag.codigo as codigo, cl.nome as nome,";
                consulta += "ag.dt_hr_inicio as data, md.nome as medico, ep.nome as ";
                consulta += "especialidade,un.nome as unidade,ag.reservado as reservado, ";
                consulta += "ag.espera as espera ";
                consulta += "FROM agendamento ag ";
                consulta += "JOIN cliente cl ";
                consulta += "ON cl.codigo = ag.cliente_codigo ";
                consulta += "JOIN medico md ";
                consulta += "ON md.codigo = ag.medico_codigo ";
                consulta += "JOIN especialidade ep ";
                consulta += "ON ep.codigo = md.especialidade_codigo ";
                consulta += "JOIN unidade un ";
                consulta += "ON un.codigo = md.unidade_codigo ";
                consulta += "JOIN status_agendamento sa ";
                consulta += "ON sa.codigo = ag.status_agendamento_codigo ";
                consulta += "WHERE ag.deletado is null ";      
                consulta += "AND ag.dt_hr_inicio like '"+dia+"%'";
                break;
            case 4: //Por Código do Médico
                consulta =  "SELECT ag.codigo as codigo, cl.nome as nome,";
                consulta += "ag.dt_hr_inicio as data, md.nome as medico, ep.nome as ";
                consulta += "especialidade,un.nome as unidade,ag.reservado as reservado, ";
                consulta += "ag.espera as espera ";
                consulta += "FROM agendamento ag ";
                consulta += "JOIN cliente cl ";
                consulta += "ON cl.codigo = ag.cliente_codigo ";
                consulta += "JOIN medico md ";
                consulta += "ON md.codigo = ag.medico_codigo ";
                consulta += "AND md.codigo = "+Integer.parseInt(valor.trim())+ " ";                
                consulta += "JOIN especialidade ep ";
                consulta += "ON ep.codigo = md.especialidade_codigo ";
                consulta += "JOIN unidade un ";
                consulta += "ON un.codigo = md.unidade_codigo ";
                consulta += "JOIN status_agendamento sa ";
                consulta += "ON sa.codigo = ag.status_agendamento_codigo ";
                consulta += "WHERE ag.deletado is null ";    
                break;  
            case 5: //Por nome do Médico
                consulta =  "SELECT ag.codigo as codigo, cl.nome as nome,";
                consulta += "ag.dt_hr_inicio as data, md.nome as medico, ep.nome as ";
                consulta += "especialidade,un.nome as unidade,ag.reservado as reservado, ";
                consulta += "ag.espera as espera ";
                consulta += "FROM agendamento ag ";
                consulta += "JOIN cliente cl ";
                consulta += "ON cl.codigo = ag.cliente_codigo ";
                consulta += "JOIN medico md ";
                consulta += "ON md.codigo = ag.medico_codigo ";
                consulta += "AND md.nome like '%"+valor.trim()+"%' ";                
                consulta += "JOIN especialidade ep ";
                consulta += "ON ep.codigo = md.especialidade_codigo ";
                consulta += "JOIN unidade un ";
                consulta += "ON un.codigo = md.unidade_codigo ";
                consulta += "JOIN status_agendamento sa ";
                consulta += "ON sa.codigo = ag.status_agendamento_codigo ";
                consulta += "WHERE ag.deletado is null ";    
                break;              
            case 6: //Por Código de Especialidade
                consulta =  "SELECT ag.codigo as codigo, cl.nome as nome,";
                consulta += "ag.dt_hr_inicio as data, md.nome as medico, ep.nome as ";
                consulta += "especialidade,un.nome as unidade,ag.reservado as reservado, ";
                consulta += "ag.espera as espera ";
                consulta += "FROM agendamento ag ";
                consulta += "JOIN cliente cl ";
                consulta += "ON cl.codigo = ag.cliente_codigo ";
                consulta += "JOIN medico md ";
                consulta += "ON md.codigo = ag.medico_codigo ";              
                consulta += "JOIN especialidade ep ";
                consulta += "ON ep.codigo = md.especialidade_codigo ";
                consulta += "AND ep.codigo = "+Integer.parseInt(valor.trim())+ " ";                  
                consulta += "JOIN unidade un ";
                consulta += "ON un.codigo = md.unidade_codigo ";
                consulta += "JOIN status_agendamento sa ";
                consulta += "ON sa.codigo = ag.status_agendamento_codigo ";
                consulta += "WHERE ag.deletado is null ";    
                break;  
            case 7: //Por nome da Especialidade
                consulta =  "SELECT ag.codigo as codigo, cl.nome as nome,";
                consulta += "ag.dt_hr_inicio as data, md.nome as medico, ep.nome as ";
                consulta += "especialidade,un.nome as unidade,ag.reservado as reservado, ";
                consulta += "ag.espera as espera ";
                consulta += "FROM agendamento ag ";
                consulta += "JOIN cliente cl ";
                consulta += "ON cl.codigo = ag.cliente_codigo ";
                consulta += "JOIN medico md ";
                consulta += "ON md.codigo = ag.medico_codigo ";             
                consulta += "JOIN especialidade ep ";
                consulta += "ON ep.codigo = md.especialidade_codigo ";
                consulta += "AND ep.nome like '%"+valor.trim()+"%' ";                   
                consulta += "JOIN unidade un ";
                consulta += "ON un.codigo = md.unidade_codigo ";
                consulta += "JOIN status_agendamento sa ";
                consulta += "ON sa.codigo = ag.status_agendamento_codigo ";
                consulta += "WHERE ag.deletado is null)";    
                break;                  
        }
        pesquisarAgendamentos(consulta);
    }//GEN-LAST:event_btnPesquisaAgendamentoActionPerformed

    private void mnuAgendamentoCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuAgendamentoCancelarMouseClicked
        int x = tabela.getSelectedRow();
        Object codigo = modelo.getValueAt(x,0);
        Object reserva = modelo.getValueAt(x,7);
        Object data = modelo.getValueAt(x,3);
        String rsv = (String) reserva;
        con.updateSQL("UPDATE agendamento SET deletado = '*' WHERE codigo ="+Integer.parseInt((String) codigo));
        if(rsv.charAt(0)=='N'){
            if(verificaReserva((String) data)){
                JOptionPane.showMessageDialog(null, "Existem reservas que podem ser efetivadas!");
            }
        }
    }//GEN-LAST:event_mnuAgendamentoCancelarMouseClicked


    private boolean verificaReserva(String data){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        boolean ret = false;
        String dia =  data.substring(6,10)+"-"+data.substring(3,5)+"-"+data.substring(0,2)
                +" "+data.substring(11)+":00.0";
        String consulta = "SELECT * from agendamento WHERE dt_hr_inicio = '"+dia+"' ";
               consulta += "AND deletado is null";
        
        con.executeSQL(consulta);

        try {
            if(con.resultset.next()){
                ret = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Agendamento.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return ret;
    }

    
    private void desabilitaPesquisa(){
        lblPesquisaAgendamento.setVisible(false);
        cmbPesquisaAgendamento.setVisible(false);
        txtPesquisaAgendamento.setVisible(false);
        btnPesquisaAgendamento.setVisible(false);        
    }
    
    private void habilitaPesquisa(){
        lblPesquisaAgendamento.setVisible(true);
        cmbPesquisaAgendamento.setVisible(true);
        txtPesquisaAgendamento.setVisible(true);
        btnPesquisaAgendamento.setVisible(true);
    }  
    
    private void centralizaForm(JInternalFrame frame) {
        Dimension desktopSize = Main.getPainel().getSize();
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
    } 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisaAgendamento;
    private javax.swing.JComboBox<String> cmbPesquisaAgendamento;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPesquisaAgendamento;
    private javax.swing.JMenuBar mnuAgendamento;
    private javax.swing.JMenu mnuAgendamentoCancelar;
    private javax.swing.JMenu mnuAgendamentoIncluir;
    private javax.swing.JMenu mnuAgendamentoPesquisar;
    private javax.swing.JMenu mnuAgendamentoTransferir;
    private javax.swing.JMenu mnuAgendamentoVisualizar;
    private javax.swing.JMenu mnuClientesSair;
    private javax.swing.JTextField txtPesquisaAgendamento;
    // End of variables declaration//GEN-END:variables
}
