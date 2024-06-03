import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class User {
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return this.hashCode() == user.hashCode();
    }

    @Override
    public int hashCode() {
        int result = 31 * this.id + this.name.toLowerCase().hashCode();
        return result;
    }
}

class PhoneBook {
    public static HashMap<User, ArrayList<Integer>> phoneBook = new HashMap<>();

    public void addPhone(User newUser, int phoneNumber) {
        for (User user : phoneBook.keySet()) {
            if (user.equals(newUser)) {
                phoneBook.get(user).add(phoneNumber);
                return;
            }
        }
        ArrayList<Integer> arrayPhoneNumbers = new ArrayList<>();
        arrayPhoneNumbers.add(phoneNumber);
        phoneBook.put(newUser, arrayPhoneNumbers);
    }

    public void deletePhone(User user, int deletePhoneNumber) {
        for (User userKey : phoneBook.keySet()) {
            if (userKey.equals(user) && phoneBook.get(userKey).contains(deletePhoneNumber)) {
                phoneBook.get(userKey).remove(Integer.valueOf(deletePhoneNumber));
                if (phoneBook.get(userKey).isEmpty()) {
                    phoneBook.remove(userKey);
                }
                break;
            }
        }
    }

    public void deleteUser(User userDelete) {
        for (User user : phoneBook.keySet()) {
            if (user.equals(userDelete)) {
                phoneBook.remove(user);
                break;
            }
        }
    }

    public void print() {
        ArrayList<Map.Entry<User, ArrayList<Integer>>> entrySort = new ArrayList<>(phoneBook.entrySet());
        entrySort.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));
        for (Map.Entry<User,ArrayList<Integer>> entry : entrySort) {
            System.out.println("User: " + entry.getKey().name + ", Phones: " + entry.getValue());
        }
    }
}

public class Main {
    public static void main(String[] args) {

        //Add users
        User user1 = new User(1, "oleg");
        User user2 = new User(2, "lena");
        User user3 = new User(3, "dima");
        User user4 = new User(4, "artur");
        User user5 = new User(1, "oleg");
        User user6 = new User(1, "oleg");

        //Create phonebook
        PhoneBook myPhoneBook = new PhoneBook();
        myPhoneBook.addPhone(user1, 11111);
        myPhoneBook.addPhone(user2, 22222);
        myPhoneBook.addPhone(user2, 77777);
        myPhoneBook.addPhone(user3, 33333);
        myPhoneBook.addPhone(user4, 44444);
        myPhoneBook.addPhone(user5, 55555);
        myPhoneBook.addPhone(user6, 66666);

        System.out.println("Before deletion: ");
        myPhoneBook.print();

        //delete user
        myPhoneBook.deleteUser(user4);

        //delete phone
        myPhoneBook.deletePhone(user2, 22222);
        myPhoneBook.deletePhone(user2, 77777);

        System.out.println("After deletion: ");
        myPhoneBook.print();
    }
}