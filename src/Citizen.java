public class Citizen {
    private String name;
    private String address;//FORMAT: Door number, street number, constituency-sector, constituencyName, pincode
    private String aadharNumber;
    private String gender;
    private int age;
    private String DOB;//in DD//MM//YYYY format

    public boolean isEligible(){
        return this.age>=18;
    }

    public Citizen(String name, String address, String aadharNumber, String gender, int age, String DOB) {
        this.name = name;
        this.address = address;
        this.aadharNumber = aadharNumber;
        this.gender = gender;
        this.age = age;
        this.DOB = DOB;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getDOB() {
        return DOB;
    }

    @Override
    public String toString() {
        return "Citizen{" + "name='" + name + '\'' + ", address='" + address + '\'' + ", aadharNumber='" + aadharNumber + '\'' + ", gender='" + gender + '\'' + ", age=" + age + ", DOB='" + DOB + '\'' + '}';
    }
}
