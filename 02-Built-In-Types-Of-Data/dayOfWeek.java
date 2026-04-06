/*
 * Program: dayOfWeek.
 * Description: Take the date as mm/dd/yyyy and returns the day of the week
 * according to the Gregorian calendar.
 * y0 = yy − (14 − mm) / 12
 * x0 = y0 + y0 / 4 − y0 / 100 + y0 / 400
 * m0 = mm + 12 × ((14 − mm) / 12) − 2
 * d0 = (d0 + x0 + 31*m0 / 12) mod 7
 */
public class dayOfWeek {
    public static void main(String[] args) {
        enum days {
            Sunday,
            Monday,
            Tuesday,
            Wednesday,
            Thursday,
            Friday,
            Saturday
        }

        int mm = Integer.parseInt(args[0]);
        int dd = Integer.parseInt(args[1]);
        int yy = Integer.parseInt(args[2]);

        int y0 = yy - (14 - mm) / 12;
        int x0 = y0 + y0 / 4 - y0 / 100 + y0 / 400;
        int m0 = mm + 12 * ((14 - mm) / 12) - 2;
        int d0 = (dd + x0 + 31 * m0 / 12) % 7;
        switch (d0) {
            case 0 -> System.out.println(days.Sunday);
            case 1 -> System.out.println(days.Monday);
            case 2 -> System.out.println(days.Tuesday);
            case 3 -> System.out.println(days.Wednesday);
            case 4 -> System.out.println(days.Thursday);
            case 5 -> System.out.println(days.Friday);
            case 6 -> System.out.println(days.Saturday);
        }

    }
}
