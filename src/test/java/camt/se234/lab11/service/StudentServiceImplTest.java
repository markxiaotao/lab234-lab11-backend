package camt.se234.lab11.service;

import camt.se234.lab11.dao.StudentDao;
import camt.se234.lab11.dao.StudentDaoImpl;
import camt.se234.lab11.entity.Student;
import camt.se234.lab11.exception.DivideZeroException;
import camt.se234.lab11.exception.NoDataException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class StudentServiceImplTest {
    StudentDao studentDao;
    StudentServiceImpl studentService;
    @Before
    public void setup(){
         studentDao = mock (StudentDao.class);
        studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
    }

    @Test
    public void testFindById(){
        StudentDaoImpl studentDao= new StudentDaoImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
        assertThat(studentService.findStudentById("123"),is(new Student("123","A","temp",2.33)));
        assertThat(studentService.findStudentById("124"),is(new Student("124","B","temp1",2.55)));
        assertThat(studentService.findStudentById("125"),is(new Student("125","C","temp2",2.99)));
        assertThat(studentService.findStudentById("126"),is(new Student("126","D","temp3",4.00)));
    }
    @Test
    public void testGetAverageGpa(){
        StudentDaoImpl studentDao= new StudentDaoImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
        assertThat(studentService.getAverageGpa(),is(2.9675000000000002));
    }
    @Test
    public void testWithMock(){

        List<Student> mockStudents =new ArrayList<>();

        mockStudents.add(new Student("123","A","temp",2.33));
        mockStudents.add(new Student("124","B","temp",2.55));
        mockStudents.add(new Student("125","C","temp",2.99));
        mockStudents.add(new Student("126","D","temp",4.00));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentById("123"),is(new Student("123","A","temp",2.33)));
    }
    @Test
    public void testWithMock1(){

        List<Student> mockStudents =new ArrayList<>();

        mockStudents.add(new Student("123","A","temp",2.33));
        mockStudents.add(new Student("124","B","temp",2.55));
        mockStudents.add(new Student("125","C","temp",2.99));
        mockStudents.add(new Student("126","D","temp",4.00));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.getAverageGpa(),is(2.9675000000000002));
    }
    @Test
    public void testFindbyPartOfId(){

        List<Student> mockStudents = new ArrayList<>();

        mockStudents.add(new Student("123","A","temp",2.33));
        mockStudents.add(new Student("124","B","temp",2.33));
        mockStudents.add(new Student("223","C","temp",2.33));
        mockStudents.add(new Student("224","D","temp",2.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentByPartOfId("22"),hasItem(new Student("223","C","temp",2.33)));
       assertThat(studentService.findStudentByPartOfId("22"),hasItems(new Student("223","C","temp",2.33),new Student("224","D","temp",2.33)));
        assertThat(studentService.findStudentByPartOfId("12"),hasItem(new Student("123","A","temp",2.33)));
        assertThat(studentService.findStudentByPartOfId("12"),hasItems(new Student("123","A","temp",2.33),new Student("124","B","temp",2.33)));

    }
    @Test(expected = NoDataException.class)
    public void testNoDataException(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("123","A","temp",2.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentById("55"),nullValue());
    }
    @Test(expected = NoDataException.class)
    public void testNoDataException1(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("123","A","temp",2.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentByPartOfId("55"),nullValue());
    }
    @Test(expected = DivideZeroException.class)
    public void testDivideZeroException(){

        List<Student> mockStudents =new ArrayList<>();

        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.getAverageGpa(),is(2.9675000000000002));
    }




}
