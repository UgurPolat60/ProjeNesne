package Sahneler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;
import ui.Buton;
import static main.OyunIstatislikleri.*;

public class OyunBitti extends OyunEkrani implements SahneMethotlari {

	private Buton bTekrarOyna, bMenu;

	public OyunBitti(Game game) {
		super(game);
		initButonlar();
	}

	private void initButonlar() {
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 300;
		int yOffset = 100;
		bMenu = new Buton("Menü", x, y, w, h);
		bTekrarOyna = new Buton("Tekrar Oyna", x, y + yOffset, w, h);
	}

	@Override
	public void render(Graphics g) {
		g.setFont(new Font("LucidaSans", Font.BOLD, 50));
		g.setColor(Color.red);
		g.drawString("Oyun Bitti!", 160, 80);
		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		bMenu.draw(g);
		bTekrarOyna.draw(g);
	}

	private void OyunuBastanOyna() {
		SıfırlaAll();
		SetOyunDurumu(Oynaniyor);

	}
	private void SıfırlaAll() {
		game.getOynaniyor().SıfırlaHersey();
	}

	@Override
	public void mouseClicked(int x, int y) {
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.MouseUzerinde(false);
		bTekrarOyna.MouseUzerinde(false);

		if (bMenu.getSinirlar().contains(x, y))
			bMenu.MouseUzerinde(true);
		else if (bTekrarOyna.getSinirlar().contains(x, y))
			bTekrarOyna.MouseUzerinde(true);
	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.SıfırlaBooleans();
		bTekrarOyna.SıfırlaBooleans();

	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getSinirlar().contains(x, y)) {
			SetOyunDurumu(MENU);
			SıfırlaAll();
		} else if (bTekrarOyna.getSinirlar().contains(x, y))
			OyunuBastanOyna();
	}

	@Override
	public void mouseClicked1(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
