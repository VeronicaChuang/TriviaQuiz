import java.sql.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
  * @author Veronica.Chuang
 */
public class logIn extends javax.swing.JFrame {
	//全域變數宣告
	private Connection conn;//member資料上傳
	protected String userName="";
	private String userPW="";
	protected String quedb=""; //題庫
	private boolean ifMember=false;
	private boolean ifquedbok=false;
	protected String player1="";
	protected String player2="";
	protected int roomID=-1;
	protected int isPlayer1 = 0;  //是p1=1, 是p2=2
	
    public logIn() {
    	
        initComponents();
        databaseConnect();//db載入
    }
    
    public void setBak(String str){
		((JPanel)this.getContentPane()).setOpaque(false);
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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
    	setBak("/img/login.jpg");
    	Container c = getContentPane();
    	jPanel1 = new javax.swing.JPanel();
    	c.add(jPanel1);
    	
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        englishBtn = new javax.swing.JRadioButton();
        liftBtn = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        playerPasswdLable = new javax.swing.JPasswordField();
        startBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        playerName = new javax.swing.JTextField();
        btnNewUser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trivia Quiz");
        setMaximizedBounds(new Rectangle(0, 0, 800, 600));
        setMaximumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);
        setSize(new Dimension(800, 600));        
        setLocationRelativeTo(null);// 置中

        jPanel4.setFont(new Font("標楷體", 0, 24)); // NOI18N
        jPanel4.setPreferredSize(new Dimension(800, 600));

        jLabel1.setBackground(new Color(0, 102, 51));
        jLabel1.setFont(new Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel1.setForeground(new Color(153, 0, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Trivia Quiz");
        jLabel1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
    	jPanel1.setOpaque(false);

        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setBackground(new Color(255, 255, 255));
        jLabel3.setFont(new Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel3.setForeground(new Color(51, 51, 255));
        jLabel3.setText("++Player Login++");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
       	jPanel3.setOpaque(false);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        buttonGroup1.add(englishBtn);
        englishBtn.setFont(new Font("Comic Sans MS", 2, 26)); // NOI18N
        englishBtn.setText("English Vocabulary");
        englishBtn.setForeground(new Color(255, 255, 153));

        buttonGroup1.add(liftBtn);
        liftBtn.setFont(new Font("Comic Sans MS", 2, 26)); // NOI18N
        liftBtn.setText("The little things in life");
        liftBtn.setForeground(new Color(255, 255, 153));

        jLabel2.setFont(new Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel2.setForeground(new Color(51, 0, 255));
        jLabel2.setText("++Database Sourcing++");

        jLabel4.setFont(new Font("Comic Sans MS", 2, 24)); // NOI18N
        jLabel4.setText("Password:");

        playerPasswdLable.setFont(new Font("標楷體", 0, 24)); // NOI18N

        startBtn.setFont(new Font("Comic Sans MS", 1, 24)); // NOI18N
        startBtn.setForeground(new Color(204, 0, 204));
        startBtn.setText("Start Game");
        startBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBtnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new Font("Comic Sans MS", 2, 24)); // NOI18N
        jLabel5.setText("Name:");

        playerName.setFont(new Font("標楷體", 0, 24)); // NOI18N
        
        btnNewUser.setFont(new Font("Comic Sans MS", 0, 24)); // NOI18N
        btnNewUser.setText("Sign up!!");
        btnNewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewUserActionPerformed(evt);
            }
        });
        
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2.setOpaque(false);    	
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(startBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(221, 221, 221)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(liftBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(englishBtn))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 57, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playerName, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playerPasswdLable, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNewUser)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playerName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playerPasswdLable, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(englishBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(liftBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(startBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);      
    	jPanel4.setOpaque(false);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, 603, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>                        
    	
    private void startBtnActionPerformed(java.awt.event.ActionEvent evt) { 
    	//start按鈕事件
    		checkMember();//確認是否會員
    		checkquedb();//確認題庫選擇    		
			dbquestionupload();//題庫上傳
			//選訂題庫撈題目
			
    		if(ifMember==true && ifquedbok==true){
    			createRoom();
    		}
    }                
    
    private void btnNewUserActionPerformed(java.awt.event.ActionEvent evt) {                                           
        //sign up事件
    	member frame2 = new member();//跳到main頁面    	
        frame2.setVisible(true);	      
        dispose(); //關掉原本的視窗
    } 
    
    public static void main(String args[]) {
//       
    	try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("exception: "+ex.toString());
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new logIn().setVisible(true);
            }
        });       
    }
    
    private void checkquedb(){//確認是否有選題庫
    	if(englishBtn.isSelected()){
    		quedb = "iii15engque";
    		ifquedbok =true;
    	}else if(liftBtn.isSelected()){
    		quedb = "iii15lifeque";
    		ifquedbok =true;
    	}
//    	System.out.println("checkquedb ok");
    }
    
