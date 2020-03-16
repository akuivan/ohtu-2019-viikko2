package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiLaittaaLiikaaTavaraa() {
        varasto.lisaaVarastoon(15);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);

        varasto.otaVarastosta(3);
//      varaston saldo nyt 10-3=7
        varasto.lisaaVarastoon(3);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaEiVoiOttaaLiikaaTavaraa() {
        varasto.otaVarastosta(15);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);

        varasto.lisaaVarastoon(6);
//      varaston saldo nyt 6
        varasto.otaVarastosta(8);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiVoiOttaaNegatiivistaTavaraa() {
        varasto.otaVarastosta(-10);
        
        assertEquals(0, varasto.otaVarastosta(-10), vertailuTarkkuus);
    }
    

    @Test
    public void varastoonEiVoiLaittaaNegatiivistaTavaraa() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-5);

        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

//    @Test
//    public void paljonMahtuuPalauttaaOikeanArvon(){
//        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
//    }
    @Test
    public void tulostetaanMerkkijonoOikein() {
        varasto.lisaaVarastoon(4);
         
        assertEquals("saldo = 4.0, vielä tilaa 6.0", varasto.toString());
    }
//eka konstruktori
    @Test
    public void konstruktorinAsettamatTilavuusEiNegatiivinen() {
        varasto = new Varasto(-5);

        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktorinAsettamatSaldoEiNegatiivinen() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
//toka konstruktori
    @Test
    public void toisenKonstruktorinAsettamatTilavuusEiNegatiivinen() {
        varasto = new Varasto(-5, -15);

        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void toisenKonstruktorinAsettamatSaldoEiNegatiivinen() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toisenKonstruktorinToimintaKunSaldoOnSuurempiKuinTilavuus() {
        varasto = new Varasto(5, 15);
        
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    
    
    @After
    public void setUpToinenKonstruktori() {
        varasto = new Varasto(10, 5);
    }

    @After
    public void toinenKonstruktoriLuoOikeanSaldollisenVaraston() {
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @After
    public void toinenKonstruktoriLuoVarastolleOikeanTilavuuden() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }


}
