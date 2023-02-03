import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.klieshchunov.Shop;
import ua.klieshchunov.model.entity.Client;
import ua.klieshchunov.model.entity.Order;
import ua.klieshchunov.model.ShopResponse;
import ua.klieshchunov.model.entity.computer.Computer;
import ua.klieshchunov.model.entity.computer.ComputerFactory;
import ua.klieshchunov.model.entity.computer.ComputerType;
import ua.klieshchunov.repository.StorageUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopTest {
    Shop shop = new Shop();
    public List<Computer> computerStorage;
    public List<Computer> laptopStorage;
    public List<Order> orderStorage;
    public List<Client> clientStorage;
    public final Client testClientIdTwo = new Client(2, "John", "Marston", "johnmarston@gmail.com", 2000);
    public final Client testClientIdThree = new Client(3, "John", "Marston", "johnmarston@gmail.com", 2000);

    @BeforeEach
    public void fillStorages() {
        computerStorage = new ArrayList<>(Arrays.asList(
                ComputerFactory.getPersonalComputer(1, 10, 800, "RAZER"),
                ComputerFactory.getServerComputer(2, 4, 1400, "HP"),
                ComputerFactory.getPersonalComputer(3, 14, 900, "MSI"),
                ComputerFactory.getPersonalComputer(4, 14, 900, "MSI"),
                ComputerFactory.getLaptop(5, 67, 400, "Lenovo"),
                ComputerFactory.getLaptop(6, 3, 650, "Dell"),
                ComputerFactory.getLaptop(7, 21, 900, "Apple")
        ));

        laptopStorage = new ArrayList<>(Arrays.asList(
                ComputerFactory.getLaptop(5, 67, 400, "Lenovo"),
                ComputerFactory.getLaptop(6, 3, 650, "Dell"),
                ComputerFactory.getLaptop(7, 21, 900, "Apple")
        ));

        orderStorage = new ArrayList<>(Arrays.asList(
                new Order(1, 1, 5)
        ));

        clientStorage = new ArrayList<>(Arrays.asList(
                new Client(1, "Alexey", "Klieshchunov", "alexeyklieshchunov@gmail.com", 100)
        ));
    }

    @Test
    public void testFindAvailableComputers() {
        ShopResponse response = shop.getAvailableComputers();
        Assertions.assertEquals(response, new ShopResponse(ShopResponse.ResponseStatus.SUCCESS,  computerStorage.toString()));
    }

    @Test
    public void testFindSpecificComputers() {
        ShopResponse response = shop.getAvailableComputers(ComputerType.LAPTOP);
        computerStorage.remove(0);
        Assertions.assertEquals(response, new ShopResponse(ShopResponse.ResponseStatus.SUCCESS,  laptopStorage.toString()));
    }

    @Test
    public void buyComputerForCreatedClient() {
        int clientId = 2;
        int productId = 5;

        shop.createNewClient(testClientIdTwo);
        ShopResponse response = shop.buyComputer(clientId, productId);
        Order order = new Order(StorageUtils.getAutoId(orderStorage), clientId, productId);

        Assertions.assertEquals(response, new ShopResponse(ShopResponse.ResponseStatus.SUCCESS, order.toString()));
    }

    @Test
    public void buyComputerForNonexistentClient() {
        int clientId = 3;
        int productId = 5;

        shop.createNewClient(testClientIdThree);
        ShopResponse response = shop.buyComputer(clientId, productId);

        Assertions.assertEquals(
                response,
                new ShopResponse(ShopResponse.ResponseStatus.FAILURE,
                        String.format("Couldn't find client with id='%s'", clientId)
                )
        );
    }

    @Test
    public void buyComputerForNonexistentComputer() {
        int clientId = 2;
        int productId = 27;

        shop.createNewClient(testClientIdTwo);
        ShopResponse response = shop.buyComputer(clientId, productId);

        Assertions.assertEquals(
                response,
                new ShopResponse(ShopResponse.ResponseStatus.FAILURE,
                        String.format("Couldn't find computer with id='%s'", productId)
                )
        );
    }

    @Test
    public void buyComputerForUserWithNotEnoughMoney() {
        int clientId = 1;
        int productId = 5;

        ShopResponse response = shop.buyComputer(clientId, productId);

        Assertions.assertEquals(
                response,
                new ShopResponse(ShopResponse.ResponseStatus.FAILURE,
                        String.format("Client with id='%s' doesn't have enough money for subtraction operation", clientId)
                )
        );
    }
}
