package by.clevertec.util;

import by.clevertec.model.Car;

public class CostUtil {

    public static String computeShippingCost(Car car) {
        if (car.getCarMake().equals("Jaguar") ||  car.getColor().equals("White")) {
            return "Туркменистан";
        }
        if (car.getMass() <= 1500 && (car.getCarMake().equals("BMW") || car.getCarMake().equals("Lexus") || car.getCarMake().equals("Chrysler") || car.getCarMake().equals("Toyota"))) {
            return "Узбекистан";
        }
        if (car.getMass() > 4000 && (car.getColor().equals("Black") || car.getCarMake().equals("GMC") || car.getCarMake().equals("Dodge"))) {
            return "Казахстан";
        }
        if (car.getReleaseYear() <= 1982 || car.getCarModel().equals("Civic") || car.getCarModel().equals("Cherokee")) {
            return "Кыргызстан";
        }
        if (!(car.getColor().equals("Yellow") || car.getColor().equals("Red") || car.getColor().equals("Green") || car.getColor().equals("Blue")) || car.getPrice() > 40000) {
            return "Россия";
        }
        if (car.getVin().contains("59")) {
            return "Монголия";
        }
        return "Оставшиеся";
    }
}
