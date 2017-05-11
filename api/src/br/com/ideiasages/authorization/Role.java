package br.com.ideiasages.authorization;

/* Must be Prime Numbers */
public class Role {
    public final static long ADMINISTRATOR = 2;
    public final static long ANALYST = 3;
    public final static long IDEALIZER = 5;

    /*
        Get the specific prime number associated with the @role
     */
    public static long getPrimeNumberFromRole(String role) {
        switch (role) {
            case "administrator":
                return Role.ADMINISTRATOR;

            case "analyst":
                return Role.ANALYST;

            case "idealizer":
                return Role.IDEALIZER;

            default:
                return -1;
        }
    }

    /*
        Merge a variety of roles and return the sum
     */
    public static long merge(long... roles) {
        long rolesSum = 1;

        for (long r : roles)
            rolesSum *= r;

        return rolesSum;
    }
}
