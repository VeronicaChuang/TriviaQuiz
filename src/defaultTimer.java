import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class defaultTimer {
	int sec = 20;
	private clock clockTask;
	Timer timer1;
	defaultTimer(){
		timer1 = new Timer();
		clockTask = new clock();
		timer1.schedule(clockTask, 0, 1000);
	}
	
	class clock extends TimerTask{		
		@Override
		public void run() {
			System.out.println(sec--);
		}
	}

	public static void main(String[] args) {
		new defaultTimer();
	}
}
