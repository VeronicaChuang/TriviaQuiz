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

public class Scord extends JFrame {
	private Connection conn;//member資料上傳
	protected String userN="", quedb="", logindb="";
	protected int userScords=0; //本次成績
	private int record=0;//歷史最高分
	private int roomid=-1;
	private int competitorScord =-1;
	private String competitor="";
	
	public Scord() {
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
	
	public Scord(String userName, int point, String systemdb, String histroyRec, String scordDB, int rID, int compeS, String comName) {
		userN=userName; userScords =point; quedb =systemdb; record= Integer.parseInt(histroyRec); logindb=scordDB; roomid=rID; competitorScord=compeS;
		competitor = comName;
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
	
	private void deleteOldRoom(){
		try {
			PreparedStatement deleteRoom = conn.prepareStatement("delete from iii15room where id='"+roomid+"'");
			deleteRoom.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	private void insertScord(){
		
		if(userScords>record){	
			try {
				String sql = "update iii15member set highscord='"+userScords+"' where account='"+userN+"' and quesdb='"+quedb+"'";
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
        vsName = new javax.swing.JLabel();
        vsScord = new javax.swing.JLabel();
        vsHScord = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();       
        jPanel3 = new javax.swing.JPanel();        
        jPanel4 = new javax.swing.JPanel();       
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trivia Quiz---"+userN);

        jp.setMaximumSize(new java.awt.Dimension(800, 800));
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1.setOpaque(false);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        title.setFont(new java.awt.Font("Comic Sans MS", 1, 48)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Scores");

        qData.setFont(new java.awt.Font("Comic Sans MS", 1, 32)); // NOI18N
        qData.setForeground(new java.awt.Color(204, 255, 255));
        qData.setText("Question Data:");        

        player.setFont(new java.awt.Font("Comic Sans MS", 1, 32)); // NOI18N
        player.setForeground(new java.awt.Color(204, 255, 255));
        player.setText("Player:");

        scord.setFont(new java.awt.Font("Comic Sans MS", 1, 32)); // NOI18N
        scord.setForeground(new java.awt.Color(204, 255, 255));
        scord.setText("Scores");

        dbSource.setFont(new java.awt.Font("Comic Sans MS", 3, 30)); // NOI18N
        dbSource.setForeground(new java.awt.Color(245, 78, 0));
        dbSource.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dbSource.setText(logindb);

        playerName.setFont(new java.awt.Font("Comic Sans MS", 3, 30)); // NOI18N
        playerName.setForeground(new java.awt.Color(82, 82, 163));
        playerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerName.setText(userN);

        showscord.setFont(new java.awt.Font("Comic Sans MS", 3, 30)); // NOI18N
        showscord.setForeground(new java.awt.Color(82, 82, 163));
        showscord.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showscord.setText(""+userScords);
        
        history.setFont(new java.awt.Font("Comic Sans MS", 3, 30)); // NOI18N
        history.setForeground(new java.awt.Color(204, 255, 255));
//        history.setText("Height Scords:");

        hScord.setFont(new java.awt.Font("Comic Sans MS", 3, 30)); // NOI18N
        hScord.setForeground(new java.awt.Color(82, 82, 163));
        hScord.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        hScord.setText(""+record);

        toLoginBtn.setFont(new java.awt.Font("Comic Sans MS", 0, 28)); // NOI18N
        toLoginBtn.setText("Play Again");
        toLoginBtn.setBorder(null);
        toLoginBtn.addMouseListener(new MouseAdapter() {   
        	@Override
        	public void mouseClicked(java.awt.event.MouseEvent e) {
	    		deleteOldRoom();//關掉舊連線
	        	logIn frame = new logIn();//帶參數跳到main頁面	
	    		frame.setVisible(true);
	    		dispose(); //關掉原本的視窗
        	};
		});
        
        vsName.setFont(new java.awt.Font("Comic Sans MS", 0, 28)); // NOI18N
        vsName.setForeground(new java.awt.Color(102, 102, 0));
        vsName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vsName.setText(""+competitor);

        vsScord.setFont(new java.awt.Font("Comic Sans MS", 0, 28)); // NOI18N
        vsScord.setForeground(new java.awt.Color(102, 102, 0));
        vsScord.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vsScord.setText(""+competitorScord);

        vsHScord.setFont(new java.awt.Font("Comic Sans MS", 0, 28)); // NOI18N
        vsHScord.setForeground(new java.awt.Color(102, 102, 0));
        vsHScord.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        vsHScord.setText("jLabel4");
        
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2.setOpaque(false);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3.setOpaque(false);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(player, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(history, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(qData, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(player, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scord, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(history, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4.setOpaque(false);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dbSource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hScord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(showscord, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(playerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(vsScord, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                            .addComponent(vsHScord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(vsName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dbSource, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vsName, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerName, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vsScord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(showscord, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hScord, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vsHScord, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        
        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5.setOpaque(false);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(toLoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(265, 265, 265))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addComponent(toLoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        javax.swing.GroupLayout TESTBGLayout = new javax.swing.GroupLayout(jp);
        jp.setLayout(TESTBGLayout);
        TESTBGLayout.setHorizontalGroup(
            TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TESTBGLayout.createSequentialGroup()
                .addGroup(TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TESTBGLayout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TESTBGLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(TESTBGLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(TESTBGLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        TESTBGLayout.setVerticalGroup(
            TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TESTBGLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TESTBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TESTBGLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(TESTBGLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        
        

	public static void main(String[] args) {
		Scord s = new Scord();
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
    private javax.swing.JLabel vsHScord;
    private javax.swing.JLabel vsName;
    private javax.swing.JLabel vsScord;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
}