    private void dbquestionupload(){ //載題庫 
    	boolean dataexist=false;//如果本來就有題目，就不再次上傳題目
    	try {
			PreparedStatement havedata = conn.prepareStatement("select * from "+quedb);
			ResultSet rs = havedata.executeQuery();
			while(rs.next()){
				dataexist=true;
				break;
			}		
    	} catch (Exception a) {
			System.out.println("checkData error: "+a.toString());
		}
    	
    	if(!dataexist){
	    	if(quedb.equals("iii15engque")){
	    		//upload english question
    		try{
    			InputStream is = logIn.class.getResourceAsStream("/jsonf/english.json");
    		    InputStreamReader inreader = new InputStreamReader(is,"UTF-8");
    			BufferedReader br = new BufferedReader(inreader);
    			String line = br.readLine();
    			JSONArray jarray = new JSONArray(line);
	    			for(int i =0; i<jarray.length();i++){
	    				JSONObject jobj = jarray.getJSONObject(i);
	    				String question = jobj.getString("question");
	    				String answer = jobj.getString("answer");
	    				String op1 = jobj.getString("op1");
	    				String op2 = jobj.getString("op2");
	    				String op3 = jobj.getString("op3");
	   				
	    				PreparedStatement stat = conn.prepareStatement("insert into iii15engque(question,answer,op1,op2,op3) values(?,?,?,?,?)");
	    				stat.setString(1, question);
	    				stat.setString(2, answer);
	    				stat.setString(3, op1);
	    				stat.setString(4, op2);
	    				stat.setString(5, op3);
	    				stat.execute();
	    				br.close();
	    	//			System.out.println("q: "+question+"a: "+answer+"1: "+op1+"2: "+op2+"3: "+op3);
	    			}
//	    			System.out.println("engQue upload ok");
	    		}catch(Exception e){
	    			System.out.println("fupload error: "+e.toString());
	    		}
	    	}else if(quedb.equals("iii15lifeque")){
	    		//upload life question
	    		try{
	    			InputStream is = logIn.class.getResourceAsStream("/jsonf/life.json");
//	    			FileInputStream fis = 
//	    		                new FileInputStream(new File("/jsonf/iii15life.json")); 
	    			
	    			InputStreamReader inreader = new InputStreamReader(is,"UTF-8");
	    			BufferedReader br = new BufferedReader(inreader);
	    			String line = br.readLine();
//	    			String utf8String = new String(line.getBytes("UTF-8"), "UTF-8"); 
	   // 			System.out.println(line);
	    			JSONArray jarray = new JSONArray(line);
	    			for(int i =0; i<jarray.length();i++){
	    				JSONObject jobj = jarray.getJSONObject(i);
	    				String question = jobj.getString("question");
	    				String answer = jobj.getString("answer");
	    				String op1 = jobj.getString("op1");
	    				String op2 = jobj.getString("op2");
	    				String op3 = jobj.getString("op3");
	   				
	    				PreparedStatement stat = conn.prepareStatement("insert into iii15lifeque(question,answer,op1,op2,op3) values(?,?,?,?,?)");
	    				stat.setString(1, question);
	    				stat.setString(2, answer);
	    				stat.setString(3, op1);
	    				stat.setString(4, op2);
	    				stat.setString(5, op3);
	    				stat.execute();
	    				
	    				br.close();
//	    				System.out.println("q: "+question+"a: "+answer+"1: "+op1+"2: "+op2+"3: "+op3);
	    			}
//	    			System.out.println("life upload ok");
	    		}catch(Exception e){
	    			e.printStackTrace();
	    			System.out.println("fupload error: "+e.toString());
	    		}
	    	}
    	}
    }
    
    private void checkMember(){//確認是否會員
    	boolean checkAccon= false;
    	userName = playerName.getText();
    	//userPW = playerPasswdLable.getText();
    	userPW = new String(playerPasswdLable.getPassword());
		String membPasswd = "";
		if(!userName.equals("") || !userPW.equals("")){
	    	try{
	    		//先query資料回來
	    		PreparedStatement prep2 = conn.prepareStatement("select password from iii15member where account='"+userName+"'");
	    		ResultSet rs = prep2.executeQuery();
				while(rs.next()){
					checkAccon = true;
					membPasswd = rs.getString("password");
					if(membPasswd.equals(userPW)){
						ifMember =true;
//						System.out.println("member check ok");
					}else{
						JLabel label = new JLabel("Please check password!");
			    		label.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
			    		JOptionPane.showMessageDialog(null, label,"--Welcom--",JOptionPane.PLAIN_MESSAGE);
			    		continue;
					}
					break;
				}
				//找不到帳號
				if(!checkAccon){
					JLabel label = new JLabel("Please Sign up first!");
		    		label.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
		    		JOptionPane.showMessageDialog(null, label,"--Welcom--",JOptionPane.PLAIN_MESSAGE);
				}
	    	}catch(Exception s){
	    		System.out.println("03checkMember error: "+ s.toString());
	    	}
		}else{
			JLabel label = new JLabel("Please fill in all columns!");
    		label.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
    		JOptionPane.showMessageDialog(null, label,"--Welcom--",JOptionPane.PLAIN_MESSAGE);
		}
    	
    }
    
