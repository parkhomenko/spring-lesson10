package com.hillel.springboot.school;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.springboot.school.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class StudentControllerWebIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllStudents() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/students", Student[].class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        Student[] body = response.getBody();
        Student student = (Student) body[0];

        assertThat(student.getId(), is(1L));
    }

    @Test
    public void shouldReturnAllStudentsWithJson() throws IOException {
        ResponseEntity<String> response = restTemplate.getForEntity("/students", String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(response.getBody());

        assertThat(responseJson.toString(), is("[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"firstname\": \"Nick\",\n" +
                "        \"lastname\": \"Black\",\n" +
                "        \"age\": 25\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"firstname\": \"John\",\n" +
                "        \"lastname\": \"Doe\",\n" +
                "        \"age\": 35\n" +
                "    }\n" +
                "]"));
    }
}
