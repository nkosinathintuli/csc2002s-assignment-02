//package skeletonCodeAssgnmt2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Thread.sleep;
import javax.swing.JLabel;

public class WordPanel extends JPanel implements Runnable {
	public static boolean done=false;
	private WordRecord[] words;
	private int noWords;
	private int maxY;
	public static  Score score = new Score();
	public int p=0;
	public String word="";
	public int total=0;

	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		g.clearRect(0,0,width,height);
		g.setColor(Color.red);
		g.fillRect(0,maxY-10,width,height);

		g.setColor(Color.black);
		g.setFont(new Font("Helvetica", Font.PLAIN, 26));
		//draw the words
		//animation must be added 
		for (int i=0;i<noWords;i++){
			//g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
			g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words	
		}

	}

	WordPanel(WordRecord[] words, int maxY) {
		this.words=words; //will this work?
		noWords = words.length;
		done=false;
		this.maxY=maxY;
	}

	public void run() {
		//add in code to animate this
		for (int k=0; k<noWords;k++){
			(new execute(k)).start();
		}
	}

	public class execute{
		Timer timer = new Timer();
		TimerTask ttask;
		int k;
		int speed;
		public execute(int k) {
			this.ttask = new TimerTask() {
				@Override
				public void run() {
					if (find(k) || move(k)){
						timer.cancel();
						//execute ex= new execute(i);
						setSpeed(words[k].getSpeed()/10);
						(new execute(k)).start();
					}
					if(score.getTotal()>=total){
						timer.cancel();
					}
					if(done){
						timer.cancel();
						score.resetScore();
						words[k].resetWord();
					}
				}
			};
		this.k=k;
		this.speed = words[k].getSpeed()/10;
		}

		public void setSpeed(int speed){
			this.speed = speed;
		}
		public  void update(){
			timer.cancel();
		}
		public  void start(){
			timer.schedule(ttask,0,speed);
		}
	}

	public synchronized boolean move(int j){
		boolean dropped = words[j].dropped();
		words[j].drop(1);
		if(dropped){
			words[j].resetWord();	score.missedWord();
		}
		repaint();
		return dropped;
	}

	public synchronized boolean find(int m){
		boolean ans=false;
		if(words[m].matchWord(word)){
			score.caughtWord(word.length());
			ans=true;
			repaint();
		}
		return ans;
	}
}
