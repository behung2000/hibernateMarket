/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.Vegetable;
import DAL.VegetableDAL;

import java.util.List;

/**
 *
 * @author caothanh
 */
public class VegetableBLL {

  private  VegetableDAL vegDAL;
  
  public VegetableBLL()
  {
      vegDAL = new VegetableDAL();
  }
  
  public Object[][] converVegetable(List<Vegetable> list)
  {
      int rows = list.size();
        int cols = 6;
        Object[][] obj = new Object[rows][cols];
        for(int i = 0; i < rows; i++)
        {
            obj[i][0] = list.get(i).getVegetableID();
            obj[i][1] = list.get(i).getVegetableName();
            obj[i][2] = list.get(i).getUnit();
            obj[i][3] = list.get(i).getAmount();
            obj[i][4] = list.get(i).getImage();
            obj[i][5] = list.get(i).getPrice();
        }
        return obj;
  }

    public List<Vegetable> getList() {
      return vegDAL.getList();
    }

    public List<Vegetable> getListWithCategory(Integer id) {
      return vegDAL.getVegetableInCategory(id);
    }
}
