/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tajava_11376;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import com.sun.glass.events.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;


/**
 *
 * @author windows
 */
public class menupanel_11376 extends javax.swing.JFrame {

    /**
     * Creates new form menupanel_11376
     */
    private Connection con;
    private Statement stm;
   private ResultSet rs;
    DefaultTableModel tbMaster;
    DefaultTableModel tbTransaksi;
    DefaultTableModel tbLaporan;
    DefaultTableModel tb;
    String formatUang="";
    String kode_fak="";
    String dateChooser;
    int uang=0;
    int subTotal=0;
    public menupanel_11376() {
        initComponents();
        koneksi();
        Object kol[]={"Kode","Nama Barang","Satuan","Harga"};
        Object data[][]={};
        tbMaster=new DefaultTableModel(data,kol);
        tableMaster.setModel(tbMaster);
        Object koll[]={"Kode","Nama Barang","Satuan","Jumlah","Harga","Total"};
        Object dataa[][]={};
        tbTransaksi=new DefaultTableModel(dataa,koll);
        jTable1.setModel(tbTransaksi);
        Object kolll[]={"No.Faktur","Tanggal","Nama Pembeli","Nama Kasir","Kode Barang","Jumlah","Total"};
        Object dataaa[][]={};
        tbLaporan=new DefaultTableModel(dataaa,kolll);
        tableLaporan.setModel(tbLaporan);
        
         Object kollll[]={"KODE","NAMA BARANG","SATUAN","JUMLAH","HARGA","TOTAL"};
        Object dataaaaa[][]={};
        tb=new DefaultTableModel(dataaaaa,kollll);
        jTable1.setModel(tb);
       
        
      
    }

      private void koneksi()
    {
        try{
            con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/tajava11376","root","");
            stm=con.createStatement();
            JOptionPane.showMessageDialog(null,"Koneksi Berhasil");
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal");
            Logger.getLogger(fakturPenjualan_11376.class.getName()).log(Level.SEVERE,null,e);
        }
    }
      
