package Yoneticiler;

import java.util.ArrayList;
import java.util.Arrays;
import Etkinlikler.Dalga;
import Sahneler.Oynaniyor;

public class DalgaYoneticisi {

	private Oynaniyor oynaniyor;
	private ArrayList<Dalga> dalgalar = new ArrayList<>();
	private int dusmancanlandirTickLimit = 60 * 1;
	private int dusmancanlandirTick = dusmancanlandirTickLimit;
	private int dusmanIndex, dalgaIndeksi;
	private int dalgaTickLimit = 60 * 5;
	private int dalgaTick = 0;
	private boolean dalgaBaslangicTimer, dalgaTickTimerOver;
	public DalgaYoneticisi(Oynaniyor oynaniyor) {
		this.oynaniyor = oynaniyor;
		olusturDalgalar();
	}
	public void Yukseltme() {
		if (dusmancanlandirTick < dusmancanlandirTickLimit)
			dusmancanlandirTick++;

		if (dalgaBaslangicTimer) {
			dalgaTick++;
			if (dalgaTick >= dalgaTickLimit) {
				dalgaTickTimerOver = true;
	     	}
		}
	}
	public void DalgaIndeksiniArttır() {
		dalgaIndeksi++;
		dalgaTick = 0;
		dalgaTickTimerOver = false;
		dalgaBaslangicTimer = false;
	}
	public boolean DalgaZamanayiciBittiMi() {
		return dalgaTickTimerOver;
	}
	public void BaslangicDalgaZamanlayici() {
		dalgaBaslangicTimer = true;
	}
	public int getsonrakidusman() {
		dusmancanlandirTick = 0;
		return dalgalar.get(dalgaIndeksi).getdusmanliste().get(dusmanIndex++);
	}
	private void olusturDalgalar() {
		dalgalar.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0))));
		dalgalar.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0))));
		dalgalar.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1))));
		dalgalar.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1))));
		dalgalar.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1))));
		dalgalar.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1))));
		dalgalar.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1))));
		dalgalar.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1))));
		dalgalar.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1))));
	}
	public ArrayList<Dalga> getDalgalar() {
		return dalgalar;
	}
	public boolean YeniDusmanZamaniMi() {
		return dusmancanlandirTick >= dusmancanlandirTickLimit;
	}
	public boolean DalgadaDahaDusmanVarMi() {
		return dusmanIndex < dalgalar.get(dalgaIndeksi).getdusmanliste().size();
	}
	public boolean DahaDalgaVarmı() {
		return dalgaIndeksi + 1 < dalgalar.size();
	}
	public void SıfırladusmanIndex() {
		dusmanIndex = 0;
	}
	public int getDalgaIndeksi() {
		return dalgaIndeksi;
	}
	public float getKalanSure() {
		float kalanTick = dalgaTickLimit - dalgaTick;
		return kalanTick / 60.0f;
	}
	public boolean isDalgaZamanlayiciBasladiMi() {
		return dalgaBaslangicTimer;
	}
	public void Sıfırla() {
		dalgalar.clear();
		olusturDalgalar();
		dusmanIndex = 0;
		dalgaIndeksi = 0;
		dalgaBaslangicTimer = false;
		dalgaTickTimerOver = false;
		dalgaTick = 0;
		dusmancanlandirTick = dusmancanlandirTickLimit;
	}

}
