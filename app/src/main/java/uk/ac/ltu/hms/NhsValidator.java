package uk.ac.ltu.hms;

public final class NhsValidator {
    private NhsValidator() {}

    public static boolean isValid(String raw) {
        if (raw == null) return false;
        String d = raw.replaceAll("\\s+", "");
        if (!d.matches("\\d{10}")) return false;

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = d.charAt(i) - '0';
            int weight = 10 - i;     // 10..2
            sum += digit * weight;
        }
        int rem = sum % 11;
        int check = 11 - rem;
        if (check == 11) check = 0; // 11 → 0
        if (check == 10) return false;

        int last = d.charAt(9) - '0';
        return check == last;
    }
}
