//package com.example.demo.configurations;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class ConfigurationH2MySQL {
//
//    @Bean
//    @Profile("h2")
//    public DataSource dataSourceH2() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:h2:mem:h2_square_games");
//        dataSource.setUsername("admin");
//        dataSource.setPassword("");
//        return dataSource;
//    }
//
//    @Bean
//    @Profile("mysql")
//    public DataSource dataSourceMySQL() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/square_games");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        return dataSource;
//    }
//
//}