      private void tampil_table_master(){
          String kode,nama,satuan,hrg;
          int harga=0;
          try{
              String sql="select * from barang11376";
              rs=stm.executeQuery(sql);
              while(rs.next())
              {
                  kode=rs.getString("kode");
                  nama=rs.getString("nama_brg");
                  satuan=rs.getString("satuan");
                  harga=Integer.parseInt(rs.getString("harga"));
                  kurs_indonesia(harga);
                  hrg=this.formatUang;
                  tbMaster.addRow(new Object[]{kode,nama,satuan,hrg});
                  tableMaster.setModel(tbMaster);
              }
          }catch(SQLException e){
               Logger.getLogger(menupanel_11376.class.getName()).log(Level.SEVERE,null,e);
          }
      }
      private void reset_table_master(){
          tbMaster.setRowCount(0);
          tableMaster.setModel(tbMaster);
      }
      private void tampil_table_laporan(){
          String nofak,tgl,nmpmbli,nmkasir,kdbrg,jmlh;
          int total;
          try{
              String sql="select * from lapor_jual";
              rs=stm.executeQuery(sql);
              while(rs.next()){
                  nofak=rs.getString("no_fak");
                  tgl=rs.getString("tanggal");
                  nmpmbli=rs.getString("nm_pembeli");
                  nmkasir=rs.getString("nm_kasir");
                  kdbrg=rs.getString("kode_brg");
                  jmlh=rs.getString("jumlah");
                  total=Integer.parseInt(rs.getString("totalharga"));
                  kurs_indonesia(total);
                  tbLaporan.addRow(new Object[]{nofak,tgl,nmpmbli,nmkasir,kdbrg,jmlh,this.formatUang});
                  tableLaporan.setModel(tbLaporan);
              }
           
              }catch(SQLException e){
              Logger.getLogger(menupanel_11376.class.getName()).log(Level.SEVERE,null,e); 
          }
      }
       private void reset_table_laporan(){
          tbLaporan.setRowCount(0);
          tableLaporan.setModel(tbLaporan);
      }
       private void tampil_table_transaksi()
    {
        int hrg=0,ttl=0;
        try{
        String sql="select * from temporary";
        String kode,nama,satuan,jumlah,harga,total;
        rs=stm.executeQuery(sql);
        while(rs.next())
        {
            kode=rs.getString("kode");
            nama=rs.getString("nama_brg");
            satuan=rs.getString("satuan");
            jumlah=rs.getString("jumlah");
            hrg=Integer.parseInt(rs.getString("harga"));
            kurs_indonesia(hrg);
            harga=this.formatUang;
            ttl=Integer.parseInt(rs.getString("total"));
            this.subTotal=this.subTotal+ttl;
            kurs_indonesia(ttl);
            total=this.formatUang;
            tb.addRow(new Object[]{kode,nama,satuan,jumlah,harga,total});
            jTable1.setModel(tb);
            kurs_indonesia(this.subTotal);
       SUBTOTALField.setText(this.formatUang);
       
        }
        }catch(SQLException e)
        {
             Logger.getLogger(fakturPenjualan_11376.class.getName()).log(Level.SEVERE,null,e);
        }
    }
         private void reset_table_transaksi(){
        this.subTotal=0;
        tb.setRowCount(0);
        jTable1.setModel(tb);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField16 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        homeButton = new javax.swing.JButton();
        MasterButton = new javax.swing.JButton();
        TransaksiButton = new javax.swing.JButton();
        LaporanButton = new javax.swing.JButton();
        keluarButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        masteerPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        kodeBaragMaster = new javax.swing.JTextField();
        namaBarangMaster = new javax.swing.JTextField();
        satuanMaster = new javax.swing.JTextField();
        hargaMaster = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMaster = new javax.swing.JTable();
        inputMaster = new javax.swing.JButton();
        koreksiMaster = new javax.swing.JButton();
        hapusMaster = new javax.swing.JButton();
        simpanMaster = new javax.swing.JButton();
        transaksiPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        noFakturField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tglFieldChooser = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        pembeliField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        kasirField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        kode = new javax.swing.JTextField();
        namaField = new javax.swing.JTextField();
        satuanField = new javax.swing.JTextField();
        jumlahfield = new javax.swing.JTextField();
        hargaField = new javax.swing.JTextField();
        totalField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        inputButton = new javax.swing.JButton();
        koreksibutton = new javax.swing.JButton();
        hapusButton = new javax.swing.JButton();
        simpanButton = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        SUBTOTALField = new javax.swing.JTextField();
        Bayarfield = new javax.swing.JTextField();
        KembaliField = new javax.swing.JTextField();
        cancelkoreksiButton = new javax.swing.JButton();
        laporanPanel = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableLaporan = new javax.swing.JTable();
        hapusLaporan = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        jTextField16.setText("jTextField16");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 51));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("PT.MANTAP JIWA");

        homeButton.setText("HOME");
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });

        MasterButton.setText("MASTER");
        MasterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasterButtonActionPerformed(evt);
            }
        });

        TransaksiButton.setText("TRANSAKSi");
        TransaksiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransaksiButtonActionPerformed(evt);
            }
        });

        LaporanButton.setText("LAPORAN");
        LaporanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaporanButtonActionPerformed(evt);
            }
        });

        keluarButton.setText("KELUAR");
        keluarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Dibuat oleh :");

        jLabel3.setText("(A11.2018.11376)");

        jLabel4.setText("Latief Setiadi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MasterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TransaksiButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LaporanButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(keluarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addGap(67, 67, 67)
                .addComponent(homeButton)
                .addGap(43, 43, 43)
                .addComponent(MasterButton)
                .addGap(43, 43, 43)
                .addComponent(TransaksiButton)
                .addGap(45, 45, 45)
                .addComponent(LaporanButton)
                .addGap(44, 44, 44)
                .addComponent(keluarButton)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new java.awt.CardLayout());

        homePanel.setBackground(new java.awt.Color(255, 255, 0));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel5.setText("SELAMAT DATANG DI PT.MANTAP JIWA");

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(jLabel5)
                .addContainerGap(210, Short.MAX_VALUE))
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(jLabel5)
                .addContainerGap(467, Short.MAX_VALUE))
        );

        jPanel2.add(homePanel, "card2");

        masteerPanel.setBackground(new java.awt.Color(255, 255, 0));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel6.setText("MASTER BARANG");

        jLabel7.setText("Kode Barang");

        jLabel8.setText("Nama Barang");

        jLabel9.setText("Satuan");

        jLabel10.setText("Harga");

        satuanMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                satuanMasterActionPerformed(evt);
            }
        });

        tableMaster.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableMaster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMasterMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableMaster);

        inputMaster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar_icon/icons8-home-24.png"))); // NOI18N
        inputMaster.setText("INPUT");
        inputMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputMasterActionPerformed(evt);
            }
        });

        koreksiMaster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar_icon/icons8-recycle-24.png"))); // NOI18N
        koreksiMaster.setText("KOREKSI");
        koreksiMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                koreksiMasterActionPerformed(evt);
            }
        });

        hapusMaster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar_icon/icons8-trash-can-24.png"))); // NOI18N
        hapusMaster.setText("DELETE");
        hapusMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusMasterActionPerformed(evt);
            }
        });

        simpanMaster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar_icon/icons8-save-all-24.png"))); // NOI18N
        simpanMaster.setText("SIMPAN");
        simpanMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanMasterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout masteerPanelLayout = new javax.swing.GroupLayout(masteerPanel);
        masteerPanel.setLayout(masteerPanelLayout);
        masteerPanelLayout.setHorizontalGroup(
            masteerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masteerPanelLayout.createSequentialGroup()
                .addGroup(masteerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(masteerPanelLayout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addComponent(jLabel6))
                    .addGroup(masteerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(masteerPanelLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(masteerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(masteerPanelLayout.createSequentialGroup()
                                .addComponent(inputMaster)
                                .addGap(18, 18, 18)
                                .addComponent(koreksiMaster)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hapusMaster)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(simpanMaster))
                            .addGroup(masteerPanelLayout.createSequentialGroup()
                                .addGroup(masteerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kodeBaragMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(masteerPanelLayout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel7)))
                                .addGap(18, 18, 18)
                                .addGroup(masteerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(namaBarangMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, masteerPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(85, 85, 85)))
                                .addGroup(masteerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(satuanMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, masteerPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(35, 35, 35)))
                                .addGroup(masteerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hargaMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(masteerPanelLayout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(jLabel10)))))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        masteerPanelLayout.setVerticalGroup(
            masteerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masteerPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel6)
                .addGap(59, 59, 59)
                .addGroup(masteerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(masteerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kodeBaragMaster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namaBarangMaster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(satuanMaster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hargaMaster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(masteerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(koreksiMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(simpanMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapusMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        jPanel2.add(masteerPanel, "card3");

        transaksiPanel.setBackground(new java.awt.Color(255, 255, 0));

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel11.setText("TRANSAKSI BARANG");

        jLabel12.setText("No.Faktur");

        noFakturField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noFakturFieldActionPerformed(evt);
            }
        });

        jLabel13.setText("Tanggal");

        tglFieldChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglFieldChooserPropertyChange(evt);
            }
        });

        jLabel14.setText("Pembeli");

        pembeliField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pembeliFieldActionPerformed(evt);
            }
        });

        jLabel15.setText("Kasir");

        jPanel3.setBackground(new java.awt.Color(204, 204, 0));

        jLabel16.setText("KODE BARANG");

        jLabel17.setText("NAMA BARANG");

        jLabel18.setText("SATUAN");

        jLabel19.setText("JUMLAH");

        jLabel20.setText("HARGA");

        jLabel21.setText("TOTAL");

        kode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kodeKeyPressed(evt);
            }
        });

        jumlahfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jumlahfieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(kode, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(namaField, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(satuanField, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jumlahfield, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hargaField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalField)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel17)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel18)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel19)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addGap(77, 77, 77))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(satuanField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlahfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hargaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        inputButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar_icon/icons8-home-24.png"))); // NOI18N
        inputButton.setText("INPUT");
        inputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputButtonActionPerformed(evt);
            }
        });

        koreksibutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar_icon/icons8-recycle-24.png"))); // NOI18N
        koreksibutton.setText("KOREKSI");
        koreksibutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                koreksibuttonActionPerformed(evt);
            }
        });

        hapusButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar_icon/icons8-trash-can-24.png"))); // NOI18N
        hapusButton.setText("HAPUS");
        hapusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusButtonActionPerformed(evt);
            }
        });

        simpanButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar_icon/icons8-save-all-24.png"))); // NOI18N
        simpanButton.setText("SIMPAN");
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanButtonActionPerformed(evt);
            }
        });

        jLabel22.setText("SUB-TOTAL");

        jLabel23.setText("BAYAR");

        jLabel24.setText("KEMBALIAN");

        Bayarfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BayarfieldKeyPressed(evt);
            }
        });

        cancelkoreksiButton.setText("Cancel Koreksi");
        cancelkoreksiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelkoreksiButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout transaksiPanelLayout = new javax.swing.GroupLayout(transaksiPanel);
        transaksiPanel.setLayout(transaksiPanelLayout);
        transaksiPanelLayout.setHorizontalGroup(
            transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiPanelLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(318, 318, 318))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiPanelLayout.createSequentialGroup()
                                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(noFakturField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tglFieldChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGap(114, 114, 114)
                                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(pembeliField, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                        .addComponent(kasirField))
                                    .addComponent(jLabel14))
                                .addGap(180, 180, 180))))
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addComponent(inputButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cancelkoreksiButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(koreksibutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hapusButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(simpanButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SUBTOTALField)
                            .addComponent(Bayarfield)
                            .addComponent(KembaliField, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiPanelLayout.createSequentialGroup()
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addContainerGap())))
        );
        transaksiPanelLayout.setVerticalGroup(
            transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksiPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel11)
                .addGap(29, 29, 29)
                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noFakturField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pembeliField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tglFieldChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kasirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(SUBTOTALField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(Bayarfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(KembaliField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksiPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelkoreksiButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputButton)
                            .addComponent(koreksibutton)
                            .addComponent(hapusButton)
                            .addComponent(simpanButton))
                        .addGap(53, 53, 53))))
        );

        jPanel2.add(transaksiPanel, "card4");

        laporanPanel.setBackground(new java.awt.Color(255, 255, 0));

        jLabel25.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel25.setText("LAPORAN JUAL YANG DISIMPAN");

        tableLaporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLaporanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableLaporan);

        hapusLaporan.setText("HAPUS");
        hapusLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusLaporanActionPerformed(evt);
            }
        });

        jButton10.setText("HAPUS SEMUA");

        javax.swing.GroupLayout laporanPanelLayout = new javax.swing.GroupLayout(laporanPanel);
        laporanPanel.setLayout(laporanPanelLayout);
        laporanPanelLayout.setHorizontalGroup(
            laporanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, laporanPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addGap(258, 258, 258))
            .addGroup(laporanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(laporanPanelLayout.createSequentialGroup()
                .addGap(297, 297, 297)
                .addComponent(hapusLaporan)
                .addGap(34, 34, 34)
                .addComponent(jButton10)
                .addContainerGap(285, Short.MAX_VALUE))
        );
        laporanPanelLayout.setVerticalGroup(
            laporanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laporanPanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(laporanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hapusLaporan)
                    .addComponent(jButton10))
                .addContainerGap(263, Short.MAX_VALUE))
        );

        jPanel2.add(laporanPanel, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
        jPanel2.removeAll();
        jPanel2.repaint();
        jPanel2.revalidate();
        
        jPanel2.add(homePanel);
        jPanel2.repaint();
        jPanel2.revalidate();
    }//GEN-LAST:event_homeButtonActionPerformed

    private void MasterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasterButtonActionPerformed
        // TODO add your handling code here:
         jPanel2.removeAll();
        jPanel2.repaint();
        jPanel2.revalidate();
        
        jPanel2.add(masteerPanel);
        jPanel2.repaint();
        jPanel2.revalidate();
        reset_table_master();
         tampil_table_master();
    }//GEN-LAST:event_MasterButtonActionPerformed

    private void TransaksiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransaksiButtonActionPerformed
        // TODO add your handling code here:
        jPanel2.removeAll();
        jPanel2.repaint();
        jPanel2.revalidate();
        
        jPanel2.add(transaksiPanel);
        jPanel2.repaint();
        jPanel2.revalidate();
         cancelkoreksiButton.setVisible(false);
         reset_table_transaksi();
         tampil_table_transaksi();
         inputButtonActionPerformed(evt);
       
    }//GEN-LAST:event_TransaksiButtonActionPerformed

    private void LaporanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaporanButtonActionPerformed
        // TODO add your handling code here:
         jPanel2.removeAll();
        jPanel2.repaint();
        jPanel2.revalidate();
        
        jPanel2.add(laporanPanel);
        jPanel2.repaint();
        jPanel2.revalidate();
        
        reset_table_laporan();
        tampil_table_laporan();
    }//GEN-LAST:event_LaporanButtonActionPerformed

    private void keluarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_keluarButtonActionPerformed

    private void satuanMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_satuanMasterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_satuanMasterActionPerformed

    private void noFakturFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noFakturFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noFakturFieldActionPerformed

    private void pembeliFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pembeliFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pembeliFieldActionPerformed

    private void inputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputButtonActionPerformed
        // TODO add your handling code here:
          noFakturField.setText("");
        tglFieldChooser.setCalendar(null);
        pembeliField.setText("");
        kasirField.setText("");
        kode.setText("");
        namaField.setText("");
        satuanField.setText("");
        jumlahfield.setText("");
        hargaField.setText("");
        totalField.setText("");
        reset_table_transaksi();
        delete_all_temporary();
        SUBTOTALField.setText("");
        Bayarfield.setText("");
        KembaliField.setText("");
        noFakturField.requestFocus();
      
    }//GEN-LAST:event_inputButtonActionPerformed

    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
        // TODO add your handling code here:
          if(kode.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Silahkan pilih data terlebih dahulu!");
        }
        else{
        int confirm=JOptionPane.showOptionDialog(this,"Yakin Dihapus?","Perhatian !",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(confirm==JOptionPane.YES_OPTION)
        {
            try{
                stm.executeUpdate("delete from temporary where kode = '"+kode.getText()+"'");
                 reset_table_transaksi();
                tampil_table_transaksi();
                JOptionPane.showMessageDialog(null,"Delete Sukses!");
                kode.setText("");
                namaField.setText("");
                satuanField.setText("");
                hargaField.setText("");
                jumlahfield.setText("");
                hargaField.setText("");
                totalField.setText("");
               
            }catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null,"Proses Hapus Gagal");
                Logger.getLogger(fakturPenjualan_11376.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        }
    }//GEN-LAST:event_hapusButtonActionPerformed

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        // TODO add your handling code here:
         String kode;
        int jumlah,totalharga;
        if((noFakturField.getText().isEmpty())||(tglFieldChooser.getDate().toString().isEmpty())||(pembeliField.getText().isEmpty())||(kasirField.getText().isEmpty()))
        {
            JOptionPane.showMessageDialog(null,"Data ada yang kosong, lengkapi data terlebih dahulu!");
        }
        else{
        int confirm=JOptionPane.showConfirmDialog(null,"Data akan disimpan di laporan jual?","Konfirmasi Simpan!",JOptionPane.YES_NO_OPTION);
        if(confirm==JOptionPane.YES_OPTION)
        {
            try{ 
                int i=jTable1.getRowCount();
                int x=0;
                while(x<i)
                {
                    kode=jTable1.getValueAt(x,0).toString();
                    jumlah=Integer.parseInt(jTable1.getValueAt(x,3).toString());
                    format_reset(jTable1.getValueAt(x,5).toString());
                    
                   stm.executeUpdate("insert into lapor_jual values"+"('"+noFakturField.getText()+"','"+dateChooser+"','"+pembeliField.getText()+"','"+kasirField.getText()+"','"+kode+"','"+jumlah+"','"+this.uang+"')");
                    x++;
                }
                JOptionPane.showMessageDialog(null,"Data Anda Berhasil Disimpan ^_^");
                }catch(SQLException e){
                Logger.getLogger(fakturPenjualan_11376.class.getName()).log(Level.SEVERE,null,e);
            
            }
        }
        }
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void simpanMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanMasterActionPerformed
        // TODO add your handling code here:
        try{
            stm.executeUpdate("insert into barang11376 values"+"('"+kodeBaragMaster.getText()+"','"+namaBarangMaster.getText()+"','"+satuanMaster.getText()+"','"+hargaMaster.getText()+"')");
            reset_table_master();
            tampil_table_master();
        }catch(SQLException e){
             Logger.getLogger(menupanel_11376.class.getName()).log(Level.SEVERE,null,e);
        }
    }//GEN-LAST:event_simpanMasterActionPerformed

    private void inputMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputMasterActionPerformed
        // TODO add your handling code here:
        kodeBaragMaster.setText("");
        namaBarangMaster.setText("");
        satuanMaster.setText("");
        hargaMaster.setText("");
    }//GEN-LAST:event_inputMasterActionPerformed

    private void tableMasterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMasterMouseClicked
        // TODO add your handling code here:
        String harga="";
        int i=tableMaster.getSelectedRow();
        if(i>-1)
        {
            kodeBaragMaster.setText(tableMaster.getValueAt(i,0).toString());
            namaBarangMaster.setText(tableMaster.getValueAt(i, 1).toString());
            satuanMaster.setText(tableMaster.getValueAt(i, 2).toString());
            format_reset(tableMaster.getValueAt(i, 3).toString());
            hargaMaster.setText(""+this.uang);
            
        }
    }//GEN-LAST:event_tableMasterMouseClicked

    private void koreksiMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_koreksiMasterActionPerformed
        // TODO add your handling code here:
        if(kodeBaragMaster.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Pilih Data Terlebih Dahulu!");
        }else{
            try{
            stm.executeUpdate("update barang11376 set nama_brg='"+namaBarangMaster.getText()+"',satuan='"+satuanMaster.getText()+"',harga='"+hargaMaster.getText()+"' where kode='"+kodeBaragMaster.getText()+"'");
            reset_table_master();
            tampil_table_master();
            }
            catch(SQLException e)
            {
                  Logger.getLogger(menupanel_11376.class.getName()).log(Level.SEVERE,null,e);
            }
            }
    }//GEN-LAST:event_koreksiMasterActionPerformed

    private void hapusMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusMasterActionPerformed
        // TODO add your handling code here:
        if(kodeBaragMaster.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Pilih Data Terlebih Dahulu!");
        }
        else{
            int confirm=JOptionPane.showConfirmDialog(null,"Apakah Yakin Ingin Menghapus Data?","Konfirmasi Hapus!",JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.YES_OPTION)
            {
                try{
                stm.executeUpdate("delete from barang11376 where kode='"+kodeBaragMaster.getText()+"'");
                reset_table_master();
                tampil_table_master();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null,"Delete Gagal");
                    Logger.getLogger(menupanel_11376.class.getName()).log(Level.SEVERE,null,e);
           
                }
                }
        }
    }//GEN-LAST:event_hapusMasterActionPerformed

    private void hapusLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusLaporanActionPerformed
        // TODO add your handling code here:
         if(this.kode_fak.isEmpty()){
            JOptionPane.showMessageDialog(null,"Pilih Data Terlebih Dahulu!");
        }
        else{
            int confirm=JOptionPane.showConfirmDialog(null,"Apakah Yakin Ingin Menghapus Data?","Konfirmasi Hapus!",JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.YES_OPTION)
            {
                try{
                stm.executeUpdate("delete from lapor_jual where no_fak='"+this.kode_fak+"'");
                reset_table_laporan();
                tampil_table_laporan();
                JOptionPane.showMessageDialog(null,"Delete Sukses");
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null,"Delete Gagal");
                    Logger.getLogger(menupanel_11376.class.getName()).log(Level.SEVERE,null,e);
           
                }
                }
            this.kode_fak="";
        }
    }//GEN-LAST:event_hapusLaporanActionPerformed

    private void tableLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLaporanMouseClicked
        // TODO add your handling code here:
        int i=tableLaporan.getSelectedRow();
        if(i>-1){
            this.kode_fak=tableLaporan.getValueAt(i,0).toString();
        }
    }//GEN-LAST:event_tableLaporanMouseClicked

    private void kodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodeKeyPressed
        // TODO add your handling code here:
              int harga;
              totalField.setText("");
        if(evt.getKeyCode()== KeyEvent.VK_ENTER)
        {
            if(isSame()==true){
                JOptionPane.showMessageDialog(null,"Data Sudah Ada!");
                kode.setText("");
            }
            else{
                if(!isWrongKodeBarang()){            
         try{
       rs=stm.executeQuery("select * from barang11376 where kode='"+kode.getText()+"'");
       while(rs.next())
       {
          namaField.setText(rs.getString("nama_brg"));
          satuanField.setText(rs.getString("satuan"));
          harga=Integer.parseInt(rs.getString("harga"));
           kurs_indonesia(harga);
          hargaField.setText(this.formatUang);
          jumlahfield.setText("");
          jumlahfield.requestFocus();
       }
        }catch(SQLException e)
        {
             Logger.getLogger(fakturPenjualan_11376.class.getName()).log(Level.SEVERE,null,e);
        }
                }
           
                else{
                    JOptionPane.showMessageDialog(null,"Kode barang tidak sesuai dengan data barang!");
                    kode.setText("");
                }
            }
         
        }
    }//GEN-LAST:event_kodeKeyPressed

    private void jumlahfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahfieldKeyPressed
        // TODO add your handling code here:
         int jumlah,harga,total;
       
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){   
        if(!koreksibutton.isEnabled())
        {
            jumlahfield.requestFocus();
             jumlah=Integer.valueOf(jumlahfield.getText());
       //harga=Integer.valueOf(hargaField.getText());
            format_reset(hargaField.getText());
            harga=this.uang;
       total=jumlah*harga;
            kurs_indonesia(total);
       totalField.setText(this.formatUang);
       try{
       stm.executeUpdate("update temporary set nama_brg='"+namaField.getText()+"',satuan='"+satuanField.getText()+"'"+",jumlah='"+jumlahfield.getText()+"',harga='"+harga+"',total='"+total+"' where kode='"+kode.getText()+"'");
              
       reset_table_transaksi();
       tampil_table_transaksi();
       JOptionPane.showMessageDialog(null,"Data Telah Dikoreksi!\nMode Koreksi OFF");
       kode.setText("");
       namaField.setText("");
       satuanField.setText("");
       jumlahfield.setText("");
       hargaField.setText("");
       totalField.setText("");
       koreksibutton.setEnabled(true);
       cancelkoreksiButton.setVisible(false);
       kode.requestFocus();
           }catch(SQLException e)
       {
           Logger.getLogger(fakturPenjualan_11376.class.getName()).log(Level.SEVERE,null,e);
       }
        }
      
        else{
            if(!isSame()){
       jumlah=Integer.valueOf(jumlahfield.getText());
       //harga=Integer.valueOf(hargaField.getText());
            format_reset(hargaField.getText());
            harga=this.uang;
       total=jumlah*harga;
            kurs_indonesia(total);
       totalField.setText(this.formatUang);
       try{
       stm.executeUpdate("insert into temporary values"+"('"+kode.getText()+"','"+namaField.getText()+"','"+satuanField.getText()+"','"+jumlahfield.getText()+"','"+harga+"','"+total+"')");
       reset_table_transaksi();
       tampil_table_transaksi();
        
           }catch(SQLException e)
       {
           Logger.getLogger(fakturPenjualan_11376.class.getName()).log(Level.SEVERE,null,e);
       }
        }
            else{
                JOptionPane.showMessageDialog(null,"Data Sudah Ada!\n Pilih Koreksi Bila Ingin Update Data");
                kode.requestFocus();
            }
        }
    }
    }//GEN-LAST:event_jumlahfieldKeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         int i=jTable1.getSelectedRow();
        if(i>-1)
        {
            kode.setText(jTable1.getValueAt(i,0).toString());
            namaField.setText(jTable1.getValueAt(i,1).toString());
            satuanField.setText(jTable1.getValueAt(i,2).toString());
            jumlahfield.setText(jTable1.getValueAt(i,3).toString());
            hargaField.setText(jTable1.getValueAt(i,4).toString());
            totalField.setText(jTable1.getValueAt(i,5).toString());
           // JOptionPane.showMessageDialog(null,jTable1.getValueAt(i,5));
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void koreksibuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_koreksibuttonActionPerformed
        // TODO add your handling code here:
        koreksibutton.setEnabled(false);
      JOptionPane.showMessageDialog(null,"Mode Koreksi Aktif!\nSilahkan Pilih Data Pada Table Tersedia");
      cancelkoreksiButton.setVisible(true);
    }//GEN-LAST:event_koreksibuttonActionPerformed

    private void cancelkoreksiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelkoreksiButtonActionPerformed
        // TODO add your handling code here:
         koreksibutton.setEnabled(true);
        cancelkoreksiButton.setVisible(false);
    }//GEN-LAST:event_cancelkoreksiButtonActionPerformed

    private void tglFieldChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglFieldChooserPropertyChange
        // TODO add your handling code here:
         try{
            if(tglFieldChooser.getDate() != null)
            {
                String pattern ="yyyy-MM-dd";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                dateChooser=String.valueOf(format.format(tglFieldChooser.getDate()));
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"eror"+e);
        }
    }//GEN-LAST:event_tglFieldChooserPropertyChange

    private void BayarfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BayarfieldKeyPressed
        // TODO add your handling code here:
         int bayar,kembalian;
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           kurs_indonesia(Integer.parseInt(Bayarfield.getText()));
           Bayarfield.setText(this.formatUang);
         int confirm=JOptionPane.showOptionDialog(this,"Yakin Ingin Membayar?","Perhatian !",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
         if(confirm==JOptionPane.YES_OPTION)
         {
             format_reset(Bayarfield.getText());
             bayar=this.uang;
             kembalian=bayar-this.subTotal;
             kurs_indonesia(kembalian);
             KembaliField.setText(this.formatUang);
             delete_all_temporary();
             
         }
         else{
             Bayarfield.setText("");
         }
       }
       
    }//GEN-LAST:event_BayarfieldKeyPressed

     private void kurs_indonesia(int harga)
    {
        try{
        int x =harga;
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
 
        formatRp.setCurrencySymbol("");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
 
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        this.formatUang="Rp."+kursIndonesia.format(x);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Format gagal");
        }
    }
    
    private void format_reset(String duit)
    {
        String x = duit;
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
 
        formatRp.setCurrencySymbol("Rp.");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
 
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        try {
            Number number = kursIndonesia.parse(x);
            int nilai = number.intValue();
            this.uang=nilai;
        } catch (ParseException ex) {
            System.out.println("Kesalahan Parsing");
        }
    }
   
     private boolean isSame()
    {
        int x=0;
        try{
            String sql="select * from temporary";
            rs=stm.executeQuery(sql);
            while(rs.next())
            {
                if(kode.getText().equals(rs.getString("kode")))
                {
                    x=1;
                }
            }
        }catch(SQLException e){
             Logger.getLogger(fakturPenjualan_11376.class.getName()).log(Level.SEVERE,null,e);
       
        }
        if(x==1)
        {
            return true;
        }
        else{
            return false;
        }
    }
     
       private boolean isWrongKodeBarang(){
        int x=0;
        try{
            String sql="select * from barang11376";
            rs=stm.executeQuery(sql);
            while(rs.next())
            {
                if(kode.getText().equals(rs.getString("kode")))
                {
                    x=1;
                }
            }
           
        }
        catch(SQLException e)
        {
             Logger.getLogger(fakturPenjualan_11376.class.getName()).log(Level.SEVERE,null,e);
       
        }
         if(x==0){
                return true;
            }
            else{
                return false;
            }
    }
        private void delete_all_temporary()
    {
        
        try{
                stm.executeUpdate("delete from temporary");
        }catch(SQLException e)
        {
          Logger.getLogger(fakturPenjualan_11376.class.getName()).log(Level.SEVERE,null,e);
       
        }
        
    }
  
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menupanel_11376.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menupanel_11376.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menupanel_11376.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menupanel_11376.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menupanel_11376().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Bayarfield;
    private javax.swing.JTextField KembaliField;
    private javax.swing.JButton LaporanButton;
    private javax.swing.JButton MasterButton;
    private javax.swing.JTextField SUBTOTALField;
    private javax.swing.JButton TransaksiButton;
    private javax.swing.JButton cancelkoreksiButton;
    private javax.swing.JButton hapusButton;
    private javax.swing.JButton hapusLaporan;
    private javax.swing.JButton hapusMaster;
    private javax.swing.JTextField hargaField;
    private javax.swing.JTextField hargaMaster;
    private javax.swing.JButton homeButton;
    private javax.swing.JPanel homePanel;
    private javax.swing.JButton inputButton;
    private javax.swing.JButton inputMaster;
    private javax.swing.JButton jButton10;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jumlahfield;
    private javax.swing.JTextField kasirField;
    private javax.swing.JButton keluarButton;
    private javax.swing.JTextField kode;
    private javax.swing.JTextField kodeBaragMaster;
    private javax.swing.JButton koreksiMaster;
    private javax.swing.JButton koreksibutton;
    private javax.swing.JPanel laporanPanel;
    private javax.swing.JPanel masteerPanel;
    private javax.swing.JTextField namaBarangMaster;
    private javax.swing.JTextField namaField;
    private javax.swing.JTextField noFakturField;
    private javax.swing.JTextField pembeliField;
    private javax.swing.JTextField satuanField;
    private javax.swing.JTextField satuanMaster;
    private javax.swing.JButton simpanButton;
    private javax.swing.JButton simpanMaster;
    private javax.swing.JTable tableLaporan;
    private javax.swing.JTable tableMaster;
    private com.toedter.calendar.JDateChooser tglFieldChooser;
    private javax.swing.JTextField totalField;
    private javax.swing.JPanel transaksiPanel;
    // End of variables declaration//GEN-END:variables
}
