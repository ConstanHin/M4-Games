package vistaJuego;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CasillaMouseListener implements MouseListener {
	
	Controlador controlador = new Controlador();

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		controlador.cambiarColor(e.getButton(), e.getComponent());
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
