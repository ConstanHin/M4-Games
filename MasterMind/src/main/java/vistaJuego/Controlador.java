package vistaJuego;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class Controlador {

	private Color[] colores = { Color.magenta, Color.yellow, Color.cyan, Color.green, Color.ORANGE, Color.BLUE };
	private ArrayList<Color> coloresSecretos = new ArrayList<Color>();
	private ArrayList<Casilla> casillasSecretas = new ArrayList<Casilla>();
	private int valor = -1;
	private int maxColor = 3;
	private int uMovimiento = 40;
	private boolean[] posicionesEncontradas = { false, false, false, false };
	private int totalEncontrados = 0;
	private int totalPerfectos = 0;
	private ArrayList<Casilla> casillasInput;
	private int maximoIntentos = 10;
	private JLabel labelFinalizada;
	private JButton comprobarBtn;
	public JPanel secretosPanel;

	public Controlador() {
	}

//	public Controlador(int maximoIntentos, int maxColor) {
//		this.maximoIntentos = maximoIntentos;
//		// Variar segun el nivel
//		this.maxColor = maxColor;
//	}

	/**
	 * Set casillas input
	 * 
	 * @param casillasInput
	 */
	public void setCasillasInput(ArrayList<Casilla> casillasInput) {
		this.casillasInput = casillasInput;
	}

	public ArrayList<Color> getColoresSecretos() {
		return coloresSecretos;
	}

	/**
	 * @return the maximointentos
	 */
	public int getMaximoIntentos() {
		return maximoIntentos;
	}

	/**
	 * @param maximointentos the maximointentos to set
	 */
	public void setMaximoIntentos(int maximointentos) {
		this.maximoIntentos = maximointentos;
	}

	/**
	 * @return the maxColor
	 */
	public int getMaxColor() {
		return maxColor;
	}

	/**
	 * @param maxColor the maxColor to set
	 */
	public void setMaxColor(int maxColor) {
		this.maxColor = maxColor;
	}

	/**
	 * @return the casillasSecretas
	 */
	public ArrayList<Casilla> getCasillasSecretas() {
		return casillasSecretas;
	}

	/**
	 * @return the labelFinalizada
	 */
	public JLabel getLabelFinalizada() {
		return labelFinalizada;
	}

	/**
	 * @param labelFinalizada the labelFinalizada to set
	 */
	public void setLabelFinalizada(JLabel labelFinalizada) {
		this.labelFinalizada = labelFinalizada;
	}

	/**
	 * @return the comprobarBtn
	 */
	public JButton getComprobarBtn() {
		return comprobarBtn;
	}

	/**
	 * @param comprobarBtn the comprobarBtn to set
	 */
	public void setComprobarBtn(JButton comprobarBtn) {
		this.comprobarBtn = comprobarBtn;
	}

	/**
	 * 
	 * @return
	 */
	public int getValor() {
		// TODO Auto-generated method stub
		return this.valor;
	}

	public Color[] getColores() {
		// TODO Auto-generated method stub
		return this.colores;
	}

	/**
	 * @return the totalEncontrados
	 */
	public int getTotalEncontrados() {
		return totalEncontrados;
	}

	/**
	 * @return the totalPerfectos
	 */
	public int getTotalPerfectos() {
		return totalPerfectos;
	}

	public void cambiarColor(int mouseButton, Component casilla) {

		// Dinamico segun la dificultad

		if (mouseButton == 1) {
			if (valor >= maxColor)
				valor = 0;
			else
				valor++;
		} else if (mouseButton == maxColor) {
			if (valor <= 0)
				valor = maxColor;
			else
				valor--;
		}
		System.out.println("MaxColor:" + maxColor);
		System.out.println("valor:" + valor);
		casilla.setBackground(colores[valor]);

	}

	/**
	 * Genera la combinacion secreta de colores v2
	 */
	public void generarCasillasSecretas() {
		for (int i = 0; i < 4; i++) {
			int numRandom = (int) (Math.random() * (maxColor + 1));
			Casilla casillaSecreta = new Casilla(i, colores[numRandom], false);
			casillasSecretas.add(casillaSecreta);
		}
	}

	/**
	 * Mover un componente hacia abajo
	 * 
	 * @param component
	 */
	public void moverAbajo(Component component) {
		// Cantidad de desplazamiento

		Point actualLocation = component.getLocation();
		component.setLocation(actualLocation.x, actualLocation.y + uMovimiento);
	}

	/**
	 * Deja una copia grafica de los colores insertados anteriormente
	 * 
	 * @param insertPanel
	 * @param padrePanel
	 * @param nrIntento
	 */
	public void dejarCopiaPanel(JPanel insertPanel, JPanel padrePanel, int nrIntento) {
		// Inicializar un nuevo panel
		JPanel pastPanel = new JPanel();

		pastPanel.setLayout(null);
		pastPanel.setBounds(0, uMovimiento * nrIntento, 260, 40);

		// Añadir hijos al pastPanel
		// Tomar los hijos del insertPanel
		Component[] insertedComponents = insertPanel.getComponents();
		// Crear y añadir nuevos hijos al pastPanel a partir de los hijos del
		// insertPanel
		for (int i = 0; i < insertedComponents.length; i++) {
			Component insertComp = insertedComponents[i];
			Component comp = new JEditorPane();
			comp.setBounds(55 * i + 55, 11, 20, 20);
			comp.setBackground(insertComp.getBackground());
			comp.setEnabled(false);
			pastPanel.add(comp);
		}

		pastPanel.setVisible(true);

		// Añadir el nuevo panel al panel padre
		padrePanel.add(pastPanel);

	}

	/**
	 * Itera los colores secretos para ver cuantos colores se han acertado y si
	 * estan en las posiciones correctas.
	 * 
	 * @param colorCasilla
	 * @param posicionCasilla
	 */
//	public void iterarColoresSecretos(Color colorCasilla, int posicionCasilla) {
//		int estadoColor = 0;
//		System.out.println("_______");
//
//		// Iterar los colores secretos
//		for (int j = 0; j < coloresSecretos.size(); j++) {
//			Color colorSecreto = coloresSecretos.get(j);
//
//			// Saltar loop si el color se ha encontrado
//			if (posicionesEncontradas[j]) {
//				continue;
//			}
//
//			// Comprobar si el color y la posición coinciden
//			if (colorCasilla.equals(colorSecreto) && posicionCasilla == j) {
//				System.out.println("Color encontrado y posición correcta");
//				estadoColor = 2;
//				break;
//			} else if (colorCasilla.equals(colorSecreto)) {
//				// Setear la posicion del color como encontrada
//				posicionesEncontradas[j] = true;
//				System.out.println("Color encontrado");
//				estadoColor = 1;
//			}
//		}
//
//		// Sumar el estado de la comprobación
//		switch (estadoColor) {
//		case 1:
//			totalEncontrados++;
//			break;
//		case 2:
//			totalPerfectos++;
//			break;
//
//		default:
//			break;
//		}
//
//	}

	/**
	 * Mostrar los aciertos en la vista
	 */
	public void mostrarAciertos(JPanel panelPadre, int nrIntento) {
		JPanel panelValidados = new JPanel();
		panelValidados.setLayout(null);
		panelValidados.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		panelValidados.setBounds(385, 40 * nrIntento, 220, 40);
		// Mostrar con negro los colores acertados y en la posicion correcta
		for (int i = 0; i < totalPerfectos; i++) {
			JEditorPane comp = new JEditorPane();
			comp.setBounds(40 * i + 40, 11, 20, 20);
			comp.setBackground(Color.BLACK);
			comp.setEnabled(false);
			panelValidados.add(comp);
		}
		// Mostrar con blanco solo los colores acertados pero no en la posicion correcta
		for (int i = 0; i < totalEncontrados; i++) {
			JEditorPane comp = new JEditorPane();
			comp.setBounds(40 * (i + totalPerfectos) + 40, 11, 20, 20);
			comp.setBackground(Color.WHITE);
			comp.setEnabled(false);
			panelValidados.add(comp);
		}

		panelPadre.add(panelValidados);

	}

	/**
	 * Validar los colores de las casillas
	 * 
	 */
//	public void validarCasillas(JPanel insertPanel) {
//		// Resetear el estado de las posiciones
//		totalPerfectos = 0;
//		totalEncontrados = 0;
//		for (int i = 0; i < posicionesEncontradas.length; i++) {
//			posicionesEncontradas[i] = false;
//		}
//		System.out.println("-------------------------");
//		// Obtener los componentes del insertPanel
//		Component[] insertedComponents = insertPanel.getComponents();
//
//		// Iterar los colores Casillas introducidos
//		for (int i = 0; i < insertedComponents.length; i++) {
//			Component insertComp = insertedComponents[i];
//			Color colorCasilla = insertComp.getBackground();
//			int posicionCasilla = i;
//
//			iterarColoresSecretos(colorCasilla, posicionCasilla);
//
//		}
//	}

//	public void comprobarPosicionCasilla() {
//		
//	}

	/**
	 * Loopear las casillas input y validarlas con las casillas secretas
	 * 
	 * @param casillasInput
	 */
	public void validarCasillas(ArrayList<Casilla> casillasInput) {

		totalPerfectos = 0;
		totalEncontrados = 0;
		// Reset casilla input comprobada con cada nuevo intento
		for (int i = 0; i < casillasInput.size(); i++) {
			Casilla casilla = casillasInput.get(i);
			casilla.setComprobada(false);
		}
		// Reset casilla Secreta comprobada con cada nuevo intento
		for (int i = 0; i < casillasSecretas.size(); i++) {
			Casilla casilla = casillasSecretas.get(i);
			casilla.setComprobada(false);
		}
		/*
		 * 
		 * 
		 */
		// Comprueba color y posición correcta de las parejas casillas de input y
		// secreto de la misma posición.
		for (int i = 0; i < casillasInput.size(); i++) {
			Casilla casillaInput = casillasInput.get(i);
			Color colorCasillaInput = casillasInput.get(i).getBackground();
			Color colorCasillaSecreta = casillasSecretas.get(i).getBackground();
			// Posicion y color correctos
			if (colorCasillaInput.equals(colorCasillaSecreta)) {
				casillasSecretas.get(i).setComprobada(true);
				casillaInput.setComprobada(true);
				totalPerfectos++;
			}
		}

		// Comprueba si el color es correcto en otra posición del grupo de casillas
		// secretas
		for (int i = 0; i < casillasInput.size(); i++) {
			Casilla casillaInput = casillasInput.get(i);
			Color colorCasillaInput = casillasInput.get(i).getBackground();
			// For grupo casillas secretas
			for (int j = 0; j < casillasSecretas.size(); j++) {
				Casilla casillaSecreta = casillasSecretas.get(j);
				Color colorCasillaSecreta = casillaSecreta.getBackground();
				if (!casillaSecreta.getComprobada() 
						&& !casillaInput.getComprobada()
						&& colorCasillaInput.equals(colorCasillaSecreta)) {
					casillaSecreta.setComprobada(true);
					totalEncontrados++;
					// Una vez encontrado salir del loop;
					break;
				}
			}
		}

		// Comprobar si has ganado
		if (totalPerfectos >= 4) {
			finalizarPartida();
		}

	}

	/**
	 * Loopear las casillas secretas
	 */
//	private void comprobarCasillasSecretas(Color colorCasillaInput) {
//		for (int i = 0; i < casillasSecretas.size(); i++) {
//			Color colorCasillaSecreta = casillasSecretas.get(i).getBackground();
//			boolean yacomprobada = casillasSecretas.get(i).getComprobada();
//			// Saltar loop si casilla ya encontrada
//			if (yacomprobada) {
//				continue;
//			}
//			if (!yacomprobada && colorCasillaInput.equals(colorCasillaSecreta)) {
//				casillasSecretas.get(i).setComprobada(true);
//				totalEncontrados++;
//				// Salir si se ha encontrado el color
//				break;
//			}
//		}
//
//	}

	/**
	 * Ejecutado al perder o ganar la partida
	 */
	public void finalizarPartida() {
		labelFinalizada.setVisible(true);
		comprobarBtn.setEnabled(false);

		// En caso de ganar
		if (totalPerfectos >= 4) {
			labelFinalizada.setText("Has ganado!");
		}
		// Mostrar la combinación secreta de colores al finalizar
		secretosPanel.setVisible(true);
	}

}
