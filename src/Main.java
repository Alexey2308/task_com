import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] products = {"Хлеб", "Яйца", "Молоко", "Творог", "Сахар", "Мука", "Томаты", "Яблоки"};
        int[] prices = {70, 90, 80, 150, 80, 120, 300, 230};
        boolean[] wasChosen = new boolean[products.length];
        int[] quantityInBasket = new int[products.length];
        int sum = 0;

        System.out.println("Список возможных товаров для покупки:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " - " + prices[i] + " руб./уп.");
        }

        while (true) {
            System.out.println("Введите номер позиции и количество товара для добавления в корзину. " +
                    "\nДля завершения введите \"end\".");
            String input = scanner.nextLine();
            String[] inputParts = input.split(" ");
            if ("end".equals(input)) {
                break;
            } else if (inputParts.length != 2) {
                System.out.println("Некорректный ввод! \n");
                continue;
            }
            int productNumber;
            int productQuantity;
            try {
                productNumber = Integer.parseInt(inputParts[0]) - 1;
                productQuantity = Integer.parseInt(inputParts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод!");
                continue;
            }
                if (productNumber < 0 || productNumber > products.length) {
                    System.out.println("Неккоректно введен номер пункта!");
                    continue;
                } else if (productQuantity < 0) {
                    System.out.println("Некорректно введено количество продуктов!");
                    continue;
                }
                int currentPrice = prices[productNumber];
                sum += (productQuantity * currentPrice);
                quantityInBasket[productNumber] += productQuantity;
                wasChosen[productNumber] = true;
                System.out.println("Добавлено в корзину: " + products[productNumber] + ", " + productQuantity + " уп.");

        }
        System.out.println("В Вашей корзине сейчас:");

        int j = 0;
        int count = 0;
        for (boolean chosen : wasChosen) {
            if (chosen) {
                if (quantityInBasket[j] > 0) {
                    System.out.println("@ " + products[j] + " - " + quantityInBasket[j] + " уп. по " + prices[j] +
                            " руб./уп. --- в сумме: " + (prices[j] * quantityInBasket[j]) + " руб.");
                    count++;
                }
            }
            j++;
        }
        if (count == 0) {
            System.out.println("пусто.");
        } else {
            System.out.println("-------------------------------------");
            System.out.println("ИТОГО: " + sum + " руб.");
        }
    }
}