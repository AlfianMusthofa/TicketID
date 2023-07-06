public class Regular extends User {

    private int tanggalPergi;
    private int noKursi;
    private int pilihanKereta;
    private String dari;
    private String ke;

    public Regular(String username, String password, String nik, String ponsel, int tanggalPergi, int noKursi, int pilihanKereta, String dari, String ke) {
        super(username, password, nik, ponsel);
        this.tanggalPergi = tanggalPergi;
        this.noKursi = noKursi;
        this.pilihanKereta = pilihanKereta;
        this.dari = dari;
        this.ke = ke;
    }

    public int getTanggalPergi() {
        return tanggalPergi;
    }

    public int getNoKursi() {
        return noKursi;
    }

    public int getPilihanKereta() {
        return pilihanKereta;
    }

    public String getDari() {
        return dari;
    }

    public String getKe() {
        return ke;
    }

    @Override
    public void displayInfo() {
        // TODO Auto-generated method stub
    }
    
}
