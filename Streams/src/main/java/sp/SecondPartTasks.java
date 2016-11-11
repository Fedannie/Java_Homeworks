package sp;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecondPartTasks {
    private static final int CNT_POINTS = 10000000;
    private static final Random rand = new Random();
    private static final double RADIUS = 0.5;

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream()
                .flatMap(SecondPartTasks::allLines)
                .filter(line -> line.contains(sequence))
                .collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        return getPoints().mapToInt(p -> (p.x * p.x + p.y * p.y > RADIUS * RADIUS) ? 0 : 1).average().orElse(0);
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions
                .entrySet()
                .stream()
                .max(Comparator.comparing(e -> e
                        .getValue()
                        .stream()
                        .mapToInt(String::length)
                        .sum()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream()
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum));
    }

    private static Stream<String> allLines(String path) {
        try {
            return Files.lines(Paths.get(path));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static Stream<Pair> getPoints() {
        return Stream.generate(() -> new Pair(rand.nextDouble() - RADIUS, rand.nextDouble() - RADIUS)).limit(CNT_POINTS);
    }

    private static class Pair{
        private double x;
        private double y;

        Pair(double a, double b) {
            x = a;
            y = b;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

    }
}
