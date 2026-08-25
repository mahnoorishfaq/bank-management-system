interface UserManagement {
    void login(String username, String password)
            throws Exception;// throws an exception if login fails
    void logout();
}
// purpose of this class ------> interface and provide a user-related operations.
