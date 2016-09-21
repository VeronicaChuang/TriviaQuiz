import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*; 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
  * @author Veronica.Chuang
 */

public class main extends javax.swing.JFrame {
	private Connection conn;
	private String mid = "";
	private String question= "";
	private String answer = "";
	private String op1 = "";
	private String op2 = "";
	private String op3 = "";
	private String realAns1 ="";
	private String realAns2 ="";
	private String realAns3 ="";
	private String realAns4 ="";
	private String quedb="";
	protected String userdb="";
	protected String scordDB="";
	protected String userName="";
	protected String competitor="";
	protected int roomid =-1;
	protected int competitorScord = -1;
	private int queCount=1;
	private boolean noTwice=false;
	private int randQ =0; 
	protected String scordRecord="";
	private int bingo =0;//對的題數
	protected int randK=0;
	protected ArrayList<Integer> list;
	private double seconds;  //user用掉的時間
	private long start, end; //計算nanotime
	private boolean correct=false, seeScord = false;
	protected int userPoints=0;
	protected int isWho =-1;// 1=p1, 2=p2
	private boolean isWrong=true;
	
	//timer
	private int sec = 20;
	private clock clockTask;
	private Timer timer1;
	private Timer timer2;
	private Timer timer3;
	private Timer timer4;
	//timer end
	
	
    public main() {
        initComponents(); 
    }
    
    public main(String question, int randnum, String userN, String vs, int rID, int who) {  //建有帶變數的建構式
    	quedb=question; randQ= randnum; userName=userN; competitor= vs; roomid=rID; isWho=who;
//    	System.out.println("isWho: "+isWho);
    	initComponents();  
        databaseConnect();// get question from db
        setque();//insert question to ui  
        
        //timer
        timer1 = new Timer();
        timer2 = new Timer();
        timer3 = new Timer();
        timer4 = new Timer();
		clockTask = new clock();
		timer1.schedule(clockTask, 0, 1000);
		timer1.schedule(new TimesUp(),21*1000);	
		timer4.schedule(new askingCompeScord(), 8*1000, 1*1000);
		timer4.schedule(new queryDB(), 20*1000, 1*1000);
		
		//ranQ
		list = new ArrayList<Integer>();
        for (int i=1; i<101; i++) {
            list.add(new Integer(i));
        }
	    Collections.shuffle(list);         
	    
	    randK=(int)(Math.random()*99);// randK 0-99
//	    System.out.println("construcion again--------------");
	    
	    //範例：Math.random() 值範圍：0 ~ 0.9999999(無窮小數)
    }
    
    public void setBak(String str){
		((JPanel)this.getContentPane()).setOpaque(false);
		ImageIcon newIcon;
		try{
			BufferedImage bimg = ImageIO.read(logIn.class.getResource(str));
			newIcon = new ImageIcon(new ImageIcon(bimg).getImage().getScaledInstance(850, 600, Image.SCALE_DEFAULT));
			JLabel background = new JLabel(newIcon);this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
			background.setBounds(0, 0, newIcon.getIconWidth(), newIcon.getIconHeight());
		}catch(Exception e){
			System.out.println("setBak error: "+e.toString());
		}
	}
	    
