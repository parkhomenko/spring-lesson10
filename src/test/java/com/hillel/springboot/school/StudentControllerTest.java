package com.hillel.springboot.school;

import com.hillel.springboot.school.controller.StudentController;
import com.hillel.springboot.school.model.Student;
import com.hillel.springboot.school.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentRepository studentRepository;

  /*
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }
  */

    @Test
    public void testGetStudent() {
        Student student = new Student();
        student.setId(1L);
        when(studentRepository.findOne(1L)).thenReturn(student);

        Student st = studentController.get(1L);

        verify(studentRepository, times(1)).findOne(1L);

        assertThat(st.getId(), is(1L));
    }
}
