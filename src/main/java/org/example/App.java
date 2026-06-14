package org.example;

import jakarta.persistence.EntityManager;
import util.JPAUtil;

public class App
{
  public static void main( String[] args )
  {
    System.out.println( "Hello World!" );
    EntityManager em = JPAUtil.getEntityManager();
    System.out.println("Conectado!");
    em.close();
  }
}
