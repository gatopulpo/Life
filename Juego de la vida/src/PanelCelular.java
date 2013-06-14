import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class PanelCelular extends JPanel implements MouseListener {
	
	private int alto;
	private int ancho;
	private int tamanoCelula = 15;
	int porcent = 0;
	private int numeroVivas;
	
	private EspacioCelular espacio;
	
	public PanelCelular(int celulasX, int celulasY){
		
		
	espacio = new EspacioCelular(celulasX, celulasY, porcent);
	
		alto = tamanoCelula * celulasY;
		ancho = tamanoCelula * celulasX;
		
		setPreferredSize(new Dimension(ancho, alto));
		addMouseListener(this);
	}
	
	public int getTamanoCelula() {
		return tamanoCelula;
	}
	//instanciamis el metodo generacion de la clase espacio celular para pinatarlo 
	public void generacion() {
		espacio.generacion();
		repaint();
	}
	//metodo para pintar las celulas y la cuadricula.
	public void paint(Graphics g){
		super.paint(g);
		int aux;
		
			for (int i = 0; i < espacio.getFilas(); i++) {
				for (int j = 0; j <espacio.getCols(); j++) {
					if (espacio.getEspacio()[i][j] == Celula.VIVA){
						g.setColor(Color.black);
						
						g.fillRect(j*tamanoCelula, i*tamanoCelula, tamanoCelula, tamanoCelula);
						
					}
				}
				
			}
			
		g.setColor(Color.black);
		for (int i = 0; i <= espacio.getFilas(); i++) {
			aux = i * tamanoCelula;
			g.drawLine(0, aux, alto, aux);
			
		}
		
		for (int j = 0; j <= espacio.getCols(); j++) {
			aux = j * tamanoCelula;
			g.drawLine(aux, 0, aux, ancho);
		}
		
		
	}
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}

	@Override//metodo para matar o resucitar celulas donde clickemos
	public void mousePressed(MouseEvent e) {
		int tamCel= getTamanoCelula();
		int fila = (e.getY()/tamCel);
		int columna = (e.getX()/tamCel);
		
		System.out.print(fila + ",");
		System.out.println(columna);
		
		
		if (espacio.getEspacio()[fila][columna]== Celula.VIVA){
			espacio.getEspacio()[fila][columna]= Celula.MUERTA;
		}else {
			espacio.getEspacio()[fila][columna]= Celula.VIVA;
		}
		repaint();
	}
	

	@Override
	public void mouseReleased(MouseEvent e) {
	
	}
	
	public void vaciar(){
		espacio = new EspacioCelular(espacio.getCols(), espacio.getFilas(), 0);				
	
			repaint();
	}
	
	public void pintarNave(){
		espacio = new EspacioCelular(espacio.getCols(), espacio.getFilas(), 0);
		
		espacio.setViva((espacio.getCols()/2), (espacio.getFilas()/2)-1);
		espacio.setViva(((espacio.getCols()/2)-1), (espacio.getFilas()/2));
		espacio.setViva(((espacio.getCols()/2)+1), (espacio.getFilas()/2));
		espacio.setViva(((espacio.getCols()/2)-1), (espacio.getFilas()/2)-1);
		espacio.setViva((espacio.getCols()/2), (espacio.getFilas()/2)+1);

		repaint();
	}
	
	public void pintarPlaneador(){
		espacio = new EspacioCelular(espacio.getCols(), espacio.getFilas(), 0);

		espacio.setViva(19,19);
		espacio.setViva(19,20);
		espacio.setViva(20,19);
		espacio.setViva(21,20);
		espacio.setViva(19,21);

		repaint();		
	}
	
	public void pintarGalaxia(){
		espacio = new EspacioCelular(espacio.getCols(), espacio.getFilas(), 0);
		//izquierda
		espacio.setViva(13,16);	espacio.setViva(14,16);	espacio.setViva(15,16);
		espacio.setViva(16,16);	espacio.setViva(17,16); espacio.setViva(18,16);
		espacio.setViva(13,17);	espacio.setViva(14,17);	espacio.setViva(15,17);
		espacio.setViva(16,17);	espacio.setViva(17,17);	espacio.setViva(18,17);		
		//arriba
		espacio.setViva(13,19);	espacio.setViva(13,20);	espacio.setViva(13,21);
		espacio.setViva(13,22);	espacio.setViva(13,23);	espacio.setViva(13,24);
		espacio.setViva(14,19);	espacio.setViva(14,20);	espacio.setViva(14,21);
		espacio.setViva(14,22);	espacio.setViva(14,23);	espacio.setViva(14,24);
		
		//derecha
		espacio.setViva(16,23);	espacio.setViva(17,23);	espacio.setViva(18,23);
		espacio.setViva(19,23);	espacio.setViva(20,23);	espacio.setViva(21,23);
		espacio.setViva(16,24);	espacio.setViva(17,24);	espacio.setViva(18,24);
		espacio.setViva(19,24);	espacio.setViva(20,24);	espacio.setViva(21,24);
		
		//abajo
		espacio.setViva(20,16); espacio.setViva(20,17);	espacio.setViva(20,18);
		espacio.setViva(20,19);	espacio.setViva(20,20);	espacio.setViva(20,21);
		espacio.setViva(21,16);	espacio.setViva(21,17);	espacio.setViva(21,18);
		espacio.setViva(21,19);	espacio.setViva(21,20);	espacio.setViva(21,21);
		
		repaint();
	}
	
	public void pintarX(){
		espacio = new EspacioCelular(espacio.getCols(), espacio.getFilas(), 0);
		
		//derecha - izquierda
		espacio.setViva(0,0);	espacio.setViva(1,1); espacio.setViva(2,2);
		espacio.setViva(3,3);	espacio.setViva(4,4); espacio.setViva(5,5);
		espacio.setViva(6,6);	espacio.setViva(7,7); espacio.setViva(8,8);
		espacio.setViva(9,9);	espacio.setViva(10,10);	espacio.setViva(11,11);
		espacio.setViva(12,12);	espacio.setViva(13,13);	espacio.setViva(14,14);
		espacio.setViva(15,15);	espacio.setViva(16,16);	espacio.setViva(17,17);
		espacio.setViva(18,18);	espacio.setViva(19,19);	espacio.setViva(20,20);
		espacio.setViva(21,21);	espacio.setViva(22,22);	espacio.setViva(23,23);
		espacio.setViva(24,24);	espacio.setViva(25,25);	espacio.setViva(26,26);
		espacio.setViva(27,27);	espacio.setViva(28,28);	espacio.setViva(29,29);
		espacio.setViva(30,30);	espacio.setViva(31,31);	espacio.setViva(32,32);
		espacio.setViva(33,33);	espacio.setViva(34,34);	espacio.setViva(35,35);
		espacio.setViva(36,36);	espacio.setViva(37,37);	espacio.setViva(38,38);
		espacio.setViva(39,39);
		
		//izquierda derecha
		espacio.setViva(0,39);	espacio.setViva(1,38); espacio.setViva(2,37);
		espacio.setViva(3,36);	espacio.setViva(4,35); espacio.setViva(5,34);
		espacio.setViva(6,33);	espacio.setViva(7,32); espacio.setViva(8,31);
		espacio.setViva(9,30);	espacio.setViva(10,29);	espacio.setViva(11,28);
		espacio.setViva(12,27);	espacio.setViva(13,26);	espacio.setViva(14,25);
		espacio.setViva(15,24);	espacio.setViva(16,23);	espacio.setViva(17,22);
		espacio.setViva(18,21);	espacio.setViva(19,20);	espacio.setViva(20,19);
		espacio.setViva(21,18);	espacio.setViva(22,17);	espacio.setViva(23,16);
		espacio.setViva(24,15);	espacio.setViva(25,14);	espacio.setViva(26,13);
		espacio.setViva(27,12);	espacio.setViva(28,11);	espacio.setViva(29,10);
		espacio.setViva(30,9);	espacio.setViva(31,8);	espacio.setViva(32,7);
		espacio.setViva(33,6);	espacio.setViva(34,5);	espacio.setViva(35,4);
		espacio.setViva(36,3);	espacio.setViva(37,2);	espacio.setViva(38,1);
		espacio.setViva(39,0);
		
		
		repaint();
		}
	
	
	public void pintar80 (){
		espacio = new EspacioCelular(espacio.getCols(), espacio.getFilas(),80);
		repaint();
	}
	
	public void pintar50 (){
		espacio = new EspacioCelular(espacio.getCols(), espacio.getFilas(),50);
		repaint();
	}
	public void pintar30 (){
		espacio = new EspacioCelular(espacio.getCols(), espacio.getFilas(),30);
		repaint();
	}
	
	
	
	public String getVivas(){
		return Integer.toString(espacio.getVivas());
	}
	
	
}
