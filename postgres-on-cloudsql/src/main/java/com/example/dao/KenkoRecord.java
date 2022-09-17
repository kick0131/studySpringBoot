package com.example.dao;

public class KenkoRecord {
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFood() {
    return food;
  }

  public void setFood(String food) {
    this.food = food;
  }

  private Long id;
  private String name;
  private String food;
  
  @Override
  public String toString() {
    return "KenkoRecord(id=" + id + ", name=" + name + ", food=" + food + ")";
  }
}
