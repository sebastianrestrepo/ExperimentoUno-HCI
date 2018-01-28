import processing.core.PApplet;
import processing.core.PFont;

public class Logica {

	private PApplet app;
	private Timer t;
	private String texto;
	private String tiempo;
	private int millis, segundos, nivel;
	private boolean tareaTerminada;
	
	private PFont dosisFuente,dosisFuenteReg;
	private int x,y;
	
	
	public Logica(PApplet app) {
		this.app = app;
		t = new Timer();
		t.start();
		tareaTerminada = false;
		
		inicializarVars();
		
		cargarfuente();
	}
	
	private void inicializarVars() {
		 nivel = 0;
		 y=app.height/2;
		 x=app.width/2;
	}

	private void cargarfuente() {
		dosisFuente = app.loadFont("Dosis-Bold-48.vlw");
		dosisFuenteReg = app.loadFont("Dosis-Regular-48.vlw");
	}

	public void cargarTxt() {
		
	}

	public void pantallas() {
		switch (nivel) {
		//Inicio
		case 0:
			  setFuenteBold(48,255);
			  app.textAlign(app.CENTER, app.CENTER);
			  app.text("LITTLE TYPE WORLDS", x, y-100);
			  
			  app.fill(255);
			  app.rect(x, y+200, 150, 50);
			break;
			
		//Instrucciones
		case 1:
			  setFuenteBold(48,255);
			  app.textAlign(app.CENTER, app.CENTER);
			  app.text("INSTRUCCIONES", x, y-200);
			  
			  setFuenteRegular(32, 255);
			  app.text("Escribe la letra/palabra/oraci�n que aparece en la pantalla!", x, y-50);
			  app.text("Sin tildes o signos de puntuaci�n", x, y);
			  
			  app.text("Cuando la hayas terminado, oprime Enter", x, y+50);
			  
			  app.fill(255);
			  app.rect(x, y+200, 150, 50);
			  
			break;
			
		//Animacion a nivel 1
		case 2:
			setFuenteBold(48,255);
			  app.textAlign(app.CENTER, app.CENTER);
			 
			  app.text("Transicion", x, y-250);
			break;
			
		//--------------- NIVEL 1 ----------- 
		case 3:
			
			apareceletra();
			setFuenteBold(48,255);
			  app.textAlign(app.CENTER, app.CENTER);
			  app.text("Nivel 1", x, y-250);
			break;
		
		//feedback nivel 1
		case 4:
			setFuenteBold(48,255);
			  app.textAlign(app.CENTER, app.CENTER);
			  app.text("�Nivel 1 Terminado!", x, y-250);
			break;
			
		
		//---------------NIVEL 2 ---------------
			
		case 5:
			setFuenteBold(48,255);
			  app.textAlign(app.CENTER, app.CENTER);
			  app.text("Nivel 2", x, y-250);
			break;
		
		//feedback nivel 2
		case 6:
			setFuenteBold(48,255);
			  app.textAlign(app.CENTER, app.CENTER);
			  app.text("�Nivel 2 Terminado!", x, y-250);
			break;
			
		//---------------NIVEL 3 ---------------
			
		case 7:
			setFuenteBold(48,255);
			  app.textAlign(app.CENTER, app.CENTER);
			  app.text("Nivel 3", x, y-250);
			break;
		
		//feedback nivel 3
		case 8:
			setFuenteBold(48,255);
			  app.textAlign(app.CENTER, app.CENTER);
			  app.text("�Nivel 3 Terminado!", x, y-250);
			break;
			
	
		//---------------NIVEL 4 ---------------
			
		case 9:
			setFuenteBold(48,255);
			  app.textAlign(app.CENTER, app.CENTER);
			  app.text("Nivel 4", x, y-250);
			break;
		
		//feedback final 
		case 10:
			setFuenteBold(48,255);
			  app.textAlign(app.CENTER, app.CENTER);
			  app.text("�Genial, gracias!", x, y-250);
			break;
			
		}

	}

	private void setFuenteBold(int i, int j) {
		 app.textFont(dosisFuente,i);
		  app.fill(j);
		
	}

	private void apareceletra() {
		validarTiempo();

		
	}


	private void setFuenteRegular(int i, int j)  {
		  app.textFont(dosisFuenteReg,i);
		  app.fill(j);
	}

	public void validarTiempo() {
		if (millis >= 1000) {
			segundos++;
			reiniciarTiempo();
		}
		millis = t.getMillis();
		tiempo = segundos + ":" + millis;
		app.fill(255);
		app.textAlign(app.LEFT, app.LEFT);
		app.text(tiempo, app.width / 2, app.height / 2);
		//System.out.println(tiempo);
	}

	public void reiniciarTiempo() {
		t.setMillis(0);
	}

	public void mouse() {
		// Cambiar pantalla
		if (nivel == 0) {
			nivel = 1;
		}
		
		else if (nivel == 1) {
			nivel = 2;
		}
		
		else if (nivel == 2) {
			nivel = 3;
		}
		else if (nivel == 3) {
			nivel = 4;  
		}
		else if (nivel == 4) {
			nivel = 5;
		}
		
		else if (nivel == 5) {
			nivel = 6;
		}
		else if (nivel == 6) {
			nivel = 7;  
		}
		
		else if (nivel == 7) {
			nivel = 8;
		}
		
		else if (nivel == 8) {
			nivel = 9;
		}
		else if (nivel == 9) {
			nivel = 10;  
		}
	}

	public void teclas() {
		if (app.keyCode == 10) {
			if (nivel == 3) {
				sigLetra();

			}
			
			else if (nivel == 5) {
				sigPalabra();

			}
			
			else if (nivel == 7) {
				sigOracion();
			}
			else if (nivel == 9) {
				sigParrafo();
			}
		}
		
	}

	private void sigParrafo() {
		// TODO Auto-generated method stub
		
	}

	private void sigOracion() {
		// TODO Auto-generated method stub
		
	}

	private void sigPalabra() {
		// TODO Auto-generated method stub
		
	}

	private void sigLetra() {
		System.out.println(tiempo);
		segundos = 0;
		millis = 0 ;
	}

}