import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

public class waitConn extends JFrame {
	private Connection conn;
	private Timer timer1;
	private Timer timer2;
	private Timer timer3;
	private clock clockTask;
	private int sec = 20;
	protected String nowPlayer="";
	protected int isWho =0;  //1是p1, 2是p2
	protected int roomId=-1;
	protected String competitor ="";
	private int t =0;
	protected int randnum=(int)(Math.random()*99);//0-99
	protected String quedb ="";
	
	public waitConn() {
		setSize(800,600);
		setResizable(false);
		setBak("/img/connbg.jpg"); //調用背景方法
		Container c = getContentPane(); //獲取JFrame面板
		jp = new JPanel(); //創建個JPanel
		jp.setOpaque(false); //把JPanel設置為透明 這樣就不會遮住後面的背景 這樣你就能在JPanel隨意加元件了
		c.add(jp);
		setVisible(true);
		initComponents();
	}
	
	public waitConn(String player, int who, String db, int rID) {
		nowPlayer = player; isWho =who; quedb =db; roomId=rID;
		setSize(800,600);
		setResizable(false);
		setBak("/img/connbg.jpg"); //調用背景方法
		Container c = getContentPane(); //獲取JFrame面板
		jp = new JPanel(); //創建個JPanel
		jp.setOpaque(false); //把JPanel設置為透明 這樣就不會遮住後面的背景 這樣你就能在JPanel隨意加元件了
		c.add(jp);
		setVisible(true);
		initComponents();
		databaseConnect();
		
		//timer	
	        timer1 = new Timer(); timer2 = new Timer(); timer3 = new Timer();
			clockTask = new clock();
			timer1.schedule(clockTask, 0, 1000);		
			timer1.schedule(new TimesUp(),21*1000);	
		//check if game start
			if(who ==1){	//玩家1畫面
				timer1.schedule(new ifGStart(), 0, 1000);//一直問是否有房間要開始遊戲
			}else if(who==2){//玩家2畫面
				P2startGame();
			}		
		
		//window close event
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
            	getInfo();//關視窗，整個房間會砍掉
//            	System.out.println("bye");
                System.exit(0);
            }
        });
	}
		
	
	//----timer task
    class clock extends TimerTask{		
		@Override
		public void run() {
			jLabel2.setText(""+sec--);
		}
	}
    
    class TimesUp extends TimerTask{    //等了20秒沒人後，跳單人遊戲
    	@Override 
    	public void run() {
    		timer1.cancel();
    		getInfo();//刪掉資料庫房間資料
    		
    		mainSingle frame = new mainSingle(quedb, randnum, nowPlayer);//帶參數跳到main頁面	
			frame.setVisible(true);	    		
			dispose(); //關掉原本的視窗
    	
    	}
    }
    
    class ifGStart extends TimerTask{
		@Override
		public void run() {
			ifGameStart();			
		}
    	
    }
    
    class goGame extends TimerTask{
		@Override
		public void run() {
			onair();
			main frame = new main(quedb, randnum, nowPlayer, competitor, roomId, isWho);//帶參數跳到main頁面	
			frame.setVisible(true);	    		
			dispose(); //關掉原本的視窗
		}
    	
    }
    
    class setBattle extends TimerTask{

		@Override
		public void run() {
			jLabel2.setText(nowPlayer + "   VS   " + competitor);
		}
    	
    }
    //----timer task end    
    
    private void P2startGame(){ //p2 find room show
    	try{
    	PreparedStatement findRoom = conn.prepareStatement("select * from iii15room where status='play' and id ='"+roomId+"'");
    	ResultSet rs= findRoom.executeQuery();
	    	while(rs.next()){
	    		competitor =rs.getString("player1");
    			timer1.cancel();	
    			timer3.schedule(new setBattle(), 1*1000);//秀對戰資訊
    			timer2.schedule(new goGame(),5*1000);  	//跳main畫面
    			break;
	    	}
    	}catch(Exception a){
    		System.out.println("P2startGame error: "+a.toString());
    		a.printStackTrace();
    	}
    }
    
    private void onair(){
    	try{
    	PreparedStatement statusOnair = conn.prepareStatement("update iii15room set status='onair' where id='"+roomId+"'");
    	statusOnair.execute();
    	
    	}catch(Exception a){
    		System.out.println("onair error: "+a.toString());
    	}
    }
    
    private void ifGameStart(){ //搜尋是否有房間開始遊戲，有的話秀出對戰
    	try{ //p1 game
	    	PreparedStatement findRoom = conn.prepareStatement("select * from iii15room where status='play' and id ='"+roomId+"'");
	    	ResultSet rs= findRoom.executeQuery();
	    	while(rs.next()){
	    		int playRID = Integer.parseInt(rs.getString("id"));
	    		competitor = rs.getString("player2");    
	    		timer1.cancel();
//    			jLabel2.setText(nowPlayer + "   VS   " + competitor);
    			timer3.schedule(new setBattle(), 1*1000); //秀出對戰資訊
    			timer2.schedule(new goGame(),5*1000); //套主遊戲畫面
	    		break;
	    	}
    	}catch(Exception a){
    		System.out.println("ifGameStart error: "+a.toString());
    		a.printStackTrace();
    	}
		
    }
        
	private void setBak(String str){
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
	
	private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new JLabel(new ImageIcon(getClass().getResource("/img/wait.gif")));
//        btnPanel = new javax.swing.JPanel();
//        goSingle = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trivia Quiz---"+nowPlayer);
        setPreferredSize(new java.awt.Dimension(800, 600));

        jp.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Searching players.....");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Count down");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        javax.swing.GroupLayout foundationLayout = new javax.swing.GroupLayout(jp);
        jp.setLayout(foundationLayout);
        foundationLayout.setHorizontalGroup(
            foundationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(foundationLayout.createSequentialGroup()
            	.addContainerGap(134, Short.MAX_VALUE)
                .addGroup(foundationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(113, 113, 113))
        );
        foundationLayout.setVerticalGroup(
            foundationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(foundationLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
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
    }// </editor-fold>                
	
	private void getInfo(){ //關視窗，整個房間會砍掉
		try {
//			PreparedStatement deleteRoom = conn.prepareStatement("delete from room where player1='"+nowPlayer+"' or player2='"+nowPlayer+"'");
			PreparedStatement deleteRoom = conn.prepareStatement("delete from iii15room where id='"+roomId+"'");
			deleteRoom.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	private void databaseConnect(){
	    	try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		String url ="jdbc:mysql://112.104.57.22/iii2003";
				Properties prop = new Properties();
				prop.setProperty("user", "iiiuser");
				prop.setProperty("password", "P@ssw0rd");
//				prop.setProperty("createDatabaseIfNotExist", "true");			
				prop.setProperty("useSSL", "false");
				prop.setProperty("useUnicode", "true");
				prop.setProperty("characterEncoding", "UTF-8");			
				conn = DriverManager.getConnection(url, prop);
				
			//抓到room id &對手
				if(isWho ==1){//P1
					PreparedStatement getRoom1 = 
							conn.prepareStatement("select * from iii15room where player1='"+nowPlayer+"'");
					ResultSet rs= getRoom1.executeQuery();
					while(rs.next()){
						competitor = rs.getString("player2");
						break;
					}
				}else if(isWho ==2){//p2
					PreparedStatement getRoom1 = 
							conn.prepareStatement("select * from iii15room where player2='"+nowPlayer+"'");
					ResultSet rs= getRoom1.executeQuery();
					while(rs.next()){
						competitor = rs.getString("player1");
						break;
					}
				}
				
	        } catch (Exception e) {
				System.out.println("sql conn error: "+ e.toString());
			}
	}	
	
	public static void main(String[] args) {
		waitConn s = new waitConn();
	}
	
	private javax.swing.JPanel jp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
//    private javax.swing.JButton goSingle;
//    private javax.swing.JPanel btnPanel;
}
