package lambda.part2.exercise;

import data.Person;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class ArrowNotationExercise {

    @Test
    public void getAge() {
        // Person -> Integer
        final Function<Person, Integer> getAge = Person::getAge;

        assertEquals(Integer.valueOf(33), getAge.apply(new Person("", "", 33)));
    }

    // compareAges: (Person, Person) -> boolean
    public void compareAges() {
        BiPredicate<Person, Person> compareAges = (p1, p2) ->
            Integer.compare(p1.getAge(), p2.getAge()) >= 0;
        assertEquals(true, compareAges.test(new Person("a", "b", 22), new Person("c", "d", 22)));
    }

    // getFullName: Person -> String
    public String getFullName(Person person) {
        // TODO
        return person.getFirstName() + " " + person.getLastName();
    }


    // TODO
    // ageOfPersonWithTheLongestFullName: (Person -> String) -> ((Person, Person) -> int)
    public BiFunction<Person, Person, Integer> ageOfPersonWithTheLongestFullName(Function<Person, String> fullName) {
        return (p1, p2) -> {
            int p1Len = fullName.apply(p1).length();
            int p2Len = fullName.apply(p2).length();
            return (p1Len >= p2Len) ? p1.getAge() : p2.getAge();
        };
    }

    @Test
    public void getAgeOfPersonWithTheLongestFullName() {
        // Person -> String
        final Function<Person, String> getFullName = this::getFullName; // TODO

        // (Person, Person) -> Integer
        // TODO use ageOfPersonWithTheLongestFullName(getFullName)
        final BiFunction<Person, Person, Integer> ageOfPersonWithTheLongestFullName = this.ageOfPersonWithTheLongestFullName(getFullName);

        assertEquals(
                Integer.valueOf(1),
                ageOfPersonWithTheLongestFullName.apply(
                        new Person("a", "b", 2),
                        new Person("aa", "b", 1)));
    }
}
