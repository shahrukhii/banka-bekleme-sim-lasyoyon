 import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Random;

public class BankaSimulasyonu {
    // Bankanın ana kasası
    static double bankaKasasi = 100000.0; 

    public static void main(String[] args) {
        Queue<Musteri> sira = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int siraSayac = 100;

        System.out.println("=== 🏦 DEV JAVABANK SİMÜLASYONU BAŞLADI ===");
        System.out.println("Başlangıç Kasası: " + bankaKasasi + " TL");

        while (true) {
            System.out.println("\n--- ANA MENÜ ---");
            System.out.println("1- Müşteri Gelişi (Sıra Al)");
            System.out.println("2- Vezneleri Çalıştır (Müşterileri Çağır)");
            System.out.println("3- Kasa Durumunu Gör");
            System.out.println("4- Çıkış");
            System.out.print("Seçim: ");
            String anaSecim = scanner.nextLine();

            if (anaSecim.equals("1")) {
                // Rastgele müşteri oluşturma
                siraSayac++;
                String[] islemler = {"Para Çekme", "Para Yatırma"};
                String islem = islemler[random.nextInt(2)];
                double miktar = 500 + (2000 * random.nextDouble()); // 500-2500 arası rastgele miktar
                
                Musteri yeni = new Musteri(siraSayac, islem, miktar);
                sira.add(yeni);
                System.out.println("✅ Sıra No [" + siraSayac + "] alındı. İşlem: " + islem);

            } else if (anaSecim.equals("2")) {
                if (sira.isEmpty()) {
                    System.out.println("❌ Sırada bekleyen kimse yok.");
                } else {
                    // 3 Vezne varmış gibi simüle edelim
                    for (int v = 1; v <= 3; v++) {
                        if (!sira.isEmpty()) {
                            Musteri m = sira.poll();
                            System.out.println(">>> [VEZNE " + v + "] İşlem Yapıyor: " + m.siraNo);
                            islemYap(m);
                        }
                    }
                }
            } else if (anaSecim.equals("3")) {
                System.out.println("💰 Güncel Banka Kasası: " + String.format("%.2f", bankaKasasi) + " TL");
                System.out.println("👥 Sırada Bekleyen Sayısı: " + sira.size());

            } else if (anaSecim.equals("4")) {
                System.out.println("Banka kapandı. Kasadaki para koruma altına alındı!");
                break;
            }
        }
    }

    // Para mekaniğini yöneten metod
    public static void islemYap(Musteri m) {
        if (m.islemTipi.equals("Para Çekme")) {
            if (bankaKasasi >= m.miktar) {
                bankaKasasi -= m.miktar;
                System.out.println("   - " + m.siraNo + " nolu müşteri " + String.format("%.2f", m.miktar) + " TL çekti.");
            } else {
                System.out.println("   - ⚠️ KASADA YETERLİ PARA YOK! Müşteri sinirli ayrıldı.");
            }
        } else {
            bankaKasasi += m.miktar;
            System.out.println("   + " + m.siraNo + " nolu müşteri " + String.format("%.2f", m.miktar) + " TL yatırdı.");
        }
    }
}

// Yardımcı Müşteri Sınıfı
class Musteri {
    int siraNo;
    String islemTipi;
    double miktar;

    public Musteri(int siraNo, String islemTipi, double miktar) {
        this.siraNo = siraNo;
        this.islemTipi = islemTipi;
        this.miktar = miktar;
    }
}