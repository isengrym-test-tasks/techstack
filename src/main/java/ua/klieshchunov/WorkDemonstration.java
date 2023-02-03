package ua.klieshchunov;

import ua.klieshchunov.model.entity.Client;
import ua.klieshchunov.model.entity.computer.ComputerType;
import ua.klieshchunov.service.impl.OrderServiceImpl;

public class WorkDemonstration {
    public static void main(String[] args) {
        Shop shop = new Shop();

        //Id will be replaced so any can be specified
        Client client = new Client(0, "Donkey", "Kong", "donkeykong@gmail.com", 0);
        System.out.println(shop.createNewClient(client));
        System.out.println("\n");
        System.out.println(shop.getAvailableComputers(ComputerType.LAPTOP));

        //Client id was given in a payload of the response we got from createNewClient method
        //Product id chosen from the list of computers we got from getAvailableComputers method
        System.out.println("\n");
        System.out.println(shop.buyComputer(2, 5));

        //Oops, our client doesn't have enough money, let's fix that
        System.out.println("\n");
        System.out.println(shop.addFunds(client, 2000));

        //Now the order is created
        System.out.println("\n");
        System.out.println(shop.buyComputer(2, 5));
        System.out.println(shop.buyComputer(2, 6));

        //Let's get the orders list for our user to make sure everything went well
        System.out.println("\n");
        System.out.println(OrderServiceImpl.getInstance().findAllForClient(client.getId()));
    }
}
