// package test.com.hit.test;

// import com.hit.dm.User;
// import com.hit.service.UserService;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import java.util.ArrayList;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// class UserServiceTest {
//     private static final String FILE_NAME = "users.txt";
//     private static UserService userService;

//     @BeforeAll
//     static void setup() {
//         userService = new UserService(FILE_NAME);
//     }

//     @BeforeEach
//     void clearData() {
//         userService.getAllUsers().forEach(userService::deleteUser);
//     }

//     @Test
//     void testCreateUser() {
//         User user = userService.createUser("johndoe", "John Doe", "password", "johndoe@example.com", "male", "555-1234");
//         assertNotNull(user.getId());
//         assertEquals("johndoe", user.username);
//         assertEquals("John Doe", user.fullName);
//         assertEquals("password", user.password);
//         assertEquals("johndoe@example.com", user.email);
//         assertEquals("male", user.gender);
//         assertEquals("555-1234", user.phoneNo);
//     }

//     @Test
//     void testGetUser() {
//         User user1 = userService.createUser("johndoe", "John Doe", "password", "johndoe@example.com", "male", "555-1234");
//         User user2 = userService.getUser(user1.getId());
//         assertNotNull(user2);
//         assertEquals(user1.getId(), user2.getId());
//         assertEquals(user1.username, user2.username);
//         assertEquals(user1.fullName, user2.fullName);
//         assertEquals(user1.password, user2.password);
//         assertEquals(user1.email, user2.email);
//         assertEquals(user1.gender, user2.gender);
//         assertEquals(user1.phoneNo, user2.phoneNo);
//     }

//     @Test
//     void testGetAllUsers() {
//         userService.createUser("johndoe", "John Doe", "password", "johndoe@example.com", "male", "555-1234");
//         userService.createUser("janedoe", "Jane Doe", "password", "janedoe@example.com", "female", "555-5678");
//         ArrayList<User> users = userService.getAllUsers();
//         assertEquals(2, users.size());
//     }

//     @Test
//     void testUpdateUser() {
//         User user = userService.createUser("johndoe", "John Doe", "password", "johndoe@example.com", "male", "555-1234");
//         userService.updateUser(user, "johndoe2", "John Doe Jr.", "newpassword", "johndoe2@example.com", "male", "555-4321");
//         User updatedUser = userService.getUser(user.getId());
//         assertNotNull(updatedUser);
//         assertEquals(user.getId(), updatedUser.getId());
//         assertEquals("johndoe2", updatedUser.username);
//         assertEquals("John Doe Jr.", updatedUser.fullName);
//         assertEquals("newpassword", updatedUser.password);
//         assertEquals("johndoe2@example.com", updatedUser.email);
//         assertEquals("male", updatedUser.gender);
//         assertEquals("555-4321", updatedUser.phoneNo);
//     }

//     @Test
//     void testDeleteUser() {
//         User user = userService.createUser("johndoe", "John Doe", "password", "johndoe@example.com", "male", "555-1234");
//         userService.deleteUser(user);
//         assertNull(userService.getUser(user.getId()));
//     }
// }
