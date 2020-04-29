class Person {
    private String lastName;
    private String firstName;
    private int age;

    public Person(String last, String first, int a) {
        lastName = last;
        firstName = first;
        age = a;
    }

    public void displayPerson() {
        System.out.println(" Last name: " + lastName);
        System.out.println(" First name: " + firstName);
        System.out.println(" Age: " + age);
    }

    public String getLast() {
        return lastName;
    }
}

class ClassDataArray {
    private Person[] a;
    private int nElems;

    public ClassDataArray(int max) {
        a = new Person[max];
        nElems = 0;
    }

    public Person find(String searchName) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (a[j].getLast().equals(searchName)) {
                break;
            }
        }

        if (j == nElems)
            return null;
        else
            return a[j];
    }

    public void insert(String last, String first, int age) {
        a[nElems] = new Person(last, first, age);
        nElems++;
    }

    public boolean delete(String searchName) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (a[j].getLast().equals(searchName)) {
                break;
            }
        }

        if (j == nElems) {
            return false;
        } else {
            for (int k = j; k < nElems; k++) {
                a[k] = a[k + 1];
            }

            nElems--;
            return true;
        }
    }

    public void displayA() {
        for (int j = 0; j < nElems; j++) {
            a[j].displayPerson();
            System.out.println("");
        }
    }

}

class ClassDataApp {

    public static void main(String[] args) {
        int maxSize = 100;
        ClassDataArray arr;
        arr = new ClassDataArray(maxSize);

        arr.insert("Evans", "Patty", 24);
        arr.insert("Marat", "Alembikov", 30);
        arr.insert("Olga", "Alembikova", 30);
        arr.insert("Artem", "Alembikov", 2);
        arr.insert("Smith", "Loriane", 37);
        arr.insert("Adams", "Henry", 31);
        arr.insert("Hashito", "Sato", 21);
        arr.insert("Velaskes", "Joe", 72);
        arr.insert("Vanh", "Mirth", 44);
        arr.insert("Sony", "Playstation", 4);

        arr.displayA();

        String searchKey = "Velaskes";
        Person found;
        found = arr.find(searchKey);

        if (found != null) {
            System.out.println("Found ");
            found.displayPerson();
        } else {
            System.out.println("Can't find " + searchKey);
        }

        System.out.println("Deleting Marat, Olga, Artem");

        arr.delete("Marat");
        arr.delete("Olga");
        arr.delete("Artem");

        arr.displayA();
    }
}