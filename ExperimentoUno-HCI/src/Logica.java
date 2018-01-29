import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Logica {

	private PApplet app;
	private Timer t;
	private String tiempo;
	private int millis, segundos, nivel;
	private boolean tareaTerminada;

	private PFont dosisFuente, dosisFuenteReg;
	private int x, y;
	private PImage imgs[];

	private int contadorItem, opacidad, contadorInterno, imgOpacidad;

	private String[] texto;
	private ArrayList<Letra> letras;
	private ArrayList<Palabra> palabras;
	private ArrayList<Oracion> oraciones;
	private ArrayList<Parrafo> parrafos;

	public Logica(PApplet app) {
		this.app = app;
		t = new Timer(app);
		t.start();
		tareaTerminada = false;

		inicializarVars();
		cargarImgs();
		cargarfuente();
		cargarTexto();
		cargarArrayTexto();
		app.imageMode(app.CENTER);
	}

	private void cargarImgs() {
		imgs = new PImage[8];

		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = app.loadImage("../data/n" + i + ".png");
		}
	}

	private void inicializarVars() {
		contadorInterno = 0;
		nivel = 0;
		y = app.height / 2;
		x = app.width / 2;
		contadorItem = 1;
		letras = new ArrayList<Letra>();
		palabras = new ArrayList<Palabra>();
		oraciones = new ArrayList<Oracion>();
		parrafos = new ArrayList<Parrafo>();
		opacidad = 255;
		imgOpacidad = 0;
	}

	private void cargarTexto() {
		texto = app.loadStrings("texto.txt");
		for (int i = 0; i < texto.length; i++) {
			System.out.println(texto[i]);
		}
	}

	public void cargarArrayTexto() {
		for (int i = 0; i < 25; i++) {
			letras.add(new Letra(texto[i]));
		}
		for (int i = 25; i < 45; i++) {
			palabras.add(new Palabra(texto[i]));
		}
		for (int i = 45; i < 49; i++) {
			oraciones.add(new Oracion(texto[i]));
		}
		for (int i = 49; i < 51; i++) {
			parrafos.add(new Parrafo(texto[i]));
		}
		// IMPRIMIR
		System.out.println("----ARRAYLIST DE LETRAS-----");
		for (int i = 0; i < letras.size(); i++) {
			System.out.println("posicion: " + i + " contiene: " + letras.get(i).getLetra());
		}
		System.out.println("----ARRAYLIST DE PALABRAS-----");
		for (int i = 0; i < palabras.size(); i++) {
			System.out.println("posicion: " + i + " contiene: " + palabras.get(i).getPalabra());
		}
		System.out.println("----ARRAYLIST DE ORACIONES-----");
		for (int i = 0; i < oraciones.size(); i++) {
			System.out.println("posicion: " + i + " contiene: " + oraciones.get(i).getOracion());
		}
		System.out.println("----ARRAYLIST DE PARRAFOS-----");
		for (int i = 0; i < parrafos.size(); i++) {
			System.out.println("posicion: " + i + " contiene: " + parrafos.get(i).getParrafo());
		}

	}

	private void cargarfuente() {
		dosisFuente = app.loadFont("Dosis-Bold-48.vlw");
		dosisFuenteReg = app.loadFont("Dosis-Regular-48.vlw");
	}

	public void pantallas() {
		app.image(imgs[0], x, y);
		switch (nivel) {
		// Inicio
		case 0:
			//
			fadeInImg();
			app.image(imgs[5], x, y);
			String first_name;
			System.out.print("Enter your first name: ");
			first_name = user_input.next( );
			//
			break;

		// Instrucciones
		case 1:
			//
			fadeInImg();
			app.image(imgs[7], x, y);
			//

			break;

		// Animacion a nivel 1
		case 2:
			setFuenteBold(48, 255);
			app.textAlign(app.CENTER, app.CENTER);

			app.text("Transicion", x, y - 250);
			break;

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// --------------- NIVEL 1 -----------
		case 3:

			setFuenteBold(48, 255);
			app.textAlign(app.CORNER, app.CORNER);
			app.text("Nivel 1", x, y - 250);

			validarTiempo();
			fadeIn();

			setFuenteBold(80, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text(letras.get(contadorItem - 1).getLetra(), x, y);

			break;

		// feedback nivel 1
		case 4:
			//
			fadeInImg();
			//
			app.image(imgs[1], x, y);

			break;

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// ---------------NIVEL 2 ---------------

		case 5:
			validarTiempo();
			setFuenteBold(48, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text("Nivel 2", x, y - 250);
			fadeIn();

			setFuenteBold(48, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text(palabras.get(contadorItem - 1).getPalabra(), x, y);
			break;

		// feedback nivel 2
		case 6:
			//
			fadeInImg();
			//
			app.image(imgs[2], x, y);
			break;

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// ---------------NIVEL 3 ---------------

		case 7:
			validarTiempo();
			setFuenteBold(48, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text("Nivel 3", x, y - 250);
			fadeIn();

			setFuenteBold(48, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text(oraciones.get(contadorItem - 1).getOracion(), x, y, 900, 300);
			break;

		// feedback nivel 3
		case 8:
			//
			fadeInImg();
			//
			app.image(imgs[3], x, y);

			break;

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// ---------------NIVEL 4 ---------------

		case 9:
			validarTiempo();
			setFuenteBold(48, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text("Nivel 4", x, y - 250);
			fadeIn();

			setFuenteBold(48, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text(parrafos.get(contadorItem - 1).getParrafo(), x, y, 900, 300);
			break;

		// feedback final
		case 10:
			//
			fadeInImg();
			//
			app.image(imgs[4], x, y);

			break;

		}

	}
	//
	private void fadeInImg() {
		
		app.tint(255, imgOpacidad);
		if (app.frameCount%5==0 && imgOpacidad<=250) {
			imgOpacidad+=50;
		}
	}
	//
	private void fadeIn() {
		if (app.frameCount % 8 == 0 && opacidad <= 250) {
			opacidad += 50;
		}
	}

	private void setFuenteBold(int i, int j) {
		app.textFont(dosisFuente, i);
		app.fill(j, j, j, opacidad);

	}

	private void setFuenteRegular(int i, int j) {
		app.textFont(dosisFuenteReg, i);
		app.fill(j, j, j, opacidad);
	}

	public void validarTiempo() {
		millis = t.millis();
		segundos = t.second();
		tiempo = segundos + ":" + millis;
		setFuenteRegular(21, 255);
		app.textAlign(app.LEFT, app.LEFT);
		app.text(tiempo, 1100, 650);
	}

	public void reiniciarTiempo() {
		t.empezar();
	}

	public void mouse() {
		// Cambiar pantalla
		if (nivel == 0) {
			nivel = 1;
		} else if (nivel == 1) {
			nivel = 2;
		} else if (nivel == 2) {
			nivel = 3;
			contadorItem = 1;
			reiniciarTiempo();
		} else if (nivel == 3) {
			nivel = 4;
		} else if (nivel == 4) {
			nivel = 5;
			contadorItem = 1;
			reiniciarTiempo();
		} else if (nivel == 5) {
			nivel = 6;
		} else if (nivel == 6) {
			nivel = 7;
			contadorItem = 1;
			reiniciarTiempo();
		} else if (nivel == 7) {
			nivel = 8;
		} else if (nivel == 8) {
			nivel = 9;
			contadorItem = 1;
			reiniciarTiempo();
		} else if (nivel == 9) {
			nivel = 10;
		}
	}

	public void teclas() {

		if (nivel == 3) {

			if (app.key == app.ENTER) {
				sigLetra();
			} else {
				validarLetra();
			}
		} else if (nivel == 5) {
			if (app.key == app.ENTER) {
				sigPalabra();
			} else {
				validarPalabra();
			}
		} else if (nivel == 7) {
			if (app.key == app.ENTER) {
				sigOracion();
			}
		} else if (nivel == 9) {
			if (app.key == app.ENTER) {
				sigParrafo();
			}
		}

	}

	private void sigParrafo() {
		System.out.println(tiempo);
		reiniciarTiempo();
		opacidad = 0;
		if (contadorItem != 2) {
			contadorItem++;
		} else {
			nivel++;
			//
			imgOpacidad = 0;
			//
		}
	}

	private void sigOracion() {
		System.out.println(tiempo);
		reiniciarTiempo();
		opacidad = 0;
		if (contadorItem != 4) {
			contadorItem++;
		} else {
			nivel++;
			//
			imgOpacidad = 0;
			//
		}
	}

	private void sigPalabra() {
		System.out.println(tiempo);
		reiniciarTiempo();
		opacidad = 0;
		if (contadorItem != 20) {
			contadorItem++;
		} else {
			nivel++;
			//
			imgOpacidad = 0;
			//
		}
	}

	private void sigLetra() {
		System.out.println(tiempo);
		reiniciarTiempo();
		opacidad = 0;

		if (contadorItem != 25) {
			contadorItem++;

		} else {
			nivel++;
			//
			imgOpacidad = 0;
			//
		}

	}

	public void validarLetra() {
		char[] letrasTemp = letras.get(contadorItem - 1).getLetra().toCharArray();
		char letraOprimida = ' ';

		letraOprimida = app.key;
		System.out.println("OPRIMIÓ: " + app.key);

		if (letrasTemp[0] == letraOprimida) {
			System.out.println(app.key + " es correto!");
		} else {
			System.out.println(app.key + " es incorreto!");
		}
	}
	
	public void validarPalabra() {
		char letraOprimida = ' ';
		letraOprimida = app.key;
		char[] palabrasTemp = palabras.get(contadorItem - 1).getPalabra().toCharArray();
		

		
		if(contadorInterno >= palabrasTemp.length) {
			contadorInterno = 0;
			System.out.println("Se reinició");
		}
		
		if(palabrasTemp[contadorInterno] == letraOprimida) {
			System.out.println(letraOprimida + " es igual a " + palabrasTemp[contadorInterno]);
			contadorInterno++;
		} else {
			System.out.println(letraOprimida + " es diferente a " + palabrasTemp[contadorInterno]);
			contadorInterno++;
		}
		
	}

}
