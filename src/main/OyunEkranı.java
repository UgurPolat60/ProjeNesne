package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import Girdiler.KlavyeDinleyici;
import Girdiler.FareDinleyici;

public class OyunEkranı extends JPanel {
	private Game game;
	private Dimension Boyut;
	private FareDinleyici fareDinleyici;
	private KlavyeDinleyici klavyeDinleyici;

	public OyunEkranı(Game game) {
		this.game = game;

		setPanelSize();
	}
	public void BaslangicGirdileri() {
		fareDinleyici = new FareDinleyici(game);
		klavyeDinleyici = new KlavyeDinleyici(game);

		addMouseListener(fareDinleyici);
		addMouseMotionListener(fareDinleyici);
		addKeyListener(klavyeDinleyici);
		requestFocus();
	}

	private void setPanelSize() {
		Boyut = new Dimension(640, 800);
		setMinimumSize(Boyut);
		setPreferredSize(Boyut);
		setMaximumSize(Boyut);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		game.getRender().render(g);

	}

}