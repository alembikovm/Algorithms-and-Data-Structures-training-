class LowArray {
    private long[] a; // Ссылка на массив а

    public LowArray(int size) { // Конструктор
        a = new long[size]; // Создание массива;
    }

    public void setElem(int index, long value) { //запись элемента
        a[index] = value;
    }

    public long getElem(int index) {  //Чтение элемента
        return a[index];
    }
}

class LowArrayApp {
    public static void main(String[] args) {
        LowArray arr; // Ссылка
        arr = new LowArray(100); //Создание массива
        int nElems = 0; // Количество элементов в массиве
        int j; //Переменная цикла

        arr.setElem(0, 77); //Вставка 10 элементов
        arr.setElem(1, 99);
        arr.setElem(2, 44);
        arr.setElem(3, 55);
        arr.setElem(4, 22);
        arr.setElem(5, 88);
        arr.setElem(6, 11);
        arr.setElem(7, 00);
        arr.setElem(8, 66);
        arr.setElem(9, 33);
        nElems = 10; //Массив содержит 10 элементов

        for (j = 0; j < nElems; j++) { //Вывод элементов для каждого элемента
            System.out.println(arr.getElem(j) + " ");
        }
        System.out.println("");

        int searchKey = 26; //Поиск элемента
        for (j = 0; j < nElems; j++) {
            if (arr.getElem(j) == searchKey) { //Значение не найдено?
                break;
            }
        }

        if (j == nElems) { //нет
            System.out.println("Can't find " + searchKey);
        } else { // да
            System.out.println("Found " + searchKey);
        }

        // Удаление элемента с номером 55
        for (j = 0; j < nElems; j++){ 
        if (arr.getElem(j) == 55) //Поиск удаляемого элемента
            break;
        }
        
        for (int k = j; k < nElems; k++) {    //Сдвиг последних элементов
            arr.setElem(k, arr.getElem(k + 1));
        }

        nElems--;   //уменьшение длинны массива

        for (j = 0; j < nElems; j++) {  //вывод массива на экран
            System.out.println(arr.getElem(j) + " ");
        }

        System.out.println("");

    }
}
