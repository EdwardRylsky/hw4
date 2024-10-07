package org.example;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void testManagerFabric() {
        TicketManager ticketManager = homeWork.managerFabric();

        OffsetDateTime now = OffsetDateTime.now();

        Ticket common0 = new Ticket("common0").withRegisterTime(now.minusMinutes(0));
        Ticket common1 = new Ticket("common1").withRegisterTime(now.minusMinutes(1));
        Ticket common2 = new Ticket("common2").withRegisterTime(now.minusMinutes(2));
        Ticket common3 = new Ticket("common3").withRegisterTime(now.minusMinutes(3));
        Ticket common4 = new Ticket("common4").withRegisterTime(now.minusMinutes(4));
        Ticket common5 = new Ticket("common5").withRegisterTime(now.minusMinutes(5));
        Ticket common6 = new Ticket("common6").withRegisterTime(now.minusMinutes(6));
        Ticket common7 = new Ticket("common7").withRegisterTime(now.minusMinutes(7));
        Ticket common8 = new Ticket("common8").withRegisterTime(now.minusMinutes(8));
        Ticket common9 = new Ticket("common9").withRegisterTime(now.minusMinutes(9));

        ticketManager.add(common7);
        ticketManager.add(common0);
        ticketManager.add(common9);
        ticketManager.add(common2);
        ticketManager.add(common4);
        ticketManager.add(common6);
        ticketManager.add(common5);
        ticketManager.add(common3);
        ticketManager.add(common1);
        ticketManager.add(common8);

        Ticket pension0 = new Ticket("pension").withRegisterTime(now.minusMinutes(0));
        Ticket pension1 = new Ticket("pension").withRegisterTime(now.minusMinutes(1));
        Ticket pension2 = new Ticket("pension").withRegisterTime(now.minusMinutes(2));
        Ticket pension3 = new Ticket("pension").withRegisterTime(now.minusMinutes(3));
        Ticket pension4 = new Ticket("pension").withRegisterTime(now.minusMinutes(4));
        Ticket pension5 = new Ticket("pension").withRegisterTime(now.minusMinutes(5));

        ticketManager.add(pension0);
        ticketManager.add(pension5);
        ticketManager.add(pension3);
        ticketManager.add(pension2);
        ticketManager.add(pension4);
        ticketManager.add(pension1);

        assertEquals(pension5, ticketManager.next());
        assertEquals(pension4, ticketManager.next());
        assertEquals(pension3, ticketManager.next());
        assertEquals(pension2, ticketManager.next());
        assertEquals(pension1, ticketManager.next());
        assertEquals(pension0, ticketManager.next());

        assertEquals(common9, ticketManager.next());
        assertEquals(common8, ticketManager.next());
        assertEquals(common7, ticketManager.next());
        assertEquals(common6, ticketManager.next());
        assertEquals(common5, ticketManager.next());
        assertEquals(common4, ticketManager.next());
        assertEquals(common3, ticketManager.next());
        assertEquals(common2, ticketManager.next());
        assertEquals(common1, ticketManager.next());
        assertEquals(common0, ticketManager.next());

        assertNull(ticketManager.next());
    }
    @Test
    void check() {
        List<Integer> expectedQueue = generateQueue(1, 4);
        List<String> pairs = generatePairs(expectedQueue);
        assertEquals(expectedQueue, homeWork.check(pairs));
    }

    private List<String> generatePairs(List<Integer> expectedQueue) {
        List<String> pairs = new ArrayList<>();
        Function<Integer, Integer> map = (x) -> (x < 0 || x >= expectedQueue.size()) ? 0 : expectedQueue.get(x);

        for (int i = 0;
             i < expectedQueue.size(); i++) {
            pairs.add(String.format("%d:%d", map.apply(i - 1), map.apply(i + 1)));
        }
        Collections.shuffle(pairs);
        return pairs;
    }

    private List<Integer> generateQueue(int seed, int length) {
        return new Random(seed)
                .ints(1, length * 100)
                .limit(length)
                .boxed()
                .collect(Collectors.toList());
    }
}