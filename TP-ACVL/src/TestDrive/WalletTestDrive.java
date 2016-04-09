package TestDrive;

import Controller.WalletController;
import Controller.WalletControllerInterface;
import Model.WalletModel;
import Model.WalletModelInterface;

public class WalletTestDrive {

    public static void main (String[] args) {
        WalletModelInterface model = new WalletModel();
		@SuppressWarnings("unused")
		WalletControllerInterface controller = new WalletController(model);
    }
}
