/**
 * TicketWallet
 */
public class TicketWallet {

    private int nominal;

    public void beliSaldo(int nominal){
        this.nominal += nominal;
        System.out.println("Pembelian berhasil!");
    }

    public void cekSaldo(){
        System.out.println("Saldo anda Rp." + this.nominal);
    }

    public void bayarTiket(int nominal){

        if (nominal <= this.nominal) {
            this.nominal -= nominal;
            System.out.println("Pembelian berhasil!");
        }
        
        else {
            System.out.println("Saldo tidak cukup!");
        }
    }

    public int getNominal(){
        return nominal;
    }

    public void setSaldo(int nominal){
        this.nominal = nominal;
    }
}