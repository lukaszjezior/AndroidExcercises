package luje.excercise2;

import org.joda.time.DateTime;

/**
 * The person class.
 */
public class Person {
    private String firstName;
    private String lastName;
    private Boolean isMarried;
    private Integer genderId;
    private DateTime birthDate;

    /**
     * Instantiates a new Person.
     *
     * @param firstName
     *          the first name
     * @param lastName
     *          the last name
     * @param isMarried
     *          the is married boolean value
     * @param genderId
     *          the gender radio button id
     * @param birthDate
     *          the birth datetime object
     */
    public Person(String firstName, String lastName, Boolean isMarried, Integer genderId, DateTime birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isMarried = isMarried;
        this.genderId = genderId;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(Boolean isMarried) {
        this.isMarried = isMarried;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isMarried=" + isMarried.toString() +
                ", genderId=" + genderId.toString() +
                ", birthDate=" + birthDate.getYear() + ":" +
                                 birthDate.getMonthOfYear() + ":" +
                                 birthDate.getDayOfMonth() +
                '}';
    }
}
