package com.luxoft.unit;

import com.luxoft.Hire;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.exec.OS;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.luxoft.unit.HasInside.hasSpaceInside;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class HireTest {

    private static Hire hire;

    @BeforeAll
    static void setUp(){
        hire = new Hire();
    }

    @Tag("tests1")
    @ParameterizedTest
    @ValueSource(ints = {13, 20, 15})
    void ht(int val){
        assumeTrue(val%2 == 1);
        assumingThat(
                OS.isFamilyWindows(),
                ()-> System.out.println("777")
        );
        assumingThat(
                OS.isFamilyWindows(),
                ()-> assertTrue(hire.isHired(val).equalsIgnoreCase("half"))
        );
        assertTrue(hire.isHired(val).equalsIgnoreCase("half"));
    }

    @ParameterizedTest
    @CsvSource(value = {"13$half", "20$yes"}, delimiter = '$')
    void ht1(int age, String response) {
        assertTrue(hire.isHired(age).equalsIgnoreCase((response)));
        assertAll(
                ()-> assertEquals(hire.isHired(age), response),
                ()-> assertTrue(age%2==0)
        );

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", delimiter = '#', numLinesToSkip = 1)
    void ht2(int age, String response){
        assertTrue(hire.isHired(age).equalsIgnoreCase((response)));
        assertAll(
                ()-> assertEquals(hire.isHired(age), response),
                ()-> assertTrue(age%2==0)
        );
        System.out.println("1111");
    }

    @ParameterizedTest
    @EnumSource(value = TestEnum.class, names = {"teen.*.*2"}, mode = EnumSource.Mode.MATCH_ALL)
    void ht3(TestEnum data) {
        assertTrue(hire.isHired(data.getAge()).equalsIgnoreCase((data.getResponse())));
        assertThat(hire.isHired(data.getAge()), is(String.class));
        assertThat(hire.isHired(data.getAge()), equalTo(data.getResponse()));
        assertThat("space marine", hasSpaceInside());

    }

    @ParameterizedTest
    @MethodSource(value = "getData")
    void mt(int age, String response){
        assertTrue(hire.isHired(age).equalsIgnoreCase((response)));

        ConfigProps props = ConfigFactory.create((ConfigProps.class));
        System.out.println(props.age());
        System.out.println(props.response());
    }

    @ParameterizedTest
    @ArgumentsSource(value = ArgsProvider.class)
    void at(int age, String response){
        assertTrue(hire.isHired(age).equalsIgnoreCase((response)));
    }

    ////----------------
    private static Stream<Arguments> getData(){
        return Stream.of(
                Arguments.of(4, "no"),
                Arguments.of(15,"half"),
                Arguments.of(100,"yes")
        );
    }

}

