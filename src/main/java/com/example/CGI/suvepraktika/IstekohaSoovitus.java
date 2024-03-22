package com.example.CGI.suvepraktika;
import org.springframework.stereotype.Service;
import java.util.Random;

/**
 * Klass nimega IstekohaSoovitus, mis soovitab kasutajale istekoha vastavalt
 * vabadele kohtadele saalis ja mitu piletit soovitakse osta.
 *
 * Näide milline võib 4x5 saal peale soovitamist välja näha:
 *
 * [[2,0,3,0,0],
 * [1,1,4,4,4],
 * [0,0,1,1,1],
 * [2,0,3,0,1]]
 *
 * 1 tähistab, et koht on juba võetud, 0 tähistab vabu kohti, 2 ja 3 vahel (kaasaaravtud 2,3) on piisavalt kohti,
 * et soovitud piletid saaksid koos istuda ja 4 tähistab kohti mida minu algoritm soovitas kasutajale.
 */
@Service
public class IstekohaSoovitus {

    /**
     * Meetod, mis suvaliselt täidab saali suvalise arvu võetud istekohtadega.
     * @return kinosaal mis on suvaliste täidetud istekohatedga. Tulemus on 2D maatriks, kus 0 tahistab et koht on vaba
     * ja 1 et koht on voetud.
     */
    public int[][] genereeriSaal(int laius, int korgus, int soovitudKohad) {
        // arvuta saali suurus
        int maxKohad = laius*korgus;
        int[][] saal = new int[laius][korgus];
        Random random = new Random();
        // vahemalt 5 kohta on igas saalis tuhi (lisaks soovitud kohtadele)
        int voetudKohad = random.nextInt((maxKohad)- 5 - soovitudKohad);
        // suvaliselt täida kino võetud istekohtadega
        while (voetudKohad > 0) {
            int rida = random.nextInt(laius);
            int veerg = random.nextInt(korgus);
            if (saal[rida][veerg] == 0) { // tühjad kohad on tähistatud 0-ga ja võetud kohad 1-ga
                saal[rida][veerg] = 1;
                voetudKohad--;
            }
        }
        return saal;
    }

    /**
     * Meetod, mis soovitab kasutajale istekohad, soltuvalt kui palju pileteid ta soovib osta.
     * @return lopplik kinosaal koos soovitatud kohtadega, mis on esitatud int[][] kujul. 0 tähistab et koht on vaba, 1 tähistab et koht on võetud,
     * number 2st 3ni on piisavalt kohti, et piletid saaks kõrvuti istuda ja kohad margistatud number 4 on soovitatud kohad.
     */
    public int[][] soovitaKohad(int[][] saal, int soovitudKohad) {

        int veerg = saal[0].length;
        int rida = saal.length;
        // kuna koige optimaalsemad kohad on saali keskel, siis alusta soovitamist keskmisest reast ja veerust
        int keskmineRida = rida / 2;
        int keskmineVeerg = veerg / 2;
        // variable mis hoiab rea jarge (ehk millisel real hetkel asun)
        int reacount = 1;
        // variable mis hoiab jarge sellel kas oled ulemises voi alumises saali osas
        boolean ulemine = false;
        int k = keskmineRida;
        int j;
        while (k >= 0 && k < rida) {
            // alustad rea keskelt ja hakka otsima vabu kohti paremale
            for (j = keskmineVeerg; j < veerg; j++) {

                //kui kohtad num 2 siis taida soovitud kohad paremale
                if (saal[k][j] == 2) {
                    for (int l = j; l < Math.min(j + soovitudKohad, veerg); l++) {
                        saal[k][l] = 4; // soovitatud kohad margista number 4-ga
                    }
                    return saal; // selleks hetkes on kohad soovitatud ning voib saali tagastada
                    //kui kohtad num 3 siis taida soovitud kohad vasakule
                } else if (saal[k][j] == 3) {
                    for (int l = j; l > Math.max(j - soovitudKohad, -1); l--) {
                        saal[k][l] = 4; // soovitatud kohad margista number 4-ga
                    }
                    return saal; // selleks hetkes on kohad soovitatud ning voib saali tagastada
                }
            }
            // alustad rea keskelt ja hakka otsima vabu kohti vasakule
            for (j = keskmineVeerg - 1; j >= 0; j--) {
                //kui kohtad num 2 siis taida soovitud kohad paremale
                if (saal[k][j] == 2) {
                    for (int l = j; l < Math.min(j + soovitudKohad, veerg); l++) {
                        saal[k][l] = 4; // soovitatud kohad margista number 4-ga
                    }
                    return saal; // selleks hetkes on kohad soovitatud ning voib saali tagastada
                    //kui kohtad num 3 siis taida soovitud kohad vasakule
                } else if (saal[k][j] == 3) {
                    for (int l = j; l > Math.max(j - soovitudKohad, -1); l--) {
                        saal[k][l] = 4; // soovitatud kohad margista number 4-ga
                    }
                    return saal; // selleks hetkes on kohad soovitatud ning voib saali tagastada
                }
            }
            // kui oled ulemises pooles saalis, siis tahad jargmisena jouda samale kaugele keskmisest reast, aga saali alumisse ossa
            // NT. kui oled keskmisest reast 3 rida korgemal, siis jargmisena tahad jouda keskmisest reast 3 rida madalamale
            if (!ulemine) {
                k = k - reacount;
                ulemine = true;
                reacount += 1;
            } else {
                k = k + reacount;
                ulemine = false;
                reacount += 1;
            }
        }
        // kui soovitud kohtadele (ehk piletiarvule) pole piisavalt kohti korvuti, siis jaga soovitudKohad pooleks ja proovi uuesti
        return polePiisavaltKohti(saal, soovitudKohad);
    }

