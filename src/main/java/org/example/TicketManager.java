package org.example;

/**
 * Обработчик талонов
 */
public interface TicketManager {

    /**
     * Регистрация талона в очереди
     *
     * @param ticket
     */
    void add(Ticket ticket);

    /**
     * Получение следующего талона или null если очередь пуста
     * В первую очередь идут талоны с Ticket.type = "pension", далее все остальные.
     * Внутри групп ("pension" или остальные) упорядочиваем по времени регистрации Ticket.registerTime по возрастанию (FIFO)
     *
     * @return
     */
    Ticket next();

    String print();
}
