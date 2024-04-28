package Server;

import com.hit.dm.User;
import com.hit.service.UserService;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {

    private ArrayList<ClientHandler> clients;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    private ObjectInputStream objectInputStream;

    private UserService userService = new UserService("users.txt");

    public ClientHandler(Socket socket, ArrayList<ClientHandler> clients) {
        try {
            this.socket = socket;
            this.clients = clients;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            //this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String msg;
            while ((msg = reader.readLine()) != null) {

                if (msg.equalsIgnoreCase( "exit")) {
                    break;
                }

                if (msg.startsWith("Register-USER#3$4:")) {
                    String userString = msg.substring("Register-USER#3$4:".length());
                    userService.createUserFromString(userString);
                    msg = null;
                }
                else if (msg.equalsIgnoreCase("REGISTEREDLIST")) {
                    sendUserList();
                    msg = null;
                }
                else {

                    for (ClientHandler cl : clients) {
                        cl.writer.println(msg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void sendUserList(){
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            ArrayList<User> users =  userService.getAllUsers();
            outputStream.writeObject(users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void RegisterUser(User newUser) {
        ObjectInputStream objectInputStream = null;
        try {
//            objectInputStream = new ObjectInputStream(socket.getInputStream());
//            User newUser = (User) objectInputStream.readObject();
            userService.createUser(newUser.username,newUser.fullName,newUser.password,newUser.email,newUser.gender,newUser.phoneNo);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
