//package skeletonCodeAssgnmt2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JPanel;

public class WordPanel extends JPanel implements Runnable {
	public static volatile boolean done;
	private WordRecord[] words;
	private int noWords;
	private int maxY;
	public static  Score score= new Score();
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
		for (int i=0; i<noWords;i++){
			execute x = new execute(i);
			x.start();
		}
	}

	

	public synchronized boolean move(int i){
		boolean dropped = words[i].dropped();
		words[i].drop(1);
		if(dropped){
			words[i].resetWord();
			score.missedWord();  
		}
		repaint(); 
		return dropped;
	}

	public synchronized boolean find(int i){
		boolean answer=false;
		if(words[i].matchWord(word)){
			score.caughtWord(word.length());
			answer=true;
			repaint();
		}
		return answer;
	}

}


