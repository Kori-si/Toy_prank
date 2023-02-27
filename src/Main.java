import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Toys> toys = Toys.getListToys(); // Получение списка игрушек из файла
        Toys.showList(toys, "Список игрушек"); // Вывод на экран списка игрушек

        try {
            int[] resultQuiz = Lottery.startQuiz(toys); // Активация игры и получение общего списка игрушек, участвующих в викторине
            int [] numberToys = Toys.findNumberToys(resultQuiz,toys); // Получение общего количества игрушек каждой категории после сортировки
            int[] findId = Toys.findId(toys); // Нахождение всех ID игрушек в списке
            int [] totalBalance = Toys.totalBalance(toys,numberToys,findId); // Получение общего остатка игрушек каждой категории
            List<Toys> newToysList = Toys.geNewtListToys(totalBalance,findId); // Создание нового списка игрушек с новыми значениями по остаткам
            toys = newToysList; // Перезапись старого списка игрушек на новый список с новыми значениями
            Toys.showList(toys,"Список с остатками игрушек"); // Вывод на экран нового списка игрушек с остатками
        }
        catch (Exception e) {
            System.out.println("Попробуйте ещё раз! " + e.getMessage()); // Вывод сообщения об ошибке, если что-то пошло не так
        }
    }
}
