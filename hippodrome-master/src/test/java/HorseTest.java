import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @Test
    public void constructor_NullNameParam_ThrowsIllegalArgumentException(){
        Throwable exep = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1,1));
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1,1));
        assertEquals("Name cannot be null.", exep.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ","  ", "\n", "\t\t", "\n\n"})
    void constructor_EmptyNameParam_ThrowsIllegalArgumentException(String arg){
        Throwable exep = assertThrows(IllegalArgumentException.class, () -> new Horse(arg, 1,1));
        assertThrows(IllegalArgumentException.class, () -> new Horse(arg, 1,1));
        assertEquals( "Name cannot be blank.", exep.getMessage());
    }

    @Test
    public void constructor_NegativeSpeed_ThrowsIllegalArgumentException(){
        Throwable exep = assertThrows(IllegalArgumentException.class, () -> new Horse("someName",-1,1));
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1,1));
        assertEquals("Speed cannot be negative.", exep.getMessage());
    }
    @Test
    public void constructor_NegativeDistance_ThrowsIllegalArgumentException(){
        Throwable exep = assertThrows(IllegalArgumentException.class, () -> new Horse("someName", 1,-1));
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1,1));
        assertEquals("Distance cannot be negative.", exep.getMessage());
    }

    @Test
    void getName_ReturnCorrectName() {
        Horse horse = new Horse("someName", 1,1);
        assertEquals("someName", horse.getName());

    }

    @Test
    void getSpeed_ReturnCorrectSpeed() {
        Horse horse = new Horse("someName", 1,1);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    void getDistance_ReturnCorrectDistance_TripleConstructor() {
        Horse horse = new Horse("someName", 1,1);
        assertEquals(1, horse.getDistance());
    }
    @Test
    void getDistance_ReturnCorrectDistance_DoubleConstructor() {
        Horse horse = new Horse("someName", 1);
        assertEquals(0, horse.getDistance());
    }
    @Test
    void move_GetRandomDoubleUseCorrectDouble() {
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            Horse horse = new Horse("someName", 1,2 );
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.6})
    void move_CorrectCalculateDistance(double someRandomNumber){

        Horse horse = new Horse("someName", 1, 2);
        double expectedDistance = 2 + 1 * someRandomNumber;
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){

            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(someRandomNumber);
            horse.move();
            assertEquals(expectedDistance, horse.getDistance());
        }
    }


}