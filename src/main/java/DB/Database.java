package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private String universityDBUrl = "jdbc:mysql://localhost:3306/universitydb";
    private String eventDBUrl = "jdbc:mysql://localhost:3306/eventdb";
    private String username = "root";
    private String password = "root";

    private String findStudentByName = "SELECT student_id, student_program_id FROM STUDENT WHERE LOWER(student_name) like ?";
    private String findStudentNameById = "SELECT student_name FROM STUDENT WHERE student_id = ?";
    private String insertEventParticipant = "INSERT INTO PARTICIPANT (student_id, guest_name, program_id) VALUES (?, ?, ?)";
    private String searchParticipant = "SELECT * FROM PARTICIPANT WHERE LOWER(guest_name) like ?";
    private String studentAlreadyRegistered = "SELECT * FROM PARTICIPANT WHERE student_id = ?";
    private String deleteEventByStudentId = "DELETE FROM PARTICIPANT WHERE student_id = ?";
    private String getAllStudents = "SELECT student_name FROM STUDENT";
    private String getAllStaff = "SELECT staff_name FROM STAFF";
    private String getAllProgramResponsibles = "SELECT pr_name FROM PROGRAM_RESPONSIBLES";
    private String getAllGuests = "SELECT DISTINCT guest_name FROM PARTICIPANT";
    private String getAllPrograms = "SELECT * FROM program";
    private String getStudentsByProgramFromEvent = "SELECT DISTINCT student_id FROM PARTICIPANT where program_id = ?";

    private Connection getEventDBConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(eventDBUrl, username, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed! error: " + e.getMessage());
        }
        return connection;
    }

    private Connection getUniversityDBConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(universityDBUrl, username, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed! error: " + e.getMessage());
        }
        return connection;
    }
    
    public Map<String, Object> findStudentIdByName(String studentName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, Object> student = new HashMap<>();
        try {
            connection = getUniversityDBConnection();
            statement = connection.prepareStatement(findStudentByName);
            statement.setString(1, studentName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student.put("student_id", resultSet.getInt("student_id"));
                student.put("program_id", resultSet.getInt("student_program_id"));
            }
        } catch (SQLException e) {
            System.out.println("FindStudentIdByName failed! error: " + e.getMessage());
        } finally {
            close(connection, statement, resultSet);
        }
        return student;
    }
    
    public void insertEventParticipant(int studentId, String guestName, int programId) { 
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getEventDBConnection();
            statement = connection.prepareStatement(insertEventParticipant);
            statement.setInt(1, studentId);
            statement.setString(2, guestName);
            statement.setInt(3, programId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("InsertEventParticipant failed! error: " + e.getMessage());
        } finally {
            close(connection, statement);
        }
    }
    
    public Map<String, Object> searchParticipant(String guestName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String, Object> result = new HashMap<>();
        try {
            connection = getEventDBConnection();
            statement = connection.prepareStatement(searchParticipant);
            statement.setString(1, guestName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.put("student_id", resultSet.getInt("student_id"));
                result.put("guest_name", resultSet.getString("guest_name"));
                result.put("program_id", resultSet.getString("program_id"));
            }
            if (!result.isEmpty()) {
                getStudentNameById(result);
            }
        } catch (SQLException e) {
            System.out.println("SearchParticipant failed! error: " + e.getMessage());
        } finally {
            close(connection, statement, resultSet);
        }
        return result;
    }
    
    private void getStudentNameById(Map<String, Object> result) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getUniversityDBConnection();
            statement = connection.prepareStatement(findStudentNameById);
            statement.setInt(1, (int) result.get("student_id"));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.put("student_name", resultSet.getString("student_name"));
            }
        } catch (SQLException e) {
            System.out.println("GetStudentNameFromId failed! error: " + e.getMessage());
        } finally {
            close(connection, statement, resultSet);
        }
    }
    
    public boolean checkStudentAlreadyRegistered(int studentId) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getEventDBConnection();
            statement = connection.prepareStatement(studentAlreadyRegistered);
            statement.setInt(1, studentId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("CheckStudentAleadyRegistered failed! error: " + e.getMessage());
        } finally {
            close(connection, statement, resultSet);
        }
        return result;
    }
    
    public void deleteEventByStudentId(int studentId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getEventDBConnection();
            statement = connection.prepareStatement(deleteEventByStudentId);
            statement.setInt(1, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DeleteEventByStudentId failed! error: " + e.getMessage());
        } finally {
            close(connection, statement);
        }
    }
    
    public List<String> getAllParticipants() {
        ArrayList<String> participants = new ArrayList<>();
        participants.addAll(getAllStudents());
        participants.addAll(getAllStaff());
        participants.addAll(getAllProgramResponsibles());
        participants.addAll(getAllGuestParticipants());
        return participants;
    }
    
    public List<String> getAllStudents() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> participants = new ArrayList<>();
        try {
            connection = getUniversityDBConnection();
            statement = connection.prepareStatement(getAllStudents);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                participants.add(resultSet.getString("student_name"));
            }
        } catch (SQLException e) {
            System.out.println("GetAllStudents failed! error: " + e.getMessage());
        } finally {
            close(connection, statement, resultSet);
        }
        return participants;
    }

    private List<String> getAllStaff() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> participants = new ArrayList<>();
        try {
            connection = getUniversityDBConnection();
            statement = connection.prepareStatement(getAllStaff);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                participants.add(resultSet.getString("staff_name"));
            }
        } catch (SQLException e) {
            System.out.println("GetAllStaff failed! error: " + e.getMessage());
        } finally {
            close(connection, statement, resultSet);
        }
        return participants;
    }

    private List<String> getAllProgramResponsibles() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> participants = new ArrayList<>();
        try {
            connection = getUniversityDBConnection();
            statement = connection.prepareStatement(getAllProgramResponsibles);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                participants.add(resultSet.getString("pr_name"));
            }
        } catch (SQLException e) {
            System.out.println("GetAllProgramResponsibles failed! error: " + e.getMessage());
        } finally {
            close(connection, statement, resultSet);
        }
        return participants;
    }
    
    private List<String> getAllGuestParticipants() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> participants = new ArrayList<>();
        try {
            connection = getEventDBConnection();
            statement = connection.prepareStatement(getAllGuests);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                participants.add(resultSet.getString("guest_name"));
            }
        } catch (SQLException e) {
            System.out.println("GetAllGuestParticipants failed! error: " + e.getMessage());
        } finally {
            close(connection, statement, resultSet);
        }
        return participants;
    }
    
    public Map<Integer, String> getAllPrograms() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<Integer, String> programs = new HashMap<>();
        try {
            connection = getUniversityDBConnection();
            statement = connection.prepareStatement(getAllPrograms);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                programs.put(resultSet.getInt("program_id"), resultSet.getString("program_name"));
            }
        } catch (SQLException e) {
            System.out.println("GetAllPrograms failed! error: " + e.getMessage());
        } finally {
            close(connection, statement, resultSet);
        }
        return programs;
    }
    
    public List<String> getStudentsByProgramFromEvent(int programId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> students = new ArrayList<>();
        try {
            connection = getEventDBConnection();
            statement = connection.prepareStatement(getStudentsByProgramFromEvent);
            statement.setInt(1, programId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> student = new HashMap<>();
                student.put("student_id", resultSet.getInt("student_id"));
                getStudentNameById(student);
                students.add((String) student.get("student_name"));
            }
        } catch (SQLException e) {
            System.out.println("GetStudentByProgram failed! error: " + e.getMessage());
        } finally {
            close(connection, statement, resultSet);
        }
        return students;
    }

    private void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! error: " + e.getMessage());
        }
        close(connection, statement);
    }
    
    private void close(Connection connection, PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! error: " + e.getMessage());
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! error: " + e.getMessage());
        }
    }
}