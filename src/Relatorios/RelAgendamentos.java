
package Relatorios;

import Conexao.Conexao;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author willian.carvalho
 */
public class RelAgendamentos extends javax.swing.JFrame {
    
    
    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();
    private Conexao con;
    
    public RelAgendamentos(Object conect,String dtIni,String dtFim ,String agenIni,
            String agenFim,String cliIni, String cliFim,String medIni, String medFim, String uniIni,
            String uniFim, int lista) {
        con = (Conexao) conect;
        dadosAgendamentos(dtIni, dtFim, agenIni, agenFim, cliIni, cliFim, medIni,
                medFim, uniIni, uniFim, lista);
  //      dadosAgendamentos(a,b,c,d);
        initComponents();
        jScrollPane1.setViewportView(tabela);
    }

   private void dadosAgendamentos(String dt_ini,String dt_fim,String ag_ini,
           String ag_fim,String cli_ini, String cli_fim,String med_ini,
           String med_fim, String uni_ini, String uni_fim, int lst){ 
        String data_ini = "20"+dt_ini.substring(6)+"-"+dt_ini.substring(3,5)+"-"+dt_ini.substring(0,2);
        String data_fim = "20"+dt_fim.substring(6)+"-"+dt_fim.substring(3,5)+"-"+dt_fim.substring(0,2);
        tabela = new JTable(modelo);   
        modelo.addColumn("Codigo");
        modelo.addColumn("Data");
        modelo.addColumn("Cliente");
        modelo.addColumn("Médico");
        modelo.addColumn("Unidade");
        modelo.addColumn("Especialidade");
        modelo.addColumn("Status");
        tabela.setShowGrid(false);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(25);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(60);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(60);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(60);

        try {
            con.conectar();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        String sql =  "SELECT  ag.codigo as codigo, ";
               sql += " ag.dt_hr_inicio as data, ";
               sql += " cl.nome as cliente, " ;
               sql += " md.nome as medico, ";
               sql += " un.nome as unidade,";
               sql += " es.nome as especialidade, ";
               sql += " st.descricao as status";
               
               sql += " FROM agendamento ag ";
               
               sql += " INNER JOIN cliente cl ";
               sql += " ON ag.cliente_codigo = cl.codigo ";
               sql += " AND cl.deletado is null ";
               
               sql += " INNER JOIN medico md ";
               sql += " ON ag.medico_codigo = md.codigo ";
               sql += " AND md.deletado is null ";
               
               sql += " INNER JOIN especialidade es ";
               sql += " ON md.especialidade_codigo = es.codigo ";
               sql += " AND es.deletado is null ";               
               
               sql += " INNER JOIN status_agendamento st ";
               sql += " ON ag.status_agendamento_codigo = st.codigo ";
               sql += " AND st.deletado is null ";
               
               sql += " INNER JOIN unidade un ";
               sql += " ON md.unidade_codigo = un.codigo ";
               sql += " AND un.deletado is null ";
               
               sql += " WHERE ag.deletado is null ";
               sql += " AND ag.dt_hr_inicio >= '"+data_ini+"' ";
               sql += " AND ag.dt_hr_inicio <= '"+data_fim+"' ";
               if(!ag_ini.isEmpty()){
                   sql += " AND ag.codigo >= "+Integer.parseInt(ag_ini)+" ";
                   sql += " AND ag.codigo <= "+Integer.parseInt(ag_fim)+" ";
               }
               if(!cli_ini.isEmpty()){
                   sql += " AND ag.cliente_codigo >= "+Integer.parseInt(cli_ini)+" ";
                   sql += " AND ag.cliente_codigo <= "+Integer.parseInt(cli_fim)+" ";
               }
               if(!med_ini.isEmpty()){
                   sql += " AND ag.medico_codigo >= "+Integer.parseInt(med_ini)+" ";
                   sql += " AND ag.medico_codigo <= "+Integer.parseInt(med_fim)+" ";
               }
               if(!uni_ini.isEmpty()){
                   sql += " AND un.codigo >= "+Integer.parseInt(uni_ini)+" ";
                   sql += " AND un.codigo <= "+Integer.parseInt(uni_fim)+" ";
               }
               if(lst==0){
                   sql += " AND ag.reservado = 'S' ";
               }else if(lst==1){
                   sql += " AND ag.espera = 'S' ";
               }
                
                dadosMedicos(sql);
    }
   
       private void dadosMedicos(String sql){
        DecimalFormat ze = new DecimalFormat("000000");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        modelo.setNumRows(0);
        con.executeSQL(sql);
        try {
            con.resultset.first();
            do{
                modelo.addRow(new Object [] {
                ze.format(con.resultset.getObject("codigo")),                                     
                df.format(con.resultset.getObject("data")),    
                con.resultset.getObject("cliente"),
                con.resultset.getObject("medico"),
                con.resultset.getObject("unidade"),
                con.resultset.getObject("especialidade"),
                con.resultset.getObject("status")});
            }while(con.resultset.next());
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }   
    }
 
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relação de Médicos");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
