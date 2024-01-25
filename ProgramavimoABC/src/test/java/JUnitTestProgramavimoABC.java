import org.example.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitTestProgramavimoABC {

    @BeforeEach
    public void TestSetUp() {
        Main.setUp();
    }

    @AfterEach
    public void TestCloseBrowser() { Main.closeBrowser();}

    @Test
    public void testSendFilmPsitive() {
        Main.sendFilm(Main.PAVADINIMAS, Main.ZANRAS, Main.AKTORIAI, Main.REZISIERIUS, Main.TRUKME);
        boolean actual = Main.checkSendData("Duomenys įrašyti sėkmingai");
        Assertions.assertTrue(actual, "Duomenys įrašyti sėkmingai");
    }
    @Test
    public void testSendFilmNegatyve() {
        Main.sendFilm("", Main.ZANRAS, Main.AKTORIAI, Main.REZISIERIUS, Main.TRUKME);
        boolean actual = Main.checkSendData("Duomenys įrašyti sėkmingai");
        Assertions.assertFalse(actual, " Duomenų įvedimo klaida ");
    }
    @Test
    public void testDeleteFilm() {
        Main.deleteFilm("54");
        boolean actual = Main.checkSendData("Įrašas ištrintas sėkmingai");
        Assertions.assertTrue(actual, "Įrašas ištrintas sėkmingai");
    }
    @Test
    public void testUpdateFilmPositive() {
        Main.updateFilm("55", "Matrix", "Action", "Actor2", "Director", "208");
        boolean actual = Main.checkSendData("Įrašas paredaguotas sėkmingai");
        Assertions.assertTrue(actual, "Įrašas paredaguotas sėkmingai");
    }
    @Test
    public void testUpdateFilmNegative() {
        Main.updateFilm("", "", "Comedy", "UpdatedActor", "UpdatedDirector", "200");
        boolean actual = Main.checkSendData("Įrašas paredaguotas sėkmingai");
        Assertions.assertFalse(actual, " Blogai įvesti duomenys redaguojant įrašą ");
    }

}
