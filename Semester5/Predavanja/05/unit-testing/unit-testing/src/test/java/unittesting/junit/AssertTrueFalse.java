package unittesting.junit;

import org.junit.Test;

import unittesting.junit.ExampleUnit;
// ovo morate sami da importujete - Eclipse to ne ume sam
import static org.junit.Assert.*;

// da je ovo nas konkretan unit koji testiramo ovo bi znacilo da ne testiramo
// dobru metodu - nismo dobro granulirali testove
// Ovaj primer sluzi da shvatimo da treba pokretati testove vise puta

public class AssertTrueFalse {
	
	ExampleUnit myUnit = new ExampleUnit();

    @Test
    public void testGetTheBooleanTrue() {
        assertTrue(myUnit.getTheBoolean());

    }
    
    @Test
    public void testGetTheBooleanFalse() {
        assertFalse(myUnit.getTheBoolean());
    }
    
}
