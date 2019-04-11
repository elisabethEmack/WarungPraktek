/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;

import java.io.Serializable;

/**
 *
 * @author Windows 8
 */
public class PendaftarYudisium implements Serializable {

    String nim;
    String nama, noHP, periodeYudisium, tanggalDaftar, syarat[];

    public PendaftarYudisium(String nim, String noHP, String nama, String tanggalDaftar) {
        this.nim = nim;
        this.noHP = noHP;
        this.nama = nama;
        this.tanggalDaftar = tanggalDaftar;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPeriodeYudisium() {
        return periodeYudisium;
    }

    public void setPeriodeYudisium(String bulan, String tahun) {
        this.periodeYudisium = bulan + " " + tahun;
    }

    public String getTanggalDaftar() {
        return tanggalDaftar;
    }

    public void setTanggalDaftar(String tanggalDaftar) {
        this.tanggalDaftar = tanggalDaftar;
    }

    public String[] getSyarat() {
        return syarat;
    }

    public void setSyarat(String[] syarat) {
        this.syarat = syarat;
    }

    @Override
    public String toString() {
        return nim+":"+periodeYudisium;
    }

}
