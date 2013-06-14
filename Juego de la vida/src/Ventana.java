import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.FlowLayout;


public class Ventana extends JFrame {
	Thread t;
	PanelCelular panel;
	JButton btnAuto;
	JButton btnGenerar;
	JSlider slider;
	JMenuBar menuBar;
	public Ventana(){
		
	
		JFrame ventana = new JFrame();
		ventana.setAutoRequestFocus(false);
		ventana.setResizable(false);
		
		ventana.setTitle("Juego de la Vida");
		ventana.setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
//		ventana.getContentPane().setBackground(Color.BLACK);
		
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ventana.getContentPane().setLayout(new BorderLayout());
		
		slider = new JSlider();
		slider.setMinimum(10);
		slider.setMajorTickSpacing(100);
		slider.setMinorTickSpacing(10);
		slider.setMaximum(1000);
		slider.setValue(1000);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		ventana.getContentPane().add(slider, BorderLayout.SOUTH);
		
		panel = new PanelCelular(40,40);
		ventana.getContentPane().add(panel, BorderLayout.CENTER);
		
		
		
		//BARRA DE MENU 
		 menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		ventana.setJMenuBar(menuBar);
			// boton de menu
		JMenu mnNewMenu = new JMenu("Opciones");
		mnNewMenu.setBackground(SystemColor.controlDkShadow);
		menuBar.add(mnNewMenu);
			// boton de menu dentro de opciones
			JMenu mnNewMenu_1 = new JMenu("Tipo de Juego");
			mnNewMenu.add(mnNewMenu_1);
			
				//boton de menu dentro de tipo de juego si se pulsa las generaciones son automaticas
				JMenuItem mntmAutomatico = new JMenuItem("Automatico");
				mntmAutomatico.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						btnAuto.setVisible(true);// si se selecciona esta opcion el boton de inicio cambia a auto sino generacion
						btnGenerar.setVisible(false);
						getRootPane().setDefaultButton(btnAuto);
						}
					
				});
			
				mnNewMenu_1.add(mntmAutomatico);
				
				//boton de menu dentro de tipo de juego
				JMenuItem mntmGeneracion = new JMenuItem("Generacion a generacion");	
				mntmGeneracion.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						btnAuto.setVisible(false);
						btnGenerar.setVisible(true);
						getRootPane().setDefaultButton(btnGenerar);
						}
					
				});
				mnNewMenu_1.add(mntmGeneracion);
				
				
//				boton de menu dentro de opciones
				JMenu mnPatrones = new JMenu("Patrones");
				mnNewMenu.add(mnPatrones);
				
				JMenuItem mntmNave = new JMenuItem("Nave");
				mntmNave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panel.pintarNave();
					}
				});
				mnPatrones.add(mntmNave);
				
				JMenuItem mntmPlaneador = new JMenuItem("Planeador");
				mntmPlaneador.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panel.pintarPlaneador();
					}
				});
				mnPatrones.add(mntmPlaneador);
				
				JMenuItem mntmGalaxia = new JMenuItem("Galaxia");
				mntmGalaxia.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panel.pintarGalaxia();
					}
				});
				mnPatrones.add(mntmGalaxia);
				
				JMenuItem mntmAleatorio = new JMenuItem("Aleatorio 50%");
				mntmAleatorio.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panel.pintar50();
					}
				});
				
				JMenuItem mntmAleatorio_2 = new JMenuItem("Aleatorio 80%");
				mntmAleatorio_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panel.pintar80();
					}
				});
				
				JMenuItem mntmGranX = new JMenuItem("Gran X");
				mntmGranX.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						panel.pintarX();
					}
				});
				mnPatrones.add(mntmGranX);
				mnPatrones.add(mntmAleatorio_2);
				mnPatrones.add(mntmAleatorio);
				
				JMenuItem mntmAleatorio_1 = new JMenuItem("Aleatorio 30%");
				mntmAleatorio_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panel.pintar30();
					}
				});
				mnPatrones.add(mntmAleatorio_1);
				
				JMenuItem mntmSalir = new JMenuItem("Salir ");
				mntmSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				mnNewMenu.add(mntmSalir);
		
	
		
		btnGenerar = new JButton("Generacion");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.generacion();
			}
		});
		
		menuBar.add(btnGenerar);
		btnGenerar.setVisible(false);
		
		//HILO PARA QUE EL JUEGO VAYA EN MODO AUTOMATICO
		btnAuto = new JButton("Auto");
		btnAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (t == null){
					t = new Thread(new Runnable() {
						public void run() {
							Thread aux = Thread.currentThread();
							while (t == aux) {
								panel.generacion();
								
								 
								
								
								repaint();
								try { Thread.sleep(slider.getValue());
								
								
								
								
								} catch (InterruptedException e1) {}
								
							}
						}
					});
					t.start();
				}
				else
					t = null;
			}
			
		});
		btnAuto.setVisible(false);
		menuBar.add(btnAuto);
		
		JButton btnVaciar = new JButton("Vaciar");
		btnVaciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.vaciar();
			}
		});
		menuBar.add(btnVaciar);
		
		
		
		btnGenerar.setVisible(false);
		
		
		
		ventana.setVisible(true);
		
		ventana.pack();
		
		
	}
	

	
	public static void main(String[] args) {
		Ventana ventana = new Ventana();
		
	}
}
