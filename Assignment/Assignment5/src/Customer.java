public class Customer {
    private Gender gender;
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.matches("[A-Za-z]+")) {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.matches("[A-Za-z]+")) {
            this.lastName = lastName;
        }
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        if (socialSecurityNumber.matches("[1-9][0-9]{7}")) {
            this.socialSecurityNumber = socialSecurityNumber;
        }
    }

    Customer(String firstName,String lastName,Gender gender,String ssn){
        setFirstName(formatName(firstName));
        setLastName(formatName(lastName));
        setGender(gender);
        setSocialSecurityNumber(ssn);
    }

    public static boolean checkName(String name){
        return name.matches("[A-Z][a-z]+");
    }

    public static String formatName(String name){
        return name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
    }

    public static boolean checkSSN(String ssn){
        return ssn.matches("[1-9][0-9]{7}");
    }




}
