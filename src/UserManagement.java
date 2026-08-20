//Purpose: Defines user management behaviors,
// specifically login and logout functionalities
interface UserManagement {
    void login(String username, String password)
            throws Exception;// throws an exception if login fails
    void logout();
    // method ---> end user's session
}
// purpose of this class ------> interface and provide a user-related operations.