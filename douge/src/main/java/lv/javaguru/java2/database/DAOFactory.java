package lv.javaguru.java2.database;

import lv.javaguru.java2.database.jdbc.*;

/**
 * Created by Radchuk Sergey on 02.11.2014.
 */
public class DAOFactory {

   //---------------------------------------------------------------------------
   // Singleton pattern realization. You can get new instance of DAOFactory using getInstance only!!!!
    private static DAOFactory instance;

    public static DAOFactory getInstance()
    {
        if (instance == null){
            instance = new DAOFactory();
        }
        return instance;
    }

    private DAOFactory()
    {

    }
    //-----------------------------------------------------------------------------


    public FilmDAO getFilmDAO(){
        return  new FilmDAOImpl();
    }

    public Film_ActorDAO getFilm_actorDAO()
    {
        return  new Film_ActorDAOImpl() ;
    }

    public Film_CategoryDAO getFilmCategoryDAO()
    {
        return new Film_CategoryDAOImpl();
    }

    public Film_TextDAO getFilmTextDAO()
    {
        return new Film_TextDAOImpl();
    }

    public ActorDAO getActorDAO()
    {
        return  new ActorDAOImpl();
    }
    public AddressDAO getAddressDAO()
    {
        return new AddressDAOImpl();
    }

    public CategoryDAO getCategoryDAO()
    {
        return  new CategoryDAOImpl();
    }

    public CityDAO getCityDAO()
    {
        return new CityDAOImpl();
    }

    public CountryDAO getCountryDAO()
    {
        return new CountryDAOImpl();
    }

    public CustomerDAO geCustomerDAO()
    {
        return new CustomerDAOImpl();
    }

    public DashboardDAO geDashboardDAO()
    {
        return new DashboardDAOImpl();
    }

    public InventoryDAO geInventoryDAO()
    {
        return new InventoryDAOImpl();
    }

    public LanguageDAO getLanguageDAO()
    {
        return new LanguageDAOImpl();
    }

    public RentalDAO getRentalDAO()
    {
        return new RentalDAOImpl();
    }

    public StaffDAO getStaffDAO()
    {
        return new StaffDAOImpl();
    }

    public StoreDAO  getStoreDAO ()
    {
        return new StoreDAOImpl();
    }

    public UserDAO getUserDAO()
    {
        return new UserDAOImpl();
    }

    public  UserTypeDAO getUserTypeDAO()
    {
        return new UserTypeDAOImpl();
    }

    public WidgetDAO getWidgetDAO()
    {
        return new WidgetDAOImpl();
    }

    public WidgetTypeDAO getWidgetTypeDAO()
    {
        return new WidgetTypeDAOImpl();
    }
}



