import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import controlP5.ControlP5;
import controlP5.Textfield;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Logica {

	private PApplet app;
	private Timer t;
	private String tiempo;
	private int millis, segundos, nivel;
	private boolean tareaTerminada, tareaTerminadaMal, tareaTerminadaPal, tareaTerminadaMalPal, tareaTerminadaOr,
			tareaTerminadaMalOr, tareaTerminadaParr, tareaTerminadaMalParr;
	private AudioSample audioBueno, audioMalo, parr1, parr2;
	private Minim minim;
	private PFont dosisFuente, dosisFuenteReg, dosisCampos;
	private int x, y;
	private PImage imgs[], formulario, planetas[], emoji[], emoji2[], trans[], nivel2 [], nivel3[], nivel4[];
	private int contadorItem, opacidad, imgOpacidad;
	private int contadorPal, contadorInternoPal, contadorInternoOr, contadorInternoParr, contadorGeneral;
	int frame = 0;
	int frame2 = 12;
	int frame3 = 0;
	int frame4 = 0;
	int frame5 = 0;
	int frame6 = 0;
	
	int imgPuntaje;
	private String[] texto;
	private String datosUsuario, nombre, carrera, semestre, edad, genero;
	private boolean[] acerto;
	private String[] resultadosUsuario;
	private String palabraEscrita;
	private String parrafoEscrito;
	private String oracionEscrita;
	private ArrayList<Letra> letras;
	private ArrayList<Palabra> palabras;
	private ArrayList<Oracion> oraciones;
	private ArrayList<Parrafo> parrafos;

	private char[] letrasTemp;
	private char[] palabraTemp;
	private char[] oracionTemp;
	private char[] parrafoTemp;
	private String letraEscrita;
	private boolean animar;
	
	private PImage puntaje[];
	
	private ControlP5 cp5;

	public Logica(PApplet app) {
		this.app = app;
		t = new Timer(app);
		t.start();
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
		
		planetas = new PImage[4];

		for (int i = 0; i < planetas.length; i++) {
			planetas[i] = app.loadImage("../data/planeta" + i + ".png");
		}

		formulario = app.loadImage("../data/formulario.png");

		emoji = new PImage[12];

		for (int i = 0; i < emoji.length; i++) {
			emoji[i] = app.loadImage("../data/Emojis/emoji_" + i + ".png");
		}

		emoji2 = new PImage[13];

		for (int i = 12; i < 25; i++) {
			emoji2[i - 12] = app.loadImage("../data/Emojis/emoji_" + i + ".png");
		}

		trans = new PImage[41];

		for (int i = 6; i < 47; i++) {
			trans[i - 6] = app.loadImage("../data/NivelUno/NivelUno_" + i + ".png");
		}
		
		nivel2 = new PImage[23];

		for (int i = 1; i < 23; i++) {
			nivel2[i - 1] = app.loadImage("../data/Animacion nivel 2/AnimacionDos_" + i + ".png");
		}
		
		nivel3 = new PImage[23];

		for (int i = 1; i < 23; i++) {
			nivel3[i - 1] = app.loadImage("../data/Animacion nivel 3/NivelTres_" + i + ".png");
		}
		
		nivel4 = new PImage[23];

		for (int i = 1; i < 23; i++) {
			nivel4[i - 1] = app.loadImage("../data/Animacion nivel 4/NivelCuatro_" + i + ".png");
		}
		
		puntaje = new PImage[16];

		for (int i = 1; i < 16; i++) {
			puntaje[i - 1] = app.loadImage("../data/Puntaje/1 (" + i + ").png");
		}
	}

	private void inicializarVars() {
		animar =  false;
		tareaTerminada = false;
		tareaTerminadaMal = false;
		tareaTerminadaPal = false;
		tareaTerminadaMalPal = false;
		tareaTerminadaOr = false;
		tareaTerminadaMalOr = false;
		tareaTerminadaParr = false;
		tareaTerminadaMalParr = false;
		contadorInternoPal = 0;
		contadorGeneral = 1;
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
		palabraEscrita = "";
		oracionEscrita = "";
		parrafoEscrito = "";
		resultadosUsuario = new String[52];
		acerto = new boolean[52];
		minim = new Minim(app);
		audioBueno = minim.loadSample("../data/Bueno.mp3", 512);
		audioMalo = minim.loadSample("../data/Malo.mp3", 512);
		parr1 = minim.loadSample("../data/Audio 1 Lento.mp3", 512);
		parr2 = minim.loadSample("../data/Audio 3 Lento.mp3", 512);
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
			app.image(trans[frame3], x, y);
			if (app.frameCount % 5 == 0) {
				frame3++;
				if (frame3 == 41) {
					frame3 = 0;
					nivel = 3;
				}

			}

			break;

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// --------------- NIVEL 1 -----------
		case 3:

			app.image(planetas[0], x, y + 40);
			setFuenteBold(48, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text("Nivel 1", x, y - 250);

			validarTiempo();
			fadeIn();

			if (tareaTerminada) {
				sigLetra();
			}

			if (tareaTerminadaMal) {
				sigLetraMal();
			}

			setFuenteBold(80, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text(letras.get(contadorItem - 1).getLetra(), x, y);
			
			setFuenteBold(35, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text(contadorItem + "/25", x+500, y - 250);
			

			break;

		// feedback nivel 1
		case 4:
			//
			fadeInImg();
			//
			
			
		
			if (animar) {
				app.image(nivel2[frame4], x, y);
				if (app.frameCount % 5 == 0) {
					frame4++;
					if (frame4 == 22) {
						nivel = 5;
						animar = false;
						}
				}
			} else {
				app.image(imgs[1], x, y);
				imgPuntaje = 12;
				app.image(puntaje[imgPuntaje], x, y - 130);
				
			}
			break;

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// ---------------NIVEL 2 ---------------

		case 5:
			app.image(planetas[1], x, y + 40);
			validarTiempo();
			setFuenteBold(48, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text("Nivel 2", x, y - 250);
			fadeIn();

			setFuenteBold(48, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text(palabras.get(contadorItem - 1).getPalabra(), x, y);
			
			setFuenteBold(35, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text(contadorItem + "/20", x+500, y - 250);

			if (tareaTerminadaPal) {
				sigPalabra();
			}
			if (tareaTerminadaMalPal) {
				sigPalabraMal();
			}

			break;

		// feedback nivel 2
		case 6:
			//
			fadeInImg();
			//
			
			
			if (animar) {
				app.image(imgs[0], x, y);
				app.image(nivel3[frame5], x, y);
				if (app.frameCount % 5 == 0) {
					frame5++;
					if (frame5 == 22) {
						nivel = 7;
						animar = false;
						}
				} else {
					app.image(imgs[2], x, y);

				}
			}
			break;

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// ---------------NIVEL 3 ---------------

		case 7:
			app.image(planetas[2], x - 10, y + 45);
			validarTiempo();
			setFuenteBold(48, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text("Nivel 3", x, y - 250);
			fadeIn();

			setFuenteBold(48, 150);
			app.textAlign(app.CENTER, app.CENTER);
			app.text(oraciones.get(contadorItem - 1).getOracion(), x, y, 900, 300);
			
			setFuenteBold(35, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text(contadorItem + "/4", x+500, y - 250);

			if (tareaTerminadaOr) {
				sigOracion();
			}
			if (tareaTerminadaMalOr) {
				sigOracionMal();
			}

			break;

		// feedback nivel 3
		case 8:
			//
			fadeInImg();
			//

			if (animar) {
				app.image(nivel4[frame6], x, y);
				if (app.frameCount % 5 == 0) {
					frame6++;
					if (frame6 == 22) {
						nivel = 9;
						animar = false;
						}
				} else {
					app.image(imgs[3], x, y);
				}
			}
			break;

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// ---------------NIVEL 4 ---------------

		case 9:
			app.image(planetas[3], x, y + 35);
			validarTiempo();
			setFuenteBold(48, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text("Nivel 4", x, y - 250);
			fadeIn();

			setFuenteBold(48, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text(parrafos.get(contadorItem - 1).getParrafo(), x, y, 900, 300);
			
			setFuenteBold(35, 255);
			app.textAlign(app.CENTER, app.CENTER);
			app.text(contadorItem + "/2", x+500, y - 250);
			
			if (tareaTerminadaParr) {
				sigParrafo();
			}
			if (tareaTerminadaMalParr) {
				sigParrafoMal();
			}
			
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

		if (nivel != 11 && nivel != 0) {
			cp5.get("").hide();
			cp5.get(" ").hide();
			cp5.get("  ").hide();
			cp5.get("   ").hide();
			cp5.get("    ").hide();
		}

		generarBaseDeDatos();

	}

	//
	private void fadeInImg() {

		app.tint(255, imgOpacidad);
		if (app.frameCount % 5 == 0 && imgOpacidad <= 255) {
			imgOpacidad += 50;
		}
	}

	//
	private void fadeIn() {
		if (app.frameCount % 8 == 0 && opacidad <= 100) {
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
		System.out.println("NIIIIIIIIIIIIIIIIIIIIIVAEEEEEEEEEEEEEL" + nivel);
		if (nivel == 0) {
			if (app.mouseX >= 521 && app.mouseX <= 683 && app.mouseY >= 389 && app.mouseY < 440) {
				imgOpacidad = 0;
				nivel = 11;
				// Campo de Texto
				int blanco = app.color(255);
				int negro = app.color(60, 60, 59);

				cp5.addTextfield("").setPosition(335, 208).setSize(400, 24).setFont(dosisCampos).setColor(negro)
						.setColorForeground(blanco).setColorBackground(blanco).setColorActive(blanco)
						.setColorLabel(blanco);

				cp5.addTextfield(" ").setPosition(335, 297).setSize(400, 24).setFont(dosisCampos).setColor(negro)
						.setColorForeground(blanco).setColorBackground(blanco).setColorActive(blanco)
						.setColorLabel(blanco);

				cp5.addTextfield("  ").setPosition(335, 387).setSize(426, 24).setFont(dosisCampos).setColor(negro)
						.setColorForeground(blanco).setColorBackground(blanco).setColorActive(blanco)
						.setColorLabel(blanco);
				cp5.addTextfield("   ").setPosition(334, 476).setSize(140, 24).setFont(dosisCampos).setColor(negro)
				.setColorForeground(blanco).setColorBackground(blanco).setColorActive(blanco)
				.setColorLabel(blanco);
				cp5.addTextfield("    ").setPosition(527, 476).setSize(345, 24).setFont(dosisCampos).setColor(negro)
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
				edad = cp5.get(Textfield.class, "   ").getText();
				genero = cp5.get(Textfield.class, "    ").getText();

				datosUsuario = "Usuario: " + nombre + " Semestre: " + semestre + " Carrera: " + carrera + " Edad: " + edad + " Genero: " + genero;
				resultadosUsuario[0] = datosUsuario;
			}
		} else if (nivel == 1) {
			if (app.mouseX > 508 && app.mouseX < 700 && app.mouseY > 585 && app.mouseY < 637) {
				nivel = 2;
				imgOpacidad = 0;
			}
		} else if (nivel == 2) {
			imgOpacidad = 0;
			opacidad = 0;
			contadorItem = 1;
			reiniciarTiempo();
			animar = true;

		} else if (nivel == 4) {
			if (app.mouseX > 499 && app.mouseX < 705 & app.mouseY > 514 && app.mouseY < 566) {
				opacidad = 0;
				imgOpacidad = 0;
				contadorItem = 1;
				reiniciarTiempo();
				animar = true;

			}
		} else if (nivel == 6) {
			if (app.mouseX > 511 && app.mouseX < 720 & app.mouseY > 514 && app.mouseY < 568) {
				imgOpacidad = 0;
				opacidad = 0;
				contadorItem = 1;
				reiniciarTiempo();
				animar = true;

			}
		} else if (nivel == 8) {
			if (app.mouseX > 499 && app.mouseX < 705 & app.mouseY > 515 && app.mouseY < 567) {
				opacidad = 0;
				contadorItem = 1;
				imgOpacidad = 0;
				reiniciarTiempo();
				animar = true;

			}
		} else if(nivel == 9) {
			if (contadorItem == 1) {
				parr1.trigger();
				
			} else  {
				parr2.trigger();
				
			}
		}
	}

	public void teclas() {

		if (nivel == 3) {

			if (app.key == app.ENTER) {
				// sigLetra();
			} else {
				System.out.println(tiempo);
				validarLetra();
				reiniciarTiempo();
			}
		} else if (nivel == 5) {
			validarPalabra();
		} else if (nivel == 7) {
			validarOracion();
		} else if (nivel == 9) {
			validarParrafo();
		}

	}

	private void sigParrafo() {
		System.out.println(tiempo);
		reiniciarTiempo();
		opacidad = 0;

		app.image(emoji[frame], x, y);

		if (app.frameCount % 4 == 0) {

			frame++;

			if (frame == 11) {
				reiniciarTiempo();

				if (contadorItem != 2) {
					contadorItem++;
				} else {
					nivel++;
					//
					imgOpacidad = 0;
					//
				}
				frame = 0;
				contadorGeneral++;
				tareaTerminadaParr = false;
			}
		}
	}

	private void sigParrafoMal() {
		System.out.println(tiempo);
		reiniciarTiempo();
		opacidad = 0;

		app.image(emoji2[frame], x, y);

		if (app.frameCount % 4 == 0) {

			frame++;

			if (frame == 11) {
				reiniciarTiempo();

				if (contadorItem != 2) {
					contadorItem++;
				} else {
					nivel++;
					//
					imgOpacidad = 0;
					//
				}
				frame = 0;
				contadorGeneral++;
				tareaTerminadaMalParr = false;
			}
		}
	}

	private void sigOracion() {
		System.out.println(tiempo);
		opacidad = 0;

		app.image(emoji[frame], x, y);

		if (app.frameCount % 4 == 0) {

			frame++;

			if (frame == 11) {
				reiniciarTiempo();

				if (contadorItem != 4) {
					contadorItem++;
				} else {
					nivel++;
					//
					imgOpacidad = 0;
					//
				}

				frame = 0;
				contadorGeneral++;
				tareaTerminadaOr = false;
			}
		}
	}

	private void sigOracionMal() {
		System.out.println(tiempo);
		opacidad = 0;

		app.image(emoji2[frame], x, y);

		if (app.frameCount % 4 == 0) {

			frame++;

			if (frame == 11) {
				reiniciarTiempo();

				if (contadorItem != 4) {
					contadorItem++;
				} else {
					nivel++;
					//
					imgOpacidad = 0;
					//
				}

				frame = 0;
				contadorGeneral++;
				tareaTerminadaMalOr = false;
			}
		}
	}

	private void sigPalabra() {
		opacidad = 0;

		app.image(emoji[frame], x, y);

		if (app.frameCount % 4 == 0) {

			frame++;

			if (frame == 11) {
				reiniciarTiempo();

				if (contadorItem != 20) {
					contadorItem++;
				} else {
					nivel++;
					//
					imgOpacidad = 0;
					//
				}

				frame = 0;
				contadorGeneral++;
				tareaTerminadaPal = false;
			}
		}
	}

	private void sigPalabraMal() {
		opacidad = 0;

		app.image(emoji2[frame], x, y);

		if (app.frameCount % 4 == 0) {

			frame++;

			if (frame == 11) {
				reiniciarTiempo();

				if (contadorItem != 20) {
					contadorItem++;
				} else {
					nivel++;
					//
					imgOpacidad = 0;
					//
				}

				frame = 0;
				contadorGeneral++;
				tareaTerminadaMalPal = false;
			}
		}
	}

	private void sigLetra() {
		opacidad = 0;

		app.image(emoji[frame], x, y);

		if (app.frameCount % 4 == 0) {

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
				contadorGeneral++;
				tareaTerminada = false;
			}
		}
	}

	public void sigLetraMal() {
		opacidad = 0;

		app.image(emoji2[frame2], x, y);

		if (app.frameCount % 4 == 0) {

			frame2++;
			if (frame2 == 13) {
				reiniciarTiempo();
				if (contadorItem != 25) {
					contadorItem++;
				} else {
					nivel++;
					//
					imgOpacidad = 0;
					//
				}

				frame2 = 0;
				contadorGeneral++;
				tareaTerminadaMal = false;
			}
		}
	}

	public void validarLetra() {
		letrasTemp = letras.get(contadorItem - 1).getLetra().toCharArray();
		char letraOprimida = ' ';
		
		letraOprimida = app.key;
		System.out.println("OPRIMIO: " + app.key);

		if (letrasTemp[0] == letraOprimida) {
			System.out.println(app.key + " es correto!");
			tareaTerminada = true;
			acerto[contadorGeneral] = true;
			audioBueno.trigger();
		} else if (letrasTemp[0] != letraOprimida) {
			System.out.println(app.key + " es incorreto!");
			tareaTerminadaMal = true;
			acerto[contadorGeneral] = false;
			audioMalo.trigger();
		}
		
		letraEscrita = Character.toString(app.key);
		resultadosUsuario[contadorGeneral] = "Letra correspondiente: " + letrasTemp[0] + " / escribió: "
				+ letraEscrita + " / acerto: " + acerto[contadorGeneral] + " / en este tiempo: " + tiempo;
	}

	public void validarPalabra() {
		palabraTemp = palabras.get(contadorItem - 1).getPalabra().toCharArray();
		String palabraTempString = palabras.get(contadorItem - 1).getPalabra();
		palabraEscrita = palabraEscrita + app.key;

		if (palabraTemp[contadorInternoPal] == app.key) {
			System.out.println(app.key + " es igual a " + palabraTemp[contadorInternoPal]);
		} else {
			System.out.println(app.key + " es diferente a " + palabraTemp[contadorInternoPal]);
		}

		contadorInternoPal++;

		for (int i = 0; i < palabraTemp.length; i++) {
			System.out.println("El contador item es: " + contadorItem + " Palabra temporal: " + palabraTemp[i]
					+ " la escrita es: " + palabraEscrita + ";" + palabraTemp.length);
			System.out.println("Palabra:" + palabras.get(contadorItem - 1).getPalabra() + ".");
		}

		if (contadorInternoPal == palabraTemp.length) {
			if (palabraTempString.equals(palabraEscrita)) {
				System.out.println("EQUALS!");
				acerto[contadorGeneral] = true;
				tareaTerminadaPal = true;
				audioBueno.trigger();
			} else {
				System.out.println("WRONG!");
				acerto[contadorGeneral] = false;
				tareaTerminadaMalPal = true;
				audioMalo.trigger();
			}
			resultadosUsuario[contadorGeneral] = "Palabra correspondiente: " + palabraTempString + " / escribió: "
					+ palabraEscrita + " / acertó: " + acerto[contadorGeneral] + " / en este tiempo: " + tiempo;
			palabraEscrita = "";
			contadorInternoPal = 0;
		}

	}

	public void validarOracion() {

		oracionTemp = oraciones.get(contadorItem - 1).getOracion().toCharArray();
		String oracionTempString = oraciones.get(contadorItem - 1).getOracion();
		oracionEscrita = oracionEscrita + app.key;

		if (oracionTemp[contadorInternoOr] == app.key) {
			System.out.println(app.key + " es igual a " + oracionTemp[contadorInternoOr]);
		} else {
			System.out.println(app.key + " es diferente a " + oracionTemp[contadorInternoOr]);
		}

		contadorInternoOr++;

		for (int i = 0; i < oracionTemp.length; i++) {
			System.out.println("El contador item es: " + contadorItem + " Oración temporal: " + oracionTemp[i]
					+ " la escrita es: " + oracionEscrita + ";" + oracionTemp.length);
			System.out.println("Oracion:" + oraciones.get(contadorItem - 1).getOracion() + ".");
		}

		if (contadorInternoOr == oracionTemp.length) {
			if (oracionTempString.equals(oracionEscrita)) {
				System.out.println("EQUALS!");
				acerto[contadorGeneral] = true;
				tareaTerminadaOr = true;
				audioBueno.trigger();
			} else {
				System.out.println("WRONG!");
				acerto[contadorGeneral] = false;
				tareaTerminadaMalOr = true;
				audioMalo.trigger();
			}
			resultadosUsuario[contadorGeneral] = "Oración correspondiente: " + oracionTempString + " / escribió: "
					+ oracionEscrita + " / acertó: " + acerto[contadorGeneral] + " / en este tiempo: " + tiempo;
			oracionEscrita = "";
			contadorInternoOr = 0;
		}

	}

	public void validarParrafo() {

		parrafoTemp = parrafos.get(contadorItem - 1).getParrafo().toCharArray();
		String parrafoTempString = parrafos.get(contadorItem - 1).getParrafo();
		parrafoEscrito = parrafoEscrito + app.key;

		if (parrafoTemp[contadorInternoParr] == app.key) {
			System.out.println(app.key + " es igual a " + parrafoTemp[contadorInternoParr]);
		} else {
			System.out.println(app.key + " es diferente a " + parrafoTemp[contadorInternoParr]);
		}

		contadorInternoParr++;

		for (int i = 0; i < parrafoTemp.length; i++) {
			System.out.println("El contador item es: " + contadorItem + " Parrafo temporal: " + parrafoTemp[i]
					+ " la escrita es: " + parrafoEscrito + ";" + parrafoTemp.length);
			System.out.println("Parrafo:" + parrafos.get(contadorItem - 1).getParrafo() + ".");
		}

		if (contadorInternoParr == parrafoTemp.length) {
			if (parrafoTempString.equals(parrafoEscrito)) {
				System.out.println("EQUALS!");
				acerto[contadorGeneral] = true;
				tareaTerminadaParr = true;
				audioBueno.trigger();
			} else {
				System.out.println("WRONG!");
				acerto[contadorGeneral] = false;
				tareaTerminadaMalParr = true;
				audioMalo.trigger();
			}
			resultadosUsuario[contadorGeneral] = "Párrafo correspondiente: " + parrafoTempString + "/ escribió: "
					+ parrafoEscrito + "/ acertó: " + acerto[contadorGeneral] + "/ en este tiempo: " + tiempo;
			parrafoEscrito = "";
			contadorInternoParr = 0;
		}
		
	}

	public void generarBaseDeDatos() {
		BufferedWriter salida;
		String textoFinal = app.join(resultadosUsuario, "\n");
		// String txtNuevo = app.join(datosUsuario, " "); //Se crea el String que recibe
		// el texto con las modificaciones y las une
		try {
			salida = new BufferedWriter(new FileWriter("data/resultados.txt"));
			salida.write(textoFinal); // Se escribe el String que contiene las modificaciones en el txt nuevo
			salida.flush();
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
