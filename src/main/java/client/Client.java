package client;

import app.controller.CustomerController;
import app.controller.ProductController;
import app.domain.Customer;
import app.domain.Product;
import app.repositories.CustomerRepository;
import app.repositories.CustomerRepositoryMap;
import app.repositories.ProductRepository;
import app.repositories.ProductRepositoryMap;
import app.services.CustomerService;
import app.services.CustomerServiceImpl;
import app.services.ProductService;
import app.services.ProductServiceImpl;

import java.util.Scanner;

public class Client {


    private static Scanner scanner;
    private static ProductController productController;
    private static CustomerController customerController;


    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepositoryMap();
        ProductService productService = new ProductServiceImpl(productRepository);
        productController = new ProductController(productService);

        CustomerRepository customerRepository = new CustomerRepositoryMap();
        CustomerService customerService = new CustomerServiceImpl(customerRepository, productRepository);
        customerController = new CustomerController(customerService);

        scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Выберите действие");
                System.out.println("1. Операции с ПРОДУКТАМИ");
                System.out.println("2. Операции с ПОКУПАТЕЛЯМИ");
                System.out.println("0. Выход");
                System.out.println("Ваш выбор :");

                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        productOperation();
                        break;
                    case 2:
                        customerOperation();
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println("Некорректный ввод");
                        break;
                }


            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }


    }

    private static void productOperation() {

        while (true) {
            try {

                System.out.println("Выберите действия с продуктом :");
                System.out.println("1. Сохранение продукта");
                System.out.println("2. Получение всех активных продуктов");
                System.out.println("3. Получение одного продукта по ID ");
                System.out.println("4. Изменение продукта ");
                System.out.println("5. Удаление продукта по ID  ");
                System.out.println("6. Удаление продукта по названию  ");
                System.out.println("7. Восстановление одного продукта по ID  ");
                System.out.println("8. Получение общего количество продуктов  ");
                System.out.println("9. Получение общей стоимости продуктов  ");
                System.out.println("10.Получение средней стоимости продуктов  ");
                System.out.println("0. Выход");
                System.out.println("Ваш выбор :");

                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.println("Введите название продукта");
                        String name = scanner.nextLine().trim();
                        System.out.println("Введите цену продукта");
                        double price = Double.parseDouble(scanner.nextLine().trim());
                        Product product = productController.save(name, price);
                        System.out.println("Сохраненный продукт: ");
                        System.out.println(product);
                        break;
                    case 2:
                        productController.getAll().forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("Введите ID продукта");
                        Long id = Long.parseLong(scanner.nextLine());
                        Product foundProduct = productController.getById(id);
                        System.out.println("Найденный продукт: ");
                        System.out.println(foundProduct);
                        break;
                    case 4:
                        System.out.println("Введите ID продукта");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите название продукта");
                        name = scanner.nextLine().trim();
                        System.out.println("Введите цену продукта");
                        price = Double.parseDouble(scanner.nextLine().trim());

                        productController.update(id, name, price);
                        break;

                    case 5:
                        System.out.println("Введите ID продукта");
                        id = Long.parseLong(scanner.nextLine());

                        productController.deleteById(id);
                        break;

                    case 6:
                        System.out.println("Введите наименование продукта: ");
                        name = scanner.nextLine();
                        productController.deleteByName(name);
                        break;
                    case 7:
                        //TODO check restore id
                        System.out.println("Введите ID продукта");
                        id = Long.parseLong(scanner.nextLine());

                        productController.restoreById(id);
                        break;

                    case 8:
                        System.out.println("Общее количество продуктов " + productController.getActiveProductsTotalCount());

                        break;

                    case 9:
                        System.out.println("Общая стоимость продуктов " + productController.getActiveProductsTotalCost());

                        break;

                    case 10:
                        System.out.println("Средняя стоимость продукта " + productController.getActiveProductsAveragePrice());

                        break;
                    case 0:
                        System.out.println("Выход из меню продуктов");

                        return;
                    default:
                        System.err.println("Некорректный ввод");
                        break;
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void customerOperation() {
        while (true) {
            try {

                System.out.println("Выберите действия с продуктом :");
                System.out.println("1. Сохранение покупателя");
                System.out.println("2. Получение всех активных покупателей");
                System.out.println("3. Получение одного покупателя по ID ");
                System.out.println("4. Изменение покупателя ");
                System.out.println("5. Удаление покупателя по ID  ");
                System.out.println("6. Удаление покупателя по названию  ");
                System.out.println("7. Восстановление одного покупателя по ID  ");
                System.out.println("8. Получение общего количества покупателей  ");
                System.out.println("9. Получение общей стоимости продуктов в корзине полупателя  ");
                System.out.println("10.Получение средней стоимости продуктов в корзине  ");
                System.out.println("11.Добавить продукт в корзину ");
                System.out.println("12.Удалить продукт из корзины");
                System.out.println("13.Очистить корзину");
                System.out.println("0. Выход");
                System.out.println("Ваш выбор :");

                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.println("Введите имя покупателя");
                        String name = scanner.nextLine().trim();
                        Customer customer = customerController.save(name);
                        System.out.println("Сохраненный покупатель: ");
                        System.out.println(customer);
                        break;
                    case 2:
                        customerController.getAll().forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("Введите ID покупателя");
                        Long id = Long.parseLong(scanner.nextLine());
                        Customer foundCustomer = customerController.getById(id);
                        System.out.println("Найденный покупатель: ");
                        System.out.println(foundCustomer);
                        break;
                    case 4:
                        System.out.println("Введите ID покупателя");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите имя покупателя");
                        name = scanner.nextLine().trim();
                        customerController.updateCustomer(id, name);

                        break;

                    case 5:
                        System.out.println("Введите ID покупателя");
                        id = Long.parseLong(scanner.nextLine());
                        customerController.deleteCustomerById(id);
                        break;
                    case 6:
                        System.out.println("Введите имя покупателя");
                        name = scanner.nextLine().trim();
                        customerController.deleteByName(name);
                        break;
                    case 7:
                        System.out.println("Введите ID покупателя");
                        id = Long.parseLong(scanner.nextLine());
                        customerController.restoreById(id);
                        break;
                    case 8:
                        System.out.println("Общее количество покупателей : " + customerController.getTotalActiveCustomerCount());
                        break;
                    case 9:
                        System.out.println("Введите ID покупателя");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Общая стоимость корзины покупателя : " + customerController.getCustomerCartTotalCost(id));
                        break;
                    case 10:
                        System.out.println("Введите ID покупателя");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Средняя стоимость продуктов покупателя покупателя : " + customerController.getAverageProductPriceInCustomerCart(id));
                        break;
                    case 11:
                        System.out.println("Введите ID покупателя:");
                        Long customerId = Long.parseLong(scanner.nextLine());

                        System.out.println("Введите ID продукта:");
                        Long productId = Long.parseLong(scanner.nextLine());

                        customerController.addProductToCustomerCart(customerId, productId);
                        System.out.println("Продукт добавлен в корзину!");
                        break;
                    case 12:
                        System.out.println("Введите ID покупателя:");
                        customerId = Long.parseLong(scanner.nextLine());

                        System.out.println("Введите ID продукта:");
                        productId = Long.parseLong(scanner.nextLine());

                        customerController.removeProductFromCustomerCart(customerId, productId);
                        System.out.println("Продукт удалён из корзины!");
                        break;
                    case 13:
                        System.out.println("Введите ID покупателя:");
                        id = Long.parseLong(scanner.nextLine());
                        customerController.clearCustomerCart(id);
                        System.out.println("Корзина очищена!");
                        break;
                    case 0:
                        System.out.println("Выход из меню покупателей");
                        return;

                    default:
                        System.out.println("Некорректный выбор, попробуйте снова.");
                        break;


                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}