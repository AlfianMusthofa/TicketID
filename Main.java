import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import koneksi.koneksiDB;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {

            char mainmenu;
            char tiket;
            String username = "";
            String password = "";
            String nik = "";
            String ponsel = "";
            User regulerUser = null;
            User guestUser = null;
            List<User> users = new ArrayList<>();
            TicketWallet wallet = new TicketWallet();
            int hargaTiket = 0;

            do {
                ClearScreen.clearScreen();
                System.out.println("Main Menu");
                System.out.println("1. Registrasi");
                System.out.println("2. Login");
                System.out.println("3. Beli Saldo TicketWallet");
                System.out.println("4. Cek Saldo TicketWallet");
                System.out.print("Pilihan : ");
                int pilihan = input.nextInt();

                switch (pilihan) {
                    case 1: // Registrasi
                        try (Connection connection = koneksiDB.getConnection()) {

                            System.out.println("-----------------------------");
                            System.out.println("|         Registrasi        |");
                            System.out.println("-----------------------------");
                            System.out.print("Masukkan username  : ");
                            username = input.next();
                            System.out.print("Masukkan password  : ");
                            password = input.next();
                            System.out.print("Masukkan nik       : ");
                            nik = input.next();
                            System.out.print("Masukkan No.Ponsel : ");
                            ponsel = input.next();

                            // ADD TO TABLE DATABASES
                            String insertQuery = "INSERT INTO user (username, password, nik, nohp) VALUES (?, ?, ?, ?)";

                            try (PreparedStatement pst = connection.prepareStatement(insertQuery)) {
                                pst.setString(1, username);
                                pst.setString(2, password);
                                pst.setString(3, nik);
                                pst.setString(4, ponsel);
                                pst.executeUpdate();
                                System.out.println("Registrasi berhasil!");

                            } catch (SQLException e) {
                                System.out.println("Terjadi kesalahan saat menyimpan data pengguna: " + e.getMessage());
                            }

                        } catch (SQLException e) {
                            System.out.println("Koneksi ke database gagal: " + e.getMessage());
                        }

                        break;

                    case 2: // Login

                        try (Connection connection = koneksiDB.getConnection()) {

                            System.out.println("-----------------------------");
                            System.out.println("|           Login           |");
                            System.out.println("-----------------------------");
                            System.out.print("Masukkan username : ");
                            String loginUsername = input.next();
                            System.out.print("Masukkan password : ");
                            String loginPassword = input.next();

                            // CHECK LOGIN
                            String loginQuery = "SELECT * FROM user WHERE username = ? AND password = ?";

                            try (PreparedStatement pst = connection.prepareStatement(loginQuery)) {

                                pst.setString(1, loginUsername);
                                pst.setString(2, loginPassword);
                                ResultSet result = pst.executeQuery();

                                if (result.next()) {
                                    
                                    do {
                                        
                                        System.out.println("1. Kereta Api");
                                        System.out.print("pilihan : ");
                                        int pilihan_trasn = input.nextInt();

                                        switch (pilihan_trasn) {
                                            case 1:
                                                
                                                System.out.print("Dari : ");
                                                String dari = input.next();
                                                System.out.print("Ke   : ");
                                                String ke = input.next();
                                                System.out.println("---------------");
                                                System.out.println("Mode perjalanan");
                                                System.out.println("1. Sekali jalan");
                                                System.out.println("2. Pulang pergi");
                                                System.out.println("---------------");
                                                System.out.print("Pilihan : ");
                                                int modeJalan = input.nextInt();
                                                System.out.print("Jumlah Penumpang : ");
                                                int jumlahPenumpang = input.nextInt();

                                                switch (modeJalan) {
                                                    case 1: // Sekali jalan

                                                        System.out.print("Tanggal Pergi : ");
                                                        int tanggalPergi = input.nextInt();
                                                        System.out.println("1. Argo Bromo");
                                                        System.out.println("2. Parahyangan");
                                                        System.out.print("Pilihan : ");
                                                        int pilihanKereta = input.nextInt();
                                                        System.out.print("No.kursi : ");
                                                        int NoKursi = input.nextInt();

                                                        if (jumlahPenumpang == 1) {
                                                            String selectQuery = "SELECT username, nik, nohp FROM user WHERE username = ?";

                                                            try (PreparedStatement get = connection.prepareStatement(selectQuery)) {

                                                                get.setString(1, loginUsername);
                                                                ResultSet resultset = get.executeQuery();

                                                                if (resultset.next()) {

                                                                    String usernameDB = resultset.getString("username");
                                                                    String nikDB = resultset.getString("nik");
                                                                    String nohpDB = resultset.getString("nohp");

                                                                    KeretaApiBuilder builder = new KeretaApiBuilder(usernameDB, loginPassword, nikDB, nohpDB)
                                                                    .setDari(dari)
                                                                    .setKe(ke)
                                                                    .setTanggalPergi(tanggalPergi)
                                                                    .setPilihanKereta(pilihanKereta)
                                                                    .setNoKursi(NoKursi);

                                                                    regulerUser = builder.build();
                                                                    hargaTiket = 50000;
                                                                    users.add(regulerUser);
                                                                    
                                                                }
                                                                
                                                            } catch (SQLException e) {
                                                                System.out.println("Terjadi kesalahan saat mengambil data pengguna: " + e.getMessage());
                                                            }
                                                        }

                                                        else if (jumlahPenumpang > 1){
                                                            String selectQuery = "SELECT username, nik, nohp FROM user WHERE username = ?";

                                                            try (PreparedStatement get = connection.prepareStatement(selectQuery)) {

                                                                get.setString(1, loginUsername);
                                                                ResultSet resultset = get.executeQuery();

                                                                if (resultset.next()) {

                                                                    String usernameDB = resultset.getString("username");
                                                                    String nikDB = resultset.getString("nik");
                                                                    String nohpDB = resultset.getString("nohp");

                                                                    KeretaApiBuilder builder = new KeretaApiBuilder(usernameDB, loginPassword, nikDB, nohpDB)
                                                                    .setDari(dari)
                                                                    .setKe(ke)
                                                                    .setTanggalPergi(tanggalPergi)
                                                                    .setPilihanKereta(pilihanKereta)
                                                                    .setNoKursi(NoKursi);

                                                                    regulerUser = builder.build();
                                                                    hargaTiket = 50000;
                                                                    users.add(regulerUser);

                                                                    for (int i = 1; i < jumlahPenumpang; i++){

                                                                        System.out.println("--------------------------");
                                                                        System.out.println("Masukkan data penumpang ke " + (i+1));
                                                                        System.out.print("Nama      : ");
                                                                        String guestUsername = input.next();
                                                                        System.out.print("NIK       : ");
                                                                        String guestNIK = input.next();
                                                                        System.out.print("No.Kursi  : ");
                                                                        int guestNoKursi = input.nextInt();
                                                                        System.out.print("No.Ponsel : ");
                                                                        String guestNoponsel = input .next();
                                                                        System.out.println("--------------------------");

                                                                        guestUser = new Guest(guestUsername,"", guestNIK,guestNoponsel,guestNoKursi);

                                                                        KeretaApiBuilder builder2 = new KeretaApiBuilder(guestUsername, "", guestNIK, guestNoponsel)
                                                                        .setDari(dari)
                                                                        .setKe(ke)
                                                                        .setPilihanKereta(pilihanKereta)
                                                                        .setNoKursi(guestNoKursi)
                                                                        .setTanggalPergi(tanggalPergi);

                                                                        guestUser = builder2.build();
                                                                        hargaTiket = 50000*jumlahPenumpang;
                                                                        users.add(guestUser);

                                                                    }
                                                                    
                                                                }

                                                                
                                                                
                                                            } catch (SQLException e) {
                                                                System.out.println("Terjadi kesalahan saat mengambil data pengguna: " + e.getMessage());
                                                            }

                                                            
                                                        }

                                                        // Sistem Pembayaran
                                                        System.out.println("Total Pembayaran Rp." + hargaTiket);
                                                        System.out.print("Masukkan nominal bayar Rp.");
                                                        int nominalBayar = input.nextInt();

                                                        if (nominalBayar >= hargaTiket) {
                                                            wallet.bayarTiket(hargaTiket);
                                                            for (User user : users){
                                                                user.displayInfo();
                                                            }
                                                        }
                                                        else {
                                                            System.out.println("E-Ticket tidak cukup!");
                                                        }

                                                        break;
                                                
                                                    default:
                                                        break;
                                                }

                                                break;
                                        
                                            default:
                                                break;
                                        }

                                        System.out.print("Kembali [y/t]: ");
                                        tiket = input.next().charAt(0);
                                    } while (tiket != 't');

                                }
                                else {
                                    System.out.println("Username atau Password Salah!");
                                }
                                
                            } catch (SQLException e) {
                                System.out.println("Terjadi kesalahan saat login: " + e.getMessage());
                            }

                        } catch (SQLException e) {
                            System.out.println("Koneksi ke database gagal: " + e.getMessage());
                        }

                        break;

                    case 3:

                        System.out.print("Masukkan nominal Rp.");
                        int nominalBeli = input.nextInt();

                        wallet.beliSaldo(nominalBeli);

                        break;

                    default:

                        wallet.cekSaldo();

                        break;
                }

                System.out.print("Main menu [y/t]: ");
                mainmenu = input.next().charAt(0);
            } while (mainmenu != 't');

        }

    }
}