    /**
     * Meetod mida kasutatakse kui pole piisavalt jarjestikke kohti uhes reas. See meetod jagab soovitud kohad pooleks
     * ja kutsub uuesti soovitaKohad meetodi.
     * @return lopplik kinosaal koos soovitatud kohtadega, mis on esitatud int[][] kujul. 0 tähistab et koht on vaba, 1 tähistab et koht on võetud,
     * number 2st 3ni on piisavalt kohti, et piletid saaks kõrvuti istuda ja kohad margistatud number 4 on soovitatud kohad.
     */
    public int[][] polePiisavaltKohti(int[][] saal, int soovitudKohad){
        // jaga kohad pooleks
        int pooledKohad;
        if(soovitudKohad % 2 == 0){
            pooledKohad = (soovitudKohad / 2);
        }
        else{
            pooledKohad = (soovitudKohad / 2) + 1; //kui soovitudKohad on paaritu arv siis umarda ules
        }
        int ulejaanudKohad = soovitudKohad - pooledKohad;
        // leia jarjest kohad uutele soovitud kohtadele
        int[][] jarjestKohad = leiaJarjestKohad(saal, pooledKohad);
        // alguses paiguta/soovita esimesed pool kohti ja siis ulejaanud kohad
        int[][] poolSaal = soovitaKohad(jarjestKohad, pooledKohad);
        return soovitaKohad(poolSaal, ulejaanudKohad);

    }

    /**
     * Meetod mis kontrollib, kas saalis on piisavalt järjestikke kohti soovitud kohtade jaoks. (Kas mitu piletit saab kõrvuti istuda)
     * @return kinosaal, mis on esitatud int[][] kujul, kus 0 tähistab et koht on vaba, 1 tähistab et koht on võetud
     * ja number 2st 3ni on piisavalt kohti, et piletid saaks kõrvuti istuda.
     */
    public int[][] leiaJarjestKohad(int[][] saal, int soovitudKohad) {
        int rida = saal.length;
        int veerg = saal[0].length;
        for (int i = 0; i < rida; i++) {
            int count = 0;
            for (int j = 0; j < veerg; j++) {
                if (saal[i][j] == 0) { // tühjad kohad on tähistatud 0-ga
                    count++;
                    if (count == soovitudKohad) {
                        saal[i][j - soovitudKohad + 1] = 2; // kõrvuti olevate kohtade algus on märgistatud 2 ja lõpp 3
                        saal[i][j] = 3; // nt. kaks rida kus on 3 jarjestikku vaba kohta naeb valja: [1,0,1,1,2,0,3,1]
                        count = 0; // kui kohad on märgistatud alusta lugemist uuesti
                    }
                } else {
                    count = 0; // kui koht on võetud alusta lugemist uuesti
                }
            }
        }
        return saal;
    }

}

