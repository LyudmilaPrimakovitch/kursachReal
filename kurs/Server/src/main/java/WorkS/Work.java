package WorkS;

import DB.Connect;
import DB.SQLFactory;
import StorOrg.*;

import java.io.*;
import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
                        System.out.println("Запрос к БД на изменение пользователя(таблица workers), клиент: " + clientSocket.getInetAddress().toString());
                        Worker worker = (Worker) sois.readObject();
                        System.out.println(worker.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getWorkers().changeWorker(worker)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Incorrect Data");
                        }
                    }

                    case "delWorker" -> {
                        System.out.println("Выполняется удаление работника...");
                        Worker worker = (Worker) sois.readObject();
                        System.out.println(worker.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getWorkers().deleteWorker(worker)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при удалении работника");
                        }
                    }

                    case "delClient" -> {
                        System.out.println("Выполняется блокировка поставщика...");
                        Clients clients = (Clients) sois.readObject();
                        System.out.println(clients.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getClients().deleteClient(clients)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при блокировке");
                        }
                    }

                    case "best"->{
                        System.out.println("Запрос к БД на получение данных о поставщиках: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Clients> clients = sqlFactory.getClients().get();
                        Collections.sort(clients, new Comparator<Clients>(){
                            public int compare(Clients c1, Clients c2)
                            {
                                return c2.getAverageV().compareTo(c1.getAverageV());
                            }
                        });
                        System.out.println(clients.toString());
                        soos.writeObject(clients);
                    }

                    case "worst"->{
                        System.out.println("Запрос к БД на получение данных о поставщиках: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Clients> clients = sqlFactory.getClients().get();
                        Collections.sort(clients, new Comparator<Clients>(){
                            public int compare(Clients c1, Clients c2)
                            {
                                return c1.getAverageV().compareTo(c2.getAverageV());
                            }
                        });
                        System.out.println(clients.toString());
                        soos.writeObject(clients);
                    }

                    case "sortV"->{
                        System.out.println("Запрос к БД на получение данных о поставщиках: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Clients> clients = sqlFactory.getClients().get();
                        Collections.sort(clients, new Comparator<Clients>(){
                            public int compare(Clients c1, Clients c2)
                            {
                                 if (c2.getPositiveV()>c1.getPositiveV()) return 1;
                                 else return 0;
                            }
                        });
                        System.out.println(clients.toString());
                        soos.writeObject(clients);
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

                    case "addProd"->{
                        System.out.println("Выполняется добавление продукции...");
                        Products product = (Products) sois.readObject();
                        System.out.println(product.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getProducts().insertProducts(product)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при записи продукции.");
                        }
                    }

                    case "findProducts"->{
                        System.out.println("Запрос к БД на поиск продукции: " + clientSocket.getInetAddress().toString());
                        Products c = (Products) sois.readObject();
                        System.out.println(c.toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Products> products = sqlFactory.getProducts().find(c);
                        System.out.println(products.toString());
                        soos.writeObject(products);
                    }

                    case "showProducts"->{
                        System.out.println("Запрос к БД на получение данных о типах продукции: " + clientSocket.getInetAddress().toString());
                        SQLFactory sqlFactory = new SQLFactory();
                        ArrayList<Products> products = sqlFactory.getProducts().get();
                        System.out.println(products.toString());
                        soos.writeObject(products);
                    }

                    case "showRequests"->
                    {
                        System.out.println("Запрос к БД на проверку работника (таблица workers), клиент: " + clientSocket.getInetAddress().toString());
                        Role r = (Role) sois.readObject();
                        System.out.println(r.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<TTNs> ttns =sqlFactory.getTTNs().getByWorker(r.getId());
                        System.out.println(ttns.toString());
                        soos.writeObject(ttns);
                    }

                    case "regTTN"->{
                        System.out.println("Выполняется регистрация накладной...");
                        Products products = (Products) sois.readObject();
                        System.out.println(products.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getProducts().registration(products)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при оформлении");
                        }
                    }

                    case "updateTTN", "rejectTTN" ->{
                        System.out.println("Выполняется регистрация накладной...");
                        TTNs ttns = (TTNs) sois.readObject();
                        System.out.println(ttns.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        if (sqlFactory.getTTNs().bring(ttns)) {
                            soos.writeObject("OK");
                        } else {
                            soos.writeObject("Ошибка при оформлении");
                        }
                    }

                    case "showTTNsClient"->{
                        System.out.println("Запрос к БД на проверку работника (таблица clients), клиент: " + clientSocket.getInetAddress().toString());
                        Role r = (Role) sois.readObject();
                        System.out.println(r.toString());

                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<TTNs> ttns =sqlFactory.getTTNs().getByClient(r.getId());
                        System.out.println(ttns.toString());
                        soos.writeObject(ttns);
                    }

                    case "inventarization"->{
                        System.out.println("Запрос в БД на инвентаризацию");
                        String proc = "{call inventarization()}";
                        try (CallableStatement callableStatement = Connect.dbConnection.prepareCall(proc)) {
                            callableStatement.execute();
                        } catch (SQLIntegrityConstraintViolationException e) {
                            System.out.println("ошибка");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<TTNs> ttns =sqlFactory.getTTNs().getTtns();
                        System.out.println(ttns.toString());
                        soos.writeObject(ttns);
                    }

                    case "getDiagrReceive" -> {
                        System.out.println("Запрос в БД на получение прибыли школы");
                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<Receive> receives = sqlFactory.getReceive().get();

                        ArrayList<AbstractMap.SimpleEntry<String, Integer>> data = new ArrayList<>();
                        for (Receive r : receives) {
                            data.add(new AbstractMap.SimpleEntry<String, Integer>(
                                    r.getStorage(), r.getBalance()));
                        }

                        soos.writeObject(data);
                    }
                    case "getChartReceive" -> {
                        System.out.println("Запрос в БД на получение прибыли школы");
                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<Receive> receives = sqlFactory.getReceive().getChart();

                        ArrayList<AbstractMap.SimpleEntry<String, Integer>> data = new ArrayList<>();
                        for (Receive r : receives) {
                            data.add(new AbstractMap.SimpleEntry<String, Integer>(
                                    r.getStorage(), r.getBalance()));
                        }

                        soos.writeObject(data);
                    }
                    case "writeReceiveReport" -> {
                        SQLFactory sqlFactory = new SQLFactory();

                        ArrayList<Receive> receives = sqlFactory.getReceive().get();

                        if (receives.size() == 0)
                            soos.writeObject("Ничего нет");
                        else {

                            BufferedWriter outputWriter = null;
                            outputWriter = new BufferedWriter(new FileWriter("profit"));
                            outputWriter.write("Прибыль по складам:\n");
                            for (Receive r : receives) {
                                outputWriter.write(r.getBalance() + "   " + r.getStorage());
                                outputWriter.newLine();
                            }
                            outputWriter.flush();
                            outputWriter.close();

                            soos.writeObject("OK");
                        }
                    }

                }
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Client disconnected." + e.getMessage());
        }
    }
}
