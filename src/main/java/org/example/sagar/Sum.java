package org.example.sagar;

public class Sum {

    public static void main(String[] args) {
        String number1 = "39999888899999999955555588889994443333333332222299988776665554448888881219201921092109201920192019201920192019019201920910291029104034343492093029092090294029402940299209420490249204204949242904";
        String number2 = "568678658769896797654654564123321994394349304930493094309430940394039403940390493049304930493094039403940394039403903943940930432930193019301930193019301930910391039109301310310931039103901310913910";

        StringBuilder stringBuilder = addTwoNumberString(number1, number2);
        System.out.println(stringBuilder);

    }

    private static StringBuilder addTwoNumberString(String number1, String number2) {
        int length1 = number1.length() - 1;
        int length2 = number2.length() - 1;
        StringBuilder stringBuilder = new StringBuilder();
        int overflow = 0;

        while (length2 >= 0 || length1 >= 0 || overflow > 0) {
            int digit1 = length1 >= 0 ? number1.charAt(length1) - '0' : 0;
            int digit2 = length2 >= 0 ? number2.charAt(length2) - '0' : 0;

            int sum = digit1 + digit2 + overflow;
            stringBuilder.append(sum % 10);
            overflow = sum / 10;
            if (length1 >= 0) length1--;
            if (length2 >= 0) length2--;
        }
        return stringBuilder.reverse();
    }
}
