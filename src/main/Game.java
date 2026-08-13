package main;

import javax.swing.JFrame;
import Girdiler.KlavyeDinleyici;
import Girdiler.FareDinleyici;
import Sahneler.Duzenleme;
import Sahneler.Menu;
import Sahneler.Oynaniyor;
import Sahneler.OyunBitti;
import Yardimcilar.YukleKaydet;
import Yoneticiler.HaritaYoneticisi;


public class Game extends JFrame implements Runnable {

	private static final long ZamanBasınaYukseltme = 1_000_000_000L / 60; // saniyede 60 güncelleme: dalga/gecikme sayaçları (60*3, 60*5...) bu hıza göre ayarlı
	private OyunEkranı oyunEkranı;
	private Thread gameThread;
	private Goruntuleme goruntuleme;
	private Menu menu;
	private Oynaniyor oynaniyor;
	private Duzenleme duzenleme;
	private OyunBitti oyunBitti;

	private HaritaYoneticisi haritaYoneticisi;

	public Game() {

		initClasses();
		olusturStandartLevel();

		setStandartCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Oyunum");
		add(oyunEkranı);
		pack();
		setVisible(true);

	}

	private void setStandartCloseOperation(int exitOnClose) {
		setDefaultCloseOperation(exitOnClose);
	}

	private void olusturStandartLevel() {
		int[] dizi = new int[400];
		for (int i = 0; i < dizi.length; i++)
			dizi[i] = 0;

		YukleKaydet.olusturLevel("new_level", dizi);

	}

	private void initClasses() {
		haritaYoneticisi = new HaritaYoneticisi();
		goruntuleme = new Goruntuleme(this);
		oyunEkranı = new OyunEkranı(this);
		menu = new Menu(this);
		oynaniyor = new Oynaniyor(this);
		duzenleme = new Duzenleme(this);
		oyunBitti = new OyunBitti(this);

	}

	private void Baslangic() {
		gameThread = new Thread(this) {
		};

		gameThread.start();
	}

	private void GuncelleOyun() {
		switch (OyunIstatislikleri.OyunDurumu) {
		case DUZENLE:
			duzenleme.Yukseltme();
			break;
		case MENU:
			break;
		case Oynaniyor:
			oynaniyor.Yukseltme();
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {

		Game game = new Game();
		game.oyunEkranı.BaslangicGirdileri();
		game.Baslangic();

	}

	@Override
	public void run() {

		
		long SonYukseltme = System.nanoTime();
		long now;

		while (true) {
			now = System.nanoTime();

			if (now - SonYukseltme >= ZamanBasınaYukseltme) {
				GuncelleOyun();
				oyunEkranı.repaint();
				SonYukseltme = now;
			}
		}

	}

	public Goruntuleme getRender() {
		return goruntuleme;
	}

	public Menu getMenu() {
		return menu;
	}

	public Oynaniyor getOynaniyor() {
		return oynaniyor;
	}


	public Duzenleme getDuzenleme() {
		return duzenleme;
	}

	public OyunBitti getOyunBitti() {
		return oyunBitti;
	}

	public HaritaYoneticisi getKaroYoneticisi() {
		return haritaYoneticisi;
	}

}