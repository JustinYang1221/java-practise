package se8.chap12Lamda;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static java.lang.System.out;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReduceCollectDemo {
    public static void main(String[] args) {
        List<Employee2> employees = Arrays.asList(
                new Employee2("Justin", 39, Gender2.MALE),
                new Employee2("Monica", 36, Gender2.FEMALE),
                new Employee2("Irene", 6, Gender2.FEMALE)
        );

        int sum = employees.stream()
                .filter(employee -> employee.getGender() == Gender2.MALE)
                .mapToInt(Employee2::getAge)
                .reduce((total, age) ->  total + age)//第一種寫法, 不需指定函式介面實例的第一個參數的初始值, 考量到數組可能為空,
                .getAsInt();                          //因此會回傳 OptionalInt

        long female = employees.stream()
                .filter(employee -> employee.getGender() == Gender2.FEMALE)
                .count();

        long average = employees.stream()
                .filter(employee -> employee.getGender() == Gender2.FEMALE)
                .mapToInt(Employee2::getAge)
                .reduce(0, (total, age) ->  total + age)/female;  //第二種寫法, identity指定給total
//                .getAsInt() / female; //

        int max = employees.stream()
                .filter(employee -> employee.getGender() == Gender2.MALE)
                .mapToInt(Employee2::getAge)
                // 函式介面 IntBinaryOperator , 介面方法 applyAsInt(Int, Int)
                .reduce(0, (currMax, age) -> age > currMax ? age : currMax); //identity 的值會assign給currMax

        Arrays.asList(sum, average, max).forEach(out::println);
    }

    List<Employee2> employees = null;

    @BeforeAll
    public void initBean(){
        employees = Arrays.asList(
                new Employee2("Justin", 39, Gender2.MALE),
                new Employee2("Monica", 36, Gender2.FEMALE),
                new Employee2("Irene", 6, Gender2.FEMALE)
        );
    }


    @Test
    public void collectTest1(){

        employees.stream().filter(e-> e.getGender() == Gender2.FEMALE)
                .collect(()-> new ArrayList<Employee2>(),
                        (maleList, employee)-> maleList.add(employee),
                        (maleList, maleList2)-> maleList.addAll(maleList2));
        //可簡化為 方法參考, 建構子參考
        //collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Test
    public void cocllectGroupByTest(){
        Map<Gender2, List<Employee2>> groupByGenderMap = employees.stream()
                .collect(groupingBy(Employee2::getGender));

        out.println(groupByGenderMap);
    }

    @Test
    public void groupByAndGetNameTest(){
        //依性別分組後, 取得分組下的姓名
        Map<Gender2, List<String>> maleNames = employees.stream()
                .collect(groupingBy(Employee2::getGender,
                        mapping(Employee2::getName, toList()))
                );
    }

    @Test
    public void groupyByAndSum(){
        //依性別分組後, 取得男女年齡加總
       Map<Gender2, Integer> genderMap = employees.stream()
                .collect(groupingBy(Employee2::getGender,
                        reducing(0, Employee2::getAge, Integer::sum )) //reducing方法回傳經過lamda表示式處理後的Collector實例.
                );

    }

    @Test
    public void flatMapTest(){
//        Optional<City> city = Optional.of(new City("test"));
        Optional<City> city = Optional.empty();
//        Customer2 customer2 = new Customer2(Optional.of(new Address(city)));
        Customer2 customer2 = new Customer2(Optional.empty());
        Order order = new Order(Optional.of(customer2));


        City city2 = order.getCustomer2().flatMap(Customer2::getAddress)
                .flatMap(Address::getCity)
                .orElse(new City("無"));

        System.out.println(city2);
    }

    @Test
    public void optionalMapTest(){
        Optional<City> city = Optional.empty();
//        Customer2 customer2 = new Customer2(Optional.of(new Address(city)));
        Customer2 customer2 = new Customer2(Optional.empty());
        Order order = new Order(Optional.of(customer2));
        Order order2 = new Order(Optional.empty());
        List<LineItem> lineItems = new ArrayList<>();
        lineItems.add(new LineItem("p1"));
        lineItems.add(new LineItem("p2"));
        lineItems.add(new LineItem("p3"));

        List<Order> orders = new ArrayList<>();

        order.setLineItems(lineItems);
        order2.setLineItems(null);

        orders.add(order);
        orders.add(order2);
        orders.stream()
                .map(Order::getLineItems)  //map 傳回 Stream<List<LineItem>> (將Stream看成盒子, List<LineItem>是盒子內的值
                .flatMap(lineItemList -> Objects.isNull(lineItemList)? Stream.empty() :lineItemList.stream()) //Stream的flatMap指定的lamda是上一個盒子內的值 List<LineItem>
                .map(LineItem::getName)
                .forEach(out::println);
                //.collect(Collectors.toList());




    }

}

enum Gender2 { FEMALE, MALE }

class Employee2 {
    private String name;
    private Integer age;
    private Gender2 gender;

    public Employee2(String name, Integer age, Gender2 gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Gender2 getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Employee2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}

class Order {
    private Optional<Customer2> customer2;
    private List<LineItem> lineItems;
    public Order(Optional<Customer2> customer2){
        this.customer2 = customer2;
    }

    public Optional<Customer2> getCustomer2() {
        return customer2;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
}

class LineItem {
    private String name;

    public LineItem(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Customer2 {
    private Optional<Address> address;

    public Customer2(Optional<Address> address){
        this.address = address;
    }

    public Optional<Address> getAddress() {
        return address;
    }

    public void setAddress(Optional<Address> address) {
        this.address = address;
    }


}

class Address {
    private Optional<City> city;

    public Address(Optional<City> city){
        this.city = city;
    }

    public Optional<City> getCity() {
        return city;
    }

    public void setCity(Optional<City> city) {
        this.city = city;
    }
}

class City {
    private String name;

    public City(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                '}';
    }
}

