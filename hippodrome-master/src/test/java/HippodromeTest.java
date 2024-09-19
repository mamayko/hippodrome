import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void constructor_NullListHorse_ThrowsIllegalArgumentException(){
        Throwable exep = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exep.getMessage());
    }

    @Test
    public void constructor_EmptyListHorse_ThrowsIllegalArgumentException(){
        List<Horse> horses = new ArrayList<>();
        Throwable exep = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be empty.", exep.getMessage());
    }

    @Test
    void getHorses_ReturnCorrectHorsesList() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i , 1, 1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertNotNull(hippodrome.getHorses());
        assertEquals(30, hippodrome.getHorses().size());
        assertEquals("Horse5", hippodrome.getHorses().get(5).getName());
        assertEquals(horses, hippodrome.getHorses());
    }




    @Test
    void move_CallsMoveMethodWithAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse hors: horses){
            Mockito.verify(hors, Mockito.times(1)).move();
        }



    }



    @Test
    void getWinner_ReturnCorrectWinner() {
        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("name1", 2,5),
                new Horse("name2", 2,8),
                new Horse("name3", 2,15)
        ));
        assertEquals("name3", hippodrome.getWinner().getName());
    }
}