    private void createRoom(){    	
    	boolean noRoom=true; //有沒有等待的房間
    	try {
			PreparedStatement haveRoom = conn.prepareStatement("select * from iii15room where status='wait' and question='"+quedb+"'");
			ResultSet rs = haveRoom.executeQuery();
			
	    	//先select是否有status是wait
			while(rs.next()){//有等待的房間
				noRoom = false;	
				isPlayer1 =2;
				player1 = rs.getString("player1");
				roomID =  Integer.parseInt(rs.getString("id"));
				
				//加入p2的名稱、房間狀態為play
				PreparedStatement setP2 = conn.prepareStatement("update iii15room set player2='"+userName+"', status='play' where id='"+roomID+"'");
				setP2.execute();
				player2 = userName;
//				System.out.println("p2-02 ok"+" roomID01:"+roomID);
				
				break;
			}		

	    	//若沒有，create一個row顯示wait  
			if(noRoom){//沒有房間創房間
				player1 = userName;
				isPlayer1 =1;
				PreparedStatement createRoom = conn.prepareStatement("insert into iii15room(player1, question) values('"+userName+"','"+quedb+"')");
				createRoom.executeUpdate();
//				System.out.println("create room ok");
			
				PreparedStatement queryRID = conn.prepareStatement("select * from iii15room where player1='"+userName+"' and status='wait'");
				ResultSet rs2 = queryRID.executeQuery();
				while(rs2.next()){
					roomID = Integer.parseInt(rs2.getString("id"));
//					System.out.println("login roomID02: "+roomID);
					break;
				}
			}
			
			waitConn frame = new waitConn(userName, isPlayer1, quedb, roomID);//跳到main頁面
    		frame.setVisible(true);	    		
    		dispose(); 
    	} catch (Exception a) {
			System.out.println("createRoom error: "+a.toString());
		}
    }
    
    private void databaseConnect(){
    	try {
			Class.forName("com.mysql.jdbc.Driver");
//			String url ="jdbc:mysql://localhost/quickAns";
			String url ="jdbc:mysql://112.104.57.22/iii2003";
			Properties prop = new Properties();
			prop.setProperty("user", "iiiuser");
			prop.setProperty("password", "P@ssw0rd");
//			prop.setProperty("createDatabaseIfNotExist", "true");			
			prop.setProperty("useSSL", "false");
			prop.setProperty("useUnicode", "true");
			prop.setProperty("characterEncoding", "UTF-8");			
			conn = DriverManager.getConnection(url, prop);
		//change database coding with UTF-8
//			PreparedStatement changeCollate = 
//					conn.prepareStatement("alter database quickAns collate utf8_general_ci");
//			changeCollate.execute();
			
		//create member table if not exist
			PreparedStatement stat = conn.prepareStatement("CREATE TABLE IF NOT EXISTS iii15member"
					+ "(id int primary key AUTO_INCREMENT, account varchar(30), password varchar(30), realname varchar(30), gender varchar(20), highscord int default'0', quesdb varchar(50))");
			stat.execute();
			
		//create life question table if not exist
			PreparedStatement quedb1 = conn.prepareStatement("CREATE TABLE IF NOT EXISTS iii15lifeque"
					+ "(id int primary key AUTO_INCREMENT, question varchar(200), answer varchar(100), op1 varchar(100), op2 varchar(100), op3 varchar(100))");
			quedb1.execute();
			
		//create eng question table if not exist
			PreparedStatement quedb2 = conn.prepareStatement("CREATE TABLE IF NOT EXISTS iii15engque"
					+ "(id int primary key AUTO_INCREMENT, question varchar(200), answer varchar(100), op1 varchar(100), op2 varchar(100), op3 varchar(100))");
			quedb2.execute();
			
		//create waiting room
			PreparedStatement waitRoom = conn.prepareStatement("CREATE TABLE IF NOT EXISTS iii15room"
					+ "(id int primary key AUTO_INCREMENT, player1 varchar(100), player2 varchar(100), question varchar(100) ,status varchar(30) default'wait', p1Scord int default'-1', p2Scord int default'-1')");
			waitRoom.execute();
     
        
        } catch (Exception e) {
			System.out.println("sql conn error: "+ e.toString());
		}
    }

    // Variables declaration - do not modify   
    private javax.swing.JButton btnNewUser;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton englishBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton liftBtn;
    private javax.swing.JTextField playerName;
    private javax.swing.JPasswordField playerPasswdLable;
    private javax.swing.JButton startBtn;
    // End of variables declaration                   
}