	    @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	private void initComponents() {
	    	
    	setBak("/img/main.jpg");
    	Container c = getContentPane();
    	jPanel1 = new javax.swing.JPanel();
    	c.add(jPanel1);

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        timerLab = new javax.swing.JLabel();
        totalQue = new javax.swing.JLabel();
        questionNum = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        questionField = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        btnAns2 = new javax.swing.JButton();
        btnAns3 = new javax.swing.JButton();
        btnAns4 = new javax.swing.JButton();
        btnAns1 = new javax.swing.JButton();
        showAnswerField = new javax.swing.JLabel();
        nextQue = new javax.swing.JButton();
        supportlabel = new javax.swing.JLabel();
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trivia Quiz---"+userName);
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        jPanel1.setFont(new java.awt.Font("標楷體", 0, 24)); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 600));
        jPanel1.setOpaque(false);
        jPanel5.setOpaque(false);
        
        timerLab.setFont(new java.awt.Font("Comic Sans MS", 1, 48)); // NOI18N
        timerLab.setForeground(new java.awt.Color(255, 0, 0));
        timerLab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerLab.setText("00:00:00");
        timerLab.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        totalQue.setFont(new java.awt.Font("標楷體", 0, 36)); // NOI18N
        totalQue.setForeground(new java.awt.Color(51,51,255));
        totalQue.setText("1/6");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3.setOpaque(false);
        
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(132, Short.MAX_VALUE)
                .addComponent(timerLab, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(totalQue, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(timerLab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalQue, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addContainerGap())
        );

        questionNum.setFont(new java.awt.Font("標楷體", 0, 24)); // NOI18N
        questionNum.setForeground(new java.awt.Color(204, 204, 204));
        questionNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        questionNum.setText("題庫題號");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2.setOpaque(false);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(questionNum, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(questionNum, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4.setOpaque(false);

        questionField.setEditable(false);
//	        questionField.setBackground(new java.awt.Color(255, 255, 204));
        questionField.setColumns(20);
        questionField.setFont(new java.awt.Font("標楷體", 0, 30)); // NOI18N
        questionField.setForeground(new java.awt.Color(51, 51, 51));
        questionField.setLineWrap(true);
        questionField.setRows(3);
        questionField.setText("題目設置區");
        questionField.setToolTipText("");
        questionField.setBorder(null);
        questionField.setFocusable(false);
        questionField.setMaximumSize(new java.awt.Dimension(600, 300));
        questionField.setOpaque(false);
        jScrollPane1.setViewportView(questionField);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4.setOpaque(false);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
        );


        btnAns2.setFont(new java.awt.Font("標楷體", 0, 20)); // NOI18N
        btnAns2.setText("B");
        btnAns2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
        btnAns2.addMouseListener(new MouseAdapter() {        		
			@Override
			public void mousePressed(MouseEvent e) {
				if(!noTwice){
					btnAns2.setBackground(Color.orange); //user選項反深綠
					queCount++; //題數
						if(realAns2.equals(answer)){//check選項與答案是否正確
							isWrong=false;
							btnAns2.setBackground(Color.green);
				    		btnAns2.setOpaque(true);
							btnAns4.repaint();
							bingo++;
				    		correct =true; //show正確答案
				    		showAnswerField.setText("Answer: "+answer);
						}else{
							isWrong =true;
							timerShowAns(); //show正確答案
						}
					timer1.schedule(new TimesUp(),0); //stop timer
					measure();//measure time takes
					noTwice=true; //點過選項後只能點next
				}
			}
        });
        
        btnAns3.setFont(new java.awt.Font("標楷體", 0, 20)); // NOI18N
        btnAns3.setText("C");
        btnAns3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
        btnAns3.addMouseListener(new MouseAdapter() {	
			@Override
			public void mousePressed(MouseEvent e) {
				if(!noTwice){
					btnAns3.setBackground(Color.orange);
					queCount++;
						if(realAns3.equals(answer)){//check選項與答案是否正確
							isWrong=false;	
							btnAns3.setBackground(Color.green);
				    		btnAns3.setOpaque(true);
							btnAns4.repaint();
				    		bingo++;
				    		correct =true; //show正確答案
				    		showAnswerField.setText("Answer: "+answer);
						}else{
							isWrong =true;
							timerShowAns(); //show正確答案
						}
					timer1.schedule(new TimesUp(),0);
					measure();
					noTwice=true;
				}	
			}
        });

        btnAns4.setFont(new java.awt.Font("標楷體", 0, 20)); // NOI18N
        btnAns4.setText("D");
        btnAns4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
        btnAns4.addMouseListener(new MouseAdapter() {	
			@Override
			public void mousePressed(MouseEvent e) {
				if(!noTwice){
					btnAns4.setBackground(Color.orange);	
					queCount++;
						if(realAns4.equals(answer)){//check選項與答案是否正確
							isWrong =false;
							btnAns4.setBackground(Color.green);
				    		btnAns4.setOpaque(true);
							btnAns4.repaint();
				    		bingo++;
				    		correct =true; //show正確答案
				    		showAnswerField.setText("Answer: "+answer);
						}else{
							isWrong =true;
							timerShowAns(); //show正確答案
						}
					timer1.schedule(new TimesUp(),0);
					measure();
					noTwice=true;
				}
			}
        });

        btnAns1.setFont(new java.awt.Font("標楷體", 0, 20)); // NOI18N
        btnAns1.setText("A");
        btnAns1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
        btnAns1.addMouseListener(new MouseAdapter() {	
			@Override
			public void mousePressed(MouseEvent e) {
				if(!noTwice){
					btnAns1.setBackground(Color.orange);
					queCount++;
						if(realAns1.equals(answer)){//check選項與答案是否正確
							isWrong =false;
							btnAns1.setBackground(Color.green);
				    		btnAns1.setOpaque(true);
							btnAns4.repaint();
							bingo++;
				    		correct =true; //show正確答案
				    		showAnswerField.setText("Answer: "+answer);
						}else{
							isWrong =true;
							timerShowAns(); //show正確答案
						}
					timer1.schedule(new TimesUp(),0);
					measure();
					noTwice=true;
				}
			}
        });
        

        showAnswerField.setFont(new java.awt.Font("標楷體", 3, 26)); // NOI18N
        showAnswerField.setForeground(new java.awt.Color(128, 255, 0));
        showAnswerField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showAnswerField.setText("");
        
        nextQue.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        nextQue.setForeground(new java.awt.Color(102, 0, 102));
        nextQue.setText("Next");
        nextQue.addMouseListener(new MouseAdapter() {
        	@Override
			public void mousePressed(MouseEvent e) {
				if(noTwice){
					if(queCount==6){
						//timer
						sec =20; //timer歸零到20
						timer1 = new Timer();
						clockTask = new clock();
						timer1.schedule(clockTask, 0, 1000);
						timer1.schedule(new TimesUp(),21*1000);
						//timer-----end
						
						getRanQ();			//題目跳亂數	        
				        noTwice=false;      //點完next只能點選項
						databaseConnect();  //重新撈題庫
						setque();           //題庫設定到ui
						giveScords();       //算出當題答題花費秒數
						nextQue.setText("Scores");
						
						getDefault();       //ui初始化					
						
			        }else if(queCount >6){
			        	insertScord();//將本次資料update到資料庫
			        	seeScord = true;
//			        	timer3.schedule(new queryDB(),100);//若找不到對手成績，一直問資料庫	
			        			        	
			        }else if(queCount<6){
			        	//timer
						sec =20; //timer歸零到20
						timer1 = new Timer();
						clockTask = new clock();
						timer1.schedule(clockTask, 0, 1000);
						timer1.schedule(new TimesUp(),21*1000);
						//timer-----end
						
						getRanQ();			//題目跳亂數	        
				        noTwice=false;      //點完next只能點選項
						databaseConnect();  //重新撈題庫
						setque();           //題庫設定到ui
						giveScords();       //算出當題答題花費秒數
						
						getDefault();       //ui初始化
			        }
				}
			}
		});
        
        supportlabel.setText("");
        supportlabel.setVisible(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(showAnswerField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnAns1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAns4, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                            .addComponent(btnAns2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAns3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nextQue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(supportlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
	        jPanel5Layout.setVerticalGroup(
	            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel5Layout.createSequentialGroup()
	            .addContainerGap()
	            .addComponent(btnAns1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	            .addComponent(btnAns2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                .addComponent(supportlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(btnAns3, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	            .addComponent(btnAns4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addGap(18, 18, 18)
	            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                .addComponent(showAnswerField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addComponent(nextQue, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
	            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	
	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addContainerGap())
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        );
	
	        pack();
	    }// </editor-fold>                            
	
    //----timer task
    class clock extends TimerTask{		
		@Override
		public void run() {
			timerLab.setText("Time: "+sec--);
			
		}
	}
    
    class TimesUp extends TimerTask{    //時間到停止timer
	    	@Override 
	    	public void run() {
	    		if(sec<=0){
	    			queCount++;
	    		}
	    		timer1.cancel();
	    		noTwice=true;
	    		timerShowAns();
	    }
    }
    
    class slowDownshowScord extends TimerTask{    	
		@Override
		public void run() {
			gotoScord();			
		}
    }
    
    class queryDB extends TimerTask{
		@Override
		public void run() {
			queryCompe();
		}
    }
    //----timer task---end
	 
    class askingCompeScord extends TimerTask{
		@Override
		public void run() {
			if(competitorScord!=-1 && seeScord){	
        		timer2.schedule(new slowDownshowScord(), 0);
        		timer3.cancel();
        		timer4.cancel();
//        		System.out.println("competitorScord got!");
        	}
		}
    }
    
    private void queryCompe(){
    	try {
			if(isWho == 1){
				PreparedStatement thisScord = conn.prepareStatement("select p2Scord from iii15room where id ='"+roomid+"'");		
				ResultSet rs = thisScord.executeQuery();
				while(rs.next()){
					competitorScord = Integer.parseInt(rs.getString("p2Scord"));
					break;
				}
				
			}else if(isWho ==2){
				PreparedStatement thisScord = conn.prepareStatement("select p1Scord from iii15room where id ='"+roomid+"'");		
				ResultSet rs = thisScord.executeQuery();
				while(rs.next()){
					competitorScord = Integer.parseInt(rs.getString("p1Scord"));
					break;
				}
			}
		} catch (Exception e) {	
			System.out.println("queryCompe error"+e.toString());;
		}
    }
    
	private void insertScord(){//將本次資料update到資料庫
		try {//isWho
			if(isWho == 1){
				PreparedStatement thisScord = conn.prepareStatement("update iii15room set p1Scord='"+userPoints+"' where id ='"+roomid+"'");		
				thisScord.execute();
			}else if(isWho ==2){
				PreparedStatement thisScord = conn.prepareStatement("update iii15room set p2Scord='"+userPoints+"' where id ='"+roomid+"'");		
				thisScord.execute();
				
			}
			
		} catch (Exception e) {	
			System.out.println("insertScord error"+e.toString());;
		}
		//userPoints
	}
    
    //get user stop time
    public void measure(){
    	end = System.nanoTime(); 
    	long elapsedTime = end - start;

    	//convert to seconds 
    	seconds = (double)elapsedTime / 1000000000.0f;
    	end =0; //歸零
    	start =0;
   }
    
    //get user stop time--------- end
    private void giveScords(){
    	//bingo 題數, seconds花費秒數
    	int totalSec = (int)seconds;
    	
    	if(correct){
    		if(totalSec<=5){
    			userPoints = userPoints + 150;
    		}else if(totalSec<=10){
    			userPoints = userPoints + 125;
    		}else if(totalSec<=15){
    			userPoints = userPoints + 100;
    		}else if(totalSec<=20){
    			userPoints = userPoints + 80;
    		}
    	}
    }
    
    private void gotoScord(){
    	if(quedb.equals("iii15engque")){
    		scordDB = "English";
    	}else if(quedb.equals("iii15lifeque")){
    		scordDB = "The little things in life";
    	}
    	getHscord();//取該帳號最高成績
    	//下一頁
    	Scord frame = new Scord(userName,userPoints,quedb,scordRecord,scordDB,roomid, competitorScord, competitor);//帶參數跳到main頁面	
		frame.setVisible(true);
		dispose(); //關掉原本的視窗

    }
    
    private void getDefault(){ //清空上次作答內容
    	btnAns1.setBackground(UIManager.getColor("Button.background"));
    	btnAns2.setBackground(UIManager.getColor("Button.background"));
    	btnAns3.setBackground(UIManager.getColor("Button.background"));
    	btnAns4.setBackground(UIManager.getColor("Button.background"));
    	showAnswerField.setText("");
    	correct =false;
    	isWrong=true;
    	totalQue.setText(queCount+"/6");
    	
    }
    
    private void getRanQ(){ //random取得下一題
	    randQ =list.get(randK); // randK 0-99
	    	
    	if(randK<85){
	    	randK++;
	    }else if(randK>=85 && randK<=94){
	    	randK  = 16;
	    }else if(randK>94){
	    	randK--;
	    }
    }
    
    private void timerShowAns(){
    	showAnswerField.setText("Answer: "+answer);
    	if(isWrong){
	    	if(realAns1.equals(answer)){ //答案秀紅色
	    		btnAns1.setBackground(Color.red);
	    		btnAns1.setOpaque(true);
	    	}else if(realAns2.equals(answer)){
	    		btnAns2.setBackground(Color.red);
	    		btnAns2.setOpaque(true);
	    	}else if(realAns3.equals(answer)){
	    		btnAns3.setBackground(Color.red);
	    		btnAns3.setOpaque(true);
	    	}else if(realAns4.equals(answer)){
	    		btnAns4.setBackground(Color.red);
	    		btnAns4.setOpaque(true);
	    	}
    	}
    }
    
    
    private void setque(){ //question into UI
    	int opid1=0, opid2=0, opid3=0, opid4=0;
    	
    	questionNum.setText(mid);
    	questionField.setText(question);
    	String ans[]={answer,op1,op2,op3};
    //get random 0-3 without repeat	
    	 ArrayList<Integer> list = new ArrayList<Integer>();
         for (int i=0; i<4; i++) {
             list.add(new Integer(i));
         }
         
         Collections.shuffle(list);        //亂序排序  
         opid1 =list.get(0);
         opid2 =list.get(1);
         opid3 =list.get(2);
         opid4 =list.get(3);
              
    	 btnAns1.setText(ans[opid2]); //set answer to btn 
    	 btnAns2.setText(ans[opid1]);
    	 btnAns3.setText(ans[opid4]);
    	 btnAns4.setText(ans[opid3]);
    	 
      //get btn text
    	 realAns1 = btnAns1.getText();
     	 realAns2 = btnAns2.getText();
     	 realAns3 = btnAns3.getText();
     	 realAns4 = btnAns4.getText();
    }
    
    private void databaseConnect(){
    	start = System.nanoTime();
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
		//random get question from db
			PreparedStatement getQfromdb = 
					conn.prepareStatement("Select * from "+quedb+" where id='"+randQ+"'");
			ResultSet rs = getQfromdb.executeQuery();
			while(rs.next()){
				mid = rs.getString("id");
				question= rs.getString("question");
				answer = rs.getString("answer");
				op1 = rs.getString("op1");
				op2 = rs.getString("op2");
				op3 = rs.getString("op3");
				break;
			}
	
        } catch (Exception e) {
			System.out.println("sql conn error: "+ e.toString());
		}
    }
    
    private void getHscord(){		//取得成績記錄
		String sRecord="";
    	String tempdb ="";
	    	if(quedb.equals("iii15engque")){
	    		tempdb = "engque";
	    	}else if(quedb.equals("iii15lifeque")){
	    		tempdb = "lifeque";
	        	
	    	}
			String sql = "select highscord from iii15member where account='"+userName+"' and quesdb='"+tempdb+"'";

		try {
			PreparedStatement changeCollate = conn.prepareStatement(sql);		
			ResultSet rs = changeCollate.executeQuery();
			while(rs.next()){
				sRecord = rs.getString("highscord");
				break;
			}			
		} catch (Exception e) {	
			System.out.println("getHscord error"+e.toString());;
			e.printStackTrace();
		}
		scordRecord = sRecord;//String to int
 	}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
//        new defaultTimer();
    }
    
    private javax.swing.JButton nextQue;
    private javax.swing.JButton btnAns1;
    private javax.swing.JButton btnAns2;
    private javax.swing.JButton btnAns3;
    private javax.swing.JButton btnAns4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea questionField;
    private javax.swing.JLabel questionNum;
    private javax.swing.JLabel showAnswerField;
    private javax.swing.JLabel timerLab;
    private javax.swing.JLabel totalQue;
    private javax.swing.JLabel supportlabel;
    // End of variables declaration                                  
}
