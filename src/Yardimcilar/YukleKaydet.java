package Yardimcilar;

import java.awt.image.BufferedImage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import Objeler.Yol;

public class YukleKaydet {

	public static BufferedImage resimal() {
		BufferedImage foto = null;
		InputStream is = YukleKaydet.class.getClassLoader().getResourceAsStream("spriteatlas.png");

		try {
			foto = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return foto;
	}

	public static void olusturFile() {
		File txtFile = new File("res/testTextFile.txt");

		try {
			txtFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void olusturLevel(String Isim, int[] iddizi) {
		File newLevel = new File("res/" + Isim + ".txt");
		if (newLevel.exists()) {
			System.out.println("Dosya: " + Isim + " zaten var!");
			return;
		} else {
			try {
				newLevel.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			WriteToFile(newLevel, iddizi, new Yol(0, 0), new Yol(0, 0));
		}

	}

	private static void WriteToFile(File f, int[] iddizi, Yol Baslangic, Yol Bitis) {
		try {
			PrintWriter pw = new PrintWriter(f);
			for (Integer i : iddizi)
				pw.println(i);
			pw.println(Baslangic.getxKord());
			pw.println(Baslangic.getyKord());
			pw.println(Bitis.getxKord());
			pw.println(Bitis.getyKord());

			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void kaydetLevel(String Isim, int[][] iddizi, Yol Baslangic, Yol Bitis) {
		File levelFile = new File("res/" + Isim + ".txt");

		if (levelFile.exists()) {
			WriteToFile(levelFile, Yardim.IkiDden1DeIntDiziAl(iddizi), Baslangic, Bitis);
		} else {
			System.out.println("Dosya: " + Isim + " bulunamadi!");
			return;
		}
	}

	private static ArrayList<Integer> ReadFromFile(File File) {
		ArrayList<Integer> liste = new ArrayList<>();

		try {
			Scanner sc = new Scanner(File);

			while (sc.hasNextLine()) {
				liste.add(Integer.parseInt(sc.nextLine()));
			}

			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return liste;
	}

	public static ArrayList<Yol> GetLevelyolpuanlari(String Isim) {
		File lvlFile = new File("res/" + Isim + ".txt");

		if (lvlFile.exists()) {
			ArrayList<Integer> liste = ReadFromFile(lvlFile);
			ArrayList<Yol> puanlar = new ArrayList<>();
			puanlar.add(new Yol(liste.get(400), liste.get(401)));
			puanlar.add(new Yol(liste.get(402), liste.get(403)));

			return puanlar;

		} else {
			System.out.println("Dosya: " + Isim + " bulunamadi!");
			return null;
		}
	}

	public static int[][] GetLevelData(String Isim) {
		File lvlFile = new File("res/" + Isim + ".txt");

		if (lvlFile.exists()) {
			ArrayList<Integer> liste = ReadFromFile(lvlFile);
			return Yardim.ArrayListTo2Dint(liste, 20, 20);

		} else {
			System.out.println("Dosya: " + Isim + " bulunamadi!");
			return null;
		}

	}
}
