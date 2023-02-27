import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Toys {
    private int id;
    private String name;
    private int weight;

    public Toys(int id, String name, int weight) { // Конструктор для создания объектов класса Toys
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    protected Toys() { // Защищенный конструктор для использования в наследуемых классах
    }

    public static List<Toys> getListToys(){ // Статический метод для создания списка игрушек
        List<Toys> list = new ArrayList<>();
        list.add(new SmallToys(1, "Зайчики", 30, 50));
        list.add(new MediumToys(2, "Мишки", 60, 25));
        list.add(new BigToys(3, "Слоны", 100, 10));
        return list;
    }

    public static List<Toys> geNewtListToys(int[] res, int[] findId){ // Статический метод для создания списка игрушек на основе данных, полученных от пользователя
        List<Toys> newToysList = new ArrayList<>();
        newToysList.add(new SmallToys(findId[0], "Зайчики", 30, res[0]));
        newToysList.add(new MediumToys(findId[1], "Мишки", 60, res[1]));
        newToysList.add(new BigToys(findId[2], "Слоны", 100, res[2]));
        return newToysList;
    }

    public int getId() {
        return id;
    }
    public static int[] findId(List<Toys> list){ // Статический метод для получения массива идентификаторов игрушек из списка
        int size = list.size();
        int[] findId = new int[size];
        int i = 0;
        for (Toys toy : list) {
            int id = toy.getId(); // получаем id каждого объекта
            findId[i]=id;
            i++;

        }
        return findId;
    }
    /*
    Статический метод для вывода списка игрушек на экран
    */
    public static void showList(List<Toys> list, String msq){
        System.out.println(msq); // Выводим переданное сообщение
        for (Toys toy : list) { // Проходимся по списку игрушек и выводим каждый объект на экран
            System.out.println(toy);
        }
    }
    /*
    метод получения количества игрушек по категориям
     */
    public static int[] findNumberToys(int[] arr, List<Toys> list) throws IOException {
        Arrays.sort(arr); // Сортируем массив arr
        int size = list.size();
        int[] res = new int[size]; // Создаем массив res размером со списком игрушек, чтобы хранить количество каждой игрушки
        int count =1; // Счетчик для подсчета количества игрушек
        int index = 0; // Индекс для заполнения массива res
        int el = arr[0]; // Инициализируем переменную el первым элементом массива arr
        for (int i =1; i<= arr.length-1; i++) { // Проходимся по массиву arr, начиная со второго элемента
            if (el != arr[i]) { // Если текущий элемент не равен предыдущему
                res[index] = count; // Записываем количество предыдущей игрушки в массив res
                count = 1; // Сбрасываем счетчик
                index++; // Увеличиваем индекс для записи в массив res
                el = arr[i]; // Обновляем значение переменной el
            } else { // Если текущий элемент равен предыдущему
                count++; // Увеличиваем счетчик
            }
            if (i == arr.length - 1) { // Если дошли до последнего элемента массива arr
                res[index] = count; // Записываем количество последней игрушки в массив res
            }
        }
        return res;  // Возвращаем массив с количеством каждой игрушки
    }
    /*
    Метод для подсчета остатков игрушек в магазине
    Принимает список игрушек, массив с количеством каждого типа игрушек в заказе и массив с id типов игрушек
    Возвращает массив с остатками каждого типа игрушек в магазине
    */
    public static int[] totalBalance(List<Toys>list, int [] numberToys, int[]findId){
        int [] res = new int[3]; // массив для хранения остатков каждого типа игрушек

        SmallToys restSmallToys = new SmallToys(); // создание объекта класса SmallToys для подсчета остатков маленьких игрушек
        int res1 = restSmallToys.getRest(list,numberToys,findId[0]); // получение остатка маленьких игрушек
        res[0] = res1; // сохранение остатка маленьких игрушек в массив

        MediumToys mediumToys = new MediumToys(); // создание объекта класса MediumToys для подсчета остатков средних игрушек
        int res2 = mediumToys.getRest(list,numberToys,findId[1]);  // получение остатков средних игрушек
        res[1] = res2; // сохранение остатков средних игрушек в массив

        BigToys bigToys = new BigToys(); // создание объекта класса BigToys для подсчета остатков больших игрушек
        int res3 = bigToys.getRest(list,numberToys,findId[2]); // получение остатков больших игрушек
        res[2] = res3; // сохранение остатков больших игрушек в массив
        return res; // возвращение массива с остатками каждого типа игрушек
    }
    /*
    метод записи в файл призовых игрушек
     */
    public static void writerFile(int[] arr) throws IOException {
        File file = getFile(); // получение объекта файла для записи
        FileOutputStream fos = new FileOutputStream(file); // создание объекта для записи в файл
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8); // создание объекта для записи в файл с указанием кодировки
        BufferedWriter writer = new BufferedWriter(osw); // создание объекта для буферизованной записи в файл

        for(int i= 0 ; i<arr.length; i++) { // проход по массиву с выданными призами
            switch (arr[i]) {
                case 1:
                    writer.write("Выдача приза: Зайчик!\n"); // запись в файл приза "Зайчик"
                    break;
                case 2:
                    writer.write("Выдача приза: Мишка!\n"); // запись в файл приза "Мишка"
                    break;
                case 3:
                    writer.write( "Выдача приза: Слон!\n"); // запись в файл приза "Слон"
                    break;
            }
        }
        writer.close(); // закрытие объекта для записи в файл
    }
    /*
    метод создания файла
    */
    public static File getFile(){
        File file = new File("prizeToys.txt"); // создание объекта файла
        try {
            boolean result = file.createNewFile(); // попытка создать файл
            if (result) {
                System.out.println("Файл успешно создан"); // если файл успешно создан, выводим сообщение
            } else {
                System.out.println("Файл уже существует"); // если файл уже существует, выводим сообщение
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage()); // если возникла ошибка при создании файла, выводим сообщение об ошибке
        }
        return file; // возвращаем объект файла
    }

    @Override
    public String toString() {
        return "id:" + id + " Название:" + name + " Вес:"+ weight + "гр."; // возвращаем строковое представление объекта игрушки
    }

    public String toString(int id){
        return name + "\n"; // возвращаем название игрушки по её идентификатору
    }
}
