package fizzbuzz;

public class FizzBuzz {

    public String pickOne(int input) {

        if (input % 3 == 0 && input % 5 == 0) {
            return "fizzbuzz";
        } if (input % 5 == 0) {
            return "buzz";
        } if (input % 3 == 0) {
            return "fizz";
        } else {
            return String.valueOf(input);
        }

    }
}
