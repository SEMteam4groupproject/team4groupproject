package com.napier.team4groupproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * the GeneralInfoQueries class provides methods to display each report listed under the general info submenu.
 */

public class GeneralInfoQueries {

    /**
     * Method to query the database, returns the sum of the countries' populations.
     *
     * @param databaseConnection passes in database connection as parameter.
     * @return returns formatted data by passing the retrieved data through the formatOutput function
     */

    public static String populationOfTheWorld(DatabaseConnection databaseConnection) throws SQLException {

        // header for clarity
        System.out.println("Population of the world");

        // sql query to add all the countries' populations
        String query =  "SELECT SUM(Population) FROM country";

        // prepare the query for execution
        try(PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)){

            // executes the query and stores the result in resultSet
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // returns formatted result set
                return App.FormatOutput(resultSet);
            }

        }

        // catches any exceptions which may occur during the query, execution or formatting
        // then prints the exceptions
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    /**
     * Method to query the database, returns the sum of the selected continent's population.
     *
     * @param databaseConnection passes in database connection as parameter.
     * @param continent passes in a continent as a parameter.
     * @return returns formatted data by passing the retrieved data through the formatOutput function
     */

    public static String populationOfAContinent(DatabaseConnection databaseConnection, String continent) throws SQLException {

        // header for clarity
        System.out.println("Population of a continent");

        // validates the user input
        String validatedContinent = InputValidation.validateStringInput(continent);

        // sql query to add all the countries' populations which are part of a specific continent
        String query =  "SELECT SUM(Population) AS Population FROM country WHERE Continent = ?";

        // prepare the query for execution
        try(PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)){

            // sets the validated continent parameter in the query
            preparedStatement.setString(1, validatedContinent);

            // executes the query and stores the result in resultSet
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // returns formatted result set
                return App.FormatOutput(resultSet);

            }

        }

        // catches any exceptions which may occur during the query, execution or formatting
        // then prints the exceptions
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method to query the database, returns the sum of the selected region's population.
     *
     * @param databaseConnection passes in database connection as parameter.
     * @param region passes in a region as a parameter.
     * @return returns formatted data by passing the retrieved data through the formatOutput function
     */

    public static String populationOfARegion(DatabaseConnection databaseConnection, String region) throws SQLException {

        // header for clarity
        System.out.println("Population of a region");

        // validates the user input
        String validatedRegion = InputValidation.validateStringInput(region);

        // sql query to add all the countries' populations which are part of a specific region
        String query =  "SELECT SUM(Population) AS Population FROM country WHERE Region = ?";

        // prepare the query for execution
        try(PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)){

            // sets the validated region parameter in the query
            preparedStatement.setString(1, validatedRegion);

            // executes the query and stores the result in resultSet
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // returns formatted result set
                return App.FormatOutput(resultSet);

            }

        }

        // catches any exceptions which may occur during the query, execution or formatting
        // then prints the exceptions
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method to query the database, returns the selected country's population.
     *
     * @param databaseConnection passes in database connection as parameter.
     * @param country passes in a country as a parameter.
     * @return returns formatted data by passing the retrieved data through the formatOutput function
     */

    public static String populationOfACountry(DatabaseConnection databaseConnection, String country) throws SQLException {

        // header for clarity
        System.out.println("Population of a country");

        // validates the user input
        String validatedCountry = InputValidation.validateStringInput(country);

        // sql query to select the country's population
        String query =  "SELECT Population FROM country WHERE Name = ?";

        // prepare the query for execution
        try(PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)){

            // sets the validated country parameter in the query
            preparedStatement.setString(1, validatedCountry);

            // executes the query and stores the result in resultSet
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // returns formatted result set
                return App.FormatOutput(resultSet);

            }

        }

        // catches any exceptions which may occur during the query, execution or formatting
        // then prints the exceptions
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method to query the database, returns the sum of the selected district's population.
     *
     * @param databaseConnection passes in database connection as parameter.
     * @param district passes in a district as a parameter.
     * @return returns formatted data by passing the retrieved data through the formatOutput function
     */

    public static String populationOfADistrict(DatabaseConnection databaseConnection, String district) throws SQLException {

        // header for clarity
        System.out.println("Population of a country");

        // validates the user input
        String validatedDistrict = InputValidation.validateStringInput(district);

        // sql query to add all the city's populations which are part of a specific district
        String query =  "SELECT SUM(Population) AS Population FROM city WHERE District = ?";

        // prepare the query for execution
        try(PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)){

            // sets the validated district parameter in the query
            preparedStatement.setString(1, validatedDistrict);

            // executes the query and stores the result in resultSet
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // returns formatted result set
                return App.FormatOutput(resultSet);

            }

        }

        // catches any exceptions which may occur during the query, execution or formatting
        // then prints the exceptions
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method to query the database, returns the selected city's population.
     *
     * @param databaseConnection passes in database connection as parameter.
     * @param city passes in a city as a parameter.
     * @return returns formatted data by passing the retrieved data through the formatOutput function
     */

    public static String populationOfACity(DatabaseConnection databaseConnection, String city) throws SQLException {

        // header for clarity
        System.out.println("Population of a country");

        // validates the user input
        String validatedCity = InputValidation.validateStringInput(city);

        // sql query to get the population of a specific city
        String query =  "SELECT Population FROM city WHERE Name = ?";

        // prepare the query for execution
        try(PreparedStatement preparedStatement = databaseConnection.getCon().prepareStatement(query)){

            // sets the validated city parameter in the query
            preparedStatement.setString(1, validatedCity);

            // executes the query and stores the result in resultSet
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // returns formatted result set
                return App.FormatOutput(resultSet);

            }

        }

        // catches any exceptions which may occur during the query, execution or formatting
        // then prints the exceptions
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}