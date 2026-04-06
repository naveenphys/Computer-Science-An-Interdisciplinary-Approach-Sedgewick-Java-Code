/* Program: windChill
 * Description: Calculate wind chill factor according to the National Weather
 *              service. Wind chill (popularly wind chill factor) is the 
 *              sensation of cold produced by the wind for a given ambient air
 *              temperature on exposed skin as the air motion accelerates the 
 *              rate of heat transfer from the body to the surrounding 
 *              atmosphere. Its values are always lower than the air 
 *              temperature in the range where the formula is valid. When the 
 *              apparent temperature is higher than the air temperature, the 
 *              heat index is used instead.
 * wc = 35.74 + 0.6215 * T_a - 35.75 * v^{+0.16} + 0.4275 * T_a * v^{+0.16}
 * Windchill temperature is defined only for temperatures at or below 
 * 10 °C (50 °F) and wind speeds above 4.8 km/h (3.0 mph).
 * 
 * https://en.wikipedia.org/wiki/Wind_chill
 * https://www.weather.gov/epz/wxcalc_windchill
 */
public class windChill {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Missing command line inputs");
            System.out.println("Temperature (C) and wind speed (km/h)");
            return;
        }
        double T_a = Double.parseDouble(args[0]);
        double V_s = Double.parseDouble(args[1]);
        if ((T_a > 10) || (Math.abs(V_s) < 4.8)) {
            System.out.println("Wind chill index: " + Double.NaN);
        } else {
            double wc = 35.74 + 0.6215 * T_a -
                    (35.75 - 0.4275 * T_a) * Math.pow(V_s, 0.16);
            System.out.println("Wind chill index: " + wc);
        }
    }
}
