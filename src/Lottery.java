import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Lottery {
    /*
    метод генерации случайных чисел с % соотношением
     */
    public static int generateNumber() {
        Random random = new Random();
        int number = random.nextInt(100);

        if (number < 60) {
            return 1;
        } else if (number < 90) {
            return 2;
        } else {
            return 3;
        }
    }
    /*
    метод получения массива подарков для раздачи в лотерее
    */
    public static int[] getArrayGifts(List<Toys> list, int size){ // Метод, который генерирует случайный массив чисел-призов заданного размера из списка игрушек
        int [] prizes = new int[size];
        for(int i = 0;i<prizes.length;i++)
        {
            prizes[i] = generateNumber();
        }
        return prizes;
    }
    public static int[] startQuiz(List<Toys>list) throws IOException { // Метод, который запрашивает у пользователя желание начать лотерею, а затем проводит игру
        Scanner scanner = new Scanner(System.in);
        System.out.println("Хотите начать лотерею? Введите 'да' или 'нет'");
        String choose = scanner.next().toLowerCase();

        final String YES = "да";
        final String NO = "нет";
        int [] result = new int[0];
        // реализаци игры
        if (choose.equals(YES)) {
            result = getArrayGifts(list, 10); // генерируем случайный массив чисел-призов
            Lottery.distributionGifts(result); // распределяем призы по победителям
            Toys.writerFile(result); // записываем результаты в файл
            int[] findId = Toys.findId(list); // ищем победителей в списке игрушек
        }
        else if (choose.equals(NO)) {
            System.out.println("До встречи!");
        }
        else {
            System.out.println("Некорректный ввод, попробуйте ещё раз");
        }
        scanner.close();
        return result;
    }

    /*
    метод раздачи подарков для каждого игрока
    */
    public static void distributionGifts(int[] array) {
        for(int i = 0;i < array.length;i++){
            System.out.printf("Игрок %d!", (i+1)); // выводим номер игрока
            switch (array[i]) { // проверяем, какой приз получил игрок
                case 1:
                    System.out.println("Поздравляем, вы выиграли Зайчика!");
                    break;
                case 2:
                    System.out.println("Поздравляем, вы выиграли Мишку!");
                    break;
                case 3:
                    System.out.println("Поздравляем, вы выиграли Слона!");
                    break;
            }
        }
    }
}