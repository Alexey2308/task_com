import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] products = {"Хлеб", "Яйца", "Молоко", "Творог", "Сахар", "Мука", "Томаты", "Яблоки"};
        String[] productsSale = {"Творог", "Томаты", "Яблоки"};
        int[] prices = {70, 90, 80, 150, 80, 120, 300, 230};
        boolean[] wasChosen = new boolean[products.length];
        boolean onSale = false;
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
                System.out.println("Некорректно введен номер пункта!");
                continue;
            }
//      Удален блок кода с проверкой отрицательного значения количества продукта.
//      Теперь можем убирать часть товаров из корзины.
            int currentPrice = prices[productNumber];
//       Добавлена возможность обнулить количество продукта в корзине:
            if (productQuantity == 0) {
                quantityInBasket[productNumber] = 0;
                System.out.println("Обнуляем количество товаров!");
            }
            sum += (productQuantity * currentPrice);
            if (productQuantity >= 3) {
                for (int g = 0; g < productsSale.length; g++) {
                    if (productsSale[g].equals(products[productNumber])) {
                        int productQuantitySale = (productQuantity % 3) + ((productQuantity / 3) * 2);
                        sum = sum + (productQuantitySale * currentPrice);
                        System.out.println("Вы приобрели продукт " + products[productNumber] + " по акции \"3 по цене 2\"!");
                        onSale = true;
                        break;
                    } else if (g == productsSale.length - 1) {
                        sum += (productQuantity * currentPrice);
                    } else {
                        continue;
                    }

                }
            }


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
            System.out.print("ИТОГО: " + sum + " руб.");
            if (onSale) {
                System.out.println(" с учетом акции \"3 по цене 2\"!");
            } else {
                System.out.println("");
            }
        }
    }
}