package Sahneler;

import java.awt.Graphics;
import main.Game;
import ui.Buton;
import static main.OyunIstatislikleri.*;

public class Menu extends OyunEkrani implements SahneMethotlari {

	private Buton bOynaniyor, bDUZENLE, bCikis;

	public Menu(Game game) {
		super(game);
		initButonlar();
	}

	private void initButonlar() {

		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;

		bOynaniyor = new Buton("Oyna", x, y, w, h);
		bDUZENLE = new Buton("DUZENLE", x, y + yOffset, w, h);
		bCikis = new Buton("Çıkış", x, y + yOffset * 2, w, h);
	}

	@Override
	public void render(Graphics g) {

		Butonciz(g);
	}

	private void Butonciz(Graphics g) {
		bOynaniyor.draw(g);
		bDUZENLE.draw(g);
		bCikis.draw(g);

	}

	@Override
	public void mouseClicked(int x, int y) {
	}

	@Override
	public void mouseMoved(int x, int y) {
		bOynaniyor.MouseUzerinde(false);
		bDUZENLE.MouseUzerinde(false);
		bCikis.MouseUzerinde(false);

		if (bOynaniyor.getSinirlar().contains(x, y))
			bOynaniyor.MouseUzerinde(true);
		else if (bDUZENLE.getSinirlar().contains(x, y))
			bDUZENLE.MouseUzerinde(true);
		else if (bCikis.getSinirlar().contains(x, y))
			bCikis.MouseUzerinde(true);

	}


	@Override
	public void mouseReleased(int x, int y) {
		ButonSıfırla();
	}

	private void ButonSıfırla() {
		bOynaniyor.SıfırlaBooleans();
		bDUZENLE.SıfırlaBooleans();
		bCikis.SıfırlaBooleans();

	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(int x, int y) {

		if (bOynaniyor.getSinirlar().contains(x, y))
			SetOyunDurumu(Oynaniyor);
		else if (bDUZENLE.getSinirlar().contains(x, y))
			SetOyunDurumu(DUZENLE);
		else if (bCikis.getSinirlar().contains(x, y))
			System.exit(0);
	}

	@Override
	public void mouseClicked1(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}