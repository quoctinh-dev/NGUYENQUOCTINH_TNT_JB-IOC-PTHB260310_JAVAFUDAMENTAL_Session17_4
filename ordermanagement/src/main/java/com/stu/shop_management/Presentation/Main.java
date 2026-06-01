package com.stu.shop_management.Presentation;

import com.stu.shop_management.entity.Customer;
import com.stu.shop_management.entity.Product;
import com.stu.shop_management.service.OrderService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final OrderService service =
            new OrderService();

    public static void main(String[] args) {

        do {

            System.out.println("\n===== SHOP MANAGEMENT =====");

            System.out.println("1. Add Product");
            System.out.println("2. Add Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Create Order");
            System.out.println("5. List All Orders");
            System.out.println("6. Search Orders By Customer");
            System.out.println("7. Exit");

            System.out.print("Choose: ");

            int choice;

            try {

                choice = Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (Exception e) {

                System.out.println(
                        "Please input number!"
                );

                continue;
            }

            switch (choice) {

                case 1:

                    addProduct();

                    break;

                case 2:

                    addCustomer();

                    break;

                case 3:

                    updateCustomer();

                    break;

                case 4:

                    createOrder();

                    break;

                case 5:

                    service.listAllOrders();

                    break;

                case 6:

                    searchOrderByCustomer();

                    break;

                case 7:

                    System.exit(0);

                default:

                    System.out.println(
                            "Invalid choice!"
                    );
            }

        } while (true);
    }

    /*
     * ADD PRODUCT
     */
    private static void addProduct() {

        try {

            System.out.print(
                    "Product Name: "
            );

            String name =
                    scanner.nextLine();

            System.out.print(
                    "Price: "
            );

            BigDecimal price =
                    new BigDecimal(
                            scanner.nextLine()
                    );

            Product product =
                    new Product(
                            null,
                            name,
                            price
                    );

            if (service.addProduct(product)) {

                System.out.println(
                        "Add Product Success!"
                );

            } else {

                System.out.println(
                        "Add Product Failed!"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Invalid Data!"
            );
        }
    }

    /*
     * ADD CUSTOMER
     */
    private static void addCustomer() {

        try {

            System.out.print(
                    "Customer Name: "
            );

            String name =
                    scanner.nextLine();

            System.out.print(
                    "Email: "
            );

            String email =
                    scanner.nextLine();

            Customer customer =
                    new Customer(
                            null,
                            name,
                            email
                    );

            if (service.addCustomer(customer)) {

                System.out.println(
                        "Add Customer Success!"
                );

            } else {

                System.out.println(
                        "Add Customer Failed!"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Invalid Data!"
            );
        }
    }

    /*
     * UPDATE CUSTOMER
     */
    private static void updateCustomer() {

        try {

            System.out.print(
                    "Customer ID: "
            );

            long customerId =
                    Long.parseLong(
                            scanner.nextLine()
                    );

            System.out.print(
                    "New Name: "
            );

            String name =
                    scanner.nextLine();

            System.out.print(
                    "New Email: "
            );

            String email =
                    scanner.nextLine();

            Customer customer =
                    new Customer(
                            null,
                            name,
                            email
                    );

            if (service.updateCustomer(
                    customerId,
                    customer
            )) {

                System.out.println(
                        "Update Success!"
                );

            } else {

                System.out.println(
                        "Update Failed!"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Invalid Data!"
            );
        }
    }

    /*
     * CREATE ORDER
     */
    private static void createOrder() {

        try {

            System.out.print(
                    "Customer ID: "
            );

            long customerId =
                    Long.parseLong(
                            scanner.nextLine()
                    );

            System.out.print(
                    "Product ID: "
            );

            long productId =
                    Long.parseLong(
                            scanner.nextLine()
                    );

            System.out.print(
                    "Quantity: "
            );

            int quantity =
                    Integer.parseInt(
                            scanner.nextLine()
                    );

            if (service.createOrder(
                    customerId,
                    productId,
                    quantity
            )) {

                System.out.println(
                        "Create Order Success!"
                );

            } else {

                System.out.println(
                        "Create Order Failed!"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Invalid Data!"
            );
        }
    }

    /*
     * SEARCH ORDER BY CUSTOMER
     */
    private static void searchOrderByCustomer() {

        try {

            System.out.print(
                    "Customer ID: "
            );

            long customerId =
                    Long.parseLong(
                            scanner.nextLine()
                    );

            service.getOrdersByCustomer(
                    customerId
            );

        } catch (Exception e) {

            System.out.println(
                    "Invalid Customer ID!"
            );
        }
    }
}