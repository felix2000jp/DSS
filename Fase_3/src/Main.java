import Data.PaleteDAO;
import Data.PrateleiraDAO;
import Data.RobotDAO;

public class Main {

    public static void main(String[] args) {
        RobotDAO.getInstance();
        PaleteDAO.getInstance();
        PrateleiraDAO.getInstance();
    }

}