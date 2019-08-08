package DAO;

public class UserDaoFactoryImplJDBC extends UserDaoFactory {
    private UserDAO userDAO;

    @Override
    public UserDAO createDAO() {
        if (this.userDAO == null) {
            this.userDAO = new UserDaoImplJDBC();
        }
        return this.userDAO;
    }
}
