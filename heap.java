import java.io.*;

class Ref {
    private int iData;

    public Ref(int key) {
        iData = key;
    }

    public int getKey() {
        return iData;
    }

    public void setKey(int id) {
        iData = id;
    }
}

class Heap {
    private Ref[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap(int mx) {
        maxSize = mx;
        currentSize = 0;
        heapArray = new Ref[maxSize];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean insert(int key) {
        if (currentSize == maxSize) {
            return false;
        }

        Ref newNode = new Ref(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }

    public void trickleUp(int index) {
        int parent = (index - 1) / 2;
        Ref bottom = heapArray[index];

        while (index > 0 && heapArray[parent].getKey() < bottom.getKey()) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    public Ref remove() {
        Ref root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    public void trickleDown(int index) {
        int largerChild;
        Ref top = heapArray[index]; // Сохранение корня
        while (index < currentSize / 2) // Пока у узла имеется
        { // хотя бы один потомок
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            // Определение большего потомка
            if (rightChild < currentSize && // (Правый потомок существует?)
                    heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
                largerChild = rightChild;
            else
                largerChild = leftChild;
            // top >= largerChild?
            if (top.getKey() >= heapArray[largerChild].getKey())
                break;
            // Потомок сдвигается вверх
            heapArray[index] = heapArray[largerChild];
            index = largerChild; // Переход вниз
        }
        heapArray[index] = top; // index <- корень
    }

    public boolean change(int index, int newValue) {
        if (index < 0 || index > currentSize) {
            return false;
        }

        int oldValue = heapArray[index].getKey();
        heapArray[index].setKey(newValue);

        if (oldValue < newValue) {
            trickleUp(index);
        } else {
            trickleDown(index);
        }

        return true;
    }

    public void displayHeap() {
        System.out.print("heapArray: "); // Формат массива
        for (int m = 0; m < currentSize; m++)
            if (heapArray[m] != null)
                System.out.print(heapArray[m].getKey() + " ");
            else
                System.out.print("-- ");
        System.out.println();
        // Формат пирамиды
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0; // Текущий элемент
        String dots = "...............................";
        System.out.println(dots + dots); // Верхний пунктир
        while (currentSize > 0) // Для каждого элемента пирамиды
        {
            if (column == 0) // Первый элемент в строке?
                for (int k = 0; k < nBlanks; k++) // Предшествующие пробелы
                    System.out.print(' ');
            // Вывод элемента
            System.out.print(heapArray[j].getKey());
            if (++j == currentSize) // Вывод завершен?
                break;
            if (++column == itemsPerRow) // Конец строки?
            {
                nBlanks /= 2; // Половина пробелов
                itemsPerRow *= 2; // Вдвое больше элементов
                column = 0; // Начать заново
                System.out.println(); // Переход на новую строку
            } else // Следующий элемент в строке
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(' '); // Внутренние пробелы
        }
        System.out.println("\n" + dots + dots); // Нижний пунктир
    }
}

class HeapApp {
    public static void main(String[] args) throws IOException {
        int value, value2;
        Heap theHeap = new Heap(31);
        boolean success;

        theHeap.insert(70);
        theHeap.insert(40);
        theHeap.insert(50);
        theHeap.insert(20);
        theHeap.insert(60);
        theHeap.insert(100);
        theHeap.insert(80);
        theHeap.insert(30);
        theHeap.insert(10);
        theHeap.insert(90);

        while (true) {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, remove, change: ");
            int choice = getChar();
            switch (choice) {
                case 's':
                    theHeap.displayHeap();
                    break;
                case 'i':
                    System.out.print("Enter value of insert: ");
                    value = getInt();
                    success = theHeap.insert(value);
                    if (!success) {
                        System.out.println("Can't insert: help full");
                    }
                    break;

                case 'r':
                    if (!theHeap.isEmpty()) {
                        theHeap.remove();
                    } else {
                        System.out.println("Can't remove: heap empty");
                    }
                    break;

                case 'c': // Изменение приоритета
                    System.out.print("Enter current index of item: ");
                    value = getInt();
                    System.out.print("Enter new key: ");
                    value2 = getInt();
                    success = theHeap.change(value, value2);
                    if (!success)
                        System.out.println("Invalid index");
                    break;
                default:
                    System.out.println("Invalid entry\n");

            }
        }
    }

    // -------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    // -------------------------------------------------------------
    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    // -------------------------------------------------------------
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}