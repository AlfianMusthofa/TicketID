/**
 * KeretaApi
 */
public class KeretaApi extends User {

    private int tanggalPergi;
    private int tanggalPulang = 0;
    private int NoKursi;
    private int pilihanKereta;
    private String dari;
    private String ke;

    public KeretaApi(KeretaApiBuilder builder) {
        super(builder.username, builder.password, builder.nik, builder.ponsel);
        this.tanggalPergi = builder.tanggalPergi;
        this.NoKursi = builder.NoKursi;
        this.pilihanKereta = builder.pilihanKereta;
        this.dari = builder.dari;
        this.ke = builder.ke;
        this.tanggalPulang = builder.tanggalPulang;
    }

    public int getNokursi(){
        return NoKursi;
    }


    @Override
    public void displayInfo() {
        
        String namaKereta = (pilihanKereta == 1) ? "Argo Bromo" : "Parahyangan";

        System.out.println("------------------------------");
        System.out.println("-           Ticket           -");
        System.out.println("------------------------------");
        System.out.println("Nama           : " + getUsername());
        System.out.println("NIK            : " + getNik());
        System.out.println("Ponsel         : " + getPonsel());
        System.out.println("Dari           : " + this.dari);
        System.out.println("Ke             : " + this.ke);
        System.out.println("Tanggal Pergi  : " + this.tanggalPergi);
        System.out.println("Tanggal Pulang : " + this.tanggalPulang);
        System.out.println("No.Kursi       : " + getNokursi());
        System.out.println("Nama Kereta    : " + namaKereta);
        System.out.println("------------------------------");

    }

    
}