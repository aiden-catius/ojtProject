package com.catius.ojtproject.config;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@DataJpaTest(properties = {
        "spring.jpa.properties.hibernate.format_sql=true",
        "spring.jpa.properties.hibernate.use_sql_comments=true"
})
@SpringJUnitConfig(classes = {JpaUserConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommonJpaTestConfiguration {
}
