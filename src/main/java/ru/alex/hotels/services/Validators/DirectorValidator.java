package ru.alex.hotels.services.Validators;

public class DirectorValidator {
    public static boolean isValidPhone(String phone) {
        return phone.matches("(\\+7|8)\\d{10}");
    }
}
