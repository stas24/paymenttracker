# Алгоритм работы программы
Для преобразования строки в объект Payment используется класс PaymentTransformer.

После запуска программы для хранения сумм всех валют создан объект класса PersistenceManager - persistenceManager.

Если при запуске передан аргумент с именем файла, то происходит чтение файла и построчная обработка с помощью объекта класса PaymentTransformer с последующим сохранением в persistenceManager. В случае возникновения ошибки формата будет вывдено соответствующее сообщение и чтение файло будет продолжено.

Затем происходит запуск таймера с заданием, в котором ежеминутно выводится содержимое persistenceManager в консоль.

В бесконечном цикле организовано чтение строк из консоли. Выход из цикла происходит если введена строка "quit". С помощью paymentTransformer введенная строка преобразуется в объект Payment и сохраняется в persistenceManager. В случае неправильного ввода будет выведено сообщение "Wrong format!". После выхода из цикла while останавливается таймер.

# PersistenceManager
PersistenceManager хранит итоговую сумму по каждой валюте в поле summary. При сохранении нового платежа Payment происходит проверка, чтоб сумма платежа не была нулем. Метод persist(InputStream is) позволяет прочитать и добавить новые платежи из потока (используется для чтения из файла). Метод out(PrintStream stream) выводит все валюты с суммами без записей с нулевой суммой.

# Пример запуска программы с аргументом
>java -jar paymenttracker-1.0-SNAPSHOT.jar "D:\payments.txt"

# Пример файла payments.txt
>USD 100

>RUB -3000

>GRN 5

>RUB 2000