package sk.peterrendek.learn2code.springshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;

@Service
public class Test {


    private JdbcTemplate jdbcTemplate;
        public Test(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplate.execute("select * from customer");

    }



}
