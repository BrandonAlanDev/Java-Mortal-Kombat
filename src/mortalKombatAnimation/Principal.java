package mortalKombatAnimation;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private Image[] img;
	private Image[] img2;
	private Image fondo;
	private InputStream imgStream;
	private InputStream imgStream2;
	private BufferStrategy estrategia;
	private int numeroImagen;
	private int numeroImagen2;
	private Timer timer1;
	private int x=50;
	private int x2=250;
	private boolean noMueve;
	private boolean noMueve2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Principal();
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		super("Animacion con Sprites");
		this.setSize(600,250);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.createBufferStrategy(2);
		estrategia=getBufferStrategy();
		numeroImagen=1;
		numeroImagen2=1;
		noMueve=true;
		this.addKeyListener(k1);
		cargarImagen();
		repaint();
		run();
	}
	public void run() {
		timer1=new Timer(200,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(noMueve==true) {
					numeroImagen++;
					if(numeroImagen>=7) numeroImagen=1;
				}
				else {
					if(numeroImagen<=8) numeroImagen=8;
					numeroImagen++;
					if(numeroImagen>=15)numeroImagen=8;
				}
				if(noMueve2==true) {
					numeroImagen2++;
					if(numeroImagen2>=7) numeroImagen2=1;
				}
				else {
					if(numeroImagen2<=8) numeroImagen2=8;
					numeroImagen2++;
					if(numeroImagen2>=15)numeroImagen2=8;
				}
				repaint();
			}
		});
		timer1.start();
	}
	public void cargarImagen() {
		img= new Image[20];
		img2= new Image[20];
		try {
			imgStream=Principal.class.getResourceAsStream("images/fondo.jpg");
			imgStream2=Principal.class.getResourceAsStream("images/fondo.jpg");
			fondo=ImageIO.read(imgStream);
			for(int i=0;i<16;i++) {
				imgStream=Principal.class.getResourceAsStream("images/"+i+".png");
				img[i]=ImageIO.read(imgStream);
				
			}
			for(int i=0;i<16;i++) {
				imgStream2=Principal.class.getResourceAsStream("images/sub"+i+".png");
				img2[i]=ImageIO.read(imgStream);
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	
	KeyListener k1=new KeyListener() {
		public void keyPressed(KeyEvent e) {
			int codigo= e.getKeyCode();
			if(codigo==KeyEvent.VK_D) {
				noMueve=false;
				x=x+5;
			} else if(codigo==KeyEvent.VK_A) {
				noMueve=false;
				x=x-5;
			}else if(codigo==KeyEvent.VK_RIGHT) {
				noMueve2=false;
				x2=x2-5;
			}else if(codigo==KeyEvent.VK_LEFT) {
				noMueve2=false;
				x2=x2-5;
			}
		}
		public void keyReleased(KeyEvent e) {
			noMueve=true;
			noMueve2=true;
		}
		public void keyTyped(KeyEvent e) {
			
		}
	};
	public void paint(Graphics g) {
		Graphics2D g2D=(Graphics2D)estrategia.getDrawGraphics();
		g2D.drawImage(fondo,0,0,600,250,null);
		g2D.drawImage(img[numeroImagen],x,80,90,156,null);
		g2D.drawImage(img2[numeroImagen2],x2,80,90,156,null);
		estrategia.show();
		repaint();
	}

}
