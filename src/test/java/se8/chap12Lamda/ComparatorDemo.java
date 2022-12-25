package se8.chap12Lamda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.out;

/**
 * @author: justin
 * @date: 2022/10/20
 */

class Customer {
    private String lastName;
    private String firstName;
    private Integer zipCode;

    public Customer(String firstName, String lastName, Integer zipCode){
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipCode = zipCode;
    }

    public String toString(){
        return String.format("Customer(%s %s %d", firstName, lastName, zipCode);
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getZipCode() {
        return zipCode;
    }
}

public class ComparatorDemo {

    public static void main(String[] args){
        List<Customer> customers = Arrays.asList(
                new Customer("Justin", "Lin", 804),
                new Customer("Monica", "Huang", 804),
                new Customer("Irene", "Lin", 804));

        //comparing方法需傳入函式介面 Function<T, R> R apply(T t)的實例
        //comparing會回傳依照傳入的欄位排序的Comparator.
        Comparator<Customer> byLastName = Comparator.comparing(Customer::getLastName);

        customers.sort(byLastName
                        .thenComparing(Customer::getFirstName)
                        .thenComparing(Customer::getZipCode));

        customers.forEach(out::println);
    }
}
