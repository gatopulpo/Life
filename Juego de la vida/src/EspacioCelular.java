
import java.awt.Graphics;
import java.util.Random;


public class EspacioCelular {
	
	
	public  final static int TAMCEL = 20;
	private Celula espacio [][]; //Array que contendrá las celulas VIVAS o MUERTAS.
	private int filas, columnas; //filas y columnas del array
	private int numeroVivas = 0; //Contador de celulas 
	private Graphics g;
	
	private int porcentaje;
	/*Constructor principal. Crea un array de filas, columnas de celulas muertas*/
	public EspacioCelular(int filas, int columnas){
		
		espacio = new Celula[filas][columnas];
		this.filas=filas;
		this.columnas=columnas;
		
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				espacio[i][j] = Celula.MUERTA;
			}
		}
	}
	
	public int getFilas (){return filas;}
	public int getCols (){return columnas;}
	public int getPorcentaje(){return porcentaje;}
	
	public Celula[][] getEspacio(){
		return espacio;
	}
	
	/*Constructor sobrecargado para rellenar el array de celulas vivas de manera aleatoria*/
	public EspacioCelular (int filas, int columnas, int porcentaje){
		
		this(filas, columnas); //Llamada al constructor principal
		this.porcentaje = porcentaje;
		Random r = new Random();
		int vivasPorMeter = (filas*columnas*porcentaje) / 100;
		while (vivasPorMeter > 0){
			int fila = r.nextInt(filas);
			int colu = r.nextInt(columnas);
			
			if (espacio[fila][colu] == Celula.MUERTA){
				espacio[fila][colu] = Celula.VIVA;
				vivasPorMeter--;
				numeroVivas++;
			}
			
		}
	}
	
	/*Conocer el estado de celulas vecinas*/
	public int getVecinas (int fila, int columna){
		int contVivas = 0;
		
		for (int i = fila-1; i <= fila+1; i++) {
			for (int j = columna-1; j <= columna+1; j++) {
				/*Comprobamos estado de las celulas vecinas y
				 * descartamos la celula del medio*/
				try {
					//Comprobamos que las celulas estan vivas o han estado vivas
					if ((espacio[i][j] == Celula.VIVA || espacio[i][j] == Celula.MORIBUNDA) 
							&& (i!=fila || j!= columna)){
						contVivas++;
					}
				} catch (IndexOutOfBoundsException e) {
					// TODO: handle exception
				}
			}
		}
		return contVivas;
	}
	//comprobamos las iteraciones con las otras celulas
	public boolean supervivencia (int fila, int columna){
		return espacio[fila][columna]==Celula.VIVA 
				&& (getVecinas(fila, columna) == 2 || getVecinas(fila, columna) == 3);
	}
	
	public boolean nacimiento (int fila, int columna){
		return espacio[fila][columna]==Celula.MUERTA 
				&& getVecinas(fila, columna) == 3; 
	}

	public boolean soledad (int fila, int columna){
		return espacio[fila][columna]==Celula.VIVA 
				&& (getVecinas(fila, columna) == 0 || getVecinas(fila, columna) == 1); 
	}
	
	public boolean superpoblacion (int fila, int columna){
		return espacio[fila][columna]==Celula.VIVA 
				&& getVecinas(fila, columna) >= 4; 
	}//fin de las comprobaciones
	

	//dependiendo de las celulas vivas que tenga alrededor las celulas toman un estado u otro
	
	public void generacion (){
		
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {

				if (nacimiento(i, j))
					espacio[i][j] = Celula.EMBRION;
				if (soledad(i, j))
					espacio[i][j] = Celula.MORIBUNDA;
				if (superpoblacion(i, j))
					espacio[i][j] = Celula.MORIBUNDA;
			}
		} //Final de FOR 1.
		
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (espacio[i][j] == Celula.EMBRION)
					espacio[i][j] = Celula.VIVA;
				if (espacio[i][j] == Celula.MORIBUNDA)
					espacio[i][j] = Celula.MUERTA;
			}
		}//Final del FOR 2.
		
	}
	
	public int getVivas(){
	
	return numeroVivas;
	}
	
	/*Metodo para poner una celula en estado viva*/
	public void setViva (int posX, int posY){
		espacio [posX][posY] = Celula.VIVA;
	}

	

}

