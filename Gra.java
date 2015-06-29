import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Gra extends Applet implements MouseListener, MouseMotionListener, KeyListener
{
	int mouseX = 0, mouseY = 0; 
	Byte msg2 = 0;
	short skala = 800;
	byte start=0;
	short zoom = 80;
	byte kej = 0;
	int tab[][] = new int[skala/(skala/zoom)][skala/(skala/zoom)];
	int kopia[][] = new int[skala/(skala/zoom)][skala/(skala/zoom)];
	public void init(){
		setSize(skala,skala);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.addKeyListener(this);   
	}
	/////////////////////////////////////////////////////obs³uga zdarzeñ/////////////////////////////////////////////////
	public void mouseEntered(MouseEvent e){
		start=0;
		paint(getGraphics());
	}
	public void mouseExited(MouseEvent e){
		start=0;
		paint(getGraphics());
	} 
	public void mouseMoved(MouseEvent e){} 
	public void mouseClicked(MouseEvent e){}
	public void keyTyped (KeyEvent e){}  
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		msg2 = 1;
		paint(getGraphics());
	}  
	public void mousePressed(MouseEvent e){
		mouseX = e.getX();
		mouseY = e.getY();
		msg2 = 1;
		paint(getGraphics());
	}
	public void mouseReleased(MouseEvent e){
		msg2 = 0;
		paint(getGraphics());
	}
	public void keyPressed ( KeyEvent e){
		kej=1;
		paint(getGraphics());
	}  
	public void keyReleased ( KeyEvent e ){  
		kej=0;
		paint(getGraphics());
	} 
	//////////////////////////funkcje pocz¹tkowe sprawdzaj¹ce co robi mysz i rysuj¹ce krate
	public void mysz(){
		if(mouseY%(skala/zoom)!=0){
			if(mouseY%(skala/zoom)<(skala/zoom)){
				mouseY=mouseY-(mouseY%(skala/zoom));
			}
		}
		if(mouseX%(skala/zoom)!=0){
			if(mouseX%(skala/zoom)<(skala/zoom)){
				mouseX=mouseX-(mouseX%(skala/zoom));
			}
		}
	}
	public void krata(Graphics g){
		for(int x=0; x<skala; x+=(skala/zoom)){
			g.drawLine(skala-x, 0, skala-x, skala);
		}
		for(int y=0; y<skala; y+=(skala/zoom)){
			g.drawLine(0, skala-y, skala, skala-y);
		}
	}
	///////////////////////////////~~~~~~END~~~~~~~///////////////////////////////////////////////////////
	////////////// FUNKCJA KTÓRA SPRAWDZA CZY DANA KOMÓRKA BÊDZIE ¯YWA CZY NIE////////////////////////////
	public void czekdys(){
		for(int keyX=1; keyX<skala/(skala/zoom)-1; keyX++){
			for(int keyY=1; keyY<skala/(skala/zoom)-1; keyY++){
				if(tab[keyX][keyY]==1){
					if(tab[keyX-1][keyY]+tab[keyX+1][keyY]+tab[keyX][keyY-1]+tab[keyX][keyY+1]+tab[keyX+1][keyY+1]+tab[keyX-1][keyY+1]+tab[keyX+1][keyY-1]+tab[keyX-1][keyY-1]==2){
						kopia[keyX][keyY]=1;
					}
					else if(tab[keyX-1][keyY]+tab[keyX+1][keyY]+tab[keyX][keyY-1]+tab[keyX][keyY+1]+tab[keyX+1][keyY+1]+tab[keyX-1][keyY+1]+tab[keyX+1][keyY-1]+tab[keyX-1][keyY-1]==3){
						kopia[keyX][keyY]=1;
					}
					else{
						kopia[keyX][keyY]=0;
					}
				}
				if(tab[keyX][keyY]==0){
					if(tab[keyX-1][keyY]+tab[keyX+1][keyY]+tab[keyX][keyY-1]+tab[keyX][keyY+1]+tab[keyX+1][keyY+1]+tab[keyX-1][keyY+1]+tab[keyX+1][keyY-1]+tab[keyX-1][keyY-1]==3){
						kopia[keyX][keyY]=1;
					}
					else{
						kopia[keyX][keyY]=0;
					}
				}
			}
		}
		for(int keyY=1; keyY<skala/(skala/zoom)-1; keyY++){
			for(int keyX=1; keyX<skala/(skala/zoom)-1; keyX++){
				tab[keyX][keyY]=kopia[keyX][keyY];
			}
		}
	}  
	/////////////////////////G£ÓWNA FUNKCJA PAINT/////////////////////////////////////
	public void paint(Graphics g){
		mysz();
		if(start<2){
			krata(g);
			start++;
		}
		if(kej==0){
			if(msg2==1){
				tab[mouseX/(skala/zoom)][mouseY/(skala/zoom)]=1;
				System.out.println((mouseX/(skala/zoom))+ " " + (mouseY/(skala/zoom)));
				g.fillRect(mouseX, mouseY, (skala/zoom), (skala/zoom));
			}
		}
		else if(kej==1){
			czekdys();
			for(int keyX=0; keyX<skala/(skala/zoom); keyX++){
				for(int keyY=0; keyY<skala/(skala/zoom); keyY++){
					if(tab[keyX][keyY]==1){				
						g.setColor(Color.BLUE);
						g.fillRect(keyX*(skala/zoom), keyY*(skala/zoom), (skala/zoom), (skala/zoom));
						g.setColor(Color.BLACK);					
						g.drawRect(keyX*(skala/zoom), keyY*(skala/zoom), (skala/zoom), (skala/zoom));	
					}
					else if(tab[keyX][keyY]==0){
						g.setColor(Color.WHITE);
						g.fillRect(keyX*(skala/zoom), keyY*(skala/zoom), (skala/zoom), (skala/zoom));
						g.setColor(Color.BLACK);					
						g.drawRect(keyX*(skala/zoom), keyY*(skala/zoom), (skala/zoom), (skala/zoom));	
					}
				}
			}
		}
	}
}