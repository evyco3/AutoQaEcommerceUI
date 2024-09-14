package com.evy.framework.pages;

import com.evy.framework.pages.account.AccountSection;
import com.evy.framework.pages.product.ProductDropdownSection;

public class HomePage extends BasePage{


    private static final HomePage INSTANCE=new HomePage();


    public static HomePage getInstance(){
        return INSTANCE;
    }

    public ProductDropdownSection navigateToProductDropdownSection(){
        return new ProductDropdownSection();
    }

    public AccountSection navigateToAccountSection(){
        return new AccountSection();
    }

}
