package org.example;

import java.util.*;


public class HomeWork {
    public static final int CAPACITY = 1 << 4;

    /**
     * <h1>Задание 1.</h1>
     * Требуется реализовать интерфейс TicketManager в соответствии с JavaDoc описанием.
     * Реализации очередей из стандартной библиотеки не используем.
     */
    public TicketManager managerFabric() {
        return new TicketManagerImpl(CAPACITY);
    }


    /**
     * Задача со звездочкой (опционально)
     * <br/>
     * На вход строки:
     *  номер_впереди : номер_сзади
     * Если впереди или сзади никого нет - то 0, для первого и последнего в очереди.
     * На выход нужно восстановить порядок номеров в очереди.
     *
     * В тестах генератор тестовых данных (очереди и пары).
     * @see <a href="https://codeforces.com/problemset/problem/490/B?locale=ru">https://codeforces.com/problemset/problem/490/B?locale=ru</a>
     */
    public List<Integer> check(List<String> records){
        return null;
    }

}
