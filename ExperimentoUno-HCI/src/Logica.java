import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import controlP5.ControlP5;
import controlP5.Textfield;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Logica {

	private PApplet app;
	private Timer t;
	private String tiempo;
	private int millis, segundos, nivel;
	private boolean tareaTerminada;

	private PFont dosisFuente, dosisFuenteReg, dosisCampos;
	private int x, y;
	private PImage imgs[], formulario, planetas[],  emoji [],  emoji2 [];

	private int contadorItem, opacidad, contadorInterno, imgOpacidad;
	int frame = 0;	
	int frame2 = 12;
	private String[] texto;
	private String nombre, carrera, semestre;
	private ArrayList<Letra> letras;
	private ArrayList<Palabra> palabras;
	private ArrayList<Oracion> oraciones;
	private ArrayList<Parrafo> parrafos;

	private ControlP5 cp5;

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

		planetas = new PImage[4];
		
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = app.loadImage("../data/n" + i + ".png");
		}
		
		for (int i = 0; i < planetas.length; i++) {
			planetas[i] = app.loadImage("../data/planeta" + i + ".png");
		}

		formulario = app.loadImage("../data/formulario.png");
		
		emoji  = new PImage[12];

		for (int i = 0; i < emoji.length; i++) {
			emoji[i] = app.loadImage("../data/Emojis/emoji_" + i + ".png");
		}
	}

	private void inicializarVars() {
		contadorInterno = 0;
		cp5 = new ControlP5(app);
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
		dosisCampos = app.createFont("Dosis-SemiBold-27.vlw", 18);
	}

	public void pantallas() {
		app.image(imgs[0], x, y);
		switch (nivel) {
		// Inicio
		case 0:
			//
			fadeInImg();
			app.image(imgs[5], x, y);

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

			app.image(planetas[0], x, y+40);
			setFuenteBold(48, 255);
			app.textAlign(app.CORNER, app.CORNER);
			app.text("Nivel 1", x, y - 250);

			validarTiempo();
			fadeIn();

			if (tareaTerminada) {
				sigLetra();
			}
			
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
			app.image(planetas[1], x, y+40);
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
			app.image(planetas[2], x-10, y+45);
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
			app.image(planetas[3], x, y+35);
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
		// formulario inicial
		case 11:

			app.image(formulario, x, y);

			break;

		}
		
		if(nivel!=11&&nivel!=0) {
			cp5.get("").hide();
			cp5.get(" ").hide();
			cp5.get("  ").hide();
		}

		generarBaseDeDatos();
		
	}

	//
	private void fadeInImg() {

		app.tint(255, imgOpacidad);
		if (app.frameCount % 5 == 0 && imgOpacidad <= 250) {
			imgOpacidad += 50;
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
	if (app.mouseX >= 521 && app.mouseX <= 683 && app.mouseY >= 389 && app.mouseY < 440) {
		imgOpacidad = 0;
				nivel = 11;
				// Campo de Texto
				int blanco = app.color(255);
				int negro = app.color(60, 60, 59);

				cp5.addTextfield("").setPosition(335, 226).setSize(400, 24).setFont(dosisCampos).setColor(negro)
						.setColorForeground(blanco).setColorBackground(blanco).setColorActive(blanco)
						.setColorLabel(blanco);

				cp5.addTextfield(" ").setPosition(335, 330).setSize(400, 24).setFont(dosisCampos).setColor(negro)
						.setColorForeground(blanco).setColorBackground(blanco).setColorActive(blanco)
						.setColorLabel(blanco);

				cp5.addTextfield("  ").setPosition(335, 440).setSize(426, 24).setFont(dosisCampos).setColor(negro)
						.setColorForeground(blanco).setColorBackground(blanco).setColorActive(blanco)
						.setColorLabel(blanco);
				
			}
		} else if (nivel == 11) {
			if (app.mouseX > 501 && app.mouseX < 707 && app.mouseY > 554 && app.mouseY < 604) {
				imgOpacidad = 0;
				nivel = 1;
				
				nombre = cp5.get(Textfield.class, "").getText();
				carrera = cp5.get(Textfield.class, " ").getText();
				semestre = cp5.get(Textfield.class, "  ").getText();
			}
		} else if (nivel == 1) {
			if (app.mouseX > 508 && app.mouseX < 700 && app.mouseY > 585 && app.mouseY < 637) {
				nivel = 2;
				imgOpacidad = 0;
			}
		} else if (nivel == 2) {
			nivel = 3;
			imgOpacidad = 0;
			opacidad = 0;
			contadorItem = 1;
			reiniciarTiempo();
		} else if (nivel == 4) {
			if(app.mouseX>499&&app.mouseX<705&app.mouseY>514&&app.mouseY<566) {
				nivel = 5;
				opacidad = 0;
				imgOpacidad = 0;
				contadorItem = 1;
				reiniciarTiempo();
			}
		} else if (nivel == 6) {
			if(app.mouseX>511&&app.mouseX<720&app.mouseY>514&&app.mouseY<568) {
			nivel = 7;
			imgOpacidad = 0;
			opacidad = 0;
			contadorItem = 1;
			reiniciarTiempo();
			}
		} else if (nivel == 8) {
			if(app.mouseX>499&&app.mouseX<705&app.mouseY>515&&app.mouseY<567) {
			nivel = 9;
			opacidad = 0;
			contadorItem = 1;
			imgOpacidad = 0;
			reiniciarTiempo();
			}
		}
	}

	public void teclas() {

		if (nivel == 3) {

			if (app.key == app.ENTER) {
				//sigLetra();
			} else {
				System.out.println(tiempo);
				validarLetra();
				reiniciarTiempo();
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
		opacidad = 0;

	
	app.image(emoji[frame], x, y);

		if (app.frameCount %4 == 0) {
			
			frame++;
			if (frame == 11) {
				reiniciarTiempo();
				if (contadorItem != 25) {
					contadorItem++;
				} else {
					nivel++;
					//
					imgOpacidad = 0;
					//
				}
				
				frame = 0;
				tareaTerminada = false;
			}
		}
	}

	public void validarLetra() {
		char[] letrasTemp = letras.get(contadorItem - 1).getLetra().toCharArray();
		char letraOprimida = ' ';

		letraOprimida = app.key;
		System.out.println("OPRIMIÓ: " + app.key);

		if (letrasTemp[0] == letraOprimida) {
			System.out.println(app.key + " es correto!");
			tareaTerminada = true;
		} else {
			System.out.println(app.key + " es incorreto!");
		}
	}

	public void validarPalabra() {
		char letraOprimida = ' ';
		letraOprimida = app.key;
		char[] palabrasTemp = palabras.get(contadorItem - 1).getPalabra().toCharArray();

		if (contadorInterno >= palabrasTemp.length) {
			contadorInterno = 0;
			System.out.println("Se reinició");
		}

		if (palabrasTemp[contadorInterno] == letraOprimida) {
			System.out.println(letraOprimida + " es igual a " + palabrasTemp[contadorInterno]);
			contadorInterno++;
		} else {
			System.out.println(letraOprimida + " es diferente a " + palabrasTemp[contadorInterno]);
			contadorInterno++;
		}

	}

	public void generarBaseDeDatos() {
		BufferedWriter salida;
		String datosUsuario = "Usuario: " + nombre + " Semestre: " + semestre + " Carrera: " + carrera;
		//String txtNuevo = app.join(datosUsuario, " "); //Se crea el String que recibe el texto con las modificaciones y las une
		try {
			salida = new BufferedWriter(new FileWriter("data/resultados.txt"));
			salida.write(datosUsuario); //Se escribe el String que contiene las modificaciones en el txt nuevo
			salida.flush(); 
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
