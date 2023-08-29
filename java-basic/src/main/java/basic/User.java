package basic;

import java.io.*;

/**
 * @author: zhangjunfeng
 * @createTime: 2023/08/29
 */
public class User {

    private static final long serialiVersionUID = 1L;

    private String userId;

    private String userName;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public static void writeObj() {
        User user = new User("1001", "Joe");
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("user.txt"));
            objectOutputStream.writeObject(user);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readObj() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("user.txt"));
            try {
                Object object = objectInputStream.readObject();
                User user = (User) object;
                System.out.println(user);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        writeObj();
    }
}
