import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * @author Veronica.Chuang
 */
public class member extends javax.swing.JFrame {
	private Connection conn;
	private String account = "";
	private String passwd = "";
	private String realname = "";
	private String gender="";
	private boolean accountRepeat=false;

    public member() {
    	
        initComponents();
        databaseConnect(); 
    }
    
    public void setBak(String str){
		((JPanel)this.getContentPane()).setOpaque(false);
	//	ImageIcon newIcon = new ImageIcon(new ImageIcon("./img/member.jpg").getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
		ImageIcon newIcon;
		try{
		BufferedImage bimg = ImageIO.read(logIn.class.getResource(str));
		newIcon = new ImageIcon(new ImageIcon(bimg).getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
	
		JLabel background = new JLabel(newIcon);this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, newIcon.getIconWidth(), newIcon.getIconHeight());
		}catch(Exception e){
			System.out.println("setBak error: "+e.toString());
		}
	}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
    	setBak("/img/member.jpg");
    	Container c = getContentPane();
    	jPanel1 = new javax.swing.JPanel();
    	jPanel1.setOpaque(false);
    	c.add(jPanel1);
    	
        buttonGroup1 = new javax.swing.ButtonGroup();
        
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        newAccon = new javax.swing.JTextField();
        newPasswd = new javax.swing.JPasswordField();
        realName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        gentleman = new javax.swing.JRadioButton();
        lady = new javax.swing.JRadioButton();
        singupOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Member List");
        setMaximumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quickly Answer");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

//        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Member Sign Up");
        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

//        jPanel4.setBackground(new java.awt.Color(204, 255, 204));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel4.setText("Real Name:");
        jPanel4.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel3.setText("Account:");
        jPanel3.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel5.setText("Password:");

        newAccon.setFont(new java.awt.Font("標楷體", 0, 24)); // NOI18N

        newPasswd.setFont(new java.awt.Font("標楷體", 0, 24)); // NOI18N

        realName.setFont(new java.awt.Font("標楷體", 0, 24)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel6.setText("Gender:");

//        gentleman.setBackground(new java.awt.Color(204, 255, 204));
        buttonGroup1.add(gentleman);
        gentleman.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        gentleman.setText("Gentleman");

//        lady.setBackground(new java.awt.Color(204, 255, 204));
        buttonGroup1.add(lady);
        lady.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        lady.setText("Lady");
        lady.setToolTipText("");

        singupOK.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        singupOK.setText("Submit");
        singupOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singupOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(newAccon)
                                .addComponent(newPasswd)
                                .addComponent(realName, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(gentleman, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(lady))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(singupOK, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(newAccon, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newPasswd, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(realName, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gentleman)
                    .addComponent(lady))
                .addGap(42, 42, 42)
                .addComponent(singupOK, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
    }// </editor-fold>                        

    private void singupOKActionPerformed(java.awt.event.ActionEvent evt) {                                         
    // submit event   	
    //先抓到輸入
    	account = newAccon.getText();
		passwd = new String(newPasswd.getPassword());
		realname = realName.getText();
		gender="";
			if(gentleman.isSelected()){
				gender = "gentleman";
			}else if(lady.isSelected()){
				gender = "lady";
			}
    	if(!account.equals("") && !passwd.equals("") && !realname.equals("") && !gender.equals("")){
    		checkAccRep();
    		
    		if(!accountRepeat){
	    		insertMemberInfo();    	
	    		
	    		logIn fram = new logIn();
		    	fram.setVisible(true);
		    	dispose();
    		}
    	}else{    		
    		//JOptionPane.showMessageDialog(frame,"Eggs are not supposed to be green.", "A plain message",
    		//	    JOptionPane.PLAIN_MESSAGE);
    		JLabel label = new JLabel("Please fill in all columns!");
    		label.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
    		JOptionPane.showMessageDialog(null, label,"---Error---",JOptionPane.PLAIN_MESSAGE);
    		
    	}
    }     
    
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
            java.util.logging.Logger.getLogger(member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new member().setVisible(true);
            }
        });
    }

    private void checkAccRep(){
    	try {
			PreparedStatement stat = conn.prepareStatement("select * from iii15member where account='"+account+"'");
			ResultSet rs = stat.executeQuery();
			if(rs.next()){
				accountRepeat=true;
				JLabel label = new JLabel("Account exist!!");
	    		label.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
	    		JOptionPane.showMessageDialog(null, label,"--Warning--",JOptionPane.PLAIN_MESSAGE);
			}else{
				accountRepeat=false;
			}
    	} catch (Exception e) {
			System.out.println("checkAccRep error: "+e.toString());
		}
    }
    
    private void insertMemberInfo(){
    	//update new member info into database		
		try{		
			String insertSql ="insert into iii15member(account, password, realname, gender, quesdb) values(?,?,?,?,?)"; 
			PreparedStatement newMember = conn.prepareStatement(insertSql);
			newMember.setString(1, account);
			newMember.setString(2, passwd);
			newMember.setString(3, realname);
			newMember.setString(4, gender);		
			newMember.setString(5, "engque");	
			newMember.execute();
			
			String insertSq2 ="insert into iii15member(account, password, realname, gender, quesdb) values(?,?,?,?,?)"; 
			PreparedStatement newMember2 = conn.prepareStatement(insertSql);
			newMember.setString(1, account);
			newMember.setString(2, passwd);
			newMember.setString(3, realname);
			newMember.setString(4, gender);		
			newMember.setString(5, "lifeque");	
			newMember.execute();
			
		}catch(Exception ee){
			System.out.println("newMemberSql error: "+ee.toString());
		}
    }
    
    private void databaseConnect(){
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			String url ="jdbc:mysql://112.104.57.22/iii2003";
			Properties prop = new Properties();
			prop.setProperty("user", "iiiuser");
			prop.setProperty("password", "P@ssw0rd");
//			prop.setProperty("createDatabaseIfNotExist", "true");			
			prop.setProperty("useSSL", "false");
			prop.setProperty("useUnicode", "true");
			prop.setProperty("characterEncoding", "UTF-8");			
			conn = DriverManager.getConnection(url, prop);
        } catch (Exception e) {
			System.out.println("sql conn error: "+ e.toString());
		}
    }
    // Variables declaration - do not modify                     
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton gentleman;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton lady;
    private javax.swing.JTextField newAccon;
    private javax.swing.JPasswordField newPasswd;
    private javax.swing.JTextField realName;
    private javax.swing.JButton singupOK;
    // End of variables declaration                   
}
