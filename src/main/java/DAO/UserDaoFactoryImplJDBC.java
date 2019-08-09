package DAO;

public class UserDaoFactoryImplJDBC extends UserDaoFactory {
    private UserDaoImplJDBC userDaoImplJDBC;

    @Override
    public UserDaoImplJDBC createDAO() {
        if (this.userDaoImplJDBC == null) {
            this.userDaoImplJDBC = new UserDaoImplJDBC();
        }
        return this.userDaoImplJDBC;
    }
}
