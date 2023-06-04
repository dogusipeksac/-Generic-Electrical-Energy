package com.example.electricitycostcalculator.helper;

public class Helper {
    public static boolean isValidServiceNumber(String serviceNumber) {
        // Hizmet numarası, tam olarak 10 karakterden oluşmalıdır ve yalnızca harf ve rakamlardan oluşmalıdır.
        String regex = "^[a-zA-Z0-9]{10}$";
        return serviceNumber.matches(regex);
    }

}
