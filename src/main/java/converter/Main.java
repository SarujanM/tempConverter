package converter;

public class Main {
    public static void main (String[] args) {
        TemperatureConverter converter = new TemperatureConverter();

        System.out.println("300k to Celsius:" + converter.kelvinToCelsius(300));
        System.out.println("0 C to Fagrenheit:" + converter.celsiusToFahrenheit(0));
        System.out.println("32 F to Celsius" + converter.fahrenheitToCelsius(32));
        System.out.println("Is -50 C exremee" + converter.isExtremeTemperature(-50));
    }
}
