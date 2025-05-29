package org.example.task_1;

public class MainTask1 {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();
        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);
        int c = calc.devide.apply(a, b); // Ошибка: деление на ноль!
        calc.println.accept(c);
    }
}