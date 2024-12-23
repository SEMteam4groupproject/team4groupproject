package com.napier.team4groupproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for CityQueries class
 *
 * <p>This class contains integration tests using JUnit to test that all methods in CityQueries behave
 * as expected when interacting with several other classes and methods.</p>
 */
public class CityQueriesIntTest {
    private static DatabaseConnection worldDB;
    private static String output;
    private static ByteArrayOutputStream printOutput;
    private static String exampleContinent;
    private static String exampleCountry;
    private static String exampleRegion;
    private static String exampleDistrict;
    private static Integer exampleTopX;
    private static Integer exampleTooHighTopX;
    private static String exampleInvalid;

    /**
     * Setting up environment
     *
     * <p>This method sets up the test environment by setting a System property 'Environment' to 'IntegrationTest'.
     * This allows certain things in the App class to only be executed in a certain environment.
     * It also sets up the database connection and different example variables.</p>
     */
    @BeforeAll
    public static void setUp() {
        System.setProperty("Environment", "IntegrationTest");
        worldDB = new DatabaseConnection();
        worldDB.connect("localhost:33060", 10000);
        printOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printOutput));
        exampleContinent = "Asia";
        exampleCountry = "Spain";
        exampleRegion = "Caribbean";
        exampleDistrict = "Buenos Aires";
        exampleTopX = 3;
        exampleTooHighTopX = 1000;
        exampleInvalid = "Invalid";
    }


    // tests for the queryResults method


    /**
     * Testing the queryResults method, with correct input and a null topX
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void queryResults_successfulNullTopX() {
        try {
            output = CityQueries.queryResults(worldDB, exampleContinent, "Continent", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Mumbai (Bombay)"));
            assertTrue(lines[lines.length-1].contains("Bandar Seri Begawan"));
        }
    }

    /**
     * Testing the queryResults method, with correct input and an example topX
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void queryResults_successfulTopX() {
        try {
            output = CityQueries.queryResults(worldDB, exampleContinent, "Continent", exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Mumbai"));
            assertTrue(lines[lines.length-1].contains("Shanghai"));
        }
    }

    /**
     * Testing the queryResults method, with correct input and filtering by continent
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void queryResults_successfulContinent() {
        try {
            output = CityQueries.queryResults(worldDB, exampleContinent, "Continent", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Mumbai (Bombay)"));
            assertTrue(lines[lines.length-1].contains("Bandar Seri Begawan"));
        }
    }

    /**
     * Testing the queryResults method, with correct input and filtering by region
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void queryResults_successfulRegion() {
        try {
            output = CityQueries.queryResults(worldDB, exampleRegion, "Region", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("La Habana"));
            assertTrue(lines[lines.length-1].contains("The Valley"));
        }
    }

    /**
     * Testing the queryResults method, with correct input and filtering by country
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void queryResults_successfulCountry() {
        try {
            output = CityQueries.queryResults(worldDB, exampleCountry, "Country", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Madrid"));
            assertTrue(lines[lines.length-1].contains("Torrejón de Ardoz"));
        }
    }

    /**
     * Testing the queryResults method, with correct input and filtering by district
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void queryResults_successfulDistrict() {
        try {
            output = CityQueries.queryResults(worldDB, exampleDistrict, "District", null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("La Matanza"));
            assertTrue(lines[lines.length-1].contains("Tandil"));
        }
    }

    /**
     * Testing the queryResults method, with a negative topX
     *
     * <p>This test checks that if a negative topX is passed into the method it is handled appropriately.
     * The string used to assert is from the inputValidation class, as calling a validation method would be
     * the easiest way of handling this.</p>
     */
    @Test
    public void queryResults_negativeTopX() {
        try {
            output = CityQueries.queryResults(worldDB, exampleContinent, "Continent", -exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(output.contains("Sorry please choose a valid number"));
    }

    /**
     * Testing the queryResults method, with topX = 0
     *
     * <p>This test checks that if topX = 0 is passed into the method it is handled appropriately.
     * The string used to assert is from the inputValidation class, as calling a validation method would be
     * the easiest way of handling this.</p>
     */
    @Test
    public void queryResults_zeroTopX() {
        try {
            output = CityQueries.queryResults(worldDB, exampleContinent, "Continent", 0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(output.contains("Sorry please choose a valid number"));
    }

    /**
     * Testing the queryResults method, with an empty userInput
     *
     * <p>This test checks that if an empty userInput is passed into the method it is handled appropriately.
     * The string used to assert is from the inputValidation class, as calling a validation method would be
     * the easiest way of handling this.</p>
     */
    @Test
    public void queryResults_emptyUserInput() {
        try {
            output = CityQueries.queryResults(worldDB, "", "Continent", exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(output.contains("Field cannot be empty"));
    }


    // tests for the allCitiesInTheWorld method


    /**
     * Testing the allCitiesInTheWorld method, with correct input and a null topX
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void allCitiesInTheWorld_successfulNullTopX() {
        try {
            output = CityQueries.allCitiesInTheWorld(worldDB, null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Mumbai (Bombay)"));
            assertTrue(lines[lines.length-1].contains("Adamstown"));
        }
    }

    /**
     * Testing the allCitiesInTheWorld method, with correct input and an example topX
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void allCitiesInTheWorld_successfulTopX() {
        try {
            output = CityQueries.allCitiesInTheWorld(worldDB, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Mumbai (Bombay)"));
            assertTrue(lines[lines.length-1].contains("São Paulo"));
        }
    }

    /**
     * Testing the allCitiesInTheWorld method, with a negative topX
     *
     * <p>This test checks that if a negative topX is passed into the method it is handled appropriately.
     * The string used to assert is from the inputValidation class, as calling a validation method would be
     * the easiest way of handling this.</p>
     */
    @Test
    public void allCitiesInTheWorld_negativeTopX() {
        try {
            output = CityQueries.allCitiesInTheWorld(worldDB, -exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(output.contains("Sorry please choose a valid number"));
    }

    /**
     * Testing the allCitiesInTheWorld method, with topX = 0
     *
     * <p>This test checks that if topX = 0 is passed into the method it is handled appropriately.
     * The string used to assert is from the inputValidation class, as calling a validation method would be
     * the easiest way of handling this.</p>
     */
    @Test
    public void allCitiesInTheWorld_zeroTopX() {
        try {
            output = CityQueries.allCitiesInTheWorld(worldDB, 0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(output.contains("Sorry please choose a valid number"));
    }

    /**
     * Testing the allCitiesInTheContinent method with example input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void allCitiesInAContinent_successful() {
        try {
            output = CityQueries.allCitiesInAContinent(worldDB, exampleContinent);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Mumbai (Bombay)"));
            assertTrue(lines[lines.length-1].contains("Bandar Seri Begawan"));
        }
    }


    // tests for all other methods


    /**
     * Testing the topXCitiesInTheContinent method with example input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void topXCitiesInAContinent_successful() {
        try {
            output = CityQueries.topXCitiesInAContinent(worldDB, exampleContinent, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Mumbai (Bombay)"));
            assertTrue(lines[lines.length-1].contains("Shanghai"));
        }
    }

    /**
     * Testing the allCitiesInTheRegion method with example input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void allCitiesInARegion_successful() {
        try {
            output = CityQueries.allCitiesInARegion(worldDB, exampleRegion);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("La Habana"));
            assertTrue(lines[lines.length-1].contains("The Valley"));
        }
    }

    /**
     * Testing the topXCitiesInTheRegion method with example input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void topXCitiesInARegion_successful() {
        try {
            output = CityQueries.topXCitiesInARegion(worldDB, exampleRegion, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("La Habana"));
            assertTrue(lines[lines.length-1].contains("Port-au-Prince"));
        }
    }

    /**
     * Testing the allCitiesInTheCountry method with example input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void allCitiesInACountry_successful() {
        try {
            output = CityQueries.allCitiesInACountry(worldDB, exampleCountry);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Madrid"));
            assertTrue(lines[lines.length-1].contains("Torrejón de Ardoz"));
        }
    }

    /**
     * Testing the topXCitiesInACountry method with example input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void topXCitiesInACountry_successful() {
        try {
            output = CityQueries.topXCitiesInACountry(worldDB, exampleCountry, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("Madrid"));
            assertTrue(lines[lines.length-1].contains("Valencia"));
        }
    }

    /**
     * Testing the allCitiesInADistrict method with example input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void allCitiesInADistrict_successful() {
        try {
            output = CityQueries.allCitiesInADistrict(worldDB, exampleDistrict);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("La Matanza"));
            assertTrue(lines[lines.length-1].contains("Tandil"));
        }
    }

    /**
     * Testing the topXCitiesInADistrict method with example input
     *
     * <p>This test checks that no unexpected exception is thrown, and that the first and last cities returned are
     * as expected.</p>
     */
    @Test
    public void topXCitiesInADistrict_successful() {
        try {
            output = CityQueries.topXCitiesInADistrict(worldDB, exampleDistrict, exampleTopX);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String[] lines = output.split("\n");
        if (lines.length > 2) {
            assertTrue(lines[2].contains("La Matanza"));
            assertTrue(lines[lines.length-1].contains("Quilmes"));
        }
    }

}
