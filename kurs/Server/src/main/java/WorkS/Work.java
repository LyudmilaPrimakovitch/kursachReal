package WorkS;

import DB.SQLFactory;
import StorOrg.Admin;
import StorOrg.Authorization;
import StorOrg.Role;
import StorOrg.Worker;
import StorOrg.Clients;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Work implements Runnable {
    protected Socket clientSocket = null;
    ObjectInputStream sois;
    ObjectOutputStream soos;

    public Work(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            sois = new ObjectInputStream(clientSocket.getInputStream());
            soos = new ObjectOutputStream(clientSocket.getOutputStream());
            while (true) {
                System.out.println("Получение команды от клиента...");
                String choice = sois.readObject().toString();
                System.out.println(choice);
                System.out.println("Команда получена");
                switch (choice) {
                    case "adminInf" -> {
                        System.out.println("Запрос к БД на получение информации об администраторе: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Admin> infList = sqlFactory.getAdmin().get();
                        System.out.println(infList.toString());
                        soos.writeObject(infList);
                    }

                    case "registrationWorker" -> {
                        System.out.println("Запрос к БД на проверку пользователя(таблица workers), клиент: " + clientSocket.getInetAddress().toString());
                        Worker worker = (Worker) sois.readObject();
                        System.out.println(worker.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getWorkers().insert(worker)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Incorrect Data");
                        }
                    }
                    case "registrationClient" -> {
                        System.out.println("Запрос к БД на проверку пользователя(таблица clients), клиент: " + clientSocket.getInetAddress().toString());
                        Clients client = (Clients) sois.readObject();
                        System.out.println(client.toString());

                        SQLFactory sqlFactory = new SQLFactory();
                        Role r = sqlFactory.getClients().insert(client);
                        System.out.println((r.toString()));

                        if (r.getId() != 0 && r.getRole() != "") {
                            soos.writeObject("OK");
                            soos.writeObject(r);
                        } else {
                            soos.writeObject("This user is already existed");
                        }
                    }
                    case "workerInf" -> {
                        System.out.println("Запрос к БД на проверку работника (таблица workers), клиент: " + clientSocket.getInetAddress().toString());
                        Role r = (Role) sois.readObject();
                        System.out.println(r.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<Worker> worker = sqlFactory.getWorkers().getWorker(r);
                        System.out.println(worker.toString());
                        soos.writeObject(worker);
                    }

                    case "changeWorker" -> {
                        System.out.println("Запрос к БД на изменение пользователя(таблица teachers), клиент: " + clientSocket.getInetAddress().toString());
                        Worker worker = (Worker) sois.readObject();
                        System.out.println(worker.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getWorkers().changeWorker(worker)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Incorrect Data");
                        }
                    }

                    case "authorization" -> {
                        System.out.println("Выполняется авторизация пользователя....");
                        Authorization auth = (Authorization) sois.readObject();
                        System.out.println(auth.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        Role r = sqlFactory.getRole().getRole(auth);
                        System.out.println(r.toString());
                        if (r.getId() != 0 && r.getRole() != "") {
                            soos.writeObject("OK");
                            soos.writeObject(r);
                        } else
                            soos.writeObject("There is no data!");
                    }

                }
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Client disconnected." + e.getMessage());
        }
    }
}
