import java.awt.Container;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scord_Single extends JFrame {
	private Connection conn;//member資料上傳
	protected String userN="", quedb="", logindb="";
	protected int userScords=0; //本次成績
	private String scordRecord="";
	private int record=0;//歷史最高分
	
	public Scord_Single() {
		setSize(800,600);
		setResizable(false);
		setBak("/img/scordbg.jpg"); //調用背景方法
		Container c = getContentPane(); //獲取JFrame面板
		jp = new JPanel(); //創建個JPanel
		jp.setOpaque(false); //把JPanel設置為透明 這樣就不會遮住後面的背景 這樣你就能在JPanel隨意加元件了
		c.add(jp);
		setVisible(true);
		initComponents();
	}
	
	public Scord_Single(String userName, int point, String db, String histroyRec, String systemDB) {
		userN=userName; userScords =point; quedb =db; record=Integer.parseInt(histroyRec); logindb = systemDB;
		setSize(800,600);
		setResizable(false);
		setBak("/img/scordbg.jpg"); //調用背景方法
		Container c = getContentPane(); //獲取JFrame面板
		jp = new JPanel(); //創建個JPanel
		jp.setOpaque(false); //把JPanel設置為透明 這樣就不會遮住後面的背景 這樣你就能在JPanel隨意加元件了
		c.add(jp);
		setVisible(true);
		initComponents();
		databaseConnect();
		insertScord();
}
	
	private void insertScord(){
		
		if(userScords>record){	
			try {
				String sql = "update iii15member set highscord='"+userScords+"' where account='"+userN+"' and quesdb='"+logindb+"'";
				PreparedStatement changeCollate = conn.prepareStatement(sql);		
				changeCollate.executeUpdate();
			} catch (Exception e) {	
				System.out.println("insertScord error"+e.toString());;
			}
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
	
	public void setBak(String str){
		((JPanel)this.getContentPane()).setOpaque(false);
		//ImageIcon img = new ImageIcon("./img/scordbg.jpg");
		ImageIcon newIcon;
		try{
		BufferedImage bimg = ImageIO.read(logIn.class.getResource(str));
		newIcon = new ImageIcon(new ImageIcon(bimg).getImage());

		JLabel background = new JLabel(newIcon);this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, newIcon.getIconWidth(), newIcon.getIconHeight());
		}catch(Exception e){
			System.out.println("setBak error: "+e.toString());
		}
	}
	                                  
    private void initComponents() {
	   // TESTBG = new javax.swing.JPanel();
	title = new javax.swing.JLabel();
	qData = new javax.swing.JLabel();
	player = new javax.swing.JLabel();
	scord = new javax.swing.JLabel();
	dbSource = new javax.swing.JLabel();
	playerName = new javax.swing.JLabel();
	showscord = new javax.swing.JLabel();
	toLoginBtn = new javax.swing.JButton();
	history = new javax.swing.JLabel();
	hScord = new javax.swing.JLabel();
	
	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	
	jp.setMaximumSize(new java.awt.Dimension(800, 800));
	
	title.setFont(new java.awt.Font("Comic Sans MS", 1, 48)); // NOI18N
	title.setForeground(new java.awt.Color(255, 255, 255));
	title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	title.setText("Scords");
	
	qData.setFont(new java.awt.Font("Comic Sans MS", 1, 32)); // NOI18N
	qData.setForeground(new java.awt.Color(204, 255, 255));
	qData.setText("Question Data:");        
	
	player.setFont(new java.awt.Font("Comic Sans MS", 1, 32)); // NOI18N
	player.setForeground(new java.awt.Color(204, 255, 255));
	player.setText("Player:");
	
	scord.setFont(new java.awt.Font("Comic Sans MS", 1, 32)); // NOI18N
	scord.setForeground(new java.awt.Color(204, 255, 255));
	scord.setText("Scords");
	
	dbSource.setFont(new java.awt.Font("Comic Sans MS", 3, 30)); // NOI18N
	dbSource.setForeground(new java.awt.Color(255, 102, 255));
	dbSource.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	dbSource.setText(quedb);
	
	playerName.setFont(new java.awt.Font("Comic Sans MS", 3, 30)); // NOI18N
	playerName.setForeground(new java.awt.Color(255, 102, 255));
	playerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	playerName.setText(userN);
	
	showscord.setFont(new java.awt.Font("Comic Sans MS", 3, 30)); // NOI18N
	showscord.setForeground(new java.awt.Color(255, 102, 255));
	showscord.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	showscord.setText(""+userScords);
	
	history.setFont(new java.awt.Font("Comic Sans MS", 3, 30)); // NOI18N
	history.setForeground(new java.awt.Color(204, 255, 255));
	history.setText("Height Scords:");
	
	hScord.setFont(new java.awt.Font("Comic Sans MS", 3, 30)); // NOI18N
	hScord.setForeground(new java.awt.Color(255, 102, 255));
	hScord.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	hScord.setText(""+record);
	
	toLoginBtn.setFont(new java.awt.Font("Comic Sans MS", 0, 28)); // NOI18N
	toLoginBtn.setText("Play Again");
	toLoginBtn.setBorder(null);
	toLoginBtn.addMouseListener(new MouseAdapter() {   
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
	    	logIn frame = new logIn();//帶參數跳到main頁面	
			frame.setVisible(true);
			dispose(); //關掉原本的視窗
	    	};
		});
	    
	    javax.swing.GroupLayout TESTBGLayout = new javax.swing.GroupLayout(jp);
	    jp.setLayout(TESTBGLayout);
	    TESTBGLayout.setHorizontalGroup(
        TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(TESTBGLayout.createSequentialGroup()
            .addContainerGap(126, Short.MAX_VALUE)
            .addGroup(TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TESTBGLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(210, 210, 210))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TESTBGLayout.createSequentialGroup()
                        .addGroup(TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(history, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(qData, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(player, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(scord, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dbSource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(playerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(showscord, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                            .addComponent(hScord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(65, 65, 65)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TESTBGLayout.createSequentialGroup()
                    .addComponent(toLoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(303, 303, 303))))
	        );
	        TESTBGLayout.setVerticalGroup(
	            TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(TESTBGLayout.createSequentialGroup()
	                .addGap(44, 44, 44)
	                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(dbSource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(qData, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(29, 29, 29)
	                .addGroup(TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(playerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(player, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(27, 27, 27)
	                .addGroup(TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(scord, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(showscord, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(27, 27, 27)
	                .addGroup(TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(history, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
	                    .addComponent(hScord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addGap(32, 32, 32)
	                .addComponent(toLoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	        );
	
	    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setHorizontalGroup(
	        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addComponent(jp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	    );
	    layout.setVerticalGroup(
	        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addComponent(jp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	    );
	
	    pack();
	}                       
        

	public static void main(String[] args) {
		Scord_Single s = new Scord_Single();
	}

	private javax.swing.JLabel qData;
	private javax.swing.JPanel jp;
	private javax.swing.JLabel title;
    private javax.swing.JLabel dbSource;
    private javax.swing.JLabel player;
    private javax.swing.JLabel playerName;
    private javax.swing.JLabel scord;
    private javax.swing.JLabel showscord;
    private javax.swing.JLabel hScord;
    private javax.swing.JLabel history;
    private javax.swing.JButton toLoginBtn;
}