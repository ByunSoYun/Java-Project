package com.project.manager;

import java.io.FileNotFoundException;

//import project.src.project1.management;

public class EmployeeManage {

   public void employeeManage() {
      management manage = new management();
      try {
         manage.empllist();
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      manage.menu();
      manage.save();
      }
   
}