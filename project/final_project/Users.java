package demo2;

import java.io.*;
import java.util.HashSet;

final class Users {
    private static HashSet<User> users;

    public static void main(String[] args) {
        users = new HashSet<>();
        save();
    }

    static class User implements Serializable {
        private static final long serialVersionUID = 1;
        private static final byte[] fibonacci = {1, 2, 3, 5, 8, 13, 21, 34, 55, 89};
        private String name, password;

        private User(String name, String password) {
            this.name = name;
            this.password = encryption(password);
        }

        private static String encryption(String clearText) {
            StringBuilder cipherText = new StringBuilder(clearText.length() << 3);
            for (int i = 0, j = clearText.length(); i < j; i++) cipherText.append(code(clearText.charAt(i)));
            String coded = cipherText.toString();
            cipherText.delete(0, cipherText.length());
            for (int i = coded.length(); i >= 16; i -= 16)
                cipherText.append((char) (int) Integer.valueOf(coded.substring(i - 16, i), 2));
            if (coded.length() % 16 != 0)
                cipherText.append((char) (int) Integer.valueOf(coded.substring(0, coded.length() % 16), 2));
            return cipherText.toString();
        }

        private static String code(char c) {
            boolean start = false;
            StringBuilder coded = new StringBuilder();
            for (int i = fibonacci.length - 1; i >= 0; c %= fibonacci[i], i--) {
                if (start) coded.append(c / fibonacci[i]);
                else if (c >= fibonacci[i]) {
                    coded.append(11);
                    start = true;
                }
            }
            return coded.toString();
        }

        String getName() {
            return name;
        }

        private String getPassword() {
            return password;
        }

        public String toString() {
            return name + " " + password;
        }

        public boolean equals(Object object) {
            if (object instanceof User) {
                User user = (User) object;
                return name.equals(user.name);
            } else return false;
        }


        public int hashCode() {
            return name.hashCode();
        }
    }

    public static boolean checkPassword(String password) {
        return password.matches("[A-Za-z0-9@#&$^=+%.?!]{3,18}");
    }

    public static boolean checkName(String name) {
        return name.matches("[A-Za-z0-9]{1,12}");
    }

    static boolean login(String name, String password) {
        load();
        return users.stream().anyMatch(user -> user.getName().equals(name) && user.getPassword().equals(User.encryption(password)));
    }

    static boolean register(String name, String password) {
        load();
        if (users.add(new User(name, password))) {
            save();
            return true;
        } else return false;
    }

    private static void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/demo2/users.dat"));
            oos.writeObject(users);
            oos.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private static void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/demo2/users.dat"));
            users = (HashSet<User>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException error) {
            error.printStackTrace();
        }
    }

}
