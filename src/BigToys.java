import java.util.List;

public class BigToys extends Toys{ // объявляем класс "BigToys", который является подклассом класса "Toys"
    private int volume; // объявляем дополнительное поле "volume" для игрушки

    public BigToys(){ // конструктор без параметров

    }
    public BigToys(int id, String name, int weight, int volume) { // конструктор с параметрами
        super(id, name, weight); // вызываем конструктор суперкласса "Toys" с помощью ключевого слова "super"
        this.volume = volume; // устанавливаем значение поля "volume" для игрушки
    }

    public int getVolume() { // метод для получения значения поля "volume"
        return volume;
    }

    public void setVolume(int volume) { // метод для установки значения поля "volume"
        this.volume = volume;
    }

    @Override
    public String toString() { // переопределяем метод "toString"
        return super.toString() + " " + volume + " шт."; // выводим информацию о игрушке, включая ее объем
    }

    public int getRest(List<Toys> list, int[] res, int id) { // метод для определения количества оставшихся игрушек из списка
        int rest = 0; // объявляем переменную для хранения количества оставшихся игрушек
        for (Toys toy : list) { // перебираем все игрушки в списке
            if (toy instanceof BigToys && toy.getId() == id) { // если игрушка является экземпляром класса "BigToys" и имеет нужный ID
                BigToys bigToys = (BigToys) toy; // создаем новый объект "BigToys" и присваиваем ему значение текущей игрушки
                rest = bigToys.getVolume() - res[id - 1]; // вычисляем количество оставшихся игрушек и сохраняем в переменной "rest"
                bigToys.setVolume(rest); // обновляем значение объема игрушки с помощью метода "setVolume"
                break; // выходим из цикла, когда найден нужный объект
            }
        }
        return rest; // возвращаем количество оставшихся игрушек
    }
}
