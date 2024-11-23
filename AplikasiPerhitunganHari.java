package com.example.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AplikasiPerhitunganHari extends JFrame {
    private JComboBox<String> cbBulan;
    private JSpinner spTahun;
    private JLabel lblJumlahHari, lblHariPertama, lblHariTerakhir;
    private JButton btnHitung, btnHitungSelisih;
    private JSpinner spTanggal1, spTanggal2;

    public AplikasiPerhitunganHari() {
        // Pengaturan JFrame
        setTitle("Aplikasi Perhitungan Hari");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel untuk input bulan dan tahun
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 10, 10));
        panelInput.add(new JLabel("Pilih Bulan:"));
        cbBulan = new JComboBox<>(new String[]{
                "Januari", "Februari", "Maret", "April", "Mei", "Juni",
                "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        });
        panelInput.add(cbBulan);

        panelInput.add(new JLabel("Masukkan Tahun:"));
        spTahun = new JSpinner(new SpinnerNumberModel(2023, 1, 9999, 1));
        panelInput.add(spTahun);

        panelInput.add(new JLabel("Tanggal 1 (yyyy-MM-dd):"));
        spTanggal1 = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorTanggal1 = new JSpinner.DateEditor(spTanggal1, "yyyy-MM-dd");
        spTanggal1.setEditor(editorTanggal1);
        panelInput.add(spTanggal1);

        panelInput.add(new JLabel("Tanggal 2 (yyyy-MM-dd):"));
        spTanggal2 = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorTanggal2 = new JSpinner.DateEditor(spTanggal2, "yyyy-MM-dd");
        spTanggal2.setEditor(editorTanggal2);
        panelInput.add(spTanggal2);

        // Panel untuk output
        JPanel panelOutput = new JPanel(new GridLayout(3, 1));
        lblJumlahHari = new JLabel("Jumlah Hari: ", JLabel.CENTER);
        lblHariPertama = new JLabel("Hari Pertama: ", JLabel.CENTER);
        lblHariTerakhir = new JLabel("Hari Terakhir: ", JLabel.CENTER);
        panelOutput.add(lblJumlahHari);
        panelOutput.add(lblHariPertama);
        panelOutput.add(lblHariTerakhir);

        // Tombol
        btnHitung = new JButton("Hitung Hari");
        btnHitungSelisih = new JButton("Hitung Selisih");

        JPanel panelButton = new JPanel();
        panelButton.add(btnHitung);
        panelButton.add(btnHitungSelisih);

        // Tambahkan komponen ke JFrame
        setLayout(new BorderLayout());
        add(panelInput, BorderLayout.NORTH);
        add(panelOutput, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        // Event Handling
        btnHitung.addActionListener(e -> hitungHari());
        btnHitungSelisih.addActionListener(e -> hitungSelisih());
    }

    private void hitungHari() {
        try {
            int bulan = cbBulan.getSelectedIndex() + 1;
            int tahun = (int) spTahun.getValue();

            // Perhitungan jumlah hari dalam bulan
            LocalDate tanggalPertama = LocalDate.of(tahun, bulan, 1);
            LocalDate tanggalTerakhir = tanggalPertama.withDayOfMonth(tanggalPertama.lengthOfMonth());

            lblJumlahHari.setText("Jumlah Hari: " + tanggalPertama.lengthOfMonth());
            lblHariPertama.setText("Hari Pertama: " + tanggalPertama.getDayOfWeek());
            lblHariTerakhir.setText("Hari Terakhir: " + tanggalTerakhir.getDayOfWeek());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan dalam perhitungan.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hitungSelisih() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate tanggal1 = LocalDate.parse(new JSpinner.DateEditor(spTanggal1, "yyyy-MM-dd").getFormat().format(spTanggal1.getValue()), formatter);
            LocalDate tanggal2 = LocalDate.parse(new JSpinner.DateEditor(spTanggal2, "yyyy-MM-dd").getFormat().format(spTanggal2.getValue()), formatter);

            long selisihHari = ChronoUnit.DAYS.between(tanggal1, tanggal2);
            JOptionPane.showMessageDialog(this, "Selisih Hari: " + Math.abs(selisihHari) + " hari", "Hasil", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan dalam menghitung selisih hari.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplikasiPerhitunganHari app = new AplikasiPerhitunganHari();
            app.setVisible(true);
        });
    }
}

