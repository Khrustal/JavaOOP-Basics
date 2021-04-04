package basics.address;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddressOfPerson {
    public static void main(String[] args) throws IOException, ParseException {
        Address[] addresses = new Address[6];
        Person[] persons = new Person[6];

        addresses[0] = new Address("Russia", "Moscow", "Ulitsa Kakhovka", "9");
        addresses[1] = new Address("Russia", "Klin", "Ulitsa Leningradskaya", "12");
        addresses[2] = new Address("USA", "Las Vegas", "NV 89169", "1565 Izabella Ave");
        addresses[3] = new Address("USA", "Corcoran", "CA 93212", "2437 Lorina Ave");
        addresses[4] = new Address("Spain", "Valencia", "Carrer de Riu Tinto", "69");
        addresses[5] = new Address("Russia", "Moscow", "Ulitsa Kakhovka", "17");

        persons[0] = new Person("Dmitry", "Holodilov", new Date(95, Calendar.APRIL, 7), addresses[0]);
        persons[1] = new Person("Alexander", "Orlov", new Date(91, Calendar.MARCH, 16), addresses[1]);
        persons[2] = new Person("John", "Smith", new Date(84, Calendar.FEBRUARY, 21), addresses[2]);
        persons[3] = new Person("Erik", "Lesly", new Date(97, Calendar.APRIL, 1), addresses[3]);
        persons[4] = new Person("Isabella", "Lopez", new Date(101, Calendar.JULY, 12), addresses[4]);
        persons[5] = new Person("Oleg", "Morozov", new Date(74, Calendar.APRIL, 13), addresses[5]);

        Format f = new SimpleDateFormat("dd/MM/yyyy"); // date formatter

        // Print all persons info
        for(int i = 0; i < 5; i++) {
            System.out.println("_______");
            System.out.println(persons[i].getName() + " " + persons[i].getSurname() + " " + f.format(persons[i].getBirthDate()));
            System.out.println(persons[i].getAddress().toString());
        }
        System.out.println("_______");

        System.out.println("****************************");
        //Finding person by surname
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input person's surname to find: ");
        final String surname = reader.readLine();
        Optional<Person> person = Arrays.stream(persons).filter(x -> x.getSurname().equals(surname)).findFirst();
        if(person.isPresent()) {
            System.out.println("Person found");
            System.out.println(person.get().getName() + " " + person.get().getSurname() + " " + f.format(person.get().getBirthDate()));
            System.out.println(person.get().getAddress().toString());
        }
        else {
            System.out.println("Persons not found");
        }

        person = Optional.empty();

        System.out.println("****************************");
        //Finding person by address(city)
        System.out.print("Input person's city to find: ");
        final String city = reader.readLine();
        person = Arrays.stream(persons).filter(x -> x.getAddress().getCity().equals(city)).findFirst();
        if(person.isPresent()) {
            System.out.println("Person found");
            System.out.println(person.get().getName() + " " + person.get().getSurname() + " " + f.format(person.get().getBirthDate()));
            System.out.println(person.get().getAddress().toString());
        }
        else {
            System.out.println("Persons not found");
        }


        System.out.println("****************************");
        //Finding persons by dates
        System.out.println("(Input example: 1/12/1990 21/12/2000");
        System.out.print("Input first date and second date: ");
        String[] dates = reader.readLine().split(" ");
        Date firstDate = new SimpleDateFormat("dd/MM/yyyy").parse(dates[0]);
        Date secondDate = new SimpleDateFormat("dd/MM/yyyy").parse(dates[1]);
        Person[] personsByDate = Arrays.stream(persons)
                .filter(x -> x.getBirthDate().after(firstDate) && x.getBirthDate().before(secondDate))
                .toArray(Person[]::new);
        System.out.println("Person's found:");
        for (Person value : personsByDate) {
            System.out.println("_______");
            System.out.println(value.getName() + " " + value.getSurname() + " " + f.format(value.getBirthDate()));
            System.out.println(value.getAddress().toString());
        }
        System.out.println("****************************");

        //Find oldest
        person = Optional.empty();
        person = Arrays.stream(persons).max(Comparator.comparing(Person::getBirthDate));
        if(person.isPresent()) {
            System.out.print("Oldest person: ");
            System.out.println(person.get().getName() + " " + person.get().getSurname() + " " + f.format(person.get().getBirthDate()));
        }
        else {
            System.out.println("Persons not found");
        }
        System.out.println("****************************");

        //Find persons on same street
        for(Person x : persons) {
            Person[] sameStreetPersons = Arrays.stream(persons)
                    .filter(z -> z.getAddress().getStreet().equals(x.getAddress().getStreet()) && !z.equals(x))
                    .toArray(Person[]::new);
            if(sameStreetPersons.length > 0) {
                System.out.println(x.getName() + " " + x.getSurname() + " lives on same street with:");
                for(Person y : sameStreetPersons) {
                    System.out.println(y.getName() + " " + y.getSurname());
                }
            }
            System.out.println("____________");
        }



    }
}
