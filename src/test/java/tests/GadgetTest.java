package tests;

import org.testng.annotations.Test;

import pages.Homepages;
import pages.ProductListPages;
import base.BaseTest;
import pages.Homepages;
import pages.ProductListPages;

public class GadgetTest extends BaseTest{
  @Test
  public void test() throws InterruptedException {
	  
	  
	  Homepages hp = new Homepages(driver);
	  
	  ProductListPages pl = new ProductListPages(driver);
	  
	  hp.searchProduct("Bluetooth Headphones");
	  pl.applyFilters("700","1400");
	  pl.printTopFive();
  }
}
